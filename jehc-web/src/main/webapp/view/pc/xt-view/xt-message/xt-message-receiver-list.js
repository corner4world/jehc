$(document).ready(function() {
/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt1 = {
		searchformId:'searchReceiverForm'
	};
	var options1 = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtMessageController/getXtMessageListByCondition',opt1);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		tableHeight:tableHeight()*0.2+'px',
		//列表表头字段
		colums:[
			{
				sClass:"text-center",
				width:"50px",
				data:"xt_message_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildReceiver " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_message_id",
				width:"50px"
			},
			{
				data:'fromName'
			},
			{
				data:'toName'
			},
			{
				data:'isread',
				render:function(data, type, row, meta) {
					if(data == 0){
						return "未读";
					}
					if(data == 1){
						return "已读";
					}
				}
			},
			{
				data:'ctime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'readtime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:"xt_message_id",
				width:"150px",
				render:function(data, type, row, meta) {
					return "<button class='btn btn-default' onclick=\"javascript:toXtMessageDetail('"+ data +"',1)\"><span class='glyphicon glyphicon-eye-open'></span></button>";
				}
			}
		]
	});
	var grid1=$('#datatablesReceiver').dataTable(options1);
	//实现全选反选
	docheckboxall('checkallReceiver','checkchildReceiver');
	//实现单击行选中
	clickrowselected('datatablesReceiver');
});
