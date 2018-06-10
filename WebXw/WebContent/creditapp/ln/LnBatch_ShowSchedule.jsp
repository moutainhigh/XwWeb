<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>详情</title>
	</head>
	<body class="body_bg">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
						<div id="main" style="height:400px"></div>
						<div class="from_btn">
		         <!--            <dhcc:button typeclass="button_form" value="返回" action="返回" onclick="LnBatchAction_findByPage.action"></dhcc:button>  -->
						</div>
					</div>
				</div>
			</div>
		</div>
	</body>
	<!-- ECharts单文件引入 -->
    <script type="text/javascript" src="<%=contextpath%>/creditapp/sys/js/echarts-all.js"></script>
    <script type="text/javascript">
        // 使用
          // 基于准备好的dom，初始化echarts图表
          var myChart = echarts.init(document.getElementById('main'));
          
          option = {
    title: {
        text: '汇总批次业务进度',
        subtext: '		单位：%',
    },
    tooltip: {
//        trigger: 'item'
  		trigger: 'axis',
        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        },
        formatter: function (params){
        	if(params[0].name=='筛查完成'){
        	return params[0].name + '<br/> 筛查完成笔数 : ' + params[0].series.valChk+'笔';
        	}
        	if(params[0].name=='审批完成'){
        	return params[0].name + '<br/> 审批完成笔数 : ' + params[0].series.valApp+'笔';
        	}
        	if(params[0].name=='放款完成'){
        	return params[0].name + '<br/> 放款完成笔数 : ' + params[0].series.valLn+'笔';
        	}
            return params[0].name + '<br/>'
                   + params[0].seriesName + ' : ' + params[0].value;
        }
    },
    toolbox: {
        
    },
    calculable: true,
    grid: {
        borderWidth: 0,
        y: 80,
        y2: 60
    },
    xAxis: [
        {
        
            type: 'category',
            show: false,
            data: ['筛查完成', '审批完成', '放款完成']
        }
    ],
    yAxis: [
        {
            type: 'value',
            show: true
        }
    ],
    series: [
        {
            name: 'ECharts例子个数统计',
            type: 'bar',
            valChk: <s:property value="valChk"/>,
            valApp: <s:property value="valApp"/>,
            valLn: <s:property value="valLn"/>,
            barWidth : 100,//柱图宽度
            itemStyle: {
                normal: {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                          '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                        ];
                        return colorList[params.dataIndex]
                    },
                    label: {
                        show: true,
                        position: 'top',
                        formatter: '{b}\n{c}'
                    }
                }
            },
            data: [<s:property value="query"/>],
            markPoint: {
                tooltip: {
                    trigger: 'item',
                    backgroundColor: 'rgba(0,0,0,0)',
                    formatter: function(params){
                        return '<img src="' 
                                + params.data.symbol.replace('image://', '')
                                + '"/>';
                    }
                },
                data: [
                    {xAxis:0, y: 350, name:'Line', symbolSize:20, symbol: 'image://../asset/ico/折线图.png'},
                    {xAxis:1, y: 350, name:'Bar', symbolSize:20, symbol: 'image://../asset/ico/柱状图.png'},
                    {xAxis:2, y: 350, name:'Scatter', symbolSize:20, symbol: 'image://../asset/ico/散点图.png'},
                    {xAxis:3, y: 350, name:'K', symbolSize:20, symbol: 'image://../asset/ico/K线图.png'},
                    {xAxis:4, y: 350, name:'Pie', symbolSize:20, symbol: 'image://../asset/ico/饼状图.png'},
                    {xAxis:5, y: 350, name:'Radar', symbolSize:20, symbol: 'image://../asset/ico/雷达图.png'},
                    {xAxis:6, y: 350, name:'Chord', symbolSize:20, symbol: 'image://../asset/ico/和弦图.png'},
                    {xAxis:7, y: 350, name:'Force', symbolSize:20, symbol: 'image://../asset/ico/力导向图.png'},
                    {xAxis:8, y: 350, name:'Map', symbolSize:20, symbol: 'image://../asset/ico/地图.png'},
                    {xAxis:9, y: 350, name:'Gauge', symbolSize:20, symbol: 'image://../asset/ico/仪表盘.png'},
                    {xAxis:10, y: 350, name:'Funnel', symbolSize:20, symbol: 'image://../asset/ico/漏斗图.png'},
                ]
            }
        }
    ]
};
          
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
    </script>
</html>