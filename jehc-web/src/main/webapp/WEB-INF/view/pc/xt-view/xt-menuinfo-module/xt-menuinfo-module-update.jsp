<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>菜单资源模块编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑资源模块</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_menuinfo_module_id"  placeholder="请输入主键" value="${xtMenuinfoModule.xt_menuinfo_module_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">模块名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="title" placeholder="请输入模块名称" value="${xtMenuinfoModule.title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">唯一值</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入唯一值"  name="keyid" placeholder="请输入唯一值" value="${xtMenuinfoModule.keyid }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="255"  name="remark" placeholder="请输入描述">${xtMenuinfoModule.remark }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status">
						<option value="0" <c:if test="${xtMenuinfoModule.status eq 0 }">selected</c:if> >激活</option>
						<option value="1" <c:if test="${xtMenuinfoModule.status eq 1 }">selected</c:if>>关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateXtMenuinfoModule()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-menuinfo-module/xt-menuinfo-module-update.js"></script> 
</html>
