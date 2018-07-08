var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
		searchformId:'searchForm'
	};
	var options = DataTablesPaging.pagingOptions({
		ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../oaSuggestionboxReplyController/getOaSuggestionboxReplyListByCondition',opt);},//渲染数据
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
				data:"oa_suggestionbox_id",
				render:function (data, type, full, meta) {
					return '<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline"><input type="checkbox" name="checkId" class="checkchild " value="' + data + '" /><span></span></label>';
				},
				bSortable:false
			},
			{
				data:"oa_suggestionbox_id",
				width:"50px"
			},
			{
				data:'oa_suggestionboxTitle'
			},
			{
				data:'oa_suggestionboxIsPub',
                render:function(data, type, row, meta) {
                	if(data == 0){
                        return '是';
                    }else if(data == 1){
                        return "否";
                    }else{
                    	return "缺省";
                    }
                }
			},
			{
				data:'state',
                render:function(data, type, row, meta) {
                	if(data == 0){
                        return '未回复';
                    }else if(data == 1){
                        return "已回复";
                    }else{
                    	return "缺省";
                    }
                }
			},
			{
				data:'oa_suggestionboxType',
                render:function(data, type, row, meta) {
                	if(data == 0){
                        return '签名方式';
                    }else if(data == 1){
                        return "匿名";
                    }else{
                    	return "缺省";
                    }
                }
			},
			{
				data:'createTime',
				render:function(data, type, row, meta) {
					return dateformat(data); 
				}
			},
			{
				data:'xt_userinfo_realName',
				render:function(data, type, row, meta) {
					if(row.oa_suggestionboxType == 1){
						return "<font color=red>保密</font>";
					}
					return data; 
				}
			},
			{
				data:"oa_suggestionbox_id",
				width:"150px",
				render:function(data, type, row, meta) {
					return "<button class='btn btn-default' onclick=\"javascript:toOaSuggestionboxReplyDetail('"+ data +"')\">回复</button>";
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
function toOaSuggestionboxReplyDetail(id){
	tlocation("../oaSuggestionboxReplyController/toOaSuggestionboxReplyDetail?oa_suggestionbox_id="+id);
}
