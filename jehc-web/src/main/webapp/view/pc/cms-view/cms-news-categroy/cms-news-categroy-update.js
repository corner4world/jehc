//返回r
function goback(){
	tlocation("../cmsNewsCategroyController/loadCmsNewsCategroy");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateCmsNewsCategroy(){
	submitBForm('defaultForm','../cmsNewsCategroyController/updateCmsNewsCategroy','../cmsNewsCategroyController/loadCmsNewsCategroy');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

