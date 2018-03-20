<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<%@ include file="/deng/include/inplugboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>员工信息表编辑页面</title>
</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>个人中心</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="row">
		        <div class="col-md-1">
		        	<label class="control-label">用户名称</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        	<label class="control-label">${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_name }</label>
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">性别</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input type="hidden" id="xt_userinfo_sex_" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_sex }">
		        		<select class="form-control" disabled="disabled" maxlength="32" id="xt_userinfo_sex"  style="width: 150px;" name="xt_userinfo_sex" placeholder="请选择" ></select>
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">出生年月</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<label class="control-label">${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_birthday }</label>
		       		</div>
		        </div>
		    </div>
			<div class="row">
		        <div class="col-md-1">
		        	<label class="control-label">真实姓名</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        	<label class="control-label">${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_realName }</label>
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">是否已婚</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input type="hidden" id="xt_userinfo_ismarried_" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_ismarried }">
		        		<select class="form-control" disabled="disabled" maxlength="32" id="xt_userinfo_ismarried"  style="width: 150px;" name="xt_userinfo_ismarried" placeholder="请选择"></select>
		      		</div>
		        </div>	
		        <div class="col-md-1">
		        	<label class="control-label">身份证号</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<label class="control-label">${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_card }</label>
		        	</div>
		        </div>
		    </div>
			<div class="row">
		        <div class="col-md-1">
		        	<label class="control-label">名族</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input type="hidden" id="xt_userinfo_nation_" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_nation }">
		        		<select class="form-control" disabled="disabled" maxlength="32" id="xt_userinfo_nation" name="xt_userinfo_nation" placeholder="请选择名族" ></select>
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">籍贯</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<label class="control-label">${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_origo }</label>
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">毕业学校</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input class="form-control" type="text" maxlength="64"  name="xt_userinfo_schoolName" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_schoolName }" placeholder="请输入毕业学校">
		        	</div>
		        </div>
		    </div>
			<div class="row">
		        <div class="col-md-1">
		        	<label class="control-label">联系电话</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input class="form-control" type="text" maxlength="30"  name="xt_userinfo_phone" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_phone }" placeholder="请输入联系电话">
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">移动电话</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input class="form-control" type="text" maxlength="20"  name="xt_userinfo_mobile" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_mobile }" placeholder="请输入移动电话">
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">其他电话</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input class="form-control" type="text" maxlength="20" name="xt_userinfo_ortherTel" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_ortherTel }" placeholder="请输入其他电话">
		        	</div>
		        </div>
		    </div>
			<div class="row">
		        <div class="col-md-1">
		        	<label class="control-label">备注</label>
		        </div>
		        <div class="col-md-11">
		        	<div class="form-group">
		        		<textarea class="form-control" cols="100" maxlength="200"  name="xt_userinfo_remark" placeholder="请输入备注">${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_remark }</textarea>
		        	</div>
		        </div>
		    </div>
			<div class="row">
		        <div class="col-md-1">
		        	<label class="control-label">地址</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input class="form-control" type="text" maxlength="255" name="xt_userinfo_address" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_address }" placeholder="请输入地址">
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">电子邮件</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input class="form-control" type="text" maxlength="50" style="width: 150px;" name="xt_userinfo_email" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_email }" placeholder="请输入电子邮件">
		        	</div>
		        </div>
		    </div>
		    <div class="row">
		        <div class="col-md-1">
		        	<label class="control-label">入职时间</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<label class="control-label">${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_intime }</label>
		       		</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">到期时间</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        	<label class="control-label">${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_contractTime }</label>
		        	</div>
		        </div>
		        <div class="col-md-1">
		        	<label class="control-label">QQ号码</label>
		        </div>
		        <div class="col-md-3">
		        	<div class="form-group">
		        		<input class="form-control" type="text" maxlength="12"  style="width: 150px;" name="xt_userinfo_qq" value="${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_qq }" placeholder="请输入qq号码">
		        	</div>
		        </div>
		    </div>
			<div class="form-group">
				&nbsp;&nbsp;&nbsp;&nbsp;<button type="button" class="btn green" onclick="updateXtUserinfo()">保存</button>
			</div>
		</form>
	</div>
</body>
<script type="text/javascript" src="../view/pc/xt-view/xt-userinfo/my-xt-userinfo-update.js"></script> 
</html>
