<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>支付退款新增页面</title>
</head>
<body>
	<div class="m-portlet">
		<div class="m-portlet__head">
			<div class="m-portlet__head-caption">
				<div class="m-portlet__head-title">
					<span class="m-portlet__head-icon m--hide">
						<i class="la la-gear"></i>
					</span>
					<h3 class="m-portlet__head-text">
						创建支付退款
					</h3>
				</div>
			</div>
		</div>
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group">
					<input class="form-control" type="hidden" maxlength="255"  name="fromto" placeholder="请输入创建来源（backstage：后台，fontpay前台退款）" value="backstage">
					<label class="col-lg-3 control-label">退款单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="refundNo" placeholder="请输入退款单号，每次进行退款的单号，此处唯一">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">支付平台订单交易号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="tradeNo" placeholder="请输入支付平台订单号,交易号">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">商户单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="outTradeNo" placeholder="请输入商户单号">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款金额</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10" value="0"  name="refundAmount" placeholder="请输入退款金额">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">订单总金额</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10" value="0"  name="totalAmount" placeholder="请输入订单总金额">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">交易日期</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="orderDate"  placeholder="请选择时间">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">货币</label>
					<div class="col-lg-6">
						<select class="form-control" name="curType"  data-bv-notempty data-bv-notempty-message="请选择支付币种" placeholder="请选择支付币种">
							<option value="">---请选择---</option>
							<option value="CNY">人民币</option>
							<option value="USD">美元</option>
							<option value="HKD">港币</option>
							<option value="MOP">澳门元</option>
							<option value="EUR">欧元</option>
							<option value="TWD">新台币</option>
							<option value="KRW">韩元</option>
							<option value="JPY">日元</option>
							<option value="SGD">新加坡元</option>
							<option value="AUD">澳大利亚元</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款说明</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1024"  name="description" placeholder="请输入退款说明"></textarea>
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-success m-btn m-btn--custom m-btn--icon" href="javascript:addPaymentRefund()">保存</a>
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:goback()">返回</a>
						</div>
						<div class="col m--align-right">
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:resetAll('defaultForm')"><span><i class="fa fa-repeat"></i><span>重置</span></span></a>
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/payment-view/payment-refund/payment-refund-add.js"></script> 
</html>
