$(document).ready(function() {
    layui.use(['form', 'table','layer'], function() {

        var $ = layui.jquery,
            table = layui.table,
            form = layui.form;

        var maxNum = $("#maxNum").val();
        // 选择类型，如果最大数量为1则为单选
        var checkType = maxNum == '1' ? "radio" : "checkbox";

        // 已选角色， key: 角色ID value: 角色名称
        var selectedMap = {};

        /**
         * 初始化已选择数据
         * */
        function initSelect() {
            var ids = $("#ids").val();
            if (ids == "") {
                return;
            }

            var names = $("#names").val();
            var idArr = ids.split(SPLIT_SEPARATOR);
            var nameArr = names.split(SPLIT_SEPARATOR);

            for (var i = 0; i < idArr.length; i++) {
                selectedMap[idArr[i]] = nameArr[i];
            }
            renderSelected();
        }
        initSelect();

        // 当前页数据
        var curPageList = [];

        table.render({
            elem: '#listDataTable01',
            filter: 'listDataTable01Filter',
            where: {status: '1'},
            cellMinWidth: 80,
            height: 460,
            url: '/role/manager/list',
            cols: [[
                {field: 'check', type: checkType},
                {field: 'roleName',  title: '角色名称'},
                {field: 'roleCode',  title: '角色标识'},
            ]],
            done: function(res, curr, count) {
                curPageList = res.data;

                // 选中当前页已选择的数据
                for (var i = 0; i < curPageList.length; i++) {
                    if (selectedMap[curPageList[i].id] != undefined) {
                        var td = $('#listDataTable01').next().find('.layui-table-body tr[data-index="'+ i +'"] td input');
                        td.next().click();
                    }
                }

            }

        });

        // 监听复选框
        table.on('checkbox(listDataTable01Filter)', function(obj){
            if (obj.type === 'one') {
                if (obj.checked) {
                    selectedMap[obj.data.id] = obj.data.roleName;
                } else {
                    delete selectedMap[obj.data.id];
                }
            } else {
                if (obj.checked) {
                    var checkStatus = table.checkStatus('listDataTable01');
                    var selectedArr = checkStatus.data;
                    for (var i = 0; i < selectedArr.length; i++) {
                        selectedMap[selectedArr[i].id] = selectedArr[i].roleName;
                    }
                } else {
                    for (var i = 0; i < curPageList.length; i++) {
                        delete selectedMap[curPageList[i].id];
                    }
                }
            }

            renderSelected();
        });

        // 监听单选框
        table.on('radio(listDataTable01Filter)', function(obj){
            selectedMap = {};
            selectedMap[obj.data.id] = obj.data.roleName;

            renderSelected();
        });

        /**
         * 渲染已选列表
         * */
        function renderSelected() {
            var selectListHtml = '';
            for (var key in selectedMap) {
                selectListHtml += '<li data-id="'+ key +'">';
                selectListHtml += '     <span>'+ selectedMap[key] +'</span>';
                selectListHtml += '     <i class="fa fa-close del-icon"></i>';
                selectListHtml += '</li>';
            }
            $("#has_select_list").html(selectListHtml);
        }

        // 绑定搜索点击事件
        $("#j-search-btn").click(function(){
            var roleName = $("#roleName").val();
            table.reload('listDataTable01', {
                where: { roleName: roleName},
            });

            return false;
        });

        // 确定按钮点击事件
        $("#ok_btn").click(function() {
            layerUtil.setData(selectedMap);
            layerUtil.closeFrameLayer();
        });

        // 已选列表删除点击事件
        $("#has_select_list").delegate("li", "click", function() {
           var id = $(this).attr("data-id");

           var isCurPage = false;

            // 取消选中指定ID的数据
            for (var i = 0; i < curPageList.length; i++) {
                // 如果是当前页的数据，则触发点击事件就行
                if (curPageList[i].id == id) {
                    var td = $('#listDataTable01').next().find('.layui-table-body tr[data-index="'+ i +'"] td input');

                    if (maxNum == '1') {
                        radioCancel(td);
                        selectedMap = {};
                        renderSelected();
                    } else {
                        td.next().click();
                    }

                    isCurPage = true;
                }
            }

            // 如果不是当前页，则需要手动删除并再次渲染
            if (!isCurPage) {
                delete selectedMap[id];
                renderSelected();
            }
        });

        // 清除所有选中
        $("#has_select_clear").click(function(){
            if (isEmpty(selectedMap)) {
                return;
            }
            layer.confirm("确定清除所有选中的数据？", {title: '确认操作', icon: 3}, function(index){
               layer.close(index);
                // 取消所有选中
                for (var i = 0; i < curPageList.length; i++) {
                    if (selectedMap[curPageList[i].id] != undefined) {
                        var td = $('#listDataTable01').next().find('.layui-table-body tr[data-index="'+ i +'"] td input');
                        if (maxNum == '1') {
                            radioCancel(td);
                        } else {
                            td.next().click();
                        }
                    }
                }

                selectedMap = {};
                renderSelected();
            });
        });

        /**
         * 取消选中
         * */
        function radioCancel(input) {
            $(input).prop("checked", false);
            form.render('radio');
        }

    });
})
