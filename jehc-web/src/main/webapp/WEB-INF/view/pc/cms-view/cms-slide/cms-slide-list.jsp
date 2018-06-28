<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台幻灯片</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
			<div class="caption">
				查询区域
			</div>
			<div class="actions">
				<button class="btn btn-default" onclick="search('datatables');">
					<i class="glyphicon glyphicon-search"></i>&nbsp;检索
				</button>
				<button class="btn btn-default" onclick="resetAll();">
					<i class="glyphicon glyphicon-trash"></i>&nbsp;重置
				</button>
			</div>
		</div>
		<div class="portlet-body form">
			<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<div class="form-group">
					<label>标题</label>
					<input type="text" class="form-control" name="title" placeholder="请输入标题">
				</div>
			</form>
		</div>
	</div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
		<button class="btn btn-default" onclick="toCmsSlideAdd()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button class="btn btn-default" onclick="toCmsSlideUpdate()">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
		</button>
		<button class="btn btn-default" onclick="delCmsSlide()">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		</button>
		<button class="btn btn-default" onclick="search('datatables')">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
	</div>
	<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>图片</th>
				<th>标题</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>最后修改时间</th>
				<th>创建人</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/cms-view/cms-slide/cms-slide-list.js"></script> 
</html>
