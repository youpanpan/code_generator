$(document).ready(function() {
    layui.use(['table', 'layer', 'laydate', 'form', 'carousel'], function () {
        var $ = layui.jquery,
            table = layui.table,
            layer = layui.layer,
            form = layui.form;
        var carousel = layui.carousel;

        /*----------------------统计--------------------*/
        /**
         * 加载数量统计数据
         * */
        function loadNumberStatistics() {
            $.ajax({
               url: "/get-number-statistics",
               type: "GET",
               dataType: "json",
               success: function(data) {
                   if (data.success) {
                       renderNumberStatistics(data.data);
                   } else {
                       layer.msg("加载数量统计数据出现异常", {icon: 5});
                   }
               },
               error: function() {
                   parent.layer.alert("网络超时", {icon: 5});
               }
            });
        }

        /**
         * 渲染数量统计数据
         * @param   data    数量统计数据
         * */
        function renderNumberStatistics(data) {
            for (var key in data) {
                $("#" + key).html(data[key]);
            }
        }
        loadNumberStatistics();
        /*----------------------/统计--------------------*/


        /*----------------------数据变化趋势--------------------*/
        // 数组每一项为一个对象{chart: echart对象, id: 元素ID, option: echart option配置项}
        var changeCharts = [];
        var changeIndex = 0;

        carousel.render({
            elem: '#change_carousel'
            ,width: '100%' //设置容器宽度
            ,height: '450px'
            ,arrow: 'none' //始终不显示箭头
            ,trigger: 'hover'
            ,autoplay: false
            ,anim: 'fade'
        });
        //监听轮播切换事件
        carousel.on('change(change_carousel)', function(obj){
            var index = obj.index;
            if (changeCharts[index].chart == null) {
                changeCharts[index].chart = echarts.init(document.getElementById(changeCharts[index].id), 'light');
                changeCharts[index].init(changeCharts[index].initArg, index);
            } else {
                changeCharts[index].chart.clear();
                changeCharts[index].chart.resize('auto', 'auto', false);
                changeCharts[index].chart.setOption(changeCharts[obj.index].option);
            }

        });

        /*----------------------代码生成数统计--------------------*/
        var codeNumberOption = {
            color: ['#3398DB'],
            tooltip : {
                trigger: 'axis',
                axisPointer : {            // 坐标轴指示器，坐标轴触发有效
                    type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                top: '2%',
                containLabel: true
            },
            xAxis : [
                {
                    type : 'category',
                    data : ['12-20', '12-21', '12-22', '12-23', '12-24', '12-25', '12-26'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }
            ],
            yAxis : [
                {
                    type : 'value',
                    minInterval: 1
                }
            ],
            series : [
                {
                    name:'代码生成数',
                    type:'bar',
                    barWidth: '60%',
                    data:[10, 52, 200, 334, 390, 330, 220]
                }
            ]
        };

        /**
         * 加载代码生成数统计
         * @param   type    统计类型，1：近一周，2：近一个月，3：近一年
         * @param   chartIndex  图表在数组中的索引
         * */
        function loadCodeNumber(type, chartIndex) {
            $.ajax({
               url: "/get-code-number?type=" + type,
               type: "GET",
               dataType: "json",
               success: function(data) {
                   if (data.success) {
                       renderBarChart(data.data, chartIndex);
                   } else {
                       layer.msg("加载代码生成统计数据发生异常", {icon: 5});
                   }
               },
               error: function() {
                   parent.layer.alert("网络超时", {icon: 5});
               }
            });
        }
        var codeNumberChart = echarts.init(document.getElementById('code_number_chart'), 'light');
        codeNumberChart.setOption(codeNumberOption);
        changeCharts[changeIndex++] = {chart: codeNumberChart, id: "code_number_chart",
            option: codeNumberOption, init: loadCodeNumber, initArg: '1'};

        // 初始加载代码生成数量
        loadCodeNumber('1', 0);

        // 监听统计类型
        form.on('select(code_statistics_type)', function(data){
            loadCodeNumber(data.value, 0);
        });

        /*----------------------/代码生成数统计--------------------*/

        /**
         * 渲染多折线图
         * @param   data    统计数据{xAxis:[], lines:[{"type":'Single',yAxis:[]}, {"type":'Scene',yAxis:[]},{"type":'Linkage',yAxis:[]}]}
         * @param   chartIndex  图表在数组中的索引
         * */
        function renderMultiLineChart(data, chartIndex) {
            changeCharts[chartIndex].option.xAxis[0].data = data.xAxis;

            var notDataMap = {"Single":0, "Scene":1, "Linkage":2};
            for (var index in data.lines) {
                var line = data.lines[index];
                if (line.type === "Single") {
                    changeCharts[chartIndex].option.series[0].data = line.yAxis;
                } else if (line.type === "Scene") {
                    changeCharts[chartIndex].option.series[1].data = line.yAxis;
                } else if (line.type === "Linkage") {
                    changeCharts[chartIndex].option.series[2].data = line.yAxis;
                }
                delete notDataMap[line.type];
            }

            // 设置没数据的series数据为空
            for (var key in notDataMap) {
                changeCharts[chartIndex].option.series[notDataMap[key]].data = [];
            }

            changeCharts[chartIndex].chart.setOption(changeCharts[chartIndex].option);
        }

        /**
         * 渲染柱状图
         * @param   data    统计数据{xAxis:[], yAxis:[]}
         * @param   chartIndex  图表在数组中的索引
         * */
        function renderBarChart(data, chartIndex) {
            changeCharts[chartIndex].option.xAxis[0].data = data.xAxis;
            changeCharts[chartIndex].option.series[0].data = data.yAxis;
            changeCharts[chartIndex].chart.setOption(changeCharts[chartIndex].option);
        }

        /**
         * 渲染饼图
         * @param   data    数据[{key: value}, {key: value},]
         * @param   chartIndex  图表在数组中的索引
         * */
        function renderPieChart(data, chartIndex) {
            var lengends = [];
            var seriesDatas = [];
            for (var i = 0; i < data.length; i++) {
                for (var key in data[i]) {
                    lengends[i] = key;
                    seriesDatas[i] = {value:data[i][key], name:key};
                }
            }
            ratioCharts[chartIndex].option.legend.data = lengends;
            ratioCharts[chartIndex].option.series[0].data = seriesDatas;
            ratioCharts[chartIndex].chart.setOption(ratioCharts[chartIndex].option);
        }

    })

});