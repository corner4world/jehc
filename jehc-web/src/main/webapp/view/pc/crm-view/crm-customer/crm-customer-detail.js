//返回r
function goback(){
	tlocation("../crmCustomerController/loadCrmCustomer");
}

//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	InitBDataComboSetV('industryId','industryId','industryId_');//读取所属行业数据字典
	InitBDataComboSetV('scaleId','scaleId','scaleId_');//读取公司规模数据字典
	InitBDataComboSetV('ageScope','ageScope','ageScope_');//读取年龄结构数据字典
	InitBDataComboSetV('businessId','businessId','businessId_');//读取业务类型数据字典
	InitBDataComboSetV('paymentType','paymentType','paymentType_');//读取缴费方式数据字典
	InitBDataComboSetV('income','income','income_');//读取缴费方式数据字典
	InitBDataComboSetV('grid','grid','grid_');//读取所属行业数据字典
	InitBDataComboSetV('status','status','status_');//读取所属行业数据字典
});

CallRegion(0);
$('#xt_province_id_0').val($('#xt_provinceID_').val());
getCity(0);
$('#xt_city_id_0').val($('#xt_cityID_').val());
getCounties(0);
$('#xt_district_id_0').val($('#xt_districtID_').val());
//回调子表附件回显操作开始
var crmCustomerAttach = crmCustomerObj.items[0].crmCustomerAttach;
for(var i = 0; i < crmCustomerAttach.length; i++){
	initBFileRight('crmCustomerAttach_'+i+'_xt_attachement_id','crmCustomerAttach_'+i+'_xt_attachement_id_pic',2);
	var params = {xt_attachment_id:$('#crmCustomerAttach_'+i+'_xt_attachement_id').val(),field_name:'crmCustomerAttach_'+i+'_xt_attachement_id'};
	ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
}
//回调子表附件回显操作结束