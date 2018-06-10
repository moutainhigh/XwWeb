var movable = false;
var firstCellX, firstCellY, secondCellX = -1, secondCellY = -1;
var rowsLength = 0;
var formId = "";
var formActiveId;
var formActiveIdTemp;
var labelCol;
var fieldCol;
var row;
var col;
var currTd;
var currClass;
var divwidth = 0;
var divheight = 0;
var currX = 0;
var currY = 0;
var currRowIndex = -1;
var currColIndex = -1;
var action;
var currPage = 0;
var lastPage = 0;
var startX = 0;
var startY = 0;
var startLeft = 0;
var startTop = 0;
var dragImg;
var dragLabel;
var dragTable;
var selectDiv;
var clientWidth = 0;
var clientHeight = 0;
var newDivX = 0;
var newDivY = 0;
var newElementType;
var currElement;
var myAlert;
var flag = false;
window.onload = function() {
	clientWidth = document.body.clientWidth;
	clientHeight = document.body.clientHeight;
	oDiv = $ge("div11");
	startX = 0;
	startY = 0;
	startLeft = 0;
	startTop = 0;
	dragImg = null;
	dragTable = null;
	oDiv.onmousedown = fun0;
};
function viewItem(formActiveId1) {
	action = "update";
	formActiveId = formActiveId1;
	var formId = $ge("formId").value;
	var url = "FormDataForToolBean_getFormElement.action";
	var param = "formId=" + formId + "&" + "formActiveId=" + formActiveId;
	fun36(url, param, "getFormElement", fun37);
}
function viewHidden(obj) {
	var pos = obj.indexOf("=");
	var id = obj.substring(pos + 1);
	viewItem(id);
}
function fun0(e) {
	flag = true;
	var e = e || window.event;
	startX = e.clientX;
	startY = e.clientY;
	startLeft = oDiv.offsetLeft;
	startTop = oDiv.offsetTop;
	if (oDiv.setCapture) {
		oDiv.onmousemove = fun1;
		oDiv.onmouseup = fun2;
		oDiv.setCapture();
	} else {
		document.addEventListener("mousemove", fun1, true);
		document.addEventListener("mouseup", fun2, true);
	}
}
function fun1(e) {
	if (selectDiv != null) {
		selectDiv.style.display = "block";
	}
	var e = e || window.event;
	var divX = e.clientX - startX + startLeft;
	var divY = e.clientY - startY + startTop;
	if (divX < 0) {
		divX = 0;
	} else if (divX > document.body.clientWidth - oDiv.offsetWidth) {
		divX = document.body.clientWidth - oDiv.offsetWidth;
	}
	if (divY < 0) {
		divY = 0
	} else if (divY > document.body.clientHeight - oDiv.offsetHeight) {
		divY = document.body.clientHeight - oDiv.offsetHeight;
	}
	oDiv.style.left = divX + "px";
	oDiv.style.top = divY + "px";
}
function fun2(e) {
	var e = e || window.event;
	oDiv.style.background = "";
	if (oDiv.releaseCapture) {
		oDiv.onmousemove = fun1;
		oDiv.onmouseup = fun2;
		oDiv.releaseCapture();
	} else {
		document.removeEventListener("mousemove", fun1, true);
		document.removeEventListener("mouseup", fun2, true);
	}
	var inFormId = $ge("formId").value;
	if (inFormId != null && LRTrim(inFormId) != "") {
		action = "insert";
		$ge("submitPropertyForm").reset();
		fun6();
		if (e.clientX > 100) {
			fun3(e.clientX, e.clientY, inFormId);
		} else {
			myAlert = $ge("popAlert");
			fun4(600, 200, myAlert);
			myAlert.style.position = "absolute";
			var select = $ge("formActive.fieldType");
			var selectType = selectDiv.id;
			var selectValue = selectType.substring(3, selectType.length);
			select.value = selectValue;
		}
	} else {
		alert("请先新增一个form");
	}
	oDiv.onmousemove = null;
	oDiv.onmouseup = null;
	oDiv.removeChild(dragTable);
	oDiv = null;
	dragImg = null;
	dragTable = null;
	startX = 0;
	startY = 0;
	startLeft = 0;
	startTop = 0;
	selectDiv = null;
}
function fun3(x, y, inFormId) {
	var inTable = $ge("form" + inFormId);
	if (inTable.rows.length == 0) {
		$ge("formActive.labelName").value = "labelName";
		$ge("formActive.fieldName").value = "fieldName";
		fun14();
	} else {
		currX = x;
		currY = y;
		fun32(inTable);
		if (secondCellX >= 0 && secondCellY >= 0) {
			var inTd1 = inTable.rows[secondCellX].cells[secondCellY];
			var inTd1InnerHTML = "" + inTd1.innerHTML + "";
			if (inTd1 != null && undefined != inTd1.innerHTML
					&& inTd1.innerHTML != null) {
				if (inTd1InnerHTML.substring(0, 6) == "&nbsp;"
						|| inTd1InnerHTML == null || inTd1InnerHTML == "") {
					var inTd2;
					if (secondCellY % 2 == 0
							&& inTable.rows[secondCellX].cells.length > secondCellY + 1) {
						inTd2 = inTable.rows[secondCellX].cells[secondCellY + 1];
					} else if (secondCellY > 0) {
						inTd2 = inTable.rows[secondCellX].cells[secondCellY - 1];
						secondCellY = secondCellY - 1;
					}
					if (inTd2 != null && undefined != inTd2.innerHTML
							&& inTd2.innerHTML != null) {
						var inTd2InnerHTML = "" + inTd2.innerHTML + "";
						if (inTd2InnerHTML == "" || inTd2InnerHTML == null
								|| inTd2InnerHTML.substring(0, 6) == "&nbsp;") {
							$ge("formActive.labelName").value = "labelName";
							$ge("formActive.fieldName").value = "fieldName";
							row = secondCellX + 1;
							fun30();
							$ge("formActive.row").value = row;
							$ge("formActive.labelCol").value = secondCellY + 1;
							$ge("formActive.fieldCol").value = secondCellY + 2;
							fun14();
						}
					}
				}
			}
		}
	}
}
var OverH, OverW, ChangeH = 50, ChangeW = 50;
function fun4(divWidth, divHeight, obj) {
	OverH = divHeight;
	OverW = divWidth;
	myAlert.style.display = "block";
	$ge("tab1").className = "tab_hide";
	$ge("tab2").className = "tab_hide";
	$ge("tab3").className = "tab_hide";
	$ge("tab4").className = "tab_hide";
	myAlert.style.background = "#e2ecf5";
	if (divWidth > divHeight) {
		ChangeH = Math.ceil((divHeight - 10) / ((divWidth - 10) / 50));
	} else if (divWidth < divHeight) {
		ChangeW = Math.ceil((divWidth - 10) / ((divHeight - 10) / 50));
	}
	myAlert.style.top = document.body.scrollTop
			+ (document.body.clientHeight - 10) / 2 + "px";
	myAlert.style.left = document.body.scrollLeft
			+ (document.body.clientWidth - 10) / 2 + "px";
	fun5();
}
var newWidth = 10, newHeight = 10;
function fun5() {
	if (newWidth > OverW - ChangeW)
		ChangeW = 2;
	if (newHeight > OverH - ChangeH)
		ChangeH = 2;
	newWidth = newWidth + ChangeW;
	newHeight = newHeight + ChangeH;
	if (OverW > newWidth || OverH > newHeight) {
		if (OverW > newWidth) {
			myAlert.style.width = newWidth + "px";
			myAlert.style.left = document.body.scrollLeft
					+ (document.body.clientWidth - newWidth) / 2 + "px";
		}
		if (OverH > newHeight) {
			myAlert.style.height = newHeight + "px";
			myAlert.style.top = document.body.scrollTop
					+ (document.body.clientHeight - newHeight - 100) / 2 + "px";
		}
		window.setTimeout("fun5()", 3);
	} else {
		newWidth = 10;
		newHeight = 10;
		ChangeH = 50;
		ChangeW = 50;
		$ge("tab1").className = "tab_block";
		myAlert.style.background = "none";
	}
}
function fun6() {
	$ge("formActive.rowSpan").value = "1";
	$ge("formActive.labelColSpan").value = "1";
	$ge("formActive.fieldColSpan").value = "1";
	$ge("formActive.labelWidth").value = "0";
	$ge("formActive.fieldWidth").value = "0";
	$ge("formActive.maxLength").value = "0";
	$ge("formActive.rowSpan").value = "1";
	var tFormId = $ge("formId").value;
	var tTable = $ge("form" + tFormId);
	$ge("formActive.row").value = tTable.rows.length + 1;
	$ge("formActive.labelCol").value = "1";
	$ge("formActive.fieldCol").value = "2";
}
function fun7(id) {
	var obj = $ge(id);
	oDiv = $ge("div11");
	if (dragTable != null) {
		oDiv.removeChild(dragTable);
	}
	newElementType = obj.id;
	oRect = obj.parentNode.getBoundingClientRect();
	selectDiv = obj.parentNode;
	dragTable = $ce("table");
	var tr = dragTable.insertRow(0);
	var td1 = tr.insertCell(0);
	var td2 = tr.insertCell(1);
	dragImg = $ce("<img>");
	dragImg.className = "dragImg";
	dragImg.src = obj.src;
	dragImg.height = 16 + "px";
	dragImg.width = 16 + "px";
	var label = obj.parentNode.getElementsByTagName("label")[0];
	dragLabel = $ce("<label>");
	dragLabel.innerHTML = label.innerHTML;
	td1.appendChild(dragImg);
	td2.appendChild(dragLabel);
	oDiv.appendChild(dragTable);
	oDiv.style.left = oRect.left;
	oDiv.style.top = oRect.top;
	oDiv.style.background = "#848284";
}
function fun8(obj) {
	selectDiv.style.display = "block";
	if (!flag) {
	}
}
function fun9(obj) {
	$ge("tab1").className = "tab_hide";
	$ge("tab2").className = "tab_hide";
	$ge("tab3").className = "tab_hide";
	$ge("tab4").className = "tab_hide";
	var spanId = obj.id;
	$ge(spanId.substring(0, 4)).className = "tab_block";
}
function fun10() {
	var formId = LRTrim($ge("insertFormId").value);
	var title = LRTrim($ge("insertTitle").value);
	var formTitleShowFlag = LRTrim($ge("insertformTitleShowFlag").value);
	if (formId != null && formId != "" && title != null && title != ""
			&& formTitleShowFlag != null && formTitleShowFlag != "") {
		var url = "FormForToolBean_insertForm.action";
		var param = "formId=" + formId + "&" + "title=" + title + "&"
				+ "formTitleShowFlag=" + formTitleShowFlag;
		fun36(url, param, "insertForm", fun37);
	} else {
		var msg = "";
		if (formId == null || formId == "") {
			msg += "表单号不能为空；";
		}
		if (title == null || title == "") {
			msg += "表单描述不能为空；";
		}
		if (formTitleShowFlag == null || formTitleShowFlag == "") {
			msg += "是否显示标题不能为空；";
		}
		alert(msg);
	}
}
function fun11() {
	var copyFormId = LRTrim($ge("copyFormId").value);
	var formId = LRTrim($ge("newFormId").value);
	var title = LRTrim($ge("newTitle").value);
	var formTitleShowFlag = LRTrim($ge("newTitleShowFlag").value);
	if (formId != null && formId != "" && title != null && title != ""
			&& formTitleShowFlag != null && formTitleShowFlag != "") {
		var url = "FormForToolBean_copyForm.action";
		var param = "formId=" + formId + "&" + "title=" + title + "&"
				+ "formTitleShowFlag=" + formTitleShowFlag + "&"
				+ "copyFormId=" + copyFormId;
		fun36(url, param, "insertForm", fun37);
	} else {
		var msg = "";
		if (formId == null || formId == "") {
			msg += "表单号不能为空；";
		}
		if (title == null || title == "") {
			msg += "表单描述不能为空；";
		}
		if (formTitleShowFlag == null || formTitleShowFlag == "") {
			msg += "是否显示标题不能为空；";
		}
		alert(msg);
	}
}
function fun12() {
	var url = "FormForToolBean_updateForm.action";
	var formId = $ge("updateFormId").value;
	var title = $ge("updateTitle").value;
	var formTitleShowFlag = $ge("updateformTitleShowFlag").value;
	var param = "formId=" + formId + "&" + "title=" + title + "&"
			+ "formTitleShowFlag=" + formTitleShowFlag;
	fun36(url, param, "update", fun37);
}
function fun13() {
	if (currPage == 0) {
		currPage = 1;
	}
	var url = "FormForToolBean_searchFormList.action";
	var formId = $ge("searchFormId").value;
	var title = $ge("searchTitle").value;
	var param = "formId=" + formId + "&" + "title=" + title + "&eadis_page="
			+ currPage;
	fun36(url, param, "search", fun37);
}
function fun14() {
	var from = $ge("submitPropertyForm");
	if (action == "update") {
		from.action = "FormDataForToolBean_updateFormElement.action";
		from.action += "?formId=" + $ge("formId").value + "&formActiveId="
				+ formActiveId;
	} else {
		from.action += "?formId=" + $ge("formId").value;
	}
	from.submit();
}
function fun15() {
	if (action == "insert") {
		$ge("popAlert").style.display = "none";
	} else {
		window.location = "FormDataForToolBean_delFormElement.action?formId="
				+ $ge("formId").value + "&formActiveId=" + formActiveId;
	}
}
function fun16(obj) {
	if (($ge("formId").value != null && LRTrim($ge("formId").value) != "")
			|| obj == "searchFormDiv" || obj == "addForm") {
		if (obj == "searchFormDiv") {
			var table = $ge("formListTable");
			if (table.rows.length > 1) {
				for (i = table.rows.length - 1; i > 0; i--) {
					table.deleteRow(i);
				}
				table.height = 20;
			}
		}
		if (obj != "saveformulavalidate") {
			if ($ge(obj) != null) {
				if ($ge(obj).style.display == "none"
						|| $ge(obj).style.display == ""
						|| $ge(obj).style.display == null) {
					$ge(obj).style.display = "block";
					if (obj == "redForkConfirm") {
						$ge(obj).style.top = document.body.scrollTop + 200;
					}
				} else {
					$ge(obj).style.display = "none";
				}
			}
		}
		if (obj == "logicalFormulaDiv") {
			var formId = $ge('formId').value;
			var param = "formId=" + formId;
			var url = "FormDataForToolBean_getInitFormulavalidate.action";
			fun36(url, param, "initLogicalFormula", fun37);
		}
		if (obj == "formulavalidate") {
			var formId = $ge('formId').value;
			var formulavalidate = $ge("1formulavalidate1").value;
			formulavalidate = encodeURIComponent(formulavalidate);
			var param = "formId=" + formId + '&formulavalidate='
					+ formulavalidate;
			var url = "FormDataForToolBean_checkFormulavalidate.action";
			fun36(url, param, "1formulavalidate1", fun37);
		} else if (obj == "saveformulavalidate") {
			var formId = $ge('formId').value;
			var formulavalidate = $ge("1formulavalidate1").value;
			formulavalidate = encodeURIComponent(formulavalidate);
			var param = "formId=" + formId + '&formulavalidate='
					+ formulavalidate;
			var url = "FormDataForToolBean_saveFormulavalidate.action";
			fun36(url, param, "saveformulavalidate", fun37);
		} else if (obj == "linkageCalculationDiv") {
			$ge("linkageCalculation").value = $ge("formActive.calculation").value;
			$ge(obj).style.display = "none";
			var formId = $ge('formId').value;
			var param = "formId=" + formId;
			var url = "FormDataForToolBean_getInitCalculation.action";
			fun36(url, param, "initLC", fun37);
		} else if (obj == "redForkConfirm") {
			formActiveId = formActiveIdTemp;
		}
	} else {
		alert("请先新增一个form");
	}
}
function fun17() {
	var formId = $ge('formId').value;
	var calculation = $ge("linkageCalculation").value;
	calculation = encodeURIComponent(calculation);
	var param = "formId=" + formId + "&calculation=" + calculation;
	var url = "FormDataForToolBean_checkCalculation.action";
	fun36(url, param, "checkCalculation", fun37);
}
function fun18() {
	var rowNum = $ge('insertRowNum').value;
	var formId = $ge('formId').value;
	var param = "formId=" + formId + "&" + "lineNum=" + rowNum;
	window.location = 'FormDataForToolBean_insertLine.action?' + param;
}
function fun19() {
	var rowNum = $ge('deleteRowNum').value;
	var formId = $ge('formId').value;
	var param = "formId=" + formId + "&" + "lineNum=" + rowNum;
	window.location = 'FormDataForToolBean_delLine.action?' + param;
}
function fun20() {
	var string;
	try {
		string = document.all["formElementSelect"].value;
	} catch (exception) {
		string = "";
	}
	fun22(string);
}
function fun21() {
	var string;
	try {
		string = document.all["lCformElementSelect"].value;
	} catch (exception) {
		string = "";
	}
	fun23(string);
}
function fun22(string) {
	$ge("1formulavalidate1").focus();
	document.selection.createRange().text = string;
}
function fun23(string) {
	$ge("linkageCalculation").focus();
	document.selection.createRange().text = string;
}
function fun24(obj) {
	var temp = document.getElementById("1formulavalidate1");
	var tempLen;
	temp.value = temp.value.substring(0, temp.value.length - 1);
}
function fun25(type) {
	if (currPage > 0) {
		if (type == "next") {
			if (currPage == lastPage) {
				currPage = 0;
			}
			currPage++;
		} else if (type == "previous") {
			if (currPage == 0) {
				currPage = 1;
			}
			currPage--;
		} else if (type == "first") {
			currPage = 1;
		} else if (type == "last") {
			currPage = lastPage;
		}
		fun13();
	}
}
function showFormMoveDiv() {
	var obj = event.srcElement;
	var obj_1 = null;
	if (obj.parentNode.tagName.toLowerCase() == "td") {
		obj = obj.parentNode;
	}
	var objHTML = "" + obj.innerHTML + "";
	if (obj.tagName.toLowerCase() == "td"
			&& objHTML.substring(0, 6) != "&nbsp;" && obj.innerHTML != ""
			&& obj.innerHTML != null) {
		currTd = obj;
		currClass = obj.className;
		var pos = new Array();
		var oDiv = document.all.tempDiv;
		oDiv.style.display = "block";
		firstCellX = obj.parentElement.rowIndex;
		firstCellY = obj.cellIndex;
		var groupName = obj.groupName;
		formId = $ge("formId").value;
		var table = $ge("form" + formId);
		if (table.rows[firstCellX].cells.length == firstCellY + 1) {
			if (firstCellY > 0
					&& table.rows[firstCellX].cells[firstCellY - 1].groupName == groupName) {
				obj_1 = obj;
				obj = table.rows[firstCellX].cells[firstCellY - 1];
				firstCellY = firstCellY - 1;
			}
		} else {
			if (table.rows[firstCellX].cells[firstCellY + 1].groupName == groupName) {
				obj_1 = table.rows[firstCellX].cells[firstCellY + 1];
			} else if (firstCellY > 0
					&& table.rows[firstCellX].cells[firstCellY - 1].groupName == groupName) {
				obj_1 = obj;
				obj = table.rows[firstCellX].cells[firstCellY - 1];
				firstCellY = firstCellY - 1;
			}
		}
		divwidth = obj.width;
		divheight = obj.offsetHeight;
		var divinnerHTML = obj.innerHTML;
		if (obj_1 != null) {
			divwidth += obj_1.width;
			divinnerHTML += obj_1.innerHTML;
		}
		try {
			oDiv.style.width = divwidth;
			oDiv.style.height = divheight;
		} catch (e) {
		}
		oDiv.style.top = event.clientY + document.body.scrollTop - 15;
		oDiv.style.left = event.clientX + document.body.scrollLeft
				- oDiv.offsetWidth / 2;
		oDiv.innerHTML = divinnerHTML;
		oDiv.style.backgroundColor = "";
		oDiv.align = "center";
		oDiv.style.cursor = "hand";
		movable = true;
	}
}
function fun26() {
	currX = 0;
	currY = 0;
	if (movable) {
		var oDiv = document.all.tempDiv;
		currX = event.clientX;
		currY = event.clientY;
		oDiv.style.top = currY + document.body.scrollTop - 15;
		oDiv.style.left = currX + document.body.scrollLeft - oDiv.offsetWidth
				/ 2;
		if (currTd != null && currTd.tagName == "TD") {
			currTd.className = currClass;
			currTd = null;
			currClass = null;
		}
		var table = $ge("form" + formId);
		fun32(table);
		if (secondCellX > -1 && secondCellY > -1) {
			currTd = table.rows[secondCellX].cells[secondCellY];
			if (currTd.tagName != null && currTd.tagName == "TD") {
				currClass = currTd.className;
				currTd.className = "dragTd";
			}
		}
	}
}
function fun27() {
	document.all.tempDiv.style.display = "none";
	if (movable) {
		var table;
		formId = $ge("formId").value;
		if (formId != null && LRTrim(formId) != "") {
			table = $ge("form" + formId);
		}
		fun32(table);
		if (currTd != null && currTd.className == "dragTd") {
			currTd.className = currClass;
			currTd = null;
			currClass = null;
		}
		if (table != null) {
			rowsLength = table.rows.length;
			if (movable) {
				if (secondCellY > -1 && secondCellX > -1) {
					if (!(secondCellX == firstCellX && secondCellY == firstCellY)) {
						var ftd = table.rows[firstCellX].cells[firstCellY];
						var ftd_1 = table.rows[firstCellX].cells[firstCellY + 1];
						var ftd_1InnerHTML = "";
						if (ftd_1 != null && undefined != ftd_1.innerHTML
								&& ftd_1.innerHTML != null)
							if (ftd_1InnerHTML.innerHTML == ""
									|| ftd_1InnerHTML.innerHTML == null
									|| ftd_1InnerHTML.substring(0, 6) == "&nbsp;"
									|| ftd.groupName != ftd_1.groupName) {
								ftd_1 = null;
							}
						var std = table.rows[secondCellX].cells[secondCellY];
						var stdInnerHTML = "" + std.innerHTML + "";
						if (ftd_1 == null) {
							if (stdInnerHTML.substring(0, 6) == "&nbsp;"
									|| std.innerHTML == ""
									|| std.innerHTML == null) {
								if (fun31()) {
									fun30();
									var param = "formId=" + formId
											+ "&formActiveId=" + formActiveId
											+ "&row=" + row + "&labelCol="
											+ labelCol + "&fieldCol="
											+ fieldCol;
									fun36(
											"FormDataForToolBean_updateRowAndCol.action",
											param, "updateRowAndCol", fun37);
								}
							}
						} else {
							if (stdInnerHTML.substring(0, 6) == "&nbsp;"
									|| std.innerHTML == ""
									|| std.innerHTML == null) {
								var gn = std.groupName;
								var std_1;
								if (secondCellY % 2 == 0) {
									std_1 = table.rows[secondCellX].cells[secondCellY + 1];
								}
								var temptd = std_1;
								if (std_1 == null || std_1.groupName != gn) {
									if (table.rows[secondCellX].cells[secondCellY - 1].groupName == gn) {
										std_1 = std;
										std = table.rows[secondCellX].cells[secondCellY - 1];
										secondCellY = secondCellY - 1;
										temptd = std;
									} else {
										std_1 = null;
									}
								}
								if (temptd != null && temptd.groupName == gn) {
									var temptdInnerHTML = "" + temptd.innerHTML
											+ "";
									if (temptdInnerHTML.substring(0, 6) == "&nbsp;"
											|| temptd.innerHTML == ""
											|| temptd.innerHTML == null) {
										if (ftd != null && std != null) {
											if (ftd_1 != null && std_1 != null) {
												if (fun31()) {
													fun30();
													var param = "formId="
															+ formId
															+ "&formActiveId="
															+ formActiveId
															+ "&row=" + row
															+ "&labelCol="
															+ labelCol
															+ "&fieldCol="
															+ fieldCol;
													fun36(
															"FormDataForToolBean_updateRowAndCol.action",
															param,
															"updateRowAndCol",
															fun37);
												}
											} else if (ftd_1 == null
													&& std_1 == null) {
												if (fun31()) {
													fun30();
													var param = "formId="
															+ formId
															+ "&formActiveId="
															+ formActiveId
															+ "&row=" + row
															+ "&labelCol="
															+ labelCol
															+ "&fieldCol="
															+ fieldCol;
													fun36(
															"FormDataForToolBean_updateRowAndCol.action",
															param,
															"updateRowAndCol",
															fun37);
												}
											}
										}
									}
								}
							}
						}
					}
				} else {
					table = $ge("form" + formId);
					var td = table.rows[firstCellX].cells[firstCellY];
					var td_1 = table.rows[firstCellX].cells[firstCellY + 1];
					if (td_1 == null || td.groupName != td_1.groupName) {
						td_1 = table.rows[firstCellX].cells[firstCellY - 1];
					}
					if (td_1 != null && td.groupName != td_1.groupName) {
						if (td.innerHTML != "&nbsp;" || td.innerHTML != ""
								|| td.innerHTML != null) {
							var table = $ge("form" + formId);
							var oRect = table.getBoundingClientRect();
							if (currX > oRect.right) {
								fun28();
							} else if (currY > oRect.bottom) {
								fun29();
								fun30();
								var param = "formId=" + formId
										+ "&formActiveId=" + formActiveId
										+ "&row="
										+ $ge("form" + formId).rows.length
										+ "&labelCol=1&fieldCol=2";
								fun36(
										"FormDataForToolBean_updateRowAndCol.action",
										param, "updateRowAndCol", fun37);
							}
						}
					} else {
						if (td.innerHTML != "&nbsp;" || td.innerHTML != ""
								|| td.innerHTML != null
								|| td_1.innerHTML != "&nbsp;"
								|| td_1.innerHTML != ""
								|| td_1.innerHTML != null) {
							var table = $ge("form" + formId);
							var oRect = table.getBoundingClientRect();
							if (currX > oRect.right) {
								fun28();
							} else if (currY > oRect.bottom) {
								fun29();
								fun30();
								var param = "formId=" + formId
										+ "&formActiveId=" + formActiveId
										+ "&row="
										+ $ge("form" + formId).rows.length
										+ "&labelCol=1&fieldCol=2";
								fun36(
										"FormDataForToolBean_updateRowAndCol.action",
										param, "updateRowAndCol", fun37);
							}
						}
					}
				}
			}
		}
	}
	secondCellY = -1;
	secondCellX = -1;
	movable = false;
}
function fun28() {
	var table = $ge("form" + formId);
	for (i = 0; i < table.rows.length; i++) {
		var tr = table.rows[i];
		var td = tr.insertCell(tr.cells.length);
		td.innerHTML = "&nbsp;";
		var td_1 = tr.insertCell(tr.cells.length);
		td_1.innerHTML = "&nbsp;"
	}
}
function fun29() {
	var table = $ge("form" + formId);
	var oRect = table.getBoundingClientRect();
	if (currY > oRect.bottom) {
		var firstTd = table.rows[firstCellX].cells[firstCellY];
		var ondblclick = "" + firstTd.ondblclick;
		var temp = ondblclick.substring(ondblclick.indexOf("'") + 1,
				ondblclick.length);
		formActiveId = temp.substring(0, temp.indexOf("'"));
		var firstGroup = firstTd.groupName;
		var tr = table.rows[firstCellX];
		var tdList = tr.cells;
		var newtr = table.insertRow(table.rows.length);
		var cellCount = 0;
		for (i = 0; i < tdList.length; i++) {
			if (tr.cells[i].groupName == firstGroup) {
				var td = newtr.insertCell(cellCount++);
				var td0 = tr.cells[i];
				fun52(td0, td);
				if (tr.cells[i].colSpan > 1) {
					var tempColSpan = tr.cells[i].colSpan;
					tr.cells[i].colSpan = 1;
					for (j = i + 1; j < tempColSpan + i; j++) {
						var tempTd = tr.insertCell(j);
						tempTd.innerHTML = "&nbsp";
					}
				}
			}
		}
		$ge("redForkDiv").style.dsiplay = "none";
		table = $ge("form" + formId);
		var maxCells = 0;
		for (i = 0; i < table.rows[0].cells.length; i++) {
			var td = table.rows[0].cells[i];
			maxCells += td.colSpan;
		}
		var newtrcells = 0;
		for (i = 0; i < newtr.cells.length; i++) {
			var td = newtr.cells[i];
			newtrcells += td.colSpan;
		}
		while (newtrcells < maxCells) {
			var td = newtr.insertCell(newtrcells);
			td.innerHTML = "&nbsp";
			newtrcells += 1;
		}
	}
}
function fun30() {
	var table = $ge("form" + formId);
	var ttd = table.rows[0].cells[0];
	var cn = ttd.className;
	if (cn == "bartitle") {
		row = row - 1;
	}
}
function fun31() {
	var table = $ge("form" + formId);
	var rows = table.rows.length;
	var colums = table.rows[0].cells.length;
	var firstTd = table.rows[firstCellX].cells[firstCellY];
	var ondblclick = "" + firstTd.ondblclick;
	var temp = ondblclick.substring(ondblclick.indexOf("'") + 1,
			ondblclick.length);
	formActiveId = temp.substring(0, temp.indexOf("'"));
	var firstGroup = firstTd.groupName;
	var firstTd_1;
	colums = table.rows[firstCellX].cells.length - 1;
	if (firstCellY < colums
			&& table.rows[firstCellX].cells[firstCellY + 1].groupName == firstGroup) {
		firstTd_1 = table.rows[firstCellX].cells[firstCellY + 1];
	} else if (firstCellY > 0
			&& table.rows[firstCellX].cells[firstCellY - 1].groupName == firstGroup) {
		firstTd_1 = firstTd;
		firstTd = table.rows[firstCellX].cells[firstCellY - 1];
	}
	var secondTd = $ge("form" + formId).rows[secondCellX].cells[secondCellY];
	var secondGroup = secondTd.groupName;
	var secondTd_1;
	var fColSpan = 0;
	var sColSpan = 0;
	fColSpan = firstTd.colSpan;
	sColSpan = secondTd.colSpan;
	fieldCol = 0;
	labelCol = 0;
	if (firstTd_1 == null) {
		if (fColSpan == sColSpan) {
			row = secondTd.parentNode.rowIndex + 1;
			if (firstTd.innerHTML.indexOf("input") > -1) {
				fieldCol = secondTd.cellIndex + 1;
				labelCol = fieldCol - 1;
			} else {
				labelCol = secondTd.cellIndex + 1;
				fieldCol = labelCol + 1;
			}
			fun52(firstTd, secondTd);
			fun33(table);
			fun34(table);
			return true;
		} else if (fColSpan > sColSpan) {
			var currRightCellY = -1;
			var currLeftCellY = -1;
			var secondRowCells = table.rows[secondCellX].cells.length;
			for ( var i = secondCellY + 1; i < secondRowCells; i++) {
				if (sColSpan >= fColSpan) {
					break;
				} else {
					var tempTd = table.rows[secondCellX].cells[i];
					var tempTdInnerHTML = "";
					if (undefined != tempTd && tempTd != null) {
						tempTdInnerHTML = "" + tempTd.innerHTML + "";
					}
					if (tempTd.innerHTML == "" || tempTd.innerHTML == null
							|| tempTdInnerHTML.substring(0, 6) == "&nbsp;") {
						sColSpan = sColSpan + tempTd.colSpan;
						currRightCellY = i;
					} else {
						break;
					}
				}
			}
			if (sColSpan < fColSpan) {
				for ( var i = secondCellY - 1; i >= 0; i--) {
					if (sColSpan >= fColSpan) {
						break;
					} else {
						var tempTd = table.rows[secondCellX].cells[i];
						var tempTdInnerHTML = "";
						if (undefined != tempTd && tempTd != null) {
							tempTdInnerHTML = "" + tempTd.innerHTML + "";
						}
						if (tempTd.innerHTML == "" || tempTd.innerHTML == null
								|| tempTdInnerHTML.substring(0, 6) == "&nbsp;") {
							sColSpan = sColSpan + tempTd.colSpan;
							currLeftCellY = i;
						} else {
							break;
						}
					}
				}
			}
			if (sColSpan >= fColSpan) {
				if (currRightCellY == -1) {
					currRightCellY = secondCellY;
				}
				if (currLeftCellY == -1) {
					currLeftCellY = secondCellY;
				}
				var tempTr = table.rows[secondCellX];
				for ( var i = currRightCellY; i > currLeftCellY; i--) {
					tempTr.deleteCell(i);
				}
				secondTd = table.rows[secondCellX].cells[currLeftCellY];
				secondTd.colSpan = fColSpan;
				row = secondTd.parentNode.rowIndex + 1;
				if (firstTd.innerHTML.indexOf("input") > -1) {
					fieldCol = secondTd.cellIndex + 1;
					labelCol = fieldCol - 1;
				} else {
					labelCol = secondTd.cellIndex + 1;
					fieldCol = labelCol + 1;
				}
				fun52(firstTd, secondTd);
				firstTd.colSpan = 1;
				for ( var i = firstCellY + 1; i < firstCellY + fColSpan; i++) {
					var td = table.rows[firstCellX].insertCell(i);
					td.innerHTML = "&nbsp;";
				}
				fun33(table);
				fun34(table);
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	} else {
		colums = table.rows[secondCellX].cells.length - 1;
		if (secondCellY < colums
				&& table.rows[secondCellX].cells[secondCellY + 1].groupName == secondGroup) {
			secondTd_1 = table.rows[secondCellX].cells[secondCellY + 1];
		} else if (secondCellY > 0
				&& table.rows[secondCellX].cells[secondCellY - 1].groupName == secondGroup) {
			secondTd_1 = secondTd;
			secondTd = table.rows[secondCellX].cells[secondCellY - 1];
		}
		fColSpan += firstTd_1.colSpan;
		if (secondTd_1 != null)
			sColSpan += secondTd_1.colSpan;
		if (sColSpan == fColSpan) {
			row = secondTd.parentNode.rowIndex + 1;
			labelCol = secondTd.cellIndex + 1;
			fieldCol = labelCol + 1;
			fun52(firstTd, secondTd);
			fun52(firstTd_1, secondTd_1);
			fun33(table);
			fun34(table);
			return true;
		} else if (fColSpan > sColSpan) {
			var f = true;
			var count = 0;
			for (i = 1; i < fColSpan; i++) {
				var tempTd = table.rows[secondCellX].cells[secondCellY + i];
				if (tempTd != null) {
					var tempHtml = tempTd.innerHTML;
					if (tempHtml.substring(0, 6) == "&nbsp;"
							|| tempTd.innerHTML == ""
							|| tempTd.innerHTML == null) {
						count++;
						continue;
					} else {
						f = false;
						break;
					}
				}
			}
			if (f && count == fColSpan - 1) {
				row = secondTd.parentNode.rowIndex + 1;
				labelCol = secondTd.cellIndex + 1;
				fieldCol = labelCol + 1;
				fun52(firstTd, secondTd);
				fun52(firstTd_1, secondTd_1);
				var tempTr = table.rows[secondCellX];
				var tempLength = tempTr.cells.length;
				var tempTd = table.rows[firstCellX].cells[firstCellY + 1];
				if (tempTd.colSpan > 1) {
					var tempColSpan = tempTd.colSpan;
					tempTd.colSpan = 1;
					var tempTr1 = table.rows[firstCellX];
					var tempLength1 = tempTr1.cells.length;
					for (i = 0; i < tempColSpan - 1; i++) {
						var td = tempTr1.insertCell(tempLength1);
						td.innerHTML = "&nbsp;";
						tempLength1 = tempTr1.cells.length;
					}
				}
				for (i = 0; i < fColSpan - sColSpan; i++) {
					tempTr.deleteCell(tempLength - 1);
					tempLength = tempTr.cells.length;
				}
				fun33(table);
				fun34(table);
				return true;
			} else {
				return false;
			}
		}
	}
}
function fun32(table) {
	secondCellX = -1;
	secondCellY = -1;
	if (table != null) {
		var oRect = table.getBoundingClientRect();
		if (currX < oRect.right && currX > oRect.left && currY < oRect.bottom
				&& currY > oRect.top) {
			var rowLength = table.rows.length;
			var colLength = 0;
			for (i = 0; i < table.rows.length; i++) {
				var tr = table.rows[i];
				var trRect = tr.getBoundingClientRect();
				if (currX < trRect.right && currX > trRect.left
						&& currY < trRect.bottom && currY > trRect.top) {
					secondCellX = i;
					for (j = 0; j < tr.cells.length; j++) {
						td = tr.cells[j];
						var tdRect = td.getBoundingClientRect();
						if (currX < tdRect.right && currX > tdRect.left
								&& currY < tdRect.bottom && currY > tdRect.top) {
							secondCellY = j;
							break;
						} else {
							continue;
						}
					}
					break;
				} else {
					continue;
				}
			}
		}
	}
}
function fun33(table) {
	var rl = table.rows.length;
	var f = true;
	for (i = rl - 1; i >= 0; i--) {
		var cl = table.rows[i].cells.length;
		for (j = 0; j < cl; j++) {
			var td = table.rows[i].cells[j];
			var cellInnerHTML = "" + td.innerHTML + "";
			if (cellInnerHTML.substring(0, 6) == "&nbsp;" || td.innerHTML == ""
					|| td.innerHTML == null) {
				continue;
			} else {
				f = false;
				break;
			}
		}
		if (f) {
			table.deleteRow(i);
		} else {
			break;
		}
	}
}
function fun34(table) {
	var rowLength = table.rows.length;
	var colLength = 0;
	var f = true;
	for (i = 0; i < table.rows[0].cells.length; i++) {
		colLength += table.rows[0].cells[i].colSpan;
	}
	for (j = 0; j < colLength; j++) {
		for (i = 0; i < rowLength; i++) {
			var tr = table.rows[i];
			var tempColLength = tr.cells.length;
			td = table.rows[i].cells[tempColLength - 1];
			var cellInnerHTML = "" + td.innerHTML + "";
			if (cellInnerHTML.substring(0, 6) == "&nbsp;" || td.innerHTML == ""
					|| td.innerHTML == null) {
				continue;
			} else {
				f = false;
				break;
			}
		}
		if (f) {
			for (k = 0; k < rowLength; k++) {
				var tr1 = table.rows[k];
				tr1.deleteCell(tr1.cells.length - 1);
			}
		} else {
			f = true;
			break;
		}
	}
}
document.onmouseup = function() {
	fun27();
};
document.onmousemove = function() {
	fun26();
};
var xmlhttp;
var result = "";
var type = null;
var url = "";
var param = "";
function fun35() {
	if (window.ActiveXObject) {
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	} else if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	}
}
function fun36(url1, param1, type1, statechangemethod) {
	url = url1;
	param = param1;
	type = type1;
	url = url + "?" + param;
	fun35();
	xmlhttp.open("post", url, true);
	xmlhttp.onreadystatechange = statechangemethod;
	xmlhttp.setRequestHeader("Content-Type", "text/html;charset=UTF-8");
	xmlhttp.setRequestHeader("If-Modified-Since", "0");
	xmlhttp.send(null);
	if (type == "search") {
		$ge("searchFormLoadingDiv").style.display = "block";
	}
}
function fun37() {
	if (xmlhttp.readystate == 4 && xmlhttp.status == 200) {
		result = xmlhttp.responseText;
		if (result == null) {
			result = "error";
		} else {
			fun38();
		}
	}
}
function fun38() {
	if (type == "insertForm") {
		if (result == 0000) {
			var temp = param.split("&")[0];
			var formId = temp.substring(7, temp.length);
			$ge("formId").value = formId;
			$ge("addForm").style.display = "none";
			fun47(formId);
		} else {
			alert(result);
		}
	} else if (type == "search") {
		$ge("searchFormLoadingDiv").style.display = "none";
		fun43(result);
	} else if (type == "view") {
		alert(result);
	} else if (type == "update") {
		if (result == 0000) {
			var temfromId = param.split("&")[0];
			var formId = temfromId.substring(7, temfromId.length);
			var updateButton = $ge(formId + "_updateButton");
			var tr = updateButton.parentNode.parentNode;
			var temptitle = param.split("&")[1];
			var title = temptitle.substring(6, temptitle.length);
			tr.cells[0].innerHTML = title;
			alert("修改成功");
		} else {
			alert(result);
		}
	} else if (type == "del") {
		if (result == 0000) {
			var formId = param.split("=")[1];
			var delButton = $ge(formId + "_delButton");
			var tr = delButton.parentNode.parentNode;
			var table = tr.parentNode;
			table.deleteRow(tr.rowIndex);
			alert("删除成功");
		} else {
			alert(result);
		}
	} else if (type == "updateRowAndCol") {
		if (result != 0000) {
			alert(result);
		}
	} else if (type == "getFormElement") {
		fun42();
	} else if (type == "initLogicalFormula") {
		fun39();
	} else if (type == "formulavalidate") {
		fun49(result);
	} else if (type == "saveformulavalidate") {
		fun50(result);
	} else if (type == "initLC") {
		fun40();
	} else if (type == "checkCalculation") {
		fun41();
	} else if (type == "1formulavalidate1") {
		if (result == 0000) {
			alert("校验通过");
		} else {
			alert(result);
		}
	}
}
function fun39() {
	xmlDoc = xmlhttp.responseXML;
	var rootNode = xmlDoc.selectNodes("/testajax")[0];
	var childNodeList = rootNode.childNodes;
	var element = $ge("1formulavalidate1");
	if (element != null) {
		element.value = "";
		if (LRTrim(childNodeList[0].childNodes[0].nodeValue) != "null") {
			element.value = LRTrim(childNodeList[0].childNodes[0].nodeValue);
		}
	}
	var objItemText = childNodeList[1].nodeName;
	var objItemValue = LRTrim(childNodeList[1].childNodes[0].nodeValue);
	if (objItemValue.length >= 4) {
		var select = $ge("formElementSelect");
		select.options.length = 0;
		var itemList = objItemValue.split(";");
		for (i = 0; i < itemList.length; i++) {
			tempList = itemList[i].split("-");
			var itemValue = tempList[0];
			var itemText = tempList[1];
			var varItem = new Option(itemText, itemValue);
			select.options.add(varItem);
		}
	}
	$ge("logicalFormulaDiv").style.display = "block";
}
function fun40() {
	xmlDoc = xmlhttp.responseXML;
	var rootNode = xmlDoc.selectNodes("/testajax")[0];
	var childNodeList = rootNode.childNodes;
	var objValue = LRTrim(childNodeList[0].childNodes[0].nodeValue);
	if (objValue.length >= 4) {
		var select = $ge("lCformElementSelect");
		select.options.length = 0;
		var itemList = objValue.split(";");
		for (i = 0; i < itemList.length; i++) {
			tempList = itemList[i].split("-");
			var itemValue = tempList[0];
			var itemText = tempList[1];
			var varItem = new Option(itemText, itemValue);
			select.options.add(varItem);
		}
	}
	$ge("linkageCalculationDiv").style.display = "block";
}
function fun41() {
	if (result == 0000) {
		$ge("linkageCalculationDiv").style.display = "none";
		$ge("formActive.calculation").value = $ge("linkageCalculation").value;
	} else {
		alert(result);
	}
}
function fun42() {
	xmlDoc = xmlhttp.responseXML;
	var rootNode = xmlDoc.selectNodes("/testajax")[0];
	var childNodeList = rootNode.childNodes;
	for (i = 0; i < childNodeList.length; i++) {
		var element = $ge("formActive." + childNodeList[i].nodeName);
		if (element != null) {
			element.value = "";
			if (LRTrim(childNodeList[i].childNodes[0].nodeValue) != "null") {
				element.value = LRTrim(childNodeList[i].childNodes[0].nodeValue);
			}
		}
	}
	myAlert = $ge("popAlert");
	fun4(600, 200, myAlert);
	myAlert.style.position = "absolute";
}
function fun43(result) {
	var resultList = result.split(';');
	var table = $ge("formListTable");
	var rowCount = table.rows.length - 1;
	if (rowCount > 0) {
		for (i = rowCount; i > 0; i--) {
			table.deleteRow(i);
		}
	}
	rowCount = 1;
	for ( var i = 0; i < resultList.length - 1; i++) {
		var formPropertyList = resultList[i].split(',');
		if (formPropertyList[0] != undefined && formPropertyList[0] != null
				&& formPropertyList[0] != "") {
			var tr = table.insertRow(rowCount++);
			var td0 = tr.insertCell(0);
			td0.align = "center";
			if (formPropertyList[0] == undefined || formPropertyList[0] == null
					|| formPropertyList[0] == 'undefined'
					|| formPropertyList[0] == 'null') {
				td0.innerHTML = "";
			} else {
				td0.innerHTML = formPropertyList[1];
			}
			var td1 = tr.insertCell(1);
			td1.align = "center";
			td1.innerHTML = formPropertyList[0];
			var td2 = tr.insertCell(2);
			td2.align = "center";
			var updateButton = $ce("<button>");
			updateButton.id = formPropertyList[0] + "_updateButton";
			updateButton.onclick = function() {
				var formId = this.id.split('_')[0];
				fun45(formId, formPropertyList[1], formPropertyList[2]);
			};
			updateButton.innerHTML = "修改描述";
			td2.appendChild(updateButton);
			var td3 = tr.insertCell(3);
			td3.align = "center";
			var delButton = $ce("<button>");
			delButton.id = formPropertyList[0] + "_delButton";
			delButton.onclick = function() {
				var formId = this.id.split('_')[0];
				fun46(formId);
			};
			delButton.innerHTML = "删除表单";
			td3.appendChild(delButton);
			var td4 = tr.insertCell(4);
			td4.align = "center";
			var viewButton = $ce("<button>");
			viewButton.id = formPropertyList[0] + "_viewButton";
			viewButton.onclick = function() {
				var formId = this.id.split('_')[0];
				fun47(formId);
			};
			viewButton.innerHTML = "详情维护";
			td4.appendChild(viewButton);
			var td5 = tr.insertCell(5);
			td5.align = "center";
			var copyButton = $ce("<button>");
			copyButton.id = formPropertyList[0] + "_copyButton";
			copyButton.onclick = function() {
				var formId = this.id.split('_')[0];
				fun48(formId);
			};
			copyButton.innerHTML = "复制";
			td5.appendChild(copyButton);
		}
	}
	var pageStr = resultList[resultList.length - 1];
	lastPage = pageStr.substring(9, pageStr.length) * 1;
	lastPage = Math.ceil(lastPage / 10);
	table.height = rowCount * 8 + "%";
}
function fun44(obj) {
	$ge("updateFormDiv").style.display = "block";
	var formId = $ge("updateFormId");
	formId.value = obj;
	formId.setAttribute("readOnly", "true");
	$ge("updateTitle").focus();
}
function fun45(obj1, obj2, obj3) {
	$ge("updateFormDiv").style.display = "block";
	var formId = $ge("updateFormId");
	formId.value = obj1;
	var updateTitle = $ge("updateTitle");
	updateTitle.value = obj2;
	if (obj3 != null && obj3 != '') {
		var updateformTitleShowFlag = $ge("updateformTitleShowFlag");
		updateformTitleShowFlag.value = obj3;
	}
	formId.setAttribute("readOnly", "true");
	$ge("updateTitle").focus();
}
function fun46(obj) {
	var isdel = confirm("确认删除？");
	if (isdel) {
		fun36("FormForToolBean_deleteForm.action", "formId=" + obj, "del",
				fun37);
	}
}
function fun47(obj) {
	window.location = 'FormForToolBean_initForm.action?formId=' + obj;
}
function fun48(obj) {
	$ge("copyFormDiv").style.display = "block";
	$ge("copyFormId").value = obj;
}
function fun49(result) {
	if (result == 0000) {
	} else {
		alert(result);
	}
}
function fun50(result) {
	if (result == 0000) {
		$ge("logicalFormulaDiv").style.display = "none";
		fun47($ge("formId").value);
	} else {
		alert(result);
	}
}
var td;
function showRedFork(id, obj) {
	formActiveIdTemp = id;
	formId = $ge("formId").value;
	var table = $ge("form" + formId);
	if ((obj.parentNode.cells.length != obj.cellIndex + 1)
			&& (obj.groupName == obj.parentNode.cells[obj.cellIndex + 1].groupName)) {
		td = obj.parentNode.cells[obj.cellIndex + 1];
	} else {
		td = obj;
	}
	var rect = td.getBoundingClientRect();
	var redForkDiv = $ge("redForkDiv");
	redForkDiv.style.display = "block";
	redForkDiv.style.left = rect.left + td.offsetWidth - 10
			+ document.body.scrollLeft;
	redForkDiv.style.top = rect.top - 3 + document.body.scrollTop;
}
function fun51() {
	window.location = "FormDataForToolBean_updateHide.action?formId="
			+ $ge("formId").value + "&formActiveId=" + formActiveId;
}
function cancleRedFork() {
	var e = window.event;
	if (!(e.clientX < td.getBoundingClientRect().right
			&& e.clientX > td.getBoundingClientRect().left
			&& e.clientY < td.getBoundingClientRect().bottom && e.clientX > td
			.getBoundingClientRect().top)) {
		$ge("redForkDiv").display = "none";
		redForkDiv.style.left = null;
		redForkDiv.style.top = null;
	}
}
function fun52(td1, td2) {
	var temp = td1.innerHTML;
	td1.innerHTML = "&nbsp;";
	td2.innerHTML = temp;
	td2.align = td1.align;
	td2.groupName = td1.groupName;
	td2.className = td1.className;
	td2.colSpan = td1.colSpan;
	td2.ondblclick = td1.ondblclick;
	td2.onmouseover = td1.onmouseover;
	td2.onmouseout = td1.onmouseout;
	td1.ondblclick = null;
	td1.onmouseover = null;
	td1.onmouseout = null;
	td1.groupName = null;
}