<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>修改记录日志详情页面</title>
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
						日志详情
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_modify_record_id"  placeholder="请输入主键" value="${xtModifyRecord.xt_modify_record_id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">更变前值</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="2147483647"  name="xt_modify_record_beforevalue" placeholder="请输入更变前值">${xtModifyRecord.xt_modify_record_beforevalue }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">变更后值</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="2147483647"  name="xt_modify_record_aftervalue" placeholder="请输入变更后值">${xtModifyRecord.xt_modify_record_aftervalue }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">模块</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_modify_record_modules" placeholder="请输入模块" value="${xtModifyRecord.xt_modify_record_modules }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">字段</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="xt_modify_record_field" placeholder="请输入字段" value="${xtModifyRecord.xt_modify_record_field }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">创建时间</label>
					<div class="col-lg-6">
						<label class="control-label">
						${xtModifyRecord.xt_modify_record_ctime }
						</label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">创建人</label>
					<div class="col-lg-6">
						<label class="control-label">
						${xtModifyRecord.xt_userinfo_realName }
						</label>
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
						</div>
						<div class="col m--align-right">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="goback()">返回</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-modify-record/xt-modify-record-detail.js"></script> 
</html>
