//返回r
function goback(){
	tlocation("../cmsRecruitmentController/loadCmsRecruitment");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateCmsRecruitment(){
	submitBForm('defaultForm','../cmsRecruitmentController/updateCmsRecruitment','../cmsRecruitmentController/loadCmsRecruitment');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

