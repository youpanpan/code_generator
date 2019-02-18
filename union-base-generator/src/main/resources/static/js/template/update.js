$(document).ready(function() {
    layui.use(['form', 'layer', 'upload'], function() {

        var $ = layui.jquery,
            form = layui.form,
            layer = layui.layer,
            upload = layui.upload;

        var templateId = $("#templateId").val();
        var templateType = $("input[name='templateType']:checked").val();

        // 选中的子模版数据
        var selectMap = {};

        /**
         * 加载之前已选的模版列表
         * */
        function loadChildTemplate() {
            $.ajax({
               url: '/childtemplate/manager/list/' + templateId,
               type: 'GET',
               dataType: 'json',
               success: function(data) {
                   if (data.success) {
                       var list = data.data;
                       for (var i = 0; i < list.length; i++) {
                           selectMap[list[i].template.id] = list[i].template.templateName;
                       }
                       renderSelectMap();
                   } else {
                       layer.msg(data.message, {icon: 5});
                   }
               },
               error: function() {
                   layer.alert("网络超时", {icon: 5});
               }
            });
        }

        // 选中的上下文数据
        var selectContextMap = {};

        /**
         * 加载之前已选的上下文列表
         * */
        function loadContextList() {
            $.ajax({
                url: '/templatecontext/manager/list/' + templateId,
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.success) {
                        var list = data.data;
                        for (var i = 0; i < list.length; i++) {
                            selectContextMap[list[i].contextId] = list[i].context.contextName;
                        }
                        renderSelectContext();
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        }
        loadContextList();

        // 模版类型为组合模版才需要加载子模版
        if (templateType == '2') {
            loadChildTemplate();
        }

        // ---------------------------表单核心-----------------------
        var uploadSuccess = false;
        var layerIndex = -1;

        //执行实例
        var uploadInst = upload.render({
            elem: '#template_file_btn' //绑定元素
            ,url: '/template/manager/upload' //上传接口
            ,accept: 'file'
            ,exts: 'zip|rar|7z'
            ,auto: false
            ,bindAction: '#upload_btn'
            ,before: function() {
                layerIndex = layer.load();
                uploadSuccess = false;
            }
            ,done: function(res){
                layer.close(layerIndex);
                if (res.success) {
                    if (res.data.templatePath == undefined || res.data.templatePath == "") {
                        layer.msg("文件上传失败", {icon: 5});
                    } else {
                        $("#templatePath").val(res.data.templatePath);
                        $("#templateFileName").val(res.data.templateFileName);
                        $("#templateFileSize").val(res.data.templateFileSize);
                        uploadSuccess = true;
                        $("#save_template").click();
                    }

                } else {
                    layer.msg(res.message, {icon: 5});
                }

            }
            ,error: function(){
                //请求异常回调
                layer.close(layerIndex);
                layer.msg("上传模版文件失败", {icon: 5});
            }
        });

        $("#upload_btn").click(function() {
            return false;
        });

        form.on('submit(update-template-filter)', function(data){
            if (data.field.templateType == '1' && !uploadSuccess) {
                if (data.field.engineId == '') {
                    layer.msg("请选择模版引擎", {icon: 5});
                    return false;
                }
                if (data.field.file != undefined && data.field.file != "") {
                    $("#upload_btn").click();
                    return false;
                }
            }

            if (data.field.templateType == '2') {
                if (isEmpty(selectMap)) {
                    layer.msg("请选择子模版", {icon: 5});
                    return false;
                } else {
                    var index = 0;
                    var childTemplateList = [];
                    for (var key in selectMap) {
                        childTemplateList[index] = {};
                        childTemplateList[index]['childTemplateId'] = key;
                        index++;
                    }
                    data.field.childTemplateList = childTemplateList;
                }
            }

            if (isEmpty(selectContextMap)) {
                layer.msg("请选择依赖的上下文", {icon: 5});
                return false;
            }
            var index = 0;
            var templateContextList = [];
            for (var key in selectContextMap) {
                templateContextList[index] = {};
                templateContextList[index]['contextId'] = key;
                index++;
            }
            data.field.templateContextList = templateContextList;

            data.field.status = data.field.switch === 'on' ? 1 : 0;
            data.field.openType = data.field.openTypeSwitch === 'on' ? 1 : 0;

            $.ajax({
                url: '/template/manager/do-update',
                type: 'PUT',
                data: JSON.stringify(data.field),
                dataType: 'json',
                contentType: 'application/json',
                success: function(data) {
                    if (data.success) {
                        layer.msg("保存成功", {icon: 6});
                        window.location.href = '/template/manager';
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

        // ---------------------------/表单核心-----------------------

        // 模版类型监听
        form.on('radio(template-type-filter)', function(data){
            if (data.value == "1") {
                $("#template_file_container").removeClass("hide");
                $("#child_template_container").addClass("hide");
                $("#template_engine_container").removeClass("hide");
            } else {
                $("#child_template_container").removeClass("hide");
                $("#template_file_container").addClass("hide");
                $("#template_engine_container").addClass("hide");
            }

        });


        // 选择子模版
        $("#child_select_btn").click(function() {
            disableScroll();
            var arr = splitMap(selectMap);

            layer.open({
                type: 2,
                title: '子模版',
                content: '/template/manager/listselect?ids='+ arr[0] +'&names=' + arr[1] + '&templateType=1&maxNum=0',
                area: ['1000px', '600px'],
                end: function() {
                    enableScroll();
                    var data = layerUtil.getData();
                    if (data != null) {
                        selectMap = data;
                        renderSelectMap();
                    }
                }
            });
        });

        /**
         * 渲染选中的数据
         * */
        function renderSelectMap() {
            var selectListHtml = '';
            if (selectMap != null) {
                for (var key in selectMap) {
                    selectListHtml += '<li title="删除" data-id="'+ key +'">';
                    selectListHtml += '     <span>'+ selectMap[key] +'</span>';
                    selectListHtml += '     <i class="fa fa-close del-icon"></i>';
                    selectListHtml += '</li>';
                }
            }

            $("#child_template_list").html(selectListHtml);
        }

        // 删除选中的模版
        $("#child_template_list").delegate("li", "click", function() {
            var id = $(this).attr("data-id");
            delete selectMap[id];
            $(this).remove();
        });

        // 选择上下文
        $("#context_select_btn").click(function() {
            disableScroll();
            var arr = splitMap(selectContextMap);

            layer.open({
                type: 2,
                title: '选择上下文',
                content: '/context/manager/listselect?ids='+ arr[0] +'&names=' + arr[1] + '&maxNum=0',
                area: ['1000px', '600px'],
                end: function() {
                    enableScroll();
                    var data = layerUtil.getData();
                    if (data != null) {
                        selectContextMap = data;
                        renderSelectContext();
                    }
                }
            });
        });

        /**
         * 渲染选择的上下文
         * */
        function renderSelectContext() {
            var selectListHtml = '';
            if (selectContextMap != null) {
                for (var key in selectContextMap) {
                    selectListHtml += '<li title="删除" data-id="'+ key +'">';
                    selectListHtml += '     <span>'+ selectContextMap[key] +'</span>';
                    selectListHtml += '     <i class="fa fa-close del-icon"></i>';
                    selectListHtml += '</li>';
                }
            }

            $("#context_list").html(selectListHtml);
        }

        // 删除选中的模版
        $("#context_list").delegate("li", "click", function() {
            var id = $(this).attr("data-id");
            delete selectContextMap[id];
            $(this).remove();
        });

    });
})
