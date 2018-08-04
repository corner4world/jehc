<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>编辑页面</title>
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
						编辑流程
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">流程编号</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="lc_process_id"  placeholder="请输入流程编号" value="${lcProcess.lc_process_id }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">流程标题</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" maxlength="4" value="1"  name="lc_process_flag" placeholder="请输入标识:0通过平台设计器设计1通过上传部署">
						<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入流程标题"  name="lc_process_title" placeholder="请输入流程标题" value="${lcProcess.lc_process_title }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
					<div class="col-lg-6">
						<select class="form-control" name="lc_process_status" placeholder="请选择">
							<option value="">请选择</option>
							<option value="0" <c:if test="${lcProcess.lc_process_status eq 0}">selected</c:if> >待发布</option>
							<option value="1" <c:if test="${lcProcess.lc_process_status eq 1}">selected</c:if> >发布中</option>
							<option value="2" <c:if test="${lcProcess.lc_process_status eq 2}">selected</c:if> >已关闭</option>
						</select>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" id="xt_constant_id_" value="${lcProcess.xt_constant_id }">
						<select class="form-control" name="xt_constant_id" id="xt_constant_id" placeholder="请选择"></select>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_attachment" id="xt_attachment" value="${lcProcess.xt_attachment }">
						<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_pic">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="800"  name="lc_process_remark" placeholder="请输入备注">${lcProcess.lc_process_remark }</textarea>
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--solid">
					<div class="row">
						<div class="col m--align-left">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="updateLcProcess()">保存</button>
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
<script type="text/javascript" src="../view/pc/lc-view/lc-process/lc-process-update.js"></script> 
</html>
