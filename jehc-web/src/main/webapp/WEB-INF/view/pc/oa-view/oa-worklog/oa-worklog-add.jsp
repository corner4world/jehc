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
	<div class="panel-body">
		<div class="page-header">
			<h4>创建工作日志</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" name="oa_worklogTitle" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">工作内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" rows="3" name="oa_worklogContent" placeholder="请输入工作内容"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-lg-3 control-label">测试内容</label>
				<div class="col-lg-6">
					<input type="text" class="form-control" placeholder="用户名"  id="username" name="username" data-bv-notempty data-bv-notempty-message="请输入用户名" data-bv-regexp="true" data-bv-regexp-regexp="[0-9]{6}" data-bv-regexp-message="验证码格式不正确"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addOaWorkLog()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-worklog/oa-worklog-add.js"></script>
</html>
