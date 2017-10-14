<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>蛋糕商城网上超市-预定</title>
</head>
<body id="dingbu">
<!-- 顶部 -->
<%@ include file="/WEB-INF/view/pc/include/common_header.jsp"%>
<!-- 滚动图片 -->
<%@ include file="/WEB-INF/view/pc/include/index_gundong.jsp"%>
<!-- 浮动在滚动层上面的今日推荐banner -->
<%@ include file="/WEB-INF/view/pc/include/index_banner.jsp"%>
<!-- 广告区域 -->
<%@ include file="/WEB-INF/view/pc/include/index_te.jsp"%>
<!-- 特惠区域 -->
<%@ include file="/WEB-INF/view/pc/include/index_tehui.jsp"%>
<!-- 生日区域 -->
<%@ include file="/WEB-INF/view/pc/include/index_shengri.jsp"%>
<!-- 祝寿区域 -->
<%@ include file="/WEB-INF/view/pc/include/index_zhushou.jsp"%>
<!-- 爱情区域 -->
<%@ include file="/WEB-INF/view/pc/include/index_aiqing.jsp"%>
<!-- 儿童区域 -->
<%@ include file="/WEB-INF/view/pc/include/index_ertong.jsp"%>
<!-- 婚礼区域 -->
<%@ include file="/WEB-INF/view/pc/include/index_hunli.jsp"%>
<!-- 庆典区域 -->
<%@ include file="/WEB-INF/view/pc/include/index_qindian.jsp"%>
<!-- 底部 -->
<%@ include file="/WEB-INF/view/pc/include/common_footer.jsp"%>
</body>
</html>
