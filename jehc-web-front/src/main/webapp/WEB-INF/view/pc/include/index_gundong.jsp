<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/taglibs.jsp"%>
<style rel="stylesheet"  type="text/css">
<c:forEach var="bRecommend" items="${bRecommendList }" varStatus="status">
	.picnews .sor_1 .sor_bg${status.index + 1}{position:relative;background:url(${jehcimg_base_url}${bRecommend.xt_attachmentPath }) 0 0 no-repeat;width:1920px;height:470px}
</c:forEach>
</style>