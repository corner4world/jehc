<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台新闻分类详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>新闻分类详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_news_categroy_id"  placeholder="请输入主键" value="${cmsNewsCategroy.cms_news_categroy_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">分类名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入分类名称"  name="name" placeholder="请输入分类名称" value="${cmsNewsCategroy.name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="255"  name="content" placeholder="请输入描述">${cmsNewsCategroy.content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control select_boot" name="status" >
						<option value="0" <c:if test="${cmsNewsCategroy.status eq 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsNewsCategroy.status eq 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<fmt:formatDate value="${cmsNewsCategroy.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">最后修改时间</label>
				<div class="col-lg-6">
					<fmt:formatDate value="${cmsNewsCategroy.ctime }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					${cmsNewsCategroy.xt_userinfo_realName }
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
<script type="text/javascript" src="../view/pc/cms-view/cms-news-categroy/cms-news-categroy-detail.js"></script> 
</html>
