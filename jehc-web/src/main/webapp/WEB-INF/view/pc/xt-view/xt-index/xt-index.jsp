<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="jEhcPermissionTag" uri="/WEB-INF/tld/jEhcPermissionTag.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
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
        <%
        String colorTheme="dark";
        Cookie cookies[]=request.getCookies(); 
		Cookie cookieC=null; 
		if(null != cookies){
			for(int i=0;i < cookies.length;i++){
			    if(("css").equals(cookies[i].getName())){ 
		      		cookieC=cookies[i]; 
		      		break; 
		      	}
		    }
		}
	    if(null != cookieC){
		    if(cookieC.getValue().indexOf("whiteClass")>=0){
		    	colorTheme = "light";
		    }
	    }
        %>
        <script type="text/javascript">
	    	var basePath = "${syspath}";
	    	var sys_pt_login = "${sys_pt_login}";
	    	var sys_pt_index = "${sys_pt_index}";
	    	var sys_pt_index_foot = "${sys_pt_index_foot}";
	    	var sys_pt_index_top = "${sys_pt_index_top}";
	    	var sys_pt_session = "${sys_pt_session}";
	    	var sys_pt_user_name = "${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_realName}";
	    	var moretext = "${moretext}";
	    	var moretexttooltip = "${moretexttooltip}";
	    	var grid_toolbar_gaps = "${grid_toolbar_gaps}";
	    	var grid_toolbar_moretext_gaps = "${grid_toolbar_moretext_gaps}";
	    	var JSESSIONID;
	    	var collapsibleDefined=true;
	    	var tbarBtnMinWidth=0;
	    	var hideCollapseToolFlag=true;
	    	var lc_design_displaywin_for_edit = ${lc_design_displaywin_for_edit};
	    	var xt_userinfo_id='${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_id}'; 
		</script>
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/other/iconfont/iconfont.css" rel="stylesheet" type="text/css" >
		<link href="${syspath }/deng/source/plugins/newAdmin/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
		<link href="${syspath }/deng/source/plugins/newAdmin/using/base/theme/smallleft/style.bundle.css" rel="stylesheet" type="text/css" />
		<link href="${syspath }/deng/source/plugins/newAdmin/tab/css/style.min.css" rel="stylesheet">
		<link href="${syspath }/deng/source/plugins/newAdmin/vendors/base/perfect-scrollbar.min.css" rel="stylesheet">
		<style type="text/css">
			.m-brand {
			    background: #17C4BB;
			}
			.m-body .m-content {
			    padding: 0px 15px;
			}
			.m-header-menu .m-menu__nav>.m-menu__item.m-menu__item--active>.m-menu__link>.m-menu__item-here, .m-header-menu .m-menu__nav>.m-menu__item.m-menu__item--expanded>.m-menu__link>.m-menu__item-here {
			    position: absolute;
			    line-height: 0;
			    display: inline-block;
			    overflow: hidden;
			    height: 16px;
			    width: 60px;
			    position: relative;
			    left:0;
			     margin-left: 0px;
			    position: absolute;
			    top: 100%;
			    z-index: 1;
			    margin-top: 0px;
			    color: #fff;
			    display: inline-block!important;
			}
			
			.m-header .m-header-head {
				-webkit-transition: none;
				-moz-transition: none;
				-ms-transition: none;
				-o-transition: none;
				transition: none;
				-webkit-box-shadow: 0 1px 0px 1px rgba(69,65,78,.1);
				-moz-box-shadow: 0 1px 0px 1px rgba(69,65,78,.1);
			    box-shadow: 0 1px 0px 1px rgba(69,65,78,.1);
			}
		</style>
		<input type="hidden" id="lc_apply_model_biz_id" value="${lc_apply_model_biz_id }"/>
	   	
	</head>
    <!-- end::Body -->
	<body  class="m-page--fluid m--skin- m-content--skin-light2 m-header--fixed m-header--fixed-mobile m-aside-left--enabled m-aside-left--skin-dark m-aside-left--fixed m-aside-left--offcanvas m-aside-left--minimize m-brand--minimize m-footer--fixed m-footer--push m-aside--offcanvas-default"  >
		<!-- begin:: Page -->
		<div class="m-grid m-grid--hor m-grid--root m-page"  style="height:100%;">
			<!-- BEGIN: Header -->
			<header id="m_header" class="m-grid__item    m-header "  m-minimize-offset="200" m-minimize-mobile-offset="200" >
				<div class="m-container m-container--fluid m-container--full-height">
					<div class="m-stack m-stack--ver m-stack--desktop">
						<!-- BEGIN: Brand -->
						<div class="m-stack__item m-brand  m-brand--skin-light ">
							<div class="m-stack m-stack--ver m-stack--general">
								<div class="m-stack__item m-stack__item--middle m-brand__logo">
									<a href="index.html" class="m-brand__logo-wrapper">
										<img alt="" src="${syspath }/deng/images/logo/logo.png"/>
									</a>
									<h3 class="m-header__title">
										移动版
									</h3>
								</div>
								<div class="m-stack__item m-stack__item--middle m-brand__tools">
									<!-- BEGIN: Responsive Aside Left Menu Toggler -->
									<a href="javascript:;" id="m_aside_left_offcanvas_toggle" class="m-brand__icon m-brand__toggler m-brand__toggler--left m--visible-tablet-and-mobile-inline-block">
										<span></span>
									</a>
									<!-- END -->
									<!-- BEGIN: Responsive Header Menu Toggler -->
									<a id="m_aside_header_menu_mobile_toggle" href="javascript:;" class="m-brand__icon m-brand__toggler m--visible-tablet-and-mobile-inline-block">
										<span></span>
									</a>
									<!-- END -->
									<!-- BEGIN: Topbar Toggler -->
									<a id="m_aside_header_topbar_mobile_toggle" href="javascript:;" class="m-brand__icon m--visible-tablet-and-mobile-inline-block">
										<i class="flaticon-more"></i>
									</a>
									<!-- BEGIN: Topbar Toggler -->
								</div>
							</div>
						</div>
						<!-- END: Brand -->
						<div class="m-stack__item m-stack__item--fluid m-header-head" id="m_header_nav">
							<div class="m-header__title">
								<h3 class="m-header__title-text">
									${sys_pt_index_top}
								</h3>
							</div>
							<!-- BEGIN: Horizontal Menu -->
							<!-- 
							<button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-light " id="m_aside_header_menu_mobile_close_btn">
								<i class="la la-close"></i>
							</button>
							<div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-light m-aside-header-menu-mobile--submenu-skin-light "  > 
							-->
							<button class="m-aside-header-menu-mobile-close  m-aside-header-menu-mobile-close--skin-light " id="m_aside_header_menu_mobile_close_btn"><i class="la la-close"></i></button>
							<!-- 默认主题 白色菜单
							<div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-light m-aside-header-menu-mobile--skin-light m-aside-header-menu-mobile--submenu-skin-light "  > 
							-->
							<!-- 黑色主题 -->
							<div id="m_header_menu" class="m-header-menu m-aside-header-menu-mobile m-aside-header-menu-mobile--offcanvas  m-header-menu--skin-light m-header-menu--submenu-skin-<%=colorTheme%> m-aside-header-menu-mobile--skin-light m-aside-header-menu-mobile--submenu-skin-light "  >
								<ul class="m-menu__nav  m-menu__nav--submenu-arrow ">
									${AdminMenuList }
								</ul>
							</div>
							<!-- END: Horizontal Menu -->				
							<!-- BEGIN: Topbar -->
							<div id="m_header_topbar" class="m-topbar  m-stack m-stack--ver m-stack--general">
								<div class="m-stack__item m-stack__item--middle m-dropdown m-dropdown--arrow m-dropdown--large m-dropdown--mobile-full-width m-dropdown--align-right m-dropdown--skin-light m-header-search m-header-search--expandable m-header-search--skin-light" id="m_quicksearch" m-quicksearch-mode="default">
									<!--BEGIN: Search Form -->
									<div class="m-header-search__form">
										<div class="m-header-search__wrapper">
											<span class="m-header-search__icon-search" onclick="search();">
												<i class="flaticon-search"></i>
											</span>
											<span class="m-header-search__input-wrapper">
												<input type="text" id="keywords" name="keywords" class="m-header-search__input" value="" placeholder="请输入关键词...">
											</span>
											<span class="m-header-search__icon-close" id="m_quicksearch_close">
												<i class="la la-remove"></i>
											</span>
											<span class="m-header-search__icon-cancel" id="m_quicksearch_cancel">
												<i class="la la-remove"></i>
											</span>
										</div>
									</div>
									<!--END: Search Form -->
									<!--BEGIN: Search Results -->
									<div class="m-dropdown__wrapper">
										<div class="m-dropdown__arrow m-dropdown__arrow--center"></div>
										<div class="m-dropdown__inner">
											<div class="m-dropdown__body">
												<div class="m-dropdown__scrollable m-scrollable" data-scrollable="true"  data-max-height="300" data-mobile-max-height="200">
													<div class="m-dropdown__content m-list-search m-list-search--skin-light"></div>
												</div>
											</div>
										</div>
									</div>
									<!--BEGIN: END Results -->
								</div>
								<div class="m-stack__item m-topbar__nav-wrapper">
									<ul class="m-topbar__nav m-nav m-nav--inline">
										<li class="m-nav__item m-topbar__notifications m-dropdown m-dropdown--large m-dropdown--arrow m-dropdown--align-center 	m-dropdown--mobile-full-width" m-dropdown-toggle="click" m-dropdown-persistent="1">
											<a href="#" class="m-nav__link m-dropdown__toggle" id="m_topbar_notification_icon">
												<span class="m-nav__link-badge m-badge m-badge--dot m-badge--dot-small m-badge--danger"></span>
												<span class="m-nav__link-icon">
													<span class="m-nav__link-icon-wrapper">
														<i class="flaticon-music-2"></i>
													</span>
												</span>
											</a>
											<div class="m-dropdown__wrapper">
												<span class="m-dropdown__arrow m-dropdown__arrow--center"></span>
												<div class="m-dropdown__inner">
													<div class="m-dropdown__header m--align-center" style="background: url(${syspath}/deng/source/plugins/newAdmin/app/media/img/misc/notification_bg.jpg); background-size: cover;">
														<span class="m-dropdown__header-title">
															${fn:length(xtDyRemind.xtNotifyReceiverList)+fn:length(xtDyRemind.xtMessageList) }个
														</span>
														<span class="m-dropdown__header-subtitle">
															通知
														</span>
													</div>
													<div class="m-dropdown__body">
														<div class="m-dropdown__content">
															<ul class="nav nav-tabs m-tabs m-tabs-line m-tabs-line--brand" role="tablist">
																<li class="nav-item m-tabs__item">
																	<a class="nav-link m-tabs__link active" data-toggle="tab" href="#topbar_notifications_notifications" role="tab">
																		平台通知
																	</a>
																</li>
																<li class="nav-item m-tabs__item">
																	<a class="nav-link m-tabs__link" data-toggle="tab" href="#topbar_notifications_events" role="tab">
																		平台消息
																	</a>
																</li>
															</ul>
															<div class="tab-content">
																<div class="tab-pane active" id="topbar_notifications_notifications" role="tabpanel">
																	<div class="m-scrollable" data-scrollable="true" data-max-height="250" data-mobile-max-height="200">
																		<div class="m-list-timeline m-list-timeline--skin-light">
																			<div class="m-list-timeline__items">
																				<c:forEach var="xtNotifyReceiver" items="${xtDyRemind.xtNotifyReceiverList }">
																					<div class="m-list-timeline__item">
																						<span class="m-list-timeline__badge -m-list-timeline__badge--state-success"></span>
																						<span class="m-list-timeline__text">
																							<a href="javascript:toXtReceiverDetail('${xtNotifyReceiver.xt_notify_receiver_id }')" class="m-list-timeline__text">
																							<c:if test="${fn:length(xtNotifyReceiver.xt_notify_title)>20 }">${fn:substring(xtNotifyReceiver.xt_notify_title,0,20)} ...</c:if>
 																							<c:if test="${fn:length(xtNotifyReceiver.xt_notify_title)<=20 }">${xtNotifyReceiver.xt_notify_title}</c:if>
																							</a>
																						</span>
																						<span class="m-list-timeline__time">
																							${xtNotifyReceiver.receive_time }
																						</span>
																					</div>
																				</c:forEach>
																			</div>
																		</div>
																	</div>
																</div>
																<div class="tab-pane" id="topbar_notifications_events" role="tabpanel">
																	<div class="m-scrollable" data-scrollable="true" data-max-height="250" data-mobile-max-height="200">
																		<div class="m-list-timeline m-list-timeline--skin-light">
																			<div class="m-list-timeline__items">
																				<c:forEach var="xtMessage" items="${xtDyRemind.xtMessageList }">
																					<div class="m-list-timeline__item">
																						<span class="m-list-timeline__badge m-list-timeline__badge--state1-success"></span>
																						<a href="javascript:toXtReceiverDetail('${xtMessage.xt_message_id}')" class="m-list-timeline__text">
																							<c:if test="${fn:length(xtMessage.xt_meesage_content)>20 }">${fn:substring(xtMessage.xt_meesage_content,0,20)} ...</c:if>
 																							<c:if test="${fn:length(xtMessage.xt_meesage_content)<=20 }">${xtMessage.xt_meesage_content}</c:if>
																						</a>
																						<span class="m-list-timeline__time">
																							${xtMessage.ctime }
																						</span>
																					</div>
																				</c:forEach>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</li>										
										<li class="m-nav__item m-topbar__user-profile  m-dropdown m-dropdown--medium m-dropdown--arrow  m-dropdown--align-right m-dropdown--mobile-full-width m-dropdown--skin-light" m-dropdown-toggle="click">
											<a href="#" class="m-nav__link m-dropdown__toggle">
												<span class="m-topbar__userpic m--hide">
													<img src="${syspath}/deng/images/default/user.png" class="m--img-rounded m--marginless m--img-centered" alt=""/>
												</span>
												<span class="m-nav__link-icon m-topbar__usericon">
													<span class="m-nav__link-icon-wrapper">
														<i class="flaticon-user-ok"></i>
													</span>
												</span>
												<span class="m-topbar__username m--hide">
													${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_realName }
												</span>
											</a>
											<div class="m-dropdown__wrapper">
												<span class="m-dropdown__arrow m-dropdown__arrow--right m-dropdown__arrow--adjust"></span>
												<div class="m-dropdown__inner">
													<div class="m-dropdown__header m--align-center">
														<div class="m-card-user m-card-user--skin-light">
															<div class="m-card-user__pic">
																<img src="${syspath}/deng/images/default/user.png" class="m--img-rounded m--marginless" alt=""/>
															</div>
															<div class="m-card-user__details">
																<span class="m-card-user__name m--font-weight-500">
																	${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_realName }
																</span>
																<a href="" class="m-card-user__email m--font-weight-300 m-link">
																	${BASE_HTTP_SESSION.XTUSERINFO.xt_userinfo_name }
																</a>
															</div>
														</div>
													</div>
													<div class="m-dropdown__body">
														<div class="m-dropdown__content">
															<ul class="m-nav m-nav--skin-light">
																<li class="m-nav__item">
																	<a href='../xtUserinfoController/loadMyXtUserinfo' class="m-nav__link nav-link J_menuItem" data-index='myUserInfo' rootId='myUserInfo' idBu='myUserInfo'>
																		<i class="m-nav__link-icon flaticon-share"></i>
																		<span class="m-nav__link-text">
																			个人资料
																		</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="javascript:displayTheme()" class="m-nav__link">
																		<i class="m-nav__link-icon flaticon-chat-1"></i>
																		<span class="m-nav__link-text">
																			切换主题
																		</span>
																	</a>
																</li>
																<li class="m-nav__separator m-nav__separator--fit"></li>
																<li class="m-nav__item">
																	<a href="javascript:updatePwd()" class="m-nav__link">
																		<i class="m-nav__link-icon flaticon-info"></i>
																		<span class="m-nav__link-text">
																			修改密码
																		</span>
																	</a>
																</li>
																<li class="m-nav__item">
																	<a href="javascript:initLockSystem(1)" class="m-nav__link">
																		<i class="m-nav__link-icon flaticon-lifebuoy"></i>
																		<span class="m-nav__link-text">
																			锁屏
																		</span>
																	</a>
																</li>
																<li class="m-nav__separator m-nav__separator--fit"></li>
																<li class="m-nav__item">
																	<a href="javascript:loginout()" class="btn m-btn--pill    btn-secondary m-btn m-btn--custom m-btn--label-brand m-btn--bolder">
																		注销
																	</a>
																</li>
															</ul>
														</div>
													</div>
												</div>
											</div>
										</li>
									</ul>
								</div>
							</div>
							<!-- END: Topbar -->
						</div>
					</div>
				</div>
			</header>
			<!-- END: Header -->			
			<!-- begin::Body -->
			<div class="m-grid__item m-grid__item--fluid m-grid m-grid--ver-desktop m-grid--desktop m-body"  style="height:100% ">
				<!-- BEGIN: 左边总区域开始 -->
				<button class="m-aside-left-close  m-aside-left-close--skin-light " id="m_aside_left_close_btn"><i class="la la-close"></i></button>
				<!-- 默认主题 白色菜单
				<div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-light "> 
				-->
				<!-- 黑色主题 -->
				<div id="m_aside_left" class="m-grid__item	m-aside-left  m-aside-left--skin-<%=colorTheme%> ">
					<!-- BEGIN: 左边菜单开始 Menu -->
					<!-- 黑色主题 -->
					<div  id="m_ver_menu" class="m-aside-menu  m-aside-menu--skin-<%=colorTheme%> m-aside-menu--submenu-skin-<%=colorTheme%> " data-menu-vertical="true" m-menu-scrollable="1" m-menu-dropdown-timeout="500">	
					<!-- 
					默认主题 白色菜单
					<div id="m_ver_menu" class="m-aside-menu  m-aside-menu--skin-light m-aside-menu--submenu-skin-light " data-menu-vertical="true" m-menu-scrollable="1" m-menu-dropdown-timeout="500" >		
				    -->
						<ul class="m-menu__nav  m-menu__nav--dropdown-submenu-arrow ">
							${MenuList }
						</ul>
					</div>
					<!-- END: 左边菜单结束 Menu -->
				</div>
				<!-- END: 左边总区域结束 -->
				<div class="m-grid__item m-grid__item--fluid m-wrapper" style="height:-moz-calc(100% - 60px); height:-webkit-calc(100% - 60px); height:calc(100% - 60px); ">
					<!-- END: 中间区域开始 -->
					<div class="m-content" style="height: 100%;">
						<div class="row content-tabs">
				            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i></button>
				            <nav class="page-tabs J_menuTabs">
				                <div class="page-tabs-content" style="margin-left: 0px;">
				                    <a href="javascript:;" class="J_menuTab active" data-id="home">首页</a>
				                </div>	
						   </nav>
				            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
				            </button>
				            <div class="btn-group roll-nav roll-right">
								<div class="btn-group mb-2">
									<button type="button" class="dropdown-toggle J_tabClose" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">操作选项卡<span class="caret"></span></button>
									<ul role="menu" class="dropdown-menu dropdown-menu-right">
										<li class="J_tabShowActive">
											<a><i class="icon-trash"></i>定位当前选项</a>
										</li>
										<div class="dropdown-divider"></div>
										<li class="J_tabCloseAll">
											<a><i class="icon-close"></i>关闭全部选项</a>
										</li>
										<li class="J_tabCloseOther">
											<a><i class="icon-close"></i>关闭其他选项</a>
										</li>
									</ul>
								</div>										               
				            </div>
			        	</div>
			        	<div class="row J_mainContent" id="content-main" style="height: 100%;">
				            <iframe class="J_iframe" id="iframe0" name="iframe0" width="100%" height="100%" src="${syspath}/index/desk.html" frameborder="0" data-id="home" seamless="" style="display: block; height: 100%;"></iframe>
				        </div>	
					</div>
					<!-- END: 中间区域结束 -->
				</div>
			</div>
			<!-- end:: Body -->
			<!-- begin::Footer -->
			<footer class="m-grid__item		m-footer ">
				<div class="m-container m-container--fluid m-container--full-height m-page__container">
					<div class="m-stack m-stack--flex-tablet-and-mobile m-stack--ver m-stack--desktop">
						<div class="m-stack__item m-stack__item--left m-stack__item--middle m-stack__item--last">
							<span class="m-footer__copyright">
								${sys_pt_index_foot}
							</span>
						</div>
						<div class="m-stack__item m-stack__item--right m-stack__item--middle m-stack__item--first">
							<ul class="m-footer__nav m-nav m-nav--inline m--pull-right">
								<li class="m-nav__item">
									<a href="#" class="m-nav__link">
										<span class="m-nav__link-text">
											关于我们
										</span>
									</a>
								</li>
								<li class="m-nav__item">
									<a href="#"  class="m-nav__link">
										<span class="m-nav__link-text">
											联系我们
										</span>
									</a>
								</li>
								<li class="m-nav__item">
									<a href="#" class="m-nav__link">
										<span class="m-nav__link-text">
											备案ICP
										</span>
									</a>
								</li>
								<li class="m-nav__item">
									<a href="#" class="m-nav__link">
										<span class="m-nav__link-text">
											公司信息
										</span>
									</a>
								</li>
								<li class="m-nav__item m-nav__item">
									<a href="#" class="m-nav__link" data-toggle="m-tooltip" title="帮助" data-placement="left">
										<i class="m-nav__link-icon flaticon-info m--icon-font-size-lg3"></i>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</footer>
			<!-- end::Footer -->
		</div>
		<!-- end:: Page -->
		
		<!-- 修改密码模态框（Modal）开始 -->
		<div class="modal fade" id="updatePwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog" id="updatePwdModalDialog">
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
			                    <input class="form-control placeholder-no-fix" type="password" placeholder="请输入旧密码" name="oldPwd" id="oldPwd"/> 
			                    </div>
			                </div>
			                <div class="form-group">
			                    <label class="control-label">新&nbsp;&nbsp;密&nbsp;&nbsp;码</label>
			                    <div class="input-icon">
			                    <input class="form-control placeholder-no-fix" type="password" placeholder="请输入新密码" name="newPwd" id="newPwd"/> 
			                    </div>
			                </div>
			                <div class="form-group">
			                    <label class="control-label">确认密码</label>
			                    <div class="input-icon">
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
			<div class="modal-dialog" id="lockModalDialog">
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
		
		<!-- 肤色模态框（Modal）开始 -->
		<div class="modal fade" id="themeModal" tabindex="-1" role="dialog" aria-labelledby="themeModalLabel" data-backdrop=”static”  aria-hidden="true">
			<div class="modal-dialog" id="themeModalDialog">
				<div class="modal-content">
					<div class="modal-header">
						<h4 class="modal-title" id="themeModalLabel">
							切换肤色
						</h4>
					</div>
					<div class="modal-body">
						<form id="themeForm" method="post">
			               <div class="form-group">
                           	<select class="bs-select form-control" id="chatheme">
                               <option value="defaultClass">默认经典</option>
                               <option value="whiteClass">清爽肤色</option>
                            </select>
                           </div>
		                </form>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
		                <button type="button" class="btn btn-primary" onclick="changeTheme()">保存</button>
		            </div>
				</div><!-- /.modal-content -->
			</div><!-- /.modal -->
		</div>
		<!-- 肤色模态框（Modal）结束 -->		
		<a href='' id="triggerID" class="nav-link J_menuItem" data-index='' rootId='' idBu='' style="display:none;">
           <span id="triggerSpanID"></span>
        </a>
		
	    <!-- begin::Scroll Top -->
		<div id="m_scroll_top" class="m-scroll-top">
			<i class="la la-arrow-up"></i>
		</div>
		<!-- end::Scroll Top -->	
    	<!--begin::Base Scripts-->
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.mousewheel.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/perfect-scrollbar.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/popper.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.smooth-scroll.min.js" type="text/javascript"></script>		
		<script src="${syspath}/deng/source/plugins/newAdmin/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/using/base/theme/smallleft/scripts.bundle.js" type="text/javascript"></script>
		<!--end::Base Scripts -->   
		<!-- 笼罩插件开始 -->
        <link type="text/css" rel="stylesheet" href="${syspath}/deng/source/plugins/other/alertplug/alert/alert.css">
		<script type="text/javascript" src='${syspath}/deng/source/plugins/other/alertplug/alert/alert.js'></script>
		<!-- 笼罩插件结束 -->
        <!-- 提示插件开始 -->
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootbox.js" type="text/javascript"></script>
		<!-- bootstrap验证框架开始 -->
		<link rel="stylesheet" type="text/css" href="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-validate/bootstrapValidator.css" />
		<script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-validate/bootstrapValidator.js" type="text/javascript"></script>
		<!-- bootstrap验证框架结束 -->
		<!-- 公共模块包含上传控件，共同JS封装 -->
		<script type="text/javascript" src="${syspath}/deng/source/js/boot.min.js"></script>
		<script type="text/javascript" src="${syspath}/deng/source/plugins/newAdmin/tab/js/contabs.min.js"></script>
	</body>
	<!-- end::Body -->
    <script type="text/javascript" src="${syspath}/view/pc/xt-view/xt-index/xt-admin.js"></script>
</html>
