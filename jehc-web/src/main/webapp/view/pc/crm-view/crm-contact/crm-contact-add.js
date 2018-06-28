//返回r
function goback(){
	var id=$("#customerId").val();
	tlocation("../crmContactController/loadCrmContact?customerId="+id);
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addCrmContact(){
	submitBForm('defaultForm','../crmContactController/addCrmContact','../crmContactController/loadCrmContact?customerId='+$('#customerId').val());
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	InitBDataCombo('postId','postId');//读取岗位数据字典
	
});
function check(temp){
	var re = /^1\d{10}$/
	if(!re.test(temp.value)){
		alert("手机号码格式不正确，请重新输入!");
		return;
	}
}