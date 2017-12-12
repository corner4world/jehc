<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公告详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>公告详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">公告ID主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="oa_noticeID"  placeholder="请输入公告ID主键" value="${oaNotice.oa_noticeID }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">公告题标</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入公告题标"  name="oa_noticeTitle" placeholder="请输入公告题标" value="${oaNotice.oa_noticeTitle }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">告公内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  data-bv-notempty data-bv-notempty-message="请输入告公内容"  name="oa_noticeContent" placeholder="请输入告公内容">${oaNotice.oa_noticeContent }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类型</label>
				<div class="col-lg-6">
					<select class="form-control show-tick cusp" data-style="btn-white" name="oa_noticeType" style="width: 200px;">
				    	<option value=''>请选择</option>
				    	<option value='1' <c:if test="${oaNotice.oa_noticeType eq 1 }">selected</c:if>>一般</option>
				    	<option value='2' <c:if test="${oaNotice.oa_noticeType eq 2 }">selected</c:if>>重要</option>
				    	<option value='3' <c:if test="${oaNotice.oa_noticeType eq 3 }">selected</c:if>>非常重要</option>
				    </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control show-tick cusp" data-style="btn-white" name="oa_noticeType" style="width: 200px;">
				    	<option value=''>请选择</option>
				    	<option value='0' <c:if test="${oaNotice.status eq 0 }">selected</c:if>>草稿</option>
				    	<option value='1' <c:if test="${oaNotice.status eq 1 }">selected</c:if>>审核中</option>
				    	<option value='2' <c:if test="${oaNotice.status eq 2 }">selected</c:if>>审核通过</option>
				    	<option value='3' <c:if test="${oaNotice.status eq 3 }">selected</c:if>>审核未通过</option>
				    </select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">附件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachement_id" id="xt_attachement_id" value="${oaNotice.xt_attachement_id }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachement_id_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">点击量</label>
				<div class="col-lg-6">
					<input class="form-control" style="width:120px;" maxlength="10" value="${oaNotice.oa_notice_hits }" data-bv-numeric data-bv-numeric-message="点击量为数字类型"  name="oa_notice_hits" placeholder="请输入点击量" >
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<fmt:formatDate value="${oaNotice.oa_noticeCreateTime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					${oaNotice.xt_userinfo_realName }
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
<script type="text/javascript" src="../view/pc/oa-view/oa-notice/oa-notice-detail.js"></script> 
</html>
