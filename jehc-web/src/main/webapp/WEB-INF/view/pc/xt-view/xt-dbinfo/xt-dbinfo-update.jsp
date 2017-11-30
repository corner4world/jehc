<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>数据库信息表编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑数据库信息表</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">数据库信息表</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_dbinfo_id"  placeholder="请输入数据库信息表" value="${xtDbinfo.xt_dbinfo_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">数据库名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoName" placeholder="请输入数据库名称" value="${xtDbinfo.xt_dbinfoName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">数据库用户名</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoUName" placeholder="请输入数据库用户名" value="${xtDbinfo.xt_dbinfoUName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">数据库密码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoPwd" placeholder="请输入数据库密码" value="${xtDbinfo.xt_dbinfoPwd }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备份IP</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoIp" placeholder="请输入备份IP" value="${xtDbinfo.xt_dbinfoIp }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">端口号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="xt_dbinfoPort" placeholder="请输入端口号" value="${xtDbinfo.xt_dbinfoPort }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_dbinfoType">
						<option value="mysql" <c:if test="${xtDbinfo.xt_dbinfoType eq 'mysql'}">selected</c:if>>mysql</option>
						<option value="Oracle" <c:if test="${xtDbinfo.xt_dbinfoType eq 'Oracle'}">selected</c:if>>Oracle</option>
						<option value="Sqlserver" <c:if test="${xtDbinfo.xt_dbinfoType eq 'Sqlserver'}">selected</c:if>>Sqlserver</option>
						<option value="db2" <c:if test="${xtDbinfo.xt_dbinfoType eq 'db2'}">selected</c:if>>db2</option>
						<option value="syBase" <c:if test="${xtDbinfo.xt_dbinfoType eq 'syBase'}">selected</c:if>>syBase</option>
						<option value="Access" <c:if test="${xtDbinfo.xt_dbinfoType eq 'Access'}">selected</c:if>>Access</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateXtDbinfo()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-dbinfo/xt-dbinfo-update.js"></script> 
</html>
