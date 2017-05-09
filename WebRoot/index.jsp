<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML>
<html>
	<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,chrome=1" />
    <meta charset="UTF-8" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>${sys_pt_login }</title>
	</head>
	<body>
		<script type="text/javascript">
		/**
		 * 获取IE版本
		 * return ieVison:0未获取到IE版本,-1非IE,其他返回IE版本 1表示火狐
		 */
		var ieVison = 6;
		function getnavigator(){
			if(navigator.userAgent.indexOf("MSIE")>0){ 
				if(navigator.userAgent.indexOf("MSIE 6.0")>0){ 
					ieVison = 6;
				}else if(navigator.userAgent.indexOf("MSIE 7.0")>0){
					ieVison = 7;
				}else if(navigator.userAgent.indexOf("MSIE 8.0")>0){
					ieVison = 8;
				}else if(navigator.userAgent.indexOf("MSIE 9.0")>0){
					ieVison = 9;
				}else if(navigator.userAgent.indexOf("MSIE 10.0")>0){
					ieVison = 10;
				}else if(navigator.userAgent.indexOf("MSIE 11.0")>0){
					ieVison = 11;
				}else{
					ieVison = 0;
				}
			}else{
				if(navigator.userAgent.indexOf("Firefox")>0){
					ieVison = 1;
				}else{
					ieVison = -1;
				}
			} 
			return ieVison;
		}
		if(getnavigator()==9 || getnavigator()==8 || getnavigator()==7 || getnavigator() == 6 || getnavigator() == 0){
			document.write("平台支持IE10及以上浏览器或谷歌，火狐，360等浏览器");
		}else{
			/**判断手机登录即是否移动端登录**/
			window.location.href="<%=basePath%>login/login.html";
			/**
			if(phoneLogin() == 'phone'){
				window.location.href="<%=basePath%>login/login";
			}else{
			**/
				/**PC端登录
			 	window.location.href="<%=basePath%>login/login.html";
			}**/
		}
		/**校验PC端或移动端**/
		function phoneLogin(){
			if(navigator.userAgent.match(/Android/i) || navigator.userAgent.match(/webOS/i) || navigator.userAgent.match(/iPad/i) || navigator.userAgent.match(/iPhone/i) || navigator.userAgent.match(/iPod/i)){
				return 'phone';
			}else{
				return 'pc';
			}
		}
	</script>
	</body>
</html>
