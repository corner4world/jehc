<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础仓库库位编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑仓库库位</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">库位编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_warehouse_location_id"  placeholder="请输入库位编号" value="${bWarehouseLocation.b_warehouse_location_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">库位名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入库位名称"  name="b_warehouse_location_name" placeholder="请输入库位名称" value="${bWarehouseLocation.b_warehouse_location_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">空间大小</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100" style="width: 120px;" name="b_warehouse_location_space" placeholder="请输入空间大小" value="${bWarehouseLocation.b_warehouse_location_space }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="22" style="width: 120px;"  name="b_warehouse_location_width" placeholder="请输入宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度" value="${bWarehouseLocation.b_warehouse_location_width }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="22" style="width: 120px;"  name="b_warehouse_location_height" placeholder="请输入高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度" value="${bWarehouseLocation.b_warehouse_location_height }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">长&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="22" style="width: 120px;"  name="b_warehouse_location_length" placeholder="请输入长&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度" value="${bWarehouseLocation.b_warehouse_location_length }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">隶属仓库</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" maxlength="32" id="b_warehouse_id"  name="b_warehouse_id" value="${bWarehouseLocation.b_warehouse_id }">
					<input class="form-control" type="text" maxlength="32"  name="b_warehouse_name" value="${bWarehouseLocation.b_warehouse_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateBWarehouseLocation()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/b-view/b-warehouse-location/b-warehouse-location-update.js"></script> 
</html>
