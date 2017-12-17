//返回r
function goback(){
	tlocation("../bWarehouseController/loadBWarehouse");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addBWarehouse(){
	submitBForm('defaultForm','../bWarehouseController/addBWarehouse','../bWarehouseController/loadBWarehouse');
}

CallRegion(0);


//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});

//商户选择器
function initBSellerList(){
	$('#bSellerSelectModal').modal();
	$('#searchBSellerForm')[0].reset();
	var opt = {
			searchformId:'searchBSellerForm'
		};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../bSellerController/getBSellerListByCondition',opt);},//渲染数据
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
					data:"b_seller_id",
					render:function (data, type, full, meta) {
						return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildBSeller " value="' + data + '" /><span></span></label>';
					},
					bSortable:false
				},
				{
					data:"b_seller_id",
					width:"50px"
				},
				{
					data:'b_seller_name'
				},
				{
					data:'b_seller_tel'
				},
				{
					data:'b_seller_level'
				},
				{
					data:'b_seller_official',
	                render:function(data, type, row, meta) {
	                	if(data == 0){
	                        return '是';
	                    }else if(data == 1){
	                        return "否";
	                    }else{
	                    	return "缺省";
	                    }
	                }
				},
				{
					data:'b_seller_status',
	                render:function(data, type, row, meta) {
	                	if(data == 0){
	                        return '可用';
	                    }else if(data == 1){
	                        return "禁用";
	                    }else{
	                    	return "缺省";
	                    }
	                }
				},
				{
					data:'b_seller_address'
				}
			]
	});
	grid=$('#bSellersDatatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkallBSeller','checkchildBSeller');
	//实现单击行选中
	clickrowselected('bSellersDatatables');
}

function doBSellerSelect(){
	if(returncheckedLength('checkchildBSeller') != 1){
		toastrBoot(4,"请选择一个商户");
		return;
	}
	msgTishCallFnBoot("确定选择的该商户？",function(){
		var id = returncheckIds('checkId').join(",");
		var table = $('#bSellersDatatables').dataTable();
		var List = getTableContent(table);
		for(var i = 0; i < List.length; i++){
			var obj = List[i];
			if(obj.b_seller_id == id){
				$('#b_seller_name').val(obj.b_seller_name);
				$('#b_seller_id').val(obj.b_seller_id);
				$('#bSellerSelectModal').modal('hide');
				return;
			}
		}
	})
}