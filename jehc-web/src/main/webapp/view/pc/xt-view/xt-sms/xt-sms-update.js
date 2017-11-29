//返回r
function goback(){
	tlocation("../xtSmsController/loadXtSms");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtSms(){
	submitBForm('defaultForm','../xtSmsController/updateXtSms','../xtSmsController/loadXtSms');
}

