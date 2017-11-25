<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>密码找回中心详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>密码找回中心详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_change_pwd_id"  placeholder="请输入主键" value="${xtChangePwd.xt_change_pwd_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">用户名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="user_name" placeholder="请输入用户名称" value="${xtChangePwd.user_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">登录账号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="login_id" placeholder="请输入登录账号" value="${xtChangePwd.login_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">性别</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="4"  name="sex" placeholder="请输入性别" value="${xtChangePwd.sex }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">手机号码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="11"  name="phone" placeholder="请输入手机号码" value="${xtChangePwd.phone }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">邮箱地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="mail" placeholder="请输入邮箱地址" value="${xtChangePwd.mail }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">居住地址</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="255"  name="address" placeholder="请输入居住地址">${xtChangePwd.address }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="ctime"  placeholder="请选择时间" value="${xtChangePwd.ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status">
						<option value="0" <c:if test="${xtChangePwd.status eq 0 }">selected</c:if>>待审核</option>
						<option value="1" <c:if test="${xtChangePwd.status eq 1 }">selected</c:if>>审核通过</option>
						<option value="2" <c:if test="${xtChangePwd.status eq 2 }">selected</c:if>>审核未通过</option>
					</select>
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
<script type="text/javascript" src="../view/pc/xt-view/xt-change-pwd/xt-change-pwd-detail.js"></script> 
</html>
