<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ page import="app.util.DBUtils,java.sql.Connection"%>
<%@ page
	import="java.sql.ResultSet,java.sql.PreparedStatement"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>Pop页列表</title>
	</head>
	<%
		String clo_name = request.getParameter("col_name");
		String sql = request.getParameter("sql").replaceAll("@", "%");
		String db_bean_rel = request.getParameter("rel");//cif_no-cifno,cif_name-cifname
		String parms = request.getParameter("parms");
	%>
	<script type="text/javascript">
		$(document).ready(function() {
			if(document.getElementsByName("roleNos") && document.getElementsByName("roleNos").length >0 ){
				var roleNos = document.getElementsByName("roleNos")[0].value;
				if(roleNos!=null){
					var arrroleNos = new Array();
					arrroleNos = roleNos.split("@");
					var objrole=document.getElementsByTagName("input");
					for(var i=0;i<arrroleNos.length;i++){
							for(var j=0;j<objrole.length;j++){
								var resultrole = objrole[j].value.indexOf(arrroleNos[i]);
								if(resultrole>0){
									objrole[j].checked=true;
								}
			        		}
						}
				}
			}
		}); 
	</script>
	<script type="text/javascript">
		$(document).ready(function() {
			if(document.getElementsByName("putTime") && document.getElementsByName("putTime").length >0 ){
				var a = document.getElementsByName("putTime")[0].value;
				var arr = new Array();
				arr = a.split("@");
				if(arr.length!=0){
					var obj=document.getElementsByTagName("input");
					alert(obj.length)
					for(var i=0;i<arr.length;i++){
						for(var j=0;j<obj.length;j++){
							var result = obj[j].value.indexOf(arr[i]);
							if(result>0){
								obj[j].checked=true;
							}
		        		}
					}
				}
			}
		}); 
	</script>
	<body class="body_bg">
	
		<s:form method="post" theme="simple" name="cms_form"
			action="PopParmConfActionInsert.action">
			<div class="right_bg">
				<div class="right_w">
					<div class="from_bg">
						<div class="right_v">
							<div class="tabCont">
								<div class="tabTitle">
									信息列表
								</div>
							</div>
							<table id="tablist" width="100%" border="0" align="center"
								cellspacing="1" class="ls_list">
								<%
									String[] clo_name_arr = clo_name.split(",");
									int clo_name_len = clo_name_arr.length;
									for (int i = 0; i < clo_name_len; i++) {
								%>
								<colgroup></colgroup>
								<%
									}
								%>
								<colgroup></colgroup>
								<thead>
								<tr>
								<td>
									<dhcc:button value="清空已选择" action="关闭" typeclass="btn_4"
									onclick="func_clean()"></dhcc:button>
									</td>
									<td  align="right" >
										<dhcc:button typeclass="btn_80"  value="确定" action="保存" onclick="javascript:confirm(this.form)" ></dhcc:button>
									</td>
									<td width="12%" align="center" >
										全选<input type="checkbox" id="all" name="allcheck" value="" onclick="javascript:allCheck()"/>
									</td>
								</tr>
									<tr>
										<%
											for (int i = 0; i < clo_name_len; i++) {
										%>
										<th scope="col" align="center"><%=clo_name_arr[i]%></th>
										<%
											}
										%>
										<th scope="col" align="center">
											操作
										</th>
									</tr>
								</thead>
								<tbody id="tab">

									<%
										Connection conn = null;
										PreparedStatement ps = null;
										ResultSet rs = null;
										try {
											conn = DBUtils.getConn();
											ps = conn.prepareStatement(sql);
											rs = ps.executeQuery();
											int len = rs.getMetaData().getColumnCount();
											String[] rel_arr = db_bean_rel.split(",");//对应关系数据 cif_no-username
											String[] rel_arr_split = null;
											int rel_len = rel_arr.length;
											int flag = 0;
											String value = "";
											List list = new ArrayList();
											while (rs.next()) {
									%>
									<tr id="tr">
										<%
											for (int i = 0; i < len; i++) {//一行 几列的值
										%>
										<td align="center" id='<%=rs.getMetaData().getColumnName(i + 1)
										.toLowerCase()%>'><%=rs.getObject(i + 1)%></td>
										<%
											//value += rs.getMetaData().getColumnName(i + 1).toLowerCase()+"-"+rs.getObject(i + 1)+"#";
											}
											for( int i=0;i<rel_len;i++ ){
												rel_arr_split = rel_arr[i].split("-");
												value += rel_arr_split[1]+"-"+rs.getObject(rel_arr_split[0])+"#";
											}
											list.add(value);
											value="";
										//System.out.println(value);
										%>
										<td align="center" width="12%">
											<input type="checkbox" id="pop_box" name="pop_box" 
											desc='' 
											value='<%=list.get(flag) %>' 
											 >
										</td>
									</tr>
									<%	
												flag++;
											}
										} catch (Exception e) {
											e.printStackTrace();
										} finally {
											DBUtils.closeResultset(rs);
											DBUtils.closeStatement(ps);
											DBUtils.closeConnection(conn);
										}
									%>

								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</s:form>
	</body>
</html>


<script type="text/javascript">
/*
$(function(){
	$("#tablist tr").slice(1).click(function(){
		var check = $(this).find(":checkbox").attr("checked");
		if( check=="checked" ){
			$(this).find(":checkbox").attr("checked", false);
		}else{
			$(this).find(":checkbox").attr("checked", true);
		}
	});
});
*/
function allCheck(){
    var obj=document.getElementsByTagName("input");
    if(document.getElementById("all").checked==true){
        for(var i=0;i<obj.length;i++){
        	obj[i].checked=true;
     	//  obj[i].checked = !obj[i].checked;
        }
      
    }else{
        for(var i=0;i<obj.length;i++){
            obj[i].checked=false;
        }
    }
}

function confirm(frm){
	var isIe = true;
	var agent = navigator.userAgent.toLowerCase();
	if (agent.indexOf("chrome") > 0 || agent.indexOf("firefox") > 0) {
		isIe = false;
	}
	var obj = frm.pop_box;
	var total = obj.length;
	alert(total)
	var name_val = "";
	var arr;
	var par_name = "";
	var arr_split;
	var parms = "<%=parms%>";
	var parms_arr;
	if( parms.indexOf(",")>0 ){
		parms_arr = parms.split(",");
		
		for( var i=0;i<parms_arr.length-1;i++ ){
			if (isIe) {
				document.getElementsByName(parms_arr[i].split("=")[0])[0].value="";

			}else{
				document.getElementsByName(parms_arr[i].split("=")[0])[0].value="";
			}
		}
	}
	var isNew = false;
	
	for(var i = 0; i< total; i++) {
		if( obj[i].checked && obj[i].value != "" ) {
			name_val = obj[i].value;
			arr = name_val.split("#");
			
			for( var j=0;j<arr.length-1;j++ ){
				
				arr_split = arr[j].split("-");
				//alert("arr_split[0]:"+arr_split[0]+",arr_split[1]:"+arr_split[1]);
				var parObj;
				if(window.dialogArguments){
					parObj = window.dialogArguments.document.getElementsByName(arr_split[0]);
				}else{
					parObj = window.parent.opener.document.getElementsByName(arr_split[0]);
				}
				if(parObj !=null && typeof(parObj)!="undefined" && parObj.length >0){
					if(!isNew){
						parObj[0].value = "";
						isNew = true;
					}
					if(typeof(parObj[0])!="undefined")
						//如果原本存在值则用@进行分割
						if(parObj[0].value!=null && parObj[0].value!="")parObj[0].value+="@" + arr_split[1];
						else parObj[0].value = arr_split[1];
					else{
						alert("主界面中不存在"+arr_split[0]+"字段，无法赋值。请仔细检查配置");
					}
				}else{
					alert("没有在主界面中拿到"+arr_split[0]+"对象的值");
				}
				/*
				par_name = window.dialogArguments.document.getElementsByName(arr_split[0])[0].value;
				if( par_name!=null && par_name!="" ){
					par_name += "@" + arr_split[1];
					alert(arr_split[0]);
					window.dialogArguments.document.getElementsByName(arr_split[0])[0].value = par_name;
				}else{
					window.dialogArguments.document.getElementsByName(arr_split[0])[0].value = arr_split[1];
				}
				*/
			}
		}
	}
	window.close();
}
	function func_clean(){
	    var db_rel = "<%=db_bean_rel%>";
	    if( db_rel!=null && db_rel!="" ){
       		var name_arr ;
       		if( db_rel.indexOf(",") > -1 ){
       			var arr = db_rel.split(",");
       			for( var i=0;i<arr.length;i++ ){
       				name_arr = arr[i].split("-");
       				window.parent.document.getElementsByName(name_arr[1])[0].value="";
       			}
       		}else{
       			name_arr = db_rel.split("-");
       			window.parent.document.getElementsByName(name_arr[1])[0].value="";
       		}
       	}
       	window.close();
	}
</script>
