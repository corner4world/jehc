//详情
function detailXtAreaRegion(){
	$('#detailXtAreaRegionForm')[0].reset();
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
		   $("#detailXtAreaRegionForm").find("#PARENT_ID").val(result.PARENT_ID);
		   $("#detailXtAreaRegionForm").find("#ID").val(result.ID);
		   $("#detailXtAreaRegionForm").find("#REGION_LEVEL").val(result.REGION_LEVEL);
		   $("#detailXtAreaRegionForm").find("#NAME").val(result.NAME);
		   $("#detailXtAreaRegionForm").find("#NAME_EN").val(result.NAME_EN);
		   $("#detailXtAreaRegionForm").find("#CODE").val(result.CODE);
		   $("#detailXtAreaRegionForm").find("#LONGITUDE").val(result.LONGITUDE);
		   $("#detailXtAreaRegionForm").find("#LATITUDE").val(result.LATITUDE);
		   $('#detailXtAreaRegionModal').modal();
	   }
	});
}
