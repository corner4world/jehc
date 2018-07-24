<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>支付退款详情页面</title>
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
						支付退款详情
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
					<label class="col-lg-3 control-label">退款单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="refundNo" placeholder="请输入退款单号，每次进行退款的单号，此处唯一" value="${paymentRefund.refundNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">支付平台订单交易号</label>
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
						<label class="control-label"><fmt:formatDate value="${paymentRefund.orderDate }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">货币</label>
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
					<label class="col-lg-3 control-label">退款说明</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1024"  name="description" placeholder="请输入退款说明">${paymentRefund.description }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款结果</label>
					<div class="col-lg-6">
						<c:choose>
							<c:when test="${paymentRefund.refundresult eq 0}">成功</c:when>
							<c:otherwise>
								失败
							</c:otherwise>
						</c:choose>
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
						<label class="control-label"><fmt:formatDate value="${paymentRefund.refundlasttime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">创建来源</label>
					<div class="col-lg-6">
						<c:choose>
							<c:when test="${paymentRefund.fromto eq 'backstage' }">后台支付</c:when>
							<c:when test="${paymentRefund.fromto eq 'fontpay' }">前台支付</c:when>
							<c:otherwise>缺省</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:goback()">返回</a>
						</div>
						<div class="col m--align-right">
						</div>
					</div>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/payment-view/payment-refund/payment-refund-detail.js"></script> 
</html>
