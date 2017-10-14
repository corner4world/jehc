//返回
function goback(){
	tlocation("../oaWorklogController/loadOaWorklog");
}
$('#defaultForm').bootstrapValidator({
  message:'此值不是有效的',
  feedbackIcons:{
      valid:'glyphicon glyphicon-ok',
      invalid:'glyphicon glyphicon-remove',
      validating:'glyphicon glyphicon-refresh'
  },
  fields:{
	  oa_worklogTitle:{
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
      oa_worklogContent:{
    	  validators:{
	    	  stringLength:{
	              max:255,
	              message:'工作内容字符不能超过255个'
	          }
    	  }
      }
  }
});

//保存
function updateOaWorkLog(){
	submitBForm('defaultForm','../oaWorklogController/updateOaWorklog','../oaWorklogController/loadOaWorklog');
}