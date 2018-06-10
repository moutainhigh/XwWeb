<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
String acctType = (String)request.getParameter("acctType");
%>
<html>
	<head>
		<title>详情</title>
<script> 
	var provinceArr = new  Array();
	provinceArr[0] = ['北京市'];  
	provinceArr[1] = ['上海市'];  
	provinceArr[2] = ['天津市']; 
	provinceArr[3] = ['重庆市'];  
	provinceArr[4] = ['河北省'];  
	provinceArr[5] = ['山西省'];
	provinceArr[6] = ['内蒙古自治区'];  
	provinceArr[7] = ['辽宁省'];  
	provinceArr[8] = ['吉林省'];  
	provinceArr[9] = ['黑龙江省'];   
	provinceArr[10] = ['江苏省'];     
	provinceArr[11] = ['浙江省']; 
	provinceArr[12] = ['安徽省'];   
	provinceArr[13] = ['福建省'];  
	provinceArr[14] = ['江西省'];  
	provinceArr[15] = ['山东省'];  
	provinceArr[16] = ['河南省'];  
	provinceArr[17] = ['湖北省'];  
	provinceArr[18] = ['湖南省'];  
	provinceArr[19] = ['广东省'];  
	provinceArr[20] = ['广西省'];  
	provinceArr[21] = ['海南省'];  
	provinceArr[22] = ['四川省'];  
	provinceArr[23] = ['贵州省']; 
	provinceArr[24] = ['云南省'];  
	provinceArr[25] = ['西藏自治区']; ;  
	provinceArr[26] = ['陕西省'];  
	provinceArr[27] = ['甘肃省'];  
	provinceArr[28] = ['宁夏回族自治区'];
	provinceArr[29] = ['青海省'];  
	provinceArr[30] = ['新疆维吾尔自治区'];  
	provinceArr[31] = ['香港特别行政区'];  
	provinceArr[32] = ['澳门特别行政区'];  
	provinceArr[33] = ['台湾省']; 
	var arr = new  Array();
	arr[0]="东城,西城,崇文,宣武,朝阳,丰台,石景山,海淀,门头沟,房山,通州,顺义,昌平,大兴,平谷,怀柔,密云,延庆" 
	arr[1]="黄浦,卢湾,徐汇,长宁,静安,普陀,闸北,虹口,杨浦,闵行,宝山,嘉定,浦东,金山,松江,青浦,南汇,奉贤,崇明" 
	arr[2]="和平,东丽,河东,西青,河西,津南,南开,北辰,河北,武清,红挢,塘沽,汉沽,大港,宁河,静海,宝坻,蓟县" 
	arr[3]="万州,涪陵,渝中,大渡口,江北,沙坪坝,九龙坡,南岸,北碚,万盛,双挢,渝北,巴南,黔江,长寿,綦江,潼南,铜梁,大足,荣昌,壁山,梁平,城口,丰都,垫江,武隆,忠县,开县,云阳,奉节,巫山,巫溪,石柱,秀山,酉阳,彭水,江津,合川,永川,南川" 
	arr[4]="石家庄,邯郸,邢台,保定,张家口,承德,廊坊,唐山,秦皇岛,沧州,衡水" 
	arr[5]="太原,大同,阳泉,长治,晋城,朔州,吕梁,忻州,晋中,临汾,运城" 
	arr[6]="呼和浩特,包头,乌海,赤峰,呼伦贝尔盟,阿拉善盟,哲里木盟,兴安盟,乌兰察布盟,锡林郭勒盟,巴彦淖尔盟,伊克昭盟" 
	arr[7]="沈阳,大连,鞍山,抚顺,本溪,丹东,锦州,营口,阜新,辽阳,盘锦,铁岭,朝阳,葫芦岛" 
	arr[8]="长春,吉林,四平,辽源,通化,白山,松原,白城,延边" 
	arr[9]="哈尔滨,齐齐哈尔,牡丹江,佳木斯,大庆,绥化,鹤岗,鸡西,黑河,双鸭山,伊春,七台河,大兴安岭" 
	arr[10]="南京,镇江,苏州,南通,扬州,盐城,徐州,连云港,常州,无锡,宿迁,泰州,淮安" 
	arr[11]="杭州,宁波,温州,嘉兴,湖州,绍兴,金华,衢州,舟山,台州,丽水" 
	arr[12]="合肥,芜湖,蚌埠,马鞍山,淮北,铜陵,安庆,黄山,滁州,宿州,池州,淮南,巢湖,阜阳,六安,宣城,亳州" 
	arr[13]="福州,厦门,莆田,三明,泉州,漳州,南平,龙岩,宁德" 
	arr[14]="南昌市,景德镇,九江,鹰潭,萍乡,新馀,赣州,吉安,宜春,抚州,上饶" 
	arr[15]="济南,青岛,淄博,枣庄,东营,烟台,潍坊,济宁,泰安,威海,日照,莱芜,临沂,德州,聊城,滨州,菏泽" 
	arr[16]="郑州,开封,洛阳,平顶山,安阳,鹤壁,新乡,焦作,濮阳,许昌,漯河,三门峡,南阳,商丘,信阳,周口,驻马店,济源" 
	arr[17]="武汉,宜昌,荆州,襄樊,黄石,荆门,黄冈,十堰,恩施,潜江,天门,仙桃,随州,咸宁,孝感,鄂州" 
	arr[18]="长沙,常德,株洲,湘潭,衡阳,岳阳,邵阳,益阳,娄底,怀化,郴州,永州,湘西,张家界" 
	arr[19]="广州,深圳,珠海,汕头,东莞,中山,佛山,韶关,江门,湛江,茂名,肇庆,惠州,梅州,汕尾,河源,阳江,清远,潮州,揭阳,云浮" 
	arr[20]="南宁,柳州,桂林,梧州,北海,防城港,钦州,贵港,玉林,南宁地区,柳州地区,贺州,百色,河池" 
	arr[21]="海口,三亚" 
	arr[22]="成都,绵阳,德阳,自贡,攀枝花,广元,内江,乐山,南充,宜宾,广安,达川,雅安,眉山,甘孜,凉山,泸州" 
	arr[23]="贵阳,六盘水,遵义,安顺,铜仁,黔西南,毕节,黔东南,黔南" 
	arr[24]="昆明,大理,曲靖,玉溪,昭通,楚雄,红河,文山,思茅,西双版纳,保山,德宏,丽江,怒江,迪庆,临沧" 
	arr[25]="拉萨,日喀则,山南,林芝,昌都,阿里,那曲" 
	arr[26]="西安,宝鸡,咸阳,铜川,渭南,延安,榆林,汉中,安康,商洛" 
	arr[27]="兰州,嘉峪关,金昌,白银,天水,酒泉,张掖,武威,定西,陇南,平凉,庆阳,临夏,甘南" 
	arr[28]="银川,石嘴山,吴忠,固原" 
	arr[29]="西宁,海东,海南,海北,黄南,玉树,果洛,海西" 
	arr[30]="乌鲁木齐,石河子,克拉玛依,伊犁,巴音郭勒,昌吉,克孜勒苏柯尔克孜,博 尔塔拉,吐鲁番,哈密,喀什,和田,阿克苏" 
	arr[31]="香港" 
	arr[32]="澳门" 
	arr[33]="台北,高雄,台中,台南,屏东,南投,云林,新竹,彰化,苗栗,嘉义,花莲,桃园,宜兰,基隆,台东,金门,马祖,澎湖" 
	function init()
	{
	    var pro = document.getElementsByName("bankProv")[0];
	    var bankProv = document.getElementById("bankProv").value;
	    var city = document.getElementsByName("bankCity")[0]; 
	    var bankCity = document.getElementById("bankCity").value;
	    for(var i=0;i<provinceArr.length;i++)
	    {
	             pro[i]=new Option(provinceArr[i],provinceArr[i]);
	             if(bankProv.indexOf(pro[i].value) >= 0||pro[i].value.indexOf(bankProv) >= 0){
	             	pro[i].selected = true;  
	             	 var cityArr = arr[i].split(",");
				     for(var j=0;j<cityArr.length;j++)
				     {			 
				             city[j]=new Option(cityArr[j],cityArr[j]);
				             if(city[j].value.indexOf(bankCity) >= 0||bankCity.indexOf(city[j].value) >= 0){
				             	city[j].selected = true;  
				             }
				     }
	             }
	    }
	}
	function getCity()
	{    
	    var pro = document.getElementsByName("bankProv")[0];
	    var city = document.getElementsByName("bankCity")[0];
	    var index = pro.selectedIndex;
	    var cityArr = arr[index].split(",");   
	    city.length = 0;
	    //将城市数组中的值填充到城市下拉框中
	    for(var i=0;i<cityArr.length;i++)
	    {
	             city[i]=new Option(cityArr[i],cityArr[i]);
	         }
	}
</script>
	</head>
	<script type="text/javascript">
	
	$(document).ready(function(){
		init();
		var b = document.getElementsByName("acctType")[0]; 
		//b.options.remove(2);   
		
	            for   (var i=0;i<b.length;i++)   
		            {   
		            if(b[i].value==acctType){
		            	b[i].selected = true;  
		            }
		            b[i].disabled=true;   
	            }
	});
	</script>

	<body class="body_bg" >
	<s:form method="post" theme="simple" name="cms_form"
		action="ProjAcctActionUpdateForFK.action" param="projNo=projNo@flag=flag" >
		<div class="right_bg">
			<div class="right_w">
				<div class="from_bg">
					<div class="right_v">
					<s:if test="query=='query'">
						<dhcc:formTag property="formproj0030" mode="query" />
					</s:if>
					<s:else>
						<dhcc:formTag property="formproj0030" mode="query" />
					</s:else>
						<div class="from_btn">
						<s:if test="query!='query'">
							 <dhcc:button typeclass="button3" commit="true" value="保存" action="保存" ></dhcc:button>
							<dhcc:button typeclass="button_form" value="返回" action="返回" onclick="ProjAcctAction_findByPageToProj_Read.action" param="projNo=projNo"></dhcc:button>
						 </s:if>
						 <s:if test="flag!=1">
						 </s:if>
						 <s:if test="backSts == 1">
							<!--<input type="button" value="返回" onclick="javascript:window.location='ProjAcctAction_findByPageForVa.action'" class="button_form"/>
						 --></s:if>
							<s:if test="backSts!=1"><!--
						      <input type="button" value="返回" onclick="javascript:window.location='ProjAcctAction_findByPageToProj.action'" class="button_form"/>
						      --></s:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
<!--	<input type="hidden" id="acctType" value="<s:property value="projAcct.acctType"></s:property>">  -->
	<input type="hidden" id="bankProv" value="<s:property value="projAcct.bankProv"></s:property>">
	<input type="hidden" id="bankCity" value="<s:property value="projAcct.bankCity"></s:property>">
	</body>
</html>