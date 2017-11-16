//返回r
function goback(){
	tlocation("../xtPlatformController/loadXtPlatform");
}

//回调子表（平台反馈意见）附件回显操作开始
var xtPlatformFeedback = xtPlatformObj.items[0].xtPlatformFeedback;
for(var i = 0; i < xtPlatformFeedback.length; i++){
	initBFileRight('xtPlatformFeedback_'+i+'_xt_userinfo_id','xtPlatformFeedback_'+i+'_xt_userinfo_id_pic',2);
	var params = {xt_attachment_id:$('#xtPlatformFeedback_'+i+'_xt_userinfo_id').val(),field_name:'xtPlatformFeedback_'+i+'_xt_userinfo_id'};
	ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
	initBFileRight('xtPlatformFeedback_'+i+'_xt_platform_feedback_remark','xtPlatformFeedback_'+i+'_xt_platform_feedback_remark_pic',2);
	var params = {xt_attachment_id:$('#xtPlatformFeedback_'+i+'_xt_platform_feedback_remark').val(),field_name:'xtPlatformFeedback_'+i+'_xt_platform_feedback_remark'};
	ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
}
//回调子表（平台反馈意见）附件回显操作结束
