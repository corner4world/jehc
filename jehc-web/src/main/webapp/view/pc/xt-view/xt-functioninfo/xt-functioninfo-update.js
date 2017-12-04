

//编辑窗体
function updateXtFunctioninfo(){
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
	$('#updateXtFunctioninfoForm')[0].reset();
	$('#updateXtFunctioninfoForm').bootstrapValidator({
		message:'此值不是有效的'
	});
	$.ajax({
	   type:"GET",
	   url:"../xtFunctioninfoController/getXtFunctioninfoById",
	   data:"xt_functioninfo_id="+nodes[0].id,
	   success: function(result){
		   result = eval("(" + result + ")");  
		   result = result.data;
		   $("#updateXtFunctioninfoForm").find("#xt_menuinfo_id_").val(result.xt_menuinfo_id);
		   $("#updateXtFunctioninfoForm").find("#xt_menuinfo_title").val(result.xt_menuinfo_title);
		   $("#updateXtFunctioninfoForm").find("#xt_functioninfoName").val(result.xt_functioninfoName);
		   $("#updateXtFunctioninfoForm").find("#xt_functioninfoTitle").val(result.xt_functioninfoTitle);
		   $("#updateXtFunctioninfoForm").find("#xt_functioninfoURL").val(result.xt_functioninfoURL);
		   $("#updateXtFunctioninfoForm").find("#xt_functioninfoMethod").val(result.xt_functioninfoMethod);
		   $("#updateXtFunctioninfoForm").find("#xt_functioninfoType").val(result.xt_functioninfoType);
		   $("#updateXtFunctioninfoForm").find("#xt_functioninfoIsAuthority").val(result.xt_functioninfoIsAuthority);
		   $("#updateXtFunctioninfoForm").find("#xt_functioninfoStatus").val(result.xt_functioninfoStatus);
		   $("#updateXtFunctioninfoForm").find("#xt_functioninfo_id").val(result.xt_functioninfo_id);
		   $('#updateXtFunctioninfoModal').modal({"backdrop":"static"});
	   }
	});
}

function doUpdateXtFunctioninfo(){
	var updateXtFunctioninfoForm =  $('#updateXtFunctioninfoForm');
	submitBFormCallFn('updateXtFunctioninfoForm','../xtFunctioninfoController/updateXtFunctioninfo',function(result){
		try {
    		result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			if(result.success){
            		window.parent.toastrBoot(3,result.msg);
            		refreshAll();
            		$('#updateXtFunctioninfoModal').modal('hide');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}