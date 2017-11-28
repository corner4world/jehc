//返回r
function goback(){
	tlocation("../lcProcessController/loadLcProcess");
}

/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/
initBFileRight('xt_attachment','xt_attachment_pic',2);
/**初始化附件右键菜单结束**/

/**配置附件回显方法开始**/
var params = {xt_attachment_id:$('#xt_attachment').val(),field_name:'xt_attachment'};
ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
/**配置附件回显方法结束**/


InitconstantListSetV(3,'xt_constant_id','xt_constant_id_');