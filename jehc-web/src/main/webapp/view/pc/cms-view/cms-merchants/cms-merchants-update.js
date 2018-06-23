//返回r
function goback(){
	tlocation("../cmsMerchantsController/loadCmsMerchants");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateCmsMerchants(){
	submitBForm('defaultForm','../cmsMerchantsController/updateCmsMerchants','../cmsMerchantsController/loadCmsMerchants');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

