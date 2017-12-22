//返回r
function goback(){
	tlocation("../bStockController/loadBStock");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addBStock(){
	submitBForm('defaultForm','../bStockController/addBStock','../bStockController/loadBStock');
}

