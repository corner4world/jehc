<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>商品(产品)单位</title>
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
				<div class="form-group">
					<label>单位名称</label>
					<input type="text" class="form-control" name="xt_unitName" placeholder="请输入单位名称">
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
        <button class="btn btn-default" onclick="toXtUnitAdd()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button class="btn btn-default" onclick="toXtUnitUpdate()">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
		</button>
		<button class="btn btn-default" onclick="delXtUnit()">
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
				<th>单位名称</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-unit/xt-unit-list.js"></script> 
</html>
