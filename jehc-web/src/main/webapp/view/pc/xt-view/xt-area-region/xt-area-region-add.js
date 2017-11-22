//新增行政区域
function addXtAreaRegion(flag){
	$('#addXtAreaRegionForm')[0].reset();
	$('#addXtAreaRegionForm').bootstrapValidator({
		message:'此值不是有效的'
	});
	if(flag == 1){
		var zTree = $.fn.zTree.getZTreeObj("tree"),
		nodes = zTree.getSelectedNodes();
		if (nodes.length != 1) {
			toastrBoot(4,"请选择节点");
			return;
		}
		if(nodes[0].level == 3){
			toastrBoot(4,"区县下面不能新增下级行政区域");
			return;
		}
		$('#PARENT_ID').val(nodes[0].id);
		$('#REGION_LEVEL').val(parseInt(nodes[0].level)+1);
	}
	$('#addXtAreaRegionModal').modal();
}

function doAddXtAreaRegion(){
	var addXtAreaRegionForm =  $('#addXtAreaRegionForm');
	submitBFormCallFn('addXtAreaRegionForm','../xtAreaRegionController/addXtAreaRegion',function(result){
		try {
    		result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			if(result.success){
            		window.parent.toastrBoot(3,result.msg);
            		refreshAll();
            		$('#addXtAreaRegionModal').modal('hide');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}