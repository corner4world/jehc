//弹新增窗体
function addXtDataDictionary(value){
	$('#addXtDataDictionaryForm')[0].reset();
	if(value != null && typeof(value) != 'undefined'){
		$('#xt_data_dictionary_pid').val(value);
	}else{
		$('#xt_data_dictionary_pid').val("");
	}
	$('#addXtDataDictionaryForm').bootstrapValidator({
		message:'此值不是有效的'
	});
	$('#addXtDataDictionaryModal').modal({"backdrop":"static"});
}
//执行新增方法
function doAddXtDataDictionary(){
	var addXtDataDictionaryForm =  $('#addXtDataDictionaryForm');
	submitBFormCallFn('addXtDataDictionaryForm','../xtDataDictionaryController/addXtDataDictionary',function(result){
		try {
    		result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			if(result.success){
            		window.parent.toastrBoot(3,result.msg);
            		initTreeTable();
            		$('#addXtDataDictionaryModal').modal('hide');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}