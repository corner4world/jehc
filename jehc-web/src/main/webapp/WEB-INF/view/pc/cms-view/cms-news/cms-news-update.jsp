<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台新闻编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑新闻</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_news_id"  placeholder="请输入主键" value="${cmsNews.cms_news_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入标题"  name="title" placeholder="请输入标题" value="${cmsNews.title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">图片</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="imgpath" id="imgpath" value="${cmsNews.imgpath }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="imgpath_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  data-bv-notempty data-bv-notempty-message="请输入内容"  name="content" placeholder="请输入内容">${cmsNews.content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0" <c:if test="${cmsNews.status = 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsNews.status = 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">新闻分类</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  data-bv-notempty data-bv-notempty-message="请输入新闻分类"  name="news_category_id" placeholder="请输入新闻分类" value="${cmsNews.news_category_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateCmsNews()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/cms-view/cms-news/cms-news-update.js"></script> 
</html>
