<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="fn" uri="/WEB-INF/tld/fn.tld"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
	<!-- IE 浏览作用 兼容模式打开页面。 chrome=1 开启 chrome 支持，适用多个核的浏览器。 -->
	<meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
	<meta charset="UTF-8">  
	<title>${sys_pt_login }</title>  
	<link rel="icon" type="image/ico" href="${syspath}/deng/images/icons/system.png" />
	<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
	<meta name="format-detection" content="telephone=no">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<!-- 基础插件样式开始 -->
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/jquery/jquery-1.7.2.min.js"></script>
	<link href="${syspath}/deng/source/plugins/admin/index/global/plugins/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/simple-line-icons/simple-line-icons.min.css" rel="stylesheet" type="text/css" />
    <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-switch/css/bootstrap-switch.min.css" rel="stylesheet" type="text/css" />
    <!-- 基础插件样式结束 -->
    <!-- 自定义主题样式开始 -->
    <link href="${syspath}/deng/source/plugins/admin/index/global/css/components-md.min.css" rel="stylesheet" id="style_components" type="text/css" />
    <link href="${syspath}/deng/source/plugins/admin/index/global/css/plugins-md.min.css" rel="stylesheet" type="text/css" />
    <!-- 自定义主题样式结束 -->
    <!-- 基础插件开始 -->
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery.min.js" type="text/javascript"></script>
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/js.cookie.min.js" type="text/javascript"></script>
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-hover-dropdown/bootstrap-hover-dropdown.min.js" type="text/javascript"></script>
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery-slimscroll/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/jquery.blockui.min.js" type="text/javascript"></script>
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-switch/js/bootstrap-switch.min.js" type="text/javascript"></script>
    <!-- 基础插件结束 -->
    <!-- 提示插件开始 -->
    <link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-toastr/toastr.min.css" rel="stylesheet" type="text/css" />
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-toastr/toastr.min.js" type="text/javascript"></script>
    <script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootbox.js" type="text/javascript"></script>
    <!-- 提示插件结束 -->
	<!-- bootstrap验证框架开始 -->
	<link rel="stylesheet" type="text/css" href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-validate/bootstrapValidator.css" />
	<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap-validate/bootstrapValidator.js" type="text/javascript"></script>
	<!-- bootstrap验证框架结束 -->
	<!-- 公共模块包含上传控件，共同JS封装 -->
	<script type="text/javascript" src="${syspath}/deng/source/js/boot.js"></script>
	<style>
	.modal.in .modal-dialog{-webkit-transform:translate(0,-50%);-ms-transform:translate(0,-50%);-o-transform:translate(0,-50%);transform:translate(0,-50%)}
	.modal-dialog{position:absolute;width:auto;margin:10px auto;left:0;right:0;top:50%;}
	@media (min-width:400px){.modal-dialog{width:400px}
 	.modal-body{
           min-height:420px;
    }
    .input-icon>.form-control {
	    height: 50px;
	}
	.form-control {
	    border: 1px solid #f5f5f5;
	    border-radius: 0px;
	}
	.input-icon>i {
	    margin: 18px 2px 4px 10px;
	    font-size: 20px;
	}
	.modal .modal-header {
	    background: #32c5d2;
	    height: 70px;
	}
	.modal-title {
		color:#ffffff;
		margin-top: 5px;
	}
	.modal-backdrop, .modal-backdrop.fade.in {
	    background-color: #f5f5f5!important;
	}
	.modal-backdrop.in {
	    opacity: .5;
	    filter: alpha(opacity=50);
	}
	.btn-primary {
	    color: #fff;
	    background-color: #32c5d2;
	    border-color: #32c5d2;
	}
	
	.modal-footer {
	    padding: 15px;
	    text-align: right;
	    border-top: 0px solid #e5e5e5;
	}
	</style>
	<script type="text/javascript">
    	var basePath = "${syspath}";
    	var sys_pt_login = "${sys_pt_login}";
	</script>
</head>
<body>
	<!-- 模态框（Modal） -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" data-backdrop=”static” aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title" id="myModalLabel">
						${sys_pt_login }
					</h4>
				</div>
				<div class="modal-body">
					<form id="loginForm" method="post">
						<div class="form-group">
		                    <label class="control-label"><font style="font-size:16px;font-family:楷体;color:#CFCFCF;">输入登录信息时，请注意周边环境</font></label>
		                    <div class="input-icon">
		                        <i class="fa fa-user"></i>
		                        <input class="form-control placeholder-no-fix" type="text" placeholder="请输入账号" name="userName" id="userName" /> 
		                    </div>
		                </div>
		                <div class="form-group">
		                    <!-- <label class="control-label">密码</label> -->
		                    <div class="input-icon">
		                    <i class="fa fa-lock"></i>
		                    <input class="form-control placeholder-no-fix" type="password" placeholder="请输入密码" name="password" id="password"/> 
		                    </div>
		                </div>
		                <div class="row">
		                    <div class="col-sm-6">
		                    	 <div class="form-group">
				                    <div class="input-icon">
					                    <i class="fa fa-sign-out"></i>
					                    <input class="form-control" type="text" placeholder="请输入验证码" name="validateCode" id="validateCode"/> 
				                    </div>
			                     </div>
		                    </div>
		                	<div class="col-sm-3">
		                    	<img src="${syspath}/VerifyCodeServlet" id="safecode" width="180" onclick="this.src=${syspath}/VerifyCodeServlet"/>
		                    	<br><a href="javascript:clickYZM()">换一张</a><br><br>
		                    </div>
		                </div>
		                <div class="form-actions">
		                    <label class="rememberme mt-checkbox mt-checkbox-outline">
		                        <input type="checkbox" name="readme" id="readme" value="1" /> 记住我？
		                        <span></span>
		                    </label>
		                </div>
		                <div class="form-group">
		                    <input type="button" id="loginBtn" class="btn btn-primary" style="width: 370px;" onclick="login()" value="登录">
		                </div>
	                </form>
				</div>
				<div class="modal-footer">
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal -->
	</div>
</body>
<script type="text/javascript">
function callFocus(){
	var userName = $('#userName').val();
	var password = $('#password').val();
	var validateCode = $('#validateCode').val();
	if(userName == null || userName == ''){
		return  $('#userName')[0].focus();
	}
	if(password == null || password == ''){
		return  $('#password')[0].focus();
	}
	if(validateCode == null || validateCode ==''){
		return  $('#validateCode')[0].focus();
	}
}
$(document).ready(function() {
	if(getnavigator()==7 || getnavigator()==8 || getnavigator()==9 || getnavigator() == 6 || getnavigator() == 0){
		document.write("平台支持IE10及以上浏览器或谷歌，火狐，360极速模式等浏览器");
		return;
	}
	$('#myModal').modal({backdrop: 'static', keyboard: false});  
	if(getCookie('readme') == 'readme'){
		$("[name='readme']").attr("checked",'true');
		$('#userName').val(getCookie('XTUSERNAMECOOKIE'));
		$('#password').val(getCookie('XTUSERPASSWORDCOOKIE'));
	}
});

//刷新验证码
function clickYZM(){
	document.getElementById('safecode').src = basePath+"/VerifyCodeServlet?"+new Date();
}

$('#loginForm').bootstrapValidator({
  message:'此值不是有效的',
  feedbackIcons:{
  },
  fields:{
	  userName:{
          validators:{
              notEmpty:{
                  message:'用户账号不能为空'
              },
              stringLength:{
                  min:1,
                  max:30,
                  message:'用户账号字符长度不能超过30个'
              }
          }
      },
   	  password:{
         validators:{
             notEmpty:{
                 message:'密码不能为空'
             },
             stringLength:{
                 min:1,
                 max:20,
                 message:'密码字符长度不能超过20个'
             }
         }
     },
     validateCode:{
         validators:{
             notEmpty:{
                 message:'验证码不能为空'
             },
             stringLength:{
                 min:6,
                 max:6,
                 message:'验证码只能为6个字符'
             }
         }
     }
  }
});

$(function(){
	document.onkeydown = function(e){ 
	    var ev = document.all ? window.event : e;
	    if(ev.keyCode==13) {
	    	login();
	     }
	}
});  
//提交登录form
function login(){
	callFocus();
	var bootform =  $('#loginForm');
	if(typeof(bootform) == "undefined" ||null == bootform || '' == bootform){
		window.parent.toastrBoot(4,"未能获取到form对象!");
		return;
	}
	//验证
	var boostrapValidator = bootform.data('bootstrapValidator');
	boostrapValidator.validate();
	//验证有效开启发送异步请求
	if(boostrapValidator.isValid()){
		$("#loginBtn").attr("disabled",true);  
		$("#loginBtn").val("正在登录中......")
		$.ajax({
            url:basePath+'/login/login',
            type:'POST',//PUT DELETE POST
            data:bootform.serialize(),
            success:function(result){
            	call(result);
            }, 
            error:function(){
            	$("#loginBtn").val("登录");
            	$("#loginBtn").attr("disabled",false); 
            }
        })
	}else{
		//window.parent.toastrBoot(4,"存在不合法的字段!");
	}
}

function call(result){
	try{
		var obj = eval("(" + result + ")");
		if(obj.success == false){
			clickYZM();
			window.parent.toastrBoot(4,obj.msg);
			$("#loginBtn").val("登录");
			$("#loginBtn").attr("disabled",false); 
		}else{
			clearCookie('readme');
			clearCookie('XTUSERNAMECOOKIE');
			clearCookie('XTUSERPASSWORDCOOKIE');
			if($('#readme').is(':checked')){
				if(null == getCookie('readme') || '' == getCookie('readme')){
					setCookie('readme','readme',240);
					setCookie('XTUSERNAMECOOKIE',$('#userName').val(),240);
					setCookie('XTUSERPASSWORDCOOKIE',$('#password').val(),240);
				}
			}
			$("#loginBtn").val("身份认证成功，开始进入平台......");
			window.parent.toastrBoot(3,"身份认证成功，开始进入平台......");
			window.location.href=basePath+"/index/index.html";
		}
	}catch(e){
		$("#loginBtn").val("登录");
		$("#loginBtn").attr("disabled",false); 
	}
}


document.onreadystatechange = pagecomplete;
function pagecomplete() { 
	if(document.readyState == "complete") {
		setTimeout(function () { 
			callFocus();
		}, 500);
	}
} 
</script>
</html> 