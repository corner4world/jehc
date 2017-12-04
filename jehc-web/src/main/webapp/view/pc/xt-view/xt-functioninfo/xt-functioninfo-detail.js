
//功能详情
function getXtFunctioninfoById(){
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"必须选择一条记录");
		return;
	}
	if(nodes[0].tempObject != '功能'){
		toastrBoot(4,"选择的记录必须为功能");
		return;
	}
	$.ajax({
	   type:"GET",
	   url:"../xtFunctioninfoController/getXtFunctioninfoById",
	   data:"xt_functioninfo_id="+nodes[0].id,
	   success: function(result){
		   result = eval("(" + result + ")");  
		   result = result.data;
		   $("#detailXtFunctioninfoForm").find("#xt_menuinfo_id_").val(result.xt_menuinfo_id);
		   $("#detailXtFunctioninfoForm").find("#xt_menuinfo_title").val(result.xt_menuinfo_title);
		   $("#detailXtFunctioninfoForm").find("#xt_functioninfoName").val(result.xt_functioninfoName);
		   $("#detailXtFunctioninfoForm").find("#xt_functioninfoTitle").val(result.xt_functioninfoTitle);
		   $("#detailXtFunctioninfoForm").find("#xt_functioninfoURL").val(result.xt_functioninfoURL);
		   $("#detailXtFunctioninfoForm").find("#xt_functioninfoMethod").val(result.xt_functioninfoMethod);
		   $("#detailXtFunctioninfoForm").find("#xt_functioninfoType").val(result.xt_functioninfoType);
		   $("#detailXtFunctioninfoForm").find("#xt_functioninfoIsAuthority").val(result.xt_functioninfoIsAuthority);
		   $("#detailXtFunctioninfoForm").find("#xt_functioninfoStatus").val(result.xt_functioninfoStatus);
		   $("#detailXtFunctioninfoForm").find("#xt_functioninfo_id").val(result.xt_functioninfo_id);
		   $('#detailXtFunctioninfoModal').modal({"backdrop":"static"});
	   }
	});
}