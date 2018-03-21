var grid;
$(document).ready(function() {
	var opt = {
		searchformId:'searchForm'
	};
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var options = DataTablesList.listOptions({
		ajax:function (data, callback, settings){datatablesListCallBack(data, callback, settings,'../xtDataAuthorityController/getXtFunctioninfoListForData',opt);},//渲染数据
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
				data:"xt_functioninfo_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:'xt_functioninfo_id',
				width:"50px",
			},
			{
				data:"xt_menuinfo_title"
			},
			{
				data:"xt_functioninfo_id",
				render:function(data, type, row, meta) {
					var xt_menuinfo_id = row.xt_menuinfo_id;
  					var xt_menuinfo_title = row.xt_menuinfo_title;
					var btn = '<button class="btn btn-default" onclick=delXtDataAuthorityDefaultAll("'+xt_menuinfo_id+'")><i class="glyphicon glyphicon-trash"></i>清空初始化</button>';
					btn = btn+'<button class="btn btn-default" onclick=delXtDataAuthorityAll("'+xt_menuinfo_id+'")><i class="glyphicon glyphicon-remove"></i>清空按人员</button>';
					btn = btn+'<button class="btn btn-default" onclick=delXtDataAuthorityDepartAll("'+xt_menuinfo_id+'")><i class="glyphicon glyphicon-remove"></i>清空按部门</button>';
					btn = btn+'<button class="btn btn-default" onclick=delXtDataAuthorityPostAll("'+xt_menuinfo_id+'")><i class="glyphicon glyphicon-remove"></i>清空按岗位</button>';
					btn = btn+'<button class="btn btn-default" onclick=showDataAuthorityDefaultAllWin("'+xt_menuinfo_id+'","'+xt_menuinfo_title+'")><i class="glyphiconglyphicon-wrench"></i>启动初始化设置权限</button>';
					btn = btn+'<button class="btn btn-default" onclick=showUserWin("'+xt_menuinfo_id+'","'+xt_menuinfo_title+'")><i class="glyphicon glyphicon-transfer"></i>按人员设置</button>';
					btn = btn+'<button class="btn btn-default" onclick=showDepartWin("'+xt_menuinfo_id+'","'+xt_menuinfo_title+'")><i class="glyphicon glyphicon-cog"></i>按部门设置</button>';
					btn = btn+'<button class="btn btn-default" onclick=showPostWin("'+xt_menuinfo_id+'","'+xt_menuinfo_title+'")><i class="glyphicon glyphicon-edit"></i>按岗位设置</button>';
					return btn;
				}
			},
			{
				data:'xt_menuinfo_id',
				bVisible:false
			}
		]
	});
	grid=$('#datatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkall','checkchild');
	//实现单击行选中
	clickrowselected('datatables');
});

//清空按人员设置数据权限
function delXtDataAuthorityAll(xt_menuinfo_id){
	msgTishCallFnBoot("确定要清空按人员数据权限设置吗?",function(){
		var params = {xt_menuinfo_id:xt_menuinfo_id};
		ajaxBReq('../xtDataAuthorityController/delXtDataAuthorityUserAll',params,['datatables']);
	})
}
//清空按部门设置数据权限
function delXtDataAuthorityDepartAll(xt_menuinfo_id){
	msgTishCallFnBoot("确定要清空按部门数据权限设置吗?",function(){
		var params = {xt_menuinfo_id:xt_menuinfo_id};
		ajaxBReq('../xtDataAuthorityController/delXtDataAuthorityDepartAll',params,['datatables']);
	})
}
//清空按岗位设置数据权限
function delXtDataAuthorityPostAll(xt_menuinfo_id){
	msgTishCallFnBoot("确定要清空按岗位数据权限设置吗?",function(){
		var params = {xt_menuinfo_id:xt_menuinfo_id};
		ajaxBReq('../xtDataAuthorityController/delXtDataAuthorityPostAll',params,['datatables']);
	})
}
//清空按初始化数据权限设置
function delXtDataAuthorityDefaultAll(xt_menuinfo_id){
	msgTishCallFnBoot("确定要清空初始化数据权限吗?",function(){
		var params = {xt_menuinfo_id:xt_menuinfo_id};
		ajaxBReq('../xtDataAuthorityController/delXtDataAuthorityDefaultAll',params,['datatables']);
	})
}

/**导出**/
function exportXtDataAuthority(grid,url){
	exportExcel(grid,url);
}
