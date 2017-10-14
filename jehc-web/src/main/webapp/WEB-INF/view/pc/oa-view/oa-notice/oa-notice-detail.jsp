<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公告管理</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h3>编辑公告</h3>
				</div>
				<form class="form-horizontal" id="defaultForm" method="post">
					<input class="form-control" type="hidden" name="oa_noticeID" value="${oaNotice.oa_noticeID }">
					<div class="form-group">
						<label class="col-lg-3 control-label">标题</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" name="oa_noticeTitle" placeholder="请输入标题" value="${oaNotice.oa_noticeTitle }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">工作内容</label>
						<div class="col-lg-4">
							<textarea class="form-control" rows="3" name="oa_noticeContent" placeholder="请输入公告内容">${oaNotice.oa_noticeContent }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">类型</label>
						<div class="col-lg-4">
							<select class="form-control show-tick cusp" data-style="btn-white" name="oa_noticeType" style="width: 200px;">
						    	<option value=''>请选择</option>
						    	<option value='1' <c:if test="${oaNotice.oa_noticeType eq 1 }">selected</c:if>>一般</option>
						    	<option value='2' <c:if test="${oaNotice.oa_noticeType eq 2 }">selected</c:if>>重要</option>
						    	<option value='3' <c:if test="${oaNotice.oa_noticeType eq 3 }">selected</c:if>>非常重要</option>
						    </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">创建时间</label>
						<div class="col-lg-4">
							${oaNotice.oa_noticeCreateTime }
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">创建人</label>
						<div class="col-lg-4">
							${oaNotice.xt_userinfo_realName }
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label"></label>
						<div class="col-lg-4">
							<button type="button" class="btn default" onclick="goback()">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-notice/oa-notice-detail.js"></script>
</html>
