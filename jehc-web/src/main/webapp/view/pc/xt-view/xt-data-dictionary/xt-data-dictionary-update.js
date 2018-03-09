function updateXtDataDictionary(value){
	$('#updateXtDataDictionaryForm')[0].reset();
	$('#updateXtDataDictionaryForm').bootstrapValidator({
		message:'此值不是有效的'
	});
	$.ajax({
	   type:"GET",
	   url:"../xtDataDictionaryController/getXtDataDictionaryById",
	   data:"xt_data_dictionary_id="+value,
	   success: function(result){
		   result = eval("(" + result + ")");  
		   result = result.data;
		   $("#updateXtDataDictionaryForm").find("#xt_data_dictionary_id").val(result.xt_data_dictionary_id);
		   $("#updateXtDataDictionaryForm").find("#xt_data_dictionary_name").val(result.xt_data_dictionary_name);
		   $("#updateXtDataDictionaryForm").find("#xt_data_dictionary_pid_").val(result.xt_data_dictionary_pid);
		   $("#updateXtDataDictionaryForm").find("#xt_data_dictionary_value").val(result.xt_data_dictionary_value);
		   $("#updateXtDataDictionaryForm").find("#xt_data_dictionary_state").val(result.xt_data_dictionary_state);
		   $("#updateXtDataDictionaryForm").find("#xt_data_dictionary_soft").val(result.xt_data_dictionary_soft);
		   $("#updateXtDataDictionaryForm").find("#xt_data_dictionary_remark").val(result.xt_data_dictionary_remark);
		   $('#updateXtDataDictionaryModal').modal({"backdrop":"static"});
	   }
	});
}
//处理编辑
function doUpdateXtDataDictionary(){
	submitBFormCallFn('updateXtDataDictionaryForm','../xtDataDictionaryController/updateXtDataDictionary',function(result){
		try {
    		result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			if(result.success){
            		window.parent.toastrBoot(3,result.msg);
            		initTreeTable();
            		$('#updateXtDataDictionaryModal').modal('hide');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}