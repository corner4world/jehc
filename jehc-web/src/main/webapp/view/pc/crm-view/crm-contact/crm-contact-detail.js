//返回r
function goback(){
	var id=$("#customerId").val();
	tlocation("../crmContactController/loadCrmContact?customerId="+id);
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	InitBDataComboSetV('postId','postId','postId_');//读取岗位数据字典
});

