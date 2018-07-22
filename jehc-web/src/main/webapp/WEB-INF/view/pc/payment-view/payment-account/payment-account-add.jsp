<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>支付账号配置新增页面</title>
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
						创建支付账号配置
					</h3>
				</div>
			</div>
		</div>
		<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" method="POST" id="defaultForm">
			<div class="m-portlet__body">
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">支付合作商户id</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="partner" placeholder="请输入支付合作商户id">
					</div>
					<div class="col-lg-1">
						<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="支付合作id,商户id，支付平台的账号或id">
							<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
						</a>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">应用id</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="32"  name="appid" placeholder="请输入应用id">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">支付平台公钥</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1204"  name="public_key" placeholder="请输入支付平台公钥"></textarea>
					</div>
					<div class="col-lg-1">
						<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="支付平台公钥(签名校验使用)，sign_type只有单一key时public_key与private_key相等，比如sign_type=MD5(友店支付除外)的情况">
							<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
						</a>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">应用私钥</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="2048"  name="private_key" placeholder="请输入应用私钥"></textarea>
					</div>
					<div class="col-lg-1">
						<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="应用私钥(生成签名)">
							<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
						</a>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">异步回调地址</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1024"  name="notify_url" placeholder="请输入异步回调地址"></textarea>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">同步回调地址</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1024"  name="return_url" placeholder="请输入同步回调地址"></textarea>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">收款账号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="256"  name="seller" placeholder="请输入收款账号">
					</div>
					<div class="col-lg-1">
						<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="收款账号, 针对支付宝">
							<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
						</a>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">签名类型</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="16"  name="sign_type" placeholder="请输入签名类型">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">字符编码</label>
					<div class="col-lg-6">
						<select class="form-control" name="input_charset" placeholder="请选择">
							<option value="utf-8">utf-8</option>
							<option value="gbk">gbk</option>
						</select>
					</div>
					<div class="col-lg-1">
						<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="枚举值，字符编码 utf-8,gbk等等">
							<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
						</a>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">支付类型</label>
					<div class="col-lg-6">
						<select class="form-control" name="pay_type" placeholder="请选择">
							<option value="aliPay">aliPay</option>
							<option value="wxPay">wxPay</option>
							<option value="youdianPay">youdianPay</option>
							<option value="fuiou">fuiou</option>
							<option value="unionPay">unionPay</option>
							<option value="payoneer">payoneer</option>
							<option value="payPal">payPal</option>
						</select>
					</div>
					<div class="col-lg-1">
						<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="支付类型,aliPay：支付宝，wxPay：微信, youdianPay: 友店微信">
							<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
						</a>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">消息类型</label>
					<div class="col-lg-6">
						<select class="form-control" name="msg_type" placeholder="请选择">
							<option value="text">text</option>
							<option value="xml">xml</option>
							<option value="json">json</option>
						</select>
					</div>
					<div class="col-lg-1">
						<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="消息类型，text,xml,json">
							<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
						</a>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">请求证书地址</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="256"  name="keystore_path" placeholder="请输入请求证书地址"></textarea>
					</div>
					<div class="col-lg-1">
						<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="" data-placement="left" data-original-title="请求证书地址，请使用绝对路径">
							<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
						</a>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">证书对应的密码</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="256"  name="store_password" placeholder="请输入证书对应的密码"></textarea>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-1 control-label">是否为测试环境</label>
					<div class="col-lg-4 col-md-9 col-sm-12">
						<div class="m-radio-inline">
							<label class="m-radio">
								<input name="is_test" type="radio" checked="checked" value="0">
								是
								<span></span>
							</label>
							<label class="m-radio">
								<input name="is_test" type="radio" value="1">
								否
								<span></span>
							</label>
						</div>
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-success m-btn m-btn--custom m-btn--icon" href="javascript:addPaymentAccount()">保存</a>
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
<script type="text/javascript" src="../view/pc/payment-view/payment-account/payment-account-add.js"></script> 
</html>
