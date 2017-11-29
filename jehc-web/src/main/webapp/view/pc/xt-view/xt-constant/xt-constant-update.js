//返回r
function goback(){
	tlocation("../xtConstantController/loadXtConstant");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtConstant(){
	submitBForm('defaultForm','../xtConstantController/updateXtConstant','../xtConstantController/loadXtConstant');
}

