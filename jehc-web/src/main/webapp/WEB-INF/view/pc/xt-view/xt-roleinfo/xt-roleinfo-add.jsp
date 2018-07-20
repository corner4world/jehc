<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户角色表新增页面</title>
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
						创建角色
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group">
					<label class="col-lg-3 control-label">名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入角色名称"  name="xt_role_name" placeholder="请输入角色名称">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">描述</label>
					<div class="col-lg-6">
						<textarea class="form-control" rows="" cols="" name="xt_role_desc" placeholder="请输入描述"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">类型</label>
					<div class="col-lg-6">
						<select class="form-control" name="xt_role_type" data-bv-notempty data-bv-notempty-message="请选择类型"  data-bv-numeric data-bv-numeric-message="请选择类型"  placeholder="请选择类型">
							<option value="">请选择</option>
							<option value="0">平台权限</option>
							<option value="1">业务权限</option>
						</select>
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="addXtRoleinfo()">保存</button>
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
<script type="text/javascript" src="../view/pc/xt-view/xt-roleinfo/xt-roleinfo-add.js"></script> 
</html>
