<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>联系我们详情页面</title>
<link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/summernote/summernote-bs4.css" rel="stylesheet" />
<script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/js/waves.js"></script>
<script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/summernote/summernote-bs4.min.js"></script>
<script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/summernote/lang/summernote-zh-CN.min.js"></script>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>联系我们详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_contact_id"  placeholder="请输入主键" value="${cmsContact.cms_contact_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="imgpath" id="imgpath" value="${cmsContact.imgpath }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="imgpath_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-9">
					<div id="summernote">${cmsContact.content }</div>
					<input type="hidden" name="content" id="content">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control select_boot" name="status" >
						<option value="0" <c:if test="${cmsContact.status eq 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsContact.status eq 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control select_boot" name="ctime"  placeholder="请选择时间" value="${cmsContact.ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">最后修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control select_boot" name="mtime"  placeholder="请选择时间" value="${cmsContact.mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					
					<input class="form-control select_boot" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人" value="${cmsContact.xt_userinfo_id }">
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
<script type="text/javascript" src="../view/pc/cms-view/cms-contact/cms-contact-detail.js"></script> 
<script>
    jQuery(document).ready(function(){
        $('#summernote').summernote({
            airMode: true
        });
    });
</script>
</html>
