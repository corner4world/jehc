<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>  
<head>  
<meta charset="UTF-8">  
<title>Logback编辑器</title>  
	<link rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/theme/ambiance.css">
	<link type="text/css" rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/lib/codemirror.css" />
	<link type="text/css" rel="stylesheet" href="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/addon/hint/show-hint.css" />
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/lib/codemirror.js"></script>
	<script type="text/javascript" src="${syspath}/deng/editor/codemirroreditor/js/codemirror-5.2/mode/xml/xml.js"></script>
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
		<textarea id="code" name="code">${xtLogbackContent }</textarea>
	</form>
    <script>
    	var dummy = {
	        attrs: {
	          color: ["red", "green", "blue", "purple", "white", "black", "yellow"],
	          size: ["large", "medium", "small"],
	          description: null
	        },
	        children: []
	      };
	
	      var tags = {
	        "!top": ["top"],
	        "!attrs": {
	          id: null,
	          class: ["A", "B", "C"]
	        },
	        top: {
	          attrs: {
	            lang: ["en", "de", "fr", "nl"],
	            freeform: null
	          },
	          children: ["animal", "plant"]
	        },
	        animal: {
	          attrs: {
	            name: null,
	            isduck: ["yes", "no"]
	          },
	          children: ["wings", "feet", "body", "head", "tail"]
	        },
	        plant: {
	          attrs: {name: null},
	          children: ["leaves", "stem", "flowers"]
	        },
	        wings: dummy, feet: dummy, body: dummy, head: dummy, tail: dummy,
	        leaves: dummy, stem: dummy, flowers: dummy
	      };
	
	      function completeAfter(cm, pred) {
	        var cur = cm.getCursor();
	        if (!pred || pred()) setTimeout(function() {
	          if (!cm.state.completionActive)
	            cm.showHint({completeSingle: false});
	        }, 100);
	        return CodeMirror.Pass;
	      }
	
	      function completeIfAfterLt(cm) {
	        return completeAfter(cm, function() {
	          var cur = cm.getCursor();
	          return cm.getRange(CodeMirror.Pos(cur.line, cur.ch - 1), cur) == "<";
	        });
	      }
	
	      function completeIfInTag(cm) {
	        return completeAfter(cm, function() {
	          var tok = cm.getTokenAt(cm.getCursor());
	          if (tok.type == "string" && (!/['"]/.test(tok.string.charAt(tok.string.length - 1)) || tok.string.length == 1)) return false;
	          var inner = CodeMirror.innerMode(cm.getMode(), tok.state).state;
	          return inner.tagName;
	        });
	      }
		  window.onload = function(){
			window.editor = CodeMirror.fromTextArea(document.getElementById('code'),{
				mode:'xml',
				indentWithTabs:true,
				smartIndent:true,
				lineNumbers:true,//显示行数
				lineWrapping:true,//在长行时文字是换行(wrap)还是滚动(scroll)，默认为滚动(scroll)。
				matchBrackets:true,
				autofocus:true,
				/**theme:"colorforth",//主题**/
				textWrapping:false,
				extraKeys:{ 
				  "'<'":completeAfter,
		          "'/'":completeIfAfterLt,
		          "' '":completeIfInTag,
		          "'='":completeIfInTag,
		          "Tab":"autocomplete"},//输入s然后Tab就可以弹出选择项说明Ctrl：输入s然后ctrl就可以弹出选择项
				hintOptions:{schemaInfo:tags}
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