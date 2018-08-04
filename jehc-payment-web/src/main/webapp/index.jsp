<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>支付账号配置</title>
</head>
<body>
	<div class="m-content">
		<div class="m-portlet">
			<div class="m-portlet__head">
				<div class="m-portlet__head-caption">
					<div class="m-portlet__head-title">
						<h3 class="m-portlet__head-text">
							<span class="m-accordion__item-icon"><i class="flaticon-search"></i>查询区域</span>
						</h3>
					</div>
				</div>
			</div>
			<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" method="POST" id="searchForm">
				<div class="m-portlet__body">
					<div class="form-group m-form__group row">
						<label class="col-form-label">测试环境：</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="is_test" placeholder="请输入测试环境">
						</div>
						<label class="col-form-label">签名类型：</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="sign_type" placeholder="请输入签名类型">
						</div>
					</div>
				</div>
				<div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
					<div class="m-form__actions m-form__actions--solid">
						<div class="row">
							<div class="col m--align-left">
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:search('datatables')">
									<span><i class="fa fa-spin fa-refresh m-r-5"></i><span>刷新</span></span>
								</a>
							</div>
							<div class="col m--align-right">
								<a href="javascript:search('datatables')" class="btn btn-info m-btn m-btn--custom m-btn--icon">
									<span><i class="fa fa-search"></i><span>检索</span></span>
								</a>
								<a href="javascript:resetAll()" class="btn btn-secondary m-btn m-btn--custom m-btn--icon">
									<span><i class="fa fa-repeat"></i><span>重置</span></span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</form>
		</div>
		<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>收款账号</th>
					<th>支付类型</th>
					<th>环境</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="${syspath }/view/pc/payment/payment.js"></script> 
</html>
