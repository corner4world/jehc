//返回r
function goback(){
	tlocation("../xtPathController/loadXtPath");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtPath(){
	submitBForm('defaultForm','../xtPathController/updateXtPath','../xtPathController/loadXtPath');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

