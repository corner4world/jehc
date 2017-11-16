<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公告</title>
</head>
<body>
	<div class="panel panel-default">
		<fieldset>
			<legend>查询区域</legend>
			<form method="POST" id="searchForm" class="form-inline">
				<div class="form-group">
					<label>题标</label>
					<input type="text" class="form-control" name="oa_noticeTitle" placeholder="请输入题标">
				</div>
				<div class="form-group">
					<label>状态</label>
					<select class="form-control" name="status" placeholder="请选择">
						<option value=''>请选择</option>
				    	<option value='0' <c:if test="${oaNotice.status eq 0 }">selected</c:if>>草稿</option>
				    	<option value='1' <c:if test="${oaNotice.status eq 1 }">selected</c:if>>审核中</option>
				    	<option value='2' <c:if test="${oaNotice.status eq 2 }">selected</c:if>>审核通过</option>
				    	<option value='3' <c:if test="${oaNotice.status eq 3 }">selected</c:if>>审核未通过</option>
					</select>
				</div>
				<div class="form-group">
					<label>类型</label>
					<select class="form-control" name="oa_noticeType" placeholder="请选择">
						<option value=''>请选择</option>
				    	<option value='1' <c:if test="${oaNotice.oa_noticeType eq 1 }">selected</c:if>>一般</option>
				    	<option value='2' <c:if test="${oaNotice.oa_noticeType eq 2 }">selected</c:if>>重要</option>
				    	<option value='3' <c:if test="${oaNotice.oa_noticeType eq 3 }">selected</c:if>>非常重要</option>
					</select>
				</div>
				<div class="form-group">
					<label>创建时间</label>
					<div class="input-group">
						<input type="text" class="form_datetime form-control" placeholder="起始时间" name="oa_noticeCreateTime_st" />
						<span class="input-group-addon">至</span>
						<input type="text" class="form_datetime form-control" placeholder="结束时间" name="oa_noticeCreateTime_et" />
					</div>
				</div>
			</form>
			<div class="form-group" style="margin-left: 35px;margin-top: 25px;">
				<button class="btn btn-primary" onclick="search('datatables')">
					<i class="glyphicon glyphicon-search"></i>&nbsp;检索
				</button>
				<button class="btn btn-default" onclick="resetAll();">重置</button>
			</div>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="toOaNoticeAdd()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button class="btn btn-default" onclick="toOaNoticeUpdate()">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button class="btn btn-default" onclick="delOaNotice()">
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
					<th>题标</th>
					<th>类型</th>
					<th>状态</th>
					<th>点击量</th>
					<th>创建时间</th>
					<th>创建人</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-notice/oa-notice-list.js"></script> 
</html>
