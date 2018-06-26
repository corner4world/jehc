<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>视频展示</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="某手机网站模板 jehc.com" />
<meta name="keywords" content="这里填写你的关键字" />
<meta name="description" content="jehc（jehc.com）是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
</head>
<body class="body_video">
	<!--顶部开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_header.jsp"%>
	<!--顶部结束-->
	<div id="content">
		<div class="video">
			<!-- 图文样式 列表开始-->
			<ul class="wxlist">
				<a href="info/199.html"><li style="width: auto">
						<div class="InfoTitle">陶瓷宣传专题片</div>
						<div class="InfoTime">2014-09-09</div>
						<div class="InfoSContent"></div>
						<div class="ShowInfo">点击查看详情&gt;&gt;</div>
				</li></a>
			</ul>
			<div class="page"></div>
			<!-- 图文样式 列表结束-->
			<div class="clear"></div>
			<ul class="wxlist">
				<li>
					<form name="frmInfoSearch" method="post"
						action="/channel/search/l/cn">
						<div>
							<input name="Keywords" value="" style="height: 30px" type="text" placeholder="请输入关键词" />
						</div>
						<div>
							<input name="btnSearch" class="ui-btn-submit" type="submit" value="站内搜索" />
						</div>
						<input type="hidden" name="__hash__" value="f355ebfe7253f78f55d116abd7fb6f19_6ba59d348db9620a677a220eea84e48c" />
					</form>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
	</div>
	<!--页脚开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_footer.jsp"%>
	<!--页脚结束-->
</body>
</html>