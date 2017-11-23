<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>部门信息表(departInfo)新增页面</title>
<link rel="stylesheet" href="${syspath}/deng/source/plugins/other/bztree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.exedit.js"></script>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建部门信息</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">上级部门</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" maxlength="32" value="0" name="xt_departinfo_parentId" id="xt_departinfo_parentId" placeholder="请输入部门父ID">
					<input class="form-control" type="text" maxlength="32" value="无" onclick="departSelect();" id="xt_departinfo_parentName" readonly="readonly">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">部门名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入部门名称"  name="xt_departinfo_name" placeholder="请输入部门名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">联系电话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="12"  name="xt_departinfo_connectTelNo" placeholder="请输入联系电话">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">移动电话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="20"  name="xt_departinfo_connectMobileTelNo" placeholder="请输入移动电话">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  name="xt_departinfo_faxes" placeholder="请输入传真">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="200"  name="xt_departinfo_desc" placeholder="请输入描述"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">立成时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" readonly="readonly" style="width: 150px;" type="text" maxlength="20"  name="xt_departinfo_time" placeholder="请输入立成时间">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">部门性质</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="xt_departinfo_type" placeholder="请输入部门性质">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtDepartinfo()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
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
<script type="text/javascript" src="../view/pc/xt-view/xt-departinfo/xt-departinfo-add.js"></script> 
</html>
