//返回r
function goback(){
	tlocation("../xtDbinfoController/loadXtDbinfo");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtDbinfo(){
	submitBForm('defaultForm','../xtDbinfoController/updateXtDbinfo','../xtDbinfoController/loadXtDbinfo');
}

