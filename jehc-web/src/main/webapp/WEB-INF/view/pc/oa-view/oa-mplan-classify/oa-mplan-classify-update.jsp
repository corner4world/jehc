<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>个人计划分类编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑个人计划分类</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">我的计划分类ID自动自增列</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="oa_mplan_classify_id"  placeholder="请输入我的计划分类ID自动自增列" value="${oaMplanClassify.oa_mplan_classify_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">分类名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="oa_mplan_classify_name" placeholder="请输入分类名称" value="${oaMplanClassify.oa_mplan_classify_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateOaMplanClassify()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-mplan-classify/oa-mplan-classify-update.js"></script> 
</html>
