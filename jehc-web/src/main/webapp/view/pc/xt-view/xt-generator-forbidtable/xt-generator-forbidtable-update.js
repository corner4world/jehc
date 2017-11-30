//返回r
function goback(){
	tlocation("../xtGeneratorForbidtableController/loadXtGeneratorForbidtable");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtGeneratorForbidtable(){
	submitBForm('defaultForm','../xtGeneratorForbidtableController/updateXtGeneratorForbidtable','../xtGeneratorForbidtableController/loadXtGeneratorForbidtable');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

