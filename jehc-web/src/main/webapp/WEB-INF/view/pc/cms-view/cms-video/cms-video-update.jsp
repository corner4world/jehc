<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台视频编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑视频</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_video_id"  placeholder="请输入主键" value="${cmsVideo.cms_video_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入标题"  name="title" placeholder="请输入标题" value="${cmsVideo.title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="imgPath" id="imgPath" value="${cmsVideo.imgPath }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="imgPath_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">视频</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="videoPath" id="videoPath" value="${cmsVideo.videoPath }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="videoPath_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0" <c:if test="${cmsProduct.status eq 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsProduct.status eq 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="1024"  name="content" placeholder="请输入内容">${cmsVideo.content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateCmsVideo()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/cms-view/cms-video/cms-video-update.js"></script> 
</html>
