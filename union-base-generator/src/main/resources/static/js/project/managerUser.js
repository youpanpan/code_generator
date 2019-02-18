$(document).ready(function() {
    layui.use(['form', 'layer'], function() {

        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;

        // 项目ID
        var projectId = $("#projectId").val();

        // 已存在的用户,key:用户ID， value: 用户信息
        var existUserMap = {};

        // 选择的用户
        var selectUserMap = {};

        /**
         * 加载已存在项目成员
         * */
        function loadUserProject() {
            $.ajax({
                url: '/userproject/manager/list/' + projectId,
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.success) {
                        var projectUserList = data.data;
                        for (var i = 0; i < projectUserList.length; i++) {
                            existUserMap[projectUserList[i].userId] = projectUserList[i];
                            selectUserMap[projectUserList[i].userId] = projectUserList[i].user.userName;
                        }
                        renderSelectUser();
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                   layer.alert("网络超时", {icon: 5});
                }
            });
        }
        loadUserProject();

        // 选择用户按钮点击
        $("#user_select_btn").click(function(){
            disableScroll();
            var arr = splitMap(selectUserMap);

            layer.open({
                type: 2,
                title: '选择用户',
                content: '/user/manager/listselect?ids='+ arr[0] +'&names=' + encodeURIComponent(arr[1]) + '&maxNum=0',
                area: ['1000px', '600px'],
                end: function() {
                    enableScroll();
                    var data = layerUtil.getData();
                    if (data != null) {
                        selectUserMap = data;
                        renderSelectUser();
                    }
                }
            });

        });

        /**
         * 渲染选择的用户
         * */
        function renderSelectUser() {
            var html = "";
            for (var key in selectUserMap) {
                html += '<li data-id="'+ key +'">';
                html += '   <div class="layui-inline">';
                html += '       <label class="layui-form-label">用户名</label>';
                html += '       <div class="layui-input-inline"><span class="user-name">'+ selectUserMap[key] +'</span></div>';
                html += '   </div>';
                html += '   <div class="layui-inline">';
                html += '       <label class="layui-form-label">项目管理员</label>';
                html += '       <div class="layui-input-inline">';
                if (existUserMap[key] != undefined && existUserMap[key].admin == '1') {
                    html += '           <input type="checkbox" name="switch'+ key +'" checked="true" lay-skin="switch" lay-text="是|否">';
                } else {
                    html += '           <input type="checkbox" name="switch'+ key +'" lay-skin="switch" lay-text="是|否">';
                }
                html += '       </div>';
                html += '   </div>';
                html += '   <div class="layui-inline del-layui-item">';
                html += '       <label class="layui-form-label"><i class="fa fa-close del-icon"></i></label>';
                html += '   </div>';
                html += '</li>';
            }

            $("#user_list").html(html);
            form.render();
        }

        // 删除选中的用户
        $("#user_list").delegate(".del-layui-item", "click", function() {
            var id = $(this).parents("li").attr("data-id");
            delete selectUserMap[id];
            $(this).parents("li").remove();
        });

        form.on('submit(save-userproject-filter)', function(data){

            if (isEmpty(selectUserMap)) {
                layer.msg("请选择项目组成员", {icon: 5});
                return false;
            }

            var arr = [];
            $("#user_list").find("li").each(function(index){
                var userId = $(this).attr("data-id");
                arr[index] = {};
                arr[index]['projectId'] = projectId;
                arr[index]['userId'] = userId;
                arr[index]['admin'] = data.field['switch' + userId] == 'on' ? '1' : '0';
            });

            $.ajax({
                url: '/userproject/manager/do-save',
                type: 'POST',
                data: JSON.stringify(arr),
                dataType: 'json',
                contentType: 'application/json',
                success: function(data) {
                    if (data.success) {
                        layer.msg("保存成功", {icon: 6});
                        window.location.href = '/project/manager';
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });

            return false;

            /*$.ajax({
               url: '/project/manager/do-add',
               type: 'POST',
               data: data.field,
               dataType: 'json',
               success: function(data) {
                   if (data.success) {
                       layer.msg("保存成功", {icon: 6});
                       window.location.href = '/project/manager';
                   } else {
                       layer.msg(data.message, {icon: 5});
                   }
               },
               error: function() {
                   layer.alert("网络超时", {icon: 5});
               }
            });*/

            return false;
        });

    });
})
