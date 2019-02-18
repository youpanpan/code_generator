$(document).ready(function() {
    layui.use(['form', 'table','layer'], function() {

        var $ = layui.jquery,
            table = layui.table,
            form = layui.form;

        var selectedMap = {};

        // 节点基础配置
        var treeId = "#url_tree";
        var menuColor = "#428BCA";
        var notMenuColor = "#B73766";
        var disableColor = "#D9D9D9";
        var menuIcon = "fa fa-link";
        var notMenuIcon = "fa fa-facebook";
        var baseTree = [
            {
                text: "根节点",
                isRoot: true,
                state: {
                    expanded: true
                },
                tags: [''],
                nodes: [

                ]
            }
        ];

        /**
         * 初始化选择的URL
         * */
        function initSelectMap() {
            var ids = $("#ids").val();
            var idArr = ids.split(SPLIT_SEPARATOR);
            for (var i = 0; i < idArr.length; i++) {
                selectedMap[idArr[i]] = true;
            }
        }
        initSelectMap();

        /**
         * 加载所有URL
         * */
        function loadAllUrl() {
            $.ajax({
                url: '/url/manager/list-all',
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.success) {
                        var urlList = data.data;
                        initNode(baseTree[0], urlList);

                        // 初始化treeview
                        $(treeId).treeview({
                            data: baseTree, 
                            showCheckbox: true,
                            onNodeChecked: function(event, node) { //选中节点
                                var selectNodes = getChildNodeIdArr(node); //获取所有子节点
                                if (selectNodes) { //子节点不为空，则选中所有子节点
                                    $(treeId).treeview('checkNode', [selectNodes, { silent: true }]);
                                }
                                setParentNodeCheck(node);
                            },
                            onNodeUnchecked: function(event, node) { //取消选中节点
                                var selectNodes = getChildNodeIdArr(node); //获取所有子节点
                                if (selectNodes) { //子节点不为空，则取消选中所有子节点
                                    $(treeId).treeview('uncheckNode', [selectNodes, { silent: true }]);
                                }
                            }
                        });

                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        }
        loadAllUrl();

        /**
         * 初始化节点下的子节点
         * */
        function initNode(node, urlList) {
            if (urlList == null || urlList == undefined || urlList.length == 0) {
                return;
            }

            var nodes = [];
            for (var i = 0; i < urlList.length; i++) {
                nodes[i] = {};
                nodes[i]['text'] = urlList[i].urlName;
                nodes[i]['color'] = urlList[i].menu == "1" ? menuColor : notMenuColor;
                if (urlList[i].status != "1") {
                    nodes[i]['color'] = disableColor;
                }
                nodes[i]['icon'] = urlList[i].menu == "1" ? menuIcon : notMenuIcon;
                var checked = false;
                if (selectedMap[urlList[i].id]) {
                    checked = true;
                }
                nodes[i]['state'] = {expanded: true, checked: checked};
                nodes[i]['tags'] = [urlList[i].orderNum];
                nodes[i]['data'] = urlList[i];
                initNode(nodes[i], urlList[i].childUrlList);
            }
            node.nodes = nodes;
        }

        // 确定按钮点击事件
        $("#ok_btn").click(function() {
            selectedMap = getSelectMapByNode(0);
            layerUtil.setData(selectedMap);
            layerUtil.closeFrameLayer();
        });

        /**
         * 获取指定节点ID下所有选中的节点信息
         * */
        function getSelectMapByNode(nodeId) {
            var checkedArr = {};
            var curNode = $(treeId).treeview("getNode", nodeId);
            if (!curNode.isRoot && curNode.state.checked) {
                checkedArr[curNode.data.id] = true;
            }
            if (curNode.nodes) {
                for (x in curNode.nodes) {
                    checkedArr = $.extend({}, checkedArr, getSelectMapByNode(curNode.nodes[x].nodeId));
                }
            }

            return checkedArr;
        }

        /**
         * 获取当前节点所有子节点
         * */
        function getChildNodeIdArr(node) {
            var ts = [];
            if (node.nodes) {
                for (x in node.nodes) {
                    ts.push(node.nodes[x].nodeId);
                    if (node.nodes[x].nodes) {
                        var getNodeDieDai = getChildNodeIdArr(node.nodes[x]);
                        for (j in getNodeDieDai) {
                            ts.push(getNodeDieDai[j]);
                        }
                    }
                }
            } else {
                ts.push(node.nodeId);
            }
            return ts;
        }

        /**
         * 设置父节点选中（如果所有子节点选中则父节点选中）
         * */
        function setParentNodeCheck(node) {
            var parentNode = $(treeId).treeview("getNode", node.parentId);
            if (parentNode.nodes) {
                var checkedCount = 0;
                for (x in parentNode.nodes) {
                    if (parentNode.nodes[x].state.checked) {
                        checkedCount ++;
                    } else {
                        break;
                    }
                }
                if (checkedCount === parentNode.nodes.length) {
                    $(treeId).treeview("checkNode", parentNode.nodeId);
                    setParentNodeCheck(parentNode);
                }
            }
        }

    });
})
