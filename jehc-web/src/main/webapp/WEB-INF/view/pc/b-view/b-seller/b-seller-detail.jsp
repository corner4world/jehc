<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础卖家详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>商户详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">基础卖家编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_seller_id"  placeholder="请输入基础卖家编号" value="${bSeller.b_seller_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商户名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="b_seller_name" placeholder="请输入卖家名称" value="${bSeller.b_seller_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_seller_status" placeholder="请选择">
						<option>请选择</option>
						<option value="0" <c:if test="${bSeller.b_seller_status eq 0}">selected</c:if> >正常</option>
						<option value="1" <c:if test="${bSeller.b_seller_status eq 1}">selected</c:if> >禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="b_seller_tel" placeholder="请输入卖家电话" value="${bSeller.b_seller_tel }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级为数字类型"  name="b_seller_level" placeholder="请输入等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级" value="${bSeller.b_seller_level }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">登陆账户</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_seller_login_id" placeholder="请输入登陆账户" value="${bSeller.b_seller_login_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">银行名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_seller_bank" placeholder="请输入银行名称" value="${bSeller.b_seller_bank }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">银行卡号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="25"  name="b_seller_bank_num" placeholder="请输入银行卡号" value="${bSeller.b_seller_bank_num }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否官方商店</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_seller_official" placeholder="请选择">
						<option>请选择</option>
						<option value="0" <c:if test="${bSeller.b_seller_official eq 0}">selected</c:if> >是</option>
						<option value="1" <c:if test="${bSeller.b_seller_official eq 1}">selected</c:if> >否</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="200"  name="b_seller_address" placeholder="请输入地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址">${bSeller.b_seller_address }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bSeller.b_seller_ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<label class="control-label">
					<fmt:formatDate value="${bSeller.b_seller_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操&nbsp;作&nbsp;&nbsp;者</label>
				<div class="col-lg-6">
					<label class="control-label">${bSeller.xt_userinfo_realName }</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/b-view/b-seller/b-seller-detail.js"></script> 
</html>
