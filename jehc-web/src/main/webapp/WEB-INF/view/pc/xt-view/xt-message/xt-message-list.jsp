<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>短消息</title>
</head>
<body>
	<div class="portlet-title tabbable-line">
		<ul id="myTab" class="nav nav-tabs">
			<li class="active">
				<a href="#tab0" data-toggle="tab">
					<i class="glyphicon glyphicon-pencil"></i>我发送的消息
				</a>
			</li>
			<li>
				<a href="#tab1" data-toggle="tab">
					<i class="glyphicon glyphicon-indent-left"></i>我收到的消息
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
							<button class="btn btn-default" onclick="search('datatables');">
								<i class="glyphicon glyphicon-search"></i>&nbsp;检索
							</button>
							<button class="btn btn-default" onclick="resetAll();">
								<i class="glyphicon glyphicon-trash"></i>&nbsp;重置
							</button>
						</div>
					</div>
					<div class="portlet-body form">
						<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
							<input type="hidden" value="0" name="type">
							<div class="form-group">
								<label>发送时间</label>
								<div class="input-group">
									<input type="text" class="form_datetime form-control" placeholder="起始时间" name="ctime_st" />
									<span class="input-group-addon">至</span>
									<input type="text" class="form_datetime form-control" placeholder="结束时间" name="ctime_et" />
								</div>
							</div>
							<div class="form-group">
								<label>取读时间</label>
								<div class="input-group">
									<input type="text" class="form_datetime form-control" placeholder="起始时间" name="readtime_st" />
									<span class="input-group-addon">至</span>
									<input type="text" class="form_datetime form-control" placeholder="结束时间" name="readtime_et" />
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
					<button class="btn btn-default" onclick="toXtMessageAdd()">
						<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
					</button>
					<button class="btn btn-default" onclick="delXtMessage(1)">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					</button>
					<button class="btn btn-default" onclick="search('datatables')">
						<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
					</button>
				</div>
				<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
					<thead>
						<tr>
							<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
							<th>序号</th>
							<th>发送者</th>
							<th>接收者</th>
							<th>是否已读</th>
							<th>发送时间</th>
							<th>取读时间</th>
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
							<button class="btn btn-default" onclick="search('datatablesReceiver');">
								<i class="glyphicon glyphicon-search"></i>&nbsp;检索
							</button>
							<button class="btn btn-default" onclick="resetAll('searchReceiverForm');">
								<i class="glyphicon glyphicon-trash"></i>&nbsp;重置
							</button>
						</div>
					</div>
					<div class="portlet-body form">
						<form method="POST" id="searchReceiverForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
							<div class="form-group">
								<label>发送时间</label>
								<div class="input-group">
									<input type="hidden" value="1" name="type">
									<input type="text" class="form_datetime form-control" placeholder="起始时间" name="ctime_st" />
									<span class="input-group-addon">至</span>
									<input type="text" class="form_datetime form-control" placeholder="结束时间" name="ctime_et" />
								</div>
							</div>
							<div class="form-group">
								<label>取读时间</label>
								<div class="input-group">
									<input type="text" class="form_datetime form-control" placeholder="起始时间" name="readtime_st" />
									<span class="input-group-addon">至</span>
									<input type="text" class="form_datetime form-control" placeholder="结束时间" name="readtime_et" />
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
					<button class="btn btn-default" onclick="delXtMessage(2)">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
					</button>
					<button class="btn btn-default" onclick="search('datatablesReceiver')">
						<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
					</button>
				</div>
				<table id="datatablesReceiver" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
					<thead>
						<tr>
							<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallReceiver" /><span></span></label></th>
							<th>序号</th>
							<th>发送者</th>
							<th>接收者</th>
							<th>是否已读</th>
							<th>发送时间</th>
							<th>取读时间</th>
							<th>操作</th>
						</tr>
					</thead>
				</table>
			</div>
		</div>
	</div>	
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-message/xt-message-list.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-message/xt-message-receiver-list.js"></script>
</html>
