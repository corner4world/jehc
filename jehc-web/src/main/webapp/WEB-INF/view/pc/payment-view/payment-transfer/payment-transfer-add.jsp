<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>转账新增页面</title>
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
						创建转账
					</h3>
				</div>
			</div>
		</div>
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group">
					<input class="form-control" type="hidden" maxlength="255"  name="fromto" placeholder="请输入创建来源（backstage：后台，fontpay前台退款）" value="backstage">
					<label class="col-lg-3 control-label">转账订单单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="outNo" placeholder="请输入转账订单单号">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">收款方账户</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="payeeAccount" placeholder="请输入收款方账户">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">转账金额</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10" value="0"  name="amount" placeholder="请输入转账金额">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">付款人名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="payerName" placeholder="请输入付款人名称">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">收款人名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="payeeName" placeholder="请输入收款人名称">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">备注</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1024"  name="remark" placeholder="请输入备注"></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">收款开户行</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="bank" placeholder="请输入收款开户行">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">币种</label>
					<div class="col-lg-6">
						<select class="form-control" name="curType"  data-bv-notempty data-bv-notempty-message="请选择支付币种" placeholder="请选择支付币种">
							<option value="">---请选择---</option>
							<option value="CNY" <c:if test="${paymentRefund.curType eq 'CNY' }">selected</c:if> >人民币</option>
							<option value="USD" <c:if test="${paymentRefund.curType eq 'USD' }">selected</c:if> >美元</option>
							<option value="HKD" <c:if test="${paymentRefund.curType eq 'HKD' }">selected</c:if> >港币</option>
							<option value="MOP" <c:if test="${paymentRefund.curType eq 'MOP' }">selected</c:if> >澳门元</option>
							<option value="EUR" <c:if test="${paymentRefund.curType eq 'EUR' }">selected</c:if> >欧元</option>
							<option value="TWD" <c:if test="${paymentRefund.curType eq 'TWD' }">selected</c:if> >新台币</option>
							<option value="KRW" <c:if test="${paymentRefund.curType eq 'KRW' }">selected</c:if> >韩元</option>
							<option value="JPY" <c:if test="${paymentRefund.curType eq 'JPY' }">selected</c:if> >日元</option>
							<option value="SGD" <c:if test="${paymentRefund.curType eq 'SGD' }">selected</c:if> >新加坡元</option>
							<option value="AUD" <c:if test="${paymentRefund.curType eq 'AUD' }">selected</c:if> >澳大利亚元</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">转账日期</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="ctime"  placeholder="请选择时间">
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-success m-btn m-btn--custom m-btn--icon" href="javascript:addPaymentTransfer()">保存</a>
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
<script type="text/javascript" src="../view/pc/payment-view/payment-transfer/payment-transfer-add.js"></script> 
</html>
