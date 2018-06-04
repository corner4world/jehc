var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
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
				width:"20px",
				data:"xt_userinfo_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_userinfo_id",
				width:"20px"
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
				data:'xt_userinfo_state',
				render:function(data, type, row, meta) {
					var xt_data_dictionary_name="∨";
					InitBDataCallFn(data,function(result){
						result = eval("(" + result + ")");  
					    result = result.data;
					    xt_data_dictionary_name = result.xt_data_dictionary_name;
					    $("#datatables tbody > tr:eq("+meta.row+ ") > td:eq("+meta.col+")").html(xt_data_dictionary_name);
//					    jQuery('td:eq('+meta.col+')', meta.row).html(xt_data_dictionary_name);   //通过异步渲染数据
					});
					return xt_data_dictionary_name;
				}
			},
			{
				data:'xt_userinfo_sex',
				render:function(data, type, row, meta) {
					var xt_data_dictionary_name="∨";
					InitBDataCallFn(data,function(result){
						result = eval("(" + result + ")");  
					    result = result.data;
					    xt_data_dictionary_name = result.xt_data_dictionary_name;
					    $("#datatables tbody > tr:eq("+meta.row+ ") > td:eq("+meta.col+")").html(xt_data_dictionary_name);
//					    jQuery('td:eq('+meta.col+')', meta.row).html(xt_data_dictionary_name); //通过异步渲染数据
					});
					return xt_data_dictionary_name;
				}
			},
			{
				data:'xt_userinfo_origo'
			},
			{
				data:'xt_userinfo_birthday'
			},
			{
				data:'xt_userinfo_email'
			},
			{
				data:"xt_userinfo_id",
				render:function(data, type, row, meta) {
					var xt_userinfo_name = row.xt_userinfo_name;
					var xt_userinfo_realName = row.xt_userinfo_realName;
					var btn = '<button class="btn btn-default" onclick=toXtUserinfoDetail("'+data+'")><i class="glyphicon glyphicon-eye-open"></i>详情</button>';
						btn = btn +'<button class="btn btn-default" onclick=initRoleinfo("'+data+'")><i class="glyphicon glyphicon-check"></i>角色权限</button>';
						btn = btn +'<button class="btn btn-default" onclick=resetXtUserinfoPwd("'+data+'","'+xt_userinfo_realName+'","'+xt_userinfo_name+'")><i class="glyphicon glyphicon-edit"></i>重置密码</button>';
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
function toXtUserinfoAdd(){
	tlocation('../xtUserinfoController/toXtUserinfoAdd');
}
//修改
function toXtUserinfoUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	tlocation("../xtUserinfoController/toXtUserinfoUpdate?xt_userinfo_id="+id);
}
//详情
function toXtUserinfoDetail(id){
	tlocation("../xtUserinfoController/toXtUserinfoDetail?xt_userinfo_id="+id);
}
//删除
function delXtUserinfo(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {xt_userinfo_id:id};
		ajaxBReq('../xtUserinfoController/delXtUserinfo',params,['datatables']);
	})
}


//已删除用户
function initListDeleted(){
	var deletedUserinfoSelectModalCount = 0 ;
	$('#deletedUserinfoSelectModal').modal({backdrop: 'static', keyboard: false});
	$('#deletedUserinfoSelectModal').on("shown.bs.modal",function(){  
		if(++deletedUserinfoSelectModalCount == 1){
			$('#searchFormDeletedUserinfo')[0].reset();
		    var opt = {
					searchformId:'searchFormDeletedUserinfo'
				};
			var options = DataTablesPaging.pagingOptions({
				ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtUserinfoController/getXtUserinfoDeletedListByCondition',opt);},//渲染数据
					//在第一位置追加序列号
					fnRowCallback:function(nRow, aData, iDisplayIndex){
						jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
						return nRow;
				},
				order:[],//取消默认排序查询,否则复选框一列会出现小箭头
				tableHeight:'120px',
				//列表表头字段
				colums:[
					{
						sClass:"text-center",
						width:"20px",
						data:"xt_userinfo_id",
						render:function (data, type, full, meta) {
							return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildDeletedUserinfo" value="' + data + '" /><span></span></label>';
						},
						bSortable:false
					},
					{
						data:"xt_userinfo_id",
						width:"20px"
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
			grid=$('#deletedUserinfoDatatables').dataTable(options);
			//实现全选反选
			docheckboxall('checkallDeletedUserinfo','checkchildDeletedUserinfo');
			//实现单击行选中
			clickrowselected('deletedUserinfoDatatables');
		}
	})
}

//恢复用户
function recoverXtUserinfo(){
	if(returncheckedLength('checkchildDeletedUserinfo') <= 0){
		toastrBoot(4,"请选择要恢复的用户");
		return;
	}
	msgTishCallFnBoot("确定要恢复所选择的用户？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {xt_userinfo_id:id};
		ajaxBRequestCallFn('../xtUserinfoController/recoverXtUserinfo',params,function(result){
			result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			$('#deletedUserinfoSelectModal').modal('hide');
    			search('datatables');
    		}
		});
	})
}



//角色权限
function initRoleinfo(id){
	var userRoleModalCount = 0;
	$('#userRoleModal').modal({backdrop: 'static', keyboard: false});
	$('#userRoleModal').on("shown.bs.modal",function(){  
		if(++userRoleModalCount == 1){
			var options = DataTablesPaging.pagingOptions({
				ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtUserinfoController/getXtRoleinfoListByUserinfoId?xt_userinfo_id='+id,null);},//渲染数据
					//在第一位置追加序列号
					fnRowCallback:function(nRow, aData, iDisplayIndex){
						jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
						return nRow;
				},
				tableHeight:'200px',
				order:[],//取消默认排序查询,否则复选框一列会出现小箭头
				//列表表头字段
				colums:[
					{
						sClass:"text-center",
						data:"xt_roleinfo_id",
						width:'20px',
						render:function (data, type, full, meta) {
							return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildUserRole" value="' + data + '" /><span></span></label>';
						},
						bSortable:false
					},
					{
						width:'20px',
						data:"xt_roleinfo_id"
					},
					{
						data:'xt_role_name'
					}
				]
			});
			$('#userRoleDatatables').dataTable(options);
			//实现全选反选
			docheckboxall('checkallUserRole','checkchildUserRole');
			//实现单击行选中
			clickrowselected('userRoleDatatables');
		}
	});
}

//重置密码
function resetXtUserinfoPwd(id,xt_userinfo_realName,xt_userinfo_name){
	msgTishCallFnBoot('确定要重置<br>姓名：'+xt_userinfo_realName+'<br>登录账户：'+xt_userinfo_name+'的密码？',function(){
		var params = {xt_userinfo_id:id,xt_userinfo_realName:xt_userinfo_realName,xt_userinfo_name:xt_userinfo_name};
		ajaxBReq('../xtUserinfoController/resetXtUserinfoPwd',params,['datatables']);
	})
}

