<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<head>  
<meta charset="UTF-8">  
<title></title>  
</head>  
<script type="text/javascript">
var basePath = '<%=basePath%>';
</script>
<script type="text/javascript">
	var win = top;
	if(window.opener != null) {win=opener.top; window.close();}
	win.location=basePath;
	/**
	top.Ext.Msg.alert('提示', "您的账号由于长时间没有操作已经失效！请重新登录!",function(){
		var win = top;
		if(window.opener != null) {win=opener.top; window.close();}
		win.location=basePath;
	});
	**/
</script>