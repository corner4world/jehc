<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>关于我们</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
			<div class="caption">
				查询区域
			</div>
		</div>
		<div class="portlet-body form">
			<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<div class="form-group">
					<label>标题</label>
					<input type="text" class="form-control" name="title" placeholder="请输入标题">
				</div>
					&nbsp;
				<a class="btn btn-outline-primary waves-light waves-effect" title="检索" href="javascript:search('datatables');">
		           <i class="fa fa-search m-r-5"></i>检索
		        </a>&nbsp;
		        <a class="btn btn-outline-danger waves-light waves-effect" title="重置" href="javascript:resetAll();;">
		           <i class="fa fa-remove m-r-5"></i>重置
		        </a>
			</form>
		</div>
	</div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
		<button class="btn btn-outline-success waves-light waves-effect" onclick="toCmsAboutAdd()">
			<i class="fa fa-plus-circle"></i>新增
		</button>
		<button class="btn btn-outline-info waves-light waves-effect" onclick="toCmsAboutUpdate()">
			<i class="fa fa-pencil"></i>修改
		</button>
		<button class="btn btn-outline-danger waves-light waves-effect" onclick="delCmsAbout()">
			<i class="fa fa-trash-o"></i>删除
		</button>
		<button class="btn btn-outline-warning waves-light waves-effect" onclick="search('datatables')">
			<i class="fa fa-spin fa-refresh"></i>刷新
		</button>
	</div>
	<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>标题</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>最后修改日期</th>
				<th>创建人</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/cms-view/cms-about/cms-about-list.js"></script> 
</html>
