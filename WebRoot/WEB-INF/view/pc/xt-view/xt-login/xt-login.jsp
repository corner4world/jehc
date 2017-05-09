<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%
String path = request.getContextPath();
String rootMenu=(String)request.getAttribute("msg");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<style type="text/css">
.x-form-text-default {
color: #000;
padding: 3px 6px 2px;
background-color: #fff;
font: 300 15px/25px helvetica, arial, verdana, sans-serif;
min-height: 50px;
}
.x-btn-default-large {
    -webkit-border-radius: 3px;
    -moz-border-radius: 0px;
    -ms-border-radius: 3px;
    -o-border-radius: 0px;
    border-radius: 0px;
    padding: 5px 4px 5px 4px;
    border-width: 1px;
    border-style: solid;
    background-color: #3892d4;
}
.user-trigger {
	display: table-cell;
	vertical-align: top;
	cursor: pointer;
	overflow: hidden;
	background-position:right;
	background-image:url(../deng/images/login/user.png)!important;
	background-repeat: no-repeat;
	text-align: center;
	line-height: 0;
	white-space: nowrap;
	width:32px;
}
.user-trigger-over {
	background-position:right;
}  
.user-trigger-click {
	background-position:right;
}

.x-form-trigger-default.x-form-trigger-focus {
	background-position:right;
}
.x-form-trigger-default.x-form-trigger-over{
	background-position:right;
}
.x-form-trigger-default.x-form-trigger-over.x-form-trigger-focus {
background-position:right;
}
.x-form-trigger-default:before {
content: "";
}
.pwd-trigger {
	display: table-cell;
	vertical-align: top;
	cursor: pointer;
	overflow: hidden;
	background-position:right;
	background-image:url(../deng/images/login/password.png)!important;
	background-repeat: no-repeat;
	text-align: center;
	line-height: 0;
	white-space: nowrap;
	width:32px;
}
.pwd-trigger-over {
	background-position:right;
}  
.pwd-trigger-click {
	background-position:right;
}
.x-window-header-default {
border-width: 0px !important;
}
.x-window-header-default-top {
-moz-border-radius-topleft: 0;
-webkit-border-top-left-radius: 0;
border-top-left-radius: 0;
-moz-border-radius-topright: 0;
-webkit-border-top-right-radius: 0;
border-top-right-radius: 0;
-moz-border-radius-bottomright: 0;
-webkit-border-bottom-right-radius: 0;
border-bottom-right-radius: 0;
-moz-border-radius-bottomleft: 0;
-webkit-border-bottom-left-radius: 0;
border-bottom-left-radius: 0;
padding: 20px 14px 20px 14px;
border-width: 2px 2px 2px 2px;
border-style: solid;
background-color: #5fa2dd;
}
.x-window-header-title-default {
color: #fff;
}
</style>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>${sys_pt_login }</title>  
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script type="text/javascript" src="../view/pc/xt-view/xt-login/xt-login.js"></script>  
</head>  
<body>
	<div id="loading-mask" style="width:100%;height:100%;background:#f1f1f1;position:absolute;z-index:20000;left:0;top:0;">&nbsp;</div> 
	<div id="loading">
		<div class="loading-indicator">
			<div>
				欢迎使用业务平台
			</div>
			<img src="../deng/images/load/loading.gif" width="18" height="18" style="margin-right: 5px;" align="absmiddle" />
			<span id="load-status" style="font-weight: normal;filter:alpha(opacity=70);">正在拼命加载平台初始化页面...</span>
			<br/>
			<span style="font-weight: normal;">请稍候</span>
		</div>
	</div>
</body>  
<script type="text/javascript">Ext.get('load-status').update('正在拼命加载平台登录页面...')</script>
</html> 