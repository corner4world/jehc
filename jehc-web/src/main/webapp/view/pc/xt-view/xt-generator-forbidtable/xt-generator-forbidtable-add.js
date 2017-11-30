//返回r
function goback(){
	tlocation("../xtGeneratorForbidtableController/loadXtGeneratorForbidtable");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtGeneratorForbidtable(){
	submitBForm('defaultForm','../xtGeneratorForbidtableController/addXtGeneratorForbidtable','../xtGeneratorForbidtableController/loadXtGeneratorForbidtable');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

