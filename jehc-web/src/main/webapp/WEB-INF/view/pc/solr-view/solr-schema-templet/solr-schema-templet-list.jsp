<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>solr schema 模板</title>
</head>
<body>
	<div class="panel panel-default">
		<fieldset>
			<legend>查询区域</legend>
			<form method="POST" id="searchForm" class="form-inline">
				<div class="form-group">
					<label>标题</label>
					<input type="text" class="form-control" name="solr_schema_templet_title" placeholder="请输入标题">
				</div>
				<div class="form-group">
					<label>状态</label>
					<select class="form-control" name="solr_schema_templet_status">
						<option value="">请选择</option>
						<option value="0">正常</option>
						<option value="1">禁用</option>
					</select>
				</div>
					&nbsp;
				<a class="btn btn-primary" title="检索" href="javascript:search('datatables');">
		           <i class="fi-search"></i>检索
		        </a>&nbsp;
		        <a class="btn btn-primary" title="重置" href="javascript:resetAll();;">
		           <i class="icon-trash"></i>重置
		        </a>
			</form>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-left" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="toSolrSchemaTempletAdd()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button class="btn btn-default" onclick="toSolrSchemaTempletUpdate()">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button class="btn btn-default" onclick="delSolrSchemaTemplet()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
			</button>
			<button class="btn btn-default" onclick="search('datatables')">
				<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
			</button>
		</div>
		<table id="datatables" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>标题</th>
					<th>创建时间</th>
					<th>修改时间</th>
					<th>状态</th>
					<th>操作人</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/solr-view/solr-schema-templet/solr-schema-templet-list.js"></script> 
</html>
