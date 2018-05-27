var xtNotifyRecivergrid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchXtNotifyReceiverForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){
			datatablesCallBack(data, callback, settings,'../xtNotifyReceiverController/getXtNotifyReceiverListByCondition',opt);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		tableHeight:tableHeight()*0.35+'px',
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		//列表表头字段
		colums:[
			{
				sClass:"text-center",
				width:"50px",
				data:"xt_notify_receiver_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkXtNotifyReceiverId" class="checkXtNotifyReceiverchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_notify_receiver_id",
				width:"50px"
			},
			{
				data:'xt_notify_title'
			},
			{
				data:'receive_time',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'sendUserRealName'
			},
			{
				data:"xt_notify_receiver_id",
				width:"150px",
				render:function(data, type, row, meta) {
					var btn = '<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=toXtReceiverDetail("'+data+'")><i class="glyphicon glyphicon-eye-open"></i>详情</button>';
					return btn;
				}
			}
		]
	});
	xtNotifyRecivergrid=$('#xtNotifyReceiverDatatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkXtNotifyReceiverall','checkXtNotifyReceiverchild');
	//实现单击行选中
	clickrowselected('xtNotifyReceiverDatatables');
	var table = $.fn.dataTable.fnTables(true); //防止tab切换后的表头错位
	if(table.length > 0) {
		$(table).dataTable().fnAdjustColumnSizing();
	};
});
//详情
function toXtReceiverDetail(id){
	tlocation("../xtNotifyReceiverController/toXtReceiverDetail?xt_notify_receiver_id="+id);
}

//删除
function delXtNotifyReceiver(){
	if(returncheckedLength('checkXtNotifyReceiverchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");                
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkXtNotifyReceiverId').join(",");
		var params = {xt_notify_receiver_id:id};
		ajaxBReq('../xtNotifyReceiverController/delXtNotifyReceiver',params,['xtNotifyReceiverDatatables']);
	})
}

