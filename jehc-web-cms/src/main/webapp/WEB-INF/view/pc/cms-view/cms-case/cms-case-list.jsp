<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
 <head>
  <title>案例展示</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
  <meta name="author" content="某手机网站模板 jehc.com" />
  <meta name="keywords" content="这里填写你的关键字" />
  <meta name="description" content="jehc（jehc.com）是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
  <meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
  <link href="${sysPath }/deng/phone/css/style.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="${sysPath }/deng/phone/js/jquery-1.7.2.min.js"></script>
  <script src="${sysPath }/deng/phone/js/common.js" type="text/javascript"></script>
 </head>
 <body class="body_picture">
  <!--顶部开始-->
  <%@ include file="/WEB-INF/view/phone/include/common_header.jsp"%>
  <!--顶部结束-->
  <div id="content">
   <div class="picture">
    <ul class="wxlist">
     <a href="info/66.html"><li style="width:auto">
       <div class="InfoTitle">
        	案例展示案例名称
       </div>
       <div class="InfoTime">
        2015-01-01
       </div>
       <div class="InfoPicture">
        <img src="${sysPath }/deng/phone/images/1409305274.jpg" />
       </div>
       <div class="InfoSContent">
        	案例展示案例名称程
       </div>
       <div class="ShowInfo">
       	 点击阅读全文&gt;&gt;
       </div></li></a>
     <a href="${sysPath }/deng/phone/info/65.html"><li style="width:auto">
       <div class="InfoTitle">
        	案例展示案例名称
       </div>
       <div class="InfoTime">
        2015-01-16
       </div>
       <div class="InfoPicture">
        <img src="${sysPath }/deng/phone/images/1409305196.jpg" />
       </div>
       <div class="InfoSContent">
        	案例展示案例简介。
       </div>
       <div class="ShowInfo">
        	点击阅读全文&gt;&gt;
       </div></li></a>
     <a href="${sysPath }/deng/phone/info/64.html"><li style="width:auto">
       <div class="InfoTitle">
        	案例展示案例名称
       </div>
       <div class="InfoTime">
        2013-03-26
       </div>
       <div class="InfoPicture">
        <img src="${sysPath }/deng/phone/images/1409305057.jpg" />
       </div>
       <div class="InfoSContent">
       	 案例展示案例名称
       </div>
       <div class="ShowInfo">
        	点击阅读全文&gt;&gt;
       </div></li></a>
     <a href="${sysPath }/deng/phone/info/63.html"><li style="width:auto">
       <div class="InfoTitle">
        	案例展示案例名称
       </div>
       <div class="InfoTime">
        	2013-03-26
       </div>
       <div class="InfoPicture">
        <img src="${sysPath }/deng/phone/images/1409304823.jpg" />
       </div>
       <div class="InfoSContent">
      		  案例展示案例名称
       </div>
       <div class="ShowInfo">
        	点击阅读全文&gt;&gt;
       </div></li></a>
     <a href="info/62.html"><li style="width:auto">
       <div class="InfoTitle">
       		 案例展示案例名称
       </div>
       <div class="InfoTime">
       		2013-03-26
       </div>
       <div class="InfoPicture">
        <img src="images/1409304642.jpg" />
       </div>
       <div class="InfoSContent">
        	案例展示案例名称
       </div>
       <div class="ShowInfo">
       	 	点击阅读全文&gt;&gt;
       </div></li></a>
     <a href="info/61.html"><li style="width:auto">
       <div class="InfoTitle">
        	案例展示案例名称
       </div>
       <div class="InfoTime">
        2013-03-26
       </div>
       <div class="InfoPicture">
        <img src="images/1409304350.jpg" />
       </div>
       <div class="InfoSContent">
      	  案例展示案例名称
       </div>
       <div class="ShowInfo">
        	点击阅读全文&gt;&gt;
       </div></li></a>
     <a href="info/60.html"><li style="width:auto">
       <div class="InfoTitle">
      	  案例展示案例名称
       </div>
       <div class="InfoTime">
        2013-03-26
       </div>
       <div class="InfoPicture">
        <img src="images/1409304109.jpg" />
       </div>
       <div class="InfoSContent">
        	案例展示案例名称
       </div>
       <div class="ShowInfo">
       	 点击阅读全文&gt;&gt;
       </div></li></a>
     <a href="info/31.html"><li style="width:auto">
       <div class="InfoTitle">
       	 案例展示案例名称
       </div>
       <div class="InfoTime">
        2013-03-26
       </div>
       <div class="InfoPicture">
        <img src="${sysPath }/deng/phone/images/1409303896.jpg" />
       </div>
       <div class="InfoSContent">
        	案例展示案例名称
       </div>
       <div class="ShowInfo">
        	点击阅读全文&gt;&gt;
       </div></li></a>
    </ul>
    <div class="page"></div>
    <!-- 图文样式 列表结束-->
    <div class="clear"></div>
    <ul class="wxlist">
     <li>
      <form name="frmInfoSearch" method="post" action="/channel/search/l/cn">
       <div>
        <input name="Keywords" value="" style="height:30px" type="text" placeholder="请输入关键词" />
       </div>
       <div>
        <input name="btnSearch" class="ui-btn-submit" type="submit" value="站内搜索" />
       </div>
       <input type="hidden" name="__hash__" value="6afa20654b76de34b05d1fd3474228a7_8ac68abc43203a881952084acb260880" />
      </form></li>
    </ul>
    <div class="clear"></div>
   </div>
  </div>
  <!--页脚开始-->
  <%@ include file="/WEB-INF/view/phone/include/common_footer.jsp"%>
  <!--页脚结束-->
 </body>
</html>