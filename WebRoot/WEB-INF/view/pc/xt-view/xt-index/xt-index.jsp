<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%
String path = request.getContextPath();
String rootMenu=(String)request.getAttribute("msg");
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
var menuJSON = "<%=rootMenu%>";
</script>
<style type="text/css">
.x-btn-default-large {
	-webkit-border-radius:0px;
	-moz-border-radius:0px;
	-ms-border-radius:0px;
	-o-border-radius:0px;
	border-radius:0px;
	padding: 5px 4px 5px 4px;
	border-width: 0px;
	border:0px;
}
.x-btn-default-small {
	-webkit-border-radius: 0px;
	-moz-border-radius: 0px;
	-ms-border-radius: 0px;
	-o-border-radius: 0px;
	border-radius: 0px;
	border-width: 0px;
	border-style: solid;
}
.x-btn-default-small {
	border-color: #fff;
}
.x-btn.x-btn-menu-active.x-btn-default-small, .x-btn.x-btn-pressed.x-btn-default-small {
	border-color: #fff;
	background-image: none;
}
.x-btn-focus.x-btn-default-small {
	background-image: none;
	-webkit-box-shadow: #fff 0 0px 0px 0 inset, #fff 0 0px 0px 0 inset, #fff 0px 0 0px 0 inset, #fff 0px 0 0px 0 inset;
	-moz-box-shadow: #fff 0 0px 0px 0 inset, #fff 0 0px 0px 0 inset, #fff 0px 0 0px 0 inset, #fff 1px 0 0px 0 inset;
	box-shadow: #fff 0 0px 0px 0 inset, #fff 0 0px 0px 0 inset, #fff 0px 0 0px 0 inset, #fff 0px 0 0px 0 inset;
}
/**
.x-panel-header-default-horizontal{
	padding:45px 15px 45px;
}
**/
.x-panel-header-definedbg{
    background-image:none;
    background-color:#35baf6;/**#35baf6**/
}
.x-btn-wrap-default-small.x-btn-arrow-right:after {
	width: 0px;
	content: "";
    padding-right: 0px;
}

.pwd{
	margin-left:15%;
	margin-top:15%;
	margin-bottom:15%;
	margin-right:15%;
	height:50px;
}
.fieldPwd{
	background-position:right;
	background-repeat:no-repeat;
	background-image:url(../deng/images/login/password.png)!important;
}
.x-grid-body {
    background: white;
    border-width: 1px;
    border-style: solid;
    border-color: #fff;
}

.x-grid-body {
    border-top-color: #f5f5f5;
}
/**
.x-panel-header-title-default {
    
**/
</style>
<!DOCTYPE HTML>
<html>
	<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>${sys_pt_index_top}</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-index/xt-index.js"></script>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-index/xt-message.js"></script>  
	<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ux/ppWin.js"></script>
	</head>  
	<body>  
		<div id="loading-mask" style="width:100%;height:100%;background:#f1f1f1;position:absolute;z-index:20000;left:0;top:0;"><br>&nbsp;</div> 
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
	<script type="text/javascript">
		var text = "Bootstrap Extjs6.2.0技术扁平化风格；<br>"+
				"采用平台统一监控如JVM，内存，CPU等；<br>"+
				"支持多数据库如Oracle，Mysql，DB2，Sqlserver等；<br>"+
				"公司，部门，岗位，组织机构，数据权限；<br>"+
				"附件上传如Html5，Extjs上传方式等；<br>"+
				"触屏端支持；<br>"+
				"简单易用，代码风格统一；<br>"+
				"支持菜单管理，平台路径配置，记录问题知识；<br>"+
				"动态调度器配置及使用；<br>"+
				"支持各类报表；<br>"+
				"拥有数据库管理工具；<br>"+
				"数据库信息，数据库库表结构，索引等；<br>"+
				"采用SSO统一认证平台实现登陆；<br>"+				
				"拥有OA，CRM等功能；<br>"+
				"短信配置；<br>"+
				"物品单位；<br>"+
				"省市区县；<br>"+
				"平台公告；<br>"+
				"平台分层结构清晰，代码易读，规范；<br>"+
				"同时拥有了电子商务基础（OTP）功能；<br>"+
				"支持客户关系平台，采用前端+后端技术；<br>"+
				"<font color=red>版本为：v1.0.0</font>";
		function init(){
			Ext.create('Ext.ux.Notification', {
	           title:'平台消息提醒',
	           iconCls:'ux-notification-icon-information',
	           position:'tc',/**br, bl, tr, tl, t, l, b, r  'bc', lc', 'rc', 'tc'**/
	           width:400,
	           height:450,
	           manager:'test',
	           stickWhileHover:false,
	           html:text
	        }).show(); 
		}
        setInterval(init,150000); 
	</script>
</html> 