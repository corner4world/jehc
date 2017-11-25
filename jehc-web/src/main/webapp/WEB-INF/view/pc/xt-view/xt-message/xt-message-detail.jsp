<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>短消息详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>短消息详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_message_id"  placeholder="请输入主键" value="${xtMessage.xt_message_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">发送者</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="from_id" placeholder="请输入发送者" value="${xtMessage.fromName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">接收者</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="to_id" placeholder="请输入接收者" value="${xtMessage.toName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">送发内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="2147483647"  name="xt_meesage_content" placeholder="请输入送发内容">${xtMessage.xt_meesage_content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否已读</label>
				<div class="col-lg-6">
					<select class="form-control" name="isread" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${xtMessage.isread eq 0}">selected</c:if>>未读</option>
						<option value="1" <c:if test="${xtMessage.isread eq 1}">selected</c:if>>已读</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">发送时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="ctime"  placeholder="请选择时间" value="${xtMessage.ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">取读时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="readtime"  placeholder="请选择时间" value="${xtMessage.readtime }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-message/xt-message-detail.js"></script> 
</html>
