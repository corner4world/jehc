<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台招贤纳士详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>招贤纳士详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_recruitment_id"  placeholder="请输入主键" value="${cmsRecruitment.cms_recruitment_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">职位</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入职位"  name="post" placeholder="请输入职位" value="${cmsRecruitment.post }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">性别</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10"  data-bv-numeric data-bv-numeric-message="性别为数字类型"  name="sex" placeholder="请输入性别" value="${cmsRecruitment.sex }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">年龄范围</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="128"  name="age_from" placeholder="请输入年龄范围" value="${cmsRecruitment.age_from }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">待遇</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="treatment" placeholder="请输入待遇" value="${cmsRecruitment.treatment }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">语言要求</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="language" placeholder="请输入语言要求" value="${cmsRecruitment.language }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">工作地点</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="workplace" placeholder="请输入工作地点" value="${cmsRecruitment.workplace }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">有效期至</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="vld" placeholder="请输入有效期至" value="${cmsRecruitment.vld }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">学历要求</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="education" placeholder="请输入学历要求" value="${cmsRecruitment.education }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">招聘人数</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="11"  name="numbersH" placeholder="请输入招聘人数" value="${cmsRecruitment.numbersH }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0" <c:if test="${cmsRecruitment.status = 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsRecruitment.status = 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">详细描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  name="content" placeholder="请输入详细描述">${cmsRecruitment.content }</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="ctime"  placeholder="请选择时间" value="${cmsRecruitment.ctime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">最后修改时间</label>
				<div class="col-lg-6">
					<input class="form_datetime form-control" name="mtime"  placeholder="请选择时间" value="${cmsRecruitment.mtime }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建人</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="xt_userinfo_id" placeholder="请输入创建人" value="${cmsRecruitment.xt_userinfo_realName }">
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
<script type="text/javascript" src="../view/pc/cms-view/cms-recruitment/cms-recruitment-detail.js"></script> 
</html>
