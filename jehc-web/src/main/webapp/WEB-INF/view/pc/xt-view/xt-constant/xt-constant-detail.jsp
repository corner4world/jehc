<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>台平常量详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>台平常量详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_constant_id"  placeholder="请输入编号" value="${xtConstant.xt_constant_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">键</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_constantName" placeholder="请输入键" value="${xtConstant.xt_constantName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">值</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_constantValue" placeholder="请输入值" value="${xtConstant.xt_constantValue }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">述描</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_constantRemark" placeholder="请输入述描" value="${xtConstant.xt_constantRemark }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control" maxlength="10" value="0" data-bv-numeric data-bv-numeric-message="类型" name="xt_constantType" placeholder="请选择">
						<option value="">请选择</option>
						<option value="1" <c:if test="${xtConstant.xt_constantType eq 1 }">selected</c:if>>平台常量</option>
						<option value="2" <c:if test="${xtConstant.xt_constantType eq 2 }">selected</c:if>>业务常量</option>
						<option value="3" <c:if test="${xtConstant.xt_constantType eq 3 }">selected</c:if>>工作流常量</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">流程常量URL可缺省</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="800"  name="xt_constantURL" placeholder="请输入流程常量URL可缺省" value="${xtConstant.xt_constantURL }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-constant/xt-constant-detail.js"></script> 
</html>
