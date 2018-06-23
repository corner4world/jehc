//返回r
function goback(){
	tlocation("../xtMenuinfoModuleController/loadXtMenuinfoModule");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtMenuinfoModule(){
	submitBForm('defaultForm','../xtMenuinfoModuleController/addXtMenuinfoModule','../xtMenuinfoModuleController/loadXtMenuinfoModule');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

