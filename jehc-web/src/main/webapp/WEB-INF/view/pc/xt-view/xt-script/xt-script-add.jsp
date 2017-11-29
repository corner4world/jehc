<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台脚本新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建平台脚本</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_script_title" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">脚本键</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入脚本键"  name="xt_script_key" placeholder="请输入脚本键">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">脚本内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  name="xt_script_text" placeholder="请输入脚本内容"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">脚本类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_script_type" placeholder="请选择">
						<option value="">请选择</option>
						<option value="1">javaScript</option>
						<option value="2">Sql</option>
						<option value="3">html</option>
						<option value="4">其他</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_script_status" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0">正常</option>
						<option value="1">禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="xt_script_content" placeholder="请输入描述"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否参与代码生成器查询配置</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_script_search_filed" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0">是</option>
						<option value="1">否</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">下拉框真实值</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_script_valuefield" placeholder="请输入下拉框真实值">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">下拉框显示值</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_script_displayfield" placeholder="请输入下拉框显示值">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addXtScript()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-script/xt-script-add.js"></script> 
</html>
