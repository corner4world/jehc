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
<title>图片选择</title>  
<style type="text/css">
.x-btn-default-small {
-webkit-border-radius: 0px;
-moz-border-radius: 0px;
-ms-border-radius: 0px;
-o-border-radius: 0px;
border-radius: 0px;
padding: 3px 3px 3px 3px;
border-width: 0px;
border-style: solid;
background-color: #f5f5f5;
}
</style>
</head>  
<body>  
<script type="text/javascript">
Ext.onReady(function(){
    Ext.create('Ext.FormPanel',{   
    	renderTo:Ext.getBody(),
    	waitMsgTarget:true,
		defaultType:'textfield',
		fieldDefaults:{
	        labelWidth:70,
	        labelAlign:"left",
	        flex:1,
	        margin:'4 5 4 5'
	    },
        items:[
        <%
        	List<String> imgList= (List<String>)request.getAttribute("imgList");
        	String xt_menuinfo_images = (String)request.getAttribute("xt_menuinfo_images");
        	for(int i = 0; i < imgList.size(); i++){
        		String img = imgList.get(i);
        		if(!img.equals(xt_menuinfo_images)){
        			%>
	        		{
			            xtype:'button',
			            tooltip:'<%=img%>',
			            icon:'${syspath}/deng/images/icons/<%=img%>',
			            handler:function(){ 
			            	setImg('<%=img%>');
			            } 
			        }
	        		<%
	        		if(i < (imgList.size()-1)){
	        		%>
	        		,
	        		<%
        			}
        		}
        	}
        	if(null != xt_menuinfo_images && !"".equals(xt_menuinfo_images)){
        		%>,
        		{
		            text:'<font color="red">已选择图标</font>',
		            xtype:'button',
		            icon:'${syspath}/deng/images/icons/<%=xt_menuinfo_images%>',
		            handler:function(){ 
		            	setImg('<%=xt_menuinfo_images%>');
		            } 
		        }
        		<%
        	}
        %>
        ]
    });
});
function setImg(imgs){
	/**iframe中获取父窗体文本框值方法**/
  	var xt_menuinfo_images = window.parent.Ext.getCmp('xt_menuinfo_images').getRawValue();
  	console.info("获取前值:"+xt_menuinfo_images);
  	window.parent.Ext.getCmp('xt_menuinfo_images').setRawValue(imgs);
  	xt_menuinfo_images = window.parent.Ext.getCmp('xt_menuinfo_images').getRawValue();
  	window.parent.Ext.getCmp('selectBtn').setIcon("${syspath}/deng/images/icons/"+imgs);
  	console.info("获取赋值后的值:"+xt_menuinfo_images);
  	window.parent.xtMenuinfoImgWin.close();
}
</script>
</body>  
</html> 