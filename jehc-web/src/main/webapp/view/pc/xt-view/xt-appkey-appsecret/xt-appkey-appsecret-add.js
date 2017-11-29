//返回r
function goback(){
	tlocation("../xtAppkeyAppsecretController/loadXtAppkeyAppsecret");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtAppkeyAppsecret(){
	submitBForm('defaultForm','../xtAppkeyAppsecretController/addXtAppkeyAppsecret','../xtAppkeyAppsecretController/loadXtAppkeyAppsecret');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

