<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>记事本编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑记事本</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="oa_note_id"  placeholder="请输入主键" value="${oaNote.oa_note_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">主题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="oa_noteTitle" placeholder="请输入主题" value="${oaNote.oa_noteTitle }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">分类</label>
				<div class="col-lg-6">
					<select class="form-control" name="oa_note_classify_id" placeholder="请选择">
						<option value="">请选择</option>
						<c:forEach var="OaNoteClassify" items="${OaNoteClassifyList }">
							<option value="${OaNoteClassify.oa_note_classify_id }" <c:if test="${oaNote.oa_note_classify_id eq OaNoteClassify.oa_note_classify_id}">selected</c:if>>${OaNoteClassify.oa_note_classify_name }</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="8000"  name="content" placeholder="请输入内容">${oaNote.content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">开始时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="begtime"  placeholder="请选择时间" value="<fmt:formatDate value="${oaNote.begtime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">结束时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="endtime"  placeholder="请选择时间" value="<fmt:formatDate value="${oaNote.endtime }" pattern="yyyy-MM-dd HH:mm:ss"/>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateOaNote()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-note/oa-note-update.js"></script> 
</html>
