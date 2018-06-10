<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>业务概况图</title>
		<style type="text/css">
 		.centerdiv {
			position:absolute; 
			top:25%;           
			left:25%;          
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
						<div id="main" style="height:600px;width:800px"></div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	</div>
	<script type="text/javascript" src="<%=contextpath%>/themes/js/jquery-1.8.0.min.js"></script>
	<!-- ECharts单文件引入 -->
    <script type="text/javascript" src="<%=contextpath%>/creditapp/sys/js/echarts-all.js" charset="utf-8"></script>
    <script type="text/javascript">
    
    
    var placeList = [
    {name:'海门市', geoCoord:[121.15, 31.89]},
    {name:'鄂尔多斯市', geoCoord:[109.781327, 39.608266]},
    {name:'招远市', geoCoord:[120.38, 37.35]},
    {name:'舟山市', geoCoord:[122.207216, 29.985295]},
    {name:'齐齐哈尔市', geoCoord:[123.97, 47.33]},
    {name:'盐城市', geoCoord:[120.13, 33.38]},
    {name:'赤峰市', geoCoord:[118.87, 42.28]},
    {name:'青岛市', geoCoord:[120.33, 36.07]},
    {name:'乳山市', geoCoord:[121.52, 36.89]},
    {name:'金昌市', geoCoord:[102.188043, 38.520089]},
    {name:'泉州市', geoCoord:[118.58, 24.93]},
    {name:'莱西市', geoCoord:[120.53, 36.86]},
    {name:'日照市', geoCoord:[119.46, 35.42]},
    {name:'胶南市', geoCoord:[119.97, 35.88]},
    {name:'南通市', geoCoord:[121.05, 32.08]},
    {name:'拉萨市', geoCoord:[91.11, 29.97]},
    {name:'云浮市', geoCoord:[112.02, 22.93]},
    {name:'梅州市', geoCoord:[116.1, 24.55]},
    {name:'文登市', geoCoord:[122.05, 37.2]},
    {name:'上海市', geoCoord:[121.48, 31.22]},
    {name:'攀枝花市', geoCoord:[101.718637, 26.582347]},
    {name:'威海市', geoCoord:[122.1, 37.5]},
    {name:'承德市', geoCoord:[117.93, 40.97]},
    {name:'厦门市', geoCoord:[118.1, 24.46]},
    {name:'汕尾市', geoCoord:[115.375279, 22.786211]},
    {name:'潮州市', geoCoord:[116.63, 23.68]},
    {name:'丹东市', geoCoord:[124.37, 40.13]},
    {name:'太仓市', geoCoord:[121.1, 31.45]},
    {name:'曲靖市', geoCoord:[103.79, 25.51]},
    {name:'烟台市', geoCoord:[121.39, 37.52]},
    {name:'福州市', geoCoord:[119.3, 26.08]},
    {name:'瓦房店市', geoCoord:[121.979603, 39.627114]},
    {name:'即墨市', geoCoord:[120.45, 36.38]},
    {name:'抚顺市', geoCoord:[123.97, 41.97]},
    {name:'玉溪市', geoCoord:[102.52, 24.35]},
    {name:'张家口市', geoCoord:[114.87, 40.82]},
    {name:'阳泉市', geoCoord:[113.57, 37.85]},
    {name:'莱州市', geoCoord:[119.942327, 37.177017]},
    {name:'湖州市', geoCoord:[120.1, 30.86]},
    {name:'汕头市', geoCoord:[116.69, 23.39]},
    {name:'昆山市', geoCoord:[120.95, 31.39]},
    {name:'宁波市', geoCoord:[121.56, 29.86]},
    {name:'湛江市', geoCoord:[110.359377, 21.270708]},
    {name:'揭阳市', geoCoord:[116.35, 23.55]},
    {name:'荣成市', geoCoord:[122.41, 37.16]},
    {name:'连云港市', geoCoord:[119.16, 34.59]},
    {name:'葫芦岛市', geoCoord:[120.836932, 40.711052]},
    {name:'常熟市', geoCoord:[120.74, 31.64]},
    {name:'东莞市', geoCoord:[113.75, 23.04]},
    {name:'河源市', geoCoord:[114.68, 23.73]},
    {name:'淮安市', geoCoord:[119.15, 33.5]},
    {name:'泰州市', geoCoord:[119.9, 32.49]},
    {name:'南宁市', geoCoord:[108.33, 22.84]},
    {name:'营口市', geoCoord:[122.18, 40.65]},
    {name:'惠州市', geoCoord:[114.4, 23.09]},
    {name:'江阴市', geoCoord:[120.26, 31.91]},
    {name:'蓬莱市', geoCoord:[120.75, 37.8]},
    {name:'韶关市', geoCoord:[113.62, 24.84]},
    {name:'嘉峪关市', geoCoord:[98.289152, 39.77313]},
    {name:'广州市', geoCoord:[113.23, 23.16]},
    {name:'延安市', geoCoord:[109.47, 36.6]},
    {name:'太原市', geoCoord:[112.53, 37.87]},
    {name:'清远市', geoCoord:[113.01, 23.7]},
    {name:'中山市', geoCoord:[113.38, 22.52]},
    {name:'昆明市', geoCoord:[102.73, 25.04]},
    {name:'寿光市', geoCoord:[118.73, 36.86]},
    {name:'盘锦市', geoCoord:[122.070714, 41.119997]},
    {name:'长治市', geoCoord:[113.08, 36.18]},
    {name:'深圳市', geoCoord:[114.07, 22.62]},
    {name:'珠海市', geoCoord:[113.52, 22.3]},
    {name:'宿迁市', geoCoord:[118.3, 33.96]},
    {name:'咸阳市', geoCoord:[108.72, 34.36]},
    {name:'铜川市', geoCoord:[109.11, 35.09]},
    {name:'平度市', geoCoord:[119.97, 36.77]},
    {name:'佛山市', geoCoord:[113.11, 23.05]},
    {name:'海口市', geoCoord:[110.35, 20.02]},
    {name:'江门市', geoCoord:[113.06, 22.61]},
    {name:'章丘市', geoCoord:[117.53, 36.72]},
    {name:'肇庆市', geoCoord:[112.44, 23.05]},
    {name:'大连市', geoCoord:[121.62, 38.92]},
    {name:'临汾市', geoCoord:[111.5, 36.08]},
    {name:'吴江市', geoCoord:[120.63, 31.16]},
    {name:'石嘴山市', geoCoord:[106.39, 39.04]},
    {name:'沈阳市', geoCoord:[123.38, 41.8]},
    {name:'苏州市', geoCoord:[120.62, 31.32]},
    {name:'茂名市', geoCoord:[110.88, 21.68]},
    {name:'嘉兴市', geoCoord:[120.76, 30.77]},
    {name:'长春市', geoCoord:[125.35, 43.88]},
    {name:'胶州市', geoCoord:[120.03336, 36.264622]},
    {name:'银川市', geoCoord:[106.27, 38.47]},
    {name:'张家港市', geoCoord:[120.555821, 31.875428]},
    {name:'三门峡市', geoCoord:[111.19, 34.76]},
    {name:'锦州市', geoCoord:[121.15, 41.13]},
    {name:'南昌市', geoCoord:[115.89, 28.68]},
    {name:'柳州市', geoCoord:[109.4, 24.33]},
    {name:'三亚市', geoCoord:[109.511909, 18.252847]},
    {name:'自贡市', geoCoord:[104.778442, 29.33903]},
    {name:'吉林市', geoCoord:[126.57, 43.87]},
    {name:'阳江市', geoCoord:[111.95, 21.85]},
    {name:'泸州市', geoCoord:[105.39, 28.91]},
    {name:'西宁市', geoCoord:[101.74, 36.56]},
    {name:'宜宾市', geoCoord:[104.56, 29.77]},
    {name:'呼和浩特市', geoCoord:[111.65, 40.82]},
    {name:'成都市', geoCoord:[104.06, 30.67]},
    {name:'大同市', geoCoord:[113.3, 40.12]},
    {name:'镇江市', geoCoord:[119.44, 32.2]},
    {name:'桂林市', geoCoord:[110.28, 25.29]},
    {name:'张家界市', geoCoord:[110.479191, 29.117096]},
    {name:'宜兴市', geoCoord:[119.82, 31.36]},
    {name:'北海市', geoCoord:[109.12, 21.49]},
    {name:'西安市', geoCoord:[108.95, 34.27]},
    {name:'金坛市', geoCoord:[119.56, 31.74]},
    {name:'东营市', geoCoord:[118.49, 37.46]},
    {name:'牡丹江市', geoCoord:[129.58, 44.6]},
    {name:'遵义市', geoCoord:[106.9, 27.7]},
    {name:'绍兴市', geoCoord:[120.58, 30.01]},
    {name:'扬州市', geoCoord:[119.42, 32.39]},
    {name:'常州市', geoCoord:[119.95, 31.79]},
    {name:'潍坊市', geoCoord:[119.1, 36.62]},
    {name:'重庆市', geoCoord:[106.54, 29.59]},
    {name:'台州市', geoCoord:[121.420757, 28.656386]},
    {name:'南京市', geoCoord:[118.78, 32.04]},
    {name:'滨州市', geoCoord:[118.03, 37.36]},
    {name:'贵阳市', geoCoord:[106.71, 26.57]},
    {name:'无锡市', geoCoord:[120.29, 31.59]},
    {name:'本溪市', geoCoord:[123.73, 41.3]},
    {name:'克拉玛依市', geoCoord:[84.77, 45.59]},
    {name:'渭南市', geoCoord:[109.5, 34.52]},
    {name:'马鞍山市', geoCoord:[118.48, 31.56]},
    {name:'宝鸡市', geoCoord:[107.15, 34.38]},
    {name:'焦作市', geoCoord:[113.21, 35.24]},
    {name:'句容市', geoCoord:[119.16, 31.95]},
    {name:'北京市', geoCoord:[116.46, 39.92]},
    {name:'徐州市', geoCoord:[117.2, 34.26]},
    {name:'衡水市', geoCoord:[115.72, 37.72]},
    {name:'包头市', geoCoord:[110, 40.58]},
    {name:'绵阳市', geoCoord:[104.73, 31.48]},
    {name:'乌鲁木齐市', geoCoord:[87.68, 43.77]},
    {name:'枣庄市', geoCoord:[117.57, 34.86]},
    {name:'杭州市', geoCoord:[120.19, 30.26]},
    {name:'淄博市', geoCoord:[118.05, 36.78]},
    {name:'鞍山市', geoCoord:[122.85, 41.12]},
    {name:'溧阳市', geoCoord:[119.48, 31.43]},
    {name:'库尔勒市', geoCoord:[86.06, 41.68]},
    {name:'安阳市', geoCoord:[114.35, 36.1]},
    {name:'开封市', geoCoord:[114.35, 34.79]},
    {name:'济南市', geoCoord:[117, 36.65]},
    {name:'德阳市', geoCoord:[104.37, 31.13]},
    {name:'温州市', geoCoord:[120.65, 28.01]},
    {name:'九江市', geoCoord:[115.97, 29.71]},
    {name:'邯郸市', geoCoord:[114.47, 36.6]},
    {name:'临安市', geoCoord:[119.72, 30.23]},
    {name:'兰州市', geoCoord:[103.73, 36.03]},
    {name:'沧州市', geoCoord:[116.83, 38.33]},
    {name:'临沂市', geoCoord:[118.35, 35.05]},
    {name:'南充市', geoCoord:[106.110698, 30.837793]},
    {name:'天津市', geoCoord:[117.2, 39.13]},
    {name:'富阳市', geoCoord:[119.95, 30.07]},
    {name:'泰安市', geoCoord:[117.13, 36.18]},
    {name:'诸暨市', geoCoord:[120.23, 29.71]},
    {name:'郑州市', geoCoord:[113.65, 34.76]},
    {name:'哈尔滨市', geoCoord:[126.63, 45.75]},
    {name:'聊城市', geoCoord:[115.97, 36.45]},
    {name:'芜湖市', geoCoord:[118.38, 31.33]},
    {name:'唐山市', geoCoord:[118.02, 39.63]},
    {name:'平顶山市', geoCoord:[113.29, 33.75]},
    {name:'邢台市', geoCoord:[114.48, 37.05]},
    {name:'德州市', geoCoord:[116.29, 37.45]},
    {name:'济宁市', geoCoord:[116.59, 35.38]},
    {name:'荆州市', geoCoord:[112.239741, 30.335165]},
    {name:'宜昌市', geoCoord:[111.3, 30.7]},
    {name:'义乌市', geoCoord:[120.06, 29.32]},
    {name:'丽水市', geoCoord:[119.92, 28.45]},
    {name:'洛阳市', geoCoord:[112.44, 34.7]},
    {name:'秦皇岛市', geoCoord:[119.57, 39.95]},
    {name:'株洲市', geoCoord:[113.16, 27.83]},
    {name:'石家庄市', geoCoord:[114.48, 38.03]},
    {name:'莱芜市', geoCoord:[117.67, 36.19]},
    {name:'常德市', geoCoord:[111.69, 29.05]},
    {name:'保定市', geoCoord:[115.48, 38.85]},
    {name:'湘潭市', geoCoord:[112.91, 27.87]},
    {name:'金华市', geoCoord:[119.64, 29.12]},
    {name:'岳阳市', geoCoord:[113.09, 29.37]},
    {name:'长沙市', geoCoord:[113, 28.21]},
    {name:'衢州市', geoCoord:[118.88, 28.97]},
    {name:'廊坊市', geoCoord:[116.7, 39.53]},
    {name:'菏泽市', geoCoord:[115.480656, 35.23375]},
    {name:'合肥市', geoCoord:[117.27, 31.86]},
    {name:'武汉市', geoCoord:[114.31, 30.52]},
    {name:'大庆市', geoCoord:[125.03, 46.58]},
	{name:'南沙群岛(**)',geoCoord:[117.27, 31.86]},                    
	{name:'凉山彝族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'铜仁地区',geoCoord:[117.27, 31.86]},                        
	{name:'黔南布依族苗族自治州',geoCoord:[117.27, 31.86]},            
	{name:'延边朝鲜族自治州',geoCoord:[117.27, 31.86]},                
	{name:'大兴安岭地区',geoCoord:[117.27, 31.86]},                    
	{name:'恩施土家族苗族自治州',geoCoord:[117.27, 31.86]},            
	{name:'神农架林区',geoCoord:[117.27, 31.86]},                      
	{name:'湘西土家族苗族自治州',geoCoord:[117.27, 31.86]},            
	{name:'兴安盟',geoCoord:[117.27, 31.86]},                          
	{name:'锡林郭勒盟',geoCoord:[117.27, 31.86]},                      
	{name:'阿拉善盟',geoCoord:[117.27, 31.86]},                        
	{name:'西沙群岛(**)',geoCoord:[117.27, 31.86]},                    
	{name:'中沙群岛的岛礁及其海域(**)',geoCoord:[117.27, 31.86]},      
	{name:'阿坝藏族羌族自治州',geoCoord:[117.27, 31.86]},              
	{name:'甘孜藏族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'黔西南布依族苗族自治州',geoCoord:[117.27, 31.86]},          
	{name:'毕节地区',geoCoord:[117.27, 31.86]},                        
	{name:'黔东南苗族侗族自治州',geoCoord:[117.27, 31.86]},            
	{name:'中国',geoCoord:[117.27, 31.86]},                            
	{name:'楚雄彝族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'红河哈尼族彝族自治州',geoCoord:[117.27, 31.86]},            
	{name:'文山壮族苗族自治州',geoCoord:[117.27, 31.86]},              
	{name:'西双版纳傣族自治州',geoCoord:[117.27, 31.86]},              
	{name:'大理白族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'德宏傣族景颇族自治州',geoCoord:[117.27, 31.86]},            
	{name:'怒江傈僳族自治州',geoCoord:[117.27, 31.86]},                
	{name:'迪庆藏族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'昌都地区',geoCoord:[117.27, 31.86]},                        
	{name:'山南地区',geoCoord:[117.27, 31.86]},                        
	{name:'日喀则地区',geoCoord:[117.27, 31.86]},                      
	{name:'那曲地区',geoCoord:[117.27, 31.86]},                        
	{name:'阿里地区',geoCoord:[117.27, 31.86]},                        
	{name:'林芝地区',geoCoord:[117.27, 31.86]},                        
	{name:'临夏回族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'甘南藏族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'海东地区',geoCoord:[117.27, 31.86]},                        
	{name:'海北藏族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'黄南藏族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'海南藏族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'果洛藏族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'玉树藏族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'海西蒙古族藏族自治州',geoCoord:[117.27, 31.86]},            
	{name:'吐鲁番地区',geoCoord:[117.27, 31.86]},                      
	{name:'哈密地区',geoCoord:[117.27, 31.86]},                        
	{name:'昌吉回族自治州',geoCoord:[117.27, 31.86]},                  
	{name:'博尔塔拉蒙古自治州',geoCoord:[117.27, 31.86]},              
	{name:'巴音郭楞蒙古自治州',geoCoord:[117.27, 31.86]},              
	{name:'阿克苏地区',geoCoord:[117.27, 31.86]},                      
	{name:'克孜勒苏柯尔克孜自治州',geoCoord:[117.27, 31.86]},          
	{name:'喀什地区',geoCoord:[117.27, 31.86]},                        
	{name:'和田地区',geoCoord:[117.27, 31.86]},                        
	{name:'伊犁哈萨克自治州',geoCoord:[117.27, 31.86]},                
	{name:'塔城地区',geoCoord:[117.27, 31.86]},                        
	{name:'阿勒泰地区',geoCoord:[117.27, 31.86]},                      
	{name:'台湾省',geoCoord:[117.27, 31.86]},                          
	{name:'香港特别行政区',geoCoord:[117.27, 31.86]},                  
	{name:'澳门特别行政区',geoCoord:[117.27, 31.86]},                  
	{name:'河北省',geoCoord:[117.27, 31.86]},                          
	{name:'山西省',geoCoord:[117.27, 31.86]},                          
	{name:'内蒙古自治区',geoCoord:[117.27, 31.86]},                    
	{name:'辽宁省',geoCoord:[117.27, 31.86]},                          
	{name:'吉林省',geoCoord:[117.27, 31.86]},                          
	{name:'黑龙江省',geoCoord:[117.27, 31.86]},                        
	{name:'江苏省',geoCoord:[117.27, 31.86]},                          
	{name:'浙江省',geoCoord:[117.27, 31.86]},                          
	{name:'安徽省',geoCoord:[117.27, 31.86]},                          
	{name:'福建省',geoCoord:[117.27, 31.86]},                          
	{name:'江西省',geoCoord:[117.27, 31.86]},                          
	{name:'山东省',geoCoord:[117.27, 31.86]},                          
	{name:'河南省',geoCoord:[117.27, 31.86]},                          
	{name:'湖北省',geoCoord:[117.27, 31.86]},                          
	{name:'湖南省',geoCoord:[117.27, 31.86]},                          
	{name:'广东省',geoCoord:[117.27, 31.86]},                          
	{name:'广西壮族自治区',geoCoord:[117.27, 31.86]},                  
	{name:'海南省',geoCoord:[117.27, 31.86]},                          
	{name:'四川省',geoCoord:[117.27, 31.86]},                          
	{name:'贵州省',geoCoord:[117.27, 31.86]},                          
	{name:'云南省',geoCoord:[117.27, 31.86]},                          
	{name:'西藏自治区',geoCoord:[117.27, 31.86]},                      
	{name:'陕西省',geoCoord:[117.27, 31.86]},                          
	{name:'甘肃省',geoCoord:[117.27, 31.86]},                          
	{name:'青海省',geoCoord:[117.27, 31.86]},                          
	{name:'宁夏回族自治区',geoCoord:[117.27, 31.86]},                  
	{name:'新疆维吾尔自治区',geoCoord:[117.27, 31.86]}
]
    
    
    function getIndexByName(list, name){
	var index = -1;
	for(i=0;i<list.length;i++){
		if(list[i].name==name){
			return i;
		}
	}
	
	return index;
}  
    $(document).ready(function(){
    var areas = [<s:property value="rptParams.data1" escape="false" />];
        // 使用
          // 基于准备好的dom，初始化echarts图表
    var myChart = echarts.init(document.getElementById('main'));
    if (areas.length <= 0) {
        myChart.showLoading({
            text: '无数据', //loading话术
            effect : 'bubble'
        });
        return;
    }

option = {
    backgroundColor: 'rgba(0,0,0,0)',
    color: [
        '#FFFAFA',
        '#FFFAFA',
        '#FFFAFA'
    ],
    title : {
        text: '区域业务分布',
        subtext: '',
        x:'center',
        textStyle : {
            color: '#8C8C8C'
        }
    },
    legend: {
    	orient: 'vertical',
        x:'left',
        data:['区域业务分布'],
        textStyle : {
            color: '#8C8C8C'
        }
    },
    toolbox: {
        show : true,
        orient : 'vertical',
        x: 'right',
        y: 'center',
        feature : {
            mark : {show: false},
            dataView : {show: false, readOnly: true},
            restore : {show: true},
            saveAsImage : {show: true}
        }
    },
    series : [
        {
            name: '小于10',
            type: 'map',
            mapType: 'china',
           // scaleLimit:{max:1, min:1},
            itemStyle:{
                normal:{
                    borderColor:'rgba(100,149,237,1)',
                    borderWidth:1.5,
                    areaStyle:{
                        color: '#CD3278'
                    }
                }
            },
            data : [],
            markPoint : {
                symbol : 'diamond',
                symbolSize: 2,
                large: true,
                effect : {
                    show: true
                },
                data : (function(){
                    var data = [];
                    for(var ii=0; ii<areas.length; ii++) {
                    	if(areas[ii].cnt <= 10){
                    		j = getIndexByName(placeList, areas[ii].name);
                          	if(j!=-1){
                          		//alert("小,"+areas[ii].name+",数量："+areas[ii].cnt)
                            	data.push({
                                  	name : placeList[j].name,
                                  	value : areas[ii].cnt,
                                  	geoCoord : placeList[j].geoCoord
                            	});
                          	}
                    	}  
                    }
                   
                    return data;
                })()
            }
        },
        {
            name: '中等',
            type: 'map',
            mapType: 'china',
            itemStyle:{
                normal:{
                    areaStyle:{
                        color: '#CD3278'
                    }
                }
            },
            data : [],
            markPoint : {
                symbol : 'diamond',
                symbolSize: 5,
                large: true,
                effect : {
                    show: true
                },
                data : (function(){
                    var data = [];
                    for(var ii=0; ii<areas.length; ii++) {
                    	if(areas[ii].cnt > 10 && areas[ii].cnt <= 50){
                    		j = getIndexByName(placeList, areas[ii].name);
                          	if(j!=-1){
                          		//alert("中,"+areas[ii].name+",数量："+areas[ii].cnt)
                            	data.push({
                                  	name : placeList[j].name,
                                  	value : areas[ii].cnt,
                                  	geoCoord : placeList[j].geoCoord
                            	});
                          	}
                    	}  
                    }
                   
                    return data;
                })()
            }
        },
        {
            name: '较多',
            type: 'map',
            mapType: 'china',
           // scaleLimit:{max:1, min:1},
            data : [],
            markPoint : {
                symbol : 'diamond',
                symbolSize: 10,
                large: true,
                effect : {
                    show: true
                },
                data : (function(){
                    var data = [];
                    for(var ii=0; ii<areas.length; ii++) {
                    	if(areas[ii].cnt > 50){
                    		j = getIndexByName(placeList, areas[ii].name);
                          	if(j!=-1){
                          		//alert("大,"+areas[ii].name+",数量："+areas[ii].cnt)
                            	data.push({
                                  	name : placeList[j].name,
                                  	value : areas[ii].cnt,
                                  	geoCoord : placeList[j].geoCoord
                            	});
                          	}
                    	}  
                    }
                   
                    return data;
                })()
            }
        }
    ]
};
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
                });
    </script>
	</body>
</html>