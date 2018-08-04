var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtNumberController/getXtNumberListByCondition',opt);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(0)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		//列表表头字段
		colums:[
			{
				data:"xt_number_id",
				width:"50px"
			},
			{
				data:'lastvalue'
			},
			{
				data:'version'
			},
			{
				data:'createTime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'lastUpdateTime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'modulesType'
			}
		]
	});
	grid=$('#datatables').dataTable(options);
	//实现单击行选中
	clickrowselected('datatables');
});
