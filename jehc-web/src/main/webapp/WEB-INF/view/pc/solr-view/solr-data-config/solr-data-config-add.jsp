<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>solr_data_config模板配置新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建solr_data_config模板配置</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="solr_data_config_title" placeholder="请输入标题">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">数据源连接配置</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="solr_data_config_datasource" placeholder="请输入数据源连接配置"></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="solr_data_config_status">
						<option value="">请选择</option>
						<option value="0">正常</option>
						<option value="1">禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">配置内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  name="solr_data_config_content" placeholder="请输入配置内容"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addSolrDataConfig()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/solr-view/solr-data-config/solr-data-config-add.js"></script> 
</html>
