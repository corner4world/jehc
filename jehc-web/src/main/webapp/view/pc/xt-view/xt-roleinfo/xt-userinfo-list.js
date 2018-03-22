function addXtUserinfo(xt_role_id,xt_role_name){
	$('#UserinfoBody').height(reGetBodyHeight());
	$('#UserinfoModal').modal({backdrop:'static',keyboard:false});
	$('#UserinfoModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        // 是弹出框居中。。。  
        var $modal_dialog = $("#UserinfoModalDialog");  
        $('#searchFormUnImportU')[0].reset();
    	$('#searchFormImportU')[0].reset();
    	$('#xt_role_id1').val(xt_role_id);
    	$('#xt_role_id2').val(xt_role_id);
    	initUnImportU(xt_role_id);
    	initImportU(xt_role_id);
        $modal_dialog.css({'width':reGetBodyWidth()*0.8+'px'});  
    });  
}
/**待导入用户**/
function initUnImportU(xt_role_id){
	var opt1 = {
			searchformId:'searchFormUnImportU'
		};
	var options1 = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtRoleinfoController/getUserinfoListByCondition',opt1);},//渲染数据
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
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildUnImportU" value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_userinfo_id",
				width:"50px"
			},
			{
				data:'xt_userinfo_name'
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:'xt_userinfo_phone'
			},
			{
				data:'xt_userinfo_origo'
			},
			{
				data:'xt_userinfo_birthday'
			},
			{
				data:'xt_userinfo_email'
			}
		]
	});
	$('#UnImportUDatatables').dataTable(options1);
	//实现全选反选
	docheckboxall('checkallUnImportU','checkchildUnImportU');
	//实现单击行选中
	clickrowselected('UnImportUDatatables');
}


/**已导入用户**/
function initImportU(xt_role_id){
	var opt = {
			searchformId:'searchFormImportU'
		};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtRoleinfoController/getUserinfoListByCondition',opt);},//渲染数据
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
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildImportU" value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_userinfo_id",
				width:"50px"
			},
			{
				data:'xt_userinfo_name'
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:'xt_userinfo_phone'
			},
			{
				data:'xt_userinfo_origo'
			},
			{
				data:'xt_userinfo_birthday'
			},
			{
				data:'xt_userinfo_email'
			}
		]
	});
	$('#ImportUDatatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkallImportU','checkchildImportU');
	//实现单击行选中
	clickrowselected('ImportUDatatables');
}

/**导入用户**/
function addXtUr(){
	if(returncheckedLength('checkchildUnImportU') <= 0){
		toastrBoot(4,"请选择待导入用户");
		return;
	}
	msgTishCallFnBoot("确定导入所选用户？",function(){
		var id = returncheckIds('checkchildUnImportU').join(",");
		var params = {xt_userinfo_id:id,xt_role_id:$('#xt_role_id1').val()};
		ajaxBRequestCallFn('../xtRoleinfoController/addXtUR',params,function(){
			search('UnImportUDatatables');
			search('ImportUDatatables');
		});
		
	})
}


/**移除用户**/
function delXtUR(){
	if(returncheckedLength('checkchildImportU') <= 0){
		toastrBoot(4,"请选择要移除的用户");
		return;
	}
	msgTishCallFnBoot("确定移除所选用户？",function(){
		var id = returncheckIds('checkchildImportU').join(",");
		var params = {xt_userinfo_id:id,xt_role_id:$('#xt_role_id1').val()};
		ajaxBRequestCallFn('../xtRoleinfoController/delXtUR',params,function(){
			search('UnImportUDatatables');
			search('ImportUDatatables');
		});
	})	
}