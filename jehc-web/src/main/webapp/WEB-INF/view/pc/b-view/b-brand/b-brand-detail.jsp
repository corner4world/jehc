<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础-品牌详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>品牌详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">品牌编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_brand_id"  placeholder="请输入品牌编号" value="${bBrand.b_brand_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">品牌名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入品牌名称"  name="b_brand_name" placeholder="请输入品牌名称" value="${bBrand.b_brand_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" maxlength="2"  data-bv-notempty data-bv-notempty-message="请选择"  name="b_brand_status" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${bBrand.b_brand_status eq 0}">selected</c:if> >可用</option>
						<option value="1" <c:if test="${bBrand.b_brand_status eq 1}">selected</c:if> >禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
				<div class="col-lg-6">
					<select class="form-control" maxlength="2"  data-bv-notempty data-bv-notempty-message="请选择"  name="b_brand_type" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${bBrand.b_brand_type eq 0}">selected</c:if> >国内</option>
						<option value="1" <c:if test="${bBrand.b_brand_type eq 1}">selected</c:if> >国外</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="排序为数字类型"  name="b_brand_soft" placeholder="请输入排序" value="${bBrand.b_brand_soft }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<label class="control-label">
					<fmt:formatDate value="${bBrand.b_brand_ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<label class="control-label">
					<fmt:formatDate value="${bBrand.b_brand_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
					</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创&nbsp;建&nbsp;&nbsp;人</label>
				<div class="col-lg-6">
					<label class="control-label">
					${bBrand.xt_userinfo_realName }
					</label>
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
<script type="text/javascript" src="../view/pc/b-view/b-brand/b-brand-detail.js"></script> 
</html>
