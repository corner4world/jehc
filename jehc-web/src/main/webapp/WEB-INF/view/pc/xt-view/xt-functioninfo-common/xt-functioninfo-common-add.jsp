<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公共功能新增页面</title>
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
						创建公共功能
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="100"  name="xt_functioninfo_common_title" placeholder="请输入标题">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">方法名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="200"  name="xt_functioninfo_common_method" placeholder="请输入方法名称">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">功能地址</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="200"  name="xt_functioninfo_common_url" placeholder="请输入功能地址">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
					<div class="col-lg-6">
						<select class="form-control" name="xt_functioninfo_common_status" placeholder="请选择">
							<option value="">请选择</option>
							<option value="0">启用</option>
							<option value="1">禁用</option>
						</select>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="800"  name="xt_functioninfo_common_content" placeholder="请输入备注"></textarea>
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="addXtFunctioninfoCommon()">保存</button>
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
<script type="text/javascript" src="../view/pc/xt-view/xt-functioninfo-common/xt-functioninfo-common-add.js"></script> 
</html>
