//返回r
function goback(){
	tlocation("../xtPlatformController/loadXtPlatform");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtPlatform(){
	submitBForm('defaultForm','../xtPlatformController/updateXtPlatform','../xtPlatformController/loadXtPlatform');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

function addXtPlatformFeedbackItems(){
	validatorDestroy('defaultForm');
	var numbers = $('#xtPlatformFeedbackFormNumber').val();
	numbers = parseInt(numbers)+1;
	$('#xtPlatformFeedbackFormNumber').val(numbers);
	//点击添加新一行
	var removeBtn = '<a class="btn btn-danger" href="javascript:delXtPlatformFeedbackItems(this,'+numbers+')" >删除该条信息</a>';
	var form = 
		'<div id="form_xtPlatformFeedback_'+numbers+'">'+
			'<fieldset>'+
			'<legend><h5>序号'+numbers+'</h5></legend>'+
			'<div class="form-group"><div class="col-lg-3">'+removeBtn+'</div></div>'+
			'<div class="form-group">'+
				'<label class="col-lg-3 control-label">主键编号</label>'+
				'<div class="col-lg-6">'+
					'<input class="form-control" type="text" maxlength="32"  data-bv-notempty data-bv-notempty-message="请输入主键编号"  id="xtPlatformFeedback_'+numbers+'_xt_platform_feedback_id" name="xtPlatformFeedback['+numbers+'].xt_platform_feedback_id"  placeholder="请输入主键编号">'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label class="col-lg-3 control-label">台平发布信息编号</label>'+
				'<div class="col-lg-6">'+
					'<input class="form-control" type="text" maxlength="32"  id="xtPlatformFeedback_'+numbers+'_xt_platform_id" name="xtPlatformFeedback['+numbers+'].xt_platform_id"  placeholder="请输入台平发布信息编号">'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label class="col-lg-3 control-label">创建时间</label>'+
				'<div class="col-lg-6">'+
					'<input class="form_datetime form-control" id="xtPlatformFeedback_'+numbers+'_xt_platform_feedback_ctime" name="xtPlatformFeedback['+numbers+'].xt_platform_feedback_ctime" placeholder="请选择时间">'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label class="col-lg-3 control-label">评论人编号</label>'+
				'<div class="col-lg-6">'+
					'<input class="form-control" type="hidden" id="xtPlatformFeedback_'+numbers+'_xt_userinfo_id" name="xtPlatformFeedback['+numbers+'].xt_userinfo_id" >'+
					'<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xtPlatformFeedback_'+numbers+'_xt_userinfo_id_pic">'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label class="col-lg-3 control-label">评论内容</label>'+
				'<div class="col-lg-6">'+
					'<input class="form-control" type="hidden" id="xtPlatformFeedback_'+numbers+'_xt_platform_feedback_remark" name="xtPlatformFeedback['+numbers+'].xt_platform_feedback_remark" >'+
					'<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="xtPlatformFeedback_'+numbers+'_xt_platform_feedback_remark_pic">'+
				'</div>'+
			'</div>'+
			'<div class="form-group">'+
				'<label class="col-lg-3 control-label">状态0正常1隐藏</label>'+
				'<div class="col-lg-6">'+
					'<input class="form-control" type="text" maxlength="10"  id="xtPlatformFeedback_'+numbers+'_xt_platform_feedback_status" name="xtPlatformFeedback['+numbers+'].xt_platform_feedback_status"  placeholder="请输入状态0正常1隐藏">'+
				'</div>'+
			'</div>'+
				'</fieldset>'+
		'</div>'
	$(".form_xtPlatformFeedback").append(form);
	/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
	initBFileRight('xtPlatformFeedback_'+numbers+'_xt_userinfo_id','xtPlatformFeedback_'+numbers+'_xt_userinfo_id_pic',1);
	initBFileRight('xtPlatformFeedback_'+numbers+'_xt_platform_feedback_remark','xtPlatformFeedback_'+numbers+'_xt_platform_feedback_remark_pic',1);
	/**初始化附件右键菜单结束**/

	datetimeInit();
	reValidator('defaultForm');
}
function delXtPlatformFeedbackItems(thiz,numbers){
	validatorDestroy('defaultForm');
	$("#form_xtPlatformFeedback_"+numbers).remove();
	var xtPlatformFeedback_removed_flag = $('#xtPlatformFeedback_removed_flag').val()
	if(null == xtPlatformFeedback_removed_flag || '' == xtPlatformFeedback_removed_flag){
		$('#xtPlatformFeedback_removed_flag').val(','+numbers+',');
	}else{
		$('#xtPlatformFeedback_removed_flag').val(xtPlatformFeedback_removed_flag+numbers+',')
	}
	reValidator('defaultForm');
}
//回调子表（平台反馈意见）附件回显操作开始
var xtPlatformFeedback = xtPlatformObj.items[0].xtPlatformFeedback;
for(var i = 0; i < xtPlatformFeedback.length; i++){
	initBFileRight('xtPlatformFeedback_'+i+'_xt_userinfo_id','xtPlatformFeedback_'+i+'_xt_userinfo_id_pic',1);
	var params = {xt_attachment_id:$('#xtPlatformFeedback_'+i+'_xt_userinfo_id').val(),field_name:'xtPlatformFeedback_'+i+'_xt_userinfo_id'};
	ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
	initBFileRight('xtPlatformFeedback_'+i+'_xt_platform_feedback_remark','xtPlatformFeedback_'+i+'_xt_platform_feedback_remark_pic',1);
	var params = {xt_attachment_id:$('#xtPlatformFeedback_'+i+'_xt_platform_feedback_remark').val(),field_name:'xtPlatformFeedback_'+i+'_xt_platform_feedback_remark'};
	ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
}
//回调子表（平台反馈意见）附件回显操作结束
