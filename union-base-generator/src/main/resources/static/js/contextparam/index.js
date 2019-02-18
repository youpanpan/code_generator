$(document).ready(function() {
    layui.use(['form', 'table','layer'], function() {

        var $ = layui.jquery,
            table = layui.table,
            form = layui.form;

        table.render({
            elem: '#listDataTable01',
            filter: 'listDataTable01Filter',
            cellMinWidth: 80,//全局定义常规单元格的最小宽度，layui 2.2.1 新增
            height: 500,
            url: '/contextparam/manager/list',
            cols: [[
                {field: 'contextName', minWidth: 200, title: '上下文', templet: '#contextNameTpl'},
                {field: 'paramName',  title: '参数名称'},
                {field: 'paramCode',  title: '参数标识'},
                {field: 'paramDefaultValue',  title: '参数默认值'},
                {field: 'paramType',  title: '参数类型', templet: '#paramTypeTpl'},
                {field: 'updateDate', minWidth: 160,  title: '修改时间'},
                {field: 'orderNum',  title: '排序号'},
                {field: 'status',title: '状态', templet: '#statusTpl'},
                {align:'center',title: '操作', templet: '#opt-col'}
            ]]
        });

        // 绑定搜索点击事件
        $("#j-search-btn").click(function(){
            var contextId = $("#contextId").val();
            var paramCode = $("#paramCode").val();
            table.reload('listDataTable01', {
                where: {
                    contextId: contextId,
                    paramCode: paramCode
                },
            });

            return false;
        });

        $("#add_contextparam_btn").click(function() {
           window.location.href = '/contextparam/manager/add';

           return false;
        });

        form.on('switch(statusSwitch)', function(data){
            var id = data.value;
            var status = 0;
            if (data.elem.checked) {
                status = 1;
            }
            $.ajax({
                url: "/contextparam/manager/do-update",
                type: "PUT",
                data: {id: id, status: status},
                dataType: "json",
                success: function(data) {
                    if (data.success) {
                        layer.msg("修改成功", {icon: 6});
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        });

        // 监听工具条
        table.on('tool(listDataTable01Filter)', function(obj) {
            var data = obj.data;
            if (obj.event === 'detail') {
                window.location.href = "/contextparam/manager/detail/" + data.id;
            } else if (obj.event === 'edit') {
                window.location.href = "/contextparam/manager/update/" + data.id;
            } else if (obj.event === 'delete') {
                layer.confirm("确定删除？", {icon: 3}, function(index) {
                    $.ajax({
                        url: "/contextparam/manager/delete/" + data.id,
                        type: "DELETE",
                        dataType: "json",
                        success: function(data) {
                            if (data.success) {
                                layer.msg("删除成功", {icon: 6});
                                table.reload('listDataTable01');
                            } else {
                                layer.msg(data.message, {icon: 5});
                            }
                        },
                        error: function() {
                            parent.layer.alert("网络超时", {icon: 5});
                        }
                    });
                });
            }
        });

    });
})
