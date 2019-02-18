$(document).ready(function() {
    layui.use(['form', 'layer', 'upload'], function() {

        var $ = layui.jquery,
            form = layui.form,
            upload = layui.upload,
            layer = layui.layer;

        // ---------------------------表单核心-----------------------
        var uploadSuccess = false;
        var layerIndex = -1;

        //执行实例
        var uploadInst = upload.render({
            elem: '#headphoto_file_btn' //绑定元素
            ,url: '/user/manager/upload' //上传接口
            ,auto: false
            ,bindAction: '#upload_btn'
            ,before: function() {
                layerIndex = layer.load();
                uploadSuccess = false;
            }
            ,done: function(res){
                layer.close(layerIndex);
                if (res.success) {
                    if (res.data.headPhoto == undefined || res.data.headPhoto == "") {
                        layer.msg("图片上传失败", {icon: 5});
                    } else {
                        $("#headPhoto").val(res.data.headPhoto);
                        uploadSuccess = true;
                        $("#save_user").click();
                    }

                } else {
                    layer.msg(res.message, {icon: 5});
                }

            }
            ,error: function(){
                //请求异常回调
                layer.close(layerIndex);
                layer.alert("网络超时", {icon: 5});
            }
        });

        $("#upload_btn").click(function() {
            return false;
        });

        form.on('submit(update-user-filter)', function(data){
            if (!uploadSuccess) {
                if (data.field.file != undefined && data.field.file != "") {
                    $("#upload_btn").click();
                    return false;
                }
            }
            data.field.status = data.field.switch === 'on' ? 1 : 0;

            $.ajax({
                url: '/user/manager/do-update',
                type: 'PUT',
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
