<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>待办任务（全部）</title>  
</head>  
<body>  
<div class="panel panel-default">
		<fieldset>
			<legend>查询区域</legend>
			<form method="POST" id="searchForm" class="form-inline">
				
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
			<button class="btn btn-default" onclick="setAssignee()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>设置经办人
			</button>
			<button class="btn btn-default" onclick="setOwner()">
				<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>设置归属人
			</button>
			<button class="btn btn-default" onclick="addGroupUser()">
				<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>添加组成员
			</button>
			<button class="btn btn-default" onclick="deleteGroupUser()">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>移除组成员
			</button>
			<button class="btn btn-default" onclick="completeTask()">
				<span class="glyphicon glyphicon-ok" aria-hidden="true"></span>完成任务
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
					<th>任务名称</th>
					<th>所属人</th>
					<th>执行人</th>
					<th>描述</th>
					<th>实例编号</th>
					<th>流程定义</th>
					<th>租户</th>
					<th>提交时间</th>
					<th>操作</th>
				</tr>
			</thead>
		</table>
	</div>
</body>
<!-- 流程图监控模态框（Modal）开始 -->
<div class="modal fade" id="lcImageModal" tabindex="-1" role="dialog" aria-labelledby="lcImageModalLabel" aria-hidden="true">
	<div class="modal-dialog" id="lcImageModalDialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="lcImageModalLabel">
					实例监控图
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body" id="lcImagePanelBody" style="overflow:auto;">
					<iframe id="lcImageIframe" scrolling="auto" frameborder="0"  width="100%" height="100%"></iframe>
				</div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeLcImageWin()">关闭</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 流程图监控模态框（Modal）结束 -->


<!-- 流程发布历史记录模态框（Modal）开始 -->
<div class="modal fade" id="lcDeploymentHisModal" tabindex="-1" role="dialog" aria-labelledby="lcDeploymentHisModalLabel" aria-hidden="true">
	<div class="modal-dialog" id="lcDeploymentHisModalDialog">
		<div class="modal-content">
			<div class="modal-header" style="display: none;">
				<h4 class="modal-title" id="lcDeploymentHisModalLabel">
					流程发布历史记录
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body" id="lcDeploymentHisPanelBody" style="overflow:auto;">
					<iframe id="lcDeploymentHisIframe" scrolling="auto" frameborder="0"  width="100%" height="100%"></iframe>
				</div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeLcProcessWin()">关闭</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 流程发布历史记录模态框（Modal）结束 -->

<!-- 人员设置模态框（Modal）开始 -->
<div class="modal fade" id="lcAssigneeModal" tabindex="-1" role="dialog" aria-labelledby="lcAssigneeModalLabel" aria-hidden="true">
	<div class="modal-dialog" id="lcAssigneeModalDialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="lcAssigneeModalLabel">
					人员设置
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body" id="lcAssigneePanelBody" style="overflow:auto;">
					<div class="panel panel-default">
						<fieldset>
							<legend>查询区域</legend>
							<form method="POST" id="searchFormUserinfo">
								<input type="hidden" class="form-control" id="lcTaskType">	
								<input type="hidden" class="form-control" id="taskId">	
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
								<button class="btn btn-primary" onclick="search('userinfoDatatables')">
									<i class="glyphicon glyphicon-search"></i>&nbsp;检索
								</button>
								<button class="btn btn-default" onclick="resetAll('searchFormUserinfo');">重置</button>
							</div>
						</fieldset>
					</div>
					<div class="panel-body">
						<table id="userinfoDatatables" class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallUserinfo" /><span></span></label></th>
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
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="doUser();">提交</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 人员设置模态框（Modal）结束 -->


<!-- 审批记录模态框（Modal）开始 -->
<div class="modal fade" id="lcHisLogModal" tabindex="-1" role="dialog" aria-labelledby="lcHisLogModalLabel" aria-hidden="true">
	<div class="modal-dialog" id="lcHisLogModalDialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title" id="lcHisLogModalLabel">
					审批记录
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body" id="lcHisLogPanelBody" style="overflow:auto;">
					<div class="panel panel-default">
						<fieldset>
							<legend>查询区域</legend>
							<form method="POST" id="searchFormApproval">
								<input type="hidden" class="form-control" id="instanceId" name="instanceId">	
							</form>
							<div class="form-group" style="margin-left: 35px;margin-top: 25px;">
								<button class="btn btn-primary" onclick="search('userinfoDatatables')">
									<i class="glyphicon glyphicon-search"></i>&nbsp;检索
								</button>
								<button class="btn btn-default" onclick="resetAll('searchFormUserinfo');">重置</button>
							</div>
						</fieldset>
					</div>
					<div class="panel-body">
						<table id="approvalDatatables" class="table table-bordered table-striped table-hover">
							<thead>
								<tr>
									<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkallApproval" /><span></span></label></th>
									<th>序号</th>
									<th>批审状态</th>
									<th>审批内容</th>
									<th>审批时间</th>
									<th>批审人</th>
								</tr>
							</thead>
						</table>
					</div>
				</div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 审批记录模态框（Modal）结束 -->
<script type="text/javascript" src="../view/pc/lc-view/lc-task/lc-task-list.js"></script> 
</body>  
</html> 