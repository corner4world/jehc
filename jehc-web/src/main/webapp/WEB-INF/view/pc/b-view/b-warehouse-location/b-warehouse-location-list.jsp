<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础仓库库位</title>
</head>
<body>
	<div class="panel panel-default">
		<fieldset>
			<legend>查询区域</legend>
			<form method="POST" id="searchForm" class="form-inline">
				<input type="hidden" value="${bWarehouse.b_warehouse_id }" name="b_warehouse_id" id="b_warehouse_id"/>
				<div class="form-group">
					<label>隶属仓库</label>
					<input class="form-control" type="text" maxlength="32" readonly="readonly" name="b_warehouse_name" id="b_warehouse_name" value="${bWarehouse.b_warehouse_name }" name="b_warehouse_name">
				</div>
				<div class="form-group">
					<label>库位名称</label>
					<input type="text" class="form-control" name="b_warehouse_location_name" placeholder="请输入库位名称">
				</div>
					&nbsp;
				<a class="btn btn-primary" title="检索" href="javascript:search('datatables');">
		           <i class="fi-search"></i>检索
		        </a>&nbsp;
		        <a class="btn btn-primary" title="重置" href="javascript:resetAll();;">
		           <i class="icon-trash"></i>重置
		        </a>
			</form>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="toBWarehouse()">
				<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>返回仓库列表
			</button>
			<button class="btn btn-default" onclick="toBWarehouseLocationAdd()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button class="btn btn-default" onclick="toBWarehouseLocationUpdate()">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button class="btn btn-default" onclick="delBWarehouseLocation()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
			<button class="btn btn-default" onclick="search('datatables')">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
			</button>
		</div>
		<table id="datatables" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>库位名称</th>
					<th>空间大小</th>
					<th>宽度</th>
					<th>高度</th>
					<th>长度</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/b-view/b-warehouse-location/b-warehouse-location-list.js"></script> 
</html>
