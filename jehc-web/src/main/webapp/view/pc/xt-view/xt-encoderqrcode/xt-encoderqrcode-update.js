//返回r
function goback(){
	tlocation("../xtEncoderqrcodeController/loadXtEncoderqrcode");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtEncoderqrcode(){
	submitBForm('defaultForm','../xtEncoderqrcodeController/updateXtEncoderqrcode','../xtEncoderqrcodeController/loadXtEncoderqrcode');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/
initBFileRight('xt_attachment_id','xt_attachment_id_pic',2);
/**初始化附件右键菜单结束**/

/**配置附件回显方法开始**/
var params = {xt_attachment_id:$('#xt_attachment_id').val(),field_name:'xt_attachment_id'};
ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
/**配置附件回显方法结束**/