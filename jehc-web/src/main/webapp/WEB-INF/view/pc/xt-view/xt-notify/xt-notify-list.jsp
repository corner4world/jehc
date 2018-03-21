<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台通知</title>
</head>
<body>
	<div class="portlet-title tabbable-line">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#tab0" data-toggle="tab">
					<i class="glyphicon glyphicon-pencil"></i>我发送的通知
				</a>
			</li>
			<li>
				<a href="#tab1" data-toggle="tab">
					<i class="glyphicon glyphicon-indent-left"></i>我收到的的通知
				</a>
			</li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="tab0">
				<div class="portlet box green" style="margin-bottom: 5px">
					<div class="portlet-title">
			            <div class="caption">
			                	查询区域
			            </div>
			            <div class="actions">
			                 <a class="btn btn-circle btn-icon-only btn-default" title="检索" href="javascript:search('datatables');">
			                     <i class="glyphicon glyphicon-search"></i>
			                 </a>
			                 <a class="btn btn-circle btn-icon-only btn-default" title="重置" href="javascript:resetAll();;">
			                     <i class="icon-trash"></i>
			                 </a>
			            </div>
			        </div>
			        <div class="portlet-body form">
			        	<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
							<div class="form-group">
								<label>标题</label>
								<input type="text" class="form-control" name="xt_notify_title" placeholder="请输入标题">
							</div>
						</form>
			        </div>
			    </div>
				<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
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
				<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
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
			<div class="tab-pane fade" id="tab1">
				<div class="portlet box green" style="margin-bottom: 5px">
					<div class="portlet-title">
			            <div class="caption">
			                	查询区域
			            </div>
			            <div class="actions">
			                 <a class="btn btn-circle btn-icon-only btn-default" title="检索" href="javascript:search('xtNotifyReceiverDatatables');">
			                     <i class="glyphicon glyphicon-search"></i>
			                 </a>
			                 <a class="btn btn-circle btn-icon-only btn-default" title="重置" href="javascript:resetAll('searchXtNotifyReceiverForm')">
			                     <i class="icon-trash"></i>
			                 </a>
			            </div>
			        </div>
			        <div class="portlet-body form">
			        	<form method="POST" id="searchXtNotifyReceiverForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
							<div class="form-group">
								<label>标题</label>
								<input type="text" class="form-control" name="xt_notify_title" placeholder="请输入标题">
							</div>
						</form>
			        </div>
			    </div>
				<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
			        <jEhcPermissionTag:jehcBtnTag modules="delXtNotifyReceiver">
						<button class="btn btn-default" onclick="delXtNotifyReceiver()">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
						</button>
					</jEhcPermissionTag:jehcBtnTag>
					<button class="btn btn-default" onclick="search('xtNotifyReceiverDatatables')">
						<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
					</button>
			    </div>
			    <table id="xtNotifyReceiverDatatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
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
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-notify/xt-notify-list.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-notify/xt-notify-receiver-list.js"></script> 
</html>
