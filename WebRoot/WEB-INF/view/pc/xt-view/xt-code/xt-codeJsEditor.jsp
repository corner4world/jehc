<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>jEhc前端运行器</title>  
	<link rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/theme/base16-light.css">
	<link type="text/css" rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/lib/codemirror.css" />
	<link type="text/css" rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/show-hint.css" />
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/lib/codemirror.js"></script>
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/sql/sql.js"></script>
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/show-hint.js"></script>
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/sql-hint.js"></script>
	<!--语言高亮支持文件：语言放在mode文件件中-->  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/clike/clike.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/xml/xml.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/javascript/javascript.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/vbscript/vbscript.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/css/css.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/htmlmixed/htmlmixed.js"></script>
	
	<!--主题文件:主题放在theme文件件中-->  
	<link rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/theme/eclipse.css">
	
	
	
	<!--引入js，绑定Vim
	<link rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/dialog/dialog.css">  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/keymap/vim.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/search/searchcursor.js"></script> 
	-->   
	<!--改善vim输入命令时的样式
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/dialog/dialog.js"></script>
	-->  
	<!--支持代码折叠
	<link rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/fold/foldgutter.css"/>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/fold/foldcode.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/fold/foldgutter.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/fold/brace-fold.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/fold/comment-fold.js"></script>
	-->  
	<!--全屏模式
	<link rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/display/fullscreen.css">  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/display/fullscreen.js"></script>
	-->  
	<!--括号匹配  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/edit/matchbrackets.js"></script>
	-->
	<!--自动补全
	<link rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/show-hint.css">  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/show-hint.js"></script>  
	<script src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/anyword-hint.js"></script>
	-->  
	<style>
		.CodeMirror {border: 0px solid black;height:100%}
		body {
	     overflow-x:hidden;  
	     overflow-y:hidden; 
		}
	</style>
</head>  
<body>  
	<form action="../xtCodeController/jsRun" method="post">
	<input type="hidden" name="jsRunContent" id="jsRunContent">
	</form>
	<form>
		<textarea id="code" name="code"></textarea>
	</form>
    <script>
	   	var editor;
	    window.onload = function(){
	    	editor = CodeMirror.fromTextArea(document.getElementById("code"), {  
		        lineNumbers:true,
		        styleActiveLine:true,
		        matchBrackets:true,
		        mode:"text/javascript",  
		        width:"100%",
		        height:"100%",
		        indentWithTabs:true,
				smartIndent:true,
				lineNumbers:true,//显示行数
				matchBrackets:true,
				autofocus:true,
				theme:"base16-light",
				textWrapping:true,
				extraKeys:{"Ctrl-Q":"autocomplete"},//输入s然后Tab就可以弹出选择项说明Ctrl：输入s然后ctrl就可以弹出选择项
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
	  
	   function runJs(){
		   var val = setTextareaValue();
		   document.getElementById("jsRunContent").value=val;
		   document.forms[0].submit();
	   }
    </script>
</body>  
</html> 