//返回r
function goback(){
	tlocation("../bMemberController/loadBMember");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addBMember(){
	submitBForm('defaultForm','../bMemberController/addBMember','../bMemberController/loadBMember');
}
CallRegion(0);
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

