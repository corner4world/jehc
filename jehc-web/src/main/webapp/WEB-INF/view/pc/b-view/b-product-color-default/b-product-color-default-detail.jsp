<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础商品默认颜色详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>商品默认颜色详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_product_color_default_id"  placeholder="请输入编号" value="${bProductColorDefault.b_product_color_default_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="b_product_color_default_name" placeholder="请输入名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称" value="${bProductColorDefault.b_product_color_default_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" style="width:120px" data-bv-numeric data-bv-numeric-message="宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度为数字类型"  name="b_product_color_default_width" placeholder="请输入宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度" value="${bProductColorDefault.b_product_color_default_width }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" style="width:120px"  data-bv-numeric data-bv-numeric-message="高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度为数字类型"  name="b_product_color_default_height" placeholder="请输入高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度" value="${bProductColorDefault.b_product_color_default_height }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">排&nbsp;&nbsp;序&nbsp;&nbsp;号</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" style="width:120px"  data-bv-numeric data-bv-numeric-message="排&nbsp;&nbsp;序&nbsp;&nbsp;号为数字类型"  name="b_product_color_default_sort" placeholder="请输入排&nbsp;&nbsp;序&nbsp;&nbsp;号" value="${bProductColorDefault.b_product_color_default_sort }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_product_color_default_status">
						<option value="0" <c:if test="${bProductColorDefault.b_product_color_default_status eq 0}">selected</c:if> >正常</option>
						<option value="1" <c:if test="${bProductColorDefault.b_product_color_default_status eq 1}">selected</c:if> >禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id" value="${bProductColorDefault.xt_attachment_id }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id_pic">
				</div>
			</div>
			<div class="form-group" style="display: none;">
				<label class="col-lg-3 control-label">商品编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_product_id" id="b_product_id" placeholder="请输入商品编号" value="${bProductColorDefault.b_product_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="200"  name="b_product_color_default_remark" placeholder="请输入备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注">${bProductColorDefault.b_product_color_default_remark }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bProductColorDefault.b_product_color_default_ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bProductColorDefault.b_product_color_default_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">操&nbsp;作&nbsp;&nbsp;者</label>
				<div class="col-lg-6">
					<label class="control-label">${bProductColorDefault.xt_userinfo_realName }</label>
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
<script type="text/javascript" src="../view/pc/b-view/b-product-color-default/b-product-color-default-detail.js"></script> 
</html>
