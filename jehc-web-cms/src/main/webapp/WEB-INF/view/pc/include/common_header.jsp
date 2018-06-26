<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!--顶部开始-->
<div id="top">
	<!--频道导航 开始-->
	<script>
		window.onload = function() {
			var oWin = document.getElementById("window");
			var oLay = document.getElementById("overlay");
			var oBtn = document.getElementById("popmenu");
			var oClose = document.getElementById("close");
			oBtn.onclick = function() {
				oLay.style.display = "block";
				oWin.style.display = "block"
			};
			oLay.onclick = function() {
				oLay.style.display = "none";
				oWin.style.display = "none"
			}
		};
	</script>
	<div style="margin-bottom: 55px;">
		<div id="toolbar">
			<div class="fixed-green">
				<a class="ui-title" id="popmenu">${title }</a> 
				<a class="ui-btn-back" href="javascript:history.go(-1)"></a> 
				<a class="ui-btn-home" href="${sysPath }/index.html"></a>
			</div>
		</div>
		<div id="overlay"></div>
		<div id="window">
			<ul class="windowlist">
				<li><a href="${sysPath }/index.html"><span>网站首页</span></a></li>
				<li><a href="${sysPath }/cmsAboutController/about.html"><span>关于我们</span></a></li>
				<li><a href="${sysPath }/cmsNewsController/news.html"><span>资讯中心</span></a></li>
				<li><a href="${sysPath }/cmsProductCategoryController/productCategory.html"><span>产品中心</span></a></li>
				<li><a href="${sysPath }/cmsCaseController/case.html"><span>案例展示</span></a></li>
				<li><a href="${sysPath }/cmsVideoController/video.html"><span>视频展示</span></a></li>
				<li><a href="${sysPath }/cmsMerchantsController/merchants.html"><span>招商加盟</span></a></li>
				<li><a href="${sysPath }/cmsRecruitmentController/recruitment.html"><span>招贤纳士</span></a></li>
				<li><a href="${sysPath }/cmsMessageController/message.html"><span>在线留言</span></a></li>
				<li><a href="${sysPath }/cmsContactController/contact.html"><span>联系我们</span></a></li>
				<div class="clear"></div>
			</ul>
		</div>
	</div>
	<!--频道导航 结束-->
</div>
<!--顶部结束-->