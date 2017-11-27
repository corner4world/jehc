//返回r
function goback(){
	tlocation("../solrDataConfigController/loadSolrDataConfig");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateSolrDataConfig(){
	submitBForm('defaultForm','../solrDataConfigController/updateSolrDataConfig','../solrDataConfigController/loadSolrDataConfig');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

