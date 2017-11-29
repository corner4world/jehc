<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>xt_appkey_appsecret编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑xt_appkey_appsecret</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_appkey_appsecret_id"  placeholder="请输入主键" value="${xtAppkeyAppsecret.xt_appkey_appsecret_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_appkey_appsecret_name" placeholder="请输入名称" value="${xtAppkeyAppsecret.xt_appkey_appsecret_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_appkey_appsecret_status" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${xtAppkeyAppsecret.xt_appkey_appsecret_status eq 0}">selected</c:if> >正常</option>
						<option value="1" <c:if test="${xtAppkeyAppsecret.xt_appkey_appsecret_status eq 1}">selected</c:if>>禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">注备</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="xt_appkey_appsecret_remark" placeholder="请输入注备">${xtAppkeyAppsecret.xt_appkey_appsecret_remark }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateXtAppkeyAppsecret()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-appkey-appsecret/xt-appkey-appsecret-update.js"></script> 
</html>
