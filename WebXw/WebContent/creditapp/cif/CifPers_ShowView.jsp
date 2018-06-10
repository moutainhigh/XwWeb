<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>显示关系图</title>
		<style type="text/css">
 		.centerdiv {
			position:absolute; 
			top:50%;           
			left:30%;          
			margin:-150px 0 0 -100px;   
			width:300px;
			height:200px;
			background:black;
			} 
</style>
	</head>
	
	<body class="body_bg">
	<div class="centerdiv">
	<s:form method="post" theme="simple" name="cms_form"
		action="CifPersInfActionUpdate.action">
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
					<div style="text-align: left;">
						客户关系图
					</div>
					
						<div id="main" style="height:300px;width:400px"></div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</div>
	<!-- ECharts单文件引入 -->
    <script type="text/javascript" src="<%=contextpath%>/creditapp/sys/js/echarts-all.js" charset="utf-8"></script>
    <script type="text/javascript">
        // 使用
          // 基于准备好的dom，初始化echarts图表
          var myChart = echarts.init(document.getElementById('main'));
          
          option = {
    title : {
        text: '<s:property escape='false' value="showRel.title" />',
        x:'right',
        y:'bottom'
    },
    tooltip : {
        trigger: 'item',
        formatter: function (params) {
            if (params.indicator2) {    // is edge
                return  params.indicator+ ' ' + params.name + ' ' + params.indicator2;
            } else {    // is node
                return params.name;
            }
        }
    },
    toolbox: {
        show : true,
        feature : {
            restore : {show: true},
            magicType: {show: true, type: ['force', 'chord']},
            saveAsImage : {show: true}
        }
    },
    legend: {
        x: 'left',
        data:[<s:property escape='false' value="showRel.data" />]
    },
    series : [
        {
            name: '<s:property escape='false' value="showRel.name" />',
            type:'chord',
            sort : 'ascending',
            sortSub : 'descending',
            ribbonType: false,
            radius: '60%',
            itemStyle : {
                normal : {
                    label : {
                        rotate : true
                    }
                }
            },
            minRadius: 7,
            maxRadius: 20,
            // 使用 nodes links 表达和弦图
            nodes: [
                <s:property escape='false' value="showRel.nodes" />
            ],
            links: [
                <s:property escape='false' value="showRel.links" />
            ]
        }
    ]
};
                // 为echarts对象加载数据 
                myChart.setOption(option); 
    </script>
	</body>
</html>