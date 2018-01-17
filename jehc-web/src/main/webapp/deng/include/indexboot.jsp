<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="jEhcPermissionTag" uri="/WEB-INF/tld/jEhcPermissionTag.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!--<![endif]-->
	    <!-- BEGIN HEAD -->
		<!-- IE 浏览作用 兼容模式打开页面。 chrome=1 开启 chrome 支持，适用多个核的浏览器。 -->
		<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
		<!-- 360 浏览器使用 webkit -->
		<meta name="renderer" content="webkit"/>
	   	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="icon" type="image/ico" href="${syspath }/deng/images/icons/system.png" />
		<input type="hidden" id="lc_apply_model_biz_id" value="${lc_apply_model_biz_id }"/>
		<input type="hidden" value="${xt_functioninfoMethod }" id="xtFunctionInfoBtnStr">
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
	    	var JSESSIONID;
	    	var collapsibleDefined=true;
	    	var tbarBtnMinWidth=0;
	    	var hideCollapseToolFlag=true;
	    	var lc_design_displaywin_for_edit = ${lc_design_displaywin_for_edit};
	    	var xt_userinfo_id='${xtUserinfo.xt_userinfo_id}'; 
		</script>
		<!-- 基础插件样式开始 -->
		<link href="${syspath}/deng/source/plugins/admin/index/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!--  
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        -->
        <!-- 基础插件样式结束 -->
        <link href="${syspath}/deng/source/plugins/admin/index/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
        <%-- 
        <link href="${syspath}/deng/source/plugins/admin/index/layouts/layout3/css/themes/red-sunglo.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="${syspath}/deng/source/plugins/admin/index/layouts/layout4/css/themes/light.min.css" rel="stylesheet" type="text/css" id="style_color" /> 
        --%>
        <link href="${syspath}/deng/source/plugins/admin/index/layouts/layout2/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="${syspath}/deng/source/plugins/admin/index/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
        <!--[if lt IE 9]>
		<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/respond.min.js"></script>
		<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
        <!-- 基础插件开始 -->
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        
        <%--
        <!-- 基础插件结束 -->
        <!-- 其他插件开始 
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js" type="text/javascript"></script>
        -->
        --%>
        <!-- 其他插件结束 -->
        <!-- 提示插件开始 -->
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootbox.js" type="text/javascript"></script>
        <!-- 提示插件结束 -->
        <script src="${syspath}/deng/source/plugins/admin/index/global/scripts/app.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script> 
        <script src="${syspath}/deng/source/plugins/admin/index/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
        <%--
        <!-- 
        <script src="${syspath}/deng/source/plugins/admin/index/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        --%>
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-addtab/css/bootstrap.addtabs.css" rel="stylesheet" type="text/css" />
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-addtab/js/bootstrap.addtabs.js" type="text/javascript"></script>
        <!-- bootstrap验证框架开始 -->
		<link rel="stylesheet" type="text/css" href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-validate/bootstrapValidator.css" />
		<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-validate/bootstrapValidator.js" type="text/javascript"></script>
		
		<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
		<!-- bootstrap验证框架结束 -->
		<!-- 公共模块包含上传控件，共同JS封装 -->
		<script type="text/javascript" src="${syspath}/deng/source/js/boot.js"></script>
		<style type="text/css">
		.page-boxed .page-footer .page-footer-inner, .page-footer-fixed .page-footer .page-footer-inner {
		    color: #ffffff;
		}
		.page-sidebar .page-sidebar-menu>li>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>li>a {
		    font-size: 14px;
		    font-weight: 300;
		    text-align: center;
		}
		.page-sidebar .page-sidebar-menu>li.open>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>li.open>a {
		    font-size: 14px;
		}
		.page-sidebar .page-sidebar-menu .sub-menu li>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu .sub-menu li>a {
		    font-size: 14px;
		}
		<%--
		/* 
		.page-footer-fixed .page-boxed .page-footer, .page-footer-fixed .page-footer {
		    background-color: #388E8E;
		}
		.page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu {
		    background: #23262E;
		}
		.page-header.navbar .page-top {
		    background: #23262E;
		}
		.page-sidebar .page-sidebar-menu {
		    background: #23262E; 
		}
		.page-header.navbar .search-form {
		    background: #23262E;
		}
		.page-header.navbar .page-logo {
		    background: #E26A6A!important;
		}
		
		*/
		--%>
		.page-header.navbar .page-logo {
		    background: #23262E!important
		}
		.page-header.navbar .page-top {
		    background: #23262E;
		}
		.page-header.navbar .search-form {
		    background: #23262E;
		} 
		.page-container-bg-solid .page-content {
		    background: #eef1f5;
		}
		.page-sidebar .page-sidebar-menu.page-sidebar-menu-compact>li>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu.page-sidebar-menu-compact>li>a {
		    min-height: 50px;
		    padding: 15px;
		} 
		.page-sidebar .page-sidebar-menu>li>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>li>a {
			<%--
		    /* border-top: 1px solid #23262E; */ 
		    --%>
		}
		.page-sidebar-fixed .page-sidebar-menu .sub-menu {
			<%--
		    background: #4E5465;
		    --%>
		}
		.page-sidebar .page-sidebar-menu .sub-menu>li.active>a, .page-sidebar .page-sidebar-menu .sub-menu>li.open>a, .page-sidebar .page-sidebar-menu .sub-menu>li:hover>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu .sub-menu>li.active>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu .sub-menu>li.open>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu .sub-menu>li:hover>a {
		    background: #009688!important;
		}
		.page-sidebar .page-sidebar-menu>li>a>i[class*=icon-], .page-sidebar .page-sidebar-menu>li>a>i[class^=icon-], .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>li>a>i[class*=icon-], .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>li>a>i[class^=icon-] {
		    color: #c3cee0;
		}
		<%--
		.page-sidebar .page-sidebar-menu>li>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>li>a {
		    color: #fff;
		}
		--%>
		.page-header-fixed .page-container {
		    margin-top: 43px;
		}
		.nav-pills, .nav-tabs {
		    margin-bottom: 0px; 
		}
		.row {
		    margin-left: -20px;
		    margin-right: -15px;
		}
		a:hover,a:focus{
			outline: none;
			text-decoration: none;
		}
		.nav-tabs{
			border: 0px solid #32404e;
		}
		.nav-tabs li{
			margin: 0;
		}
		.nav-tabs li a{
			font-size:14px;
			/* color:#1e232f;  */
			/* background:#fff; */
			/* margin:0; */
			/* 
			padding:10px 5px; 
			*/
			border-radius:0;
			border:none;
			/* border-right:1px solid #f5f5f5; */
			text-transform:capitalize;/**capitalize(不改变英文大小写) lowercase(改变为小写) uppercase（改变为小写）**/
			position:relative;
		}
		.nav-tabs li a:hover{
			border-top: none;
			border-bottom: none;
			border-right-color: #ddd;
		}
		.nav-tabs li.active a,
		.nav-tabs li.active a:hover{
			color: #fff;
			border: none;
			/* 
			background: #17C4BB; 
			*/
			border-right: 0px solid #ddd;
		}
		.nav-tabs li.active a:before{
			/* width: 58%;
			height: 4px; */
			/* background: #fff; */
			/* position: absolute;
			top: 0;
			left: 0;
			right: 0;
			margin: 0 auto; */
		}
		.nav-tabs li.active a:after{
			/* border-top: 10px solid #17C4BB; 
			border-left: 10px solid transparent;
			border-right: 10px solid transparent;
			position: absolute;
			bottom: -10px;
			left: 43%; */
		}
		.nav-tabs > li.active > a, .nav-tabs > li.active > a:focus, .nav-tabs > li.active > a:hover {
		    background-color: #ffffff;/**选项卡背景颜色#23262E**/
		    border: 1px solid #23262E;
		}
		.page-container-bg-solid .page-content {
		    background: #f5f5f5;
		}
		.page-sidebar-fixed:not(.page-footer-fixed) .page-footer {
		    background-color: #f5f5f5;
		}
		@media only screen and (max-width: 480px){
			.nav-tabs li{
				width: 100%;
				text-align: center;
			}
			.nav-tabs li.active a,
			.nav-tabs li.active a:after,
			.nav-tabs li.active a:hover{
				border: none;
			}
		}
		.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover {
		    color: #000000;
		    background-color: #ffffff;
		    border: 0px solid #f5f5f5;
		    border-bottom-color: transparent;
		    cursor: default;
		}
		.page-sidebar .page-sidebar-menu.page-sidebar-menu-compact>li>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu.page-sidebar-menu-compact>li>a {
		    min-height: 50px;
		    padding: 21px;
		}
		.nav>li>a {padding: 10px 20px;}
		.glyphicon-remove:before {
		    content: "\e014";
		    color: #dddddd;
		}
		/* [class*=" fa-"]:not(.fa-stack), [class*=" glyphicon-"], [class*=" icon-"], [class^=fa-]:not(.fa-stack), [class^=glyphicon-], [class^=icon-] {
		    line-height: 32px;
		} */
		.close-tab {
		    font-size: 14px;
		    padding-top: 10px;
		    padding-right:4px;
		    cursor: pointer;
		    color: #f5f5f5;
		}
		.page-sidebar .page-sidebar-menu.page-sidebar-menu-compact li>a>.arrow.open:before, .page-sidebar .page-sidebar-menu.page-sidebar-menu-compact li>a>.arrow:before, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu.page-sidebar-menu-compact li>a>.arrow.open:before, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu.page-sidebar-menu-compact li>a>.arrow:before {
		    margin-top: -41px;
		}
		.page-sidebar .page-sidebar-menu>li.active>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>li.active>a {
		    font-size: 14px;
		}
		.page-sidebar .page-sidebar-menu li>a>.arrow:before, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu li>a>.arrow:before {
		    font-size: 30px;
    		padding-top: 26px;
		    content: "\002b";
		}
		.page-sidebar .page-sidebar-menu li>a>.arrow.open:before, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu li>a>.arrow.open:before {
		    content: "\2212";
		}
		.nav-tabs li.active a, .nav-tabs li.active a:hover {
		    color: #000;
		}
		.modal.in .modal-dialog{-webkit-transform:translate(0,-50%);-ms-transform:translate(0,-50%);-o-transform:translate(0,-50%);transform:translate(0,-50%)}
		.modal-dialog{position:absolute;width:auto;margin:10px auto;left:0;right:0;top:50%;}
		@media (min-width:768px){.modal-dialog{width:768px}
		</style>
	</head>
</html>