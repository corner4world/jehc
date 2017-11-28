<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>无权操作页面</title>  
<style type="text/css">
body {margin: 0px; padding:0px; font-family:"微软雅黑", Arial, "Trebuchet MS", Verdana, Georgia,Baskerville,Palatino,Times; font-size:16px;}
div{margin-left:auto; margin-right:auto;}
a {text-decoration: none; color: #1064A0;}
a:hover {color: #0078D2;}
img { border:none; }
h1,h2,h3,h4 {
/*	display:block;*/
	margin:0;
	font-weight:normal; 
	font-family: "微软雅黑", Arial, "Trebuchet MS", Helvetica, Verdana ; 
}
h1{font-size:20px; color:#0188DE; padding:20px 0px 10px 0px;}
h2{color:#0188DE; font-size:14px; padding:10px 0px 40px 0px;}

#page{width:910px; padding:20px 20px 40px 20px; margin-top:80px;}
.button{width:180px; height:28px; margin-left:0px; margin-top:10px; background:#009CFF; border-bottom:1px solid #f5f5f5; text-align:center;}
.button a{width:180px; height:28px; display:block; font-size:14px; color:#fff; }
.button a:hover{ background:#5BBFFF;}
</style>
</head>  
<body>  
	<div id="page" style="border-style:dotted;border-color:#e4e4e4;line-height:30px;">
		<h1>抱歉，您没有权限操作，请联系管理员~</h1>
		<h2>Sorry, the site now can not be accessed. </h2>
		<font color="#666666">你操作的功能，没有权限，我们建议您联系管理员，谢谢！</font><br /><br />
		<div class="button">
			<a href="#" title="返回" target="_blank">返回</a>
		</div>
	</div>
	${ex }
</body>  
</html> 