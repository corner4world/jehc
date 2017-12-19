<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础商品默认图片详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>商品默认图片详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">默认图片编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_product_img_default_id"  placeholder="请输入默认图片编号" value="${bProductImgDefault.b_product_img_default_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="b_product_img_name" placeholder="请输入图片名称" value="${bProductImgDefault.b_product_img_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_product_img_type">
						<option value="0" <c:if test="${bProductImgDefault.b_product_img_type eq 0}">selected</c:if> >大图片</option>
						<option value="1" <c:if test="${bProductImgDefault.b_product_img_type eq 1}">selected</c:if> >小图片</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_product_img_status">
						<option value="0" <c:if test="${bProductImgDefault.b_product_img_status eq 0}">selected</c:if> >正常</option>
						<option value="1" <c:if test="${bProductImgDefault.b_product_img_status eq 1}">selected</c:if> >禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group" style="display: none;">
				<label class="col-lg-3 control-label">商品编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_product_id" id="b_product_id" placeholder="请输入商品编号" value="${bProductImgDefault.b_product_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片宽度</label>
				<div class="col-lg-6">
					<input class="form-control" style="width:120px" maxlength="10" data-bv-numeric data-bv-numeric-message="图片宽度为数字类型"  name="b_product_img_width" placeholder="请输入图片宽度" value="${bProductImgDefault.b_product_img_width }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片高度</label>
				<div class="col-lg-6">
					<input class="form-control" style="width:120px" maxlength="10" data-bv-numeric data-bv-numeric-message="图片高度为数字类型"  name="b_product_img_height" placeholder="请输入图片高度" value="${bProductImgDefault.b_product_img_height }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">使用说明</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="200"  name="b_product_img_remark" placeholder="请输入使用说明">${bProductImgDefault.b_product_img_remark }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id" value="${bProductImgDefault.xt_attachment_id }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bProductImgDefault.b_product_img_ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bProductImgDefault.b_product_img_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操&nbsp;作&nbsp;&nbsp;者</label>
				<div class="col-lg-6">
					<label class="control-label">${bProductImgDefault.xt_userinfo_realName }</label>
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
<script type="text/javascript" src="../view/pc/b-view/b-product-img-default/b-product-img-default-detail.js"></script> 
</html>
