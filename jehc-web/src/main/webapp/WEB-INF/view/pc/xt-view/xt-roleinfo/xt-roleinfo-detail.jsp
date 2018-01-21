<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户角色表详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>角色详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">角色权限id</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_role_id"  placeholder="请输入角色权限id" value="${xtRoleinfo.xt_role_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入角色名称"  name="xt_role_name" placeholder="请输入角色名称" value="${xtRoleinfo.xt_role_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" rows="" cols="" name="xt_role_desc" placeholder="请输入描述">${xtRoleinfo.xt_role_desc }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_role_type" data-bv-notempty data-bv-notempty-message="请选择类型"  data-bv-numeric data-bv-numeric-message="请选择类型"  placeholder="请选择类型">
						<option value="">请选择</option>
						<option value="0"<c:if test="${xtRoleinfo.xt_role_type eq 0 }">selected</c:if> >平台权限</option>
						<option value="1"<c:if test="${xtRoleinfo.xt_role_type eq 1 }">selected</c:if> >业务权限</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					${xtRoleinfo.xt_role_createTime }
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					${xtRoleinfo.xt_role_updateTime }
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
<script type="text/javascript" src="../view/pc/xt-view/xt-roleinfo/xt-roleinfo-detail.js"></script> 
</html>
