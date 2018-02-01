var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../lcApplyController/getLcApplyListByCondition',opt);},//渲染数据
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
				data:"lc_apply_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"lc_apply_id",
				width:"50px"
			},
			{
				data:'lc_apply_title'
			},
			{
				data:'xt_constantRemark'
			},
			{
				data:'lc_apply_time',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			}
		]
	});
	grid=$('#datatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkall','checkchild');
	//实现单击行选中
	clickrowselected('datatables');
	
	//实现单击操作开始
	var table = $('#datatables').DataTable();
    $('#datatables tbody').on('click', 'tr', function () {
        var data = table.row( this ).data();
        showLcAppyDetail(data);
        console.info(data.lc_apply_id);
    });
    //实现单击操作结束
});
/**删除操作**/
function delLcApply(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {lc_apply_id:lc_apply_id};
		ajaxBReq('../lcApplyController/delLcApply',params,['datatables']);
	})
}
var grid_;
function showLcAppyDetail(data){
	var processInstance_id = data.processInstance_id;
	var processdefinitions_id = data.processdefinitions_id;
	var lc_apply_model_biz_id = data.lc_apply_model_biz_id;
	var lc_apply_id = data.lc_apply_id;
	var urlInstanceImag = "../lcProcessController/loadLcProcessInstanceImg?processInstanceId="+processInstance_id;
	var url ="../"+ data.xt_constantURL+"?lc_apply_model_biz_id="+lc_apply_model_biz_id;
	initLcApprovalWin(processInstance_id);
	$('#lcApplyDetailPanelBody').height(reGetBodyHeight()*0.8);
	$('#lcApplyDetailModal').modal({backdrop:'static',keyboard:false});
	$("#lcFormIframe",document.body).attr("src",url) 
	$("#lcProcessInstanceImgIframe",document.body).attr("src",urlInstanceImag) 
	$('#lcApplyDetailModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        // 是弹出框居中。。。  
        var $modal_dialog = $("#lcApplyDetailModalDialog");  
        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
    });  
	
	$('#xt_constantRemark').val(data.xt_constantRemark);
	$('#lc_apply_title').val(data.lc_apply_title);
	$('#lc_apply_time').val(dateformat(data.lc_apply_time));
}

function initLcApprovalWin(id){
	$('#searchFormApproval')[0].reset();
	$('#lc_apply_id_').val(id);
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
	grid_=$('#approvalDatatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkallApproval','checkchildApproval');
	//实现单击行选中
	clickrowselected('approvalDatatables');
}