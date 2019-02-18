/**
 * layer工具类
 * @author  youpanpan
 * @date    2019-01-10
 */
var layerUtil = {
    /**
     * 弹出层关闭时设置数据
     * */
    setData: function(data) {
        top.window._return_data = data;
    },

    /**
     * 获取弹出层设置的数据（一次性获取）
     * */
    getData: function() {
        var data = top.window._return_data;
        top.window._return_data = null;
        return data;
    },

    /**
     * 关闭当前iframe层的layer
     * */
    closeFrameLayer: function() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    }
}