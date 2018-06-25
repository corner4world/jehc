<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>招商加盟</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="某手机网站模板 jehc.com" />
<meta name="keywords" content="这里填写你的关键字" />
<meta name="description" content="jehc（jehc.com）是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
</head>
<body class="body_page">
	<!--顶部开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_header.jsp"%>
	<!--顶部结束-->
	<div id="content">
		<div class="single">
			<ul class="wxlist">
				<li>
					<div class="ChannelName">
						<h1>招商加盟</h1>
					</div>
					<div class="ChannelPicture">
						<img src="${sysPath }/deng/phone/images/1409446193.jpg" alt="招商加盟" />
					</div>
					<div class="ChannelContent">诚挚邀请您加盟某某陶瓷！！！</div>
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