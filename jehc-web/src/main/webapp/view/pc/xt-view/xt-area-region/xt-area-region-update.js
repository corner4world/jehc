//修改行政区域
function updateXtAreaRegion(){
	$('#updateXtAreaRegionForm')[0].reset();
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"选择数据非法");
		return;
	}
	$.ajax({
	   type:"GET",
	   url:"../xtAreaRegionController/getXtAreaRegionById",
	   data:"ID="+nodes[0].id,
	   success: function(result){
		   result = eval("(" + result + ")");  
		   result = result.data;
		   $("#updateXtAreaRegionForm").find("#PARENT_ID").val(result.PARENT_ID);
		   $("#updateXtAreaRegionForm").find("#ID").val(result.ID);
		   $("#updateXtAreaRegionForm").find("#REGION_LEVEL").val(result.REGION_LEVEL);
		   $("#updateXtAreaRegionForm").find("#NAME").val(result.NAME);
		   $("#updateXtAreaRegionForm").find("#NAME_EN").val(result.NAME_EN);
		   $("#updateXtAreaRegionForm").find("#CODE").val(result.CODE);
		   $("#updateXtAreaRegionForm").find("#LONGITUDE").val(result.LONGITUDE);
		   $("#updateXtAreaRegionForm").find("#LATITUDE").val(result.LATITUDE);
		   $('#updateXtAreaRegionModal').modal();
	   }
	});
	$('#updateXtAreaRegionForm').bootstrapValidator({
		message:'此值不是有效的'
	});
}

//执行修改操作
function doUpdateXtAreaRegion(){
	var updateXtAreaRegionForm =  $('#updateXtAreaRegionForm');
	submitBFormCallFn('updateXtAreaRegionForm','../xtAreaRegionController/updateXtAreaRegion',function(result){
		try {
    		result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			if(result.success){
            		window.parent.toastrBoot(3,result.msg);
            		refreshAll();
            		$('#updateXtAreaRegionModal').modal('hide');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}
