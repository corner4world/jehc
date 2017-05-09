<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>工作流设计器</title>
    <script type="text/javascript">
		mxBasePath = '${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/';
	</script>
	<style type="text/css">
	.add-icon {
		background-image: url(<%= request.getContextPath() %>/editors/images/add.gif ) !important;
		background-repeat: no-repeat;
	}
	.del-icon {
		background-image: url(<%= request.getContextPath() %>/editors/images/del_gray.gif ) !important;
		background-repeat: no-repeat;
	}
	.pen-icon {
		background-image: url(<%= request.getContextPath() %>/editors/images/35912.gif ) !important;
		background-attachment: scroll !important;
		padding-left: 20px !important;
		background-position: 3px 0 !important;
		background-repeat: no-repeat !important;
	}
	.x-btn-inner-default-toolbar-small {
		max-width: 100%;
		/**隐藏**/
		display:block;
	}
    </style>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/mxClient.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/css/grapheditor.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/css/common.css" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/css/explorer.css" />
    <!-- 系统属性邓纯杰也重写过!呦西! -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/eastPanel.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/leftPanel.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/viewport.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/cellstyle.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/swimlane.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/addSidebarIcon.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/outline.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/cellsbutton.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/graphEditor.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/mainPanel.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/diagramStore.js"></script>
    <!-- 验证方法 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/validate.js"></script>
    <!-- 表单属性配置 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_event_form.js"></script>
    <!-- 会签属性配置 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_multiInstance.js"></script>
    <!-- 事件配置 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_event.js"></script>
    <!-- 一般配置 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_normal.js"></script>
	<!-- 数据属性 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_dataProperties.js"></script>
	<!-- 核心方法 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/all.js"></script>
	<!-- 基本流程事件配置 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_event_main.js"></script>
	<!-- 基本流程数据属性 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_datamainProperties.js"></script>
	<!-- 基本流程信号属性 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_signalmain.js"></script>
	<!-- 基本流程消息属性 -->
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_common/node_messagemain.js"></script>
	<!-- 自定义属性 -->
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/nodeAttribute.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/usertask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/manualTask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/end.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/callActivity.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/exclusiveGateway.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/start.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/errorBoundaryEvent.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/mailTask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/parallelGateway.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/receiveTask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/scriptTask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/serviceTask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/subProcess.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/businessRuleTask.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/timerBoundaryEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/transition.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/timerStartEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/messageStartEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/errorStartEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/signalStartEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/errorEndEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/terminateEndEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/cancelEndEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/inclusiveGateway.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/eventGateway.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/messageBoundaryEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/cancelBoundaryEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/compensationBoundaryEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/signalBoundaryEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/eventSubProcess.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/transactionProcess.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/pool.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/lane.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/timerCatchingEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/signalCatchingEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/messageCatchingEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/signalThrowingEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/compensationThrowingEvent.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/view/pc/lc-view/lc-design/archive/grapheditor/js/node_attribute/noneThrowingEvent.js"></script>
	<script type="text/javascript">
		if(!mxClient.isBrowserSupported()){
			Ext.example.msg('提示', "浏览器不支持");
		}
		mxGraphHandler.prototype.guidesEnabled = true;
	    mxGuide.prototype.isEnabledForEvent = function(evt){
	    	return !mxEvent.isAltDown(evt);
	    };
		mxEdgeHandler.prototype.snapToTerminals = true;
		mxUtils.alert = function(message){
			Ext.example.msg(message, '', '');
		};
	</script>
</head>
<body onload="main(); ">
	<input type="hidden" value="${lc_process_id }" id="lc_process_hideid"/>
</body>
</html>