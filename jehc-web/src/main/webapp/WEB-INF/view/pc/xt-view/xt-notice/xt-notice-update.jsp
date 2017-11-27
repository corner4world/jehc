<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>平台公告编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑平台公告</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_notice_id"  placeholder="请输入主键" value="${xtNotice.xt_notice_id }">
					<input class="form-control" type="hidden" id="xt_userinfo_id"  value="${xtNotice.xt_userinfo_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="120"  name="xt_title" placeholder="请输入标题" value="${xtNotice.xt_title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="xt_content" placeholder="请输入内容">${xtNotice.xt_content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="xt_state" placeholder="请选择类型">
						<option value="">请选择</option>
						<option value="0" <c:if test="${xtNotice.xt_state eq 0 }">selected</c:if>>初稿</option>
						<option value="1" <c:if test="${xtNotice.xt_state eq 1 }">selected</c:if>>审核提交</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">基本附件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id" value="${xtNotice.xt_attachment_id }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">其它附件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachment_id_" id="xt_attachment_id_" value="${xtNotice.xt_attachment_id_ }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachment_id__pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<jEhcPermissionTag:jehcBtnTag modules="updateXtNotice" systemUID="${xtNotice.xt_userinfo_id }">
						<button type="button" class="btn green" onclick="updateXtNotice()">保存</button>
					</jEhcPermissionTag:jehcBtnTag>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-notice/xt-notice-update.js"></script> 
</html>
