//返回r
function goback(){
	tlocation("../xtIpFrozenController/loadXtIpFrozen");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtIpFrozen(){
	submitBForm('defaultForm','../xtIpFrozenController/addXtIpFrozen','../xtIpFrozenController/loadXtIpFrozen');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

