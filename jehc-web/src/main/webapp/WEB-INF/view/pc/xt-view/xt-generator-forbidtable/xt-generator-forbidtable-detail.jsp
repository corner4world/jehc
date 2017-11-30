<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>禁止使用代码生成器生成的表信息详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>禁止使用代码生成器生成的表信息详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_generator_forbidtable_id"  placeholder="请输入主键编号" value="${xtGeneratorForbidtable.xt_generator_forbidtable_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">表名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入表名"  name="xt_generator_forbidtable_table" placeholder="请输入表名" value="${xtGeneratorForbidtable.xt_generator_forbidtable_table }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="500"  name="xt_generator_forbidtable_remark" placeholder="请输入备注">${xtGeneratorForbidtable.xt_generator_forbidtable_remark }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_generator_forbidtable_ctime"  placeholder="请选择时间" value="${xtGeneratorForbidtable.xt_generator_forbidtable_ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="xt_generator_forbidtable_mtime"  placeholder="请选择时间" value="${xtGeneratorForbidtable.xt_generator_forbidtable_mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操作者</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入操作者" value="${xtGeneratorForbidtable.xt_userinfo_realName }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-generator-forbidtable/xt-generator-forbidtable-detail.js"></script> 
</html>
