<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>xt_appkey_appsecret详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>xt_appkey_appsecret详情</h4>
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
				<label class="col-lg-3 control-label">appkey</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_appkey" placeholder="请输入appkey" value="${xtAppkeyAppsecret.xt_appkey }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">appsecret</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_appsecret" placeholder="请输入appsecret" value="${xtAppkeyAppsecret.xt_appsecret }">
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
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_ctime"  placeholder="请选择时间" value="${xtAppkeyAppsecret.xt_ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_mtime"  placeholder="请选择时间" value="${xtAppkeyAppsecret.xt_mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人" value="${xtAppkeyAppsecret.xt_userinfo_realName }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-appkey-appsecret/xt-appkey-appsecret-detail.js"></script> 
</html>
