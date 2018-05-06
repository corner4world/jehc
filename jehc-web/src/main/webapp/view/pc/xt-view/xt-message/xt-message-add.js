//返回r
function goback(){
	tlocation("../xtMessageController/loadXtMessage");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function addXtMessage(){
	submitBForm('defaultForm','../xtMessageController/addXtMessage','../xtMessageController/loadXtMessage');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});



//用户选择器
function XtUserSelect(){
	$('#UserinfoSelectModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
		$('#UserinfoBody').height(reGetBodyHeight()*0.5);
      // 是弹出框居中。。。  
      var $modal_dialog = $("#UserinfoModalDialog");  
      $('#searchFormUserinfo')[0].reset();
      $modal_dialog.css({'width':reGetBodyWidth()*0.5+'px'});  
      var opt = {
  			searchformId:'searchFormUserinfo'
  		};
  	var options = DataTablesPaging.pagingOptions({
  		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../xtUserinfoController/getXtUserinfoListByCondition',opt);},//渲染数据
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
  			}
  		]
  	});
  	grid=$('#UserinfoDatatables').dataTable(options);
  	//实现全选反选
  	docheckboxall('checkallUserinfo','checkchildUserinfo');
  	//实现单击行选中
  	clickrowselected('UserinfoDatatables');
  });  
}

function doSelectXtUserinfo(){
	if(returncheckedLength('checkchildUserinfo') <= 0){
		toastrBoot(4,"请选择接收人");
		return;
	}else if(returncheckedLength('checkchildUserinfo') > 1){
		toastrBoot(4,"接收人只能选择一条，重新选择！");
		return;
	}
	var xt_userinfo_id;
	var xt_userinfo_realName;
	var table = $('#UserinfoDatatables').dataTable();
	var List = getTableContent(table);
	var idList = returncheckIds('checkId');
	for(var i = 0; i < idList.length; i++){
		var id = idList[i];
		for(var j = 0; j < List.length; j++){
			if(id == List[j].xt_userinfo_id){
				xt_userinfo_id = List[j].xt_userinfo_id;
				xt_userinfo_realName = List[j].xt_userinfo_realName;
				break;
			}
		}
	}
	msgTishCallFnBoot("确定要所选择的用户？",function(){
		$('#xt_userinfo_realName').val(xt_userinfo_realName);
		$('#to_id').val(xt_userinfo_id);
		$('#UserinfoSelectModal').modal('hide');
	})
}