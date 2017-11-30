//返回r
function goback(){
	tlocation("../xtEncoderqrcodeController/loadXtEncoderqrcode");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtEncoderqrcode(){
	submitBForm('defaultForm','../xtEncoderqrcodeController/addXtEncoderqrcode','../xtEncoderqrcodeController/loadXtEncoderqrcode');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

