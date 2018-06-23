<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>关于我们详情页面</title>
<link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/4.0.0/plugins/summernote/summernote-bs4.css" rel="stylesheet" />
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>关于我们详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_about_id"  placeholder="请输入主键" value="${cmsAbout.cms_about_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入标题"  name="title" placeholder="请输入标题" value="${cmsAbout.title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-12">
					<div id="summernote">${cmsAbout.content }</div>
					<input type="hidden" name="content" id="content">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">照片</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="imgpath" id="imgpath" value="${cmsAbout.imgpath }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="imgpath_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0" <c:if test="${cmsAbout.status = 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsAbout.status = 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="ctime"  data-bv-notempty data-bv-notempty-message="请输入创建时间"  placeholder="请选择时间" value="${cmsAbout.ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">最后修改日期</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="mtime"  placeholder="请选择时间" value="${cmsAbout.mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  data-bv-notempty data-bv-notempty-message="请输入创建人"  name="xt_userinfo_id" placeholder="请输入创建人" value="${cmsAbout.xt_userinfo_id }">
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

<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/4.0.0/js/waves.js"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/4.0.0/plugins/summernote/summernote-bs4.min.js"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/4.0.0/plugins/summernote/lang/summernote-zh-CN.min.js"></script>
<script type="text/javascript" src="../view/pc/cms-view/cms-about/cms-about-detail.js"></script> 
<script>
    jQuery(document).ready(function(){
        $('#summernote').summernote({
            airMode: true
        });
    });
</script>

</html>
