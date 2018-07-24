<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>支付记录</title>
<link href="${syspath}/deng/source/css/bootlist.css" rel="stylesheet" type="text/css" />
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
						<label class="col-form-label">支付币种</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="curType" placeholder="请输入支付币种">
						</div>
						<label class="col-form-label">微信会员唯一标识</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="openid" placeholder="请输入微信会员唯一标识">
						</div>
						<label class="col-form-label">商户订单号</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="outTradeNo" placeholder="请输入商户订单号">
						</div>
						<label class="col-form-label">商品名称</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="subject" placeholder="请输入商品名称">
						</div>
					</div>
					<div class="form-group m-form__group row">
						<label class="col-form-label">支付时间</label>
						<div class="col-lg-2">
							<div class="input-group">
								<input type="text" class="form_datetime form-control" placeholder="起始时间" name="ctime_st" />
								<label class="col-form-label">至</label>
								<input type="text" class="form_datetime form-control" placeholder="结束时间" name="ctime_et" />
							</div>
						</div>
					</div>
				</div>
				<div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
					<div class="m-form__actions m-form__actions--solid">
						<div class="row">
							<div class="col m--align-left">
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:toPaymentRecordAdd()">
									<span><i class="fa fa-pencil fa-lg"></i><span>创建支付</span></span>
								</a>
								<!-- <a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:toPaymentRecordUpdate()">
									<span><i class="fa fa-magic fa-lg"></i><span>修改</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:delPaymentRecord()">
									<span><i class="fa fa-times"></i><span>删除</span></span>
								</a> -->
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
					<!-- <th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th> -->
					<th>序号</th>
					<th>商户订单号</th>
					<th>商品名称</th>
					<th>价格</th>
					<th>银行卡类型</th>
					<th>设备信息</th>
					<th>付款条码串</th>
					<th>微信会员唯一标识</th>
					<th>交易类型</th>
					<th>支付币种</th>
					<th>支付时间</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/payment-view/payment-record/payment-record-list.js"></script> 
</html>
