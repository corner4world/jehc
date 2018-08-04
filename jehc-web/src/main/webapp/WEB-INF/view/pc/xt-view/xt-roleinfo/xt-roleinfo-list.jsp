<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>用户角色表</title>
<style>
#RoleDatatables{width:100%!important}
#ImportUDatatables{width:100%!important} 
#UnImportUDatatables{width:100%!important}
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
			<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed " method="POST" id="searchForm">
				<div class="m-portlet__body">	
					<div class="form-group m-form__group row">
						<label class="col-form-label">角色名称:</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="xt_role_name" placeholder="请输入角色名称">
						</div>
					</div>
	            </div>
	            <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
					<div class="m-form__actions m-form__actions--solid">
						<div class="row">
							<div class="col m--align-left">
						        <a class="btn btn-default" onclick="toXtRoleinfoAdd()">
									<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
								</a>
								<a class="btn btn-default" onclick="toXtRoleinfoUpdate()">
									<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
								</a>
								<a class="btn btn-default" onclick="delXtRoleinfo()">
									<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>禁用
								</a>
								<a class="btn btn-default" onclick="initListDeleted()">
									<span class="glyphicon glyphicon-trash" aria-hidden="true"></span>找回角色
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
		<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
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
		<div class="modal-dialog modal-lg">
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
	
	<!-- 分配用户模态框（Modal）开始 -->
	<div class="modal fade" id="UserinfoModal" tabindex="-1" role="dialog" aria-labelledby="UserinfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg"  id="UserinfoModalDialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
					<h4 class="modal-title" id="UserinfoModalLabel">
						分配用户
					</h4>
				</div>
				<div class="modal-body" id="UserinfoBody" style="overflow:auto;">
					<div class="row">
						<div class="col-md-12">					
							<!-- 待分配用户开始 -->
							<div class="portlet box green" style="margin-bottom: 5px;">
								<div class="portlet-title">
						            <div class="caption">
						                	待分配用户
						            </div>
						            <div class="actions">
						                <button class="btn btn-default" onclick="search('UnImportUDatatables')">
											<i class="glyphicon glyphicon-search"></i>&nbsp;检索
										</button>
										<button class="btn btn-default" onclick="resetAll('searchFormUnImportU');">重置</button>
										<button class="btn btn-default" onclick="addXtUr();">导入用户</button>
						            </div>
						        </div>
						        <div class="portlet-body form">
						        	<form method="POST" id="searchFormUnImportU" style="padding: 5px 0px 5px 0px;">
										<input type="hidden" id="flag2" name="flag" value="2">
										<input type="hidden" name="xt_roleinfo_id" id="xt_role_id1">
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
									<table id="UnImportUDatatables" class="table table-bordered table-striped table-hover">
										<thead>
											<tr>
												<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallUnImportU" /><span></span></label></th>
												<th>序号</th>
												<th>用户名</th>
												<th>真实姓名</th>
												<th>电子邮件</th>
											</tr>
										</thead>
									</table>
						        </div>
						    </div>
						    </div>
							<!-- 待分配用户结束 -->
					    </div>
					    <div class="row">
					    	<!-- 已分配用户开始 -->
							<div class="col-md-12">
							<div class="portlet box green" style="margin-bottom: 5px;">
								<div class="portlet-title">
						            <div class="caption">
						                	已分配用户
						            </div>
						            <div class="actions">
						                <button class="btn btn-default" onclick="search('ImportUDatatables')">
											<i class="glyphicon glyphicon-search"></i>&nbsp;检索
										</button>
										<button class="btn btn-default" onclick="resetAll('searchFormImportU');">重置</button>
										<button class="btn btn-default" onclick="delXtUR();">移除用户</button>
						            </div>
						        </div>
						        <div class="portlet-body form">
						        	<form method="POST" id="searchFormImportU" style="padding: 5px 0px 5px 0px;">
										<input type="hidden" name="flag" value="1">
										<input type="hidden" name="xt_roleinfo_id" id="xt_role_id2">
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
									<table id="ImportUDatatables" class="table table-bordered table-striped table-hover">
										<thead>
											<tr>
												<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallImportU" /><span></span></label></th>
												<th>序号</th>
												<th>用户名</th>
												<th>真实姓名</th>
												<th>电子邮件</th>
											</tr>
										</thead>
									</table>
						        </div>
					        </div>
					    </div>
					</div>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
			</div><!-- /.modal-content -->
			<!-- 已分配用户开结束-->
		</div><!-- /.modal -->
	</div>
	<!-- 分配用户模态框（Modal）结束 -->
	
	
	
	<!-- 分配菜单模态框（Modal）开始 -->
	<div class="modal fade" id="XtMenuinfoModal" tabindex="-1" role="dialog" aria-labelledby="XtMenuinfoModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="XtMenuinfoModalLabel">
						分配菜单
					</h4>
				</div>
				<div class="modal-body" id="XtMenuinfoBody" style="overflow:auto;">
					<ul id="tree" class="ztree"></ul>
				</div>
				<div class="modal-footer">
					<input type="hidden" id="roleIdForMenu">
	                <button type="button" class="btn btn-default" onclick="closeXtMenuinfoWin();">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="addXtMR();">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 分配菜单模态框（Modal）结束 -->
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-roleinfo/xt-roleinfo-list.js"></script> 
<script type="text/javascript" src="../view/pc/xt-view/xt-roleinfo/xt-userinfo-list.js"></script> 
<link rel="stylesheet" href="${syspath}/deng/source/plugins/other/bztree/css/bootstrapStyle/bootstrapStyle.css" type="text/css">
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.core.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.excheck.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/other/bztree/js/jquery.ztree.exedit.js"></script>
<script type="text/javascript" src="../view/pc/xt-view/xt-roleinfo/xt-menuinfo-treelist.js"></script>
</html>
