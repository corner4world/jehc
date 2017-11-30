//返回r
function goback(){
	tlocation("../xtDbtableStrategyController/loadXtDbtableStrategy");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtDbtableStrategy(){
	submitBForm('defaultForm','../xtDbtableStrategyController/updateXtDbtableStrategy','../xtDbtableStrategyController/loadXtDbtableStrategy');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

