<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础商品图片详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>基础商品图片详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">图片编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_product_img_id"  placeholder="请输入图片编号" value="${bProductImg.b_product_img_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="b_product_img_name" placeholder="请输入图片名称" value="${bProductImg.b_product_img_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_product_img_type">
						<option value="0" <c:if test="${bProductImg.b_product_img_type eq 0}">selected</c:if> >大图片</option>
						<option value="1" <c:if test="${bProductImg.b_product_img_type eq 1}">selected</c:if> >小图片</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_product_img_status">
						<option value="0" <c:if test="${bProductImg.b_product_img_status eq 0}">selected</c:if> >正常</option>
						<option value="1" <c:if test="${bProductImg.b_product_img_status eq 1}">selected</c:if> >禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group" style="display: none;">
				<label class="col-lg-3 control-label">商品编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_product_id"  id="b_product_id" value="${bProductImg.b_product_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;户</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_seller_id"  value="${bProductImg.b_seller_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id" value="${bProductImg.xt_attachment_id }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" style="width: 150px;"  data-bv-numeric data-bv-numeric-message="宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度为数字类型"  name="b_product_img_width" placeholder="请输入宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度" value="${bProductImg.b_product_img_width }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" style="width: 150px;" data-bv-numeric data-bv-numeric-message="高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度为数字类型"  name="b_product_img_height" placeholder="请输入高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度" value="${bProductImg.b_product_img_height }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">使用说明</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="200"  name="b_product_img_remark" placeholder="请输入使用说明">${bProductImg.b_product_img_remark }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bProductImg.b_product_img_ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bProductImg.b_product_img_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操&nbsp;&nbsp;作&nbsp;&nbsp;者</label>
				<div class="col-lg-6">
					<label class="control-label">${bProductImg.xt_userinfo_realName }</label>
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
<script type="text/javascript" src="../view/pc/b-view/b-product-img/b-product-img-detail.js"></script> 
</html>
