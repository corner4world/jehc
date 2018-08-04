<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>支付平台</title>
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
						普通web支付订单
					</h3>
				</div>
			</div>
		</div>
		<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" method="POST" id="defaultForm">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">支付账号id</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="id"  placeholder="请输入支付账号id" value="${payment.id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">商品名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="subject" placeholder="请输入商品名称">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">商户订单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="outTradeNo" placeholder="请输入商户订单号">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">价格</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10" value="0"  name="price" placeholder="请输入价格">
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-success m-btn m-btn--custom m-btn--icon" href="javascript:updatePaymentAccount()">普通web端支付</a>
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
	
	<!-- 2.公众号支付(请用微信访问)开始 -->
	<div class="m-portlet">
		<div class="m-portlet__head">
			<div class="m-portlet__head-caption">
				<div class="m-portlet__head-title">
					<span class="m-portlet__head-icon m--hide">
						<i class="la la-gear"></i>
					</span>
					<h3 class="m-portlet__head-text">
						公众号支付(微信访问)
					</h3>
				</div>
			</div>
		</div>
		<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" method="POST" id="defaultForm">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">支付账号id</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="id"  placeholder="请输入支付账号id" value="${payment.id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">商品名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="subject" placeholder="请输入商品名称">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">商户订单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="outTradeNo" placeholder="请输入商户订单号">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">价格</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10" value="0"  name="price" placeholder="请输入价格">
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-success m-btn m-btn--custom m-btn--icon" href="javascript:updatePaymentAccount()">普通web端支付</a>
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
	
	<!-- 
	
	<br> 
	APP提交(返回对应的json，具体实现，app端demo暂时未实现)
	<form action="getOrderInfo" target="_blank">
		账户id<input type="text" name="payId"> <br> 金额<input
			type="text" name="price"> <br> 交易类型<input type="text"
			name="transactionType" value="APP" readonly> <br> <input
			type="submit" value="提交">
	</form> -->

	<br> 普通web提交
	<form action="toPay.html" target="_blank">
		账户id<input type="text" name="payId"> <br> 金额<input
			type="text" name="price"> <br> 交易类型<input type="text"
			name="transactionType"> <br> <input type="submit"
			value="提交">
	</form>
	<br> 公众号支付(请用微信访问)
	<form id="jsapi">
		账户id<input type="text" name="payId"> <br> 金额<input
			type="text" name="price"> <br> openid<input type="text"
			name="openid"> <br>
	</form>
	<button id="js_submit">提交</button>


	<br> 主动收款(pos条码声波付等)
	<form action="microPay" target="_blank">
		账户id<input type="text" name="payId"> <br> 金额<input
			type="text" name="price"> <br> 交易类型<input type="text"
			name="transactionType"> <br> 条码授权信息<input type="text"
			name="authCode"> <br> <input type="submit" value="提交">
	</form>
	<br>

	<br> 二维码
	<form action="toQrPay.jpg" target="_blank">
		账户id<input type="text" name="payId"> <br> 金额<input
			type="text" name="price"> <br> 交易类型<input type="text"
			name="transactionType"> <br> <input type="submit"
			value="提交">
	</form>
	<br> 支付宝微信一码付二维码
	<form action="toWxAliQrPay.jpg" target="_blank">
		账户（微信）id<input type="text" name="wxPayId"> <br> 账户(支付宝)id<input
			type="text" name="aliPayId"> <br> 金额<input type="text"
			name="price"> <br> <br> <input type="submit"
			value="提交">
	</form>
	<br> 查询账单
	<form action="query" target="_blank">
		账户id<input type="text" name="payId"> <br> 支付平台订单号<input
			type="text" name="tradeNo"> <br> 商户单号<input type="text"
			name="outTradeNo"> <br> <input type="submit" value="提交">
	</form>
	<br /> 交易关闭接口
	<form action="close" target="_blank">
		账户id<input type="text" name="payId"> <br> 支付平台订单号<input
			type="text" name="tradeNo"> <br> 商户单号<input type="text"
			name="outTradeNo"> <br> <input type="submit" value="提交">
	</form>
	<br /> 申请退款接口
	<form action="refund" target="_blank">
		账户id<input type="text" name="payId"> <br> 支付平台订单号<input
			type="text" name="tradeNo"> <br> 商户单号<input type="text"
			name="outTradeNo"> <br> 退款金额<input type="text"
			name="refundAmount"> <br> 总金额(微信必填)<input type="text"
			name="totalAmount"> <br> <input type="submit" value="提交">
	</form>
	<br /> 查询退款
	<form action="refundquery" target="_blank">
		账户id<input type="text" name="payId"> <br> 支付平台订单号<input
			type="text" name="tradeNo"> <br> 商户单号<input type="text"
			name="outTradeNo"> <br> <input type="submit" value="提交">
	</form>

	<br> 下载对账单(日期，不支持月份)
	<form action="downloadbill" target="_blank">
		账户id<input type="text" name="payId"> <br>
		账单时间：具体请查看对应支付平台<input type="text" name="billDate"> <br>
		账单类型 <input type="text" name="billType"> <br> <input
			type="submit" value="提交">
	</form>

	<br> 通用查询接口，根据 交易类型（TransactionType） 进行实现,此接口不包括退款
	<form action="secondaryInterface" target="_blank">
		账户id<input type="text" name="payId"> <br> 支付平台订单号或者账单日期<input
			type="text" name="tradeNoOrBillDate"> <br> 商户单号或者 账单类型<input
			type="text" name="outTradeNoBillType"> <br> 交易类型<input
			type="text" name="transactionType"> <br> <input
			type="submit" value="提交">
	</form>
	<script src="jquery-3.1.1.min.js"></script>
	<script>
		$(function($) {
			$("#submit").click(function() {
				$.ajax({
					url : "add",
					type : "post",
					data : $("#form").serialize(),
					dataType : 'json',
					success : function(data) {
						if (data.code == 0) {
							alert("保存成功");
							return;
						}
						alert("保存失败");
					},
					error : function(edata) {
						alert("服务器异常")
					}
				})
			});
			$("#js_submit")
					.click(
							function() {
								$
										.ajax({
											url : "jsapi",
											type : "post",
											async : true,
											data : $("#jsapi").serialize(),
											dataType : 'json',
											success : function(data) {
												if (data.code == 0) {
													if (typeof WeixinJSBridge == "undefined") {
														if (document.addEventListener) {
															document
																	.addEventListener(
																			'WeixinJSBridgeReady',
																			onBridgeReady(data),
																			false);
														} else if (document.attachEvent) {
															document
																	.attachEvent(
																			'WeixinJSBridgeReady',
																			onBridgeReady(data));
															document
																	.attachEvent(
																			'onWeixinJSBridgeReady',
																			onBridgeReady(data));
														}
													} else {
														onBridgeReady(data);
													}
													return;
												}
												alert("保存失败");
											},
											error : function(edata) {
												alert("服务器异常")
											}
										})
							})
		});
		function onBridgeReady(data) {
			WeixinJSBridge.invoke('getBrandWCPayRequest', {
				"appId" : data.appid, //公众号名称，由商户传入
				"timeStamp" : data.timestamp, //时间戳，自1970年以来的秒数
				"nonceStr" : data.noncestr, //随机串
				"package" : data.package,
				"signType" : data.signType, //微信签名方式：
				"paySign" : data.sign
			//微信签名
			}, function(res) {
				// 使用以断前端返回,微信团队郑重提示：res.err_msg将在用户支付成功后返回    ok，但并不保证它绝对可靠。
				if (res.err_msg == "get_brand_wcpay_request:ok") {
					alert("支付成功")

				}
			});
		}
	</script>
	<script type="text/javascript" src="jweixin-1.0.0.js"></script>
</body>
</html>
