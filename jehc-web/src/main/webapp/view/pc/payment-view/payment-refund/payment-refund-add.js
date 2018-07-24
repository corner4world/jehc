//返回r
function goback(){
	tlocation("../paymentRefundController/loadPaymentRefund");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addPaymentRefund(){
	submitBForm('defaultForm','../paymentRefundController/addPaymentRefund','../paymentRefundController/loadPaymentRefund');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

