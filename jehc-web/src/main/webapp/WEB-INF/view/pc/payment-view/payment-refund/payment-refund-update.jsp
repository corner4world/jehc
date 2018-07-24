<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>支付退款编辑页面</title>
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
						编辑支付退款
					</h3>
				</div>
			</div>
		</div>
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">主键</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="id"  placeholder="请输入主键" value="${paymentRefund.id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款单号，每次进行退款的单号，此处唯一</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="refundNo" placeholder="请输入退款单号，每次进行退款的单号，此处唯一" value="${paymentRefund.refundNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">支付平台订单号,交易号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="tradeNo" placeholder="请输入支付平台订单号,交易号" value="${paymentRefund.tradeNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">商户单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="outTradeNo" placeholder="请输入商户单号" value="${paymentRefund.outTradeNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款金额</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10"  name="refundAmount" placeholder="请输入退款金额" value="${paymentRefund.refundAmount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">订单总金额</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10"  name="totalAmount" placeholder="请输入订单总金额" value="${paymentRefund.totalAmount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">交易日期</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="orderDate"  placeholder="请选择时间" value="${paymentRefund.orderDate }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">货币</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="curType" placeholder="请输入货币" value="${paymentRefund.curType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款说明</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1024"  name="description" placeholder="请输入退款说明">${paymentRefund.description }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款结果（0成功1失败）</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="refundresult" placeholder="请输入退款结果（0成功1失败）" value="${paymentRefund.refundresult }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款结果描述</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1024"  name="refundresultmsg" placeholder="请输入退款结果描述">${paymentRefund.refundresultmsg }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">最后操作时间</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="refundlasttime"  placeholder="请选择时间" value="${paymentRefund.refundlasttime }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">是否删除0正常 1删除</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10"  data-bv-numeric data-bv-numeric-message="是否删除0正常 1删除为数字类型"  name="isdelete" placeholder="请输入是否删除0正常 1删除" value="${paymentRefund.isdelete }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">创建来源（backstage：后台，fontpay前台退款）</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="fromto" placeholder="请输入创建来源（backstage：后台，fontpay前台退款）" value="${paymentRefund.fromto }">
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-success m-btn m-btn--custom m-btn--icon" href="javascript:updatePaymentRefund()">保存</a>
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
<script type="text/javascript" src="../view/pc/payment-view/payment-refund/payment-refund-update.js"></script> 
</html>
