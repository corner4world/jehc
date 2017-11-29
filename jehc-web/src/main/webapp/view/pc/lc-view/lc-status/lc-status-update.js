//返回r
function goback(){
	tlocation("../lcStatusController/loadLcStatus");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateLcStatus(){
	submitBForm('defaultForm','../lcStatusController/updateLcStatus','../lcStatusController/loadLcStatus');
}

