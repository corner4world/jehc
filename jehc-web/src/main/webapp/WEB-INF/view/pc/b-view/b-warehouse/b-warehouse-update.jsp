<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础仓库编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑仓库</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">仓库编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_warehouse_id"  placeholder="请输入仓库编号" value="${bWarehouse.b_warehouse_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户</label>
				<div class="col-lg-6">
					<div class="input-group" style="width:280px;" >
		        		<input class="form-control" type="hidden"  data-bv-notempty data-bv-notempty-message="请选择商户、卖家" value="${bWarehouse.b_seller_id }" placeholder="请选择商户、卖家" name="b_seller_id" id="b_seller_id"/> 
		                <input class="form-control" type="text"readonly="readonly" data-bv-notempty data-bv-notempty-message="请选择商户、卖家" placeholder="请选择商户、卖家" value="${bWarehouse.b_seller_name }" id="b_seller_name"/> 
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" onclick="initBSellerList()">
								请选择
							</button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">仓库名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="b_warehouse_name" placeholder="请输入仓库名称" value="${bWarehouse.b_warehouse_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" id="xt_provinceID_" value="${bWarehouse.xt_provinceID }">
					<select class="form-control" id="xt_province_id_0" name="xt_provinceID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" id="xt_cityID_" value="${bWarehouse.xt_cityID }">
					<select class="form-control" id="xt_city_id_0" name="xt_cityID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" id="xt_districtID_" value="${bWarehouse.xt_districtID }">
					<select class="form-control" id="xt_district_id_0" name="xt_districtID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">详细地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_warehouse_address" placeholder="请输入详细地址" value="${bWarehouse.b_warehouse_address }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">仓库类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_warehouse_type" >
						<option>请选择</option>
						<option value="0" <c:if test="${bWarehouse.b_warehouse_type eq 0}">selected</c:if> >赠品</option>
						<option value="1" <c:if test="${bWarehouse.b_warehouse_type eq 1}">selected</c:if> >疵品</option>
						<option value="2" <c:if test="${bWarehouse.b_warehouse_type eq 2}">selected</c:if> >正品</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateBWarehouse()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>


<!-- 商户模态框（Modal）开始 -->
<div class="modal fade" id="bSellerSelectModal" tabindex="-1" role="dialog" aria-labelledby="bSellerModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="bSellerModalLabel">
					商户选择器
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel panel-default">
					<fieldset>
						<legend>查询区域</legend>
						<form method="POST" id="searchBSellerForm" class="form-inline">
							<div class="form-group">
								<label>商户名称</label>
								<input type="text" class="form-control" name="b_seller_name" placeholder="请输入商户名称">
							</div>
							<div class="form-group">
								<label>电话</label>
								<input type="text" class="form-control" name="b_seller_tel" placeholder="请输入电话">
							</div>
							<div class="form-group">
								<label>是否官方商店</label>
								<select class="form-control" name="b_seller_official" placeholder="请选择">
									<option value="">请选择</option>
									<option value="0">是</option>
									<option value="1">否</option>
								</select>
							</div>
						</form>
						<div class="form-group" style="margin-left: 35px;margin-top: 25px;">
							<button class="btn btn-primary" onclick="search('bSellersDatatables')">
								<i class="glyphicon glyphicon-search"></i>&nbsp;检索
							</button>
							<button class="btn btn-default" onclick="resetAll('searchBSellerForm');">重置</button>
						</div>
					</fieldset>
				</div>
				<div class="panel-body">
					<table id="bSellersDatatables" class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallBSeller" /><span></span></label></th>
								<th>序号</th>
								<th>商户名称</th>
								<th>电话</th>
								<th>等级</th>
								<th>是否官方商店</th>
								<th>状态</th>
								<th>地址</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="doBSellerSelect();">保存</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 商户模态框（Modal）结束 -->
<script type="text/javascript" src="../view/pc/b-view/b-warehouse/b-warehouse-update.js"></script> 
</html>
