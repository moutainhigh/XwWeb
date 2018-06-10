<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<%@ include file="/include/tld.jsp"%>

<html>

	<head>
		<title>显示关系图</title>
	</head>

					   <div  id="myproj_daily_1" style="float:left;height:100%;width:50%;"></div>
					   <div  id="myproj_1" style="float:right;height:100%;width:50%;"></div>


	<!-- ECharts单文件引入 -->
    <script type="text/javascript" src="<%=contextpath%>/creditapp/sys/js/echarts-all.js" charset="utf-8"></script>
    <script type="text/javascript">
     	 function jumplist() {
			window.location = "ProjBaseAction_myProj_echarts.action?projNatu=01";
		}
     
    </script>
    <script type="text/javascript"> 
        var myproj = echarts.init(document.getElementById('myproj_1'));
        var idx = 0;   
    option = {
    title: {
        x: 'center',
        text: '项目规模',
        subtext: '单位：亿',
    },
    //
    tooltip: {
        trigger: 'item',
        formatter: "{b} : {c}"
    },
    calculable: true,
    grid: {
        borderWidth: 0,
        y: 70,
        y2: 20
    },
    xAxis: [
        {
            type: 'category',
            show: false,
            data: [<s:property escape='false' value="projRel.name" />]
        }
    ],
    yAxis: [
        {
            type: 'value',
            show: false
        }
    ],
    series: [
        {
            name: '<s:property escape='false' value="" />',
            type: 'bar',
            //barWidth : 30,//柱图宽度
            itemStyle: {
                normal: {
                    color: function(params) {
                        // build a color map as your need.
                        var colorList = [
                           '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0',
                           '#C1232B','#B5C334','#FCCE10','#E87C25','#27727B',
                           '#FE8463','#9BCA63','#FAD860','#F3A43B','#60C0DD',
                           '#D7504B','#C6E579','#F4E001','#F0805A','#26C0C0'
                       ];
                        return colorList[params.dataIndex]
                   // return "#"+("FF0000"+((Math.random()*16777215+0.1)>>0).toString(16)).slice(-6);
                    },
                    label: {
                        show: true,
                        position: 'top'
                    }
                }
            },
            data: [
                    <s:property escape='false' value="projRel.nodes" />
                  ]

        }
    ]
}; 
                // 为echarts对象加载数据 
               myproj.setOption(option); 
    </script> 
    <script type="text/javascript"> 
        var myproj_daily = echarts.init(document.getElementById('myproj_daily_1'));
        var idx = 0;   
option = {
    title: {
        x: 'center',
        text: '放贷量',
        subtext: ''
    },
    tooltip : {
        trigger: 'item',
        formatter: "{b} : {c} ({d}%)"
    },
//    legend: {
//        orient : 'vertical',
//        x : 'left',
//        data:[<s:property escape='false' value="fundRel1.name" />,<s:property escape='false' value="fundRel2.name" />]
//    },
    calculable : false,
    grid: {
        borderWidth: 0,
        y: 0,
        y2: 0
    },
    series : [
        {
            name:'',  //内环
            type:'pie',//单一选中模式
            selectedMode: 'single',
            radius : [0, 40],//饼图的半径 [内半径，外半径] 
            
            // for funnel
            x: '20%',
            width: '40%',
            funnelAlign: 'right',
            max: 1548,
            
            itemStyle : {
                normal : {
                 //   color: function(params) {
                 //        return "#"+("F00000"+((Math.random()*16777215+0.5)>>0).toString(16)).slice(-6);
                 //   },
                    label : {
                        position : 'inner'//内置文本标签 
                    },
                    labelLine : {
                        show : false //牵引线不设置
                    }
                }
            },
            data:[
                <s:property escape='false' value="projRel2.nodes" />
            ]
        },
        {
            name:'',
            type:'pie',
            radius : [60, 90],      
            
            // for funnel
            x: '60%',
            width: '35%',
            funnelAlign: 'left',
            max: 1048,
            itemStyle : {
             //   normal : {
             //       color: function(params) {
             //            return "#"+("F00000"+((Math.random()*16777215+0.5)>>0).toString(16)).slice(-6);
             //        }
             //       }
                    },
            data:[
              <s:property escape='false' value="projRel1.nodes" />
            ]
        }
    ]
};
                   
                // 为echarts对象加载数据 
               myproj_daily.setOption(option); 
    </script>     
    <body>
    </body>
</html>