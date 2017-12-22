//返回r
function goback(){
	var b_warehouse_id = $('#b_warehouse_id').val();
	var b_warehouse_name = $('#b_warehouse_name').val();
	tlocation("../bWarehouseLocationController/loadBWarehouseLocation?b_warehouse_id="+b_warehouse_id+"&b_warehouse_name="+b_warehouse_name);
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateBWarehouseLocation(){
	var b_warehouse_id = $('#b_warehouse_id').val();
	var b_warehouse_name = $('#b_warehouse_name').val();
	submitBForm('defaultForm','../bWarehouseLocationController/updateBWarehouseLocation',"../bWarehouseLocationController/loadBWarehouseLocation?b_warehouse_id="+b_warehouse_id+"&b_warehouse_name="+b_warehouse_name);
}

