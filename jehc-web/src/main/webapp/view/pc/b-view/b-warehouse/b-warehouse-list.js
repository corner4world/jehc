var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../bWarehouseController/getBWarehouseListByCondition',opt);},//渲染数据
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
				data:"b_warehouse_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"b_warehouse_id",
				width:"50px"
			},
			{
				data:'b_seller_name'
			},
			{
				data:'b_warehouse_name'
			},
			{
				data:'xt_provinceName'
			},
			{
				data:'xt_cityName'
			},
			{
				data:'xt_districtName'
			},
			{
				data:'b_warehouse_address'
			},
			{
				data:'b_warehouse_ctime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'b_warehouse_mtime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:'b_warehouse_type',
                render:function(value, type, row, meta) {
                	if(value == 0){
						return "赠品";
					}else if(value==1){
						return "疵品";
					}else if(value == 2){
						return '正品';
					}else{
						return '---';
					}
                }
			},
			{
				data:"b_warehouse_id",
				width:"150px",
				render:function(data, type, row, meta) {
					var b_warehouse_name = row.b_warehouse_name;
					var b_warehouse_localtionbtn = '<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=toBWarehouseLocation("'+data+'","'+b_warehouse_name+'")><i class="glyphicon glyphicon-tower"></i>库位</button>';
					var b_warehouse_detailbtn = '<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=toBWarehouseDetail("'+data+'")><i class="glyphicon glyphicon-eye-open"></i>详情</button>';
					return b_warehouse_localtionbtn+b_warehouse_detailbtn;
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
function toBWarehouseAdd(){
	tlocation('../bWarehouseController/toBWarehouseAdd');
}
//修改
function toBWarehouseUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	tlocation("../bWarehouseController/toBWarehouseUpdate?b_warehouse_id="+id);
}
//详情
function toBWarehouseDetail(id){
	tlocation("../bWarehouseController/toBWarehouseDetail?b_warehouse_id="+id);
}
//删除
function delBWarehouse(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {b_warehouse_id:id};
		ajaxBReq('../bWarehouseController/delBWarehouse',params,['datatables']);
	})
}

//发送至库位列表页面
function toBWarehouseLocation(b_warehouse_id,b_warehouse_name){
	tlocation("../bWarehouseLocationController/loadBWarehouseLocation?b_warehouse_id="+b_warehouse_id+"&b_warehouse_name="+b_warehouse_name);
}