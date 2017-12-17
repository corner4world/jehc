//返回r
function goback(){
	tlocation("../bMemberController/loadBMember");
}

CallRegion(0);
$('#xt_province_id_0').val($('#xt_provinceID_').val());
getCity(0);
$('#xt_city_id_0').val($('#xt_cityID_').val());
getCounties(0);
$('#xt_district_id_0').val($('#xt_districtID_').val());