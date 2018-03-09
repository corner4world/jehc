$(function () {
	initTreeTable();
}) 
function initTreeTable(){
	$.ajax({
        url:"../xtDataDictionaryController/getXtDataDictionaryListByCondition",
        type:"post",
        dataType:"json",
        success:function(data) {
        	var $table = $("#table");
        	data = eval("(" + data + ")");
        	//data = jQuery.parseJSON(data);
            $table.bootstrapTable('destroy').bootstrapTable({
            	data:data,
                striped:true,
                sidePagination:"client",//表示服务端请求  
                idField:'id',
                sortable:false,//是否启用排序
                columns:[
                    /* 
                    {
                        field: 'ck',
                        checkbox:true
                    }, 
                    */
                    {
                        field:'name',
                        title:'名称'
                    },
                    {
                        field:'content',
                        title:'备注'
                    },
                    {
                        field:'tempObject',
                        title:'状态',
                        formatter:'statusFormatter'
                    },
                    {
                        field:'buessid',
                        title:'操作',
                        formatter:'btnFormatter'
                    }
                ],
                treeShowField:'name',
                parentIdField:'pid',
                onLoadSuccess:function(data){
                }
            });
            $table.treegrid({
                initialState:'collapsed',//收缩
                treeColumn:0,//指明第几列数据改为树形
                expanderExpandedClass:'glyphicon glyphicon-triangle-bottom',
                expanderCollapsedClass:'glyphicon glyphicon-triangle-right',
                onChange:function(){
                    $table.bootstrapTable('resetWidth');
                }
            });
        }
	});
}
//格式化按钮
function btnFormatter(value, row, index){
	var integerappend = row.integerappend;
	if(integerappend == 0){
		return '<a href=javascript:addXtDataDictionary("'+value+'") class="btn btn-success btn-sm" title="添加下级"><i class="glyphicon glyphicon-plus"></i></a><a href=javascript:updateXtDataDictionary("'+value+'") title="编辑" class="btn btn-info btn-sm"><i class="fa fa-edit"></i></a><a href=javascript:delXtDataDictionary("'+value+'") class="btn btn-danger btn-sm" title="删 除"><i class="fa fa-trash-o"></i></a>';
	}else{
		return '<a href=javascript:updateXtDataDictionary("'+value+'") class="btn btn-info btn-sm" title="编 辑"><i class="fa fa-edit"></i></a><a href=javascript:delXtDataDictionary("'+value+'") class="btn btn-danger btn-sm" title="删 除"><i class="fa fa-trash-o"></i></a>';
	}
}

//格式化状态
function statusFormatter(value, row, index) {
  if(value === '启用') {
    return '<span class="label label-success">启用</span>';
  } else {
    return '<span class="label label-default">禁用</span>';
  }
}

function delXtDataDictionary(value){
	if(value == null){
		toastrBoot(4,"未能获取该数据编号");
		return;
	}
	msgTishCallFnBoot("确定删除该数据？",function(){
		var params = {xt_data_dictionary_id:value};
		ajaxBRequestCallFn('../xtDataDictionaryController/delXtDataDictionary',params,function(result){
			try {
	    		result = eval("(" + result + ")");  
	    		if(typeof(result.success) != "undefined"){
	    			if(result.success){
	            		window.parent.toastrBoot(3,result.msg);
	            		initTreeTable();
	        		}else{
	        			window.parent.toastrBoot(4,result.msg);
	        		}
	    		}
			} catch (e) {
				
			}
		});
	})
}