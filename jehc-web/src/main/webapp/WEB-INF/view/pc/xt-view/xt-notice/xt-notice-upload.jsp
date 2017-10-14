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
<title>平台公告</title>  
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/jquery/scroll/js/jquery.nicescroll.min.js"></script>
	<link rel="stylesheet" href="${syspath}/deng/source/plugins/other/html5/zyupload/zyupload/skins/zyupload-1.0.0.min.css" type="text/css">
	<script type="text/javascript" src="${syspath}/deng/source/plugins/other/html5/zyupload/zyupload/zyupload-1.0.0.min.js"></script>
	<style type="text/css">
	</style>
</head>  
<body>  
    <script type="text/javascript">
    	Ext.onReady(function() {
    		reGetWidthAndHeight();
			var panel = Ext.create('Ext.Panel',{  
                title:'编辑图片',
                autoScroll:true,
                html:'<div id="zyupload" class="zyupload"></div>',
                tools:[
		           		{
		            	type:'left',
		            	text:'返回列表',
						glyph:0xf060,
		            	handler:function(button){
							window.location.href = '../xtNoticeController/loadXtNotice';
						}
		            }
		        ],
                buttons:[{
					text:'返回列表',
					glyph:0xf060,
					handler:function(button){
						window.location.href = '../xtNoticeController/loadXtNotice';
					}
				}]
            }); 
            Ext.create('Ext.Viewport',{
				layout:'fit',
				xtype:'viewport',
				items:panel
			});
			// 初始化插件
			$("#zyupload").zyUpload({
				width            :   clientWidth*0.94,                 // 宽度
				height           :   clientHeight,                 // 宽度
				itemWidth        :   "140px",                 // 文件项的宽度
				itemHeight       :   "115px",                 // 文件项的高度
				url              :   "/upload/UploadAction",  // 上传文件的路径
				fileType         :   ["jpg","png","txt","js","exe"],// 上传文件的类型
				fileSize         :   5120000,                // 上传文件的大小
				multiple         :   true,                    // 是否可以多个文件上传
				dragDrop         :   false,                    // 是否可以拖动上传文件
				tailor           :   true,                    // 是否可以裁剪图片
				del              :   true,                    // 是否可以删除文件
				finishDel        :   false,  				  // 是否在上传文件完成后删除预览
				/* 外部获得的回调接口 */
				onSelect: function(selectFiles, allFiles){    // 选择文件的回调方法  selectFile:当前选中的文件  allFiles:还没上传的全部文件
					console.info("当前选择了以下文件：");
					console.info(selectFiles);
				},
				onDelete: function(file, files){              // 删除一个文件的回调方法 file:当前删除的文件  files:删除之后的文件
					console.info("当前删除了此文件：");
					console.info(file.name);
				},
				onSuccess: function(file, response){          // 文件上传成功的回调方法
					console.info("此文件上传成功：");
					console.info(file.name);
					console.info("此文件上传到服务器地址：");
					console.info(response);
					//$("#uploadInf").append("<p>上传成功，文件地址是：" + response + "</p>");
				},
				onFailure: function(file, response){          // 文件上传失败的回调方法
					console.info("此文件上传失败：");
					console.info(file.name);
				},
				onComplete: function(response){           	  // 上传完成的回调方法
					console.info("文件上传完成");
					console.info(response);
				}
			});
        }); 
	</script> 
</body>  
</html> 