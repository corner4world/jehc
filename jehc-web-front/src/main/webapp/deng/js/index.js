$(document).ready(function () {
    //首先将#back-to-top隐藏 
	$(".piao").hide(); 
    //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失 
	$(function () {
		$(window).scroll(function () {
			if ($(window).scrollTop() > 650) {
				$(".piao").fadeIn(500);
			} else {
				$(".piao").fadeOut(500);
			}
		}); 
    //当点击跳转链接后，回到页面顶部位置 
		$("#back-to-top").click(function () {
			$("body,html").animate({scrollTop:0}, 1000);
			return false;
		});
	}); 
    //当滚动条的位置处于距顶部100像素以下时，跳转链接出现，否则消失 
	$(".piao .a1").click(function (event) { //绑定按钮的单击事件
		$("html,body").animate({scrollTop:$("#shengri").offset().top}, 1000); //一秒跳转到该ID的位置
	});
	$(".piao .a2").click(function (event) { //绑定按钮的单击事件
		$("html,body").animate({scrollTop:$("#zhushou").offset().top}, 1000); //一秒跳转到该ID的位置
	});
	$(".piao .a3").click(function (event) { //绑定按钮的单击事件
		$("html,body").animate({scrollTop:$("#aiqing").offset().top}, 1000); //一秒跳转到该ID的位置
	});
	$(".piao .a4").click(function (event) { //绑定按钮的单击事件
		$("html,body").animate({scrollTop:$("#ertong").offset().top}, 1000); //一秒跳转到该ID的位置
	});
	$(".piao .a5").click(function (event) { //绑定按钮的单击事件
		$("html,body").animate({scrollTop:$("#hunli").offset().top}, 1000); //一秒跳转到该ID的位置
	});
	$(".piao .a6").click(function (event) { //绑定按钮的单击事件
		$("html,body").animate({scrollTop:$("#qindian").offset().top}, 1000); //一秒跳转到该ID的位置
	});
	/**
	$(".ll1").hover(function () {
		$(".l1").stop().animate({left:"215px"}, 0);
		$(".l1").stop().animate({left:"235px"}, 500);
	}, function () {
		$(".l1").stop().animate({left:"215px"}, 500);
		$(".l1").stop().animate({left:"-2015px"}, 0);
	});
	$(".l1").hover(function () {
		$(this).stop().animate({left:"235px"}, 0);
		$(this).stop().animate({left:"235px"}, 500);
	}, function () {
		$(this).stop().animate({left:"-2015px"}, 0);
	});
	$(".ll2").hover(function () {
		$(".l2").stop().animate({left:"215px"}, 0);
		$(".l2").stop().animate({left:"235px"}, 500);
	}, function () {
		$(".l2").stop().animate({left:"215px"}, 500);
		$(".l2").stop().animate({left:"-2015px"}, 0);
	});
	$(".l2").hover(function () {
		$(this).stop().animate({left:"235px"}, 0);
		$(this).stop().animate({left:"235px"}, 500);
	}, function () {
		$(this).stop().animate({left:"-2015px"}, 0);
	});
	$(".ll3").hover(function () {
		$(".l3").stop().animate({left:"215px"}, 0);
		$(".l3").stop().animate({left:"235px"}, 500);
	}, function () {
		$(".l3").stop().animate({left:"215px"}, 500);
		$(".l3").stop().animate({left:"-2015px"}, 0);
	});
	$(".l3").hover(function () {
		$(this).stop().animate({left:"235px"}, 0);
		$(this).stop().animate({left:"235px"}, 500);
	}, function () {
		$(this).stop().animate({left:"-2015px"}, 0);
	});
	$(".ll4").hover(function () {
		$(".l4").stop().animate({left:"215px"}, 0);
		$(".l4").stop().animate({left:"235px"}, 500);
	}, function () {
		$(".l4").stop().animate({left:"215px"}, 500);
		$(".l4").stop().animate({left:"-2015px"}, 0);
	});
	$(".l4").hover(function () {
		$(this).stop().animate({left:"235px"}, 0);
		$(this).stop().animate({left:"235px"}, 500);
	}, function () {
		$(this).stop().animate({left:"-2015px"}, 0);
	});
	$(".ll5").hover(function () {
		$(".l5").stop().animate({left:"215px"}, 0);
		$(".l5").stop().animate({left:"235px"}, 500);
	}, function () {
		$(".l5").stop().animate({left:"215px"}, 500);
		$(".l5").stop().animate({left:"-2015px"}, 0);
	});
	$(".l5").hover(function () {
		$(this).stop().animate({left:"235px"}, 0);
		$(this).stop().animate({left:"235px"}, 500);
	}, function () {
		$(this).stop().animate({left:"-2015px"}, 0);
	});
	$(".ll6").hover(function () {
		$(".l6").stop().animate({left:"215px"}, 0);
		$(".l6").stop().animate({left:"235px"}, 500);
	}, function () {
		$(".l6").stop().animate({left:"215px"}, 500);
		$(".l6").stop().animate({left:"-2015px"}, 0);
	});
	$(".l6").hover(function () {
		$(this).stop().animate({left:"235px"}, 0);
		$(this).stop().animate({left:"235px"}, 500);
	}, function () {
		$(this).stop().animate({left:"-2015px"}, 0);
	});
	**/
	$("#btnL").click(function () {
		$(".bannerBox ul").animate({left:"-380px"}, 0);
	});
	$("#btnR").click(function () {
		$(".bannerBox ul").animate({left:"0px"}, 0);
	});
	
	/*切换城市 click 事件*/
	$(".dianji-qh").click(function(event) {
		$(".select-city").show();
	});
	$(".close-select-city").click(function(event) {
		$(".select-city").hide();
	});
	$(".sl-city-con dd a").click(function(event) {
		var dqm = $(this).text();
		$(".dqm").text(dqm);
		$(".select-city").hide();
	});
});
//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量
var intDiff = parseInt(60000);//倒计时总秒数量
function timer(intDiff) {
	window.setInterval(function () {
		var day = 0, hour = 0, minute = 0, second = 0;//时间默认值        
		if (intDiff > 0) {
			day = Math.floor(intDiff / (60 * 60 * 24));
			hour = Math.floor(intDiff / (60 * 60)) - (day * 24);
			minute = Math.floor(intDiff / 60) - (day * 24 * 60) - (hour * 60);
			second = Math.floor(intDiff) - (day * 24 * 60 * 60) - (hour * 60 * 60) - (minute * 60);
		}
		if (minute <= 9) {
			minute = "0" + minute;
		}
		if (second <= 9) {
			second = "0" + second;
		}
		$("#day_show").html(day);
		$("#hour_show").html("<s id=\"h\"></s>" + hour);
		$("#minute_show").html("<s></s>" + minute);
		$("#second_show").html("<s></s>" + second);
		intDiff--;
	}, 1000);
}
$(function () {
	timer(intDiff);
}); 
//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量//倒计时总秒数量

//搜索框
$(function(){
	 //通用头部搜索切换
     $('#search-hd .search-input').on('input propertychange',function(){
         var val = $(this).val();
         if(val.length > 0){
             $('#search-hd .pholder').hide(0);
         }else{
             var index = $('#search-bd li.selected').index();
             $('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
         }
     })
     $('#search-bd li').click(function(){
         var index = $(this).index();
         $('#search-hd .pholder').eq(index).show().siblings('.pholder').hide(0);
         $('#search-hd .search-input').eq(index).show().siblings('.search-input').hide(0);
         $(this).addClass('selected').siblings().removeClass('selected');
         $('#search-hd .search-input').val('');
     });
})
//图片加载时候显示加载失败
$(document).ready(function () {
	$("img").one("error", function(e){
	     $(this).attr("src", "deng/images/missing.jpg");
	});
});


