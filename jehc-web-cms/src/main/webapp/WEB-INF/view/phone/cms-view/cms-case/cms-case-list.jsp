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
    	<c:forEach var="cmsCase" items="${page.list }">
			<a href="${sysPath }/cmsCaseController/toCmsCaseDetail">
				<li style="width:auto">
			       <div class="InfoTitle">
			        	${cmsCase.title }
			       </div>
			       <div class="InfoTime">
			        	<fmt:formatDate value="${cmsCase.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
			       </div>
			       <div class="InfoPicture">
			        	<img src="${jehcimg_base_url }${cmsCase.xt_attachmentPath }" />
			       </div>
			       <div class="InfoSContent">
			        	${cmsCase.remark }
			       </div>
			       <div class="ShowInfo">
			       	 	点击阅读全文&gt;&gt;
			       </div>
		      </li>
	    	</a>
		</c:forEach>
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