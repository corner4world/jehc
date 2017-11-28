<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>流程详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>流程详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">流程编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="lc_process_id"  placeholder="请输入流程编号" value="${lcProcess.lc_process_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">打包后的ZIP路径</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="lc_process_path" placeholder="请输入打包后的ZIP路径" value="${lcProcess.lc_process_path }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">流程定义中id（uuid）</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="lc_process_uid" placeholder="请输入流程定义中id（uuid）" value="${lcProcess.lc_process_uid }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">流程uk（键）</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="lc_process_uk" placeholder="请输入流程uk（键）" value="${lcProcess.lc_process_uk }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">bpmn xml内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="2147483647"  name="lc_process_bpmn" placeholder="请输入bpmn xml内容">${lcProcess.lc_process_bpmn }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">流程标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入流程标题"  name="lc_process_title" placeholder="请输入流程标题" value="${lcProcess.lc_process_title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">bpmn文件路径</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="lc_process_bpmn_path" placeholder="请输入bpmn文件路径" value="${lcProcess.lc_process_bpmn_path }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片路径</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="lc_process_img_path" placeholder="请输入图片路径" value="${lcProcess.lc_process_img_path }">
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
				<label class="col-lg-3 control-label">标识</label>
				<div class="col-lg-6">
				<label class="control-label">
					<c:if test="${lcProcess.lc_process_flag eq 0}">通过平台设计器设计</c:if>
					<c:if test="${lcProcess.lc_process_flag eq 1}">通过上传部署</c:if>
				</label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">mxgraphxml字符串</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="2147483647"  name="lc_process_mxgraphxml" placeholder="请输入mxgraphxml字符串">${lcProcess.lc_process_mxgraphxml }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">样式风格</label>
				<div class="col-lg-6">
				<label class="control-label">
					<c:if test="${lcProcess.lc_process_mxgraph_style eq 0}">直线</c:if>
					<c:if test="${lcProcess.lc_process_mxgraph_style eq 1}">曲线</c:if>
				</label>
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
				<label class="col-lg-3 control-label">最后修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="lc_process_mtime"  placeholder="请选择时间" value="${lcProcess.lc_process_mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="lc_process_ctime"  placeholder="请选择时间" value="${lcProcess.lc_process_ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人" value="${lcProcess.xt_userinfo_realName }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/lc-view/lc-process/lc-process-detail.js"></script> 
</html>
