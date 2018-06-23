function updateXtMenuinfo(value){
	$('#updateXtMenuinfoForm')[0].reset();
	$('#updateXtMenuinfoForm').bootstrapValidator({
		message:'此值不是有效的'
	});
	$.ajax({
	   type:"GET",
	   url:"../xtMenuinfoController/getXtMenuinfo",
	   data:"xt_menuinfo_id="+value,
	   success: function(result){
		   result = eval("(" + result + ")");  
		   result = result.data;
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_id").val(result.xt_menuinfo_id);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_title").val(result.xt_menuinfo_title);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_parentId_").val(result.xt_menuinfo_parentId);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_parentTitle_").val(result.xt_menuinfo_parentTitle);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_leaf").val(result.xt_menuinfo_leaf);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_sys").val(result.xt_menuinfo_sys);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_iconCls").val(result.xt_menuinfo_iconCls);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_url").val(result.xt_menuinfo_url);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_sort").val(result.xt_menuinfo_sort);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_status").val(result.xt_menuinfo_status);
		   $("#updateXtMenuinfoForm").find("#xt_menuinfo_module_idTemp").val(result.xt_menuinfo_module_id);
		   InitMenuModuleListSetV('xt_menuinfo_module_id_','xt_menuinfo_module_idTemp');
		   $('#updateXtMenuinfoModal').modal({"backdrop":"static"});
		}
	});
}

function doUpdateXtMenuinfo(){
	submitBFormCallFn('updateXtMenuinfoForm','../xtMenuinfoController/updateXtMenuinfo',function(result){
	try {
		result = eval("(" + result + ")");  
		if(typeof(result.success) != "undefined"){
			if(result.success){
        		window.parent.toastrBoot(3,result.msg);
        		initTreeTable();
        		$('#updateXtMenuinfoModal').modal('hide');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}