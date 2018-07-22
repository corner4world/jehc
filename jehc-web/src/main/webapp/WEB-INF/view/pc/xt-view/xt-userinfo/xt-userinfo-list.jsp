<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>员工信息表</title>
<link href="${syspath}/deng/source/css/bootlist.css" rel="stylesheet" type="text/css" />
<style>
#deletedUserinfoDatatables{width:100%!important}
#userRoleDatatables{width:100%!important} 
</style>
</head>
<body>
	<div class="m-content">
		<div class="m-portlet">
			<div class="m-portlet__head">
				<div class="m-portlet__head-caption">
					<div class="m-portlet__head-title">
						<h3 class="m-portlet__head-text">
							<span class="m-accordion__item-icon"><i class="flaticon-search"></i>查询区域</span>
						</h3>
					</div>
				</div>
			</div>
			<!--begin::Form-->
			<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" method="POST" id="searchForm">
				<div class="m-portlet__body">	
					<div class="form-group m-form__group row">
						<label class="col-form-label">隶属部门:</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="xt_departinfo_name" placeholder="请输入部门名称">
						</div>
						<label class="col-form-label">岗位:</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="xt_post_name" placeholder="请输入岗位名称">
						</div>
						<label class="col-form-label">姓名:</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="xt_userinfo_name" placeholder="请输入用户名">
						</div>
						<label class="col-form-label">用户名:</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="xt_userinfo_name" placeholder="请输入用户名">
						</div>
					</div>	
	            </div>
	            <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
					<div class="m-form__actions m-form__actions--solid">
						<div class="row">
							<div class="col m--align-left">
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:toXtUserinfoAdd()">
									 <span><i class="fa fa-pencil fa-lg"></i><span>新增</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:toXtUserinfoUpdate()">
									 <span><i class="fa fa-magic fa-lg"></i><span>修改</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:delXtUserinfo()">
									<span><i class="fa fa-times"></i><span>删除</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:initListDeleted()">
									<span><i class="fa fa-history fa-lg"></i><span>找回用户</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:search('datatables')">
									<span><i class="fa fa-spin fa-refresh m-r-5"></i><span>刷新</span></span>
								</a>
							</div>
							<div class="col m--align-right">
								<a href="javascript:search('datatables')" class="btn btn-info m-btn m-btn--custom m-btn--icon">
									<span><i class="fa fa-search"></i><span>检索</span></span>
								</a>
								<a href="javascript:resetAll()" class="btn btn-secondary m-btn m-btn--custom m-btn--icon">
									<span><i class="fa fa-repeat"></i><span>重置</span></span>
								</a>
							</div>
						</div>
					</div>
				</div>
			</form>
			<!--end::Form-->
		</div>
		<table id="datatables" class="table table-bordered table-striped table-hover">
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
	</div>
</body>
<!-- 已废除用户模态框（Modal）开始 -->
<div class="modal fade" id="deletedUserinfoSelectModal" tabindex="-1" role="dialog" aria-labelledby="deletedUserinfoModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" id="UserinfoModalDialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="deletedUserinfoModalLabel">
					禁用用户列表
				</h4>
			</div>
			<div class="modal-body" id="deletedUserinfoBody" style="overflow:auto;">
				<div class="panel panel-default">
					<div class="portlet box green" style="margin-bottom: 5px;">
						<div class="portlet-title">
				            <div class="caption">
				                     查询区域
				            </div>
				            <div class="actions">
				                <button class="btn btn-default" onclick="search('deletedUserinfoDatatables')">
									<i class="glyphicon glyphicon-search"></i>&nbsp;检索
								</button>
								<button class="btn btn-default" onclick="resetAll('searchFormDeletedUserinfo');">重置</button>
				            </div>
				        </div>
				        <div class="portlet-body form">
				        	<form method="POST" id="searchFormDeletedUserinfo" style="padding: 5px 5px 5px 5px;">
								<div class="row">
									<div class="col-md-3">
										<label>隶属部门</label>
										<input type="text" class="form-control" name="xt_departinfo_name" placeholder="请输入部门名称">
									</div>
									<div class="col-md-3">
										<label>岗位</label>
										<input type="text" class="form-control" name="xt_post_name" placeholder="请输入岗位名称">
									</div>
									<div class="col-md-3">
										<label>姓名</label>
										<input type="text" class="form-control" name="xt_userinfo_realName" placeholder="请输入姓名">
									</div>
									<div class="col-md-3">
										<label>用户名</label>
										<input type="text" class="form-control" name="xt_userinfo_name" placeholder="请输入用户名">
									</div>
								</div>
							</form>
				        </div>
				    </div>
				</div>
				<table id="deletedUserinfoDatatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap;">
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
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="recoverXtUserinfo();">恢复用户</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 已废除用户模态框（Modal）结束 -->



                            
<!-- 已拥有权限模态框（Modal）开始 -->
<div class="modal fade" id="userRoleModal" tabindex="-1" role="dialog" aria-labelledby="userRoleModalLabel">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
            	 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myLargeModalLabel">角色列表</h4>
            </div>
            <div class="modal-body">
                <div class="panel-body">
					<table id="userRoleDatatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap;">
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
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- 已拥有权限用户模态框（Modal）结束 -->
<script type="text/javascript" src="../view/pc/xt-view/xt-userinfo/xt-userinfo-list.js"></script> 
</html>
