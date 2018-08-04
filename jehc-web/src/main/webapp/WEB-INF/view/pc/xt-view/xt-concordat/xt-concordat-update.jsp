<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>合同管理编辑页面</title>
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
						编辑合同
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">合同编号</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_concordat_id"  placeholder="请输入合同编号" value="${xtConcordat.xt_concordat_id }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">合同名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="50"  name="xt_concordatName" placeholder="请输入合同名称" value="${xtConcordat.xt_concordatName }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">合同描述</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="50"  name="xt_concordatDesc" placeholder="请输入合同描述">${xtConcordat.xt_concordatDesc }</textarea>
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="updateXtConcordat()">保存</button>
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="goback()">返回</button>
						</div>
						<div class="col m--align-right">
							<a href="javascript:resetAll('defaultForm')" class="btn btn-secondary m-btn m-btn--custom m-btn--icon">
								<span><i class="fa fa-repeat"></i><span>重置</span></span>
							</a>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-concordat/xt-concordat-update.js"></script> 
</html>
