//返回
function goback(){
	tlocation("../oaNoticeController/loadOaNotice");
}
$('#defaultForm').bootstrapValidator({
  message:'此值不是有效的',
  feedbackIcons:{
      valid:'glyphicon glyphicon-ok',
      invalid:'glyphicon glyphicon-remove',
      validating:'glyphicon glyphicon-refresh'
  },
  fields:{
	  oa_noticeTitle:{
          validators:{
              notEmpty:{
                  message:'标题不能为空'
              },
              stringLength:{
                  min:1,
                  max:100,
                  message:'标题字符不能超过100个'
              }
          }
      },
      oa_noticeContent:{
    	  validators:{
	    	  stringLength:{
	              max:255,
	              message:'公告内容字符不能超过255个'
	          }
    	  }
      },
      oa_noticeType:{
          validators:{
              notEmpty:{
                  message:'请选择类型'
              }
          }
      }
  }
});

//保存
function addOaNotice(flag){
	if(flag == 1){
		$('#submitType').val(flag);
	}
	submitBForm('defaultForm','../oaNoticeController/addOaNotice','../oaNoticeController/loadOaNotice');
}

initBFileRight('xt_attachment_id','xt_attachment_id_pic');

