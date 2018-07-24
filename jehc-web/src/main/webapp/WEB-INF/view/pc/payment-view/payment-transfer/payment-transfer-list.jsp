<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>转账</title>
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
						<label class="col-form-label">付款人名称</label>
						<div class="col-lg-2">
								<input type="text" class="form-control" name="payerName" placeholder="请输入付款人名称">
						</div>
						<label class="col-form-label">收款开户行</label>
						<div class="col-lg-2">
								<input type="text" class="form-control" name="bank" placeholder="请输入收款开户行">
						</div>
						<label class="col-form-label">收款方账户</label>
						<div class="col-lg-2">
								<input type="text" class="form-control" name="payeeAccount" placeholder="请输入收款方账户">
						</div>
						<label class="col-form-label">转账订单单号</label>
						<div class="col-lg-2">
								<input type="text" class="form-control" name="outNo" placeholder="请输入转账订单单号">
						</div>
					</div>
					<div class="form-group m-form__group row">
						<label class="col-form-label">最后操作时间</label>
						<div class="col-lg-2">
							<div class="input-group">
								<input type="text" class="form_datetime form-control" placeholder="起始时间" name="transferlasttime_st" />
								<label class="col-form-label">至</label>
								<input type="text" class="form_datetime form-control" placeholder="结束时间" name="transferlasttime_et" />
							</div>
						</div>
						<label class="col-form-label">转账日期</label>
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
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:toPaymentTransferAdd()">
									<span><i class="fa fa-pencil fa-lg"></i><span>新增</span></span>
								</a>
								<!-- <a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:toPaymentTransferUpdate()">
									<span><i class="fa fa-magic fa-lg"></i><span>修改</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:delPaymentTransfer()">
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
					<th>序号</th>
					<th>转账订单单号</th>
					<th>收款方账户</th>
					<th>转账金额</th>
					<th>付款人名称</th>
					<th>收款人名称</th>
					<th>收款开户行</th>
					<th>币种</th>
					<th>转账日期</th>
					<th>最后操作时间</th>
					<th>创建来源</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/payment-view/payment-transfer/payment-transfer-list.js"></script> 
</html>
