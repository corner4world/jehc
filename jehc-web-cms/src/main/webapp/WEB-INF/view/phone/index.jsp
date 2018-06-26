<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
 <head>
  <title>jEhc开源平台</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="author" content="某手机网站模板" />
  <meta name="keywords" content="这里填写你的关键字" />
  <meta name="description" content="jehc是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
  <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
  <script>  
  	  /* 
	  document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() { 
	  	WeixinJSBridge.call('hideToolbar'); 
	  });
	  document.addEventListener('WeixinJSBridgeReady', function onBridgeReady() {
		WeixinJSBridge.call('hideOptionMenu');
	  });
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
 <body>
  <!--页眉开始-->
  <div id="header">
   <!--幻灯片 开始-->
   <script type="text/javascript" src="${sysPath }/deng/phone/js/jquery.flexslider-min.js"></script>
   <script type="text/javascript">         
   		$(document).ready(function() {
	        $('.myflexslider').flexslider({
	            controlNav: true, 
	            directionNav: false,
	            animation: "slide"
	            //,manualControls:".myflexslider .slidenav"
	
	        });
        });
   </script>
   <div class="myflexslider" style="margin-top:1px;">
    <ul class="slides">
     <li><img src="${sysPath }/deng/phone/images/1408672627.jpg" width="100%" /></li>
     <li><img src="${sysPath }/deng/phone/images/1408672648.jpg" width="100%" /></li>
     <li><img src="${sysPath }/deng/phone/images/1408672632.jpg" width="100%" /></li>
     <li><img src="${sysPath }/deng/phone/images/1409647536.jpg" width="100%" /></li>
    </ul>
   </div>
   <!--幻灯片 结束-->
  </div>
  <div id="content">
   <!--导航开始-->
   <ul class="channellist">
    <li>
    	<a href="${sysPath }/cmsAboutController/about.html">
      	<div class="ChannelIcon">
       		<img src="${sysPath }/deng/phone/images/index/q1.png" />
      	</div>
      	<div class="ChannelName">
       		关于我们
      	</div>
      	</a>
    </li>
    <li>
    	<a href="${sysPath }/cmsNewsController/news.html">
	      	<div class="ChannelIcon">
	       		<img src="${sysPath }/deng/phone/images/index/q2.png" />
	      	</div>
	      	<div class="ChannelName">
	       		资讯中心
	      	</div>
      	</a>
    </li>
    <li>
    	<a href="${sysPath }/cmsProductCategoryController/productCategory.html">
      		<div class="ChannelIcon">
       			<img src="${sysPath }/deng/phone/images/index/q3.png" />
      		</div>
      		<div class="ChannelName">
     	 		 产品中心
      		</div>
      	</a>
    </li>
    <li>
    	<a href="${sysPath }/cmsCaseController/case.html">
      		<div class="ChannelIcon">
       			<img src="${sysPath }/deng/phone/images/index/q4.png" />
      		</div>
      		<div class="ChannelName">
       			案例展示
      		</div>
      	</a>
    </li>
    <!-- 
    <li>
    	<a href="video.html">
      		<div class="ChannelIcon">
       			<img src="${sysPath }/deng/phone/images/index/q5.png" />
      		</div>
      		<div class="ChannelName">
       			视频展示
      		</div>
      	</a>
    </li>
    -->
    <li>
    	<a href="${sysPath }/cmsMerchantsController/merchants.html">
      		<div class="ChannelIcon">
       			<img src="${sysPath }/deng/phone/images/index/q6.png" />
      		</div>
      		<div class="ChannelName">
       			招商加盟
      		</div>
      	</a>
    </li>
    
    <li>
    	<a href="${sysPath }/cmsRecruitmentController/recruitment.html">
      		<div class="ChannelIcon">
       			<img src="${sysPath }/deng/phone/images/index/q7.png" />
      		</div>
      		<div class="ChannelName">
       			招贤纳士
      		</div>
      	</a>
    </li>
    <li>
    	<a href="${sysPath }/cmsMessageController/message.html">
      		<div class="ChannelIcon">
       			<img src="${sysPath }/deng/phone/images/index/q8.png" />
      		</div>
      		<div class="ChannelName">
      			 在线留言
      		</div>
      	</a>
    </li>
    <li>
    	<a href="${sysPath }/cmsContactController/contact.html">
      		<div class="ChannelIcon">
       			<img src="${sysPath }/deng/phone/images/index/q9.png" />
      		</div>
      		<div class="ChannelName">
      	 		联系我们
      		</div>
      	</a>
     </li>
   </ul>
   <!--导航结束-->
   <div class="clear"></div>
  </div>
  <!--页脚开始-->
  <%@ include file="/WEB-INF/view/phone/include/common_footer.jsp"%>
  <!--页脚结束-->
 </body>
</html>