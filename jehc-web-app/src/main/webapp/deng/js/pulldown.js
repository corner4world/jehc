$(function () {
	//获取要定位元素距离浏览器顶部的距离
	var navH = $(".mulu .tit").offset().top;
	//滚动条事件
	$(window).scroll(function () {
		//获取滚动条的滑动距离
		var scroH = $(this).scrollTop();
		//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
		if (scroH >= navH) {
			$(".mulu .tit").css({"position":"fixed", "top":0, "width":"1148px", "z-index":"1000", "border-radius":"5px", "box-shadow":"0 0 10px #ccc"});
			$(".mulu .tit span a").css({"color":"#FE5842"});
			$(".search1").fadeIn();
		} else {
			if (scroH < navH) {
				$(".mulu .tit").css({"position":"static", "width":"918px", "z-index":"1000", "box-shadow":"0 0 0px #ccc"});
				$(".mulu .tit span a").css({"color":"#000"});
				$(".search1").fadeOut();
			}
		}
	});
	//获取要定位元素距离浏览器顶部的距离
	var navH = $(".mulu .ctt").offset().top;
	//滚动条事件
	$(window).scroll(function (){
		//获取滚动条的滑动距离
		var scroH = $(this).scrollTop();
		//滚动条的滑动距离大于等于定位元素距离浏览器顶部的距离，就固定，反之就不固定
		if (scroH >= navH) {
			$(".mulu .ctt").css({"position":"fixed", "top":"41px", "width":"1188px", "z-index":"990", "border-radius":"5px", "box-shadow":"0 0 10px #ccc", "border-bottom":"2px solid #FE5842"});
			$(".yincang ,.mulu .ctt .guanggao").fadeIn();
			$(document).ready(function () {
				$(".yincang .sp1").click(function () {
					$(".mulu .ctt").css("margin", "-268px 0 0 0");
					$(this).css("display", "none");
					$(".yincang .sp2").css("display", "block");
				});
				$(".yincang .sp2").click(function () {
					$(".mulu .ctt").css("margin", "-0px 0 0 0");
					$(this).css("display", "none");
					$(".yincang .sp1").css("display", "block");
				});
			});
		} else {
			if (scroH < navH) {
				$(".mulu .ctt").css({"position":"static", "width":"958px", "top":"0", "z-index":"990", "box-shadow":"0 0 0px #ccc", "display":"block", "border-bottom":"0", "margin":"0"});
				$(".yincang").fadeOut();
				$(".yincang .sp1").css("display", "block");
				$(".yincang .sp2 ,.mulu .ctt .guanggao").css("display", "none");
			}
		}
	});
});

