//弹新增窗体
function addXtMenuinfo(value,valueText){
	$('#addXtMenuinfoForm')[0].reset();
	if(value != null && typeof(value) != 'undefined'){
		$('#xt_menuinfo_parentId').val(value);
	}else{
		$('#xt_menuinfo_parentId').val("0");
	}
	if(valueText != null && typeof(valueText) != 'undefined'){
		$('#xt_menuinfo_parentTitle').val(valueText);
	}else{
		$('#xt_menuinfo_parentTitle').val("一级菜单");
	}
	$('#addXtMenuinfoForm').bootstrapValidator({
		message:'此值不是有效的'
	});
	InitMenuModuleListSetV('xt_menuinfo_module_id');
	$('#addXtMenuinfoModal').modal({"backdrop":"static"});
}
//执行新增操作
function doAddXtMenuinfo(){
	var addXtMenuinfoForm =  $('#addXtMenuinfoForm');
	submitBFormCallFn('addXtMenuinfoForm','../xtMenuinfoController/addXtMenuinfo',function(result){
		try {
    		result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			if(result.success){
            		window.parent.toastrBoot(3,result.msg);
            		initTreeTable();
            		$('#addXtMenuinfoModal').modal('hide');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}