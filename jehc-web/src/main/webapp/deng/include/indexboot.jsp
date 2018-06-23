<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<%@ taglib prefix="jEhcPermissionTag" uri="/WEB-INF/tld/jEhcPermissionTag.tld"%>
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
		<link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/other/iconfont/iconfont.css" rel="stylesheet" type="text/css" >
		<link href="${syspath }/deng/source/plugins/newAdmin/vendors/base/vendors.bundle.css" rel="stylesheet" type="text/css" />
		<link href="${syspath }/deng/source/plugins/newAdmin/using/base/theme/smallleft/style.bundle.css" rel="stylesheet" type="text/css" />
		<link href="${syspath }/deng/source/plugins/newAdmin/tab/css/style.min.css" rel="stylesheet">
		<link href="${syspath }/deng/source/plugins/newAdmin/vendors/base/perfect-scrollbar.min.css" rel="stylesheet">
		<!--begin::Base Scripts-->
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.mousewheel.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/perfect-scrollbar.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/popper.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.smooth-scroll.min.js" type="text/javascript"></script>		
		 <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
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
	</head>
</html>