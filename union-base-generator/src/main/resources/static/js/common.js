
String.prototype.endWith = function(str){
    if(str==null || str=="" || this.length == 0 ||str.length > this.length){
        return false;
    }
    if(this.substring(this.length - str.length) == str){
        return true;
    }else{
        return false;
    }
};

String.prototype.startWith = function(str){
    if(str == null || str== "" || this.length== 0 || str.length > this.length){
        return false;
    }
    if(this.substr(0,str.length) == str){
        return true;
    }else{
        return false;
    }
};


$(document).ready(function() {
    $(".j-step-backward").click(function(){
        history.go(-1);
    });

    // 初始化基本元素使用，否则无法使用像Tabs这样的动态组件
    layui.use(['element', 'layer'], function() {
        var element = layui.element,
            layer = layui.layer;

        $(".logout-link").click(function() {
            layer.confirm("退出登录", {icon: 3, title: "确定"}, function(index){
                $.ajax({
                    url: '/login/do-logout',
                    type: 'GET',
                    dataType: 'json',
                    success: function(data) {
                        if (data.success) {
                            window.location.href = "/login";
                        } else {
                            layer.msg(data.message, {icon: 5});
                        }
                    },
                    error: function() {
                        layer.alert("网络超时", {icon: 5});
                    }
                });
            });
        });

        $(".lang-link").click(function() {
            var lang = $(this).attr("data-language");
            $.ajax({
                url: '/set-language/' + lang,
                type: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.success) {
                        window.location.reload();
                    } else {
                        layer.msg(data.message, {icon: 5});
                    }
                },
                error: function() {
                    layer.alert("网络超时", {icon: 5});
                }
            });
        });
    });

    // 链接选中状态切换
    $(".nav-list").find("a").each(function() {
        var href = $(this).attr("href");

        if (window.location.href.endWith(href)) {
            $(".nav-list").find("li").each(function() {
                $(this).removeClass("active").removeClass("open");
            });
            $(this).parent("li").parents("li").addClass("active").addClass("open");
            $(this).parent("li").addClass("active");
        }
    });

});

// 分隔符
var SPLIT_SEPARATOR = ",,,";

// 禁用滚动条
function disableScroll() {
    document.documentElement.style.overflowY = 'hidden';
}

// 启用滚动条
function enableScroll() {
    document.documentElement.style.overflowY = 'auto';
}

/**
 * 判断对象中是否有值
 * */
function isEmpty(obj) {
    var count = 0;
    for (var key in obj) {
        count++;
    }
    return count==0;
}

/**
 * 拆解map对象，key为一组，value为一组，每页多个数据用SPLIT_SEPARATOR隔开
 * @return  [key, value]
 * */
function splitMap(map) {
    if (map == null || map == undefined) {
        return ["", ""];
    }

    var keys = [];
    var values = [];
    var index = 0;
    for (var key in map) {
        keys[index] = key;
        values[index] = map[key];
        index++;
    }

    return [keys.join(SPLIT_SEPARATOR), values.join(SPLIT_SEPARATOR)];
}
