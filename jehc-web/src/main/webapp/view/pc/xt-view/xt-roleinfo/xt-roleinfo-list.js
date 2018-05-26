var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtRoleinfoController/getXtRoleinfoListByCondition?xt_role_isdelete=0',opt);},//渲染数据
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
				data:"xt_role_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_role_id",
				width:"50px"
			},
			{
				data:'xt_role_name'
			},
			{
				data:'xt_role_desc'
			},
			{
				data:'xt_role_type',
				render:function(data, type, row, meta) {
					if(data == 0){
						return "平台权限";
					}
					if(data == 1){
						return "业务权限";
					}
				}
			},
			{
				data:'xt_role_createTime'
			},
			{
				data:'xt_role_updateTime'
			},
			{
				data:"xt_role_id",
				width:"260px",
				render:function(data, type, row, meta) {
					var xt_role_id = row.xt_role_id;
  					var xt_role_name = row.xt_role_name;
					var btn = '<button class="btn btn-default" onclick=toXtRoleinfoDetail("'+data+'")><i class="glyphicon glyphicon-eye-open"></i>详情</button>';
					btn = btn+'<button class="btn btn-default" onclick=addXtMenuinfo("'+xt_role_id+'","'+xt_role_name+'")><i class="glyphicon glyphicon-edit"></i>分配菜单</button>';
					btn = btn+'<button class="btn btn-default" onclick=addXtUserinfo("'+xt_role_id+'","'+xt_role_name+'")><i class="glyphicon glyphicon-edit"></i>分配用户</button>';
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
function toXtRoleinfoAdd(){
	tlocation('../xtRoleinfoController/toXtRoleinfoAdd');
}
//修改
function toXtRoleinfoUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	tlocation("../xtRoleinfoController/toXtRoleinfoUpdate?xt_role_id="+id);
}
//详情
function toXtRoleinfoDetail(id){
	tlocation("../xtRoleinfoController/toXtRoleinfoDetail?xt_role_id="+id);
}
//删除
function delXtRoleinfo(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要禁用的数据");
		return;
	}
	msgTishCallFnBoot("确定要禁用所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {xt_role_id:id};
		ajaxBReq('../xtRoleinfoController/delXtRoleinfo',params,['datatables']);
	})
}


function initListDeleted(){
	$('#RoleModal').modal();
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtRoleinfoController/getXtRoleinfoListByCondition?xt_role_isdelete=1',null);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		tableHeight:'200px',
		//列表表头字段
		colums:[
			{
				sClass:"text-center",
				width:"50px",
				data:"xt_role_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildRole " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_role_id",
				width:"150px"
			},
			{
				data:'xt_role_name'
			},
			{
				data:'xt_role_type',
				render:function(data, type, row, meta) {
					if(data == 0){
						return "平台权限";
					}
					if(data == 1){
						return "业务权限";
					}
				}
			},
			{
				data:'xt_role_createTime'
			},
			{
				data:'xt_role_updateTime'
			}
		]
	});
	grid=$('#RoleDatatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkallRole','checkchildRole');
	//实现单击行选中
	clickrowselected('RoleDatatables');
}


/**恢复操作**/
function recoverXtRoleinfo(){
	if(returncheckedLength('checkchildRole') <= 0){
		toastrBoot(4,"请选择要恢复的角色");
		return;
	}
	msgTishCallFnBoot("确定要恢复所选择的角色？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {xt_role_id:id};
		ajaxBRequestCallFn('../xtRoleinfoController/recoverXtRoleinfo',params,function(result){
			result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			$('#RoleModal').modal('hide');
    			search('datatables');
    		}
		});
	})
}
