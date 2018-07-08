//返回r
function goback(){
	tlocation("../oaSuggestionboxReplyController/loadOaSuggestionboxReply");
}

var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../oaSuggestionboxReplyController/getOaSuggestionboxReplyList',opt);},//渲染数据
			//在第一位置追加序列号
			fnRowCallback:function(nRow, aData, iDisplayIndex){
				jQuery('td:eq(1)', nRow).html(iDisplayIndex +1);  
				return nRow;
		},
		order:[],//取消默认排序查询,否则复选框一列会出现小箭头
		//列表表头字段
		tableHeight:'100px',
		colums:[
			{
				sClass:"text-center",
				width:"50px",
				data:"oa_suggestionbox_replyID",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"oa_suggestionbox_replyID",
				width:"50px"
			},
			{
				data:'xt_userinfo_realName'
			},
			{
				data:'oa_suggestionbox_replyContent',
                render:function(data, type, row, meta) {
                	if(data.length>20){
                		return data.substring(0,20)+"...";
                	}else{
                		return data;
                	}
                }
			},
			{
				data:'createtime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:"oa_suggestionbox_replyID",
				width:"150px",
				render:function(data, type, row, meta) {
					data = row.oa_suggestionbox_replyContent;
					return "<button class='btn btn-default' onclick=\"javascript:toOaSuggestionboxReplyDetail('"+ data +"')\"><span class='icon-loop'></span></button>";
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
function toOaSuggestionboxReplyAdd(){
	tlocation('../oaSuggestionboxReplyController/toOaSuggestionboxReplyAdd');
}
//详情
function toOaSuggestionboxReplyDetail(data){
	$('#detailOaSuggestionboxReplyModal').modal({"backdrop":"static"});
	$('#oa_suggestionbox_replyContent').val(data);
}

//新增
function toOaSuggestionboxReplyAdd(){
	$('#addOaSuggestionboxReplyModal').modal({"backdrop":"static"});
	$('#addOaSuggestionboxReplyForm')[0].reset();
	$('#addOaSuggestionboxReplyForm').bootstrapValidator({
		message:'此值不是有效的'
	});
}

//处理添加操作
function doAddOaSuggestionboxReply(){
	submitBFormCallFn('addOaSuggestionboxReplyForm','../oaSuggestionboxReplyController/addOaSuggestionboxReply',function(result){
		try {
    		result = eval("(" + result + ")");  
    		if(typeof(result.success) != "undefined"){
    			if(result.success){
            		window.parent.toastrBoot(3,result.msg);
            		$('#addOaSuggestionboxReplyModal').modal('hide');
            		search('datatables');
        		}else{
        			window.parent.toastrBoot(4,result.msg);
        		}
    		}
		} catch (e) {
			
		}
	});
}
