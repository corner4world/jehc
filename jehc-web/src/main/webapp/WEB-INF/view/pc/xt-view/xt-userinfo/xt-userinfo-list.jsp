<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>员工信息表</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px;">
		<div class="portlet-title">
            <div class="caption">
                	查询区域
            </div>
            <div class="actions">
                 <a class="btn btn-circle btn-icon-only btn-default" title="检索" href="javascript:search('datatables');">
                     <i class="glyphicon glyphicon-search"></i>
                 </a>
                 <a class="btn btn-circle btn-icon-only btn-default" title="重置" href="javascript:resetAll();;">
                     <i class="icon-trash"></i>
                 </a>
            </div>
        </div>
        <div class="portlet-body form">
        	<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<div class="form-group">
					<label>隶属部门</label>
					<input type="text" class="form-control" name="xt_departinfo_name" placeholder="请输入部门名称">
				</div>
				<div class="form-group">
					<label>岗位</label>
					<input type="text" class="form-control" name="xt_post_name" placeholder="请输入岗位名称">
				</div>
				<div class="form-group">
					<label>姓名</label>
					<input type="text" class="form-control" name="xt_userinfo_realName" placeholder="请输入姓名">
				</div>
				<div class="form-group">
					<label>用户名</label>
					<input type="text" class="form-control" name="xt_userinfo_name" placeholder="请输入用户名">
				</div>
			</form>
        </div>
    </div>
	<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
        <button class="btn btn-default" onclick="toXtUserinfoAdd()">
		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button class="btn btn-default" onclick="toXtUserinfoUpdate()">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
		</button>
		<button class="btn btn-default" onclick="delXtUserinfo()">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		</button>
		<button class="btn btn-default" onclick="initListDeleted()">
			<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>已废除
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
				<th>用户名</th>
				<th>真实姓名</th>
				<th>联系电话</th>
				<th>状态</th>
				<th>性别</th>
				<th>籍贯</th>
				<th>生日</th>
				<th>电子邮件</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<!-- 已废除用户模态框（Modal）开始 -->
<div class="modal fade" id="deletedUserinfoSelectModal" tabindex="-1" role="dialog" aria-labelledby="deletedUserinfoModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="deletedUserinfoModalLabel">
					已废除用户列表
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel panel-default">
					<fieldset>
						<legend>查询区域</legend>
						<form method="POST" id="searchFormDeletedUserinfo">
							<div class="row">
								<div class="col-md-2">
									<label>隶属部门</label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" name="xt_departinfo_name" placeholder="请输入部门名称">
								</div>
								<div class="col-md-2">
									<label>岗位</label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" name="xt_post_name" placeholder="请输入岗位名称">
								</div>
							</div>
							<div class="row">
								<div class="col-md-2">
									<label>姓名</label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" name="xt_userinfo_realName" placeholder="请输入姓名">
								</div>
								<div class="col-md-2">
									<label>用户名</label>
								</div>
								<div class="col-md-3">
									<input type="text" class="form-control" name="xt_userinfo_name" placeholder="请输入用户名">
								</div>
							</div>
						</form>
						<div class="form-group" style="margin-left: 35px;margin-top: 25px;">
							<button class="btn btn-primary" onclick="search('deletedUserinfoDatatables')">
								<i class="glyphicon glyphicon-search"></i>&nbsp;检索
							</button>
							<button class="btn btn-default" onclick="resetAll('searchFormDeletedUserinfo');">重置</button>
						</div>
					</fieldset>
				</div>
				<div class="panel-body">
					<table id="deletedUserinfoDatatables" class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallDeletedUserinfo" /><span></span></label></th>
								<th>序号</th>
								<th>用户名</th>
								<th>真实姓名</th>
								<th>联系电话</th>
								<th>籍贯</th>
								<th>生日</th>
								<th>电子邮件</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="recoverXtUserinfo();">恢复用户</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 已废除用户模态框（Modal）结束 -->


<!-- 已拥有权限模态框（Modal）开始 -->
<div class="modal fade" id="userRoleModal" tabindex="-1" role="dialog" aria-labelledby="userRoleModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="userRoleModalLabel">
					角色列表
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body">
					<table id="userRoleDatatables" class="table table-bordered table-striped table-hover">
						<thead>
							<tr>
								<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallUserRole" /><span></span></label></th>
								<th>序号</th>
								<th>角色名称</th>
							</tr>
						</thead>
					</table>
				</div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 已拥有权限用户模态框（Modal）结束 -->
<script type="text/javascript" src="../view/pc/xt-view/xt-userinfo/xt-userinfo-list.js"></script> 
</html>
