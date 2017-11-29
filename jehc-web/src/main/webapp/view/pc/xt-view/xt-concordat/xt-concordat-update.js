//返回r
function goback(){
	tlocation("../xtConcordatController/loadXtConcordat");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtConcordat(){
	submitBForm('defaultForm','../xtConcordatController/updateXtConcordat','../xtConcordatController/loadXtConcordat');
}

