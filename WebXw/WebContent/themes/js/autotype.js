// JavaScript Document
$.fn.autotype = function() {
  var _this=$(this);
  var str=_this.html();
  // 正则替换多个空格;
  str=str.replace(/(\s){2,}/g,"$1");
	var index = 0;
	$(this).html('');
	var timer = function() {
		var args=arguments;
		var current = str.slice(index, index+1);
		// html标签完整输出,如：<p>
		if (current == '<'){
			index = str.indexOf('>', index) + 1;
		}
		else{
		index++;
		}
		_this.html(str.substring(0, index));
		clearTimeout(timer);
		setTimeout(args.callee,70)
	};
	// 延迟1s开始
	setTimeout(timer,1500);
}