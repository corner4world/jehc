<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/deng/include/includePhone.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
<title>中国海洋大学</title>
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
		<div class="picture">
			<ul class="wxlist">
				<li><h1>中国海洋大学</h1>
					<div class="InfoTime">2013-03-26 20:46:31</div>
					<div class="InfoPicture">
						<img src="${sysPath }/deng/phone/images/1409305274.jpg" alt="中国海洋大学" />
					</div>
					<div class="InfoContent"></div></li>
			</ul>
			<!--评论列表 开始-->
			<script type="text/javascript" src="${sysPath }/deng/phone/js/jquery.form.js"></script>
			<script type="text/javascript">
				$(document).ready(function() {
					$('#frmComment').ajaxForm({
						success : complete,
						dataType : 'json'
					});
					function complete(data) {
						if (data.status == 1) {
							alert("评论成功!");
							$('#frmComment').resetForm();
						} else if (data.status == 0) {
							alert(data.info);
						}
						ChangeCode();
					}
					$('#frmComment').submit(function() { // 提交表单
						return false; //为了防止普通浏览器进行表单提交和产生页面导航（防止页面刷新？）返回false  

					});

				});
			</script>
			<script type="text/javascript">
				function ChangeCode() {
					var timenow = "&t=" + new Date().getTime();
					var obj = document.getElementById('CommentCode');
					if (obj){
						obj.src = "<>" + timenow;
					}
				}
			</script>
			<div class="comment">
				<ul class="wxlist">
					<li>
						<div class="CommentList">
							<h1>评论列表</h1>
						</div>
						<div class="page"></div>
						<div class="LeaveComment">
							<h1>我要评论</h1>
						</div>
						<form action="/channel/commentadd/l/cn" id="frmComment" method="post">
							<table border="0px" cellpadding="0px" cellspacing="3px" class="commenttable">
								<tbody>
									<tr>
										<td class="t1"><span class="required">评论内容*</span></td>
										<td class="t2"><textarea id="CommentContent" name="CommentContent"></textarea></td>
									</tr>
									<tr>
										<td class="t1">姓名</td>
										<td class="t2">
											<input id="GuestName" name="GuestName" type="text" value="" maxlength="100" />
											<input id="GuestID" name="GuestID" type="hidden" value="" maxlength="100" />
											<input id="InfoID" name="InfoID" type="hidden" value="66" maxlength="100" /></td>
									</tr>
									<tr>
										<td colspan="2">
										<input class="ui-btn-submit" type="submit" name="submit" value="发表评论" /></td>
									</tr>
								</tbody>
							</table>
							<input type="hidden" name="__hash__" value="9d25" />
						</form>
					</li>
				</ul>
			</div>
			<!--评论列表 结束-->
		</div>
	</div>
	<!--页脚开始-->
	<%@ include file="/WEB-INF/view/phone/include/common_footer.jsp"%>
	<!--页脚结束-->
</body>
</html>