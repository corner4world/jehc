//返回r
function goback(){
	tlocation("../xtNotifyController/loadXtNotify");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtNotify(){
	submitBForm('defaultForm','../xtNotifyController/addXtNotify','../xtNotifyController/loadXtNotify');
}
$(document).ready(function() {
	$('#sel_userTag').select2({  
	    placeholder:"请选择接收人",  
	    tags:true,  
	    createTag:function (decorated, params) {  
	        return null;  
	    },  
	    width:'456px'  
	});  
});
//用户选择器
function UserSelect(){
	/*$('#UserinfoSelectPanelBody').height(reGetBodyHeight()*0.95);*/
	$('#UserinfoSelectModal').modal({backdrop:'static',keyboard:false});
	$('#UserinfoSelectModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        // 是弹出框居中。。。  
        var $modal_dialog = $("#UserinfoSelectModalDialog");  
        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
    });
	
	$('#searchFormUserinfo')[0].reset();
	var opt = {
			searchformId:'searchFormUserinfo'
		};
	var options = DataTablesList.listOptions({
		ajax:function (data, callback, settings){datatablesListCallBack(data, callback, settings,'../xtUserinfoController/getXtUserinfoByMap',opt);},//渲染数据
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
				data:"xt_userinfo_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchildUserinfo" value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"xt_userinfo_id",
				width:"50px"
			},
			{
				data:'xt_userinfo_name'
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:'xt_userinfo_phone'
			},
			{
				data:'xt_userinfo_origo'
			},
			{
				data:'xt_userinfo_birthday'
			},
			{
				data:'xt_userinfo_email'
			}
		]
	});
	grid=$('#UserinfoDatatables').dataTable(options);
	//实现全选反选
	docheckboxall('checkallUserinfo','checkchildUserinfo');
	//实现单击行选中
	clickrowselected('UserinfoDatatables');
}

function doUserSelect(){
	
}