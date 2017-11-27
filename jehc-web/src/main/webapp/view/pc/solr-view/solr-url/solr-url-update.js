//返回r
function goback(){
	tlocation("../solrUrlController/loadSolrUrl");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateSolrUrl(){
	submitBForm('defaultForm','../solrUrlController/updateSolrUrl','../solrUrlController/loadSolrUrl');
}

