//返回r
function goback(){
	tlocation("../crmCustomerController/loadCrmCustomer");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addCrmCustomer(){
	submitBForm('defaultForm','../crmCustomerController/addCrmCustomer','../crmCustomerController/loadCrmCustomer');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	InitBDataCombo('industryId','industryId');//读取所属行业数据字典
	InitBDataCombo('scaleId','scaleId');//读取公司规模数据字典
	InitBDataCombo('ageScope','ageScope');//读取年龄结构数据字典
});

CallRegion(0);
getCity(0);
getCounties(0);



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
					'<input class="form-control" type="hidden" id="crmCustomerAttach_'+numbers+'_xt_attachement_id" name="crmCustomerAttach['+numbers+'].xt_attachement_id" >'+
					'<img src = "../deng/images/default/add_d.png" width="96"  height="96" class="img" id="crmCustomerAttach_'+numbers+'_xt_attachement_id_pic">'+
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
	$("#form_crmCustomerAttach_"+numbers).remove();
	var crmCustomerAttach_removed_flag = $('#crmCustomerAttach_removed_flag').val()
	if(null == crmCustomerAttach_removed_flag || '' == crmCustomerAttach_removed_flag){
		$('#crmCustomerAttach_removed_flag').val(','+numbers+',');
	}else{
		$('#crmCustomerAttach_removed_flag').val(crmCustomerAttach_removed_flag+numbers+',')
	}
	reValidator('defaultForm');
}
