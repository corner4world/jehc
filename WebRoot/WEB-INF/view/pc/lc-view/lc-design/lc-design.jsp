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
	.x-btn-over.x-btn-default-toolbar-small {
		/* border-color: #5fa2dd; */
		border-color: #FFFFFF;
		background-image: none;
		/* background-color: #5fa2d */d;
	}
	.x-btn-default-toolbar-small {
		/* border-color: #5fa2dd; */
		border-color: #ffffff;
		height:28px;
	}
	.x-btn-inner-default-toolbar-small {
		max-width: 100%;
		/**隐藏**/
		display:block;
	}
	/**tbar与bbar样式开始**/
	.x-toolbar-default {
		padding: 6px 0 6px 8px;
		border-style: solid;
		/* border-color: #cecece; */
		border-color: #FFFFFF;
		border-width: 1px;
		background-image: none;
		/* background-color: #fff; *//**#157fcc**/
	}
	.x-btn-default-toolbar-small {
		border-color: #fff; 
	}
	.x-btn-default-toolbar-small {
		-webkit-border-radius: 0px;
		-moz-border-radius: 0px;
		-ms-border-radius: 0px;
		-o-border-radius: 0px;
		border-radius: 0px;
		padding: 3px 3px 3px 3px;
		border-width: 0px;
		border-style: solid;
		/* background-color: #5fa2dd; */
	}
	/**tbar与bbar鼠标移上样式**/
	.x-btn-over.x-btn-default-toolbar-small {
		border-color: #fff;
		background-image: none;
		/* background-color: #5fa2dd; */
	}
	/**tbar与bbar鼠标点击时样式**/
	.x-btn.x-btn-menu-active.x-btn-default-toolbar-small, .x-btn.x-btn-pressed.x-btn-default-toolbar-small {
		/* border-color: #5fa2dd; */
		border-color: #ffffff;
		background-image: none;
		/* background-color: #5fa2dd; */
	}
	/**tbar与bbar鼠标点击后样式**/
	.x-btn-focus.x-btn-default-toolbar-small {
		background-image: none;
		/* background-color: #3892d3; */
		-webkit-box-shadow: #fff 0 0px 0px 0 inset, #fff 0 -1px 0px 0 inset, #fff -1px 0 0px 0 inset, #fff 0px 0 0px 0 inset;
		-moz-box-shadow: #fff 0 0px 0px 0 inset, #fff 0 -1px 0px 0 inset, #fff -1px 0 0px 0 inset, #fff 0px 0 0px 0 inset;
		box-shadow: #fff 0 0px 0px 0 inset, #fff 0 -1px 0px 0 inset, #fff -1px 0 0px 0 inset, #fff 1px 0 0px 0 inset;
	}
	/**tbar与bbar禁用后样式**/
	.x-btn.x-btn-disabled.x-btn-default-toolbar-small {
		background-image: none;
		/* background-color: #3892d3; */
	}
	.x-btn-wrap-default-toolbar-small.x-btn-arrow-right:after {
		width:0px;
		padding-right:0px;
		/* background-image: url(); */
	}
	/**button选项默认样式开始**/
	.x-btn-default-small {
	    /* border-color: #fff; */
	}
	.x-btn-icon-el-default-small.x-btn-glyph {
		/* color: #35baf6; */
	}
	.x-btn-default-small {
	    -webkit-border-radius: 0px;
	    -moz-border-radius: 0px;
	    -ms-border-radius: 0px;
	    -o-border-radius: 0px;
	    border-radius: 0px;
	    border-width: 1px;
	    border-style: solid;
	   /*  background-color: #5fa2dd; */
	}
	/**button选项默认样式结束**/
	/**tbar字体颜色开始**/
	.x-btn-inner-default-toolbar-small {
    	font: 300 14px/16px helvetica,arial,verdana,sans-serif;
    	/* color: #fff; */
    	padding: 0 2px;
    	max-width: 100%;
	}
	/**tbar字体颜色结束**/
	.x-tab-bar-default-top .x-box-scroller-tab-bar-default {
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