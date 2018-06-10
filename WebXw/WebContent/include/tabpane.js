function TabPane(name, width, height){
	this._name = name;
	this._items = new Array();
};

function TabItem(name, text, url){
	if(TabItem.arguments.length <= 2){		
		 this._text = name;		
		 this._url = text;	
	}else{	
		this._name = name;	
		this._text = text;	
		this._url = url;
	}
};

var maxHei=0;

_p = TabPane.prototype;
_p.addItem = function (item){	
	this._items[this._items.length] = item;
};

function tabChange(obj, url){
	if(obj.className=="tab_mid_on"){
		return false;
	}
	document.getElementById("tabPage").src = url;
	var lab = obj;
	if(obj.tagName=="SPAN" || obj.tagName=="span"){
		lab = obj.parentNode;
	}
	var labs = lab.parentNode.childNodes;
	for(var i=0;i<labs.length;i++){
		if(labs[i].className=="tab_mid_on"){
			labs[i].className = "tab_mid";
			break;
		}
	}
	lab.className = "tab_mid_on";
}
/*$(document).ready(function(){
	if(!(window.dialogArguments || window.opener)){	
		$("#tabPage").attr("height", ($(window.frames['top'].document).height()-150)+"px");
	}else {	//弹出窗口
		var btn = $("div.tab_btn_div").find("input.back_btn");
		if(btn){
			btn.val("关闭");
			btn.bind("click",function(){
				window.close();
			});
		}
		if($(window.frames['top'].document).find("frameset").length == 0) {
			$("#tabPage").attr("height", ($(window.frames['top'].document).height()-50)+"px");
		}else {	//包含多个frame的弹出页,类似客户详情
			$("#tabPage").attr("height", ($(window.frames['top'].document).height()-150)+"px");
		}
	}
	$("#tabPage").parents("table").attr("height","95%");
	$("#tabPage").parents("table").attr("width","100%");
});*/
$(window).resize(function(){
	$("#tabPage").attr("height", ($(window.frames['top'].document).height()-150)+"px");
});

_p.loadPane = function(){
	var resultStr = "<table style='width:100%' cellspacing='0' cellpadding='0'><tr><td width='15px' style='border-bottom:1px #cccccc solid;'></td><td style='border-bottom:1px #cccccc solid;'>";
	for(var i=0;i<this._items.length;i++){
		if(i==0){
			resultStr += "<label class='tab_mid_on' onclick=\"tabChange(this,'"+this._items[i]._url+"')\"><span>"+this._items[i]._text+"</span></label>";
		}else {
			resultStr += "<label class='tab_mid' onclick=\"tabChange(this,'"+this._items[i]._url+"')\"><span>"+this._items[i]._text+"</span></label>";
		}
	}
	resultStr += "</td><td style='border-bottom:1px #cccccc solid;'></td></tr><tr height='3px'><td colspan='3'></td></tr><tr><td colspan='3' style='padding:0px;'>" +
			"<iframe style='width:99%;overflow-x:hidden;margin:2px;' width='98%' id='tabPage' name='tabPage' src='"+this._items[0]._url+"' align='center' frameborder='0' scrolling='auto'/></td><tr></table>";
	document.writeln(resultStr);
	
	
	if(!(window.parent.dialogArguments || window.dialogArguments || window.opener || window.parent.opener)){	
		$("#tabPage").attr("height", ($(window.frames['top'].document).height()-150)+"px");
	}else {	//弹出窗口
		/*var btn = $("div.tab_btn_div").find("input.back_btn");
		if(btn){
			btn.val("关闭");
			btn.attr("class","tab_btn");
			btn.bind("click",function(){
				if($(window.frames['top'].document).find("frameset").get(0)){
					window.parent.close();
				}else {
					window.close();
				}
			});
		}*/
		if(!$(window.frames['top'].document).find("frameset").get(0)) {
			$("#tabPage").attr("height", ($(window.frames['top'].document).height()-50)+"px");
		}else {	//包含多个frame的弹出页,类似客户详情
			$("#tabPage").attr("height", ($(window.frames['top'].document).height()-150)+"px");
		}
	}
	$("#tabPage").parents("table").attr("height","95%");
	$("#tabPage").parents("table").attr("width","100%");
	//document.getElementById("tabPage").setAttribute("height", (document.body.clientHeight-61)+"px");
	/*$("td.tab_body").find("label").each(function(){
		$(this).click(function(){
			$("#tabPage").attr("src",$(this).attr("url"));
			$(this).parent("td").find("label.tab_mid_on").attr("class","tab_mid");
			$(this).attr("class","tab_mid_on");
		});
		$(this).find("span").click(function(){
			$("#tabPage").attr("src",$(this).parent("label").attr("url"));
			$(this).parent("label").parent("td").find("label.tab_mid_on").attr("class","tab_mid");
			$(this).parent("label").attr("class","tab_mid_on");
		});
	});*/
};