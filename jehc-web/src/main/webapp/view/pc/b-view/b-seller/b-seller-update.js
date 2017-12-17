//返回r
function goback(){
	tlocation("../bSellerController/loadBSeller");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateBSeller(){
	submitBForm('defaultForm','../bSellerController/updateBSeller','../bSellerController/loadBSeller');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

