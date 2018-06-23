//返回r
function goback(){
	tlocation("../cmsSeoController/loadCmsSeo");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateCmsSeo(){
	submitBForm('defaultForm','../cmsSeoController/updateCmsSeo','../cmsSeoController/loadCmsSeo');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

