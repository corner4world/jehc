<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/indexboot.jsp"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>${sys_pt_index_top}</title>
        <link rel="icon" type="image/ico" href="${syspath }/deng/images/icons/system.png" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta content="width=device-width, initial-scale=1" name="viewport" />
        <meta content="" name="description" />
        <meta content="" name="author" />
	</head>
    <!-- END HEAD -->
    <!-- <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid"> 非采用滚动条-->
    <body class="page-header-fixed page-sidebar-closed-hide-logo page-container-bg-solid page-sidebar-fixed"><!-- 采用滚动条menu -->
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
                                                        <img src="${syspath}/deng/images/default/user.png" class="img-circle" alt=""> </span>
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
                            <!-- 
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                   <span class="username username-hide-on-mobile"> 选项卡操作</span>
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
                                    <li>
                                        <a href="javascript:refreshCruTab()">
                                            <i class="icon-reload"></i>刷新当前选项卡标签
                                        </a>
                                    </li>
                                </ul>
                            </li> 
                            -->
                            <!-- END QUICK SIDEBAR TOGGLER -->
                            <!-- BEGIN USER LOGIN DROPDOWN -->
                            <li class="dropdown dropdown-user">
                                <a href="javascript:;" class="dropdown-toggle" data-toggle="dropdown" data-hover="dropdown" data-close-others="true">
                                    <img alt="" class="img-circle" src="${syspath}/deng/images/default/user.png" />
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
                   <%--
                   <!-- BEGIN SIDEBAR MENU -->
                   <!-- 手风琴样式 
                   <ul class="page-sidebar-menu page-sidebar-menu-light page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 0px">
                        ${MenuList }
                   </ul>
                   -->
                   <!-- 鼠标移入样式
                   <ul class="page-sidebar-menu page-sidebar-menu-hover-submenu page-header-fixed " data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200" style="padding-top: 0px">
                        ${MenuList }
                   </ul>
                   -->
                   --%>
                   <!-- 手风琴+侧边样式 -->
                   <ul class="page-sidebar-menu  page-header-fixed page-sidebar-menu-compact" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                        <li class="nav-item start active open" id="menuMyCenterP">
							<a href="javascript:;" class="nav-link nav-toggle">
							<i class="icon-user"></i>
							<span class="title">个人中心</span>
							<span class="arrow  open"></span>
							</a>
							<ul class="sub-menu">
							<li class="nav-item">
	                            <a href='../xtUserinfoController/loadMyXtUserinfo' class="nav-link J_menuItem" data-index='myUserInfo' rootId='myUserInfo' idBu='myUserInfo' class="nav-link nav-toggle">
	                                <!--  -->
	                                <span class="title">个人资料</span>
	                                <span class="badge badge-danger">新</span>
	                            </a>
	                        </li>
	                        </ul>
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
                    <%--
                    <!-- 
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
                                <span>主题样式</span>
                                <select class="layout-style-option form-control input-small">
                                    <option value="square" selected="selected">方角</option>
                                    <option value="rounded">圆角</option>
                                </select>
                            </div>
                            
                            <div class="theme-option">
                                <span> 布局风格 </span>
                                <select class="layout-option form-control input-small">
                                    <option value="fluid" selected="selected">全屏</option>
                                    <option value="boxed">非全屏</option>
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
                                <span> 页脚风格 </span>
                                <select class="page-footer-option form-control input-small">
                                    <option value="fixed">固定</option>
                                    <option value="default" selected="selected">默认</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    -->
                    --%>
                    <!-- END THEME PANEL -->
		            <div class="main">
		                <div class="row content-tabs">
				            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
				            <nav class="page-tabs J_menuTabs">
				                <div class="page-tabs-content">
				                    <a href="javascript:;" class="active J_menuTab" data-id="home">首页</a>
				                </div>
				            </nav>
				            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
				            </button>
				            <div class="btn-group roll-nav roll-right">
				                <button class="dropdown J_tabClose" data-toggle="dropdown">操作选项卡<span class="caret"></span></button>
				                <ul role="menu" class="dropdown-menu dropdown-menu-right">
				                    <li class="J_tabShowActive">
				                        <a><i class="icon-trash"></i>定位当前选项卡</a>
				                    </li>
				                    <li class="divider"></li>
				                    <li class="J_tabCloseAll">
				                        <a><i class="icon-close"></i>关闭全部选项卡</a>
				                    </li>
				                    <li class="J_tabCloseOther">
				                        <a><i class="icon-close"></i>关闭其他选项卡</a>
				                    </li>
				                </ul>
				            </div>
				            <!-- <a href="#" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i>注销</a> -->
				        </div>
				        <div class="row J_mainContent" id="content-main">
				            <iframe class="J_iframe" id="iframe0" name="iframe0" width="100%" height="100%" src="../index/desk.html" frameborder="0" data-id="home" seamless=""></iframe>
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
		<!-- 修改密码模态框（Modal）开始 -->
		<div class="modal fade" id="updatePwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">
							修改密码
						</h4>
					</div>
					<div class="modal-body">
						<form id="updatePwdForm" method="post">
			                <div class="form-group">
			                    <label class="control-label">旧&nbsp;&nbsp;密&nbsp;&nbsp;码</label>
			                    <div class="input-icon">
			                    <i class="fa fa-lock"></i>
			                    <input class="form-control placeholder-no-fix" type="password" placeholder="请输入旧密码" name="oldPwd" id="oldPwd"/> 
			                    </div>
			                </div>
			                <div class="form-group">
			                    <label class="control-label">新&nbsp;&nbsp;密&nbsp;&nbsp;码</label>
			                    <div class="input-icon">
			                    <i class="fa fa-lock"></i>
			                    <input class="form-control placeholder-no-fix" type="password" placeholder="请输入新密码" name="newPwd" id="newPwd"/> 
			                    </div>
			                </div>
			                <div class="form-group">
			                    <label class="control-label">确认密码</label>
			                    <div class="input-icon">
			                    <i class="fa fa-lock"></i>
			                    <input class="form-control placeholder-no-fix" type="password" placeholder="请再次输入新密码" name="surePwd" id="surePwd"/> 
			                    </div>
			                </div>
		                </form>
					</div>
					<div class="modal-footer">
		                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
		                <button type="button" class="btn btn-primary" onclick="doUpdate()">提交更改</button>
		            </div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- 修改密码模态框（Modal）结束 -->
		<!-- 锁屏模态框（Modal）开始 -->
		<div class="modal fade" id="lockModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop=”static”  aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="myModalLabel">
							用户<font color=red>${xtUserinfo.xt_userinfo_realName}</font>已被锁定中...
						</h4>
					</div>
					<div class="modal-body">
						<form id="lockForm" method="post">
			                <div class="form-group">
			                    <div class="input-icon">
			                    <i class="fa fa-lock"></i>
			                    <input class="form-control placeholder-no-fix" type="password" placeholder="请输入密码" name="password" id="password"/> 
			                    </div>
			                </div>
		                </form>
					</div>
					<div class="modal-footer">
		                <button type="button" class="btn btn-primary" onclick="unlockSystem()">执行解锁</button>
		            </div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- 锁屏模态框（Modal）结束 -->
    </body>
    <script type="text/javascript" src="${syspath}/view/pc/xt-view/xt-index/xt-admin.js"></script>
</html>
