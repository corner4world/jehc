//返回r
function goback(){
	tlocation("../xtRoleinfoController/loadXtRoleinfo");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtRoleinfo(){
	submitBForm('defaultForm','../xtRoleinfoController/addXtRoleinfo','../xtRoleinfoController/loadXtRoleinfo');
}

