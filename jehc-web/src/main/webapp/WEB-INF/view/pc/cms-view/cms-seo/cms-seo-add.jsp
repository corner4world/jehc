<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台SEO配置新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建SEO配置</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">域名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入域名"  name="domainname" placeholder="请输入域名">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">站点标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="title" placeholder="请输入站点标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">关键字</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="keywords" placeholder="请输入关键字">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描述</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="content" placeholder="请输入描述">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">站点邮箱</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="email" placeholder="请输入站点邮箱">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">联系人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="contact" placeholder="请输入联系人">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">联系电话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="phone" placeholder="请输入联系电话">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备案ICP</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="128"  name="icp" placeholder="请输入备案ICP">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备案地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="address" placeholder="请输入备案地址">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0">正常</option>
						<option value="1">关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addCmsSeo()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/cms-view/cms-seo/cms-seo-add.js"></script> 
</html>
