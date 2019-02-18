$(document).ready(function() {
    layui.use([ 'layer','form'], function() {

        var $ = layui.jquery,
            layer = layui.layer;

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

        /**
         * 渲染选中的数据
         * */
        function renderSelectMap() {
            var selectListHtml = '';
            if (selectMap != null) {
                for (var key in selectMap) {
                    selectListHtml += '<li data-id="'+ key +'">';
                    selectListHtml += '     <span>'+ selectMap[key] +'</span>';
                    selectListHtml += '</li>';
                }
            }

            $("#child_template_list").html(selectListHtml);
        }

        /**
         * 渲染选择的上下文
         * */
        function renderSelectContext() {
            var selectListHtml = '';
            if (selectContextMap != null) {
                for (var key in selectContextMap) {
                    selectListHtml += '<li title="" data-id="'+ key +'">';
                    selectListHtml += '     <span>'+ selectContextMap[key] +'</span>';
                    selectListHtml += '</li>';
                }
            }

            $("#context_list").html(selectListHtml);
        }
    });
})
