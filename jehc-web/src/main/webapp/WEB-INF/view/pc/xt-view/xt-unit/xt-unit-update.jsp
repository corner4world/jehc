<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>商品(产品)单位编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑商品(产品)单位</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">单位编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_unit_id"  placeholder="请输入单位编号" value="${xtUnit.xt_unit_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">单位名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  data-bv-notempty data-bv-notempty-message="请输入单位名称"  name="xt_unitName" placeholder="请输入单位名称" value="${xtUnit.xt_unitName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateXtUnit()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-unit/xt-unit-update.js"></script> 
</html>
