<%@ page language="java" contentType="text/html; charset=GBK"
	pageEncoding="GBK"%>
<%@ include file="/include/tld.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta HTTP-EQUIV="pragma" CONTENT="no-cache">
<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">
<meta HTTP-EQUIV="expires" CONTENT="0">
<title>菜单[ <s:property value="menu_no" /> ]的按钮配置</title>
<style type="text/css">
.lev_one {
	background-color: #FCEECD;
}

.lev_two {
	background-color: rgb(250, 250, 250);
}

.lev_blank {
	background-color: rgb(243, 250, 243);
}
</style>
<script type="text/javascript">
	function updateBtn(obj) {
		var buttonNo = $(obj).parent("td").parent("tr").attr("id");
		var desc = $(obj).parent("td").prev("td").find(
				"input[name='buttonDesc']").val();
		$.post("SysButtonAction_updateBtn.action", {
			button_no : encodeURI(buttonNo),
			menu_no : $("#menu_no").val(),
			button_desc : encodeURI(desc),
			rdm : Math.random()
		}, function(data) {
			if ($.trim(data) == "fail") {
				alert("按钮修改失败!");
			} else {
				alert("按钮修改成功!");
			}
		});
	}
	function deleteBtn(obj) {
		if (!$(obj).parent("td").parent("tr").attr("id")) {
			if ($("#lastline").attr("class") == "lev_two") {
				$("#lastline").attr("class", "lev_blank");
			} else {
				$("#lastline").attr("class", "lev_two");
			}
			$("#lastline").prev("tr").remove();
			return;
		}
		var buttonNo = $(obj).parent("td").parent("tr").find("td:first-child")
				.find("input:text").val();
		if (confirm("确定删除按钮[ " + buttonNo + " ]?")) {
			$.post("SysButtonAction_deleteBtn.action", {
				button_no : encodeURI(buttonNo),
				menu_no : $("#menu_no").val(),
				rdm : Math.random()
			}, function(data) {
				if ($.trim(data) == "fail") {
					alert("按钮删除失败!");
				} else {
					$("#" + buttonNo).remove();
				}
			});
		}
	}
	function insertBtn() {
		if ($("#lastline").attr("class") == "lev_two") {
			$("#lastline").attr("class", "lev_blank");
		} else {
			$("#lastline").attr("class", "lev_two");
		}
		$("#lastline")
				.before(
						"<tr class=\"lev_two\"><td align=\"center\"><input type=\"text\" name=\"buttonNo\" /></td><td align=\"center\"><input type=\"text\" name=\"buttonDesc\" size=\"40\"/></td><td align=\"center\"><a href=\"#\" onclick=\"saveNew(this)\">保存新增</a> | <a href=\"#\" onclick=\"deleteBtn(this)\">删除</a></td></tr>");
	}
	function saveNew(obj) {
		var buttonNo = $(obj).parent("td").parent("tr").find("td:first-child")
				.find("input:text").val();
		if ($.trim(buttonNo) == "") {
			alert("请输入按钮编号!");
			return;
		}
		var desc = $(obj).parent("td").prev("td").find(
				"input[name='buttonDesc']").val();
		$.post("SysButtonAction_insertBtn.action", {
			button_no : encodeURI(buttonNo),
			menu_no: $("#menu_no").val(),
			button_desc : encodeURI(desc),
			rdm : Math.random()
		}, function(data) {
			if ($.trim(data) == "fail") {
				alert("按钮新增失败!");
			} else {
				alert("按钮新增成功!");
				$(obj).parent("td").parent("tr").attr("id", buttonNo);
				$(obj).parent("td").parent("tr").find("td:first-child").find(
						"input:text").attr("readonly", "readonly");
				$(obj).parent("td").parent("tr").find("td:nth-child(2)").find(
						"a:first-child").bind("click", function(event) {
					updateBtn($(this));
				});
				$(obj).text("保存修改");
			}
		});
	}
</script>
</head>
<body class="body_bg">
	<div class="right_bg">
		<div class="right_w">
			<div class="from_bg">
				<div class="right_v">

					<div class="tabCont">
						<div class="tabTitle" style="float: left;">菜单按钮配置</div>
						<input type="button" value="添加按钮" onclick="insertBtn();"
							class="t_ico_tj">
					</div>
					<s:form name="cms_form" theme="simple" validate="false">
						<input type="hidden" name="menuNo" id="menu_no" value="<s:property value="menu_no"/>" />
						<table class="ls_list" width="100%" border="0" cellpadding="0"
							cellspacing="1">
							<colgroup>
							</colgroup>
							<colgroup>
							</colgroup>
							<colgroup>
							</colgroup>
							<thead>
								<tr>
									<th scope="col" nowrap="nowrap"><b>按钮编号（与Button标签中aciton关联）</b>
									</th>
									<th scope="col" nowrap="nowrap"><b>按钮描述</b>
									</th>
									<th scope="col" width="120px"><b>操作</b>
									</th>
								</tr>
							</thead>
							<tbody id="tab">
								<s:iterator value="sysButtonList" status="status">
									<tr id="<s:property value="button_no"/>"
										class="<s:if test="#status.even">lev_two</s:if><s:else>lev_blank</s:else>">
										<td align="center"><input type="text" name="buttonNo"
											value="<s:property value="button_no"/>" readonly="readonly" />
										</td>
										<td align="center"><input type="text" name="buttonDesc"
											value="<s:property value="button_desc"/>" size="40" />
										</td>
										<td align="center"><a href="#" onclick="updateBtn(this)">保存修改</a>
											| <a href="#" onclick="deleteBtn(this)">删除</a></td>
									</tr>
								</s:iterator>
								<tr
									class="<s:if test="sysButtonList.size()%2!=0">lev_two</s:if><s:else>lev_blank</s:else>"
									id="lastline">

									<td colspan="3" align="center"><div class="from_btn">
											<input type="button" value="关闭" class="btn_80"
												onclick="javascript:window.close();" />
										</div>
									</td>
								</tr>

							</tbody>
						</table>
					</s:form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>