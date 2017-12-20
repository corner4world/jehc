<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%@ include file="/deng/include/inplugboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础_商品新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建商品</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">产品名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入产品名称"  name="b_product_name" placeholder="请输入产品名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类</label>
				<div class="col-lg-6">
					<div class="input-group">
						<input class="form-control" type="hidden" maxlength="32"  data-bv-notempty data-bv-notempty-message="请选择"  name="b_category_id" id="b_category_id" placeholder="请选择">
						<input class="form-control" type="text" maxlength="32"  data-bv-notempty data-bv-notempty-message="请选择"  name="b_category_name" id="b_category_name" placeholder="请选择">
						<span class="input-group-btn">
							<button class="btn btn-default" type="button" onclick="bCategorySelect()">
								选择品类
							</button>
						</span>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">品&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;牌</label>
				<div class="col-lg-6">
					<select class="form-control" data-bv-notempty data-bv-notempty-message="请选择"  name="b_brand_id"  id="b_brand_id" placeholder="请选择">
						
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">条&nbsp;形&nbsp;&nbsp;码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_barcode" placeholder="请输入条形码">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品货号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_product_code" placeholder="请输入商品货号">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">二&nbsp;维&nbsp;&nbsp;码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_qr_code" placeholder="请输入二维码">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品型号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_model" placeholder="请输入商品型号">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">型号名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_model_name" placeholder="请输入商品型号">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品颜色</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="30"  name="b_product_color" placeholder="请输入商品颜色">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品简介</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  name="b_product_features" placeholder="请输入商品功能简介"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">产&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;地</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_product_origin" placeholder="请输入产地">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">质量等级</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="12"  name="b_product_qualitylevel" placeholder="请输入质量等级">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品毛重</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="22"  name="b_product_gross_weight" placeholder="请输入商品毛重">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">商品净重</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="22"  name="b_product_net_weight" placeholder="请输入商品净重">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">长</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0" style="width:150px;" data-bv-numeric data-bv-numeric-message="请输入尺寸长为数字类型"  name="b_product_size_length" placeholder="请输入商品尺寸长">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">宽</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0" style="width:150px;"  data-bv-numeric data-bv-numeric-message="请输入尺寸宽为数字类型"  name="b_product_size_width" placeholder="请输入商品尺寸宽">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">高</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  style="width:150px;"  data-bv-numeric data-bv-numeric-message="请输入尺寸高为数字类型"  name="b_product_size_height" placeholder="请输入商品尺寸高">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addBProduct()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
	
	<!-- 品类选择器模态框（Modal）开始 -->
	<div class="modal fade" id="bcategorySelectModal" tabindex="-1" role="dialog" aria-labelledby="bcategorySelectModalLabel" aria-hidden="true">
		<div class="modal-dialog" id="bcategorySelectDialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="bcategorySelectModalLabel">
						品类选择器
					</h4>
				</div>
				<div class="modal-body" id="bcategoryBody" style="overflow:auto;">
					<ul id="menu" class="ztree"></ul>
					<input class="form-control" type="hidden" name="flag" id="flag"/> 
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doBcategorySelect()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 品类选择器模态框（Modal）结束 -->
</body>
<script type="text/javascript" src="../view/pc/b-view/b-product/b-product-add.js"></script> 
</html>
