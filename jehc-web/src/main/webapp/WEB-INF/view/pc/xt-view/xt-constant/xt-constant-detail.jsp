<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>台平常量详情页面</title>
</head>
<body>
	<div class="m-portlet">
		<div class="m-portlet__head">
			<div class="m-portlet__head-caption">
				<div class="m-portlet__head-title">
					<span class="m-portlet__head-icon m--hide">
					<i class="la la-gear"></i>
					</span>
					<h3 class="m-portlet__head-text">
						台平常量详情
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-1 control-label">编号</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_constant_id"  placeholder="请输入编号" value="${xtConstant.xt_constant_id }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">键</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_constantName" placeholder="请输入键" value="${xtConstant.xt_constantName }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">值</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_constantValue" placeholder="请输入值" value="${xtConstant.xt_constantValue }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_constantRemark" placeholder="请输入述描" value="${xtConstant.xt_constantRemark }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
					<div class="col-lg-6">
						<select class="form-control" maxlength="10" value="0" data-bv-numeric data-bv-numeric-message="类型" name="xt_constantType" placeholder="请选择">
							<option value="">请选择</option>
							<option value="1" <c:if test="${xtConstant.xt_constantType eq 1 }">selected</c:if>>平台常量</option>
							<option value="2" <c:if test="${xtConstant.xt_constantType eq 2 }">selected</c:if>>业务常量</option>
							<option value="3" <c:if test="${xtConstant.xt_constantType eq 3 }">selected</c:if>>工作流常量</option>
						</select>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">流程常量URL可缺省</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="800"  name="xt_constantURL" placeholder="请输入流程常量URL可缺省" value="${xtConstant.xt_constantURL }">
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="goback()">返回</button>
						</div>
						<div class="col m--align-right">
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-constant/xt-constant-detail.js"></script> 
</html>
