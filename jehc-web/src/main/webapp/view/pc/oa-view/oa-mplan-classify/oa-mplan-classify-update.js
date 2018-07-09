//返回r
function goback(){
	tlocation("../oaMplanClassifyController/loadOaMplanClassify");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateOaMplanClassify(){
	submitBForm('defaultForm','../oaMplanClassifyController/updateOaMplanClassify','../oaMplanClassifyController/loadOaMplanClassify');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

