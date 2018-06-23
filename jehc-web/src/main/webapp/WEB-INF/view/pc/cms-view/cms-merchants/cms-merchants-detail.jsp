<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台招商加盟详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>招商加盟详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_merchants_id"  placeholder="请输入主键" value="${cmsMerchants.cms_merchants_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="title" placeholder="请输入标题" value="${cmsMerchants.title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">大图片</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="imgpath" placeholder="请输入大图片" value="${cmsMerchants.imgpath }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  name="content" placeholder="请输入内容">${cmsMerchants.content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0" <c:if test="${cmsMerchants.status = 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsMerchants.status = 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="ctime"  placeholder="请选择时间" value="${cmsMerchants.ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">最后修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="mtime"  placeholder="请选择时间" value="${cmsMerchants.mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人" value="${cmsMerchants.xt_userinfo_id }">
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
<script type="text/javascript" src="../view/pc/cms-view/cms-merchants/cms-merchants-detail.js"></script> 
</html>
