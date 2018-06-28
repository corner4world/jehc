<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台幻灯片详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>幻灯片详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_slide_id"  placeholder="请输入主键" value="${cmsSlide.cms_slide_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="128"  name="title" placeholder="请输入标题" value="${cmsSlide.title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="imgpath" id="imgpath" value="${cmsSlide.imgpath }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="imgpath_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">链接地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="href" placeholder="请输入链接地址" value="${cmsSlide.href }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control select_boot" name="status" >
						<option value="0" <c:if test="${cmsSlide.status eq 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsSlide.status eq 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">备注</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="255"  name="content" placeholder="请输入备注">${cmsSlide.content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<fmt:formatDate value="${cmsSlide.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<fmt:formatDate value="${cmsSlide.mtime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					${cmsAbout.xt_userinfo_realName }
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
<script type="text/javascript" src="../view/pc/cms-view/cms-slide/cms-slide-detail.js"></script> 
</html>
