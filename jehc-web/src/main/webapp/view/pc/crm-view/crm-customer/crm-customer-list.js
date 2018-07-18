var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	InitBDataComboSetV('industryId','industryId','industryId_');//读取所属行业数据字典
	CallRegion(0);
	getCity(0);
	getCounties(0);
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../crmCustomerController/getCrmCustomerListByCondition',opt);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		tableHeight:tableHeight()*0.45+'px',
		//列表表头字段
		colums:[
			{
				sClass:"text-center",
				width:"50px",
				data:"customerId",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:'customerId'
			},
			{
				data:'name'
			},
			{
				data:'shortName'
			},
			{
				data:'corporation'
			},
			{
				data:'tel'
			},
			{
				data:'cdate',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'mdate',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:"customerId",
				render:function(data, type, row, meta) {
					var customerId = row.customerId;
  					var name = row.name;
					var btn = '<button class="btn btn-default" onclick=toCrmCustomerDetail("'+data+'")><i class="glyphicon glyphicon-eye-open"></i>详情</button>';
					btn = btn+'<button class="btn btn-default" onclick=loadCrmFollowup("'+data+'")><i class="glyphicon glyphicon-edit"></i>跟进日志</button>';
					btn = btn+'<button class="btn btn-default" onclick=loadCrmContact("'+data+'")><i class="glyphicon glyphicon-edit"></i>客户联系人</button>';
					return btn;
				}
			}
		]
	});
	grid=$('#datatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkall','checkchild');
	//实现单击行选中
	clickrowselected('datatables');
});
//新增
function toCrmCustomerAdd(){
	tlocation('../crmCustomerController/toCrmCustomerAdd');
}
//修改
function toCrmCustomerUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	tlocation("../crmCustomerController/toCrmCustomerUpdate?customerId="+id);
}
//详情
function toCrmCustomerDetail(id){
	tlocation("../crmCustomerController/toCrmCustomerDetail?customerId="+id);
}
//跟进日志
function loadCrmFollowup(id){
	tlocation("../crmFollowupController/loadCrmFollowup?customerId="+id);
}
//分配联系人
function loadCrmContact(id){
	tlocation("../crmContactController/loadCrmContact?customerId="+id);
}
//提交申请
function addCrmLevelApply(){
	console.info($('#systemUID').val());
	submitBFormCallFn('applyForm','../crmCustomerController/addCrmLevelApply?customerId='+$('#customerId').val(),function(result){
		result = eval("(" + result + ")");  
		if(typeof(result.success) != "undefined"){
			toastrBoot(3,result.msg);
			$('#levelApplyModal').modal('hide');
		}
	});
}
//申请记录
function loadCrmLevelApply(id){
	tlocation("../crmLevelApplyController/loadCrmLevelApply?customerId="+id);
}
//删除
function delCrmCustomer(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {customerId:id};
		ajaxBReq('../crmCustomerController/delCrmCustomer',params,['datatables']);
	})
}

//客户等级申请
function levelApplication(){
	if(returncheckedLength('checkchild') != 1){
		toastrBoot(4,"只能选择一个客户");
		return;
	}
	var table = $('#datatables').dataTable();
	var List = getTableContent(table);
	var idList = returncheckIds('checkId');
	var customerName;
	var customerId;
	var systemUID=[];
	for(var i = 0; i < idList.length; i++){
		var id = idList[i];
		for(var j = 0; j < List.length; j++){
			if(id == List[j].customerId){
				customerId = List[j].customerId;
				customerName =List[j].name;
				/**控制数据功能权限操作相关代码开始**/
				systemUID.push(List[j].createUser);
				/**控制数据功能权限操作相关代码结束**/
				if(List[j].levelStatus == 2){
					toastrBoot(4,"您以提交了该客户："+customerName+"的申请。");
					return;
				}
				break;
			}
		}
	}
	var levelApplyModalModalCount = 0 ;
	$('#applyForm')[0].reset();
	$('#levelApplyModal').modal({backdrop:'static',keyboard:false});
	$('#levelApplyModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
		if(++levelApplyModalModalCount == 1){
			// 是弹出框居中。。。  
	        var $modal_dialog = $("#levelApplyoModalDialog");  
	        $('#levelApplyLabel').html("客户等级申请<font color=red>["+customerName+"]</font>");
	        $('#customerId').val(customerId);
	        $('#systemUID').val(systemUID.join(","));
	        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
	        $('#applyForm').bootstrapValidator({
	        	message:'此值不是有效的'
	        });
		}
    });  
}



function doSelectUser(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要分配的客户");
		return;
	}
	var id = returncheckIds('checkId').join(",");
	$('#deletedUserinfoSelectModal').modal();
	var deletedUserinfoSelectModalCount = 0 ;
	$('#searchFormDeletedUserinfo')[0].reset();
	$('#customerId').val(id);
	$('#deletedUserinfoSelectModal').on("shown.bs.modal",function(){  
		if(++deletedUserinfoSelectModalCount == 1){
			var opt = {
					searchformId:'searchFormDeletedUserinfo'
				};
			var options = DataTablesPaging.pagingOptions({
				ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtUserinfoController/getXtUserinfoListByCondition',opt);},//渲染数据
					//在第一位置追加序列号
					fnRowCallback:function(nRow, aData, iDisplayIndex){
						jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
						return nRow;
				},
				order:[],//取消默认排序查询,否则复选框一列会出现小箭头
				//列表表头字段
				colums:[
					{
						sClass:"text-center",
						width:"50px",
						data:"xt_userinfo_id",
						render:function (data, type, full, meta) {
							return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildDeletedUserinfo" value="' + data + '" /><span></span></label>';
						},
						bSortable:false
					},
					{
						data:"xt_userinfo_id",
						width:"50px"
					},
					{
						data:'xt_userinfo_realName'
					},
					{
						data:'xt_userinfo_phone'
					},
					{
						data:"xt_userinfo_id",
						render:function(data, type, row, meta) {
							var xt_userinfo_name = row.xt_userinfo_name;
							var xt_userinfo_realName = row.xt_userinfo_realName;
							var btn ='<button class="btn btn-default" onclick=doSelect("'+data+'","'+xt_userinfo_realName+'","'+xt_userinfo_name+'")><i class="glyphicon glyphicon-edit"></i>执行分配该员工</button>';
							return btn;
						}
					}
				]
			});
			grid=$('#deletedUserinfoDatatables').dataTable(options);
			//实现全选反选
			docheckboxall('checkallDeletedUserinfo','checkchildDeletedUserinfo');
			//实现单击行选中
			clickrowselected('deletedUserinfoDatatables');
		}
	})
}

//分配用户
function doSelect(xt_userinfo_id,xt_userinfo_realName,xt_userinfo_name){
	msgTishCallFnBoot("确定将所选的客户分配至员工<font color='red'>【"+xt_userinfo_realName+"】</font>下面？",function(){
		var params = {customerId:$('#customerId').val(),xt_userinfo_id:xt_userinfo_id};
		ajaxBRequestCallFn('../crmCustomerController/updateCToUser',params,function(result){
			
			result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			toastrBoot(3,result.msg);
    			$('#deletedUserinfoSelectModal').modal('hide');
    			search('datatables');
    		}
		});
	})
}

function exportCustomerInfo(params){
	window.open("../crmCustomerController/customerExport?customerId="+params,+"_blank");
}

function initUser(id){
	var str = "<option value=''>请选择</option>";
	$.ajax({
	   type:"GET",
	   xhrFields:{withCredentials:true},
	   url:"../crmCustomerController/getXtUserinfoListAll",
	   success: function(result){
		   $("#"+id).html("");
		   //从服务器获取数据进行绑定
           $.each(result, function(i, item){
           	 str += "<option value=" + item.xt_userinfo_id + ">" + item.xt_userinfo_realName + "</option>";
           })
           $("#"+id).append(str);
           try {
        	   if(null != value_id && '' != value_id){
        		   if('undefined' != typeof($('#'+value_id).val()) && null != $('#'+value_id).val() && '' != $('#'+value_id).val() && '请选择' != $('#'+value_id).val()){
        			   $('#'+id).val($('#'+value_id).val());
        		   }
               }
		   } catch (e) {
		   }
	   }
	});
}
