<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>支付记录编辑页面</title>
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
						编辑支付记录
					</h3>
				</div>
			</div>
		</div>
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">主键</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="id"  placeholder="请输入主键" value="${paymentRecord.id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">商品名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="subject" placeholder="请输入商品名称" value="${paymentRecord.subject }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">商户订单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="outTradeNo" placeholder="请输入商户订单号" value="${paymentRecord.outTradeNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">价格</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10"  name="price" placeholder="请输入价格" value="${paymentRecord.price }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">描述</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="65535"  name="body" placeholder="请输入描述">${paymentRecord.body }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">银行卡类型</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="bankType" placeholder="请输入银行卡类型" value="${paymentRecord.bankType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">设备信息</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="deviceInfo" placeholder="请输入设备信息" value="${paymentRecord.deviceInfo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">支付创建ip</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="spbillCreateIp" placeholder="请输入支付创建ip" value="${paymentRecord.spbillCreateIp }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">付款条码串</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="authCode" placeholder="请输入付款条码串" value="${paymentRecord.authCode }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">WAP支付链接</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="2408"  name="wapUrl" placeholder="请输入WAP支付链接" value="${paymentRecord.wapUrl }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">WAP支付网页名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="wapName" placeholder="请输入WAP支付网页名称" value="${paymentRecord.wapName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">微信会员唯一标识</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="11"  name="openid" placeholder="请输入微信会员唯一标识" value="${paymentRecord.openid }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">交易类型</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="transactionType" placeholder="请输入交易类型" value="${paymentRecord.transactionType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">支付币种</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="curType" placeholder="请输入支付币种" value="${paymentRecord.curType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">支付时间</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="ctime"  placeholder="请选择时间" value="${paymentRecord.ctime }">
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-success m-btn m-btn--custom m-btn--icon" href="javascript:updatePaymentRecord()">保存</a>
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
<script type="text/javascript" src="../view/pc/payment-view/payment-record/payment-record-update.js"></script> 
</html>
