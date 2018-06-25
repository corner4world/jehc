<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>这里是产品名称-产品详细页面</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="某手机网站模板 jehc.com" />
<meta name="keywords" content="这里填写你的关键字" />
<meta name="description" content="jehc（jehc.com）是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
</head>
<body>
	<!--顶部开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_header.jsp"%>
	<!--顶部结束-->
	<div id="content">
		<div class="product">
			<ul class="wxlist">
				<li><h1>这里是产品名称</h1>
					<div class="InfoPicture">
						<img src="images/1409289631.JPG" alt="这里是产品名称" />
					</div>
					<div class="f2">编号：123456789</div>
					<div class="InfoContent">
						<div style="text-align: center">
							<br /> <img alt="" src="${sysPath }/deng/phone/images/140876194575.jpg" style="width: 780px; height: 521px" />
						</div>
					</div></li>
			</ul>
			<!--在线订购 开始-->
			<script type="text/javascript" src="${sysPath }/deng/phone/js/jquery.form.js"></script>
			<script type="text/javascript">    
			$(document).ready(function(){
	        	$('#frmOrder').ajaxForm({
	            success: complete,
	            dataType: 'json',
				beforeSubmit: check
        	});
	  		function check(){
				if( $("#verifycode").val() == ""){
					alert("验证码不能为空！");
					$("#verifycode").focus();
					return false;
				}
				return true;
			}
        	function complete(data){
	            if (data.status==1){
	                alert(data.info);
	                $('#frmOrder').resetForm();
					$("#ProductName").val("这里是产品名称");
	
	            }else if(data.status==0){
	                alert(data.info);
	            }
	            ChangeOrderCode();
        	};
	        $('#frmOrder').submit(function(){  // 提交表单
	            return false;  //为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false  
	        });
			$("#ProductName").val("这里是产品名称");
	    });
		</script>
		<script type="text/javascript">	
		function ChangeOrderCode(){
		var timenow = new Date().getTime();
		var obj = document.getElementById('OrderCode'); 
		if( obj ) obj.src = '<>'+timenow;
		}
		</script>
		<div class="order">
			<ul class="wxlist">
				<li>
					<div class="WantOrder">
						<h1>我要订购</h1>
					</div>
					<form method="post" id="frmOrder" action="/channel/orderAdd/l/cn">
						<table border="0px" cellpadding="0px" cellspacing="3px" class="ordertable">
							<tbody>
								<tr>
									<td class="t1">订购产品</td>
									<td class="t2"><input id="ProductName" name="ProductName" type="text" maxlength="100" value="" /></td>
								</tr>
								<tr>
									<td class="t1">姓名</td>
									<td class="t2"><input id="GuestName" name="GuestName" type="text" maxlength="100" value="" /></td>
								</tr>
								<tr>
									<td class="t1">订购数量</td>
									<td class="t2"><input id="Quantity" name="Quantity" type="text" maxlength="100" value="" /></td>
								</tr>
								<tr>
									<td class="t1">电话</td>
									<td class="t2"><input id="Telephone" name="Telephone" type="text" maxlength="100" value="" /></td>
								</tr>
								<tr>
									<td class="t1">QQ</td>
									<td class="t2"><input id="QQ" name="QQ" type="text" maxlength="100" value="" /></td>
								</tr>
								<tr>
									<td class="t1">Email</td>
									<td class="t2"><input id="Email" name="Email" type="text" maxlength="100" value="" /></td>
								</tr>
								<tr>
									<td class="t1">地址</td>
									<td class="t2"><input id="Address" name="Address" type="text" maxlength="100" value="" /></td>
								</tr>
								<tr>
									<td class="t1">备注</td>
									<td class="t2"><textarea id="Remark" name="Remark"></textarea></td>
								</tr>
								<tr>
									<td class="t1"><span class="required">验证码*</span></td>
									<td class="t2"><input id="verifycode" name="verifycode" type="text" maxlength="4" style="width: 80px;" />&nbsp; <img src="&lt;&gt;" onClick="ChangeOrderCode()" style="cursor: pointer;" id="OrderCode" align="absMiddle" /></td>
								</tr>
								<tr>
									<td colspan="2"><input class="ui-btn-submit" type="submit" name="submit" value="提交订单" /></td>
								</tr>
							</tbody>
						</table>
						<input type="hidden" name="__hash__" value="25546544" />
					</form>
				</li>
			</ul>
		</div>
		<!--在线订购 结束-->
	</div>
	</div>
	<!--页脚开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_footer.jsp"%>
	<!--页脚结束-->
</body>
</html>