

//新增窗体
function addXtFunctioninfo(){
	$('#addXtFunctioninfoForm')[0].reset();
	$('#addXtFunctioninfoForm').bootstrapValidator({
		message:'此值不是有效的'
	});
	$('#addXtFunctioninfoModal').modal({"backdrop":"static"});
}

//处理新增窗体
function doAddXtFunctioninfo(){
	var addXtFunctioninfoForm =  $('#addXtFunctioninfoForm');
	submitBFormCallFn('addXtFunctioninfoForm','../xtFunctioninfoController/addXtFunctioninfo',function(result){
		try {
    		result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			if(result.success){
            		window.parent.toastrBoot(3,result.msg);
            		refreshAll();
            		$('#addXtFunctioninfoModal').modal('hide');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}