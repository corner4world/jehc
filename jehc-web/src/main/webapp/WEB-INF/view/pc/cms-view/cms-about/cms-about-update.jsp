<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/deng/include/includeboot.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="UTF-8">
<title>关于我们编辑页面</title>
<link href="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/4.0.0/plugins/summernote/summernote-bs4.css" rel="stylesheet" />

</head>
<body>
	<div class="panel-body">
		<div class="page-header">
			<h4>编辑关于我们</h4>
		</div>
		<form class="form-horizontal" id="defaultForm" method="post">
			<div class="form-group" style="display:none;">
				<label class="col-lg-3 control-label">主键</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="cms_about_id"  placeholder="请输入主键" value="${cmsAbout.cms_about_id }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">标题</label>
				<div class="col-lg-6">
					<input class="form-control" type="text" maxlength="255"  data-bv-notempty data-bv-notempty-message="请输入标题"  name="title" placeholder="请输入标题" value="${cmsAbout.title }">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">内容</label>
				<div class="col-lg-12">
					<div id="summernote">${cmsAbout.content }</div>
					<input type="hidden" name="content" id="content">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">照片</label>
				<div class="col-lg-6">
					<input class="form-control" type="hidden" name="imgpath" id="imgpath" value="${cmsAbout.imgpath }">
					<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="imgpath_pic">
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label">状态</label>
				<div class="col-lg-6">
					<select class="form-control" name="status" >
						<option value="0" <c:if test="${cmsAbout.status = 0 }">selected</c:if> >正常</option>
						<option value="1" <c:if test="${cmsAbout.status = 1 }">selected</c:if> >关闭</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label class="col-lg-3 control-label"></label>
				<div class="col-lg-6">
					<button type="button" class="btn green" onclick="updateCmsAbout()">保存</button>
					<button type="button" class="btn default" onclick="goback()">返回</button>
				</div>
			</div>
		</form>
	</div>
</body>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/4.0.0/js/waves.js"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/4.0.0/plugins/summernote/summernote-bs4.min.js"></script>
<script src="${syspath}/deng/source/plugins/admin/index/global/plugins/bootstrap/4.0.0/plugins/summernote/lang/summernote-zh-CN.min.js"></script>
<script type="text/javascript" src="../view/pc/cms-view/cms-about/cms-about-update.js"></script> 
<script>
    jQuery(document).ready(function(){
        $('#summernote').summernote({
            height:550,                 // set editor height
            minHeight:'100%',             // set minimum height of editor
            maxHeight:'100%',             // set maximum height of editor
            focus:false,                 // set focus to editable area after initializing summernote
            callbacks:{  
                onImageUpload: function(file) {//图片默认以二进制的形式存储到数据库，调用此方法将请求后台将图片存储到服务器，返回图片请求地址到前端
                      //将图片放入Formdate对象中                                         
                      var formData = new FormData();  
                      //‘picture’为后台获取的文件名，file[0]是要上传的文件
                      formData.append("picture", file[0]); 
                      $.ajax({                            
                           type:'post',        
                           url:basePath+'/xtCommonController/upload',                        
                           cache: false,
                           data:formData, 
                           processData: false,
                           contentType: false,
                           dataType:'text', //请求成功后，后台返回图片访问地址字符串，故此以text格式获取，而不是json格式
                           success: function(picture) {                                            
                             $('#summernote').summernote('insertImage',picture); 
                           },  
                           error:function(){                                                  
                              msgTishi(4,"上传失败");                                                     
                           } 
                      });
                }  
            },
            toolbar: [
                      <!--字体工具-->
                      ['fontname', ['fontname']], //字体系列                                 
                      ['style', ['bold', 'italic', 'underline', 'clear']], // 字体粗体、字体斜体、字体下划线、字体格式清除       
                      ['font', ['strikethrough', 'superscript', 'subscript']], //字体划线、字体上标、字体下标   
                      ['fontsize', ['fontsize']], //字体大小                                
                      ['color', ['color']], //字体颜色
                      
                      <!--段落工具-->                
                      ['style', ['style']],//样式
                      ['para', ['ul', 'ol', 'paragraph']], //无序列表、有序列表、段落对齐方式
                      ['height', ['height']], //行高
                      
                      <!--插入工具-->    
                      ['table',['table']], //插入表格    
                      ['hr',['hr']],//插入水平线                
                      ['link',['link']], //插入链接                
                      ['picture',['picture']], //插入图片                
                      ['video',['video']], //插入视频
                      
                      <!--其它-->
                      ['fullscreen',['fullscreen']], //全屏
                      ['codeview',['codeview']], //查看html代码
                      ['undo',['undo']], //撤销
                      ['redo',['redo']], //取消撤销
                      ['help',['help']]  //帮助
                    ],
                    lang:'zh-CN',  //设置中文，需引入中文插件summernote-zh-CN.js
                    placeholder: 'write here...', //占位符
                    dialogsInBody: true,  //对话框放在编辑框还是Body
                    dialogsFade: true ,//对话框显示效果
                    disableDragAndDrop: true ,//禁用拖放功能
                    shortcuts: false ,//禁用快捷键
        });
    });  
</script>
</html>
