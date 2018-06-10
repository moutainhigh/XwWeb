
<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<script language="JavaScript">
var cssPath = "<%=session.getAttribute("color")%>"; //获取默认皮肤路径
var msgDialogColor = "#faa932";
var msgBgColor = "#dfe4ea";
 if(cssPath=="blue") {
	 msgDialogColor = "#fcc97f";
	 msgBgColor = "#fcc97f";
 }else if(cssPath=="purple") {
	 msgDialogColor = "#970086";
	 msgBgColor = "#ebdee9";
 }else if(cssPath=="green") {
	 msgDialogColor = "#0f8d00";
	 msgBgColor = "#dfebde";
 }else if(cssPath=="red") {
	 msgDialogColor = "#b60045";
	 msgBgColor = "#ebdee3";
 }else if(cssPath=="cyan") {
	 msgDialogColor = "#008282";
	 msgBgColor = "#dfe9e9";
 }else if(cssPath=="orange") {
	 msgDialogColor = "#aa3a00";
	 msgBgColor = "#ebe2de";
 }else if(cssPath=="darkred") {
	 msgDialogColor = "#a1261f";
	 msgBgColor = "#ebe2de";
 }else if(cssPath=="yellow") {
	 msgDialogColor = "#FFCC22";
	 msgBgColor = "#ebe2de";
 }else if(cssPath=="pink") {
	 msgDialogColor = "#f27ca0";
	 msgBgColor = "#ebe2de";
 }else if(cssPath=="silver") {
	 msgDialogColor = "#D1D1D1";
	 msgBgColor = "#ebe2de";
 }
   
 function startCompareAlert(str){
	document.onreadystatechange=function changeselect(){
		if(document.readyState=="complete"){
			 if( str != "" ) {
			 	sAlert(str);
			 }
		}
	}
 }
 
 function sAlert(str,titleStr,call_func) {
	 if(document.getElementById("bgDiv")){
		 return;
	 }
        var msgw, msgh, bordercolor;
        
        var brarray = str.match(/(<br)/g);
        var brnum = 1;
        if(brarray!=null){
	        brnum = brarray.length + 1;
        }
        
        //滚动条置顶
        document.documentElement.scrollTop=0;//非IE Quick
        document.body.scrollTop=0;//IE 兼容模式
  
        msgw = 450;//提示窗口的宽度   
        msgh = 75 + (brnum * 25);//提示窗口的高度   
        titleheight = 25 //提示窗口标题高度   
        bordercolor = msgDialogColor;//提示窗口的边框颜色   
        titlecolor = msgDialogColor;//提示窗口的标题颜色   
        var sWidth, sHeight;
        sWidth = document.body.scrollWidth;//网页正文全文宽
        sHeight = document.body.scrollHeight;//网页正文全文高
        //背景层（大小与窗口有效区域相同，即当弹出对话框时，背景显示为放射状透明灰色）   
        var bgObj = document.createElement("div");//创建一个div对象（背景层）   
        //定义div属性，即相当于
  
        // <div   id= "msgDiv "   align= "center "   style= "background-color:white;   border:1px   solid   #336699;   position:absolute;   left:50%;   top:50%;   font:12px/1.6em   Verdana,Geneva,Arial,Helvetica,sans-serif;   margin-left:-225px;   margin-top:npx;   width:400px;   height:100px;   text-align:center;   line-height:25px;   z-index:100001; "> __tag_35$351_  
        bgObj.setAttribute("id", "bgDiv");
        bgObj.style.position = "absolute";
        bgObj.style.top = "0";
        bgObj.style.background = msgBgColor;
        bgObj.style.filter = "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75";
        bgObj.style.opacity = "0.6";
        bgObj.style.left = "0";
        bgObj.style.width = sWidth + "px";
        bgObj.style.height = sHeight + "px";
        bgObj.style.zIndex = "10000";
        document.body.appendChild(bgObj);//在body内添加该div对象   
  
        var msgObj = document.createElement("div")//创建一个div对象（提示框层）   
        //定义div属性，即相当于
        // <div   id= "msgDiv "   align= "center "   style= "background-color:white;   border:1px   solid   #336699;   position:absolute;   left:50%;   top:50%;   font:12px/1.6em   Verdana,Geneva,Arial,Helvetica,sans-serif;   margin-left:-225px;   margin-top:npx;   width:400px;   height:100px;   text-align:center;   line-height:25px;   z-index:100001; "> __tag_72$353_   
        msgObj.setAttribute("id", "msgDiv");
        msgObj.setAttribute("align", "center");
        msgObj.style.background = "white";
        msgObj.style.border = "1px solid";
        msgObj.style.position = "absolute";
        msgObj.style.left = "50%";
        msgObj.style.top = "100px";
        msgObj.style.font = "12px/1.6em Verdana,Geneva,Arial,Helvetica,sans-serif";
        msgObj.style.marginLeft = "-225px";
        msgObj.style.marginTop = document.documentElement.scrollTop + "px";
        msgObj.style.width = msgw + "px";
        //msgObj.style.height = msgh + "px";
        msgObj.style.height = "auto";
        msgObj.style.textAlign = "center";
        msgObj.style.lineHeight = "25px";
        msgObj.style.zIndex = "10001";
  
        var title = document.createElement("h4");//创建一个h4对象（提示框标题栏）   
        //定义h4的属性，即相当于   
        // <h4   id= "msgTitle "   align= "right "   style= "margin:0;   padding:3px;   background-color:#336699;   filter:progid:DXImageTransform.Microsoft.Alpha(startX=20,   startY=20,   finishX=100,   finishY=100,style=1,opacity=75,finishOpacity=100);   opacity:0.75;   border:1px   solid   #336699;   height:18px;   font:12px   Verdana,Geneva,Arial,Helvetica,sans-serif;   color:white;   cursor:pointer; "   onclick= " "> 关闭 __tag_110$425_   
        title.setAttribute("id", "msgTitle");
        title.setAttribute("align", "left");
        title.style.margin = "0";
        title.style.padding = "3px";
        title.style.background = bordercolor;
        title.style.filter = "progid:DXImageTransform.Microsoft.Alpha(startX=20,startY=20,finishX=100,finishY=100,style=1,opacity=75,finishOpacity=100);";  
        title.style.opacity = "0.75";
        title.style.border = "1px solid";
        title.style.height = "18px";
        title.style.font = "13px Verdana,Geneva,Arial,   Helvetica,sans-serif";
        title.style.color = "white";
        title.style.cursor = "pointer";
        title.style.fontSize="14";
        if(titleStr){
        	title.innerHTML = "<span style='padding-left:7px'> <b>"+titleStr+"</b> </span>";
        }else{
        	title.innerHTML = "";
        }
        title.onclick = removeObj;
        var button = document.createElement("input");//创建一个input对象（提示框按钮）   
        //定义input的属性，即相当于   
        // __tag_146$7_   
  
        button.setAttribute("type", "button");
        button.setAttribute("value", "关闭");
        button.style.width = "60px";
        button.style.align = "center";
       	//button.style.marginLeft = "230px";
        button.style.marginBottom = "10px";
        button.style.background = bordercolor;
        button.style.border = "1px solid";
        if(cssPath == "yellow"){
        	button.style.color = "black";
        }else{
        	button.style.color = "white";
        }
        button.onclick = removeObj;
        
        var cen = document.createElement("center");
        cen.appendChild(button);
        function removeObj() {//点击标题栏触发的事件   
            document.body.removeChild(bgObj);//删除背景层Div   
            document.getElementById("msgDiv").removeChild(title);//删除提示框的标题栏   
            document.body.removeChild(msgObj);//删除提示框层  
            if(call_func)
            	eval(call_func);
        }  
        document.body.appendChild(msgObj);//在body内添加提示框div对象msgObj   
        document.getElementById("msgDiv").appendChild(title);//在提示框div中添加标题栏对象title   
        var txt = document.createElement("p");//创建一个p对象（提示框提示信息）   
  
        //定义p的属性，即相当于   
        // __tag_192$7_ 测试效果 __tag_192$60_   
        txt.style.margin = "1em 0";
        txt.setAttribute("id", "msgTxt");  
        txt.style.margin="0 15px 10px 15px";
        txt.style.align = 'left';
        txt.innerHTML = str;//来源于函数调用时的参数值   
  
        document.getElementById("msgDiv").appendChild(txt);//在提示框div中添加提示信息对象txt   
        document.getElementById("msgDiv").appendChild(cen);//在提示框div中添加按钮对象button
        
       
        button.focus();
        return;
     }
</script>

<s:if test="hasActionErrors()">
	<script language="JavaScript">
var actionmsg = "";
</script>
	<s:iterator value="actionErrors">
		<script language="JavaScript">
actionmsg = actionmsg+"<s:property escape="false"/>"+"</br>";
	    </script>
	</s:iterator>
	<script language="JavaScript">
if (actionmsg != "") {
	startCompareAlert(actionmsg);
}
</script>
</s:if>


<s:if test="hasActionMessages()">
	<script language="JavaScript">
var actionmsg = "";
</script>
	<s:iterator value="actionMessages">
		<script language="JavaScript">
actionmsg = actionmsg+"<s:property escape="false"/>"+"</br>";
	    </script>
	</s:iterator>
	<script language="JavaScript">
if (actionmsg != "") {
	startCompareAlert(actionmsg);
}
</script>
</s:if>


<s:if test="hasFieldErrors()">
	<script language="JavaScript">
var msg = "";
</script>
	<s:iterator value="fieldErrors">
		<script language="JavaScript">
msg = msg+"<s:property escape="false"/>"+"</br>";
	        </script>
	</s:iterator>
	<script language="JavaScript">
if (msg != "") {
	startCompareAlert(msg);
}
</script>
</s:if>

