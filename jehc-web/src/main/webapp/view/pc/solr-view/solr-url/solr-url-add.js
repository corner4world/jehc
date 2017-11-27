//返回r
function goback(){
	tlocation("../solrUrlController/loadSolrUrl");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addSolrUrl(){
	submitBForm('defaultForm','../solrUrlController/addSolrUrl','../solrUrlController/loadSolrUrl');
}

