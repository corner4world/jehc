<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>公司信息</title>  
</head>  
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>公司信息</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">公司名称</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" type="hidden" maxlength="32" name="xt_company_id" value="${xt_Company.xt_company_id }">
					<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入公司名称"  value="${xt_Company.xt_company_name }" name="xt_company_name" placeholder="请输入公司名称">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">公司电话</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" type="text" maxlength="20"  name="xt_company_tel" placeholder="请输入公司电话" value="${xt_Company.xt_company_tel }">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">联&nbsp;系&nbsp;人</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" type="text" maxlength="20"  name="xt_company_userName" placeholder="请输入联系人" value="${xt_Company.xt_company_userName }">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">公司性质</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" type="text" maxlength="20"  name="xt_company_type" placeholder="请输入公司性质" value="${xt_Company.xt_company_type }">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">立成时间</label>
				</div>
				<div class="col-md-3">
					<input class="form_datetime form-control" readonly="readonly" style="width: 150px;" type="text" maxlength="20"  name="xt_company_upTime" placeholder="请输入立成时间" value="${xt_Company.xt_company_upTime }">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">总负责人</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" type="text" maxlength="20"  name="xt_company_ceo" placeholder="请输入总负责人" value="${xt_Company.xt_company_ceo }">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">省市区县</label>
				</div>
				<div class="col-md-3">
					<input class="form-control" type="hidden" id="xt_provinceID_" value="${xt_Company.xt_provinceID }">
					<select class="form-control" id="xt_province_id_0" name="xt_provinceID">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="hidden" id="xt_cityID_" value="${xt_Company.xt_cityID }">
					<select class="form-control" id="xt_city_id_0" name="xt_cityID">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="hidden" id="xt_districtID_" value="${xt_Company.xt_districtID }">
					<select class="form-control" id="xt_district_id_0" name="xt_districtID">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" maxlength="20"  name="xt_company_address" placeholder="请输入详细地址" value="${xt_Company.xt_company_address }">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">公司简介</label>
				</div>
				<div class="col-md-3">
					<textarea class="form-control" maxlength="500"  name="xt_company_remark" placeholder="请输入公司简介">${xt_Company.xt_company_remark }</textarea>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
				</div>
				<div class="col-md-3">
					<button type="button" class="btn green" onclick="addOrUpdateXtCompany()">保存</button>
				</div>
			</div>
		</form>
	</div>  
</body>  
<script type="text/javascript" src="../view/pc/xt-view/xt-company/xt-company-list.js"></script>
</html> 