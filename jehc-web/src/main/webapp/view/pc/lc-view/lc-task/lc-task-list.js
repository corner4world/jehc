var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../lcTaskController/getTaskListByCondition',opt);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		//列表表头字段
		colums:[
			{
				sClass:"text-center",
				width:"50px",
				data:"taskId",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"taskId",
				width:"50px"
			},
			{
				data:'name'
			},
			{
				data:'owner'
			},
			{
				data:'assignee'
			},
			{
				data:'description'
			},
			{
				data:'processInstanceId'
			},
			{
				data:'processDefinitionId'
			},
			{
				data:'tenantId'
			},
			{
				data:'createTime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:"taskId",
				render:function(data, type, row, meta) {
					var processInstanceId = row.processInstanceId;
					var lc_process_title = row.lc_process_title;
					var btn = '<button class="btn btn-default" title="查看流程实例图" onclick=showLcProcessInstance('+processInstanceId+')><i class="fa fa-gears"></i></button>';
					btn = btn +'<button class="btn btn-default" title="查看流程实例审批记录" onclick=initLcApprovalWin('+processInstanceId+')><i class="fa fa-user-plus"></i></button>';
					return btn;
				}
			}
		]
	});
	grid=$('#datatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkall','checkchild');
	//实现单击行选中
	clickrowselected('datatables');
});



function setAssignee(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"请选择要设置的一项！");
		return;
	}
	var taskId =$(".checkchild:checked").val();
	initassignee(taskId,0);
}

function setOwner(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"请选择要设置的一项！");
		return;
	}
	var taskId =$(".checkchild:checked").val();
	initassignee(taskId,1);
}

function addGroupUser(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"请选择一项！");
		return;
	}
	var taskId =$(".checkchild:checked").val();
	initassignee(taskId,2);
}

function deleteGroupUser(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"请选择要删除的一项！");
		return;
	}
	var taskId =$(".checkchild:checked").val();
	initassignee(taskId,3);
}

function initassignee(taskId,type){
	var text = "";
	$('#lcTaskType').val(type);
	$('#taskId').val(taskId);
	if(type == 0){
		text="设置经办人";
	}
	if(type == 1){
		text="设置归属人";
	}
	if(type == 2){
		text="添加组成员";
	}
	if(type == 3){
		text="移除组成员";
	}
	$('#lcAssigneePanelBody').height(reGetBodyHeight()*0.6);
	$('#lcAssigneeModalLabel').html(text);
	$('#lcAssigneeModal').modal({backdrop:'static',keyboard:false});
	$('#lcAssigneeModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        // 是弹出框居中。。。  
        var $modal_dialog = $("#lcAssigneeModalDialog");  
        /**
        //获取可视窗口的高度  
        var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;  
        //得到dialog的高度  
        var dialogHeight = $modal_dialog.height();  
        //计算出距离顶部的高度  
        var m_top = (clientHeight - dialogHeight)/2;  
        console.log("clientHeight : " + clientHeight);  
        console.log("dialogHeight : " + dialogHeight);  
        console.log("m_top : " + m_top);  
        $modal_dialog.css({'margin': m_top + 'px auto'});  
        **/
        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
        $('#searchFormUserinfo')[0].reset();
    	var opt = {
    			searchformId:'searchFormUserinfo'
    		};
    	var options = DataTablesPaging.pagingOptions({
    		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtUserinfoController/getXtUserinfoListByCondition',opt);},//渲染数据
    			//在第一位置追加序列号
    			fnRowCallback:function(nRow, aData, iDisplayIndex){
    				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
    				return nRow;
    		},
    		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
    		tableHeight:'150px',
    		//列表表头字段
    		colums:[
    			{
    				sClass:"text-center",
    				width:"50px",
    				data:"xt_userinfo_id",
    				render:function (data, type, full, meta) {
    					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildUserinfo" value="' + data + '" /><span></span></label>';
    				},
    				bSortable:false
    			},
    			{
    				data:"xt_userinfo_id",
    				width:"50px"
    			},
    			{
    				data:'xt_userinfo_name'
    			},
    			{
    				data:'xt_userinfo_realName'
    			},
    			{
    				data:'xt_userinfo_phone'
    			},
    			{
    				data:'xt_userinfo_origo'
    			},
    			{
    				data:'xt_userinfo_birthday'
    			},
    			{
    				data:'xt_userinfo_email'
    			}
    		]
    	});
    	grid=$('#userinfoDatatables').dataTable(options);
    	//实现全选反选
    	docheckboxall('checkallUserinfo','checkchildUserinfo');
    	//实现单击行选中
    	clickrowselected('UserinfoDatatables');
    	//实现单击操作开始
    	var table = $('#userinfoDatatables').DataTable();
        $('#userinfoDatatables tbody').on('click', 'tr', function () {
            var data = table.row( this ).data();
            console.info(data.xt_userinfo_realName);
        });
        //实现单击操作结束
    });
}

function doUser(){
	if($(".checkchildUserinfo:checked").length != 1){
		toastrBoot(4,"请选择要处理的人！");
		return;
	}
	msgTishCallFnBoot("确定要操作该项？",function(){
		var xt_userinfo_id =$(".checkchildUserinfo:checked").val();
		var taskId = $('#taskId').val();
		var lcTaskType = $('#lcTaskType').val(); 
		var params = {taskId:taskId,userId:xt_userinfo_id};
		if(lcTaskType == 0){
			ajaxBReq('../lcTaskController/setAssignee',params,['datatables']);
		}
		if(lcTaskType == 1){
			ajaxBReq('../lcTaskController/setOwner',params,['datatables']);
		}
		if(lcTaskType == 2){
			ajaxBReq('../lcTaskController/addGroupUser',params,['datatables']);
		}
		if(lcTaskType == 3){
			ajaxBReq('../lcTaskController/deleteGroupUser',params,['datatables']);
		}
		$('#lcAssigneeModal').modal('hide');
		search('datatables');
	})
}
//完成任务
function completeTask(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"请选择要完成的一项！");
		return;
	}
	msgTishCallFnBoot("确定要完成该任务？",function(){
		var taskId =$(".checkchild:checked").val();
		var params = {taskId:taskId};
		ajaxBReq('../lcTaskController/completeTask',params,['datatables']);
	})
}

function showLcProcessInstance(id){
	$('#lcImagePanelBody').height(reGetBodyHeight()*0.6);
	$('#lcImageModalLabel').html("实例监控图");
	$('#lcImageModal').modal({backdrop:'static',keyboard:false});
	$("#lcImageIframe",document.body).attr("src",'../lcProcessController/loadLcProcessInstanceImg?processInstanceId='+id) 
	$('#lcImageModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        // 是弹出框居中。。。  
        var $modal_dialog = $("#lcImageModalDialog");  
        /**
        //获取可视窗口的高度  
        var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;  
        //得到dialog的高度  
        var dialogHeight = $modal_dialog.height();  
        //计算出距离顶部的高度  
        var m_top = (clientHeight - dialogHeight)/2;  
        console.log("clientHeight : " + clientHeight);  
        console.log("dialogHeight : " + dialogHeight);  
        console.log("m_top : " + m_top);  
        $modal_dialog.css({'margin': m_top + 'px auto'});  
        **/
        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
    });  
}

function closeLcImageWin(){
	search('datatables');
}

function initLcApprovalWin(id){
	$('#lcHisLogPanelBody').height(reGetBodyHeight()*0.6);
	$('#lcHisLogModal').modal({backdrop:'static',keyboard:false});
	$('#lcHisLogModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        // 是弹出框居中。。。  
        var $modal_dialog = $("#lcHisLogModalDialog");  
        /**
        //获取可视窗口的高度  
        var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;  
        //得到dialog的高度  
        var dialogHeight = $modal_dialog.height();  
        //计算出距离顶部的高度  
        var m_top = (clientHeight - dialogHeight)/2;  
        console.log("clientHeight : " + clientHeight);  
        console.log("dialogHeight : " + dialogHeight);  
        console.log("m_top : " + m_top);  
        $modal_dialog.css({'margin': m_top + 'px auto'});  
        **/
        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
        $('#searchFormApproval')[0].reset();
    	$('#instanceId').val(id);
    	var opt = {
    			searchformId:'searchFormApproval'
    		};
    	var options = DataTablesPaging.pagingOptions({
    		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../lcApprovalController/getLcApprovalListByCondition',opt);},//渲染数据
    			//在第一位置追加序列号
    			fnRowCallback:function(nRow, aData, iDisplayIndex){
    				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
    				return nRow;
    		},
    		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
    		tableHeight:'150px',
    		//列表表头字段
    		colums:[
    			{
    				sClass:"text-center",
    				width:"50px",
    				data:"lc_approval_id",
    				render:function (data, type, full, meta) {
    					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildApproval" value="' + data + '" /><span></span></label>';
    				},
    				bSortable:false
    			},
    			{
    				data:"lc_approval_id",
    				width:"50px"
    			},
    			{
    				data:'lc_status_name'
    			},
    			{
    				data:'lc_approval_remark'
    			},
    			{
    				data:'lc_approval_time'
    			},
    			{
    				data:'xt_userinfo_realName'
    			}
    		]
    	});
    	grid=$('#approvalDatatables').dataTable(options);
    	//实现全选反选
    	docheckboxall('checkallApproval','checkchildApproval');
    	//实现单击行选中
    	clickrowselected('approvalDatatables');
    });
}
