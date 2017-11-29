//返回r
function goback(){
	tlocation("../xtFunctioninfoCommonController/loadXtFunctioninfoCommon");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtFunctioninfoCommon(){
	submitBForm('defaultForm','../xtFunctioninfoCommonController/addXtFunctioninfoCommon','../xtFunctioninfoCommonController/loadXtFunctioninfoCommon');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

