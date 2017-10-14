var grid;
$(document).ready(function() {
	/////////////jehc扩展属性目的可方便使用（boot.js文件datatablesCallBack方法使用） 如弹窗分页查找根据条件 可能此时的form发生变化 此时 可以解决该类问题
	var opt = {
			searchformId:'searchForm'
	};
    var options = DataTablesPaging.pagingOptions({
    	ajax:function (data, callback, settings){datatablesCallBack(data, callback, settings,'../oaNoticeController/getOaNoticeListByCondition',opt);},//渲染数据
		 //在第一位置追加序列号
		 fnRowCallback:function(nRow, aData, iDisplayIndex){  
            jQuery("td:eq(1)", nRow).html(iDisplayIndex +1);  
            return nRow;  
         }, 
         order:[],//取消默认排序查询,否则复选框一列会出现小箭头
    	 //列表表头字段
		 colums:[
			{
				sClass:"text-center",
				width:"50px",
			    data:"oa_noticeID",
			    render:function (data, type, full, meta) {
			        return '<input type="checkbox" name="checkId" class="checkchild" value="' + data + '" />';
			    },
			    bSortable:false
			},
			{
				data:"oa_noticeID",
				width:"50px"
			}, 
            {
				data:"oa_noticeTitle",
				width:"150px",
                render:function(data, type, row, meta) {
                	if(data !=null){
                        return data;
                    }else{
                        return "";
                    }
                }	
            }, 
            {
            	data:"oa_noticeType",
            	width:"150px",
                render:function(data, type, row, meta) {
                	if(data == 1){
                        return '一般';
                    }else if(data == 2){
                        return "重要";
                    }else if(data == 3){
                    	return "非常重要";
                    }else{
                    	return "缺省";
                    }
                }
            },
            {
            	data:"xt_userinfo_realName",
            	width:"150px",
                render:function(data, type, row, meta) {
                	if(data !=null){
                        return data;
                    }else{
                        return "";
                    }
                }
            },
            {
            	data:"oa_noticeCreateTime",
            	width:"150px",
                render:function(data, type, row, meta) {
                	if(data !=null){
                        return data;
                    }else{
                        return "";
                    }
                }
            },
            {
            	data:"oa_noticeID",
            	width:"150px",
                render:function(data, type, row, meta) {
                	 return "<a href=\"javascript:toOaNoticeDetail('"+ data +"')\"><span class=\"glyphicon glyphicon-eye-open\"></span></a>";
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

//初始化日期选择器
$(document).ready(function() {
	datetimeInit();
	initLcStatus();
});

function initLcStatus(){
	//清空下拉数据
    $("#lc_status_store").html("");
    var str = "<option value=''>请选择</option>";
    $.ajax({
        type:"POST",
        url:"../lcStatusController/getLcStatusList",
        dataType:"JSON",
        async:false,
        success:function(data){
            //从服务器获取数据进行绑定
            $.each(data, function(i, item){
            	 str += "<option value=" + item.lc_status_id + ">" + item.lc_status_name + "</option>";
            })
            //将数据添加到省份这个下拉框里面
            $("#lc_status_store").append(str);
        },
        error:function(){}
    });
}

//新增
function toOaNoticeAdd(){
	tlocation("../oaNoticeController/toOaNoticeAdd");
}
//修改
function toOaNoticeUpdate(){
	 if($(".checkchild:checked").length != 1){
		toastrBoot(4,"选择数据非法");                
		return;
	 }
	 var id = $(".checkchild:checked").val();
	 tlocation("../oaNoticeController/toOaNoticeUpdate?oanoticeID="+id);
}
//删除
function delOaNotice(){
	if(returncheckedLength('checkchild') <= 0){
		toastrBoot(4,"请选择要删除的数据");                
		return;
	}
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		var id = returncheckIds('checkId').join(",");
		var params = {oanoticeID:id};
		ajaxBReq('../oaNoticeController/delOaNotice',params,['datatables']);
	})
}
//发送至详情页面
function toOaNoticeDetail(id){
	 tlocation("../oaNoticeController/toOaNoticeDetail?oanoticeID="+id);
}