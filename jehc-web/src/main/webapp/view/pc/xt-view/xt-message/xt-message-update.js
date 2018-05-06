//返回r
function goback(){
	tlocation("../xtMessageController/loadXtMessage");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtMessage(){
	submitBForm('defaultForm','../xtMessageController/updateXtMessage','../xtMessageController/loadXtMessage');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

