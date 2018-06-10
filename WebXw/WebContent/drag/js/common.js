var startPopX=0;
var startPopY=0;
var pop;
var moveFlag=false;
function $ce(element) 
{
	return document.createElement(element);
}
function $ge(element) 
{
	if (document.getElementById(element) != null) 
	{
		return document.getElementById(element);
	} 
	else 
	{
		return document.getElementsByName(element)[0];
	}
}

function dragPro(obj)
{
    if(moveFlag)
    {
    	pop=$ge(obj);
        var e=e||window.event;
        startPopX=e.clientX;
    	startPopY=e.clientY;
    	startPopLeft=pop.offsetLeft;
    	startPopTop=pop.offsetTop;
        //document.addEventListener("mousemove",doDragPro,true);
    	//document.addEventListener("mouseup",stopDargPro,true);
    	pop.onmousemove=doDragPro; 
    	pop.onmouseup=stopDargPro;
    	//pop.setCapture(); 
    }
}
var popX=0;
var popY=0;
function doDragPro()
{
    var e=e||window.event;
    
    popX=e.clientX;
    popY=e.clientY;
    var osx=popX-startPopX;
    var osy=popY-startPopY;
    if(osx>5||osy>5||osx<-5||osy<-5)
    {
        var newLeft=startPopLeft+popX-startPopX;
	    var newTop=startPopTop+popY-startPopY;
	    pop.style.left=newLeft+"px";
	    pop.style.top=newTop+"px";
    }
}
function stopDargPro()
{
    pop.onmousemove=null; 
	pop.onmouseup=null;
	pop=null;
    //document.removeEventListener("mousemove",doDragPro,true);
	//document.removeEventListener("mouseup",stopDargPro,true);
}
function stopDarg()
{
	moveFlag=false;
}
function dargBlur()
{
	moveFlag=true;
}
function closeDiv(obj)
{
    obj.style.display="none";
}
function closeDiv2(obj)
{
    $ge(obj).style.display="none";
}
function closePopAlert()
{
    $ge("popAlert").style.display="none";
}
function LRTrim(str) {
	return str.replace(/^\s*|\s*$/g, "");
}
function selectonchange()
{
	var typeValue=$ge("formActive.dataType").value;
	if(typeValue=="12")
	{
		$ge("formActive.unit").value="元";
	}
	else if(typeValue=="13")
	{
		$ge("formActive.unit").value="分";
	}
	else if(typeValue=="14")
	{
		$ge("formActive.unit").value="角";
	}
	else if(typeValue=="15")
	{
		$ge("formActive.unit").value="万元";
	}
	else if(typeValue=="16")
	{
		$ge("formActive.unit").value="亿元";
	}
	else if(typeValue=="17")
	{
		$ge("formActive.unit").value="%";
	}
	else if(typeValue=="18")
	{
		$ge("formActive.unit").value="‰";
	}
	else if(typeValue=="19")
	{
		$ge("formActive.unit").value="万分之";
	}
}
//function trim(str) {
//	return str.replace(/(^\s*)|(\s*$)/g, "");
//}
/*
window.onresize=function()  //浏览器，onresize 事件会在窗口或框架被调整大小时发生 
{
	var oDiv=document.getElementById('div1');
	if(oDiv.offsetLeft>document.documentElement.clientWidth-oDiv.offsetWidth) 
	{
		oDiv.style.left=document.documentElement.clientWidth-oDiv.offsetWidth+"px";
	}
	if(oDiv.offsetTop>document.documentElement.clientHeight-oDiv.offsetHeight) 
	{
		oDiv.style.top=document.documentElement.clientHeight-oDiv.offsetHeight+"px";
	}
}
*/