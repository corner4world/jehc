<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础会员新增页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>创建基础会员</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group">
				<label class="col-lg-3 control-label">会员名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  data-bv-notempty data-bv-notempty-message="请输入会员名称"  name="b_member_name" placeholder="请输入会员名称">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_member_tel" placeholder="请输入电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_member_status" >
						<option>请选择</option>
						<option value="0">正常</option>
						<option value="1">禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级为数字类型"  name="b_member_level" placeholder="请输入等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</label>
				<div class="col-lg-6">
					<select class="form-control" id="xt_province_id_0" name="xt_provinceID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</label>
				<div class="col-lg-6">
					<select class="form-control" id="xt_city_id_0" name="xt_cityID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县</label>
				<div class="col-lg-6">
					<select class="form-control" id="xt_district_id_0" name="xt_districtID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">详细地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_member_address" placeholder="请输入详细地址">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_member_sex" >
						<option>请选择</option>
						<option value="0">男</option>
						<option value="1">女</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="b_member_pwd" placeholder="请输入密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="b_member_email" placeholder="请输入邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">q&nbsp;q&nbsp;&nbsp;账号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="16"  name="b_member_qq" placeholder="请输入q&nbsp;q&nbsp;&nbsp;账号">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">微博账号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_member_wb" placeholder="请输入微博账号">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_member_type" >
						<option>请选择</option>
						<option value="0">普通会员</option>
						<option value="1">VIP会员</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="addBMember()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/b-view/b-member/b-member-add.js"></script> 
</html>
