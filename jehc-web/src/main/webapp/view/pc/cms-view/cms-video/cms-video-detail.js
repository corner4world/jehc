//返回r
function goback(){
	tlocation("../cmsVideoController/loadCmsVideo");
}
/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/
initBFileRight('imgPath','imgPath_pic',2);
initBFileRight('videoPath','videoPath_pic',2);
/**初始化附件右键菜单结束**/

/**配置附件回显方法开始**/
var params = {xt_attachment_id:$('#imgPath').val()+','+$('#videoPath').val(),field_name:'imgPath,videoPath'};
ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
/**配置附件回显方法结束**/
