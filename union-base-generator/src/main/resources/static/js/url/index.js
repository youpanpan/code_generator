$(document).ready(function() {
    layui.use(['form', 'table','layer'], function() {

        var $ = layui.jquery,
            table = layui.table,
            form = layui.form;

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
                        $(treeId).treeview({data: baseTree, showTags: true});

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
                if (urlList[i].icon != null && urlList[i].icon != '') {
                    nodes[i]['icon'] = urlList[i].icon;
                } else {
                    nodes[i]['icon'] = urlList[i].menu == "1" ? menuIcon : notMenuIcon;
                }
                nodes[i]['state'] = {expanded: true};
                nodes[i]['tags'] = [urlList[i].orderNum];
                nodes[i]['data'] = urlList[i];
                initNode(nodes[i], urlList[i].childUrlList);
            }
            node.nodes = nodes;
        }

        // 1、鼠标右键菜单
        // bootstrap-menu

        // 2、节点颜色、icon区分（菜单与功能URL）
        // 菜单：#428BCA，功能URL：#B73766
        // 菜单：fa fa-th-large，功能URL：fa fa-link
        // 不可用颜色: #ABBAC3

        // 3、根节点不可删除
        // 通过额外的isRoot属性标识

        // 4、节点右键菜单内容获取
        // 通过额外的data属性设置

        // 5、节点内容重新加载并定位到指定节点

        /**
         * 初始化鼠标右键菜单
         * */
        function initMenuContext() {
            var menu=new BootstrapMenu('.node-url_tree',{
                fetchElementData:function($nodeElem){     //fetchElementData获取元数据
                    var nodeId = $($nodeElem[0]).attr("data-nodeid");
                    var data = $(treeId).treeview('getNode', nodeId);
                    return data;    //return的目的是给下面的onClick传递参数
                },

                actionsGroups: [  //给右键菜单的选项加一个分组，分割线
                    ['viewUrl'],
                    ['editUrl'],
                    ['deleteUrl']

                ],
                //自定义右键菜单的功能
                actions: {
                    addUrl: {
                        name: '添加子URL',
                        iconClass: 'fa-plus',
                        onClick: function(data) {
                            var parentId = "";
                            if (!data.isRoot) {
                                parentId = data.data.id;
                            }
                            var parentName = data.text;

                            disableScroll();
                            layer.open({
                                type: 2,
                                title: '新增URL',
                                content: '/url/manager/add?parentId='+ parentId +'&parentName=' + encodeURIComponent(parentName),
                                area: ['800px', '650px'],
                                end: function() {
                                    enableScroll();
                                    var data = layerUtil.getData();
                                    if (data) {
                                        loadAllUrl();
                                    }
                                }
                            });

                        },
                        isEnabled: function(data) {
                            return true;
                        }
                    },
                    viewUrl: {
                        name: '查看',
                        iconClass: 'fa-eye',
                        onClick: function(data) {   //修改右击事件
                            disableScroll();
                            layer.open({
                                type: 2,
                                title: '查看URL',
                                content: '/url/manager/detail/'+ data.data.id,
                                area: ['800px', '650px'],
                                shadeClose: true,
                                end: function() {
                                    enableScroll();
                                }
                            });
                        },
                        isEnabled: function(data) {
                            return !data.isRoot;
                        }
                    },
                    editUrl: {
                        name: '修改',
                        iconClass: 'fa-edit',
                        onClick: function(data) {   //修改右击事件
                            disableScroll();
                            layer.open({
                                type: 2,
                                title: '修改URL',
                                content: '/url/manager/update/'+ data.data.id,
                                area: ['800px', '650px'],
                                end: function() {
                                    enableScroll();
                                    var data = layerUtil.getData();
                                    if (data) {
                                        loadAllUrl();
                                    }
                                }
                            });
                        },
                        isEnabled: function(data) {
                            return !data.isRoot;
                        }
                    },

                    deleteUrl: {
                        name: '删除',
                        iconClass: 'fa-trash',
                        onClick: function(data) {  //删除右击事件
                            layer.confirm("确定删除？", {icon: 3}, function(index) {
                                $.ajax({
                                    url: "/url/manager/delete/" + data.data.id,
                                    type: "DELETE",
                                    dataType: "json",
                                    success: function(data) {
                                        if (data.success) {
                                            layer.msg("删除成功", {icon: 6});
                                            loadAllUrl();
                                        } else {
                                            layer.msg(data.message, {icon: 5});
                                        }
                                    },
                                    error: function() {
                                        parent.layer.alert("网络超时", {icon: 5});
                                    }
                                });
                            });
                        },
                        isEnabled: function(data) {
                            return !data.isRoot;
                        }
                    }
                }
            });
        }
        initMenuContext();
    });
})
