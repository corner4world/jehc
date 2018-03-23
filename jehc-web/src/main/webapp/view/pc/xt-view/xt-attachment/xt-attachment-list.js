var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtAttachmentController/getXtAttachmentListByCondition',opt);},//渲染数据
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
				data:"xt_attachment_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_attachment_id",
				width:"50px"
			},
			{
				data:'xt_attachmentTitle',
				render:function(data, type, row, meta) {
					if(data.length > 20){
						return data.substring(0,20)+"...";
					}
					return data;
				},
				width:"50px"
			},
			{
				data:'xt_attachmentName'
			},
			{
				data:'xt_attachmentType'
			},
			{
				data:'xt_attachmentSize'
			},
			{
				data:'xt_attachmentIsDelete',
				render:function(data, type, row, meta) {
					if(data == 0){
						return "正常";
					}
					if(data == 1){
						return "已删除";
					}
					return "缺省";
				}
			},
			{
				data:'xt_attachmentCtime'
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:"xt_attachment_id",
				render:function(data, type, row, meta) {
					return "<a href=\"javascript:toXtAttachmentDetail('"+ data +"')\"  class='btn btn-default'><span class='glyphicon glyphicon-eye-open'></span></a>";
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
function toXtAttachmentAdd(){
	tlocation('../xtAttachmentController/toXtAttachmentAdd');
}
//修改
function toXtAttachmentUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	tlocation("../xtAttachmentController/toXtAttachmentUpdate?xt_attachment_id="+id);
}
//详情
function toXtAttachmentDetail(id){
	tlocation("../xtAttachmentController/toXtAttachmentDetail?xt_attachment_id="+id);
}
//删除
function delXtAttachment(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {xt_attachment_id:id};
		ajaxBReq('../xtAttachmentController/delXtAttachment',params,['datatables']);
	})
}
