<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>基础会员编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑基础会员</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">会员编号</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="b_member_id"  placeholder="请输入会员编号" value="${bMember.b_member_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">会员名称</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  data-bv-notempty data-bv-notempty-message="请输入会员名称"  name="b_member_name" placeholder="请输入会员名称" value="${bMember.b_member_name }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_member_tel" placeholder="请输入电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话" value="${bMember.b_member_tel }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_member_status" >
						<option>请选择</option>
						<option value="0" <c:if test="${bMember.b_member_status eq 0 }">selected</c:if>>正常</option>
						<option value="1" <c:if test="${bMember.b_member_status eq 1 }">selected</c:if>>禁用</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级</label>
				<div class="col-lg-6">
					<input class="form-control" maxlength="10" value="0"  data-bv-numeric data-bv-numeric-message="等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级为数字类型"  name="b_member_level" placeholder="请输入等&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级" value="${bMember.b_member_level }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" id="xt_provinceID_" value="${bMember.xt_provinceID }">
					<select class="form-control" id="xt_province_id_0" name="xt_provinceID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">城&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;市</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" id="xt_cityID_" value="${bMember.xt_cityID }">
					<select class="form-control" id="xt_city_id_0" name="xt_cityID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">区&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;县</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" id="xt_districtID_" value="${bMember.xt_districtID }">
					<select class="form-control" id="xt_district_id_0" name="xt_districtID">
						<option value=''>请选择</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">详细地址</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="200"  name="b_member_address" placeholder="请输入详细地址" value="${bMember.b_member_address }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_member_sex" >
						<option>请选择</option>
						<option value="0" <c:if test="${bMember.b_member_sex eq 0 }">selected</c:if>>男</option>
						<option value="1" <c:if test="${bMember.b_member_sex eq 1 }">selected</c:if>>女</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="64"  name="b_member_pwd" placeholder="请输入密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码" value="${bMember.b_member_pwd }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="100"  name="b_member_email" placeholder="请输入邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱" value="${bMember.b_member_email }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">q&nbsp;q&nbsp;&nbsp;账号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="16"  name="b_member_qq" placeholder="请输入q&nbsp;q&nbsp;&nbsp;账号" value="${bMember.b_member_qq }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">微博账号</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="32"  name="b_member_wb" placeholder="请输入微博账号" value="${bMember.b_member_wb }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label>
				<div class="col-lg-6">
					<select class="form-control" name="b_member_type" >
						<option>请选择</option>
						<option value="0" <c:if test="${bMember.b_member_type eq 0 }">selected</c:if>>普通会员</option>
						<option value="1" <c:if test="${bMember.b_member_type eq 1 }">selected</c:if>>VIP会员</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateBMember()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/b-view/b-member/b-member-update.js"></script> 
</html>
