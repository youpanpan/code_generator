$(document).ready(function() {
    layui.use(['form', 'layer', 'element'], function () {

        var $ = layui.jquery,
            element = layui.element,
            form = layui.form;
        var contextId = "";

        form.on('select(instance-filter)', function(data){
            var instanceId = "-1";
            if (data.value != "") {
                var arr = data.value.split("###");
                instanceId = arr[0];
                contextId = arr[1];
            } else {
                if (contextId == '') {
                    return;
                }
            }

            $.ajax({
               url: '/contextparamvalue/manager/list/'+ contextId +'/' + instanceId,
               type: 'GET',
               dataType: 'json',
               success: function(data) {
                   if (data.success) {
                       renderContextParamValueList(contextId, data.data);
                   } else {
                       layer.msg(data.message, {icon: 5});
                   }
               },
               error: function() {
                   layer.alert("网络超时", {icon: 5});
              }
            });
        });

        /**
         * 渲染实例参数列表
         * @param   list    实例参数列表
         * */
        function renderContextParamValueList(contextId, list) {
            var html = '';

            for (var i = 0; i < list.length; i++) {
                html += '<div class="layui-form-item">';
                html += '   <label class="layui-form-label">'+ list[i].contextParam.paramName +'</label>';
                html += '   <div class="layui-input-block">';
                var value = list[i].paramValue;
                if (value == null || value == '') {
                    value = list[i].contextParam.paramDefaultValue;
                }

                var verify = "";
                if (list[i].paramType == '2') {
                    verify = "number";
                }
                html += '       <input type="text" name="'+ list[i].contextParam.paramCode +'"  value="'+ (value==null?"":value) +'"';
                html += '          lay-verify="'+ verify +'"  placeholder="请输入'+ list[i].contextParam.paramName +'" autocomplete="off" class="layui-input">';
                html += '   </div>';
                html += '</div>';
            }

            $("#"+ contextId +" #param_list_container").html(html);
        }


    });
});