//返回r
function goback(){
	tlocation("../xtVersionController/loadXtVersion");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtVersion(){
	submitBForm('defaultForm','../xtVersionController/updateXtVersion','../xtVersionController/loadXtVersion');
}
/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
initBFileRight('xt_attachment_id','xt_attachment_id_pic',1);
/**初始化附件右键菜单结束**/

/**配置附件回显方法开始**/
var params = {xt_attachment_id:$('#xt_attachment_id').val(),field_name:'xt_attachment_id'};
ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
/**配置附件回显方法结束**/
