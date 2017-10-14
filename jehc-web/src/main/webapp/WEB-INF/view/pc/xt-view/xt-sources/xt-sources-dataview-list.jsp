<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/include.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>静态资源DataView</title>  
	<script type="text/javascript" src="../view/pc/xt-view/xt-sources/xt-sources-dataview-list.js"></script>  
	<style type="">
		.card-box{  
		    width: 195px;  
		    height: 60px;  
		    float: left;  
		    border: 1px solid #ccc;  
		    background-color: #fff;  
		    border-radius: 3px;  
		    margin-left: 10px;  
		    margin-top: 10px;  
		    overflow: hidden;  
		    position: relative;  
		    -moz-user-select: none;  
		    -webkit-user-select: none;  
		    -ms-user-select: none;  
		    -khtml-user-select: none;  
		    user-select: none;  
		    cursor: pointer;  
		    font-family:微软雅黑,宋体,Arial,Helvetica,Verdana,sans-serif;  
		    color:#000000;  
		    font-size:12px;  
		}  
		.card-box.active{  
		    border: 2px solid #ff5d5b;  
		    padding:0 0px;  
		    background: url(../deng/images/img/duihao.png) right top no-repeat;  
		}  
		.card-box-img{  
		    float: left;  
		    width: 60px;  
		    height: 60px;  
		    line-height: 58px;  
		    margin:0px;  
		    padding:0px;  
		    background-color: #EBEBEB;  
		    border-right: 1px solid #ccc;  
		}  
		.card-box-img img{  
		    margin-left: 9px;  
		    margin-top:5px;  
		    border-radius: 45px;  
		    vertical-align:middle;  
		    padding:0px;  
		}  
		.card-box-content{  
		    float: left;  
		    padding-left: 6px;  
		    padding-top: 4px;  
		}  
		.card-box-content p{  
		    overflow: hidden;  
		    white-space: nowrap;  
		    text-overflow: ellipsis;  
		    width: 120px;  
		    padding:0px;  
		    margin:0px;  
		}  
	</style>
</head>  
<body>  
</body>  
</html> 