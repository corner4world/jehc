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
			<h4><font color=red>意见申诉详情</font></h4>
		</div>
		<form class="form-horizontal" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="oa_suggestionbox_id"  placeholder="请输入主键" value="${oaSuggestionbox.oa_suggestionbox_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">
				标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题：${oaSuggestionbox.oa_suggestionboxTitle }
				</label>
			</div>
			<div class="row">
				<label class="col-lg-3 control-label">
				意见内容:
				<textarea class="form-control select_boot"  maxlength="800"  name="oa_suggestionboxContent" placeholder="请输入意见内容">${oaSuggestionbox.oa_suggestionboxContent }</textarea>
				</label>
			</div>
			<div class="row">
				<div class="col-md-2">
					<div class="form-group">
		        	<label class="control-label">
		        		是否公开：
		        		<c:if test="${oaSuggestionbox.oa_suggestionboxIsPub eq 0 }">是</c:if>
		        		<c:if test="${oaSuggestionbox.oa_suggestionboxIsPub eq 1 }">否</c:if>
		        	</label>
		        	</div>
		        </div>
		        <div class="col-md-2">
		        	<div class="form-group">
		        	 <label class="control-label">
		        	 	是否回复：
		        	 	<c:if test="${oaSuggestionbox.state eq 0 }">否</c:if>
		        		<c:if test="${oaSuggestionbox.state eq 1 }">是</c:if>
		        	 </label>
		        	</div>
		        </div>
		        <div class="col-md-2">
		         	<div class="form-group">
		        	 <label class="control-label">
	        	 		类型：
	        	 		<c:if test="${oaSuggestionbox.oa_suggestionboxType eq 0 }">签名方式</c:if>
		        	 	<c:if test="${oaSuggestionbox.oa_suggestionboxType eq 1 }">匿名</c:if>
		        	 </label>
		        	</div>
		        </div>
	        </div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间：<fmt:formatDate value="${oaSuggestionbox.createTime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">发&nbsp;&nbsp;送&nbsp;&nbsp;人：
					<c:choose>
						<c:when test="${oaSuggestionbox.oa_suggestionboxType eq 1 }">保密</c:when>
						<c:otherwise>${oaSuggestionbox.xt_userinfo_realName }</c:otherwise>
					</c:choose>
				</label>
			</div>
		</form>
		<fieldset>
			<legend><font color=red>&nbsp;&nbsp;申诉回复</font></legend>
			<div class="portlet box green" style="margin-bottom: 5px">
				<div class="portlet-body form" style="display: none;">
					<form method="POST" id="searchForm" class="form-inline" style="padding: 5px 0px 5px 0px;">
						<div class="form-group">
							<input type="text" class="form-control" name="oa_suggestionbox_id" value="${oaSuggestionbox.oa_suggestionbox_id }" id="oa_suggestionbox_id" placeholder="请输入外键">
						</div>
					</form>
				</div>
			</div>
			<div class="pull-left form-actions" style="margin-right:0px;margin-bottom: 5px">
				<button class="btn btn-outline-success waves-light waves-effect" onclick="toOaSuggestionboxReplyAdd()">
					<i class="fa fa-plus-circle"></i>新增
				</button>
				<button class="btn btn-outline-warning waves-light waves-effect" onclick="search('datatables')">
					<i class="fa fa-spin fa-refresh"></i>刷新
				</button>
			</div>
			<table id="datatables" class="table table-bordered table-striped table-hover" style="white-space: nowrap; width: 99.9%">
				<thead>
					<tr>
						<th><label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" class="checkall" /><span></span></label></th>
						<th>序号</th>
						<th>回复人</th>
						<th>复回内容</th>
						<th>回复时间</th>
						<th>操作</th>
					</tr>
				</thead>
			</table>
		</fieldset>
		<div class="form-group">
			<label class="col-lg-3 control-label"></label>
			<div class="col-lg-6">
				<button type="button" class="btn default" onclick="goback()">返回</button>
			</div>
		</div>
	</div>
	
	
	
	
	<!-- 回复模态框（Modal）开始 -->
	<div class="modal fade" id="addOaSuggestionboxReplyModal" tabindex="-1" role="dialog" aria-labelledby="addOaSuggestionboxReplyModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="addOaSuggestionboxReplyModalLabel">
						回复信息
					</h4>
				</div>
				<div class="modal-body">
					<form id="addOaSuggestionboxReplyForm" class="form-horizontal" method="post">
						<div class="form-group">
						<input class="form-control" type="hidden" name="oa_suggestionbox_id" value="${oaSuggestionbox.oa_suggestionbox_id }">
						<textarea rows="5" cols="100" class="form-control" maxlength="800" name="oa_suggestionbox_replyContent" placeholder="请输入复回内容"></textarea>
	               		</div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	                <button type="button" class="btn btn-primary" onclick="doAddOaSuggestionboxReply()">保存</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 回复模态框（Modal）结束 -->
	
	
	
	<!-- 回复详情模态框（Modal）开始 -->
	<div class="modal fade" id="detailOaSuggestionboxReplyModal" tabindex="-1" role="dialog" aria-labelledby="detailOaSuggestionboxReplyModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="detailOaSuggestionboxReplyModalLabel">
						回复信息
					</h4>
				</div>
				<div class="modal-body">
					<form id="detailOaSuggestionboxReplyForm" class="form-horizontal" method="post">
						<div class="form-group">
						<input class="form-control" type="hidden" name="oa_suggestionbox_id" value="${oaSuggestionbox.oa_suggestionbox_id }">
						<textarea rows="5" cols="100" class="form-control select_boot" maxlength="800" id="oa_suggestionbox_replyContent"></textarea>
	               		</div>
	                </form>
				</div>
				<div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- 回复详情模态框（Modal）结束 -->
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-suggestionbox-reply/oa-suggestionbox-reply-detail.js"></script> 
</html>
