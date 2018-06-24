<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台SEO配置编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑SEO配置</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_seo_id"  placeholder="请输入主键" value="${cmsSeo.cms_seo_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">域名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入域名"  name="domainname" placeholder="请输入域名" value="${cmsSeo.domainname }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">站点标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="title" placeholder="请输入站点标题" value="${cmsSeo.title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">关键字</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="keywords" placeholder="请输入关键字" value="${cmsSeo.keywords }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描述</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="content" placeholder="请输入描述" value="${cmsSeo.content }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">站点邮箱</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="email" placeholder="请输入站点邮箱" value="${cmsSeo.email }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">联系人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="contact" placeholder="请输入联系人" value="${cmsSeo.contact }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">联系电话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="phone" placeholder="请输入联系电话" value="${cmsSeo.phone }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备案ICP</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="128"  name="icp" placeholder="请输入备案ICP" value="${cmsSeo.icp }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备案地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="address" placeholder="请输入备案地址" value="${cmsSeo.address }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0" <c:if test="${cmsSeo.status eq 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsSeo.status eq 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateCmsSeo()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/cms-view/cms-seo/cms-seo-update.js"></script> 
</html>
