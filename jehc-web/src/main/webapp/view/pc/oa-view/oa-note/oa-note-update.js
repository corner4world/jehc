//返回r
function goback(){
	tlocation("../oaNoteController/loadOaNote");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateOaNote(){
	submitBForm('defaultForm','../oaNoteController/updateOaNote','../oaNoteController/loadOaNote');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

