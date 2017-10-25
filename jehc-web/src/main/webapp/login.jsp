<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<!-- IE 浏览作用 兼容模式打开页面。 chrome=1 开启 chrome 支持，适用多个核的浏览器。 -->
<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
<meta charset="UTF-8">  
<title>${sys_pt_login }</title>  
<link rel="icon" type="image/ico" href="${syspath}/deng/images/icons/system.png" />
<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="format-detection" content="telephone=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/classic/theme-crisp-touch/resources/theme-crisp-touch-all.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${syspath}/deng/source/css/index.css" />
<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ext-all.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/classic/locale/locale-zh_CN.js"></script>
<script type="text/javascript" src="${syspath}/deng/source/js/common.js"></script>
<script type="text/javascript">
   	var basePath = "${syspath}";
   	var sys_pt_login = "${sys_pt_login}";
   	var sys_pt_index = "${sys_pt_index}";
   	var sys_pt_index_foot = "${sys_pt_index_foot}";
   	var sys_pt_index_top = "${sys_pt_index_top}";
   	var sys_pt_session = "${sys_pt_session}";
   	var sys_pt_user_name = "${xtUserinfo.xt_userinfo_realName}";
   	var moretext = "${moretext}";
   	var moretexttooltip = "${moretexttooltip}";
   	var grid_toolbar_gaps = "${grid_toolbar_gaps}";
   	var grid_toolbar_moretext_gaps = "${grid_toolbar_moretext_gaps}";
</script>
<style type="text/css">
	.x-form-text-default {
		color: #000000;
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
		background-image:url(${syspath}/deng/images/login/user.png)!important;
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
		background-image:url(${syspath}/deng/images/login/password.png)!important;
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
	.x-panel-header-defined{
	    background-image:none;
	    background-color:#32c5d2;
	}
	.x-btn-default-toolbar-large {
	    -webkit-border-radius: 0px;
	    -moz-border-radius: 0px;
	    -ms-border-radius: 0px;
	    -o-border-radius: 0px;
	}
</style> 
</head>  
<body>
	<div id="loading-mask" style="width:100%;height:100%;background:#f1f1f1;position:absolute;z-index:20000;left:0;top:0;">&nbsp;</div> 
	<div id="loading">
		<div class="loading-indicator">
			<div>
				欢迎使用业务平台
			</div>
			<img src="${syspath}/deng/images/load/loading.gif" width="18" height="18" style="margin-right: 5px;" align="absmiddle" />
			<span id="load-status" style="font-weight: normal;filter:alpha(opacity=70);">正在拼命加载平台初始化页面...</span>
			<br/>
			<span style="font-weight: normal;">请稍候</span>
		</div>
	</div>
</body>  
<script type="text/javascript" src="${syspath}/view/pc/xt-view/xt-login/xt-login.js"></script> 
<script type="text/javascript">Ext.get('load-status').update('正在拼命加载平台登录页面...')</script>
<link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/packages/shared/example.css" type="text/css" />
<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/packages/shared/examples.js"></script>
<style>
	.x-body {
    	background: #f5f5f5!important;
	}
	.x-btn-default-large {
	    border-color: #FFFFFF;
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
	    background-color: #32c5d2;
	}
	.x-panel-header-default-horizontal.x-header-noborder {
	    border-radius: 5px 5px 0px 0px;
	}
	
	.x-panel-body-default {
	    border-radius: 0px 0px 5px 5px;
	}
</sty>
</html> 