//返回r
function goback(){
	tlocation("../xtUserinfoController/loadXtUserinfo");
}

//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	InitBDataComboSetV('gender','xt_userinfo_sex','xt_userinfo_sex_');//读取性别数据字典
	InitBDataComboSetV('xt_userinfo_nation','xt_userinfo_nation','xt_userinfo_nation_');//读取民族数据字典
	InitBDataComboSetV('xt_userinfo_highestDegree','xt_userinfo_highestDegree','xt_userinfo_highestDegree_');//读取文化程度数据字典
	InitBDataComboSetV('xt_userinfo_workYear','xt_userinfo_workYear','xt_userinfo_workYear_');//读取工作年限数据字典
	InitBDataComboSetV('xt_userinfo_state','xt_userinfo_state','xt_userinfo_state_');//读取状态数据字典
	InitBDataComboSetV('xt_userinfo_ismarried','xt_userinfo_ismarried','xt_userinfo_ismarried_');//读取是否已婚数据字典
});
