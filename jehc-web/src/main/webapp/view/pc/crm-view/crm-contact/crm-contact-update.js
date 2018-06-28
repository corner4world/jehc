//返回r
function goback(){
	var id=$("#customerId").val();
	tlocation("../crmContactController/loadCrmContact?customerId="+id);
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateCrmContact(){
	submitBForm('defaultForm','../crmContactController/updateCrmContact','../crmContactController/loadCrmContact?customerId='+$('#customerId').val());
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	InitBDataComboSetV('postId','postId','postId_');//读取岗位数据字典
});
