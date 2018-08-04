<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>文件路径设置新增页面</title>
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
						创建文件路径
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入名称"  name="xt_path_name" placeholder="请输入名称">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">路&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;径</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入路径"  name="xt_path" placeholder="请输入路径">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">常量值唯一</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入常量值唯一"  name="xt_value" placeholder="请输入常量值唯一">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
					<div class="col-lg-6">
						<select class="form-control" maxlength="20"  data-bv-notempty data-bv-notempty-message="请选择"  name="xt_type" placeholder="请选择">
							<option value="">请选择</option>
							<option value="0">平台模块</option>
							<option value="1">业务模块</option>
						</select>
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="addXtPath()">保存</button>
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
<script type="text/javascript" src="../view/pc/xt-view/xt-path/xt-path-add.js"></script> 
</html>
