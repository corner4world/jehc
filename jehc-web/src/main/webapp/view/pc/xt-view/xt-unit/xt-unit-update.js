//返回r
function goback(){
	tlocation("../xtUnitController/loadXtUnit");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtUnit(){
	submitBForm('defaultForm','../xtUnitController/updateXtUnit','../xtUnitController/loadXtUnit');
}

