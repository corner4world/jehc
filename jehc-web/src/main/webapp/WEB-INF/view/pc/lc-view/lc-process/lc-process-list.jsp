<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>流程中心列表</title>
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
						<label class="col-form-label">流程标题:</label>
						<div class="col-lg-2">
							<input type="text" class="form-control" name="lc_process_title" placeholder="请输入流程标题">
						</div>
						<label class="col-form-label">模块:</label>
						<div class="col-lg-2">
							<select class="form-control" name="xt_constant_id" id="xt_constant_id" placeholder="请选择"></select>
						</div>
						<label class="col-form-label">标识:</label>
						<div class="col-lg-2">
							<select class="form-control" name="lc_process_flag" placeholder="请选择">
								<option value="">请选择</option>
								<option value="0">通过平台设计器设计</option>
								<option value="1">通过上传部署</option>
							</select>
						</div>
						<label class="col-form-label">状态:</label>
						<div class="col-lg-2">
							<select class="form-control" name="lc_process_status" placeholder="请选择">
								<option value="">请选择</option>
								<option value="0">待发布</option>
								<option value="1">发布中</option>
								<option value="2">已关闭</option>
							</select>
						</div>
					</div>
	            </div>
	            <div class="m-portlet__foot m-portlet__no-border m-portlet__foot--fit">
					<div class="m-form__actions m-form__actions--solid">
						<div class="row">
							<div class="col m--align-left">
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="toLcProcessAdd()">
									<span><i class="fa fa-pencil fa-lg"></i><span>新增</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="toLcProcessUpdate()">
									<span><i class="fa fa-magic fa-lg"></i><span>修改</span></span>
								</a>
								<a class="btn btn-secondary m-btn m-btn--custom m-btn--icon" href="javascript:delLcProcess()">
									<span><i class="fa fa-times"></i><span>删除</span></span>
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
	</div>
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
