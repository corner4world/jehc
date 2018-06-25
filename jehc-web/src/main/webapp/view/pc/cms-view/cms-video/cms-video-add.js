//返回r
function goback(){
	tlocation("../cmsVideoController/loadCmsVideo");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addCmsVideo(){
	submitBForm('defaultForm','../cmsVideoController/addCmsVideo','../cmsVideoController/loadCmsVideo');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
initBFileRight('imgPath','imgPath_pic',1);
initBFileRight('videoPath','videoPath_pic',1);
/**初始化附件右键菜单结束**/

