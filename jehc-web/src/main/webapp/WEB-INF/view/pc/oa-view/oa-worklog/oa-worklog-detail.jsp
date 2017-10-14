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
<title>工作日志</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h3>工作日志详情</h3>
				</div>
				<form class="form-horizontal" id="defaultForm" method="post">
					<input class="form-control" type="hidden" name="oa_worklogID" value="${oaWorklog.oa_worklogID }">
					<div class="form-group">
						<label class="col-lg-3 control-label">标题</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" name="oa_worklogTitle" placeholder="请输入标题" value="${oaWorklog.oa_worklogTitle }">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">工作内容</label>
						<div class="col-lg-4">
							<textarea class="form-control" rows="3" name="oa_worklogContent" placeholder="请输入工作内容">${oaWorklog.oa_worklogContent }</textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">创建时间</label>
						<div class="col-lg-4">
							${oaWorklog.oa_worklogCreateTime }
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">创建人</label>
						<div class="col-lg-4">
							${oaWorklog.xt_userinfo_realName }
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
<script type="text/javascript" src="../view/pc/oa-view/oa-worklog/oa-worklog-detail.js"></script>
</html>
