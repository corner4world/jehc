<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户基础资料详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>客户详情</h4>
		</div>
		<div class="page-header">
			<h4>基础资料</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">Id主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="customerId"  placeholder="请输入Id主键" value="${crmCustomer.customerId }">
				</div>
			</div>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">客户名称</label>
				</div>
				<div class="col-lg-2">
					<input class="form-control select_boot" type="text" maxlength="255"  name="name" placeholder="请输入客户名称" value="${crmCustomer.name }">
				</div>
				<div class="col-md-1">
					<label class="control-label">客户简称</label>
				</div>
				<div class="col-lg-2">
					<input class="form-control select_boot" type="text" maxlength="64"  name="shortName" placeholder="请输入客户简称" value="${crmCustomer.shortName }">
				</div>
				<div class="form-group form-inline">
					<div class="col-lg-6">
						<label class="control-label">状态</label>
					</div>
					<div class="col-lg-6">
						<select class="form-control select_boot" disabled="disabled" type="text" maxlength="32" id="status"  name="status" placeholder="请选择状态">
		        			<option value="0" <c:if test="${crmCustomer.status eq 0}">selected</c:if> >初稿</option>
		        			<option value="1" <c:if test="${crmCustomer.status eq 1}">selected</c:if> >审核中</option>
		        			<option value="2" <c:if test="${crmCustomer.status eq 2}">selected</c:if> >审核通过</option>
		        			<option value="3" <c:if test="${crmCustomer.status eq 3}">selected</c:if> >审核未通过</option>
		        		</select>
	        		</div>
				</div>
			</div>
			<div class="row">
				<div class="col-md-1">
		        	<label class="control-label">所属行业</label>
		        </div>
				<div class="col-lg-2">
					<input type="hidden" id="industryId_" value="${crmCustomer.industryId }">
	        		<select class="form-control select_boot" id="industryId"  name="industryId" placeholder="请输入所属行业，注：数据字典读取"></select>
				</div>	
				 <div class="col-md-1">
		        	<label class="control-label">公司规模</label>
		        </div>
				<div class="col-lg-2">
					<input type="hidden" id="scaleId_" value="${crmCustomer.scaleId }">
	        		<select class="form-control select_boot" id="scaleId"  name="scaleId" placeholder="请输入公司规模，注：数据字典读取"></select>
				</div>
				<div class="col-md-1">
		        	<label class="control-label">年龄结构</label>
		        </div>
				<div class="col-lg-2">
					<input type="hidden" id="ageScope_" value="${crmCustomer.ageScope }">
	        		<select class="form-control select_boot" id="ageScope"  name="ageScope" placeholder="请输入年龄结构，注：数据字典读取"></select>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">公司法人</label>
				</div>
				<div class="col-lg-2">
					<input class="form-control select_boot" type="text" maxlength="32"  name="corporation" placeholder="请输入公司法人" value="${crmCustomer.corporation }">
				</div>
				<div class="col-md-1">
					<label class="control-label">公司电话</label>
				</div>
				<div class="col-lg-2">
					<input class="form-control select_boot" type="text" maxlength="255"  name="tel" placeholder="请输入公司电话" value="${crmCustomer.tel }">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">合同到期日期</label>
				</div>
				<div class="col-lg-2">
					<input class="form_datetime form-control select_boot" readonly="readonly" type="text" name="endTime" value="<fmt:formatDate value='${crmCustomer.endTime}' pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">客户等级</label>
				</div>
				<div class="col-lg-2">
					<select class="form-control select_boot" name="levelnum" disabled="disabled">
						<option value="1" <c:if test="${crmCustomer.levelnum == 1}">selected</c:if>>A</option>
						<option value="2" <c:if test="${crmCustomer.levelnum == 2}">selected</c:if>>AA</option>
						<option value="3" <c:if test="${crmCustomer.levelnum == 3}">selected</c:if>>AAA</option>
						<option value="4" <c:if test="${crmCustomer.levelnum == 4}">selected</c:if>>AAAA</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">省市区县</label>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="hidden" id="xt_provinceID_" value="${crmCustomer.provinceId }">
					<select class="form-control select_boot" maxlength="32"  name="provinceId" id="xt_province_id_0" placeholder="请选择省份">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="hidden" id="xt_cityID_" value="${crmCustomer.cityId }">
					<select class="form-control select_boot"  maxlength="32"  name="cityId" id="xt_city_id_0" placeholder="请选择城市">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<input class="form-control" type="hidden" id="xt_districtID_" value="${crmCustomer.districtId }">
					<select class="form-control select_boot" maxlength="32"  name="districtId" id="xt_district_id_0" placeholder="请选择区县">
						<option value=''>请选择</option>
					</select>
				</div>
				<div class="col-md-2">
					<input class="form-control select_boot"  type="text" maxlength="255"  value="${crmCustomer.address }" name="address" placeholder="请输入详细地址">
				</div>
			</div>
			
			<div class="page-header">
				<h4>附件资料</h4>
			</div>
			<div class="form_crmCustomerAttach form-inline">
				<c:forEach var="crmCustomerAttach" items="${crmCustomer.crmCustomerAttach }" varStatus="crmCustomerAttachStatus">
				<div id="form_crmCustomerAttach_${crmCustomerAttachStatus.index}">
				<fieldset>
					<legend><h5>序号${crmCustomerAttachStatus.index+1}</h5></legend>
					<div class="form-group">
						<div class="col-lg-6">
							<input class="form-control" type="hidden" maxlength="32"  data-bv-notempty data-bv-notempty-message="请输入主键编号"  id="crmCustomerAttach_${crmCustomerAttachStatus.index}_customer_attach_id" name="crmCustomerAttach[${crmCustomerAttachStatus.index}].customer_attach_id" placeholder="请输入主键编号" value="${crmCustomerAttach.customer_attach_id }">
							<input class="form-control" type="hidden" id="crmCustomerAttach_${crmCustomerAttachStatus.index}_xt_attachement_id" name="crmCustomerAttach[${crmCustomerAttachStatus.index}].xt_attachement_id" value="${crmCustomerAttach.xt_attachement_id }">
							<img src = "../deng/images/default/add_d.png" class="img" id="crmCustomerAttach_${crmCustomerAttachStatus.index}_xt_attachement_id_pic">
						</div>
					</div>
				</fieldset>
				</div>
				</c:forEach>
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
					<select class="form-control select_boot">
						<option value="0"  <c:if test="${crmInvoice.invoiceType eq 0 }">selected</c:if>>普通发票</option>
						<option value="1" <c:if test="${crmInvoice.invoiceType eq 1 }">selected</c:if>>增值税发票</option>
					</select>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">发票名称</label>
				</div>
				<div class="col-md-2">
					<input class="form-control select_boot" type="text" maxlength="32"  name="invoiceName" placeholder="请输入发票名称" value="${crmInvoice.invoiceName }">
				</div>
				<div class="col-md-1">
					<label class="control-label">发票税号</label>
				</div>
				<div class="col-md-2">
					<input class="form-control select_boot" type="text" maxlength="32"  name="invoiceTaxNumber" placeholder="请输入发票税号" value="${crmInvoice.invoiceTaxNumber }">
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label">客户所属人</label>
				</div>
				<div class="col-md-2">
					<label class="form-control select_boot" >${crmCustomer.xt_userinfo_realName }</label>
				</div>
			</div>
			<br>
			<div class="row">
				<div class="col-md-1">
					<label class="control-label select_boot">创建日期</label>
				</div>
				<div class="col-md-2">
					<label class="form-control select_boot"><fmt:formatDate value="${crmCustomer.cdate }" pattern="yyyy-MM-dd HH:mm:ss"/> </label>
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
<script type="text/javascript">
	var crmCustomerObj = '${crmCustomerJSON}';
	try{
		crmCustomerObj = eval("("+crmCustomerObj+")");
	}catch(e){
	}
</script>
<script type="text/javascript" src="../view/pc/crm-view/crm-customer/crm-customer-detail.js"></script> 
</html>
