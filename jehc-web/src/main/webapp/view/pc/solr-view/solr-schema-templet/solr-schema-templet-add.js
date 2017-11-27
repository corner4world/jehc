//返回r
function goback(){
	tlocation("../solrSchemaTempletController/loadSolrSchemaTemplet");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addSolrSchemaTemplet(){
	submitBForm('defaultForm','../solrSchemaTempletController/addSolrSchemaTemplet','../solrSchemaTempletController/loadSolrSchemaTemplet');
}

