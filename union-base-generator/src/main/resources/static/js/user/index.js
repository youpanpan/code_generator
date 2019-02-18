$(document).ready(function() {
    layui.use(['form', 'table','layer'], function() {

        var $ = layui.jquery,
            table = layui.table,
            form = layui.form;

        // 选择的角色
        var selectRoleMap = {};

        table.render({
            elem: '#listDataTable01',
            filter: 'listDataTable01Filter',
            cellMinWidth: 80,//全局定义常规单元格的最小宽度，layui 2.2.1 新增
            height: 500,
            url: '/user/manager/list',
            cols: [[
                
                {field: 'userName',  title: '用户名'},
                {field: 'email',  title: '注册邮箱'},
                {field: 'headPhoto',  title: '用户头像', templet: '#headPhotoTpl'},
                {field: 'updateDate',  title: '修改时间'},
                {field: 'status',title: '状态', templet: '#statusTpl'},
                {align:'center',title: '操作', templet: '#opt-col'}
            ]]
        });

        // 绑定搜索点击事件
        $("#j-search-btn").click(function(){
            var userName = $("#userName").val();
            table.reload('listDataTable01', {
                where: { userName: userName},
            });

            return false;
        });

        $("#add_user_btn").click(function() {
           window.location.href = '/user/manager/add';

           return false;
        });

        form.on('switch(statusSwitch)', function(data){
            var id = data.value;
            var status = 0;
            if (data.elem.checked) {
                status = 1;
            }
            $.ajax({
                url: "/user/manager/do-update",
                type: "PUT",
                data: {id: id, status: status},
                dataType: "json",
                success: function(data) {
                    if (data.success) {
                        layer.msg("修改成功", {icon: 6});
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        });

        // 监听工具条
        table.on('tool(listDataTable01Filter)', function(obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                window.location.href = "/user/manager/detail/" + data.id;
            } else if (obj.event === 'edit') {
                window.location.href = "/user/manager/update/" + data.id;
            } else if (obj.event === 'delete') {
                layer.confirm("确定删除？", {icon: 3}, function(index) {
                    layer.close(index);
                    $.ajax({
                        url: "/user/manager/delete/" + data.id,
                        type: "DELETE",
                        dataType: "json",
                        success: function(data) {
                            if (data.success) {
                                layer.msg("删除成功", {icon: 6});
                                table.reload('listDataTable01');
                            } else {
                                layer.msg(data.message, {icon: 5});
                            }
                        },
                        error: function() {
                            parent.layer.alert("网络超时", {icon: 5});
                        }
                    });
                });
            } else if (obj.event === 'resetpassword') {
                layer.confirm("确定重置密码？", {icon: 3}, function(index) {
                    layer.close(index);
                    $.ajax({
                        url: "/user/manager/reset-password/" + data.id,
                        type: "PUT",
                        dataType: "json",
                        success: function(data) {
                            if (data.success) {
                                layer.msg("重置成功", {icon: 6});
                            } else {
                                layer.msg(data.message, {icon: 5});
                            }
                        },
                        error: function() {
                            parent.layer.alert("网络超时", {icon: 5});
                        }
                    });
                });
            } else if (obj.event === 'userrole') {
                loadUserRole(data.id);
            }
        });

        var layerIndex = -1;
        /**
         * 加载用户权限
         * */
        function loadUserRole(userId) {
            selectRoleMap = {};
            layerIndex = layer.load(2);
            $.ajax({
                url: '/userrole/manager/list/' + userId,
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    layer.close(layerIndex);
                    if (data.success) {
                        var userRoleList = data.data;
                        for (var i = 0; i < userRoleList.length; i++) {
                            selectRoleMap[userRoleList[i].roleId] = userRoleList[i].role.roleName;
                        }

                        openUserRoleWindow(userId);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.close(layerIndex);
                    layer.alert("网络超时", {icon: 5});
                }
            });
        }

        /**
         * 打开用户角色选择窗口
         * */
        function openUserRoleWindow(userId) {
            disableScroll();
            var arr = splitMap(selectRoleMap);

            layer.open({
                type: 2,
                title: '选择角色',
                content: '/role/manager/listselect?ids='+ arr[0] +'&names=' + encodeURIComponent(arr[1]) + '&maxNum=0',
                area: ['1000px', '600px'],
                shadeClose: true,
                end: function() {
                    enableScroll();
                    var data = layerUtil.getData();
                    if (data != null) {
                        selectRoleMap = data;
                        saveUserRole(userId);
                    }
                }
            });
        }

        /**
         * 保存用户角色
         * @param   userId  用户ID
         * */
        function saveUserRole(userId) {
            var params = {};
            params['id'] = userId;

            var userRoleList = [];
            var index = 0;
            for (var key in selectRoleMap) {
                userRoleList[index] = {};
                userRoleList[index]['userId'] = userId;
                userRoleList[index]['roleId'] = key;
                index++;
            }
            params['userRoleList'] = userRoleList;

            $.ajax({
                url: '/userrole/manager/do-save',
                type: 'POST',
                data: JSON.stringify(params),
                dataType: 'json',
                contentType: 'application/json',
                success: function(data) {
                    if (data.success) {
                        layer.msg("保存成功", {icon: 6});
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        }

    });
})
