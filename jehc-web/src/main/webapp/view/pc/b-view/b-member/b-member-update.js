//返回r
function goback(){
	tlocation("../bMemberController/loadBMember");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
CallRegion(0);
$('#xt_province_id_0').val($('#xt_provinceID_').val());
getCity(0);
$('#xt_city_id_0').val($('#xt_cityID_').val());
getCounties(0);
$('#xt_district_id_0').val($('#xt_districtID_').val());
//保存
function updateBMember(){
	submitBForm('defaultForm','../bMemberController/updateBMember','../bMemberController/loadBMember');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
