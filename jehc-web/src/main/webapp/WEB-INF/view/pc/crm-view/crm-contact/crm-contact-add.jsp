<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户联系人新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建客户【${crmCustomer.name }】联系人</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">姓&nbsp;&nbsp;名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64" style="width: 300px;" name="contactName" data-bv-notempty data-bv-notempty-message="请输入姓名"  placeholder="请输入姓名">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">联系电话</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" maxlength="32" id="customerId" value="${crmContact.customerId }" name="customerId" placeholder="请输入客户编号">
					<input type="text" onblur="check(this)" class="form-control" style="width: 300px;" type="text" name="contactTel" placeholder="请输入联系电话">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗&nbsp;&nbsp;位</label>
				<div class="col-md-2">
	        		<select class="form-control" type="text" maxlength="32" id="postId" style="width: 300px;" name="postId" placeholder="请选择岗位">
	        		</select>
		        </div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">在职状态</label>
				<div class="col-lg-6">
					<select name="incumbency" style="width: 300px;" class="form-control">
						<option value="0">在职</option>
						<option value="1">已离职</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">爱&nbsp;&nbsp;好</label>
				<div class="col-lg-6">
					<textarea rows="8" cols="120" class="form-control"  name="interests" placeholder="请输入爱好"></textarea>
				</div>
			</div> 
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addCrmContact()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/crm-view/crm-contact/crm-contact-add.js"></script> 
</html>
