//返回r
function goback(){
	tlocation("../bStockController/loadBStock");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateBStock(){
	submitBForm('defaultForm','../bStockController/updateBStock','../bStockController/loadBStock');
}

