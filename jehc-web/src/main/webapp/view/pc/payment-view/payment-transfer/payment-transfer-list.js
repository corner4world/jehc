var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../paymentTransferController/getPaymentTransferListByCondition',opt);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(0)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		//列表表头字段
		colums:[
			/*{
				sClass:"text-center",
				width:"50px",
				data:"id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},*/
			{
				data:"id",
				width:"50px"
			},
			{
				data:'outNo'
			},
			{
				data:'payeeAccount'
			},
			{
				data:'amount'
			},
			{
				data:'payerName'
			},
			{
				data:'payeeName'
			},
			{
				data:'bank'
			},
			{
				data:'curType',
				render:function(data, type, row, meta) {
					if(data == 'CNY'){
						return '人民币'
					}
					if(data == 'USD'){
						return '美元'
					}
					if(data == 'HKD'){
						return '港币'
					}
					if(data == 'MOP'){
						return '澳门元'
					}
					if(data == 'EUR'){
						return '欧元'
					}
					if(data == 'TWD'){
						return '新台币'
					}
					if(data == 'KRW'){
						return '韩元'
					}
					if(data == 'JPY'){
						return '日元'
					}
					if(data == 'SGD'){
						return '新加坡元'
					}
					if(data == 'AUD'){
						return '澳大利亚元'
					}
					return '缺省'; 
				}
			},
			{
				data:'ctime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'transferlasttime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'fromto',
				render:function(data, type, row, meta) {
					if(data == 'backstage'){
						return '后台支付'
					}
					if(data == 'fontpay'){
						return '前台支付'
					}
					return '缺省'; 
				}
			},
			{
				data:"id",
				width:"150px",
				render:function(data, type, row, meta) {
					return "<button class='btn btn-default' onclick=\"javascript:toPaymentTransferDetail('"+ data +"')\"><span class='icon-loop'></span></button>";
				}
			}
		]
	});
	grid=$('#datatables').dataTable(options);
	//实现全选反选
	/*docheckboxall('checkall','checkchild');*/
	//实现单击行选中
	clickrowselected('datatables');
});
//新增
function toPaymentTransferAdd(){
	tlocation('../paymentTransferController/toPaymentTransferAdd');
}
//修改
function toPaymentTransferUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	tlocation("../paymentTransferController/toPaymentTransferUpdate?id="+id);
}
//详情
function toPaymentTransferDetail(id){
	tlocation("../paymentTransferController/toPaymentTransferDetail?id="+id);
}
//删除
function delPaymentTransfer(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {id:id};
		ajaxBReq('../paymentTransferController/delPaymentTransfer',params,['datatables']);
	})
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
