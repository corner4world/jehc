<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/taglibs.jsp"%>
<div class="clear"></div>
<div class="banner">
	<div class="width">
		<div id="btnL"></div>
		<div id="btnR"></div>
		<div class="bannerBox" id="gundong">
			<ul class="hd">
				<li>
					<h2>
						<span class="baokuan">今日爆款推荐</span><p class="time-item">距特卖结束
					    <span id="day_show">0</span>天
					    <strong id="hour_show">0</strong>时
					    <strong id="minute_show">0</strong>分
					    <strong id="second_show">0</strong>秒
					</p>
					</h2>
					<p class="p1">好吃的蛋糕巧克力蛋糕</p>
					<p class="p2">好吃的蛋糕巧克力蛋糕好吃的蛋糕巧克力蛋糕好吃的蛋糕巧克力蛋糕好吃的蛋糕巧克力蛋糕...</p>
					<p class="p3"><b><i>￥</i>88.0</b><span class="sp1">￥1200.0 <i class="zhekou">1.8折</i></span><span><font style="color:#FFB857;">8888</font> 人已开抢</span><button></button></p>
				</li>
				<li>
					<h2><span class="baokuan">今日爆款推荐1</span><p class="time-item">距特卖结束
					    <span id="day_show">0</span>天
					    <strong id="hour_show">0</strong>时
					    <strong id="minute_show">0</strong>分
					    <strong id="second_show">0</strong>秒
					</p>
					</h2>
					<p class="p1">好吃的蛋糕巧克力蛋糕</p>
					<p class="p2">好吃的蛋糕巧克力蛋糕好吃的蛋糕巧克力蛋糕好吃的蛋糕巧克力蛋糕好吃的蛋糕巧克力蛋糕...</p>
					<p class="p3"><b><i>￥</i>88.0</b><span class="sp1">￥1200.0 <i class="zhekou">1.8折</i></span><span><font style="color:#FFB857;">8888</font> 人已开抢</span><button></button></p>
				</li>
			</ul>
		</div>
	</div>
	<div class="picnews" id="box-163css">
		<div class="lef_rt_f">
			<div class="leftsor hd"></div>
			<div class="rightsor hd"></div>
	 	</div>
		<div class="soar_mid">    
			<div class="sor_1" style="display: block; ">
				<div class="sor_bg1">
					<a href="#" target="_blank"></a>			
				</div>
			</div>    
	        <div class="sor_1" style="display: none; ">
				<div class="sor_bg1 sor_bg2">
					<a href="#" target="_blank"></a>
				</div>
			</div>
		    <div class="sor_1" style="display: none; ">
				<div class="sor_bg1 sor_bg3">
					<a href="#" target="_blank"></a>
				</div>
			</div>
			<div class="sor_1" style="display: none; ">
				<div class="sor_bg1 sor_bg4">
					<a href="#" target="_blank"></a>
				</div>
			</div>
			<div class="sor_1" style="display: none; ">
				<div class="sor_bg1 sor_bg5">
					<a href="#" target="_blank"></a>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		$(function(){
			$('#box-163css .sor_1').soChange({
				botPrev: '#box-163css .leftsor',
				botNext: '#box-163css .rightsor',
				thumbNowClass: 'cur',
				slideTime: 300,
				changeTime: 3000,
				autoChange: false,
				delayTime: 600
			});
		});
	</script>
</div>