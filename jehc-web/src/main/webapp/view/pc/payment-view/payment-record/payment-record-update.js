//返回r
function goback(){
	tlocation("../paymentRecordController/loadPaymentRecord");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updatePaymentRecord(){
	submitBForm('defaultForm','../paymentRecordController/updatePaymentRecord','../paymentRecordController/loadPaymentRecord');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

