<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公告新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建公告</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<input class="form-control" type="hidden" id="submitType" name="submitType" value="0">
			<div class="form-group">
				<label class="col-lg-3 control-label">公告题标</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入公告题标"  name="oa_noticeTitle" placeholder="请输入公告题标">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">告公内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  data-bv-notempty data-bv-notempty-message="请输入告公内容"  name="oa_noticeContent" placeholder="请输入告公内容"></textarea>
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
				<label class="col-lg-3 control-label">附件</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="xt_attachement_id" id="xt_attachement_id">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xt_attachement_id_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addOaNotice()">保存</button>
					<button type="button" class="btn btn-warning" onclick="addOaNotice(1)">保存并提交</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-notice/oa-notice-add.js"></script> 
</html>
