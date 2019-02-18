$(document).ready(function() {
    layui.use(['form', 'layer'], function() {

        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;

        form.on('submit(save-user-filter)', function(data){
            if (data.field.switch == 'on') {
                data.field.status = 1;
            } else {
                data.field.status = 0;
            }

            $.ajax({
               url: '/user/manager/do-add',
               type: 'POST',
               data: data.field,
               dataType: 'json',
               success: function(data) {
                   if (data.success) {
                       layer.msg("保存成功", {icon: 6});
                       window.location.href = '/user/manager';
                   } else {
                       layer.msg(data.message, {icon: 5});
                   }
               },
               error: function() {
                   layer.alert("网络超时", {icon: 5});
               }
            });

            return false;
        });

    });
})
