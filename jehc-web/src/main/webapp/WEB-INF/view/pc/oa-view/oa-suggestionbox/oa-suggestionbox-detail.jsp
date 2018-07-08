<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>意见申诉详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>意见申诉详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="oa_suggestionbox_id"  placeholder="请输入主键" value="${oaSuggestionbox.oa_suggestionbox_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="oa_suggestionboxTitle" placeholder="请输入标题" value="${oaSuggestionbox.oa_suggestionboxTitle }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">意见内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="800"  name="oa_suggestionboxContent" placeholder="请输入意见内容">${oaSuggestionbox.oa_suggestionboxContent }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否公开</label>
				<div class="col-lg-6">
					<select class="form-control" name="oa_suggestionboxIsPub" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${oaSuggestionbox.oa_suggestionboxIsPub eq 0 }">selected</c:if> >是</option>
						<option value="1" <c:if test="${oaSuggestionbox.oa_suggestionboxIsPub eq 1 }">selected</c:if> >否</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control" name="oa_suggestionboxType" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${oaSuggestionbox.oa_suggestionboxType eq 0 }">selected</c:if> >签名方式</option>
						<option value="1" <c:if test="${oaSuggestionbox.oa_suggestionboxType eq 1 }">selected</c:if> >匿名</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">是否回复</label>
				<div class="col-lg-6">
					<select class="form-control" name="oa_suggestionboxType" placeholder="请选择">
						<option value="">请选择</option>
						<option value="0" <c:if test="${oaSuggestionbox.state eq 0 }">selected</c:if> >否</option>
						<option value="1" <c:if test="${oaSuggestionbox.state eq 1 }">selected</c:if> >是</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<fmt:formatDate value="${oaSuggestionbox.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">发送人</label>
				<div class="col-lg-6">
					${oaSuggestionbox.xt_userinfo_realName }
				</div>
			</div>
			<fieldset>
				<legend><font color=red>&nbsp;&nbsp;申诉回复</font></legend>
				<c:if test="${empty oaSuggestionboxReplyList }">
					
					<div class="form-group">
						<div class="col-lg-6">
							暂无回复
						</div>
					</div>
				</c:if>
				<c:forEach var="oaSuggestionboxReply" items="${oaSuggestionboxReplyList }">
					<div class="mt-4">
		                <div class="media mb-4 mt-1">
		                    <div class="media-body">
		                        <span class="pull-right"><fmt:formatDate value="${oaSuggestionboxReply.createtime }" pattern="yyyy-MM-dd HH:mm:ss"/></span>
		                        <h6 class="m-0">${oaSuggestionboxReply.xt_userinfo_realName }</h6>
		                        <small class="text-muted">${oaSuggestionboxReply.xt_userinfo_email }</small>
		                    </div>
		                </div>
		                <p><b>${oaSuggestionboxReply.oa_suggestionbox_replyContent }</b></p>
		            </div>
				</c:forEach>
			</fieldset>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-suggestionbox/oa-suggestionbox-detail.js"></script> 
</html>
