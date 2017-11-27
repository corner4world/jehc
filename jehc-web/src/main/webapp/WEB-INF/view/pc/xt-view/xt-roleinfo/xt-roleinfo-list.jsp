<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户角色表</title>
</head>
<body>
	<div class="panel panel-default">
		<fieldset>
			<legend>查询区域</legend>
			<form method="POST" id="searchForm" class="form-inline">
				<div class="form-group">
					<label>角色名称</label>
					<input type="text" class="form-control" name="xt_role_name" placeholder="请输入角色名称">
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
			<button class="btn btn-default" onclick="toXtRoleinfoAdd()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
			</button>
			<button class="btn btn-default" onclick="toXtRoleinfoUpdate()">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
			</button>
			<button class="btn btn-default" onclick="delXtRoleinfo()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>禁用
			</button>
			<button class="btn btn-default" onclick="initListDeleted()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>已禁用角色
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
					<th>角色名称</th>
					<th>描述</th>
					<th>类型</th>
					<th>创建时间</th>
					<th>修改时间</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
	
	<!-- 已废除权限模态框（Modal）开始 -->
	<div class="modal fade" id="RoleModal" tabindex="-1" role="dialog" aria-labelledby="RoleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="RoleModalLabel">
						角色列表
					</h4>
				</div>
				<div class="modal-body">
					<div class="panel-body">
						<table id="RoleDatatables" class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallRole" /><span></span></label></th>
									<th>序号</th>
									<th>角色名称</th>
									<th>类型</th>
									<th>创建时间</th>
									<th>修改时间</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="recoverXtRoleinfo();">恢复角色</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 已废除权限用户模态框（Modal）结束 -->
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-roleinfo/xt-roleinfo-list.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-roleinfo/xt-menuinfo-treelist.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-roleinfo/xt-userinfo-list.js"></script> 
</html>
