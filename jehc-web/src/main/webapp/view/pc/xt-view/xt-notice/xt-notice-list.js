var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtNoticeController/getXtNoticeListByCondition',opt);},//渲染数据
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
				data:"xt_notice_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_notice_id",
				width:"150px"
			},
			{
				data:'xt_title'
			},
			{
				data:'xt_state',
				render:function(data, type, row, meta) {
					if(data == 0){
						return "初稿";
					}
					if(data == 1){
						return "审核中";
					}
					if(data == 2){
						return "审核通过";
					}
					if(data == 3){
						return "审核未通过";
					}
				}
			},
			{
				data:'xt_createTime'
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:"xt_notice_id",
				width:"150px",
				render:function(data, type, row, meta) {
					var xt_userinfo_id = row.xt_userinfo_id;
					var btn = '<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=toXtNoticeDetail("'+data+'")><i class="glyphicon glyphicon-eye-open"></i>详情</button>';
						btn = btn +'<jEhcPermissionTag:jehcBtnTag modules="updateXtNotice" systemUID="'+xt_userinfo_id+'"><button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=toXtNoticeUpdate_("'+data+'")><i class="glyphicon glyphicon-edit"></i>修改</button></jEhcPermissionTag:jehcBtnTag>';
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
//新增
function toXtNoticeAdd(){
	tlocation('../xtNoticeController/toXtNoticeAdd');
}
//修改
function toXtNoticeUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	tlocation("../xtNoticeController/toXtNoticeUpdate?xt_notice_id="+id);
}
//修改
function toXtNoticeUpdate_(id){
	tlocation("../xtNoticeController/toXtNoticeUpdate?xt_notice_id="+id);
}
//详情
function toXtNoticeDetail(id){
	tlocation("../xtNoticeController/toXtNoticeDetail?xt_notice_id="+id);
}
//删除
function delXtNotice(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");
		return;
	}
	/**控制数据功能权限操作相关代码开始**/
	var systemUID=[];
	var table = $('#datatables').dataTable();
	var List = getTableContent(table);
	var idList = returncheckIds('checkId');
	for(var i = 0; i < idList.length; i++){
		var id = idList[i];
		for(var j = 0; j < List.length; j++){
			if(id == List[j].xt_notice_id){
				systemUID.push(List[j].xt_userinfo_id);
				break;
			}
		}
	}
	/**控制数据功能权限操作相关代码结束**/
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = idList.join(",");
		var params = {xt_notice_id:id,systemUID:systemUID.join(",")};
		ajaxBReq('../xtNoticeController/delXtNotice',params,['datatables']);
	})
}
