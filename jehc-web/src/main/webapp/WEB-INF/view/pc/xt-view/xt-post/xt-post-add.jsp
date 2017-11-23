<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户岗位新增页面</title>
<link rel="stylesheet" href="${syspath}/deng/source/plugins/other/bztree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.exedit.js"></script>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建岗位</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">隶属部门</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" maxlength="32" readonly="readonly" data-bv-notempty data-bv-notempty-message="请选择隶属部门"  name="xt_departinfo_id" id="xt_departinfo_id" placeholder="请选择隶属部门">
					<input class="form-control" type="text" maxlength="32" readonly="readonly" onclick="departSelect();" data-bv-notempty data-bv-notempty-message="请选择隶属部门"  name="xt_departinfo_name" id="xt_departinfo_name" placeholder="请选择隶属部门">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">上级岗位</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" maxlength="32" readonly="readonly" value="0" name="xt_post_parentId" id="xt_post_parentId" placeholder="请选择上级岗位">
					<input class="form-control" type="text" maxlength="32" onclick="postSelect();" readonly="readonly" value="无" name="xt_post_parentName" id="xt_post_parentName" placeholder="请选择上级岗位">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗位名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入岗位名称"  name="xt_post_name" placeholder="请输入岗位名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗位描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="200"  name="xt_post_desc" placeholder="请输入岗位描述"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗位最大人数</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0" style="width:150px;" data-bv-numeric data-bv-numeric-message="岗位最大人数为数字类型"  name="xt_post_maxNum" placeholder="请输入岗位最大人数">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">岗位级别</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0" style="width:150px;" data-bv-numeric data-bv-numeric-message="岗位级别为数字类型"  name="xt_post_grade" placeholder="请输入岗位级别">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtPost()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
	
	
	<!-- 部门选择器模态框（Modal）开始 -->
	<div class="modal fade" id="departSelectModal" tabindex="-1" role="dialog" aria-labelledby="departSelectModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="departSelectModalLabel">
						部门选择器
					</h4>
				</div>
				<div class="modal-body">
					<ul id="tree" class="ztree"></ul>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doDepartSelect()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 部门选择器模态框（Modal）结束 -->
	
	<!-- 岗位选择器模态框（Modal）开始 -->
	<div class="modal fade" id="postSelectModal" tabindex="-1" role="dialog" aria-labelledby="postSelectModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="postSelectModalLabel">
						岗位选择器
					</h4>
				</div>
				<div class="modal-body">
					<ul id="posttree" class="ztree"></ul>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doPostSelect()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 岗位选择器模态框（Modal）结束 -->
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-post/xt-post-add.js"></script> 
</html>
