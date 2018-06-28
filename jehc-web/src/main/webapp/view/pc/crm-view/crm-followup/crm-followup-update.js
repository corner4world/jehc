//返回r
function goback(){
	tlocation("../crmFollowupController/loadCrmFollowup");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateCrmFollowup(){
	submitBForm('defaultForm','../crmFollowupController/updateCrmFollowup','../crmFollowupController/loadCrmFollowup');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

