//返回r
function goback(){
	tlocation("../bRecommendController/loadBRecommend");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addBRecommend(){
	submitBForm('defaultForm','../bRecommendController/addBRecommend','../bRecommendController/loadBRecommend');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
initBFileRight('xt_attachment_id','xt_attachment_id_pic',1);
/**初始化附件右键菜单结束**/

