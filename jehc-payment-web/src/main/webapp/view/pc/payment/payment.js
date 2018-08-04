var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,basePath+'/index/list',opt);},//渲染数据
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
				data:"id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"id",
				width:"50px"
			},
			{
				data:'seller'
			},
			{
				data:'pay_type'
			},
			{
				data:'is_test',
				render:function(data, type, row, meta) {
					if(data == 0){
						return '测试环境'
					}
					if(data == 1){
						return '正式环境'
					}
					return '缺省'; 
				}
			},
			{
				data:"id",
				width:"150px",
				render:function(data, type, row, meta) {
					return "<button class='btn btn-info m-btn m-btn--custom m-btn--icon' onclick=\"javascript:toPayment('"+ data +"')\">去支付</button>";
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
//详情
function toPayment(id){
	tlocation(basePath+"/index/goPay?id="+id);
}
