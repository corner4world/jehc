//返回r
function goback(){
	tlocation("../bBrandController/loadBBrand");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateBBrand(){
	submitBForm('defaultForm','../bBrandController/updateBBrand','../bBrandController/loadBBrand');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

