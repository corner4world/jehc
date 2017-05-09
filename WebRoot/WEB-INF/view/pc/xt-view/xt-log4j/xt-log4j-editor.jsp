<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>Log4j编辑器</title>  
	<link rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/theme/ambiance.css">
	<link type="text/css" rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/lib/codemirror.css" />
	<link type="text/css" rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/show-hint.css" />
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/lib/codemirror.js"></script>
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/properties/properties.js"></script>
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/show-hint.js"></script>
	<style>
		.CodeMirror {border: 0px solid black;height:100%}
		body {
	     overflow-x:hidden;  
	     overflow-y:hidden; 
		}
	</style>
</head>  
<body>  
	<form>
		<textarea id="code" name="code">${xtLog4jContent }</textarea>
	</form>
    <script>
	  window.onload = function(){
		window.editor = CodeMirror.fromTextArea(document.getElementById('code'),{
			mode:{name:"text/x-properties"},
			indentWithTabs:true,
			smartIndent:true,
			lineNumbers:true,//显示行数
			lineWrapping:true,//在长行时文字是换行(wrap)还是滚动(scroll)，默认为滚动(scroll)。
			matchBrackets:true,
			autofocus:true,
			/**theme:"colorforth",//主题**/
			textWrapping:false,
			extraKeys:{"Tab":"autocomplete"},//输入s然后Tab就可以弹出选择项说明Ctrl：输入s然后ctrl就可以弹出选择项
			hintOptions:{tables:{
			  users:{name: null, score: null, birthDate: null},
			  countries:{name: null, population: null, size: null}
			}}
	  });
	};
	
	//赋值
	function setTextareaValue(){
		//该方法得到的结果是经过转义的数据
		var val = editor.getValue();
		//该方法得到的结果是未经过转义的数据
		/**
		editor.toTextArea();
		editor.getTextArea().value;
		**/
		return val;
	}
    </script>
</body>  
</html> 