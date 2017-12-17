<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础友情链接详情页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>友情链接详情</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_friendship_link_id"  placeholder="请输入编号" value="${bFriendshipLink.b_friendship_link_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="50"  data-bv-notempty data-bv-notempty-message="请输入名称"  name="b_friendship_link_name" placeholder="请输入名称" value="${bFriendshipLink.b_friendship_link_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">链接地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="500"  name="b_friendship_link_url" placeholder="请输入链接地址" value="${bFriendshipLink.b_friendship_link_url }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" style="width:150px;" name="b_friendship_link_status">
						<option>请选择</option>
						<option value="0" <c:if test="${bFriendshipLink.b_friendship_link_status eq 0}">selected</c:if>>正常</option>
						<option value="1" <c:if test="${bFriendshipLink.b_friendship_link_status eq 1}">selected</c:if>>禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">排&nbsp&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="排序为数字类型"  name="b_friendship_link_sort" placeholder="请输入排序" value="${bFriendshipLink.b_friendship_link_sort }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创建时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bFriendshipLink.b_friendship_link_ctime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">修改时间</label>
				<div class="col-lg-6">
					<label class="control-label"><fmt:formatDate value="${bFriendshipLink.b_friendship_link_mtime }" pattern="yyyy-MM-dd HH:mm:ss"/></label>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">创&nbsp;建&nbsp;&nbsp;人</label>
				<div class="col-lg-6">
					<label class="control-label">${bFriendshipLink.xt_userinfo_realName }</label>
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
<script type="text/javascript" src="../view/pc/b-view/b-friendship-link/b-friendship-link-detail.js"></script> 
</html>
