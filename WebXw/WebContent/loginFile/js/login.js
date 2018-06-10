setTimeout(function() {
	
	$(".openTop").animate({
		height: '0%'
	}, 1000);
	$(".openBottom").animate({
		height: '0%'
	}, 1000);
	$("#login_logo").hide("slow");
	$("#starBack").show();
	
}, 1500);

var $showDiv;
$('#login-button').click(function(event) {
	//event.preventDefault();
	if(!checkInput())return false;
	var checkValidate = checkLogin();
	if(checkValidate){
		$showDiv = $('.text-sts');
	}else{
		$showDiv = $('.text-err');
		$("#loginFlag").val("1");
	}
	$('form').fadeOut(200, function() {
		$('.spinner').fadeIn(300).addClass("scaleBall");
	});
	$('.wrapper').addClass('form-success');
	setTimeout(function() {
		$('.spinner').removeClass('scaleBall').fadeOut(200, function() {
			$showDiv.fadeIn(300).addClass("scaleBall");
		});
		if(checkValidate){
			setTimeout(function(){
				location.href="creditapp/index20.jsp";
			},500);
		}
	}, 1000);
});

$('#backToLogin').click(function(event) {
	$showDiv.fadeOut(200, function() {
		$('.spinner').fadeIn(300).addClass("scaleBall");
	});
	$('.wrapper').removeClass('form-success');
	$("#loginFlag").val("0");
	setTimeout(function() {
		$('.spinner').removeClass('scaleBall').fadeOut(200, function() {
			$('.form').fadeIn(300).addClass("scaleBall");
		});
	}, 1000);
});

function checkInput(){
	var pname = document.getElementById('IdInput').value;
    var pwd= document.getElementById('PwdInput').value;
    if(!pname || pname==""){
    	$("#IdInput").attr("placeholder","用户名不得为空");
        $("#IdInput").addClass("invalid");
        return false;
    }
    if(!pwd || pwd==""){
    	$("#PwdInput").attr("placeholder","请输入密码");
        $("#PwdInput").addClass("invalid");
        return false;
    }
    return true;
}

function checkLogin(){
	var pname = document.getElementById('IdInput').value;
    var pwd= document.getElementById('PwdInput').value;
    var flag = false;
    $.ajax({
		url:"creditapp/cmsForAjax.action",
		data:{userId:pname,password:pwd},
		type:"post",
		cache:false,
		async:false,
		dataType:"text",
		success:function(data){
			if(data == "success")flag = true;
			else{
				var errMessage = data.split("|")[1];
				$("#errMessage").html(errMessage);
			}
		},
		error:function(){}
	});
    return flag;
}

$(".demo-1 .large-header0").animate({
	opacity: '0'
}, 10000);
var largeNum = 1;
var largeNew = 1;
var timer1 = setInterval(function() {
	if(largeNum < 3) {
		largeNum++;
	} else {
		largeNum = 1;
	};
	if(largeNew < 2) {
		largeNew++;
	} else {
		largeNew = 0;
	};
	largeOp = largeNum - 1;
	/*$(".demo-1 .large-header").removeClass("large-header1").removeClass("large-header2").removeClass("large-header3");					
	$(".demo-1 .large-header").addClass("large-header"+largeNum);
	$(".demo-1 .large-header").animate({ opacity: '1' }, 3000).animate({ opacity: '0.1' },3000);*/
	$(".demo-1 .large-header" + largeNew).css("z-index", "13").css("opacity", "1");
	$(".demo-1 .large-header" + largeOp).css("z-index", "14");
	
	$(".demo-1 .large-header" + largeOp).animate({
		opacity: '0'
	}, 6000);
	
}, 6000);
// Selecting the container element
/*
var el = document.querySelector('.enterLogo1');

// All the possible options (these are the default values)
// Remember that every option (except individualDelays) can be defined as single value or array
var options = {
	size: [70, 70, 70, 70, 70],
	weight: [10, 10, 10, 10, 10],
	color: '#fff',
	duration: [2.5, 2.6, 2.7, 2.8, 2.9],
	delay: [ 0.3, 0.35, 0.4, 0.45],
	fade: 1.5,
	easing: d3_ease.easeExpInOut.ease
};

// Initializing the text (Letters parameters: container-element, options)

var myText = new Letters(el, options);
$(function() {
	// Show letters with the default options defined
	myText.show();
});
*/