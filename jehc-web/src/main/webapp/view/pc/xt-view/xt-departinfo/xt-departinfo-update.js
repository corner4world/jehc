//返回r
function goback(){
	tlocation("../xtDepartinfoController/loadXtDepartinfo");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
//保存
function updateXtDepartinfo(){
	submitBForm('defaultForm','../xtDepartinfoController/updateXtDepartinfo','../xtDepartinfoController/loadXtDepartinfo');
}


$.ajax({
   type:"GET",
   url:"../xtDepartinfoController/getXtDepartinfoById",
   data:"xt_departinfo_id="+$('#xt_departinfo_parentId').val(),
   success: function(result){
	   result = eval("(" + result + ")");  
	   result = result.data;
	   if(result != null && result != ''){
		   $('#xt_departinfo_parentName').val(result.xt_departinfo_name);
	   }else{
		   $('#xt_departinfo_parentName').val("无");
		   $('#xt_departinfo_parentId').val("0");
	   }
   }
});
