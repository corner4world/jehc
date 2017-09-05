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
                <!-- BEGIN RESPONSIVE MENU TOGGLER -->
                <a href="javascript:;" class="menu-toggler responsive-toggler" data-toggle="collapse" data-target=".navbar-collapse"> </a>
                <!-- END RESPONSIVE MENU TOGGLER -->
                <!-- BEGIN PAGE ACTIONS -->
                <!-- DOC: Remove "hide" class to enable the page header actions -->
                <div class="page-actions">
                    <div class="btn-group">
                        <button type="button" class="btn btn-circle btn-outline red dropdown-toggle" data-toggle="dropdown">
                            <i class="fa fa-plus"></i>&nbsp;
                            <span class="hidden-sm hidden-xs">创建&nbsp;</span>&nbsp;
                            <i class="fa fa-angle-down"></i>
                        </button>
                        <ul class="dropdown-menu" role="menu">
                            <li>
                                <a href="javascript:;">
                                    <i class="icon-docs"></i> 新增我的日程 </a>
                            </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="icon-tag"></i> 新增我的计划 </a>
                            </li>
                            <li class="divider"> </li>
                            <li>
                                <a href="javascript:;">
                                    <i class="icon-flag"></i>我的消息
                                    <span class="badge badge-success">4</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                </div>
                <!-- END PAGE ACTIONS -->
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
                        	<li class="dropdown dropdown-extended dropdown-notification" id="header_notification_bar">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <i class="icon-bell"></i>
                                    <span class="badge badge-default"> 1 </span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="external">
                                        <h3>
                                            <span class="bold">1 个</span> 通知</h3>
                                        <a href="#">全部</a>
                                    </li>
                                    <li>
                                        <ul class="dropdown-menu-list scroller" style="height: 250px;" data-handle-color="#637283">
                                            <li>
                                                <a href="javascript:;">
                                                    <span class="time">刚才</span>
                                                    <span class="details">
                                                        <span class="label label-sm label-icon label-success">
                                                            <i class="fa fa-plus"></i>
                                                        </span> 新用户注册. </span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <!-- END NOTIFICATION DROPDOWN -->
                            <!-- BEGIN INBOX DROPDOWN -->
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                            <li class="dropdown dropdown-extended dropdown-inbox" id="header_inbox_bar">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <i class="icon-envelope-open"></i>
                                    <span class="badge badge-default"> 1 </span>
                                </a>
                                <ul class="dropdown-menu">
                                    <li class="external">
                                        <h3>您有
                                            <span class="bold">1 个</span> 消息</h3>
                                        <a href="#">显示全部</a>
                                    </li>
                                    <li>
                                        <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                                            <li>
                                                <a href="#">
                                                    <span class="photo">
                                                        <img src="${syspath}/deng/source/plugins/admin/index/layouts/layout2/img/avatar3_small.jpg" class="img-circle" alt=""> </span>
                                                    <span class="subject">
                                                        <span class="from"> 邓纯杰 </span>
                                                        <span class="time">刚刚 </span>
                                                    </span>
                                                    <span class="message">今天心情不错...</span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <!-- END INBOX DROPDOWN -->
                            <!-- BEGIN TODO DROPDOWN -->
                            <!-- DOC: Apply "dropdown-dark" class after below "dropdown-extended" to change the dropdown styte -->
                            <li class="dropdown dropdown-extended dropdown-tasks" id="header_task_bar">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <i class="icon-calendar"></i>
                                    <span class="badge badge-default"> 1 </span>
                                </a>
                                <ul class="dropdown-menu extended tasks">
                                    <li class="external">
                                        <h3>您有
                                            <span class="bold">1 个</span> 任务</h3>
                                        <a href="#">全部</a>
                                    </li>
                                    <li>
                                        <ul class="dropdown-menu-list scroller" style="height: 275px;" data-handle-color="#637283">
                                            <li>
                                                <a href="javascript:;">
                                                    <span class="task">
                                                        <span class="desc">任务进度 </span>
                                                        <span class="percent">30%</span>
                                                    </span>
                                                    <span class="progress">
                                                        <span style="width: 40%;" class="progress-bar progress-bar-success" aria-valuenow="40" aria-valuemin="0" aria-valuemax="100">
                                                            <span class="sr-only">40% 完成</span>
                                                        </span>
                                                    </span>
                                                </a>
                                            </li>
                                        </ul>
                                    </li>
                                </ul>
                            </li>
                            <!-- BEGIN QUICK SIDEBAR TOGGLER -->
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                   <span class="username username-hide-on-mobile"> 关闭选项卡</span>
                                   <i class="fa fa-angle-down"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-menu-default">
                                    <li>
                                        <a href="javascript:closeAllTab()">
                                            <i class="icon-trash"></i>关闭全部标签
                                        </a>
                                    </li>
                                    <li>
                                        <a href="javascript:closeCruTab()">
                                            <i class="icon-close"></i>关闭当前项
                                        </a>
                                    </li>
                                </ul>
                            </li>
                            <!-- END QUICK SIDEBAR TOGGLER -->
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
                        <!-- <li class="nav-item start ">
                            <a href="javascript:indexHome();" class="nav-link nav-toggle">
                                <i class="icon-home"></i>
                                <span class="title">平台首页</span>
                            </a>
                        </li> -->
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
		                        <li role="presentation" class="active">
		                            <a href="#home" aria-controls="home" role="tab" data-toggle="tab">平台首页</a>
		                        </li>
		                    </ul>
		                    <!-- Tab panes -->
		                    <div class="tab-content">
		                        <div role="tabpanel" class="tab-pane active" id="home">
		                        	<h3 class="page-title"> 开源平台
				                        <small>扁平化例子...</small>
				                    </h3>
		                        	<div class="portlet light ">
				                        <div class="portlet-title">
				                            <div class="caption">
				                                <span class="caption-subject font-green-sharp sbold">快捷入口</span>
				                            </div>
				                            <div class="actions">
				                                <div class="btn-group">
				                                    <a class="btn green-haze btn-outline btn-circle btn-sm" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true" aria-expanded="true">Action
				                                        <i class="fa fa-angle-down"></i>
				                                    </a>
				                                </div>
				                            </div>
				                        </div>
				                        <div class="portlet-body">
				                            <div class="tiles">
				                                <div class="tile double-down bg-blue-hoki">
				                                    <div class="tile-body">
				                                        <i class="fa fa-bell-o"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 通知 </div>
				                                        <div class="number"> 6 </div>
				                                    </div>
				                                </div>
				                                <div class="tile bg-red-sunglo">
				                                    <div class="tile-body">
				                                        <i class="fa fa-calendar"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 会议 </div>
				                                        <div class="number"> 12 </div>
				                                    </div>
				                                </div>
				                                <div class="tile double selected bg-green-turquoise">
				                                    <div class="corner"> </div>
				                                    <div class="check"> </div>
				                                    <div class="tile-body">
				                                        <h4>jehc.com</h4>
				                                        <p> Re: v2.0.1 - Project Update! </p>
				                                        <p> 更新中... </p>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name">
				                                            <i class="fa fa-envelope"></i>
				                                        </div>
				                                        <div class="number"> 14 </div>
				                                    </div>
				                                </div>
				                                <div class="tile selected bg-yellow-saffron">
				                                    <div class="corner"> </div>
				                                    <div class="tile-body">
				                                        <i class="fa fa-user"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 会员管理 </div>
				                                        <div class="number"> 452 </div>
				                                    </div>
				                                </div>
				                                <div class="tile double bg-blue-madison">
				                                    <div class="tile-body">
				                                        <img src="${syspath}/deng/source/plugins/admin/index//pages/media/profile/photo1.jpg" alt="">
				                                        <h4>开源社区jEhc</h4>
				                                        <p> 让你二次开发 快速入手... </p>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 1个人开发</div>
				                                        <div class="number"> 9.3 dengcj 2017 </div>
				                                    </div>
				                                </div>
				                                <div class="tile bg-purple-studio">
				                                    <div class="tile-body">
				                                        <i class="fa fa-shopping-cart"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 订单中心 </div>
				                                        <div class="number"> 121 </div>
				                                    </div>
				                                </div>
				                                <div class="tile image selected">
				                                    <div class="tile-body">
				                                        <img src="${syspath}/deng/source/plugins/admin/index/pages/media/gallery/image2.jpg" alt=""> </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 音频 </div>
				                                    </div>
				                                </div>
				                                <div class="tile bg-green-meadow">
				                                    <div class="tile-body">
				                                        <i class="fa fa-comments"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 短消息中心 </div>
				                                        <div class="number"> 12 </div>
				                                    </div>
				                                </div>
				                                <div class="tile double bg-grey-cascade">
				                                    <div class="tile-body">
				                                        <img src="${syspath}/deng/source/plugins/admin/index/pages/media/profile/photo2.jpg" alt="" class="pull-right">
				                                        <h3>@jehc</h3>
				                                        <p> 开源社区jEhc </p>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name">
				                                            <i class="fa fa-twitter"></i>
				                                        </div>
				                                        <div class="number"> 10:45PM, 23 dengcj </div>
				                                    </div>
				                                </div>
				                                <div class="tile bg-red-intense">
				                                    <div class="tile-body">
				                                        <i class="fa fa-coffee"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 相遇 </div>
				                                        <div class="number"> 12 Jan </div>
				                                    </div>
				                                </div>
				                                <div class="tile bg-green">
				                                    <div class="tile-body">
				                                        <i class="fa fa-bar-chart-o"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 报表 </div>
				                                        <div class="number"> </div>
				                                    </div>
				                                </div>
				                                <div class="tile bg-blue-steel">
				                                    <div class="tile-body">
				                                        <i class="fa fa-briefcase"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 文档 </div>
				                                        <div class="number"> 172 </div>
				                                    </div>
				                                </div>
				                                <div class="tile image double selected">
				                                    <div class="tile-body">
				                                        <img src="${syspath}/deng/source/plugins/admin/index/pages/media/gallery/image4.jpg" alt=""> </div>
				                                    <div class="tile-object">
				                                        <div class="name"> Gallery </div>
				                                        <div class="number">172</div>
				                                    </div>
				                                </div>
				                                <div class="tile bg-yellow-lemon selected">
				                                    <div class="corner"> </div>
				                                    <div class="check"> </div>
				                                    <div class="tile-body">
				                                        <i class="fa fa-cogs"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 设置... </div>
				                                    </div>
				                                </div>
				                                <div class="tile bg-red-sunglo">
				                                    <div class="tile-body">
				                                        <i class="fa fa-plane"></i>
				                                    </div>
				                                    <div class="tile-object">
				                                        <div class="name"> 项目 </div>
				                                        <div class="number"> 34 </div>
				                                    </div>
				                                </div>
				                            </div>
				                        </div>
				                    </div>
		                        </div>
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
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-addtab/css/bootstrap.addtabs.css" rel="stylesheet" type="text/css" />
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
			.nav-tabs>li.active>a, .nav-tabs>li.active>a:focus, .nav-tabs>li.active>a:hover {
			    color: #888;
			    /* background-color: 44b6ae; */
			    border-color: #44b6ae;
			    color: #ffffff;
			    background: #44b6ae;
			    border-radius: 15px 15px 15px 15px!important; 
			}
			a:hover {
			    cursor: pointer;
			    color: #fff;
			}
			a {
			    text-shadow: none;
			    color: #ffffff;
			}
			.nav>li>a {
			    padding: 5px 15px;
			}
			.nav-pills>li>a, .nav-tabs>li>a {
			    font-size: 18px;
			    background: 0 0;
			    border-color: #e7505a;
			    border-radius: 15px 15px 15px 15px!important;
			}
			
			/* .page-sidebar .page-sidebar-menu>li>a, .page-sidebar-closed.page-sidebar-fixed .page-sidebar:hover .page-sidebar-menu>li>a {
			    font-size: 16px;
			} */
   		</style>
    </body>
    <script type="text/javascript" src="${syspath}/view/pc/xt-view/xt-index/xt-admin.js"></script>
    <!-- 对话框插件 -->
	<link rel="stylesheet" type="text/css" href="${syspath}/deng/source/plugins/other/jquery/alert/zeroModal.css" />
	<script src="${syspath}/deng/source/plugins/other/jquery/alert/zeroModal.min.js"></script>
</html>
