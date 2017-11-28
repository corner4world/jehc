var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../lcProcessController/getLcProcessListByCondition',opt);},//渲染数据
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
				data:"lc_process_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"lc_process_id",
				width:"50px"
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:'lc_process_title'
			},
			{
				data:'lc_process_status',
				render:function(data, type, row, meta) {
					if(data == 0){
						return "待发布";
					}
					if(data == 1){
						return "发布中";
					}
					if(data == 2){
						return "已关闭";
					}
				}
			},
			{
				data:'lc_process_flag',
				render:function(data, type, row, meta) {
					if(data == 0){
						return "通过平台设计器设计";
					}
					if(data == 1){
						return "通过上传部署";
					}
				}
			},
			{
				data:"lc_process_id",
				render:function(data, type, row, meta) {
					var lc_process_flag = row.lc_process_flag;
					var lc_process_title = row.lc_process_title;
					var xt_attachment = row.xt_attachment;
					var btn = '<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=toLcProcessDetail("'+data+'")><i class="glyphicon glyphicon-eye-open"></i>详情</button>';
					if(lc_process_flag == 0){
			        	btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=addLcDesign("'+data+'","'+lc_process_title+'")><i class="glyphicon glyphicon-cog"></i>设计流程</button>';
			        	btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=downFileBpmn("'+data+'")><i class="glyphicon glyphicon-save"></i>下载bpmn文件</button>';
			        	btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=downFileImg("'+data+'")><i class="glyphicon glyphicon-save"></i>下载img文件</button>';
					}else{
						btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=downFile("'+xt_attachment+'")><i class="glyphicon glyphicon-save"></i>下载附件</button>';
					}
					btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=createDeployment("'+data+'")><i class="glyphicon glyphicon-pencil"></i>发布流程</button>';
					btn = btn +'<button class="btn btn-sm green btn-outline filter-submit margin-bottom" onclick=showLcDeploymentHis("'+data+'","'+lc_process_title+'")><i class="glyphicon glyphicon-wrench"></i>发布历史记录</button>';
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
	
	
	InitconstantList(3,'xt_constant_id');
});
//新增
function toLcProcessAdd(){
	tlocation('../lcProcessController/toLcProcessAdd');
}
//修改
function toLcProcessUpdate(){
	if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");
		return;
	}
	var id = $(".checkchild:checked").val();
	var table = $('#datatables').dataTable();
	var List = getTableContent(table);
	for(var i = 0; i < List.length; i++){
		var obj = List[i];
		if(obj.lc_process_id == id && obj.lc_process_flag == 0){
			toastrBoot(4,"通过平台设计器设计的数据不能在编辑中修改 只能在设计器中修改");
			return;
		}
	}
	tlocation("../lcProcessController/toLcProcessUpdate?lc_process_id="+id);
}
//详情
function toLcProcessDetail(id){
	tlocation("../lcProcessController/toLcProcessDetail?lc_process_id="+id);
}
//删除
function delLcProcess(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {lc_process_id:id};
		ajaxBReq('../lcProcessController/delLcProcess',params,['datatables']);
	})
}


function addLcDesign(lc_process_id,lc_process_title){
	$('#lcDesignPanelBody').height(reGetBodyHeight()*0.8);
	$('#lcDesignModalLabel').html("在线设计---<font color=red>"+lc_process_title+"</font>");
	$('#lcDesignModal').modal({backdrop:'static',keyboard:false});
	$("#lcDesignIframe",document.body).attr("src",'../lcProcessController/loadLcDesign?lc_process_id='+lc_process_id) 
	
	$('#lcDesignModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        // 是弹出框居中。。。  
        var $modal_dialog = $("#lcDesignModalDialog");  
        /**
        //获取可视窗口的高度  
        var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;  
        //得到dialog的高度  
        var dialogHeight = $modal_dialog.height();  
        //计算出距离顶部的高度  
        var m_top = (clientHeight - dialogHeight)/2;  
        console.log("clientHeight : " + clientHeight);  
        console.log("dialogHeight : " + dialogHeight);  
        console.log("m_top : " + m_top);  
        $modal_dialog.css({'margin': m_top + 'px auto'});  
        **/
        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
    });  
}
function closeLcProcessWin(){
	search('datatables');
}
function showLcDeploymentHis(lc_process_id,lc_process_title){
	$('#lcDeploymentHisPanelBody').height(reGetBodyHeight()*0.8);
	$('#lcDeploymentHisModalLabel').html("流程发布历史记录---<font color=red>"+lc_process_title+"</font>");
	$('#lcDeploymentHisModal').modal({backdrop:'static',keyboard:false});
	$("#lcDeploymentHisIframe",document.body).attr("src",'../lcDeploymentHisController/loadLcDeploymentHis?lc_process_id='+lc_process_id) 
	$('#lcDeploymentHisModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        var $modal_dialog = $("#lcDeploymentHisModalDialog");  
        /**
        //获取可视窗口的高度  
        var clientHeight = (document.body.clientHeight < document.documentElement.clientHeight) ? document.body.clientHeight: document.documentElement.clientHeight;  
        //得到dialog的高度  
        var dialogHeight = $modal_dialog.height();  
        //计算出距离顶部的高度  
        var m_top = (clientHeight - dialogHeight)/2;  
        console.log("clientHeight : " + clientHeight);  
        console.log("dialogHeight : " + dialogHeight);  
        console.log("m_top : " + m_top);  
        $modal_dialog.css({'margin': m_top + 'px auto'});  
        **/
        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
    });  
//	reGetWidthAndHeight();
//	lcDeploymentHisWin = Ext.create('Ext.Window',{
//		layout:'fit',
//		width:clientWidth,                    
//		height:clientHeight, 
//		maximizable:true,
//		minimizable:true,
//		animateTarget:document.body,
//		plain:true,
//		modal:true,
//		title:'流程发布历史记录',
//		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../lcDeploymentHisController/loadLcDeploymentHis?lc_process_id='+lc_process_id+'"></iframe>',
//		listeners:{
//			minimize:function(win,opts){
//				win.collapse();
//			},
//			close:function(){
//				search('datatables');
//			}
//		}
//	});
//	lcDeploymentHisWin.show();
}

function downFileImg(lc_process_id){
	downOrExportB(basePath+'/lcProcessController/downFileImg?lc_process_id='+lc_process_id);
}

function downFile(xt_attachment){
	downOrExportB(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment);
}

function downFileBpmn(lc_process_id){
	downOrExportB(basePath+'/lcProcessController/downFileBpmn?lc_process_id='+lc_process_id);
}

function createDeployment(lc_process_id){
	msgTishCallFnBoot("确定要发布该流程？",function(){
		var params = {lc_process_id:lc_process_id};
		ajaxBReq('../lcProcessController/createDeployment',params,['datatables']);
	})
}