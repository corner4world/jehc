<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户跟进日志</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
			<div class="caption">
				查询区域
			</div>
		</div>
		<div class="portlet-body form">
			<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<input type="hidden" class="form-control" id="customerId" name="customerId" value="${crmFollowup.customerId }">
				<div class="form-group">
					<label>填写日志时间</label>
					<div class="input-group">
						<input type="text" class="form_datetime form-control" placeholder="起始时间" name="ctime_st" />
						<span class="input-group-addon">至</span>
						<input type="text" class="form_datetime form-control" placeholder="结束时间" name="ctime_et" />
					</div>
				</div>
					&nbsp;
				<a class="btn btn-primary" title="检索" href="javascript:search('datatables');">
		           <i class="fi-search"></i>检索
		        </a>&nbsp;
		        <a class="btn btn-primary" title="重置" href="javascript:resetAll();;">
		           <i class="icon-trash"></i>重置
		        </a>
			</form>
		</div>
	</div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
		<button class="btn btn-default" onclick="toCrmFollowupAdd()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button class="btn btn-default" onclick="search('datatables')">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
		<button type="button" class="btn default" onclick="goback()">返回</button>
	</div>
	<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>跟进日期</th>
				<th>跟进人</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/crm-view/crm-followup/crm-followup-list.js"></script> 
</html>
