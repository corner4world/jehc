<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>solr多实例URL配置详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>solr多实例URL配置详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="solr_url_id"  placeholder="请输入主键" value="${solrUrl.solr_url_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">访问URL</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="1000"  name="solr_url_url" placeholder="请输入访问URL" value="${solrUrl.solr_url_url }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="solr_url_ctime" placeholder="请输入创建时间" value="${solrUrl.solr_url_ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="solr_url_mtime" placeholder="请输入修改时间" value="${solrUrl.solr_url_mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人" value="${solrUrl.xt_userinfo_realName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="solr_url_content" placeholder="请输入备注">${solrUrl.solr_url_content }</textarea>
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
<script type="text/javascript" src="../view/pc/solr-view/solr-url/solr-url-detail.js"></script> 
</html>
