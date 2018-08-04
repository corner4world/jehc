<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>流程详情页面</title>
</head>
<body>
	<div class="m-portlet">
		<div class="m-portlet__head">
			<div class="m-portlet__head-caption">
				<div class="m-portlet__head-title">
					<span class="m-portlet__head-icon m--hide">
					<i class="la la-gear"></i>
					</span>
					<h3 class="m-portlet__head-text">
						流程详情
					</h3>
				</div>
			</div>
		</div>
		<!--begin::Form-->
		<form class="m-form m-form--fit m-form--label-align-left m-form--group-seperator-dashed" id="defaultForm" method="post">
			<div class="m-portlet__body">
				<div class="form-group" style="display:none;">
					<label class="col-lg-2 control-label">流程编号</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="lc_process_id"  placeholder="请输入流程编号" value="${lcProcess.lc_process_id }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">流程标题</label>
					<div class="col-lg-6">
						${lcProcess.lc_process_title }
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">图片路径</label>
					<div class="col-lg-6">
						${lcProcess.lc_process_img_path }
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
					<div class="col-lg-6">
						<label class="control-label">
							<font color="orange">
								<c:choose>
									<c:when test="${lcProcess.lc_process_status eq 0}">待发布</c:when>
									<c:when test="${lcProcess.lc_process_status eq 1}">发布中</c:when>
									<c:when test="${lcProcess.lc_process_status eq 2}">已关闭</c:when>
									<c:otherwise>缺省</c:otherwise>
								</c:choose>
							</font>
						</label>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;识</label>
					<div class="col-lg-6">
					<label class="control-label">
						<c:if test="${lcProcess.lc_process_flag eq 0}">通过平台设计器设计</c:if>
						<c:if test="${lcProcess.lc_process_flag eq 1}">通过上传部署</c:if>
					</label>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">definitionsid</label>
					<div class="col-lg-6">
						<label class="control-label">
							<c:choose>
								<c:when test="${empty lcProcess.lc_process_uid }"><font color=red>未设置</font></c:when>
								<c:otherwise>${lcProcess.lc_process_uid }</c:otherwise>
							</c:choose>
						</label>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">definitionskey</label>
					<div class="col-lg-6">
						<label class="control-label">
							<c:choose>
								<c:when test="${empty lcProcess.lc_process_uk }"><font color=red>未设置</font></c:when>
								<c:otherwise>${lcProcess.lc_process_uk }</c:otherwise>
							</c:choose>
						</label>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">bpmn xml</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="2147483647"  rows="12" name="lc_process_bpmn" placeholder="请输入bpmn xml">${lcProcess.lc_process_bpmn }</textarea>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">bpmn文件路径</label>
					<div class="col-lg-6">
						<input class="form-control" type="text" maxlength="255"  rows="12" name="lc_process_bpmn_path" placeholder="请输入bpmn文件路径" value="${lcProcess.lc_process_bpmn_path }">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">mxgraphxml字符串</label>
					<div class="col-lg-6">
						<textarea class="form-control" maxlength="2147483647"  rows="12" name="lc_process_mxgraphxml" placeholder="请输入mxgraphxml字符串">${lcProcess.lc_process_mxgraphxml }</textarea>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">样式风格</label>
					<div class="col-lg-6">
					<label class="control-label">
						<font color="orange">
						<c:if test="${lcProcess.lc_process_mxgraph_style eq 0}">直线</c:if>
						<c:if test="${lcProcess.lc_process_mxgraph_style eq 1}">曲线</c:if>
						</font>
					</label>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" id="xt_constant_id_" value="${lcProcess.xt_constant_id }">
						<select class="form-control" name="xt_constant_id" id="xt_constant_id" placeholder="请选择"></select>
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件</label>
					<div class="col-lg-6">
						<input class="form-control" type="hidden" name="xt_attachment" id="xt_attachment" value="${lcProcess.xt_attachment }">
						<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_pic">
					</div>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label>
					<div class="col-lg-6">
						<textarea class="form-control select_boot" maxlength="800" rows="12" name="lc_process_remark" placeholder="请输入备注">${lcProcess.lc_process_remark }</textarea>
					</div>
				</div>
				<%-- 
				<div class="form-group m-form__group row">
					<label class="col-lg-8 control-label">最后修改时间&nbsp;<font color=red><fmt:formatDate value="${lcProcess.lc_process_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/></font></label>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-8 control-label">创建时间&nbsp;<font color=red><fmt:formatDate value="${lcProcess.lc_process_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/></font></label>
				</div> 
				--%>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">打包后的zip路径</label>
					<label class="col-lg-6 control-label">${lcProcess.lc_process_path }</label>
				</div>
				<div class="form-group m-form__group row">
					<label class="col-lg-2 control-label">创建人 &nbsp;<font color=red>${lcProcess.xt_userinfo_realName }</font></label>
					<div class="col-lg-6">
					</div>
				</div>
			</div>
            <div class="m-portlet__foot m-portlet__foot--fit">
				<div class="m-form__actions m-form__actions--right">
					<div class="row">
						<div class="col m--align-left">
						</div>
						<div class="col m--align-right">
							<button type="button" class="btn btn-secondary m-btn m-btn--custom m-btn--icon" onclick="goback()">返回</button>
						</div>
					</div>
				</div>
			</div>
		</form>
		<!--end::Form-->
	</div>
</body>
<script type="text/javascript" src="../view/pc/lc-view/lc-process/lc-process-detail.js"></script> 
</html>
