<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/include.jsp"%>
<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html lang="en">
    <!--<![endif]-->
    <!-- BEGIN HEAD -->
    <head>
        <meta charset="utf-8" />
        <title>${sys_pt_index_top}</title>
        <link rel="icon" type="image/ico" href="${syspath }/deng/images/icons/system.png" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-addtab/css/bootstrap.addtabs.css" rel="stylesheet" type="text/css" />
        <!-- END GLOBAL MANDATORY STYLES -->
        <!-- BEGIN THEME GLOBAL STYLES -->
        <link href="${syspath}/deng/source/plugins/admin/index/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- END THEME GLOBAL STYLES -->
        <!-- BEGIN THEME LAYOUT STYLES -->
        <link href="${syspath}/deng/source/plugins/admin/index/layouts/layout2/css/layout.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/layouts/layout2/css/themes/blue.min.css" rel="stylesheet" type="text/css" id="style_color" />
        <link href="${syspath}/deng/source/plugins/admin/index/layouts/layout2/css/custom.min.css" rel="stylesheet" type="text/css" />
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
    	var xt_userinfo_id; 
		</script>
	</head>
    <!-- END HEAD -->
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid">
        <!-- BEGIN HEADER -->
        <div class="page-header navbar navbar-fixed-top">
            <!-- BEGIN HEADER INNER -->
            <div class="page-header-inner ">
                <!-- BEGIN LOGO -->
                <div class="page-logo">
               		<%-- <div style="position:absolute;width:100px;height:65px;text-align:center;line-height:65px;">
                		<font style="text-align:center;font-weight: 700;font-size: 20px;" color="#ffffff">${sys_pt_index}</font>
                	</div>  --%>
                	
                    <a href="index.html">
                    	<img src="${syspath}/deng/images/logo/logo.png" alt="logo" class="logo-default" /> 
                    </a>
                    <div class="menu-toggler sidebar-toggler">
                        <!-- DOC: Remove the above "hide" to enable the sidebar toggler button on header -->
                    </div>
                </div>
                <!-- END LOGO -->
                
                <!-- BEGIN PAGE TOP -->
                <div class="page-top">
                    <!-- BEGIN HEADER SEARCH BOX -->
                    <!-- DOC: Apply "search-form-expanded" right after the "search-form" class to have half expanded search box -->
                    <form class="search-form search-form-expanded" method="GET">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="请输入关键字..." id="keywords" name="keywords">
                            <span class="input-group-btn">
                                <a href="javascript:search();" class="btn submit">
                                    <i class="icon-magnifier"></i>
                                </a>
                            </span>
                        </div>
                    </form>
                    <!-- END HEADER SEARCH BOX -->
                    <!-- BEGIN TOP NAVIGATION MENU -->
                    <div class="top-menu">
                        <ul class="nav navbar-nav pull-right">
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <img alt="" class="img-circle" src="${syspath}/deng/source/plugins/admin/index/layouts/layout2/img/avatar3_small.jpg" />
                                    <span class="username username-hide-on-mobile"> ${xtUserinfo.xt_userinfo_realName }</span>
                                    <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                    <li>
                                        <a href="javascript:updateUserPic()">
                                            <i class="icon-user"></i>修改头像
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:updatePwd()">
                                            <i class="icon-calendar"></i>修改密码 
                                        </a>
                                    </li>
                                    <li class="divider"> </li>
                                    <li>
                                        <a href="javascript:initLockSystem(1)">
                                            <i class="icon-lock"></i> 锁屏 </a>
                                    </li>
                                    <li>
                                        <a href="javascript:loginout()">
                                            <i class="icon-key"></i> 注销 </a>
                                    </li>
                                </ul>
                            </li>
                            <!-- END USER LOGIN DROPDOWN -->
                            <!-- BEGIN QUICK SIDEBAR TOGGLER -->
                            <li class="dropdown dropdown-extended quick-sidebar-toggler">
                                <span class="sr-only">Toggle Quick Sidebar</span>
                                <i class="icon-logout"></i>
                            </li>
                            <!-- END QUICK SIDEBAR TOGGLER -->
                        </ul>
                    </div>
                    <!-- END TOP NAVIGATION MENU -->
                </div>
                <!-- END PAGE TOP -->
            </div>
            <!-- END HEADER INNER -->
        </div>
        <!-- END HEADER -->
        <!-- BEGIN HEADER & CONTENT DIVIDER -->
        <div class="clearfix"> </div>
        <!-- END HEADER & CONTENT DIVIDER -->
        <!-- BEGIN CONTAINER -->
        <div class="page-container">
            <!-- BEGIN SIDEBAR -->
            <div class="page-sidebar-wrapper">
                <!-- END SIDEBAR -->
                <div class="page-sidebar navbar-collapse collapse">
                    <!-- BEGIN SIDEBAR MENU -->
                    <ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-hover-submenu " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                        <li class="nav-item start ">
                            <a href="javascript:clickAddTab('/index/desk.html','平台首页','home_index');" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">平台首页</span>
                            </a>
                        </li>
                        ${MenuList }
                    </ul>
                    <!-- END SIDEBAR MENU -->
                </div>
                <!-- END SIDEBAR -->
            </div>
            <!-- END SIDEBAR -->
            <!-- BEGIN CONTENT -->
            <div class="page-content-wrapper">
                <!-- BEGIN CONTENT BODY -->
                <div class="page-content">
                    <!-- BEGIN PAGE HEADER-->
                    <!-- BEGIN THEME PANEL -->
                    <div class="theme-panel">
                        <div class="toggler tooltips" data-container="body" data-placement="left" data-html="true" data-original-title="切换主题">
                            <i class="icon-settings"></i>
                        </div>
                        <div class="toggler-close">
                            <i class="icon-close"></i>
                        </div>
                        <div class="theme-options">
                            <div class="theme-option theme-colors clearfix">
                                <span> 主题 </span>
                                <ul>
                                    <li class="color-default current tooltips" data-style="default" data-container="body" data-original-title="默认"> </li>
                                    <li class="color-grey tooltips" data-style="grey" data-container="body" data-original-title="灰色"> </li>
                                    <li class="color-blue tooltips" data-style="blue" data-container="body" data-original-title="蓝色"> </li>
                                    <li class="color-dark tooltips" data-style="dark" data-container="body" data-original-title="棕黑色"> </li>
                                    <li class="color-light tooltips" data-style="light" data-container="body" data-original-title="亮黑色"> </li>
                                </ul>
                            </div>
                            
                            <div class="theme-option">
                                <span> 侧边样式</span>
                                <select class="sidebar-style-option form-control input-small">
                                    <option value="default" selected="selected">默认</option>
                                    <option value="compact">左侧对其</option>
                                </select>
                            </div>
                            <div class="theme-option">
                                <span> 边栏菜单 </span>
                                <select class="sidebar-menu-option form-control input-small">
                                    <option value="accordion" selected="selected">手风琴</option>
                                    <option value="hover">鼠标移入</option>
                                </select>
                            </div>
                            <div class="theme-option">
                                <span> 侧栏位置 </span>
                                <select class="sidebar-pos-option form-control input-small">
                                    <option value="left" selected="selected">左侧</option>
                                    <option value="right">右侧</option>
                                </select>
                            </div>
                            <div class="theme-option">
                                <span> 页脚 </span>
                                <select class="page-footer-option form-control input-small">
                                    <option value="fixed">固定</option>
                                    <option value="default" selected="selected">默认</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <!-- END THEME PANEL -->
                    <div class="main">
	                    <div id="tabs">
		                    <!-- Nav tabs -->
		                    <ul class="nav nav-tabs" role="tablist">
		                        <li class="active">
		                            <!-- <a href="#home" aria-controls="home" role="tab" data-toggle="tab">平台首页</a> -->
		                        </li>
		                    </ul>
		                    <!-- Tab panes -->
		                    <div class="tab-content">
		                       <!-- <div role="tabpanel" class="tab-pane active" id="home"></div> -->
		                    </div>
		                </div>
	                </div>
                    <!-- END PAGE HEADER-->
                    
                <!-- END CONTENT BODY -->
            </div>
            <!-- END CONTENT -->
            
        </div>
        <!-- END CONTAINER -->
        <!-- BEGIN FOOTER -->
        <div class="page-footer">
            <div class="page-footer-inner"> 
				${sys_pt_index_foot}
            </div>
            <div class="scroll-to-top">
                <i class="icon-arrow-up"></i>
            </div>
        </div>
        <!-- END FOOTER -->
        <!--[if lt IE 9]>
		<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/respond.min.js"></script>
		<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/excanvas.min.js"></script> 
		<![endif]-->
        <!-- BEGIN CORE PLUGINS -->
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/js.cookie.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
        <!-- END CORE PLUGINS -->
        <!-- BEGIN PAGE LEVEL PLUGINS -->
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL PLUGINS -->
        <!-- BEGIN THEME GLOBAL SCRIPTS -->
        <script src="${syspath}/deng/source/plugins/admin/index/global/scripts/app.min.js" type="text/javascript"></script>
        <!-- END THEME GLOBAL SCRIPTS -->
        <!-- BEGIN PAGE LEVEL SCRIPTS -->
        <script src="${syspath}/deng/source/plugins/admin/index/apps/scripts/inbox.min.js" type="text/javascript"></script>
        <!-- END PAGE LEVEL SCRIPTS -->
        <!-- BEGIN THEME LAYOUT SCRIPTS -->
        <script src="${syspath}/deng/source/plugins/admin/index/layouts/layout2/scripts/layout.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/layouts/layout2/scripts/demo.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/layouts/global/scripts/quick-sidebar.min.js" type="text/javascript"></script>
        <!-- END THEME LAYOUT SCRIPTS -->
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-addtab/js/bootstrap.addtabs.js" type="text/javascript"></script>
   		<style type="text/css">
   			/* .nav-tabs {border-bottom: 1px solid #4d5b69;} */
			/* .page-container-bg-solid .page-content {background: #4d5b69;} */
			.nav-pills>li>a, .nav-tabs>li>a {
			    font-size: 16px;
			}
			.page-sidebar-menu.page-sidebar-menu-hover-submenu>li:hover>.sub-menu>li .sub-menu {
			    margin-left: 194px;
			    margin-top: -41px!important;
			}
			.page-header.navbar .page-top {
			    box-shadow: 0 1px 0px 0 rgba(50,50,50,.2);
			}
			/* 
			.page-container-bg-solid {
			    background: #4d5b69;
			} 
			.x-body{background:#4d5b69;} 
			*/
   		</style>
    </body>
    <script type="text/javascript" src="${syspath}/view/pc/xt-view/xt-index/xt-admin.js"></script>
</html>
