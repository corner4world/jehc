<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>在线留言</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="author" content="某手机网站模板 jehc.com" />
<meta name="keywords" content="这里填写你的关键字" />
<meta name="description" content="jehc（jehc.com）是最专业的HTML5移动建站资源分享、交流学习生态圈，为大家提供更多的手机建站资源。" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<script type="text/javascript" src="${sysPath }/deng/phone/js/jquery.form.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#frm').ajaxForm({
			success : complete,
			dataType : 'json',
			beforeSubmit : check
		});
		function check() {
			if ($("#MessageTitle").val() == "") {
				alert("标题不能为空");
				$("#MessageTitle").focus();
				return false;
			}
			if ($("#verifycode").val() == "") {
				alert("验证码不能为空！");
				$("#verifycode").focus();
				return false;
			}
			return true;
		}
		function complete(data) {
			if (data.status == 1) {
				alert("提交成功!");
				$('#frm').resetForm();
			} else if (data.status == 0) {
				alert(data.info);
			}
			ChangeCode();
		}
		$('#frm').submit(function() {
			return false; //为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false  
		});
	});
</script>
<script>
	function ChangeCode() {
		var timenow = new Date().getTime();
		var obj = document.getElementById('code');
		if (obj){
			obj.src = "<>" + timenow;
		}
	}
</script>
</head>
<body class="body_page">
	<!--顶部开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_header.jsp"%>
	<!--顶部结束-->
	<div id="content">
		<div class="guestbook">
			<ul class="wxlist">
				<li>
					<div class="ChannelName">在线留言</div>
					<form method="post" id="frm" action="/channel/guestbookadd/l/cn">
						<table border="0" cellpadding="0" cellspacing="3"
							class="guestbooktable">
							<tr>
								<td class="t1"><span class="required">标题*</span></td>
								<td class="t2"><input id="MessageTitle" name="MessageTitle" class="form-control" type="text" maxlength="100" value="" /></td>
							</tr>
							<tr>
								<td class="t1">内容</td>
								<td class="t2"><textarea class="form-control" id="MessageContent" rows="8" name="MessageContent"></textarea></td>
							</tr>
							<tr>
								<td class="t1">姓名</td>
								<td class="t2"><input id="GuestName" class="form-control" name="GuestName" type="text" maxlength="100" value="" /></td>
							</tr>
							<tr>
								<td class="t1">电话</td>
								<td class="t2"><input id="Contact" class="form-control" name="Contact" type="text" maxlength="100" value="" /></td>
							</tr>
							<tr>
								<td colspan="2"><input class="ui-btn-submit" type="submit" name="submit" value="提交" />
							</tr>
						</table>
						<input type="hidden" name="__hash__" value="20e9069c074db1487c38924f89012b3f_b88e23258e1340f488f4b0c6cf9116f3" />
					</form>
				</li>
			</ul>
			<div class="clear"></div>
		</div>
	</div>
	<!--页脚开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_footer.jsp"%>
	<!--页脚结束-->
</body>
</html>