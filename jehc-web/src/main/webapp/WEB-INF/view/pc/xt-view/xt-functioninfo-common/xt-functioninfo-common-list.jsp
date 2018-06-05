<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公共功能</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
            <div class="caption">
                	查询区域
            </div>
            <div class="actions">
                 <a class="btn btn-circle btn-icon-only btn-default" title="检索" href="javascript:search('datatables');">
                     <i class="fi-search"></i>
                 </a>
                 <a class="btn btn-circle btn-icon-only btn-default" title="重置" href="javascript:resetAll();;">
                     <i class="icon-trash"></i>
                 </a>
            </div>
        </div>
        <div class="portlet-body form">
        	<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<div class="form-group">
					<label>功能地址</label>
					<input type="text" class="form-control" name="xt_functioninfo_common_url" placeholder="请输入功能地址">
				</div>
				<div class="form-group">
					<label>状态</label>
					<select class="form-control" name="xt_functioninfo_common_status" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0">启用</option>
						<option value="1">禁用</option>
					</select>
				</div>
				<div class="form-group">
					<label>方法名称</label>
					<input type="text" class="form-control" name="xt_functioninfo_common_method" placeholder="请输入方法名称">
				</div>
				<div class="form-group">
					<label>标题</label>
					<input type="text" class="form-control" name="xt_functioninfo_common_title" placeholder="请输入标题">
				</div>
			</form>
        </div>
    </div>
    <div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
        <button class="btn btn-default" onclick="toXtFunctioninfoCommonAdd()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button class="btn btn-default" onclick="toXtFunctioninfoCommonUpdate()">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
		</button>
		<button class="btn btn-default" onclick="delXtFunctioninfoCommon()">
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
				<th>标题</th>
				<th>方法名称</th>
				<th>状态</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>创建人</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-functioninfo-common/xt-functioninfo-common-list.js"></script> 
</html>
