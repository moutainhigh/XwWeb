<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    String color = "yellow";
    if (request.getCookies()!=null) {
    	for(Cookie c : request.getCookies()){
   		 	//System.out.println(c.getName() + "=" + c.getValue());
    		if("loginColorForCMS".equals(c.getName())){
    			color = c.getValue();
    			break;
			}
		}
	}
    if (color == null || "".equals(color) || color.equals("null")) {
    	color = "yellow";
	}

%>
<!DOCTYPE html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<meta http-equiv="X-UA-Compatible" content="IE=edge"> 
<title>北京信托消费信贷系统</title>

<script src="<%=basePath%>themes/js/jquery-1.8.0.min.js"
	type="text/javascript" language="javascript"></script>
<script src="<%=basePath%>themes/js/jquery.easing.1.3.js"
	type="text/javascript" language="javascript"></script>
<!--bootstap库	-->
<script src="<%=basePath%>include/bootstrap/js/bootstrap.js"
	language="javascript" type="text/javascript"></script>
<link rel="stylesheet"
	href="<%=basePath%>include/bootstrap/css/bootstrap.min-yellow.css" />
<link rel="stylesheet" href="<%=basePath%>themes/theme_<%=color %>/Css/entor_<%=color %>.css" />
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="<%=basePath%>themes/js/html5shiv.min.js"></script>
      <script src="<%=basePath%>themes/js/respond.min.js"></script>
    <![endif]-->
<!-- logo 文字特效  -->
<!--<script src="<%=basePath%>themes/js/jquery.fittext.js"></script>
<script src="<%=basePath%>themes/js/jquery.lettering.js"></script>                            
<script src="<%=basePath%>themes/js/highlight.min.js"></script> -->
<script src="<%=basePath%>themes/js/autotype.js"></script>
<!--<script src="<%=basePath%>themes/js/jquery.textillate.js"></script>-->

<!--引用icomoon字体图标-->
<link href="<%=basePath%>include/iconmoon/style.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript">
        function colorChange(obj){
        }

        $(function(){
        	document.getElementById("loginColor").value="<%=color%>";
        	var heightx=$(document).height();
			var heighttb=(heightx-300)/2;
			$(window).resize(function(){
				var heighty=$(document).height();
				var heightca=(heighty-300)/2;
				$(".mainCotent").css("top",heightca+"px");
				$(".layoutTop").css("height",heightca+"px");
	            $(".layoutBottom").css("height",heightca+"px");
			});
       	/* $("#autotype").autotype();*/
       	 	$(".mainCotent").css("top",heighttb+"px");
            $(".layoutTop").animate({height:[heighttb+"px", 'easeInExpo']}, 1000);
            $(".layoutBottom").animate({height:[heighttb+"px", 'easeInExpo']}, 1000);
 /*           logoTextChange();*/
            document.getElementById("IdInput").focus();
            <s:if test="hasActionErrors()">
                var actionmsg ="";
            <s:iterator value="actionErrors">
            actionmsg = actionmsg+"<s:property escape="false"/>" + " ";
            </s:iterator>
            if(actionmsg!=""){
                $("#loginInfo").text(actionmsg);
                $("#loginInfo").css("display","block");
                if($.trim(actionmsg)=="密码错误!"){
                  setTimeout("animateDom('.PwdInputDiv')",1000);
                }else{
                setTimeout("animateDom('.IdInputDiv')",1000);
                }
            }
            </s:if>
        })

        //logo特效
       /* function logoTextChange(){
            $('.logotext').fitText(1,{minFontSize:24}).textillate({in: {effect: 'flipInX'}});
            $('.welcomeText').fitText(1, {maxFontSize: 16}).textillate({initialDelay: 1000, in: {effect: 'fadeIn'}});
        }$*/
        function submitForm(evt){
            //IE & Firefox兼容处理
            var evt = evt ? evt : (window.event ? window.event : null);
            if(evt.keyCode == 13){
                chklogon();
            }
        }
        //登录
        function chklogon(){
            var LoginBtn = document.getElementById('LoginBtn');
            var op_noDiv = document.getElementById('loginInfo');
//            var trmnoDiv = document.getElementById('trmnoDiv');
            var pname = document.getElementById('IdInput').value;
            var pwd= document.getElementById('PwdInput').value;
            var codeInput = document.getElementById('CodeInput').value;
            if(!pname || pname==""){
                $("#loginInfo").text("请输入用户名!");
                animateDom(".IdInputDiv");
                document.getElementById('IdInput').focus();
                return false;
            }
            if(!pwd || pwd==""){
                $("#loginInfo").text("请输入密码!");
                animateDom(".PwdInputDiv");
                document.getElementById('PwdInput').focus();
                return false;
            }
            document.LoginForm.submit();
        }
        function changeCodeImg(){
            document.getElementById("codeImage").src = "<%=basePath %>CodeImage.jsp?rdm=" + Math.random();
            document.getElementById("CodeInput").value="";
            document.getElementById("CodeInput").focus();
        }


        function animateDom(dom){
            $(dom).stop()
                    .animate({ left: "-10px" }, 100).animate({ left: "10px" }, 100)
                    .animate({ left: "-10px" }, 100).animate({ left: "10px" }, 100)
                    .animate({ left: "0px" }, 100)
        }
        /*
		$(function(){
       		$("#PwdInput").keydown(function(event) {  
	              if (event.keyCode == 13) {  
	                  chklogon(); 
	              }  
	         });
	         $("#CodeInput").keydown(function(event) {  
	              if (event.keyCode == 13) {  
	                  chklogon(); 
	              }  
	         });
       	});
        */
    </script>
</head>

<body>
	<div class="layoutTop">
		<div class="sysLog">
			<div class="logo_top">
				<div class="centerDiv"  style="width:436px;">
					<!--<span class="enterLogo"></span> <span class="logotext" id="autotype"  style="width:360px;color:#000;text-align:left;">
					-->
					<!--<img src="<%=path %>/themes/images/logofont.png" width="357" height="42"  alt=""/>-->
					</span>
				</div>
				<!--<span class="welcomeText" style="color:#000;padding-top:16px;"><img src="<%=path %>/themes/images/1112.png" width="161" height="21"  alt=""/></span>
			     -->
			</div>
		</div>
	</div>

	<div class="mainCotent" style="300px;">
		<form action="<%=path %>/creditapp/cms.action" name="LoginForm" method="post">
			<input type="hidden" name="browserVersion" value="new">
			<div class="container-fluid"  style="width:460px; margin:0 auto;">
				<div class="row errorMessageRow">
					
								<span class="errorMessage" id="loginInfo">&nbsp;</span>
				</div>

			
								<div class="input-group IdInputDiv" style="margin-bottom:20px;margin-top:20px;overflow:hidden;margin-top:30px;margin-left:50px;">
									<span style="display:block;float:left; padding:0 5px; height:34px;+height:31px;	 border:1px solid #ccc;padding-top:3px;"> <img src="<%=path %>/themes/images/man.png"  alt=""/></span> <input type="text"
										name="op.user_no" id="IdInput" class="form-control"  style="display:block;float:left;width:290px;height:34px;+height:22px;"
										placeholder="用户名" 
										<s:if test='op.user_no!=null && op.user_no!=""'> value="<s:property value="op.user_no"/>"</s:if> />
								</div>

	
								<div class="input-group PwdInputDiv" style="margin-bottom:20px;+padding-left:50px;">
									<span style="display:block;float:left; padding:0 5px; height:34px;+height:31px; border:1px solid #ccc;padding-top:3px;margin-left:50px;+margin-left:0px;"> <img src="<%=path %>/themes/images/lock.png" alt=""/></span>
									 <input name="op.pass_word" type="password" id="PwdInput" class="form-control"  placeholder="密 码" style="display:block;float:left;width:290px;height:34px;+height:22px;" 
										<s:if test='op.pass_word!=null && op.pass_word!=""'> value="<s:property value="op.pass_word"/>"</s:if><s:else> value=""</s:else> />
								</div>
								<div style="display:block;+padding-top:20px;">
									<div  style="width:110px;float:left;margin-left:50px;+margin-left:25px;">
										<input type="text" id="CodeInput" class="form-control validate" name="code" style="line-height:30px;" style="display:block;float:left;height:34px;"
											 placeholder="验证码">
									</div>
	
									<div  style="float:left;margin-left:30px;+margin-left:60px;">
										<img title="不区分大小写" id="codeImage"
													src="<%=basePath%>CodeImage.jsp"
													style="vertical-align: middle;" onclick="changeCodeImg();" />
									</div>
	
									<div class=" changeCodeText" style="margin-bottom:20px; float:right;margin-right:50px;">
										<a onclick="changeCodeImg();" href="javascript:void(0);"
											class="changeCode">看不清楚?换一张</a>
									</div>
								</div>
								<button type="button" id="LoginBtn"
									class="btn btn-primary btn-block" onclick="chklogon()" style="color:#000;width:330px;+width:350px;margin-left:50px;">登录</button>
							</div>
						<input type="hidden" id="loginColor"  value="<%=color %>" />
		</form>
	</div>

	<div class="layoutBottom"></div>
	<script type="text/javascript">
	  function func_submit(frm){
		/*--  	var username1 = $("input[name='op.username1']").val();
			if(username1!=null){
				$("input[name='op.username']").val(username1);
			}--*/
		  	frm.submit();
		  }
		  function doblur()
		  {	var displayname;
		    var username = $("input[name='op.username']").val();
		    if(displayname!=null)
		    	return;
		    displayname=null;
		    $.ajax({
							type : "POST",
							url:	"creditapp/TblOrgUserAction_userNameHX.action",
							data:	{"username":username,"displayname":displayname},
							dataType: "json",   
							success:	function(data){
								if(data!=null)
								{	var displayname = data.name;
									$("input[name='op.username1']").val(username);
									$("input[name='op.username']").val(displayname);
									}
							}});
			
		    return;
		  }
		//回车登录
		 document.onkeydown =  function submitForm(evt) {
		  	//IE & Firefox兼容处理
		  	var evt = evt ? evt : (window.event ? window.event : null);
		  	if (evt.keyCode == 13) {
		  		chklogon();
		  	}
		  }
	</script>
	
</body>