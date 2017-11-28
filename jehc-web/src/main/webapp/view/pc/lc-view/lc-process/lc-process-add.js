//返回r
function goback(){
	tlocation("../lcProcessController/loadLcProcess");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addLcProcess(){
	submitBForm('defaultForm','../lcProcessController/addLcProcess','../lcProcessController/loadLcProcess');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	InitconstantList(3,'xt_constant_id');
});

/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
initBFileRight('xt_attachment','xt_attachment_pic',1);
/**初始化附件右键菜单结束**/