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
	.row{
	margin-right:0px;
	margin-left:0px;
	}
</style>
</head>
<body>
	<div class="m-portlet">
		<div class="m-portlet__body  m-portlet__body--no-padding">
			<div class="row m-row--no-padding m-row--col-separator-xl">
				<div class="col-xl-4">
					<!--begin:: Widgets/Stats2-1 -->
					<div class="m-widget1">
						<div class="m-widget1__item">
							<div class="row m-row--no-padding align-items-center">
								<div class="col">
									<h3 class="m-widget1__title">
										登录次数
									</h3>
									<!-- <span class="m-widget1__desc">
										当前登录人登录平台历史次数
									</span> -->
								</div>
								<div class="col m--align-right">
									<span class="m-widget1__number m--font-brand">
										${xtLoginLogsCount }
									</span>
								</div>
							</div>
						</div>
						<div class="m-widget1__item">
							<div class="row m-row--no-padding align-items-center">
								<div class="col">
									<h3 class="m-widget1__title">
										平台公告数
									</h3>
									<!-- <span class="m-widget1__desc">
										平台历史发布公告总数统计
									</span> -->
								</div>
								<div class="col m--align-right">
									<span class="m-widget1__number m--font-danger">
										${xtNoticeCount }
									</span>
								</div>
							</div>
						</div>
						<div class="m-widget1__item">
							<div class="row m-row--no-padding align-items-center">
								<div class="col">
									<h3 class="m-widget1__title">
										知识库数
									</h3>
									<!-- <span class="m-widget1__desc">
										平台知识库数量统计
									</span> -->
								</div>
								<div class="col m--align-right">
									<span class="m-widget1__number m--font-success">
										${xtKnowledgeCount }
									</span>
								</div>
							</div>
						</div>
					</div>
					<!--end:: Widgets/Stats2-1 -->
				</div>
				<div class="col-xl-4">
					<!--begin:: Widgets/Daily Sales-->
					<div class="m-widget14">
						<div class="m-widget14__header">
							<h3 class="m-widget14__title">
								任务统计报表
							</h3>
							<span class="m-widget14__desc">
								个人任务统计报表查看
							</span>
						</div>
						<div class="row  align-items-center">
							<div class="col">
								<div id="m_chart_revenue_change" class="m-widget14__chart1" style="height: 180px"><svg height="180" version="1.1" width="243" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" style="overflow: hidden; position: relative; left: -0.4375px; top: -0.375px;"><desc style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">Created with Raphaël 2.2.0</desc><defs style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></defs><path fill="none" stroke="#00c5dc" d="M121.5,143.33333333333334A53.333333333333336,53.333333333333336,0,0,0,174.2735447138144,82.29302765130979" stroke-width="2" opacity="1" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); opacity: 1;"></path><path fill="#00c5dc" stroke="#ffffff" d="M121.5,146.33333333333334A56.333333333333336,56.333333333333336,0,0,0,177.24205660396646,81.85951045669596L200.66031707072162,78.43954147696468A80,80,0,0,1,121.5,170Z" stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="none" stroke="#f4516c" d="M174.2735447138144,82.29302765130979A53.333333333333336,53.333333333333336,0,0,0,131.91744960680708,37.693966044689645" stroke-width="2" opacity="0" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); opacity: 0;"></path><path fill="#f4516c" stroke="#ffffff" d="M177.24205660396646,81.85951045669596A56.333333333333336,56.333333333333336,0,0,0,132.50343114718999,34.75175163470344L136.14953850957247,16.44463975034482A75,75,0,0,1,195.7127972538015,79.16207013465439Z" stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><path fill="none" stroke="#716aca" d="M131.91744960680708,37.693966044689645A53.333333333333336,53.333333333333336,0,1,0,121.48324483945652,143.33333070143885" stroke-width="2" opacity="0" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); opacity: 0;"></path><path fill="#716aca" stroke="#ffffff" d="M132.50343114718999,34.75175163470344A56.333333333333336,56.333333333333336,0,1,0,121.48230236167595,146.33333055339477L121.47643805548573,164.9999962988984A75,75,0,1,1,136.14953850957247,16.44463975034482Z" stroke-width="3" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);"></path><text x="121.5" y="80" text-anchor="middle" font-family="&quot;Arial&quot;" font-size="15px" stroke="none" fill="#000000" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-family: Arial; font-size: 15px; font-weight: 800;" font-weight="800" transform="matrix(1.1854,0,0,1.1854,-22.619,-16.8727)" stroke-width="0.8435872395833334"><tspan dy="6" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">任务报表统计</tspan></text><text x="121.5" y="100" text-anchor="middle" font-family="&quot;Arial&quot;" font-size="14px" stroke="none" fill="#000000" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0); text-anchor: middle; font-family: Arial; font-size: 14px;" transform="matrix(1.1111,0,0,1.1111,-13.5104,-10.2222)" stroke-width="0.8999999999999999"><tspan dy="5" style="-webkit-tap-highlight-color: rgba(0, 0, 0, 0);">10</tspan></text></svg></div>
							</div>
							<div class="col">
								<div class="m-widget14__legends">
									<div class="m-widget14__legend">
										<span class="m-widget14__legend-bullet m--bg-accent"></span>
										<span class="m-widget14__legend-text">
											86% 重要任务
										</span>
									</div>
									<div class="m-widget14__legend">
										<span class="m-widget14__legend-bullet m--bg-warning"></span>
										<span class="m-widget14__legend-text">
											85% 一般任务
										</span>
									</div>
									<div class="m-widget14__legend">
										<span class="m-widget14__legend-bullet m--bg-brand"></span>
										<span class="m-widget14__legend-text">
											90% 紧急任务
										</span>
									</div>
								</div>
							</div>
						</div>
					</div>
					<!--end:: Widgets/Daily Sales-->
				</div>
				<div class="col-xl-4">
					<!-- 客户 订单 日程开始 -->
					<div class="m-widget24">
						<div class="m-widget24__item">
					        <h4 class="m-widget24__title">
					                     本月订单数
					        </h4><br>
					        <span class="m-widget24__desc">
					                    订单总金额
					        </span>
					        <span class="m-widget24__stats m--font-danger">
					            86980
					        </span>		
					        <div class="m--space-10"></div>
							<div class="progress m-progress--sm">
								<div class="progress-bar m--bg-danger" role="progressbar" style="width: 69%;" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
							</div>
							<span class="m-widget24__change">
								同比增长
							</span>
							<span class="m-widget24__number">
								86%
				            </span>
					    </div>		
					</div>
					<!-- 客户 订单 日程结束 -->
				</div>
			</div>
		</div>
	</div>
	
	
	
	
	<div class="row">
		<div class="col-xl-8">
			<div class="m-portlet m-portlet--mobile ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<h3 class="m-portlet__head-text">
								平台公告
							</h3>
						</div>
					</div>
				</div>
				<div class="m-portlet__body">
					<div class="tab-pane active show" id="m_widget5_tab1_content" aria-expanded="true">
						<!--begin::m-widget5-->
						<div class="m-widget5">
							<c:forEach var="xtNotice" items="${xtNoticeList }">
							<div class="m-widget5__item">
								<div class="m-widget5__pic">
									<!-- <img class="m-widget7__img" src="" alt=""> -->
								</div>
								<div class="m-widget5__content">
									<h4 class="m-widget5__title">
										<c:choose>
	                                        <c:when test="${fn:length(xtNotice.xt_title)>20 }">
	                                        	${fn:substring( xtNotice.xt_title ,0,20)}...
	                                        </c:when>
	                                        <c:otherwise>
	                                        	${xtNotice.xt_title }
	                                        </c:otherwise>
                                        </c:choose>
									</h4>
									<span class="m-widget5__desc">
										${xtNotice.xt_content }
									</span>
									<div class="m-widget5__info">
										<span class="m-widget5__author">
											发布人:
										</span>
										<span class="m-widget5__info-author m--font-info">
											${xtNotice.xt_userinfo_realName }
										</span>
										<span class="m-widget5__info-label">
											发布时间:
										</span>
										<span class="m-widget5__info-date m--font-info">
											${xtNotice.xt_createTime }
										</span>
									</div>
								</div>
								<div class="m-widget5__stats1">
									<span class="m-widget5__number">
										${xtNotice.xt_createTime }
									</span>
									<br>
								</div>
							</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-xl-4">
			<!--begin:: Widgets/Audit Log-->
			<div class="m-portlet m-portlet--full-height ">
				<div class="m-portlet__head">
					<div class="m-portlet__head-caption">
						<div class="m-portlet__head-title">
							<h3 class="m-portlet__head-text">
								个人任务
							</h3>
						</div>
					</div>
				</div>
				<div class="m-portlet__body">
				
				
					
					<div class="m-timeline-3">
						<div class="m-timeline-3__items">
							<c:forEach var="xtMessage" items="${xtMessageList }">
								<div class="m-timeline-3__item m-timeline-3__item--info">
									<span class="m-timeline-3__item-time">
										<fmt:formatDate value="${xtMessage.ctime }" pattern="HH:mm"/>
									</span>
									<div class="m-timeline-3__item-desc">
										<span class="m-timeline-3__item-text">
											${xtMessage.xt_meesage_content }
										</span>
										<br>
										<span class="m-timeline-3__item-user-name">
											<a href="#" class="m-link m-link--metal m-timeline-3__item-link">
												${xtMessage.fromName }
											</a>
										</span>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
					
					
					
				</div>
			</div>
			<!--end:: Widgets/Audit Log-->
		</div>
	</div>
</body>
</html>
