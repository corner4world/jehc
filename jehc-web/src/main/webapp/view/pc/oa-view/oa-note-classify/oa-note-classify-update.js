//返回r
function goback(){
	tlocation("../oaNoteClassifyController/loadOaNoteClassify");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateOaNoteClassify(){
	submitBForm('defaultForm','../oaNoteClassifyController/updateOaNoteClassify','../oaNoteClassifyController/loadOaNoteClassify');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

