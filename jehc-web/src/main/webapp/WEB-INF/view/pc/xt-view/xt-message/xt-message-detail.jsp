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
			<div class="row">
				<div class="col-md-1">
		        	<label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;发送者</label>
		        </div>
		        <div class="col-md-11">
		        	<label class="control-label">${xtMessage.fromName }</label>
		        </div>
			</div>
			<div class="row">
				<div class="col-md-1">
		        	<label class="control-label">&nbsp;&nbsp;&nbsp;&nbsp;接收者</label>
		        </div>
		        <div class="col-md-11">
		        	<label class="control-label">${xtMessage.toName }</label>
		        </div>
			</div>
			<div class="row">
				<div class="col-md-1">
		        	<label class="control-label">送发内容</label>
		        </div>
		        <div class="col-md-11">
		        	<label class="control-label">${xtMessage.xt_meesage_content }</label>
		        </div>
			</div>
			<div class="row">
				<div class="col-md-1">
		        	<label class="control-label">是否已读</label>
		        </div>
		        <div class="col-md-11">
		        	<label class="control-label">
		        		<c:if test="${xtMessage.isread eq 0}">未读</c:if>
		        		<c:if test="${xtMessage.isread eq 1}">已读</c:if>
		        	</label>
		        </div>
			</div>
			<div class="row">
				<div class="col-md-1">
		        	<label class="control-label">发送时间</label>
		        </div>
		        <div class="col-md-11">
		        	<label class="control-label">
		        		<fmt:formatDate value="${xtMessage.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		        	</label>
		        </div>
			</div>
			<div class="row">
				<div class="col-md-1">
		        	<label class="control-label">取读时间</label>
		        </div>
		        <div class="col-md-11">
		        	<label class="control-label">
		        		<fmt:formatDate value="${xtMessage.readtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
		        	</label>
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
