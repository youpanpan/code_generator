$(document).ready(function() {
    layui.use(['form', 'table','layer', 'element'], function() {

        var $ = layui.jquery,
            table = layui.table,
            form = layui.form,
            layer = layui.layer,
            element = layui.element;

        // 选择模版
        var selectTemplateMap = {};
        $("#child_select_btn").click(function() {
            disableScroll();
            var arr = splitMap(selectTemplateMap);

            layer.open({
                type: 2,
                title: '选择模版',
                content: '/template/manager/listselect?ids='+ arr[0] +'&names=' + arr[1] + '&templateType=&maxNum=1',
                area: ['1000px', '600px'],
                end: function() {
                    enableScroll();
                    var data = layerUtil.getData();
                    if (data != null) {
                        $("#context_tab .layui-tab-title").removeClass("hide-bottom");
                        selectTemplateMap = data;
                        renderSelectTemplate();
                        var templateId = -1;
                        var templateName = "";
                        for(var key in selectTemplateMap) {
                            templateId = key;
                            templateName = selectTemplateMap[key];
                        }
                        $("#codeFileName").val(templateName + "生成代码");
                        loadTemplateContextList(templateId);
                    }
                }
            });
        });

        /**
         * 渲染选择的模版
         * */
        function renderSelectTemplate() {
            var selectListHtml = '';
            if (selectTemplateMap != null) {
                for (var key in selectTemplateMap) {
                    selectListHtml += '<li data-id="'+ key +'">';
                    selectListHtml += '     <span>'+ selectTemplateMap[key] +'</span>';
                    selectListHtml += '</li>';
                }
            }

            $("#child_template_list").html(selectListHtml);
        }

        // 选择上下文
        var selectContextMap = {};
        /**
         * 加载模版依赖的上下文列表
         * */
        function loadTemplateContextList(templateId) {
            $.ajax({
                url: '/templatecontext/manager/list/' + templateId,
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.success) {
                        var list = data.data;
                        selectContextMap = {};
                        for (var i = 0; i < list.length; i++) {
                            selectContextMap[list[i].contextId] = list[i].context.contextName;
                        }
                        renderSelectTab();
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        }

        // 选择上下文
        /*var selectContextMap = {};
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
                        //renderSelectContext();
                        renderSelectTab();
                    }
                }
            });
        });*/

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

        // 删除选中的上下文
        $("#context_list").delegate("li", "click", function() {
            var id = $(this).attr("data-id");
            deleteTab(id);
            delete selectContextMap[id];
            $(this).remove();
            element.render('tab');
        });

        function renderSelectTab() {
            var hasExistMap = {};
            $("#context_tab .layui-tab .layui-tab-content").find(".layui-tab-item").each(function() {
                var id = $(this).attr("data-id");
                hasExistMap[id] = true;
            })

            var deleteMap = {};
            for (var key in hasExistMap) {
                if (selectContextMap[key] == undefined) {
                    deleteMap[key] = true;
                }
            }
            var empty = isEmpty(hasExistMap);
            var index = 0;
            var titleHtml = '';
            var contentHtml = '';
            for (var key in selectContextMap) {
                if (hasExistMap[key]) {
                    index++;
                    continue;
                }
                if (empty && index == 0) {
                    titleHtml += '<li class="layui-this" data-id="'+ key +'">'+ selectContextMap[key] +'</li>';
                    contentHtml += '<div class="layui-tab-item layui-show" data-id="'+ key +'"></div>';
                } else {
                    titleHtml += '<li data-id="'+ key +'">'+ selectContextMap[key] +'</li>';
                    contentHtml += '<div class="layui-tab-item" data-id="'+ key +'"></div>';
                }
                index++;
            }

            $("#context_tab .layui-tab .layui-tab-title").append(titleHtml);
            $("#context_tab .layui-tab .layui-tab-content").append(contentHtml);

            for (var key in deleteMap) {
                deleteTab(key);
            }

            element.render('tab');

            for (var key in selectContextMap) {
                if (hasExistMap[key]) {
                    continue;
                }
                loadTabContent(key);
            }
        }

        /**
         * 删除指定Tab
         * @param   deleteId    如果删除的tab为激活的tab，则默认选择已有的第一个tab为激活状态
         *
         * */
        function deleteTab(deleteId) {
            // 如果删除的是激活的tab，则选择第一个为选中的tab
            var active = $("#context_tab .layui-tab .layui-tab-title li[data-id='"+ deleteId +"']").hasClass("layui-this");
            $("#context_tab .layui-tab .layui-tab-title li[data-id='"+ deleteId +"']").remove();
            $("#context_tab .layui-tab .layui-tab-content .layui-tab-item[data-id='"+ deleteId +"']").remove();
            if (active && $("#context_tab .layui-tab .layui-tab-title li").length > 0) {
                $("#context_tab .layui-tab .layui-tab-title li").eq(0).addClass("layui-this");
                $("#context_tab .layui-tab .layui-tab-content .layui-tab-item").eq(0).addClass("layui-show");
            }
        }

        function loadTabContent(contentId) {
            $.ajax({
                url: '/contextparam/manager/list/' + contentId,
                type: 'GET',
                dataType: 'html',
                success: function(data) {
                    $("#context_tab .layui-tab .layui-tab-content").find(".layui-tab-item[data-id='"+ contentId +"']").html(data);
                    form.render();
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        }

        // 监听tab删除
        element.on('tabDelete(tab-filter)', function(data){
            var index = data.index;
            var i = 0;
            var contextId = "";
            for (var key in selectContextMap) {
                if (i == index) {
                    contextId = key;
                    break;
                }
                i++;
            }
            delete selectContextMap[contextId];
            renderSelectContext();
        });

        form.on('submit(save-generate-filter)', function(data){
            if (isEmpty(selectTemplateMap)) {
                layer.msg("请选择模版", {icon: 5});
                return false;
            }

            var paramMap = {};
            var contextMap = {};
            $("#context_tab .layui-tab .layui-tab-content").find(".layui-tab-item").each(function() {
                var contextId = $(this).attr("data-id");
                contextMap[contextId] = {};
                $(this).find("input,select").each(function() {
                   var name = $(this).attr("name");
                   var value = $(this).val();
                   if (name != '') {
                       contextMap[contextId][name] = value;
                   }
                });
            });
            paramMap['contextMap'] = contextMap;

            var arr = splitMap(selectTemplateMap);
            paramMap['templateId'] = arr[0];
            paramMap['codeFileName'] = data.field.codeFileName;

            var layerIndex = layer.load(2);
            $.ajax({
                url: '/codegenerate/manager/do-generate',
                type: 'POST',
                data: JSON.stringify(paramMap),
                dataType: 'json',
                contentType: 'application/json',
                success: function(data) {
                    layer.close(layerIndex);
                    if (data.success) {
                        layer.msg("保存成功", {icon: 6});
                        window.location.href = '/codegenerate/manager';
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.close(layerIndex);
                    layer.alert("网络超时", {icon: 5});
                }
            });

            return false;
        });

    });
})
