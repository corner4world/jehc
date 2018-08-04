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
		<!-- 基础插件样式开始 -->
		<%-- 
		<link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" /> 
		--%>
        <link href="${syspath }/deng/source/plugins/newAdmin/vendors/base/flaticon.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/css/icons.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/css/style.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/newAdmin/using/base/theme/default/style.bundle.css" rel="stylesheet" type="text/css" />
        <!-- 基础插件样式结束 -->
        <!-- 基础插件开始 -->
        <script src="${syspath}/deng/source/plugins/newAdmin/vendors/base/jquery.min.js" type="text/javascript"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/js/popper.min.js"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/js/bootstrap.min.js" type="text/javascript"></script>
        <!-- DataTables插件开始 -->
        <link href="${syspath }/deng/source/plugins/newAdmin/datatables/1.10.15/css/dataTables.bootstrap4.min.css" rel="stylesheet" type="text/css" />
        <script src="${syspath }/deng/source/plugins/newAdmin/datatables/1.10.15/js/jquery.dataTables.min.js" type="text/javascript"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/datatables/1.10.15/js/dataTables.bootstrap4.min.js" type="text/javascript"></script>
        <%-- <link href="${syspath }/deng/source/plugins/newAdmin/using/base/theme/smallleft/style.bundle.css" rel="stylesheet" type="text/css" /> --%>
        <link href="${syspath }/deng/source/plugins/newAdmin/vendors/base/datatables.bundle.css" rel="stylesheet" type="text/css" /> 
        <!-- DataTables插件结束 -->
        <!-- 基础插件结束 -->
        <!-- 日期选择器控件开始 -->
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/bootstrap-datepicker/css/bootstrap-datepicker.min.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/bootstrap-daterangepicker/daterangepicker.css" rel="stylesheet" type="text/css" />
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/bootstrap-timepicker/bootstrap-timepicker.min.css" rel="stylesheet" type="text/css" />
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/bootstrap-daterangepicker/daterangepicker.js" type="text/javascript"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/bootstrap-datepicker/js/bootstrap-datepicker.min.js" type="text/javascript"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/4.0.0/plugins/bootstrap-timepicker/bootstrap-timepicker.js" type="text/javascript"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-datepicker/locales/bootstrap-datepicker.zh-CN.min.js" type="text/javascript"></script>
        <!-- 日期选择器控件结束 -->
        <!-- 笼罩插件开始 -->
		<link type="text/css" rel="stylesheet" href="${syspath}/deng/source/plugins/other/alertplug/alert/alert.css"'/>
		<script type="text/javascript" src='${syspath}/deng/source/plugins/other/alertplug/alert/alert.js'></script>
		<!-- 笼罩插件结束 -->
        <!-- 提示插件开始 -->
        <link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootbox.js" type="text/javascript"></script>
        <!-- 提示插件结束 -->
		<!-- 公共模块包含上传控件，共同JS封装 -->
		<!-- 文件上传通用模块 -->
		<link href="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-fileinput/css/fileinput.css" media="all" rel="stylesheet" type="text/css" />
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-fileinput/js/fileinput.js" type="text/javascript"></script>
        <script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-fileinput/js/fileinput_locale_zh.js" type="text/javascript"></script>
		<!-- 右键插件-->
		<script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-contextmenu/BootstrapMenu.min.js" type="text/javascript"></script>
		<link href="${syspath}/deng/source/css/bootcommon.min.css" rel="stylesheet" type="text/css" />
		<!-- bootstrap验证框架开始 -->
		<link rel="stylesheet" type="text/css" href="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-validate/bootstrapValidator.css" />
		<script src="${syspath }/deng/source/plugins/newAdmin/bootstrap/bootstrap-validate/bootstrapValidator.js" type="text/javascript"></script>
		<!-- bootstrap验证框架结束 -->
		<script type="text/javascript" src="${syspath}/deng/source/js/boot.min.js"></script>
		<script src="${syspath}/deng/source/plugins/newAdmin/using/base/theme/default/scripts.bundle.js" type="text/javascript"></script>
		<link href="${syspath}/deng/source/css/bootlist.css" rel="stylesheet" type="text/css" />
	</head>
	<!-- upload模态框（Modal）开始 -->
	<div class="modal fade" id="jehcUploadModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop=”static”  aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">
						附件上传
					</h4>
				</div>
				<div class="modal-body">
	                <form role="form" id="jehcUploadForm" method="post" enctype="multipart/form-data">
                        <input id="jehcFile" name="picFile" class="file-loading" type="file" multiple = "false"  data-min-file-count="1" data-max-file-count="1" data-show-upload="true" data-show-preview="false"> <br>
                    </form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" onclick="closeUploadWin()">关闭</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- upload模态框（Modal）结束 -->
	
	<!-- image预览模态框（Modal）开始 -->
	<div class="modal fade" id="jehcImagePreModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop=”static”  aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">
						附件预览
					</h4>
				</div>
				<div class="modal-body">
					<div>
						<img src = "../deng/images/default/add_d.png" id="jehcImagePre">    
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	            </div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
	<!-- image预览模态框（Modal）结束 -->
</html>