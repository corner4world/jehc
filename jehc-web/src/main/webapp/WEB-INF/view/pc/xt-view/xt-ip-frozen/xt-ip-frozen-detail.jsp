<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台IP冻结账户详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>平台IP冻结账户详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">IP冻结编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_ip_frozen_id"  placeholder="请输入IP冻结编号" value="${xtIpFrozen.xt_ip_frozen_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">IP地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="30"  data-bv-notempty data-bv-notempty-message="请输入IP地址"  name="xt_ip_frozen_address" placeholder="请输入IP地址" value="${xtIpFrozen.xt_ip_frozen_address }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_ip_frozen_status">
						<option value="">请选择</option>
						<option value="0" <c:if test="${xtIpFrozen.xt_ip_frozen_status eq 0}">selected</c:if>>正常</option>
						<option value="1" <c:if test="${xtIpFrozen.xt_ip_frozen_status eq 0}">selected</c:if>>冻结</option>
						<option value="2" <c:if test="${xtIpFrozen.xt_ip_frozen_status eq 0}">selected</c:if>>黑名单</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="500"  name="xt_ip_frozen_content" placeholder="请输入内容">${xtIpFrozen.xt_ip_frozen_content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_ip_frozen_ctime"  placeholder="请选择时间" value="${xtIpFrozen.xt_ip_frozen_ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_ip_frozen_mtime"  placeholder="请选择时间" value="${xtIpFrozen.xt_ip_frozen_mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人" value="${xtIpFrozen.xt_userinfo_realName }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-ip-frozen/xt-ip-frozen-detail.js"></script> 
</html>
