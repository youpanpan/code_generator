$(document).ready(function() {
    layui.use(['form', 'layer'], function() {

        var form = layui.form,
            layer = layui.layer;

        var contextId = $("#contextId").val();
        var instanceId = $("#instanceId").val();

        /**
         * 加载实例参数列表
         * */
        function loadContextParamValueList() {
            $.ajax({
                url: '/contextparamvalue/manager/list/' + contextId + '/' + instanceId,
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.success) {
                        renderContextParamValueList(data.data);
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        }

        /**
         * 渲染实例参数列表
         * @param   list    实例参数列表
         * */
        function renderContextParamValueList(list) {
            var html = '';

            for (var i = 0; i < list.length; i++) {
                html += '<div class="layui-form-item">';
                html += '   <label class="layui-form-label">'+ list[i].contextParam.paramName +'</label>';
                html += '   <div class="layui-input-block">';
                html += '       <input type="hidden" name="contextParamValueList['+ i +'].id" value="'+ (list[i].id==null?"":list[i].id) +'"/>';
                html += '       <input type="hidden" name="contextParamValueList['+ i +'].instanceId" value="'+ instanceId +'"/>';
                html += '       <input type="hidden" name="contextParamValueList['+ i +'].paramId" value="'+ list[i].paramId +'"/>';
                var value = list[i].paramValue;
                if (value == null || value == '') {
                    value = list[i].contextParam.paramDefaultValue;
                }

                var verify = "";
                if (list[i].paramType == '2') {
                    verify = "number";
                }
                html += '       <input type="text" name="contextParamValueList['+ i +'].paramValue"  value="'+ (value==null?"":value) +'"';
                html += '          lay-verify="'+ verify +'" autocomplete="off" class="layui-input" readonly>';
                html += '   </div>';
                html += '</div>';
            }

            $("#param_list_container").html(html);
        }

        loadContextParamValueList();
    });

});