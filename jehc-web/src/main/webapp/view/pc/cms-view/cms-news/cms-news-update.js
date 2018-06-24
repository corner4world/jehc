//返回r
function goback(){
	tlocation("../cmsNewsController/loadCmsNews");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateCmsNews(){
	$('#content').val($('#summernote').summernote('code'));
	submitBForm('defaultForm','../cmsNewsController/updateCmsNews','../cmsNewsController/loadCmsNews');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
initBFileRight('imgpath','imgpath_pic',1);
/**初始化附件右键菜单结束**/

/**配置附件回显方法开始**/
var params = {xt_attachment_id:$('#imgpath').val(),field_name:'imgpath'};
ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
/**配置附件回显方法结束**/
