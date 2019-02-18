$(document).ready(function() {
    layui.use(['form', 'table','layer'], function() {

        var $ = layui.jquery,
            table = layui.table,
            form = layui.form;

        table.render({
            elem: '#listDataTable01',
            filter: 'listDataTable01Filter',
            cellMinWidth: 80,//全局定义常规单元格的最小宽度，layui 2.2.1 新增
            height: 500,
            url: '/role/manager/list',
            cols: [[
                
                {field: 'roleCode',  title: '角色标识符'},
                {field: 'roleName',  title: '角色名称'},
                {field: 'roleDesc',  title: '角色描述'},
                {field: 'updateDate',  title: '修改时间'},
                {align:'center',title: '操作', templet: '#opt-col'}
            ]]
        });

        // 绑定搜索点击事件
        $("#j-search-btn").click(function(){
            var roleName = $("#roleName").val();
            table.reload('listDataTable01', {
                where: { roleName: roleName},
            });

            return false;
        });

        $("#add_role_btn").click(function() {
           window.location.href = '/role/manager/add';

           return false;
        });

        form.on('switch(statusSwitch)', function(data){
            var id = data.value;
            var status = 0;
            if (data.elem.checked) {
                status = 1;
            }
            $.ajax({
                url: "/role/manager/do-update",
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
                window.location.href = "/role/manager/detail/" + data.id;
            } else if (obj.event === 'edit') {
                window.location.href = "/role/manager/update/" + data.id;
            } else if (obj.event === 'delete') {
                layer.confirm("确定删除？谨慎操作！", {icon: 3}, function(index) {
                    $.ajax({
                        url: "/role/manager/delete/" + data.id,
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
            } else if (obj.event === 'url') {
                loadRoleUrl(data.id);
            }
        });

        var selectUrlMap = {};
        var layerIndex = -1;

        /**
         * 加载角色能访问的URL
         * */
        function loadRoleUrl(roleId) {
            selectUrlMap = {};
            layerIndex = layer.load(2);
            $.ajax({
                url: '/roleurl/manager/list/' + roleId,
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    layer.close(layerIndex);
                    if (data.success) {
                        var roleUrlList = data.data;
                        for (var i = 0; i < roleUrlList.length; i++) {
                            selectUrlMap[roleUrlList[i].urlId] = "";
                        }

                        openRoleUrlWindow(roleId);
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
         * 打开选择URL窗口
         * */
        function openRoleUrlWindow(roleId) {
            disableScroll();
            var arr = splitMap(selectUrlMap);

            layer.open({
                type: 2,
                title: '选择URL',
                content: '/url/manager/treeselect?ids='+ arr[0],
                area: ['1000px', '600px'],
                shadeClose: true,
                end: function() {
                    enableScroll();
                    var data = layerUtil.getData();
                    if (data != null) {
                        selectUrlMap = data;
                        saveRoleUrl(roleId);
                    }
                }
            });
        }

        /**
         * 保存角色URL
         * @param   roleId  角色ID
         * */
        function saveRoleUrl(roleId) {
            var params = {};
            params['id'] = roleId;

            var roleUrlList = [];
            var index = 0;
            for (var key in selectUrlMap) {
                roleUrlList[index] = {};
                roleUrlList[index]['roleId'] = roleId;
                roleUrlList[index]['urlId'] = key;
                index++;
            }
            params['roleUrlList'] = roleUrlList;

            $.ajax({
                url: '/roleurl/manager/do-save',
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
