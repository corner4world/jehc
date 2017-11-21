var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesList.listOptions({
		ajax:function (data, callback, settings){datatablesListCallBack(data, callback, settings,'../xtOnLineUserController/getXtOnLineUserList',opt);},//渲染数据
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
				data:"sessionId",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"sessionId",
				width:"150px"
			},
			{
				data:'xt_userinfo_name',
				width:"150px"
			},
			{
            	data:"xt_userinfo_realName",
            	width:"150px"
            }
		]
	});
	grid=$('#datatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkall','checkchild');
	//实现单击行选中
	clickrowselected('datatables');
});


//移除全部用户
function removeAllXtOnLineUser(){
	msgTishCallFnBoot("确定要移除所有在线用户？",function(){
		var params = {};
		ajaxBReq('../xtOnLineUserController/removeAllXtOnLineUser',params,['datatables']);
	})
}

//移除单个用户
function removeXtOnLineUser(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要移除用户");
		return;
	}
	msgTishCallFnBoot("确定要移除所选择的用户？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {sessionId:id};
		ajaxBReq('../xtOnLineUserController/removeXtOnLineUser',params,['datatables']);
	})
}