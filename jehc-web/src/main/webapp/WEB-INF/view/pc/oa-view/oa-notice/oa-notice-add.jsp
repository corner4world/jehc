<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>公告管理</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<div class="page-header">
					<h3>创建公告</h3>
				</div>
				<form class="form-horizontal" id="defaultForm" method="post">
					<input class="form-control" type="hidden" id="submitType" name="submitType" value="0">
					<div class="form-group">
						<label class="col-lg-3 control-label">标题</label>
						<div class="col-lg-4">
							<input class="form-control" type="text" name="oa_noticeTitle" placeholder="请输入标题">
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">工作内容</label>
						<div class="col-lg-4">
							<textarea class="form-control" rows="3" name="oa_noticeContent" placeholder="请输入工作内容"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">类型</label>
						<div class="col-lg-4">
							<select class="form-control show-tick cusp" data-style="btn-white" name="oa_noticeType" style="width: 200px;">
						    	<option value=''>请选择</option>
						    	<option value='1'>一般</option>
						    	<option value='2'>重要</option>
						    	<option value='3'>非常重要</option>
						    </select>
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label">附件</label>
						<div class="col-lg-4">
							<input class="form-control" type="hidden" name="xt_attachment_id" id="xt_attachment_id">
							<img src = "../deng/images/default/add_d.png" class="img" id="xt_attachment_id_pic" width="96" height="96">    
						</div>
					</div>
					<div class="form-group">
						<label class="col-lg-3 control-label"></label>
						<div class="col-lg-4">
							<button type="button" class="btn green" onclick="addOaNotice()">保存</button>
							<button type="button" class="btn btn-warning" onclick="addOaNotice(1)">保存并提交</button>
							<button type="button" class="btn default" onclick="goback()">返回</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript" src="../view/pc/oa-view/oa-notice/oa-notice-add.js"></script>
</html>
