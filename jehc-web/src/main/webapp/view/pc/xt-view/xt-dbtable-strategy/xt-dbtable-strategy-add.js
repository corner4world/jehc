//返回r
function goback(){
	tlocation("../xtDbtableStrategyController/loadXtDbtableStrategy");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtDbtableStrategy(){
	submitBForm('defaultForm','../xtDbtableStrategyController/addXtDbtableStrategy','../xtDbtableStrategyController/loadXtDbtableStrategy');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

