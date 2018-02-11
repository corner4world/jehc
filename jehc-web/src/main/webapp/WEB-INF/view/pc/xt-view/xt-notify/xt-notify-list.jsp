<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台通知</title>
</head>
<body>
	<div class="panel panel-default">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#tab0" data-toggle="tab">
					我发送的通知
				</a>
			</li>
			<li>
				<a href="#tab1" data-toggle="tab">
					我收到的的通知
				</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="tab0">
				<div class="panel panel-default">
					<fieldset>
						<legend>查询区域</legend>
						<form method="POST" id="searchForm" class="form-inline">
							<div class="form-group">
								<label>标题</label>
								<input type="text" class="form-control" name="xt_notify_title" placeholder="请输入标题">
							</div>
						</form>
						<div class="form-group" style="margin-left: 35px;margin-top: 25px;">
							<button class="btn btn-primary" onclick="search('datatables')">
								<i class="glyphicon glyphicon-search"></i>&nbsp;检索
							</button>
							<button class="btn btn-default" onclick="resetAll();">重置</button>
						</div>
					</fieldset>
				</div>
				<div class="panel-body">
					<div class="btn-group pull-right" style="margin-right: 20px;">
						<jEhcPermissionTag:jehcBtnTag modules="addXtNotify">
							<button class="btn btn-default" onclick="toXtNotifyAdd()">
								<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>创建
							</button>
						</jEhcPermissionTag:jehcBtnTag>
						<jEhcPermissionTag:jehcBtnTag modules="delXtNotify">
							<button class="btn btn-default" onclick="delXtNotify()">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
							</button>
						</jEhcPermissionTag:jehcBtnTag>
						<button class="btn btn-default" onclick="search('datatables')">
							<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
						</button>
					</div>
					<table id="datatables" class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
								<th>序号</th>
								<th>标题</th>
								<th>创建时间</th>
								<th>创建人</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="tab-pane fade" id="tab1">
				<div class="panel panel-default">
					<fieldset>
						<legend>查询区域</legend>
						<form method="POST" id="searchXtNotifyReceiverForm" class="form-inline">
							<div class="form-group">
								<label>标题</label>
								<input type="text" class="form-control" name="xt_notify_title" placeholder="请输入标题">
							</div>
						</form>
						<div class="form-group" style="margin-left: 35px;margin-top: 25px;">
							<button class="btn btn-primary" onclick="search('xtNotifyReceiverDatatables')">
								<i class="glyphicon glyphicon-search"></i>&nbsp;检索
							</button>
							<button class="btn btn-default" onclick="resetAll('searchXtNotifyReceiverForm');">重置</button>
						</div>
					</fieldset>
				</div>
				<div class="panel-body">
					<div class="btn-group pull-right" style="margin-right: 20px;">
						<jEhcPermissionTag:jehcBtnTag modules="delXtNotifyReceiver">
							<button class="btn btn-default" onclick="delXtNotifyReceiver()">
								<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
							</button>
						</jEhcPermissionTag:jehcBtnTag>
						<button class="btn btn-default" onclick="search('xtNotifyReceiverDatatables')">
							<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
						</button>
					</div>
					<table id="xtNotifyReceiverDatatables" class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkXtNotifyReceiverall" /><span></span></label></th>
								<th>序号</th>
								<th>标题</th>
								<th>发送时间</th>
								<th>发送者</th>
								<th>操作</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-notify/xt-notify-list.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-notify/xt-notify-receiver-list.js"></script> 
</html>
