<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
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
		<script type="text/javascript" src="${syspath}/deng/source/plugins/other/jquery/jquery-1.7.2.min.js"></script>
		<link href="${syspath}/deng/source/plugins/admin/index/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
        <!-- 基础插件样式结束 -->
        <!-- 自定义主题样式开始 -->
        <link href="${syspath}/deng/source/plugins/admin/index/global/css/components.min.css" rel="stylesheet" id="style_components" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/css/plugins.min.css" rel="stylesheet" type="text/css" />
        <!-- 自定义主题样式结束 -->
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
        <!-- 基础插件结束 -->
        <!-- DataTables插件开始 -->
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/datatables/datatables.min.css" rel="stylesheet" type="text/css" />
        <script src="${syspath}/deng/source/plugins/admin/index/global/scripts/datatable.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/datatables/datatables.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/datatables/plugins/bootstrap/datatables.bootstrap.js" type="text/javascript"></script>
        <!-- DataTables插件结束 -->
        <!-- 日期选择器控件开始 -->
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-daterangepicker/daterangepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-datepicker/css/bootstrap-datepicker3.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-timepicker/css/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/clockface/css/clockface.css" rel="stylesheet" type="text/css" />
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/moment.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-daterangepicker/daterangepicker.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-timepicker/js/bootstrap-timepicker.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/clockface/js/clockface.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
        <!-- 日期选择器控件结束 -->
        <!-- 其他插件开始 -->
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/fancybox/source/jquery.fancybox.pack.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-wysihtml5/wysihtml5-0.3.0.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-wysihtml5/bootstrap-wysihtml5.js" type="text/javascript"></script>
        <!-- 其他插件结束 -->
        <!-- 提示插件开始 -->
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootbox.js" type="text/javascript"></script>
        <!-- 提示插件结束 -->
        <!-- 添加选项卡插件开始 -->
        <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-addtab/css/bootstrap.addtabs.css" rel="stylesheet" type="text/css" />
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-addtab/js/bootstrap.addtabs.js" type="text/javascript"></script>
        <!-- 添加选项卡插件结束 -->
        <!-- 对话框插件开始 -->
		<link rel="stylesheet" type="text/css" href="${syspath}/deng/source/plugins/other/jquery/alert/zeroModal.css" />
		<script src="${syspath}/deng/source/plugins/other/jquery/alert/zeroModal.min.js"></script>
		<!-- 对话框插件结束 -->
		<!-- bootstrap验证框架开始 -->
		<link rel="stylesheet" type="text/css" href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-validate/bootstrapValidator.css" />
		<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-validate/bootstrapValidator.js" type="text/javascript"></script>
		<!-- bootstrap验证框架结束 -->
		<!-- jquery验证框架开始
		<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery-validation/js/jquery.validate.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery-validation/js/additional-methods.min.js" type="text/javascript"></script>
        <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery-validation/js/localization/messages_zh.min.js" type="text/javascript" />
		 -->
		<!-- jquery验证框架结束 -->
		<!-- 公共模块包含上传控件，共同JS封装 -->
		<script type="text/javascript" src="${syspath}/deng/source/js/boot.js"></script>
		<style type="text/css">
			table.dataTable.no-footer {
			    border-bottom: 1px solid #ffffff;
			}
			.uneditable-input {
			    padding: 6px 12px;
			    min-width: 56px;
			}
			.panel-default>.panel-heading {
			    color: #17C4BB;
			}
		</style>
	</head>
</html>