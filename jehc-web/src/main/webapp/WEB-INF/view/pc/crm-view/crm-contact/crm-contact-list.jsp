<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>客户联系人</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px;">
		<div class="portlet-title">
            <div class="caption">
                	分配联系人---【${crmCustomer.name}】
	          </div>
	      </div>
	       <div class="portlet-body form">
        	<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<div class="form-group">
					<label>联系人姓名</label>
					<input type="text" class="form-control" name="contactName" placeholder="请输入联系人姓名">
					<input type="hidden" class="form-control" id="customerId" name="customerId" value="${crmContact.customerId }">
				</div>
					&nbsp;
				<a class="btn btn-primary" title="检索" href="javascript:search('datatables');">
		           <i class="fi-search"></i>检索
		        </a>&nbsp;
		        <a class="btn btn-primary" title="重置" href="javascript:resetAll();;">
		           <i class="icon-trash"></i>重置
		        </a>
			</form>
	     </div>
    </div>
    <div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
       <button class="btn btn-default" onclick="toCrmContactAdd()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button class="btn btn-default" onclick="toCrmContactUpdate()">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
		</button>
		<button class="btn btn-default" onclick="delCrmContact()">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		</button>
		<button class="btn btn-default" onclick="search('datatables')">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
		<button type="button" class="btn default" onclick="goback()">返回</button>
    </div>
		<table id="datatables" class="table table-bordered table-striped table-hover">
			<thead>
				<tr>
					<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
					<th>序号</th>
					<th>联系人姓名</th>
					<th>联系人电话</th>
					<th>岗位</th>
					<th>在职状态</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<script type="text/javascript" src="../view/pc/crm-view/crm-contact/crm-contact-list.js"></script> 
</html>
