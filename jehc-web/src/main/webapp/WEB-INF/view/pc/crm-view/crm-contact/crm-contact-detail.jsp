<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户联系人详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>客户联系人详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">联系人id，主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="contactId"  placeholder="请输入联系人id，主键" value="${crmContact.contactId }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64" style="width: 300px;"  name="contactName" data-bv-notempty data-bv-notempty-message="请输入姓名"  placeholder="请输入姓名" value="${crmContact.contactName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">联系电话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  style="width: 300px;" name="contactTel" placeholder="请输入联系人电话" value="${crmContact.contactTel }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位</label>
				<div class="col-lg-2">
					<input type="hidden" id="postId_" value="${crmContact.postId }">
	        		<select class="form-control" id="postId" style="width: 300px;" name="postId" placeholder="请输入岗位"></select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">在职状态</label>
				<div class="col-lg-6">
					<select name="incumbency" style="width: 300px;" class="form-control">
						<option value="0" <c:if test="${crmContact.incumbency eq 0 }">selected</c:if>>在职</option>
						<option value="1" <c:if test="${crmContact.incumbency eq 1 }">selected</c:if>>已离职</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<div class="col-lg-6">
				<input class="form-control" type="hidden" maxlength="32" id="customerId" value="${crmContact.customerId }" name="customerId" placeholder="请输入客户编号">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">爱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;好</label>
				<div class="col-lg-6">
					<textarea rows="8" cols="120" class="form-control"  name="interests" placeholder="请输入爱好">${crmContact.interests }</textarea>
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
<script type="text/javascript" src="../view/pc/crm-view/crm-contact/crm-contact-detail.js"></script> 
</html>
