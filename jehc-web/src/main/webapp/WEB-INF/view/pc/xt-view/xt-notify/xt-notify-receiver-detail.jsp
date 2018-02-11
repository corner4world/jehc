<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台通知</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>通知详情</h4>
		</div>
		<form class="form-horizontal form" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</label>
				<div class="col-lg-6">
					<label class="control-label">${xtNotifyReceiver.xt_notify_title }</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容</label>
				<div class="col-lg-6">
					<label class="control-label">${xtNotifyReceiver.xt_notify_content }</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">已读时间</label>
				<div class="col-lg-6">
                    <label class="control-label">${xtNotifyReceiver.read_time }</label>
                </div>
            </div>
			<div class="form-group">
				<label class="col-lg-3 control-label">发&nbsp;送&nbsp;&nbsp;人</label>
				<div class="col-lg-6">
                    <label class="control-label">${xtNotifyReceiver.sendUserRealName }</label>
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
<script type="text/javascript" src="../view/pc/xt-view/xt-notify/xt-notify-detail.js"></script> 
</html>
