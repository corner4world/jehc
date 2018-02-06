//返回r
function goback(){
	tlocation("../xtQuartzController/loadXtQuartz");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtQuartz(){
submitBForm('defaultForm','../xtQuartzController/updateXtQuartz','../xtQuartzController/loadXtQuartz');
}

