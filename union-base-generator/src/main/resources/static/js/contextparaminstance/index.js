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
            url: '/contextparaminstance/manager/list',
            cols: [[
                {field: 'contextName',  title: '上下文', templet: '#contextNameTpl'},
                {field: 'instanceName',  title: '实例名称'},
                {field: 'instanceDesc', maxWidth: 200,  title: '实例描述'},
                {field: 'openType',  title: '开放类型', templet: '#openTypeTpl'},
                {field: 'createUser',  title: '创建用户', templet: '#createUserTpl'},
                {field: 'updateDate', minWidth: 160,  title: '修改时间'},
                {field: 'orderNum',  title: '排序号'},
                {field: 'status',title: '状态', templet: '#statusTpl'},
                {align:'center',title: '操作', templet: '#opt-col'}
            ]]
        });

        // 绑定搜索点击事件
        $("#j-search-btn").click(function(){
            var contextId = $("#contextId").val();
            var instanceName = $("#instanceName").val();
            table.reload('listDataTable01', {
                where: {
                    contextId: contextId,
                    instanceName: instanceName
                },
            });

            return false;
        });

        $("#add_contextparaminstance_btn").click(function() {
           window.location.href = '/contextparaminstance/manager/add';

           return false;
        });

        form.on('switch(statusSwitch)', function(data){
            var id = data.value;
            var status = 0;
            if (data.elem.checked) {
                status = 1;
            }
            $.ajax({
                url: "/contextparaminstance/manager/do-update",
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
                window.location.href = "/contextparaminstance/manager/detail/" + data.id;
            } else if (obj.event === 'edit') {
                window.location.href = "/contextparaminstance/manager/update/" + data.id;
            } else if (obj.event === 'delete') {
                layer.confirm("确定删除？", {icon: 3}, function(index) {
                    $.ajax({
                        url: "/contextparaminstance/manager/delete/" + data.id,
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
            } else if (obj.event === 'paramvalue') {
                layer.open({
                    type: 2,
                    title: '参数值',
                    content: '/contextparamvalue/manager/listdetail/' + data.contextId + '/' + data.id,
                    area: ['600px', '500px']
                });
            }
        });

    });
})
