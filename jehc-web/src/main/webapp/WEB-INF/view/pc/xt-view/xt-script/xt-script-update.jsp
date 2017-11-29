<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台脚本编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑平台脚本</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">脚本编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_script_id"  placeholder="请输入脚本编号" value="${xtScript.xt_script_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_script_title" placeholder="请输入标题" value="${xtScript.xt_script_title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">脚本键</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  data-bv-notempty data-bv-notempty-message="请输入脚本键"  name="xt_script_key" placeholder="请输入脚本键" value="${xtScript.xt_script_key }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">脚本内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  name="xt_script_text" placeholder="请输入脚本内容">${xtScript.xt_script_text }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">脚本类型</label>
				<div class="col-lg-6">
				<select class="form-control" name="xt_script_type" placeholder="请选择">
					<option value="">请选择</option>
					<option value="1"  <c:if test="${xtScript.xt_script_type eq 0 }">selected</c:if>>javaScript</option>
					<option value="2"  <c:if test="${xtScript.xt_script_type eq 0 }">selected</c:if>>Sql</option>
					<option value="3"  <c:if test="${xtScript.xt_script_type eq 0 }">selected</c:if>>html</option>
					<option value="4"  <c:if test="${xtScript.xt_script_type eq 0 }">selected</c:if>>其他</option>
				</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
				<select class="form-control" name="xt_script_status" placeholder="请选择">
					<option value="">请选择</option>
					<option value="0"  <c:if test="${xtScript.xt_script_status eq 0 }">selected</c:if>>正常</option>
					<option value="1"  <c:if test="${xtScript.xt_script_status eq 1 }">selected</c:if>>禁用</option>
				</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="xt_script_content" placeholder="请输入描述">${xtScript.xt_script_content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否参与代码生成器查询配置</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_script_search_filed" placeholder="请选择">
						<option value=""><option>
						<option value="0" <c:if test="${xtScript.xt_script_search_filed eq 0 }">selected</c:if>>是</option>
						<option value="1" <c:if test="${xtScript.xt_script_search_filed eq 1 }">selected</c:if>>否</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">下拉框真实值</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_script_valuefield" placeholder="请输入下拉框真实值" value="${xtScript.xt_script_valuefield }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">下拉框显示值</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="xt_script_displayfield" placeholder="请输入下拉框显示值" value="${xtScript.xt_script_displayfield }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateXtScript()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-script/xt-script-update.js"></script> 
</html>
