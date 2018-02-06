var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesList.listOptions({
		ajax:function (data, callback, settings){datatablesListCallBack(data, callback, settings,'../xtQuartzSetController/getXtQuartzSetList',opt);},//渲染数据
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
				data:"jobId",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"jobId",
				width:"50px"
			},
			{
				data:'jobName'
			},
			{
				data:'jobGroup'
			},
			{
				data:'jobStatus',
				render:function(value, type, row, meta) {
					if(value == 'NORMAL'){
						return "启动";
					}else if(value == 'PAUSED'){
						return "暂停";
					}else{
						return "其它";
					}
				}
			},
			{
				data:'cronExpression'
			},
			{
				data:'desc_',
				render:function(value, type, row, meta) {
					if(value == null){
						return "";
					}
					return value;
				}
			},
			{
				data:"jobId",
				render:function(data, type, row, meta) {
					var jobGroup = row.jobGroup;
  					var jobName = row.jobName;
  					var jobStatus = row.jobStatus;
					var btn ='<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=stopXtQuartz("'+jobGroup+'","'+jobName+'","'+jobStatus+'")><i class="glyphicon glyphicon-stop"></i>暂停</button>';
					btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=reStartXtQuartz("'+jobGroup+'","'+jobName+'","'+jobStatus+'")><i class="glyphicon glyphicon-wrench"></i>恢复</button>';
					btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=delXtQuartz("'+jobGroup+'","'+jobName+'","'+jobStatus+'")><i class="glyphicon glyphicon-trash"></i>删除</button>';
					btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=startXtQuartz("'+jobGroup+'","'+jobName+'","'+jobStatus+'")><i class="glyphicon glyphicon-time"></i>执行</button>';
					btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=oneSecondXtQuartz("'+jobGroup+'","'+jobName+'","'+jobStatus+'")><i class="glyphicon glyphicon-play"></i>一秒一次</button>';
					btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=fiveSecondsXtQuartz("'+jobGroup+'","'+jobName+'","'+jobStatus+'")><i class="glyphicon glyphicon-pause"></i>五秒一次</button>';
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

function stopXtQuartz(jobGroup,jobName,jobStatus){
	if(jobStatus == 'NORMAL'){
    	doStopXtQuartz(jobGroup,jobName);
    }else{
    	toastrBoot("任务已经关闭,不能操作了!");
    }
}

function reStartXtQuartz(jobGroup,jobName,jobStatus){
	if(jobStatus == 'PAUSED'){
    	doReStartXtQuartz(jobGroup,jobName);
    }else{
    	toastrBoot("任务已经启动,不能操作了!");
    }
}

/**暂停任务**/
function doStopXtQuartz(jobGroup,jobName){
	msgTishCallFnBoot("确定暂停该任务？",function(){
		var params = {jobGroup:jobGroup,jobName:jobName};
		ajaxBReq('../xtQuartzSetController/stopXtQuartzSet',params,['datatables']);
	})
}
/**删除操作**/
function delXtQuartz(jobGroup,jobName){
	msgTishCallFnBoot("确定删除该任务？",function(){
		var params = {jobGroup:jobGroup,jobName:jobName};
		ajaxBReq('../xtQuartzSetController/delXtQuartzSet',params,['datatables']);
	})
}
/**重启任务**/
function reStartXtQuartz(jobGroup,jobName){
	msgTishCallFnBoot("确定重启该任务？",function(){
		var params = {jobGroup:jobGroup,jobName:jobName};
		ajaxBReq('../xtQuartzSetController/reStartXtQuartzSet',params,['datatables']);
	})
}
/**立即执行一次**/
function startXtQuartz(jobGroup,jobName){
	msgTishCallFnBoot("确定要立即执行一次该任务？",function(){
		var params = {jobGroup:jobGroup,jobName:jobName};
		ajaxBReq('../xtQuartzSetController/startXtQuartzSet',params,['datatables']);
	})
}
/**一秒执行一次**/
function oneSecondXtQuartz(jobGroup,jobName){
	msgTishCallFnBoot("确定要一秒执行一次该任务？",function(){
		var params = {jobGroup:jobGroup,jobName:jobName};
		ajaxBReq('../xtQuartzSetController/oneSecondXtQuartzSet',params,['datatables']);
	})
}
/**五秒执行一次**/
function fiveSecondsXtQuartz(jobGroup,jobName){
	msgTishCallFnBoot("确定要五秒执行一次该任务？",function(){
		var params = {jobGroup:jobGroup,jobName:jobName};
		ajaxBReq('../xtQuartzSetController/fiveSecondsXtQuartzSet',params,['datatables']);
	})
}
/**默认启动**/
function defaultStartXtQuartzSet(){
	msgTishCallFnBoot("确定要默认启动该任务？",function(){
		ajaxBReq('../xtQuartzSetController/defaultStartXtQuartzSet',null,['datatables']);
	})
}

