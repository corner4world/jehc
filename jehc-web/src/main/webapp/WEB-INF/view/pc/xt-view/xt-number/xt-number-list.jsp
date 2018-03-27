<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>单号生成序列</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
            <div class="caption">
                	单号维护列表
            </div>
            <div class="actions">
                 <button class="btn btn-default" onclick="search('datatables')">
					<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
				</button>
            </div>
        </div>
    </div>
    <table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>当前值</th>
				<th>最后版本号</th>
				<th>创建时间</th>
				<th>最后修改时间</th>
				<th>模块类型</th>
			</tr>
		</thead>
	</table>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-number/xt-number-list.js"></script> 
</html>
