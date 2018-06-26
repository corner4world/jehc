<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>关于我们</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="某手机网站模板 jehc.top" />
<meta name="keywords" content="这里填写你的关键字" />
<meta name="description" content="jehc（jehc.top）是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
</head>
<body class="body_page">
	<!--顶部开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_header.jsp"%>
	<!--顶部结束-->
	<div id="content">
		<div class="single">
			<ul class="wxlist">
				<c:forEach var="cmsAbout" items="${page.list }">
					<a href="${sysPath }/cmsAboutController/toCmsAboutDetail">
						<li>
							<div class="ChannelName">${cmsAbout.title }</div>
							<div class="ChannelPicture">
								<img src="${cmsAbout.xt_attachmentPath }" />
							</div>
							<div class="ChannelSContent">
								发布时间：<fmt:formatDate value="${cmsAbout.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
							</div>
							<div class="ShowInfo">点击查看详情&gt;&gt;</div>
						</li>
					</a>
				</c:forEach>
			</ul>
			<!--子频道显示 结束-->
			<div class="clear"></div>
		</div>
	</div>
	<!--页脚开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_footer.jsp"%>
	<!--页脚结束-->
</body>
</html>