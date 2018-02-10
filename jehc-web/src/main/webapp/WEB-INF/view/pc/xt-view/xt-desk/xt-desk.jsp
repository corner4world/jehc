<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/indexboot.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<style>
body {
	background-color: #ffffff;
}
.page-title {
	font-size: 18px;
}
.portlet>.portlet-title>.actions .btn-icon-only.btn-default.fullscreen {
    font-family: FontAwesome;
    color: #a0a0a0;
    padding-top: 3px;
}
.portlet>.portlet-title>.actions .btn-icon-only.btn-default {
    padding: 4px 6px 2px;
}
.portlet.light>.portlet-title>.actions .btn-icon-only {
    height: 27px;
    width: 27px;
}
.btn-circle {
    border-radius: 25px!important;
    overflow: hidden;
}
</style>
</head>
<body>
	<h3 class="page-title">
		桌面控制台 <small>工作区 &amp; 统计</small>
	</h3>
	<div class="page-bar">
		<ul class="page-breadcrumb">
			<li><i class="icon-home"></i> <a href="index.html">首页</a> <i
				class="fa fa-angle-right"></i></li>
			<li><span>工作区</span></li>
		</ul>
	</div>
	<div class="row">
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<a class="dashboard-stat dashboard-stat-v2 blue" href="#">
				<div class="visual">
					<i class="fa fa-comments"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${xtLoginLogsCount }">${xtLoginLogsCount }</span>
					</div>
					<div class="desc">登录次数</div>
				</div>
			</a>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<a class="dashboard-stat dashboard-stat-v2 red" href="#">
				<div class="visual">
					<i class="fa fa-bar-chart-o"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${xtNoticeCount }">${xtNoticeCount }</span>条
					</div>
					<div class="desc">平台公告数</div>
				</div>
			</a>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<a class="dashboard-stat dashboard-stat-v2 green" href="#">
				<div class="visual">
					<i class="fa fa-shopping-cart"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${xtKnowledgeCount }">${xtKnowledgeCount }</span>条
					</div>
					<div class="desc">平台知识库数量</div>
				</div>
			</a>
		</div>
		<div class="col-lg-3 col-md-3 col-sm-6 col-xs-12">
			<a class="dashboard-stat dashboard-stat-v2 purple" href="#">
				<div class="visual">
					<i class="fa fa-globe"></i>
				</div>
				<div class="details">
					<div class="number">
						<span data-counter="counterup" data-value="${xtOnlineUserCount }">${xtOnlineUserCount }</span>个
					</div>
					<div class="desc">当前在线人数</div>
				</div>
			</a>
		</div>
	</div>
	<div class="clearfix"></div>
	<div class="row">
		<div class="col-md-6 col-sm-6">
			<div class="portlet light ">
				<div class="portlet-title">
					<div class="caption">
						<i class="icon-share font-dark hide"></i><span class="caption-subject font-dark bold uppercase">平台公告</span>
					</div>
				</div>
				<div class="portlet-body">
                    <div class="scroller" style="height: 300px;" data-always-visible="1" data-rail-visible="0">
                        <ul class="feeds">
                        	<c:forEach var="xtNotice" items="${xtNoticeList }">
                        		<li>
	                                <div class="col1">
	                                    <div class="cont">
	                                        <div class="cont-col1">
	                                            <div class="label label-sm label-warning">
	                                                <i class="fa fa-bell-o"></i>
	                                            </div>
	                                        </div>
	                                        <div class="cont-col2">
	                                            <c:choose>
	                                            <c:when test="${fn:length(xtNotice.xt_title)>20 }">
		                                            <div class="desc" title="${xtNotice.xt_title }">${fn:substring( xtNotice.xt_title ,0,20)}...<span class="label label-sm label-default "> 完成 </span></div>
	                                            </c:when>
	                                            <c:otherwise>
	                                            <div class="desc" title="${xtNotice.xt_title }">${xtNotice.xt_title }<span class="label label-sm label-default "> 完成 </span></div>
	                                            </c:otherwise>
	                                            </c:choose>
	                                        </div>
	                                    </div>
	                                </div>
	                                <div class="col2">
	                                    <div class="date">
	                                    <c:choose>
                                        	<c:when test="${fn:length(xtNotice.xt_createTime)>16 }">${fn:substring(xtNotice.xt_createTime ,8,16)}</c:when>
                                        	<c:otherwise>-</c:otherwise>
                                        </c:choose>
	                                    </div>
	                                </div>
	                            </li>
                        	</c:forEach>
                        </ul>
                    </div>
					<div class="scroller-footer">
						<div class="btn-arrow-link pull-right">
							<a href="javascript:;">更多...</a><i class="icon-arrow-right"></i>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-md-6 col-sm-6">
			 <div class="portlet light tasks-widget ">
                <div class="portlet-title">
                    <div class="caption">
                        <i class="icon-share font-dark hide"></i>
                        <span class="caption-subject font-dark bold uppercase">个人任务</span>
                    </div>
                    <div class="actions">
                        <a class="btn btn-circle btn-icon-only btn-default fullscreen" href="javascript:;" data-original-title="" title=""> </a>
                    </div>
                </div>
				<div class="portlet-body">
                    <div class="task-content">
                       <div class="scroller" style="height: 312px;" data-always-visible="1" data-rail-visible1="1">
							<ul class="task-list">
								<li>
									<div class="task-checkbox">
										<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
											<input type="checkbox" class="checkboxes" value="1">
											<span></span>
										</label>
									</div>
									<div class="task-title">
										<span class="task-title-sp"> jEhc平台V1.0上线了...</span> <span class="label label-sm label-success">公司</span> <span class="task-bell"> <i class="fa fa-bell-o"></i></span>
									</div>
									<div class="task-config">
										<div class="task-config-btn btn-group">
											<a class="btn btn-sm default" href="javascript:;" data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <i class="fa fa-cog"></i> <i class="fa fa-angle-down"></i></a>
											<ul class="dropdown-menu pull-right">
												<li><a href="javascript:;"> <i class="fa fa-check"></i> 完成 </a></li>
												<li><a href="javascript:;"> <i class="fa fa-pencil"></i> 编辑 </a></li>
												<li><a href="javascript:;"> <i class="fa fa-trash-o"></i> 取消</a></li>
											</ul>
										</div>
									</div>
								</li>
							</ul>
						</div>
					</div>
					<div class="task-footer">
                        <div class="btn-arrow-link pull-right">
                            <a href="javascript:;">更多...</a> <i class="icon-arrow-right"></i>
                            <i class="icon-arrow-right"></i>
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</div>
</body>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/counterup/jquery.waypoints.min.js" type="text/javascript"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/counterup/jquery.counterup.min.js" type="text/javascript"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/horizontal-timeline/horozontal-timeline.min.js" type="text/javascript"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/flot/jquery.flot.min.js" type="text/javascript"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/flot/jquery.flot.resize.min.js" type="text/javascript"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/flot/jquery.flot.categories.min.js" type="text/javascript"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery.sparkline.min.js" type="text/javascript"></script>
</html>
