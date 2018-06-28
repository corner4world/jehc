//返回r
function goback(){
	tlocation("../crmCustomerController/loadCrmCustomer");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateCrmCustomer(){
	var systemUID = $('#createUser').val();
	submitBForm('defaultForm','../crmCustomerController/updateCrmCustomer?systemUID='+systemUID,'../crmCustomerController/loadCrmCustomer');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	InitBDataComboSetV('industryId','industryId','industryId_');//读取所属行业数据字典
	InitBDataComboSetV('scaleId','scaleId','scaleId_');//读取公司规模数据字典
	InitBDataComboSetV('ageScope','ageScope','ageScope_');//读取年龄结构数据字典
});
CallRegion(0);
$('#xt_province_id_0').val($('#xt_provinceID_').val());
getCity(0);
$('#xt_city_id_0').val($('#xt_cityID_').val());
getCounties(0);
$('#xt_district_id_0').val($('#xt_districtID_').val());


function addCrmCustomerAttachItems(){
	validatorDestroy('defaultForm');
	var numbers = $('#crmCustomerAttachFormNumber').val();
	numbers = parseInt(numbers)+1;
	$('#crmCustomerAttachFormNumber').val(numbers);
	//点击添加新一行
	var removeBtn = '<br><a class="btn btn-danger" href="javascript:delCrmCustomerAttachItems(this,'+numbers+')" >删&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;除</a>';
	var form = 
		'<div id="form_crmCustomerAttach_'+numbers+'">'+
			'<fieldset>'+
			'<legend><h5>序号'+numbers+'</h5></legend>'+
			'<div class="form-group">'+
				'<div class="col-lg-6">'+
					'<input class="form-control" type="hidden" maxlength="32"  id="crmCustomerAttach_'+numbers+'_customer_attach_id" name="crmCustomerAttach['+numbers+'].customer_attach_id" >'+
					'<input class="form-control" type="hidden" maxlength="32"  id="crmCustomerAttach_'+numbers+'_custom_id" name="crmCustomerAttach['+numbers+'].custom_id">'+
					'<input class="form-control" type="hidden" id="crmCustomerAttach_'+numbers+'_xt_attachement_id" name="crmCustomerAttach['+numbers+'].xt_attachement_id" >'+
					'<img src = "../deng/images/default/add_d.png" class="img" width="96"  height="96"  id="crmCustomerAttach_'+numbers+'_xt_attachement_id_pic">'+
				'</div>'+
			'</div>'+
			'<div class="form-group"><div class="col-lg-3">'+removeBtn+'</div></div>'+
			'</fieldset>'+
		'</div>'
	$(".form_crmCustomerAttach").append(form);
	/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
	initBFileRight('crmCustomerAttach_'+numbers+'_xt_attachement_id','crmCustomerAttach_'+numbers+'_xt_attachement_id_pic',1);
	/**初始化附件右键菜单结束**/
	reValidator('defaultForm');
}
function delCrmCustomerAttachItems(thiz,numbers){
	validatorDestroy('defaultForm');
	var id = $('#crmCustomerAttach_'+numbers+'_customer_attach_id').val()
	if(null != id){
		msgTishCallFnBoot("确定要删除该项？",function(){
			console.info(id);
			var params = {customer_attach_id:id};
			ajaxBRequestCallFn('../crmCustomerAttachController/delCrmCustomerAttach',params,function(result){
				result = eval("(" + result + ")");  
	    		if(typeof(result.success) != "undefined"){
	    			$("#form_crmCustomerAttach_"+numbers).remove();
	    			var crmCustomerAttach_removed_flag = $('#crmCustomerAttach_removed_flag').val()
	    			if(null == crmCustomerAttach_removed_flag || '' == crmCustomerAttach_removed_flag){
	    				$('#crmCustomerAttach_removed_flag').val(','+numbers+',');
	    			}else{
	    				$('#crmCustomerAttach_removed_flag').val(crmCustomerAttach_removed_flag+numbers+',')
	    			}
	    			reValidator('defaultForm');
	    		}
			});
		})
	}else{
		$("#form_crmCustomerAttach_"+numbers).remove();
		var crmCustomerAttach_removed_flag = $('#crmCustomerAttach_removed_flag').val()
		if(null == crmCustomerAttach_removed_flag || '' == crmCustomerAttach_removed_flag){
			$('#crmCustomerAttach_removed_flag').val(','+numbers+',');
		}else{
			$('#crmCustomerAttach_removed_flag').val(crmCustomerAttach_removed_flag+numbers+',')
		}
		reValidator('defaultForm');
	}
}
//回调子表附件回显操作开始
var crmCustomerAttach = crmCustomerObj.items[0].crmCustomerAttach;
for(var i = 0; i < crmCustomerAttach.length; i++){
	initBFileRight('crmCustomerAttach_'+i+'_xt_attachement_id','crmCustomerAttach_'+i+'_xt_attachement_id_pic',1);
	var params = {xt_attachment_id:$('#crmCustomerAttach_'+i+'_xt_attachement_id').val(),field_name:'crmCustomerAttach_'+i+'_xt_attachement_id'};
	ajaxBFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params);
}
//回调子表附件回显操作结束