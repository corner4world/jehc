<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>转账编辑页面</title>
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
						编辑转账
					</h3>
				</div>
			</div>
		</div>
		<form class="m-form" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-3 control-label">主键</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="id"  placeholder="请输入主键" value="${paymentTransfer.id }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">转账订单单号</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="outNo" placeholder="请输入转账订单单号" value="${paymentTransfer.outNo }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">收款方账户</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="64"  name="payeeAccount" placeholder="请输入收款方账户" value="${paymentTransfer.payeeAccount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">转账金额</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10"  name="amount" placeholder="请输入转账金额" value="${paymentTransfer.amount }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">付款人名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="payerName" placeholder="请输入付款人名称" value="${paymentTransfer.payerName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">收款人名称</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="payeeName" placeholder="请输入收款人名称" value="${paymentTransfer.payeeName }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">备注</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="1024"  name="remark" placeholder="请输入备注">${paymentTransfer.remark }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">收款开户行</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="bank" placeholder="请输入收款开户行" value="${paymentTransfer.bank }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">币种</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="curType" placeholder="请输入币种" value="${paymentTransfer.curType }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">转账日期</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="ctime"  placeholder="请选择时间" value="${paymentTransfer.ctime }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">最后操作时间</label>
					<div class="col-lg-6">
						<input class="form_datetime form-control" name="transferlasttime"  placeholder="请选择时间" value="${paymentTransfer.transferlasttime }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款结果</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="transferresult" placeholder="请输入退款结果" value="${paymentTransfer.transferresult }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">退款结果描述</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="255"  name="transferresultmsg" placeholder="请输入退款结果描述">${paymentTransfer.transferresultmsg }</textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">创建来源</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  name="fromto" placeholder="请输入创建来源" value="${paymentTransfer.fromto }">
					</div>
				</div>
				<div class="form-group">
					<label class="col-lg-3 control-label">是否删除0正常 1删除</label>
					<div class="col-lg-6">
						<input class="form-control" maxlength="10"  data-bv-numeric data-bv-numeric-message="是否删除0正常 1删除为数字类型"  name="isdelete" placeholder="请输入是否删除0正常 1删除" value="${paymentTransfer.isdelete }">
					</div>
				</div>
			</div>
			<div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
							<a class="btn btn-success m-btn m-btn--custom m-btn--icon" href="javascript:updatePaymentTransfer()">保存</a>
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
<script type="text/javascript" src="../view/pc/payment-view/payment-transfer/payment-transfer-update.js"></script> 
</html>
