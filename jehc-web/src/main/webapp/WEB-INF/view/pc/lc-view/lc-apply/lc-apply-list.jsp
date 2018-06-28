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
<title>我的申请列表</title>  
</head>  
<body>
	<div class="panel panel-default">
		<fieldset>
			<legend>查询区域</legend>
			<form method="POST" id="searchForm" class="form-inline">
				<label>标题</label>
				<input type="text" class="form-control" name="lc_apply_title" placeholder="请输入标题">
					&nbsp;
				<a class="btn btn-primary" title="检索" href="javascript:search('datatables');">
		           <i class="fi-search"></i>检索
		        </a>&nbsp;
		        <a class="btn btn-primary" title="重置" href="javascript:resetAll();;">
		           <i class="icon-trash"></i>重置
		        </a>
			</form>
		</fieldset>
	</div>
	<div class="panel-body">
		<div class="btn-group pull-right" style="margin-right: 20px;">
			<button class="btn btn-default" onclick="delLcApply()">
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
					<th>标题</th>
					<th>模块</th>
					<th>申请时间</th>
				</tr>
			</thead>
		</table>
	</div>
	
	
	
	<!-- 流程申请详情模态框（Modal）开始 -->
	<div class="modal fade" id="lcApplyDetailModal" tabindex="-1" role="dialog" aria-labelledby="lcApplyDetailModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg" id="lcApplyDetailModalDialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="lcApplyDetailModalLabel">
						申请详情
					</h4>
				</div>
				<div class="modal-body">
					<div class="panel-body" id="lcApplyDetailPanelBody" style="overflow:auto;">
						<ul id="myTab" class="nav nav-tabs nav nav-tabs tabs-bordered">
							<li >
								<a href="#tab0" data-toggle="tab" aria-expanded="false" class="nav-link active show">实例信息</a>
							</li>
							<li>
								<a href="#tab1" data-toggle="tab" aria-expanded="false" class="nav-link">表单信息</a>
							</li>
							<li>
								<a href="#tab2" data-toggle="tab" aria-expanded="false" class="nav-link">审批记录</a>
							</li>
							<li>
								<a href="#tab3" data-toggle="tab" aria-expanded="false" class="nav-link">流程实例图</a>
							</li>
						</ul>
						<div id="myTabContent" class="tab-content" style="height:80%;">
							<div class="tab-pane active show" id="tab0">
								<form class="form-horizontal" id="defaultForm" method="post">
								<div class="form-group">
									<label class="col-lg-3 control-label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题</label>
									<div class="col-lg-4">
										<input class="form-control" type="text" id="lc_apply_title">
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块</label>
									<div class="col-lg-4">
										<textarea class="form-control" rows="3" id="xt_constantRemark" ></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">申请内容</label>
									<div class="col-lg-4">
										<textarea class="form-control" rows="3" id="lc_apply_remark" ></textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">申请时间</label>
									<div class="col-lg-4">
										<textarea class="form-control" rows="3" id="lc_apply_time" ></textarea>
									</div>
								</div>
								</form>
							</div>
							<div class="tab-pane" id="tab1" style="height:100%;">
								<iframe id="lcFormIframe" scrolling="auto" frameborder="0"  width="100%" height="100%"></iframe>
							</div>
							<div class="tab-pane" id="tab2" style="height:100%;">
								<div class="panel-body" id="lcHisLogPanelBody" style="overflow:auto;">
									<div class="panel panel-default">
										<fieldset>
											<form method="POST" id="searchFormApproval">
												<input type="hidden" class="form-control" id="lc_apply_id_" name="lc_apply_id">	
											</form>
										</fieldset>
									</div>
									<div class="panel-body" style="height:100%;">
										<table id="approvalDatatables" class="table table-bordered table-striped table-hover" style="width:99%">
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
							<div class="tab-pane" id="tab3" style="height:100%;">
								<iframe id="lcProcessInstanceImgIframe" scrolling="auto" frameborder="0"  width="100%" height="100%"></iframe>
							</div>
						</div>
					</div>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 流程申请详情模态框（Modal）结束 -->
</body>  
<script type="text/javascript" src="../view/pc/lc-view/lc-apply/lc-apply-list.js"></script> 
</html> 