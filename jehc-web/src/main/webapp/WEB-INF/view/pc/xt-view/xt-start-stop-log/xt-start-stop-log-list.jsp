<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>服务器启动与关闭日志; InnoDB free: 9216 kB</title>
</head>
<body>
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
					<label>服务器停止时间</label>
					<div class="input-group">
						<input type="text" class="form_datetime form-control" placeholder="起始时间" name="xt_start_stop_log_stoptime_st" />
						<span class="input-group-addon">至</span>
						<input type="text" class="form_datetime form-control" placeholder="结束时间" name="xt_start_stop_log_stoptime_et" />
					</div>
				</div>
				<div class="form-group">
					<label>服务器启动时间</label>
					<div class="input-group">
						<input type="text" class="form_datetime form-control" placeholder="起始时间" name="xt_start_stop_log_starttime_st" />
						<span class="input-group-addon">至</span>
						<input type="text" class="form_datetime form-control" placeholder="结束时间" name="xt_start_stop_log_starttime_et" />
					</div>
				</div>
			</form>
        </div>
    </div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
        <button class="btn btn-default" onclick="delXtStartStopLog()">
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
				<th>服务器启动时间</th>
				<th>服务器停止时间</th>
				<th>是否出错</th>
				<th>加载内容</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-start-stop-log/xt-start-stop-log-list.js"></script> 
</html>
