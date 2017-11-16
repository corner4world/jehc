//返回r
function goback(){
	tlocation("../oaNoticeController/loadOaNotice");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateOaNotice(){
	submitBForm('defaultForm','../oaNoticeController/updateOaNotice','../oaNoticeController/loadOaNotice');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
initBFileRight('xt_attachement_id','xt_attachement_id_pic',1);
/**初始化附件右键菜单结束**/

/**配置附件回显方法开始**/
var params = {xt_attachment_id:$('#xt_attachement_id').val(),field_name:'xt_attachement_id'};
ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
/**配置附件回显方法结束**/
