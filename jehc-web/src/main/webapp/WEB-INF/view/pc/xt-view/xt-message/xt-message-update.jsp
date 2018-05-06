<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>短消息编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑短消息</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_message_id"  placeholder="请输入主键" value="${xtMessage.xt_message_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">发送者编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="from_id" placeholder="请输入发送者编号" value="${xtMessage.from_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">接收者编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="to_id" placeholder="请输入接收者编号" value="${xtMessage.to_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">送发内容</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="2147483647"  name="xt_meesage_content" placeholder="请输入送发内容" value="${xtMessage.xt_meesage_content }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否已读0未读1已读</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="4"  name="isread" placeholder="请输入是否已读0未读1已读" value="${xtMessage.isread }">
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
					<button type="button" class="btn green" onclick="updateXtMessage()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-message/xt-message-update.js"></script> 
</html>
