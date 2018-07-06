<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>意见申诉新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建意见申诉</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="oa_suggestionboxTitle" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">意见内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="oa_suggestionboxContent" placeholder="请输入意见内容"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否公开</label>
				<div class="col-lg-6">
					<select class="form-control" name="oa_suggestionboxIsPub" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0">是</option>
						<option value="1">否</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="oa_suggestionboxType" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0">签名方式</option>
						<option value="1">匿名</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addOaSuggestionbox()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-suggestionbox/oa-suggestionbox-add.js"></script> 
</html>
