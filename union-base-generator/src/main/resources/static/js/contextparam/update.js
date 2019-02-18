$(document).ready(function() {
    layui.use(['form', 'layer'], function() {

        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;

        form.on('submit(update-contextparam-filter)', function(data){
            if (data.field.switch == 'on') {
                data.field.status = 1;
            } else {
                data.field.status = 0;
            }

            $.ajax({
               url: '/contextparam/manager/do-update',
               type: 'PUT',
               data: data.field,
               dataType: 'json',
               success: function(data) {
                   if (data.success) {
                       layer.msg("保存成功", {icon: 6});
                       window.location.href = '/contextparam/manager';
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
