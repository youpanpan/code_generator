$(document).ready(function() {
    layui.use(['form', 'layer'], function() {

        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer;

        form.on('switch(menu-filter)', function(data){
            if (data.elem.checked) {
                $("#menu_icon").removeClass("hide");
            } else {
                $("#menu_icon").addClass("hide");
            }
        });

        form.on('submit(update-url-filter)', function(data){
            data.field.status = data.field.switch == 'on' ? 1 : 0;
            data.field.menu = data.field.menuSwitch == 'on' ? 1 : 0;

            $.ajax({
               url: '/url/manager/do-update',
               type: 'PUT',
               data: data.field,
               dataType: 'json',
               success: function(data) {
                   if (data.success) {
                       layer.msg("保存成功", {icon: 6});
                       layerUtil.closeFrameLayer();
                       layerUtil.setData(true);
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
