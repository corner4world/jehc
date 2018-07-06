//返回r
function goback(){
	tlocation("../oaSuggestionboxController/loadOaSuggestionbox");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addOaSuggestionbox(){
	submitBForm('defaultForm','../oaSuggestionboxController/addOaSuggestionbox','../oaSuggestionboxController/loadOaSuggestionbox');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

