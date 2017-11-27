//返回r
function goback(){
	tlocation("../solrSchemaTempletController/loadSolrSchemaTemplet");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateSolrSchemaTemplet(){
	submitBForm('defaultForm','../solrSchemaTempletController/updateSolrSchemaTemplet','../solrSchemaTempletController/loadSolrSchemaTemplet');
}

