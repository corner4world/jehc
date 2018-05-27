var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtMessageController/getXtMessageListByCondition',opt);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		tableHeight:tableHeight()*0.4+'px',
		//列表表头字段
		colums:[
			{
				sClass:"text-center",
				width:"50px",
				data:"xt_message_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
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
					return "<button class='btn btn-default' onclick=\"javascript:toXtMessageDetail('"+ data +"',0)\"><span class='glyphicon glyphicon-eye-open'></span></button>";
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
//新增
function toXtMessageAdd(){
	tlocation('../xtMessageController/toXtMessageAdd');
}
//修改
function toXtMessageUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	tlocation("../xtMessageController/toXtMessageUpdate?xt_message_id="+id);
}
//详情
function toXtMessageDetail(id,type){
	tlocation("../xtMessageController/toXtMessageDetail?xt_message_id="+id+"&type="+type);
}
//删除
function delXtMessage(type){
	if(type == 2){
		if(returncheckedLength('checkchildReceiver') <= 0){
			toastrBoot(4,"请选择要删除的数据");
			return;
		}
		msgTishCallFnBoot("确定要删除所选择的数据？",function(){
			var id = returncheckIds('checkId').join(",");
			var params = {xt_message_id:id,type:type};
			ajaxBReq('../xtMessageController/delXtMessage',params,['datatablesReceiver']);
		})
	}else{
		if(returncheckedLength('checkchild') <= 0){
			toastrBoot(4,"请选择要删除的数据");
			return;
		}
		msgTishCallFnBoot("确定要删除所选择的数据？",function(){
			var id = returncheckIds('checkId').join(",");
			var params = {xt_message_id:id,type:type};
			ajaxBReq('../xtMessageController/delXtMessage',params,['datatables']);
		})
	}
	
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
