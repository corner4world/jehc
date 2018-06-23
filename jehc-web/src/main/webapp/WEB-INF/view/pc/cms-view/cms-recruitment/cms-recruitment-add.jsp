<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>内容发布平台招贤纳士新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建招贤纳士</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">职位</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入职位"  name="post" placeholder="请输入职位">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">性别</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="性别为数字类型"  name="sex" placeholder="请输入性别">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">年龄范围</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="128"  name="age_from" placeholder="请输入年龄范围">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">待遇</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="treatment" placeholder="请输入待遇">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">语言要求</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="language" placeholder="请输入语言要求">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">工作地点</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="workplace" placeholder="请输入工作地点">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">有效期至</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="vld" placeholder="请输入有效期至">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">学历要求</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  name="education" placeholder="请输入学历要求">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">招聘人数</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="11"  name="numbersH" placeholder="请输入招聘人数">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0">正常</option>
						<option value="1">关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">详细描述</label>
				<div class="col-lg-6">
					<textarea class="form-control" maxlength="65535"  name="content" placeholder="请输入详细描述"></textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addCmsRecruitment()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/cms-view/cms-recruitment/cms-recruitment-add.js"></script> 
</html>
