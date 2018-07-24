//返回r
function goback(){
	tlocation("../paymentTransferController/loadPaymentTransfer");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updatePaymentTransfer(){
	submitBForm('defaultForm','../paymentTransferController/updatePaymentTransfer','../paymentTransferController/loadPaymentTransfer');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

