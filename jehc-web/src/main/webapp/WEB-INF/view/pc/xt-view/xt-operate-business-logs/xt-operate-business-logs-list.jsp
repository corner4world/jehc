<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台业务操作日志表</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
            <div class="caption">
                	查询区域
            </div>
            <div class="actions">
                 <a class="btn btn-circle btn-icon-only btn-default" title="检索" href="javascript:search('datatables');">
                    <i class="fi-search"></i>
                 </a>
                 <a class="btn btn-circle btn-icon-only btn-default" title="重置" href="javascript:resetAll();;">
                     <i class="icon-trash"></i>
                 </a>
            </div>
        </div>
        <div class="portlet-body form">
        	<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<div class="form-group">
					<label>模块</label>
					<input type="text" class="form-control" name="xt_operate_b_logsModules" placeholder="请输入模块">
				</div>
				<div class="form-group">
					<label>执行方法</label>
					<input type="text" class="form-control" name="xt_operate_b_logsMethod" placeholder="请输入执行方法">
				</div>
				<div class="form-group">
					<label>方法参数</label>
					<input type="text" class="form-control" name="xt_operate_b_logsMethodPar" placeholder="请输入方法参数">
				</div>
			</form>
        </div>
    </div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
        <button class="btn btn-default" onclick="delXtOperateBusinessLogs()">
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
				<th>模块</th>
				<th>执行方法</th>
				<th>方法参数</th>
				<th>执行的结果</th>
				<th>操作时间</th>
				<th>操作人</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-operate-business-logs/xt-operate-business-logs-list.js"></script> 
</html>
