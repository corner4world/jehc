<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>商品(产品)单位新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建商品(产品)单位</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">单位名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  data-bv-notempty data-bv-notempty-message="请输入单位名称"  name="xt_unitName" placeholder="请输入单位名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtUnit()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-unit/xt-unit-add.js"></script> 
</html>
