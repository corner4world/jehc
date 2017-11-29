<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>文件路径设置详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>文件路径设置详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">ID</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_path_id"  placeholder="请输入ID" value="${xtPath.xt_path_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入名称"  name="xt_path_name" placeholder="请输入名称" value="${xtPath.xt_path_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">路径</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入路径"  name="xt_path" placeholder="请输入路径" value="${xtPath.xt_path }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">常量值唯一</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入常量值唯一"  name="xt_value" placeholder="请输入常量值唯一" value="${xtPath.xt_value }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control" maxlength="20"  data-bv-notempty data-bv-notempty-message="请选择"  name="xt_type" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${xtPath.xt_type eq 0}">selected</c:if>>平台模块</option>
						<option value="1" <c:if test="${xtPath.xt_type eq 1}">selected</c:if>>业务模块</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_time"  data-bv-notempty data-bv-notempty-message="请输入创建时间"  placeholder="请选择时间" value="${xtPath.xt_time }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-path/xt-path-detail.js"></script> 
</html>
