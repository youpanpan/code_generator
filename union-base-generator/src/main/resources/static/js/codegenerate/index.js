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
            url: '/codegenerate/manager/list',
            cols: [[
                {field: 'templateName',  title: '模版名称', templet: '#templateNameTpl'},
                {field: 'codePath',  title: '代码文件', templet: '#codePathTpl'},
                {field: 'codeSize',  title: '代码文件大小', templet: '#codeSizeTpl'},
                {field: 'templatePath',  title: '模版文件', templet: '#templatePathTpl'},
                {field: 'templateType',  title: '模版类型', templet: '#templateTypeTpl'},
                {field: 'createUser',  title: '创建用户', templet: '#createUserTpl'},
                {field: 'createDate',  title: '创建时间'},
                {align:'center',title: '操作', templet: '#opt-col'}
            ]]
        });

        // 绑定搜索点击事件
        $("#j-search-btn").click(function(){
            var className = $("#className").val();
            table.reload('listDataTable01', {
                where: { className: className},
            });

            return false;
        });

        $("#add_codegenerate_btn").click(function() {
           window.location.href = '/codegenerate/manager/add';

           return false;
        });

        form.on('switch(statusSwitch)', function(data){
            var id = data.value;
            var status = 0;
            if (data.elem.checked) {
                status = 1;
            }
            $.ajax({
                url: "/codegenerate/manager/do-update",
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
                window.location.href = "/codegenerate/manager/detail/" + data.id;
            } else if (obj.event === 'delete') {
                layer.confirm("确定删除？", {icon: 3}, function(index) {
                    $.ajax({
                        url: "/codegenerate/manager/delete/" + data.id,
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
