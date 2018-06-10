/*
 * 列表获取焦点时，提示框
 */


$(document).ready(function(){
	var setText = function(event, api) {
		var target = $(event.originalEvent && event.originalEvent.target);
		if(target.length) {
			api.set('content', ("<div><table style='table-layout:fixed' width='200px'><tr><td style='word-wrap : break-word; overflow:hidden;'>"+target.text()+"</td></tr></table></div>")); // 更新内容
		}
	}
	// 在指定区域创建用于共享的tip
	if($("#tablist td[mytitle]")){
	if($("#tablist td[mytitle]").get(0)){
		$("#tablist td[mytitle]").each(function(){
			if($(this).attr("mytitle")){
				$(this).qtip({
					content: ("<div ><table style='table-layout:fixed' width='200px'><tr><td style='word-wrap : break-word; overflow:hidden;'>"+$(this).attr("mytitle")+"</td></tr></table></div>"),
					style: { 
						width:{min:200},
						padding: 5,
						background: '#CCCCCC',
						color: 'black',
						textAlign: 'left',
						border: {
							width: 5,
							radius: 7,
							color: '#CCCCCC'
						},
						tip: 'leftMiddle',//箭头所在图形位置
						name: 'dark' // Inherit the rest of the attributes from the preset dark style
					},
					position: {
						target: 'mouse', // 跟随鼠标指针
						effect: false, // 关闭效果
						corner: {
		                    tooltip: "leftMiddle", // 箭头所在位置
		                    target: "leftMiddle" // 提示框所在位置
	                  	},
						viewport: $(window),
						adjust: { x: 0, y: 0 } // tip位置偏移，防止遮住鼠标指针
					},
					events: {
						show: setText,
						move: setText // 移动时也设置内容
					}
				});
			}
		});
	}
	}
});