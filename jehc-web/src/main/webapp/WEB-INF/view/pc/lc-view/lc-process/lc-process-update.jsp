<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>流程详细信息即流程部署内容,BPMN文件,ZIP路径,IMG路径,MXGraph内容等等（流程表）编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑流程</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">流程编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="lc_process_id"  placeholder="请输入流程编号" value="${lcProcess.lc_process_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">流程标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" maxlength="4" value="1"  name="lc_process_flag" placeholder="请输入标识:0通过平台设计器设计1通过上传部署">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入流程标题"  name="lc_process_title" placeholder="请输入流程标题" value="${lcProcess.lc_process_title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="lc_process_status" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${lcProcess.lc_process_status eq 0}">selected</c:if> >待发布</option>
						<option value="1" <c:if test="${lcProcess.lc_process_status eq 1}">selected</c:if> >发布中</option>
						<option value="2" <c:if test="${lcProcess.lc_process_status eq 2}">selected</c:if> >已关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">模块</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" id="xt_constant_id_" value="${lcProcess.xt_constant_id }">
					<select class="form-control" name="xt_constant_id" id="xt_constant_id" placeholder="请选择"></select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">附件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachment" id="xt_attachment" value="${lcProcess.xt_attachment }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="lc_process_remark" placeholder="请输入备注">${lcProcess.lc_process_remark }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateLcProcess()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/lc-view/lc-process/lc-process-update.js"></script> 
</html>
