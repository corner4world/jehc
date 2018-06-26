<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>联系我们</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="某手机网站模板 jehc.com" />
<meta name="keywords" content="这里填写你的关键字" />
<meta name="description" content="jehc（jehc.com）是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script>
	/* 
	 document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() { 
	 WeixinJSBridge.call('hideToolbar'); 
	 });
	 document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
	 WeixinJSBridge.call('hideOptionMenu');
	 });
	 */
	/*
	 function weixinAddContact(name){ 
	 WeixinJSBridge.invoke("addContact", {webtype: "1",username: name}, function(e) { 
	 //WeixinJSBridge.log(e.err_msg); 
	 //e.err_msg:add_contact:added 已经添加 
	 //e.err_msg:add_contact:cancel 取消添加 
	 //e.err_msg:add_contact:ok 添加成功 
	 if( e.err_msg == 'add_contact:ok'){ 
	 alert("关注成功");
	 } else if(e.err_msg == 'add_contact:added'){
	 alert("已经关注过!");
	 }else{
	 alert(e.err_msg);
	 }
	 }) 
	 } 
	 */
</script>
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
					联系我们
				</div>
				<div class="ChannelPicture">
					<img src="${jehcimg_base_url }${page.xt_attachmentPath }" alt="联系我们" />
				</div>
				<div class="ChannelContent">
					${page.content }
				</div>
			</li>
			</ul>
			<div class="clear"></div>
		</div>
	</div>
	<!--页脚开始-->
	<div id="footer">
		<div class="copyright">
			这里是底部信息<br>免责声明：所有资源仅供学习与参考，版权归原作者，请勿用于商业用途，否则产生的一切后果将由您自行承担，与jehc无关。
		</div>
	</div>
	<!--页脚结束-->
</body>
</html>