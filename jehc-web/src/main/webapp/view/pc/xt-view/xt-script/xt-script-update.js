//返回r
function goback(){
	tlocation("../xtScriptController/loadXtScript");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtScript(){
	submitBForm('defaultForm','../xtScriptController/updateXtScript','../xtScriptController/loadXtScript');
}

