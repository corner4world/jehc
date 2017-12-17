<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础仓库详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>仓库详情</h4>
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
							<button class="btn btn-default" type="button" onclick="bSellerSelect()">
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
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<label class="control-label">
					<fmt:formatDate value="${bWarehouse.b_warehouse_ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<label class="control-label">
					<fmt:formatDate value="${bWarehouse.b_warehouse_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操&nbsp;作&nbsp;&nbsp;者</label>
				<div class="col-lg-6">
					<label class="control-label">${bWarehouse.xt_userinfo_realName }</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/b-view/b-warehouse/b-warehouse-detail.js"></script> 
</html>
