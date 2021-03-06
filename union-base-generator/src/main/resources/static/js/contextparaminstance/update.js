$(document).ready(function() {
    layui.use(['form', 'layer'], function() {

        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;

        form.on('submit(update-contextparaminstance-filter)', function(data){
            data.field.status = data.field.switch === 'on' ? 1 : 0;
            data.field.openType = data.field.openTypeSwitch === 'on' ? 1 : 0;

            $.ajax({
               url: '/contextparaminstance/manager/do-update',
               type: 'PUT',
               data: data.field,
               dataType: 'json',
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

            return false;
        });

    });
})
