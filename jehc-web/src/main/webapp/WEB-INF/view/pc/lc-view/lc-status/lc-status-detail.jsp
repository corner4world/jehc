<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>流程状态详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>流程状态详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">流程状态编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="lc_status_id"  placeholder="请输入流程状态编号" value="${lcStatus.lc_status_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="lc_status_name" placeholder="请输入状态名称" value="${lcStatus.lc_status_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="lc_status_remark" placeholder="请输入备注">${lcStatus.lc_status_remark }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">流程常量</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="lc_status_constant" placeholder="请输入流程常量" value="${lcStatus.lc_status_constant }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="30"  name="lc_status_ctime" placeholder="请输入创建时间" value="${lcStatus.lc_status_ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="30"  name="lc_status_mtime" placeholder="请输入修改时间" value="${lcStatus.lc_status_mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作人" value="${lcStatus.xt_userinfo_realName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/lc-view/lc-status/lc-status-detail.js"></script> 
</html>
