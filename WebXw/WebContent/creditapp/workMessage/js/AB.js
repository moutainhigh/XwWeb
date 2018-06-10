var pageNum=0;
function turnAorB(flag){
	 			if(flag==null){
	 				var $d = $(".rotate");
	 				if($d.hasClass("turnA")){
	 					flag = 1;
	 					$d.removeClass("turnA");
	 				}else{
	 					flag = 0;
	 					$d.addClass("turnA");
	 				}
	 			}
	 			//top.myLayout.center.children.layout1.toggle("west");
//	 			if(flag==pageNum) return;
//				var $d = $(".rotate");
//				var $Box = $(".middle-center");
//				var $UL = $(".outer-west-center ul");
				var $pages = $(".pt-page");
				$pages.attr("class","pt-page");
				$pages.eq(flag).addClass("pt-page-current");
				var outClass = '', inClass = '',animation=pageNum+"-"+flag;
				switch (animation){
					case "0-1"://A-B1
						outClass = 'pt-page-rotateCubeLeftOut';
						inClass = 'pt-page-rotateCubeLeftIn';
						break;
					case "1-0"://B1-A
						outClass = 'pt-page-rotateCubeRightOut';
						inClass = 'pt-page-rotateCubeRightIn';
						break;
						break;
					default:
						break;
				}
				$pages.eq(pageNum).addClass(outClass);
				$pages.eq(flag).addClass(inClass);
				pageNum = flag;
	 		}