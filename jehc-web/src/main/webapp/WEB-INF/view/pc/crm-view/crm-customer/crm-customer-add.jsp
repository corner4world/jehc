<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户基础资料新增页面</title>
<link rel="stylesheet" href="${syspath}/deng/source/plugins/other/bztree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.exedit.js"></script>
<style>
.help-block{
	    color: #e73d4a;
}
</style>

</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建客户</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<input class="form-control" type="hidden" name="crmCustomerAttach_removed_flag" id="crmCustomerAttach_removed_flag" >
			<input class="form-control" type="hidden" value="0" name="crmCustomerAttachFormNumber" id="crmCustomerAttachFormNumber" >
			<div class="page-header">
				<h4>基础资料</h4>
			</div>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">客户名称</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" maxlength="255"  name="name" data-bv-notempty data-bv-notempty-message="请输入客户名称" placeholder="请输入客户名称">
				</div>
				<div class="col-md-1">
					<label class="control-label">客户简称</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" maxlength="64"  name="shortName" placeholder="请输入客户简称">
				</div>
				<div class="col-md-3">
					<div class="form-group form-inline">
						<div class="col-lg-3">
							<label class="control-label">状态</label>
						</div>
						<div class="col-lg-6">
							<select class="form-control select_boot" disabled="disabled" type="text" maxlength="32" id="status"  name="status" placeholder="请选择状态">
			        			<option value="0">初稿</option>
			        			<option value="1">审核中</option>
			        			<option value="2">审核通过</option>
			        			<option value="3">审核未通过</option>
			        		</select>
		        		</div>
					</div>
		        </div>
			</div>
			<br>
			<div class="row">
				 <div class="col-md-1">
		        	<label class="control-label">所属行业</label>
		        </div>
		        <div class="col-md-2">
	        		<select class="form-control" type="text" maxlength="32" id="industryId"  name="industryId" placeholder="请选择所属行业">
	        		</select>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">公司规模</label>
		        </div>
		        <div class="col-md-2">
	        		<select class="form-control" type="text" maxlength="32" id="scaleId"  name="scaleId" placeholder="请选择公司规模">
	        		</select>
		        </div>
				<div class="col-md-1">
		        	<label class="control-label">年龄结构</label>
		        </div>
		        <div class="col-md-2">
	        		<select class="form-control" type="text" maxlength="32" id="ageScope"  name="ageScope" placeholder="请选择年龄结构">
	        		</select>
		        </div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">公司法人</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" maxlength="32"  name="corporation" placeholder="请输入公司法人">
				</div>
				<div class="col-md-1">
					<label class="control-label">公司电话</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" maxlength="255"  name="tel" placeholder="请输入公司电话">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">合同到期日期</label>
				</div>
				<div class="col-md-2">
					<input class="form_datetime form-control"  name="endTime" type="text" placeholder="请选择时间">
				</div>
			</div>
			<br>
			
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">客户等级</label>
				</div>
				<div class="col-md-2">
					<select class="form-control" name="level">
						<option value="">请选择</option>
						<option value="1">A</option>
						<option value="2">AA</option>
						<option value="3">AAA</option>
						<option value="4">AAAA</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">省市区县</label>
				</div>
				<div class="col-md-2">
					<select class="form-control" maxlength="32"  name="provinceId" id="xt_province_id_0" placeholder="请选择省份">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<select class="form-control"  maxlength="32"  name="cityId" id="xt_city_id_0" placeholder="请选择城市">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<select class="form-control" maxlength="32"  name="districtId" id="xt_district_id_0" placeholder="请选择区县">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" maxlength="255"  name="address" placeholder="请输入详细地址">
				</div>
			</div>
			<div class="page-header">
			</div>
			<div class="page-header">
				<h4>附件资料</h4>
			</div>
			<div class="form-group">
				<div class="col-lg-4">
					<a class="btn btn-mini btn-primary glyphicon glyphicon-plus" href="javascript:addCrmCustomerAttachItems()" role="button">新一行</a>
				</div>
			</div>
			<div class="form_crmCustomerAttach form-inline">
			</div>
			<div class="page-header">
			</div>
			<div class="page-header">
				<h4>发票信息</h4>
			</div>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">发票类型</label>
				</div>
				<div class="col-md-2">
					<select class="form-control">
						<option value="0">普通发票</option>
						<option value="1">增值税发票</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">发票名称</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" maxlength="32"  name="invoiceName" placeholder="请输入发票名称">
				</div>
				<div class="col-md-1">
					<label class="control-label">发票税号</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="text" maxlength="32"  name="invoiceTaxNumber" placeholder="请输入发票税号">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addCrmCustomer()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/crm-view/crm-customer/crm-customer-add.js"></script> 
</html>
