<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础库存新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建基础库存</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_seller_product_id" placeholder="请输入商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;品">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存为数字类型"  name="b_stock_countable_sell" placeholder="请输入库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;存">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">可&nbsp;&nbsp;卖&nbsp;数</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="可&nbsp;&nbsp;卖&nbsp;数为数字类型"  name="b_stock_locks_number" placeholder="请输入可&nbsp;&nbsp;卖&nbsp;数">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addBStock()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/b-view/b-stock/b-stock-add.js"></script> 
</html>
