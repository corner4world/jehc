<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>岗位详情</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>岗位详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">序列号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_post_id"  placeholder="请输入序列号" value="${xtPost.xt_post_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">隶属部门</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" readonly="readonly" maxlength="32" name="xt_departinfo_id" value="${xtPost.xt_departinfo_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">上级岗位</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" maxlength="32"  name="xt_post_parentId" id="xt_post_parentId" placeholder="请输入上级岗位" value="${xtPost.xt_post_parentId }">
					<input class="form-control" type="text" readonly="readonly" name="xt_post_parentName" id="xt_post_parentName">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗位名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" readonly="readonly" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入岗位名称"  name="xt_post_name" placeholder="请输入岗位名称" value="${xtPost.xt_post_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗位描述</label>
				<div class="col-lg-6">
					<textarea class="form-control"  readonly="readonly" maxlength="200"  name="xt_post_desc" placeholder="请输入岗位描述">${xtPost.xt_post_desc }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗位最大人数</label>
				<div class="col-lg-6">
					<input class="form-control" readonly="readonly" maxlength="10" value="0" style="width:150px;" data-bv-numeric data-bv-numeric-message="岗位最大人数为数字类型"  name="xt_post_maxNum" placeholder="请输入岗位最大人数" value="${xtPost.xt_post_maxNum }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗位级别</label>
				<div class="col-lg-6">
					<input class="form-control" readonly="readonly" maxlength="10" value="0" style="width:150px;" data-bv-numeric data-bv-numeric-message="岗位级别为数字类型"  name="xt_post_grade" placeholder="请输入岗位级别" value="${xtPost.xt_post_grade }">
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
<script type="text/javascript" src="../view/pc/xt-view/xt-post/xt-post-detail.js"></script> 
</html>
