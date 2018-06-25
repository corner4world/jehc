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
				<a href="/">
					<li>
						<div class="ChannelName">公司简介</div>
						<div class="ChannelPicture">
							<img src="${sysPath }/deng/phone/images/1408954023.jpg" />
						</div>
						<div class="ChannelSContent">
							科蚁团队致力于将jehc建立成为国内最专业的HTML5建站交流、学习、分享社区，打造HTML5建站生态圈，与HTML5建站爱好者共同成长进步。...
						</div>
						<div class="ShowInfo">点击查看详情&gt;&gt;</div>
					</li>
				</a>
				<a href="/">
					<li>
						<div class="ChannelName">发展理念</div>
						<div class="ChannelPicture">
							<img src="${sysPath }/deng/phone/images/1409279934.jpg" />
						</div>
						<div class="ChannelSContent">
							科蚁团队致力于将jehc建立成为国内最专业的HTML5建站交流、学习、分享社区，打造HTML5建站生态圈，与HTML5建站爱好者共同成长进步。...
						</div>
						<div class="ShowInfo">点击查看详情&gt;&gt;</div>
					</li>
				</a>
				<a href="/">
					<li>
						<div class="ChannelName">核心技术</div>
						<div class="ChannelPicture">
							<img src="${sysPath }/deng/phone/images/1409279958.jpg" />
						</div>
						<div class="ChannelSContent">
							科蚁团队致力于将jehc建立成为国内最专业的HTML5建站交流、学习、分享社区，打造HTML5建站生态圈，与HTML5建站爱好者共同成长进步。。
						</div>
						<div class="ShowInfo">点击查看详情&gt;&gt;</div>
					</li>
				</a>
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