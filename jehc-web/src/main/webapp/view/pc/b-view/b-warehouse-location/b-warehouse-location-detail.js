//返回r
function goback(){
	var b_warehouse_id = $('#b_warehouse_id').val();
	var b_warehouse_name = $('#b_warehouse_name').val();
	tlocation("../bWarehouseLocationController/loadBWarehouseLocation?b_warehouse_id="+b_warehouse_id+"&b_warehouse_name="+b_warehouse_name);
}

