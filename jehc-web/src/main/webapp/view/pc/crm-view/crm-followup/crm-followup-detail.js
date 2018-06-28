//返回r
function goback(){
	var id=$("#customerId").val();
	tlocation("../crmFollowupController/loadCrmFollowup?customerId="+id);
	//tlocation("../crmFollowupController/loadCrmFollowup");
}

