<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>流程中心列表</title>
</head>
<body>
	<div class="portlet box green" style="margin-bottom: 5px">
		<div class="portlet-title">
            <div class="caption">
                	查询区域
            </div>
        </div>
        <div class="portlet-body form">
        	<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
				<div class="form-group">
					<label>流程标题</label>
					<input type="text" class="form-control" name="lc_process_title" placeholder="请输入流程标题">
				</div>
				<div class="form-group">
					<label>模块</label>
					<select class="form-control" name="xt_constant_id" id="xt_constant_id" placeholder="请选择"></select>
				</div>
				<div class="form-group">
					<label>标识</label>
					<select class="form-control" name="lc_process_flag" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0">通过平台设计器设计</option>
						<option value="1">通过上传部署</option>
					</select>
				</div>
				<div class="form-group">
					<label>状态</label>
					<select class="form-control" name="lc_process_status" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0">待发布</option>
						<option value="1">发布中</option>
						<option value="2">已关闭</option>
					</select>
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
        <button class="btn btn-default" onclick="toLcProcessAdd()">
			<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
		</button>
		<button class="btn btn-default" onclick="toLcProcessUpdate()">
			<span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>修改
		</button>
		<button class="btn btn-default" onclick="delLcProcess()">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>删除
		</button>
		<button class="btn btn-default" onclick="search('datatables')">
			<span class="glyphicon glyphicon-refresh" aria-hidden="true"></span>刷新
		</button>
    </div>
	<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%;">
		<thead>
			<tr>
				<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
				<th>序号</th>
				<th>创建人</th>
				<th>流程标题</th>
				<th>状态</th>
				<th>标识</th>
				<th>操作</th>
			</tr>
		</thead>
	</table>
</body>
<!-- 流程设计器模态框（Modal）开始 -->
<div class="modal fade" id="lcDesignModal" tabindex="-1" role="dialog" aria-labelledby="lcDesignModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" id="lcDesignModalDialog">
		<div class="modal-content">
			<div class="modal-header" style="display: none;">
				<h4 class="modal-title" id="lcDesignModalLabel">
					在线设计
				</h4>
			</div>
			<div class="modal-body">
				<div class="panel-body" id="lcDesignPanelBody" style="overflow:auto;">
					<iframe id="lcDesignIframe" scrolling="auto" frameborder="0"  width="100%" height="100%"></iframe>
				</div>
			</div>
			<div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" onclick="closeLcProcessWin()">关闭</button>
            </div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal -->
</div>
<!-- 流程设计器模态框（Modal）结束 -->


<!-- 流程设计器模态框（Modal）开始 -->
<div class="modal fade" id="lcDeploymentHisModal" tabindex="-1" role="dialog" aria-labelledby="lcDeploymentHisModalLabel" aria-hidden="true">
	<div class="modal-dialog modal-lg" id="lcDeploymentHisModalDialog">
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
<!-- 流程设计器模态框（Modal）结束 -->
<script type="text/javascript" src="../view/pc/lc-view/lc-process/lc-process-list.js"></script> 
</html>
