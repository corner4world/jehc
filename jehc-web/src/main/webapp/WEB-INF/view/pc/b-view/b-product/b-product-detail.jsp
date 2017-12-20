<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础_商品详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>商品详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">产品编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_product_id" value="${bProduct.b_product_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">产品名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_name" value="${bProduct.b_product_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32" name="b_category_id" value="${bProduct.b_category_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_brand_id" value="${bProduct.b_brand_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">条&nbsp;形&nbsp;&nbsp;码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_barcode" value="${bProduct.b_product_barcode }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品货号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_product_code" value="${bProduct.b_product_code }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">二&nbsp;维&nbsp;&nbsp;码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_qr_code" value="${bProduct.b_product_qr_code }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_product_status" style="width:150px;" data-bv-notempty data-bv-notempty-message="请选择状态">
						<option>请选择</option>
						<option value="0" <c:if test="${bProduct.b_product_status eq 0 }">selected</c:if> >可用</option>
						<option value="1" <c:if test="${bProduct.b_product_status eq 1 }">selected</c:if> >禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品型号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_model"  value="${bProduct.b_product_model }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">型号名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_model_name" value="${bProduct.b_product_model_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品颜色</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="30"  name="b_product_color" value="${bProduct.b_product_color }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品简介</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  name="b_product_features" >${bProduct.b_product_features }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">产&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_origin" value="${bProduct.b_product_origin }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">质量等级</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="12"  name="b_product_qualitylevel" value="${bProduct.b_product_qualitylevel }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品毛重</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="22"  name="b_product_gross_weight" value="${bProduct.b_product_gross_weight }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品净重</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="22"  name="b_product_net_weight" value="${bProduct.b_product_net_weight }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">长</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0" style="width:150px;" name="b_product_size_length" value="${bProduct.b_product_size_length }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">宽</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0" style="width:150px;" value="${bProduct.b_product_size_width }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">高</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0" style="width:150px;" value="${bProduct.b_product_size_height }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bProduct.b_product_ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bProduct.b_product_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创&nbsp;建&nbsp;&nbsp;人</label>
				<div class="col-lg-6">
					<label class="control-label">${bProduct.xt_userinfo_realName }</label>
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
<script type="text/javascript" src="../view/pc/b-view/b-product/b-product-detail.js"></script> 
</html>
