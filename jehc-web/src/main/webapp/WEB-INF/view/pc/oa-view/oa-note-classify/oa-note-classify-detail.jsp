<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>记事本分类详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>记事本分类详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="oa_note_classify_id"  placeholder="请输入主键" value="${oaNoteClassify.oa_note_classify_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">分类名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入分类名称"  name="oa_note_classify_name" placeholder="请输入分类名称" value="${oaNoteClassify.oa_note_classify_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<fmt:formatDate value="${oaNoteClassify.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">最后修改时间</label>
				<div class="col-lg-6">
					<fmt:formatDate value="${oaNoteClassify.mtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					${oaNoteClassify.xt_userinfo_realName }
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
<script type="text/javascript" src="../view/pc/oa-view/oa-note-classify/oa-note-classify-detail.js"></script> 
</html>
