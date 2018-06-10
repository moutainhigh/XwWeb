<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="/WEB-INF/tld/loan.tld" prefix="dhcc" %>
<%@ taglib uri="/WEB-INF/tld/dict.tld" prefix="dict" %>
<%
	String contextpath = request.getContextPath();
	Object themeObj = session==null?null:session.getAttribute("color");
	String theme = themeObj==null?"yellow":((String)themeObj);
	String query = (String)request.getAttribute("query");
	response.addHeader("X-XSS-Protection","0");//关闭IE浏览器 XSS筛选器功能
%>
<%@ include file="/include/message.jsp"%>

<script type="text/javascript" src="<%=contextpath%>/themes/js/jquery-1.8.0.min.js"></script>
<script type='text/javascript' src='<%=contextpath%>/creditapp/js/My97DatePicker/WdatePicker.js'></script>
<link rel="stylesheet" type="text/css" href="<%=contextpath%>/themes/jqueryUI/smoothness/jquery.ui.theme.css" />
<link rel="stylesheet" type="text/css" href="<%=contextpath%>/themes/jqueryUI/smoothness/jquery.ui.core.css" />
<link rel="stylesheet" type="text/css" href="<%=contextpath%>/themes/jqueryUI/smoothness/jquery.ui.datepicker.css" />
<link href="<%=contextpath%>/themes/jqueryUI/smoothness/jquery.ui.dialog.css" type="text/css" rel="stylesheet" />
<link href="<%=contextpath%>/themes/jqueryUI/smoothness/jquery.ui.button.css" type="text/css" rel="stylesheet" />

<script src="<%=contextpath%>/themes/js/jquery-ui-1.10.1.custom.min.js" type="text/javascript"></script>

<script src="<%=contextpath%>/themes/js/jquery.ui.core.js"  type="text/javascript"></script>
<script src="<%=contextpath%>/themes/js/jquery.ui.datepicker.js"  type="text/javascript"></script>
<script src="<%=contextpath%>/themes/js/jquery.ui.datepicker-zh-CHS.js"type="text/javascript"></script>
<link rel="stylesheet" href="<%=contextpath%>/themes/css/main.css" type="text/css" />
<link rel="stylesheet" href="<%=contextpath%>/themes/theme_<%=theme%>/Css/sysUI_<%=theme%>.css?ver=123" type="text/css" />


<script type="text/javascript" src='<%=contextpath%>/include/uior_val.js'> </script>
<script type="text/javascript" src='<%=contextpath%>/include/xcqi_cal.js'> </script>
<script type="text/javascript" src="<%=contextpath%>/creditapp/js/pop.js"></script>
<script type="text/javascript" src="<%=contextpath%>/creditapp/js/listutils.js"></script>
<script type="text/javascript" src="<%=contextpath%>/creditapp/js/idvalidate.js"></script>
<script type='text/javascript' src='<%=contextpath%>/include/idCheck.js'></script>
<script type='text/javascript' src='<%=contextpath%>/include/base.js'></script>
<link rel="stylesheet" href="<%=contextpath%>/include/screencore.css" type="text/css" />

<script type="text/javascript" src="<%=contextpath%>/creditapp/js/jquery.qtip-1.0.0-rc3.min.js"></script>
<script type='text/javascript' src='<%=contextpath%>/include/tablelistshowdiv.js'></script>

<script type="text/javascript" src="<%=contextpath%>/include/dhcc.jquery.datagrid.js"></script>
<script type="text/javascript" src="<%=contextpath%>/include/dhcc.jquery.tabpane.js"></script>

<script type='text/javascript' src='<%=contextpath%>/include/improveGrid.js'></script>


<script type="text/javascript">
var appContextPath = "<%=contextpath%>";
var treeIconPath = "";
treeIconPath = "img";

window.onload = pageInit;
var query = "<%=query%>";
function pageInit(){
	// 去掉查看页面的小放大镜
	if(query == "query" ){
		$(".btn_50").hide();
		$(".btn_51").hide();
		$(".btn_52").hide();
	}
	/*
	// 选取不含有readonly属性的单行文本框
    var $text = $('table :text').not('[readonly]');
    $text.hover(function() {
    		$(this).addClass('text-border');
    	},function() {
        	$(this).removeClass('text-border');
    }).focus(function() {
    	$(this).addClass('text-border2');
    }).blur(function() {
    	$(this).removeClass('text-border2');
    });
    */
    /* form隔行换色 */
    var index = 1;
    //$(".from_w > tbody > tr:even").addClass("evenRow");//奇数行
    var from_w = $(".from_w");
    var table_w = $(".table_w");
    if(from_w!=null&&from_w!="null"){
	    $(".from_w > tbody > tr").each(function(){
	    	if($(this).find("td.bartop").get(0) || $(this).find("td.bartitle").get(0)){
	    		index = 1;
	    	}else {
		    	if(index%2==0){
		    		$(this).addClass("evenRow");
		    	}
		    	index++;
	    	}
	    });
	}else if(table_w!=null&&table_w!="null"){
		$(".table_w > tbody > tr").each(function(){
	    	if($(this).find("td.bartop").get(0) || $(this).find("td.bartitle").get(0)){
	    		index = 1;
	    	}else {
		    	if(index%2==0){
		    		$(this).addClass("evenRow");
		    	}
		    	index++;
	    	}
	    });
	}
    var tabIndex = 1;
    var objTable = $("table.ls_list");
    if( objTable ) {
    	$("table.ls_list").attr("cellSpacing","0");
    	$("table.ls_list > tbody >tr").each(function(){
	    	if($(this).find("td.bartop").get(0) || $(this).find("td.bartitle").get(0)){
	    		tabIndex = 1;
	    	}else {
		    	if(tabIndex%2!=0){
		    		//$(this).addClass("evenRow");
		    		$(this).addClass("t1");
		    	}else {
		    		//$(this).addClass("oddRow");
		    		$(this).addClass("t2");
		    	}
		    	tabIndex++;
	    	}
    	/*
    	var trtemp;
    	$(this).mouseover(function(){
        	trtemp = $(this).attr("class");
    		$(this).attr("class","t3");
    	}).mouseout(function(){
    		$(this).attr("class",trtemp);
    	});*/
    	});
    }
    /* 为列表头添加圆角 */
    /*$("table.ls_list").find("thead tr th:first-child").addClass("first");
    $("table.ls_list").find("thead tr th:last-child").addClass("last");*/
    if( objTable ) {
	    var firstHead = $("table.ls_list").find("thead tr th:first-child");
	    if(!firstHead.attr("class")){
	    	firstHead.addClass("first");
	    }
    }
    if( objTable ) {
	    var lastHead = $("table.ls_list").find("thead tr th:last-child");
	    if(!lastHead.attr("class")){
	    	lastHead.addClass("last");
	    }
    }
    if( $("input:text") ) {
	    $("input:text").each(function(){
	        if(!$(this).attr("class") || $(this).attr("class")==""){
	    		$(this).addClass("INPUT_TEXT");
	        }
	    });
    }
    // 选取不含有readonly属性的多行文本框
    if( $('table textarea') ){
		var $textarea = $('table textarea').not('[readonly]');
	    for (var i = 0; i < $textarea.length; i++) {
	        var name = $($textarea[i]).attr('name'); // 每个textarea的name属性
	        var maxlength = $($textarea[i]).attr('maxlength'); // 每个textarea的maxlength属性
			var cols = $($textarea[i]).attr('cols');
	        if(!cols || typeof(cols)==undefined || cols==null){
	        	$($textarea[i]).attr('cols',"80");
	        }
	        var $textarea_div = $("<div id='" + name + "-div' class='textarea-div'></div>"); // 包围textarea的Div节点
	        $($textarea[i]).wrap($textarea_div);
	
	        var $chars_div = $("<div id='" + name + "-charsdiv' class='chars-info'>" + maxlength / 2 + "</div>"); // 显示剩余字数的Div节点
	        $($textarea[i]).after($chars_div); // 将$chars_div节点添加到textarea节点之后
	    }
    }
}

$(function(){
	if($(".searchstyle select").length >0){
		$(".searchstyle select").each(function(){
			$(this).change(function(){
				if(this.form){this.form.submit();}
			});
		});
	}
});

</script>
<%
	String isDialog = request.getParameter("isDialog");
	if("1".equals(isDialog)){
%>
	<script type="text/javascript">
		window.onload = function() {
			/*
			if (document.body.scrollWidth > (window.screen.availWidth - 100)) {
				window.dialogWidth = (window.screen.availWidth - 100)
						.toString()
						+ "px"
			} else {
				window.dialogWidth = (document.body.scrollWidth + 50)
						.toString()
						+ "px"
			}

			if (document.body.scrollHeight > (window.screen.availHeight - 70)) {
				window.dialogHeight = (window.screen.availHeight - 50)
						.toString()
						+ "px"
			} else {
				window.dialogHeight = (document.body.scrollHeight + 115)
						.toString()
						+ "px"
			}
			window.dialogLeft = ((window.screen.availWidth - document.body.clientWidth) / 2)
					.toString()
					+ "px"
			window.dialogTop = ((window.screen.availHeight - document.body.clientHeight) / 2)
					.toString()
					+ "px"
					*/
		}
	</script>
<%		
	}

%>
<%String sysDate = (String)session.getAttribute("sys_date"); %>
<input type="hidden" name="sysDate" id="sysDate" value="<%=sysDate %>" title="营业日期"/>
<div id="screenLockDiv"></div>