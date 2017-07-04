<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<!-- IE 浏览作用 兼容模式打开页面。 chrome=1 开启 chrome 支持，适用多个核的浏览器。 -->
		<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
		<!-- 360 浏览器使用 webkit -->
		<meta name="renderer" content="webkit"/>
	   	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<link rel="icon" type="image/ico" href="${syspath }/deng/images/logo/logo.png" />
		<input type="hidden" id="lc_apply_model_biz_id" value="${lc_apply_model_biz_id }"/>
		<input type="hidden" value="${xt_functioninfoMethod }" id="xtFunctionInfoBtnStr">
	   	<!-- 主题技术Extjs支持 -->
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ext-all.js"></script>	
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/classic/locale/locale-zh_CN.js"></script>
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
	    	var collapsibleDefined=false;
	    	var tbarBtnMinWidth=0;
	    	var hideCollapseToolFlag=true;
	    	var lc_design_displaywin_for_edit = ${lc_design_displaywin_for_edit};
	    	var xt_userinfo_id; 
		</script>
		<c:if test="${not empty xtUserinfo.xt_userinfo_id}">
			<script>
			xt_userinfo_id = '${xtUserinfo.xt_userinfo_id}';
			</script>
		</c:if>
        <%
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
		    if(cookieC.getValue().indexOf("touch")>=0){
				%>
		<link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/classic/theme-crisp-touch/resources/theme-crisp-touch-all.css" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${syspath}/deng/source/css/index.css" />
				<%
			}else{
				if(cookieC.getValue().indexOf("triton")>=0){
				%>
		<link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/classic/theme-triton/resources/theme-triton-all.css" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${syspath}/deng/source/css/triton.css" />
				<%
				}else{
				%>
		<link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/classic/theme-crisp/resources/theme-crisp-all.css" type="text/css" />
		<link type="text/css" rel="stylesheet" href="${syspath}/deng/source/css/index.css" />
				<%
				}
			}
	    	%>
	    	<%
	    }else{
	    	%>
	    <link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/classic/theme-triton/resources/theme-triton-all.css" type="text/css" />
	    <link type="text/css" rel="stylesheet" href="${syspath}/deng/source/css/triton.css" />
	    	<%
	    }
        %>
		<link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/css/font-awesome.css" type="text/css" />
		<%
		if(null != cookieC){
			if(cookieC.getValue().indexOf("touch")>=0){
				%>
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/classic/theme-crisp-touch/theme-crisp-touch.js"></script>
		<style type="text/css">
			.x-column-header-checkbox .x-column-header-inner, .x-grid-checkcolumn-cell-inner {
	    		padding: 0px 0px 0px 0px;
			}
		</style>
				<%
			}else{
				if(cookieC.getValue().indexOf("triton")>=0){
				%>
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/classic/theme-triton/theme-triton.js"></script>
		<script type="text/javascript">collapsibleDefined=true;hideCollapseToolFlag=false;</script>
				<%
				}else{
				%>
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/classic/theme-crisp/theme-crisp.js"></script>
				<%
				}
			}
	    	%>
	    	<%
	    }else{
	    	%>
	    <script type="text/javascript" src="${syspath}/deng/source/plugins/e6/classic/theme-triton/theme-triton.js"></script>
		<script type="text/javascript">collapsibleDefined=true;hideCollapseToolFlag=false;</script>
	    	<%
	    }
        %>
        <script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ux/ProgressBarPager.js"></script>
		<!-- 公共模块包含上传控件，共同JS封装 -->
		<script type="text/javascript" src="${syspath}/deng/source/js/common.js"></script>
		<!-- 数据	权限按钮操作 -->
		<script type="text/javascript" src="${syspath}/deng/source/js/xtFunctionInfoStr.js"></script>
		<!-- 主题切换 -->
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/theme.js"></script>
		<!-- 时间精确时分秒 -->
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ux/datetimefield/Ext.ux.DateTimePicker.js"></script>
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ux/datetimefield/Ext.ux.DateTimeField.js"></script>
		<!-- 提示 -->		
		<link rel="stylesheet" href="${syspath}/deng/source/plugins/e6/packages/shared/example.css" type="text/css" />
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/packages/shared/examples.js"></script>
		<!-- 插件包含关闭Tab,下拉树 -->
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ux/TreePicker.js"></script>
		<script type="text/javascript" src="${syspath}/deng/source/plugins/e6/ux/TabCloseMenu.js"></script>
		<!-- Ajax拦截器 -->
		<script type="text/javascript" src="${syspath}/deng/source/js/AjaxInterceptor.js"></script>
		<!-- 打印支持 -->
		<script type="text/javascript" src="../deng/source/plugins/e6/ux/grid/Printer.js"></script> 
		<!-- Jquery支持 -->
		<script type="text/javascript" src="${syspath}/deng/source/plugins/other/jquery/jquery-1.7.2.min.js"></script>
		<script type="text/javascript">
			Ext.onReady(function(){
				/**
				Ext.Loader.setConfig({  
			        enabled:true//启用这个机制     
			    });
			    **/
				Ext.setGlyphFontFamily('FontAwesome');
				//Ext.tip.QuickTipManager;
				Ext.QuickTips.init();
				//设置为空, 否则会有冲突
    			Ext.form.Field.prototype.msgTarget='';
				/**屏蔽右键**/
				Ext.getDoc().on("contextmenu", function(e){
			    	//e.stopEvent();
				});
			});
		</script>
		<style type="text/css">
			.x-btn-inner-default-toolbar-small {
			<%
				String grid_toolbar_text_is_view = (String)application.getAttribute("grid_toolbar_text_is_view");
				if(null != grid_toolbar_text_is_view && "0".equals(grid_toolbar_text_is_view)){
					%>
						display:none;
					<%
				}
			%>
			}
			.addBtn{
			}
			.updateBtn{
			}
			.delBtn{
			}
			.jkBtn{
			}
			.refreshBtn{
			}
			.payBtn{
			}
			.backBtn{
			}
			.searchBtn{
			}
			.clearBtn{
			}
			.detailBtn{
			}
			.setBtn{
			}
			.completeBtn{
			}
			.exportBtn{
			}
			.printBtn{
			}
			/**
			.addBtn{
			  	background-color:#925e8b;
			}
			.updateBtn{
			  	background-color:#90c258;
			}
			.delBtn{
			 	background-color:#ff7b76;
			}
			.jkBtn{
			  	background-color:#039fbc;
			}
			.refreshBtn{
				background-color:#90EE90;
			}
			.payBtn{
				background-color:#ffc000;
			}
			.backBtn{
				background-color:#82d9ea;
			}
			.searchBtn{
				background-color:#68dff0;
			}
			.clearBtn{
				background-color:#B0C4DE;
			}
			.detailBtn{
				background-color:#cd97eb;
			}
			.setBtn{
				background-color:#FFEC8B
			}
			.completeBtn{
				background-color:#B0C4DE
			}
			.exportBtn{
				background-color:#B0C4DE
			}
			.printBtn{
				background-color:#A2B5CD
			}
			**/
			/**定义密码样式开始**/
			.x-form-pwd-trigger{
				background-position:right;
				background-image:url(../deng/images/login/password.png)!important;
				background-repeat:no-repeat;
				text-align:center;
				width:32px;
			}
			.x-form-pwd-trigger-default:before {
				content:"";
			}
			.x-form-pwd-trigger-default.x-form-pwd-trigger-over.x-form-pwd-trigger-focus {
			background-position:right;
			}
			.x-form-pwd-trigger-default.x-form-pwd-trigger-focus {
				background-position:right;
			}
			.x-form-pwd-trigger-default.x-form-pwd-trigger-over{
				background-position:right;
			}
			/**定义密码样式结束**/
			/**定义搜索样式开始**/
			.x-form-ss-trigger{
				background-position:right;
				background-image:url(../deng/images/top/index_search.png)!important;
				background-repeat:no-repeat;
				text-align:center;
				width:32px;
			}
			.x-form-ss-trigger-default:before {
				content:"";
			}
			.x-form-ss-trigger-default.x-form-ss-trigger-over.x-form-ss-trigger-focus {
			background-position:right;
			}
			.x-form-ss-trigger-default.x-form-ss-trigger-focus {
				background-position:right;
			}
			.x-form-ss-trigger-default.x-form-ss-trigger-over{
				background-position:right;
			}
			/**定义搜索样式结束**/
			/**定义清空样式开始**/
			.x-form-cl-trigger{
				background-position:right;
				background-image:url(../deng/images/icons/clear.png)!important;
				background-repeat:no-repeat;
				text-align:center;
				width:32px;
			}
			.x-form-cl-trigger-default:before {
				content:"";
			}
			.x-form-cl-trigger-default.x-form-cl-trigger-over.x-form-cl-trigger-focus {
			background-position:right;
			}
			.x-form-cl-trigger-default.x-form-cl-trigger-focus {
				background-position:right;
			}
			.x-form-cl-trigger-default.x-form-cl-trigger-over{
				background-position:right;
			}
			/**定义清空样式结束**/
			/**定义用户选择样式开始**/
			.x-form-userselect-trigger{
				background-position:right;
				background-image:url(../deng/images/icons/user.png)!important;
				background-repeat:no-repeat;
				text-align:center;
				width:32px;
			}
			.x-form-userselect-trigger-default:before {
				content:"";
			}
			.x-form-userselect-trigger-default.x-form-userselect-trigger-over.x-form-userselect-trigger-focus {
			background-position:right;
			}
			.x-form-userselect-trigger-default.x-form-userselect-trigger-focus {
				background-position:right;
			}
			.x-form-userselect-trigger-default.x-form-userselect-trigger-over{
				background-position:right;
			}
			/**定义用户选择样式结束**/
			/**定义用户组选择样式开始**/
			.x-form-usergroupselect-trigger{
				background-position:right;
				background-image:url(../deng/images/icons/users.png)!important;
				background-repeat:no-repeat;
				text-align:center;
				width:32px;
			}
			.x-form-usergroupselect-trigger-default:before {
				content:"";
			}
			.x-form-usergroupselect-trigger-default.x-form-usergroupselect-trigger-over.x-form-usergroupselect-trigger-focus {
			background-position:right;
			}
			.x-form-usergroupselect-trigger-default.x-form-usergroupselect-trigger-focus {
				background-position:right;
			}
			.x-form-usergroupselect-trigger-default.x-form-usergroupselect-trigger-over{
				background-position:right;
			}
			/**定义用户组选择样式结束**/
			
			/**定义用户组织机构选择样式开始**/
			.x-form-userorgselect-trigger{
				background-position:right;
				background-image:url(../deng/images/icons/contact_card.png)!important;
				background-repeat:no-repeat;
				text-align:center;
				width:32px;
			}
			.x-form-userorgselect-trigger-default:before {
				content:"";
			}
			.x-form-userorgselect-trigger-default.x-form-userorgselect-trigger-over.x-form-userorgselect-trigger-focus {
			background-position:right;
			}
			.x-form-userorgselect-trigger-default.x-form-userorgselect-trigger-focus {
				background-position:right;
			}
			.x-form-userorgselect-trigger-default.x-form-userorgselect-trigger-over{
				background-position:right;
			}
			/**定义用户组织机构选择样式结束**/
			
			<%
			if(null != cookieC && cookieC.getValue().indexOf("triton")>=0){
			%>
			.x-panel-header-defined{
			    background-image:none;
			    background-color:#5fa2dd;
			}
			<%
			}else if(null == cookieC){
				%>
			.x-panel-header-defined{
			    background-image:none;
			    background-color:#5fa2dd;
			}	
				<%
			}else{
				%>
			/*Panel自定义样式*/
			.x-panel-header-defined{
			    background-image:none;
			    background-color:#5fa2dd;/**#35baf6**/
			}
			.x-panel-header-default .x-tool-img{
				background-color:#fff;/**0e90d2,#dd514c**/
			}
			.x-accordion-item .x-accordion-hd{
				background:#fff;/**0e90d2,#dd514c**/
				border-color:#fff #cfcfcf #cfcfcf;
			}
			.x-accordion-hd .x-panel-header-title{
				text-transform:none;
			}
			.x-tab-bar-default{
				background-color:#fff;
			}
				<%
			}
			%>
			.x-title-text {
			    cursor: pointer;
			}
			.x-accordion-item .x-accordion-hd-fff{
			    background-image: none;
			    background: #fff;
			}
	        /* .tbar{ background-color:#5fa2dd;background-image:url();} */
		</style>
	</head>
</html>