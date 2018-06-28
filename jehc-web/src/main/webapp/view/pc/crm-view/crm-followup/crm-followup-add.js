//返回r
function goback(){
	tlocation("../crmFollowupController/loadCrmFollowup?customerId="+id);
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addCrmFollowup(){
	submitBForm('defaultForm','../crmFollowupController/addCrmFollowup','../crmFollowupController/loadCrmFollowup?customerId='+$('#customerId').val());
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

