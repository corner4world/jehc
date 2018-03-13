$(function () {
	initTreeTable();
}) 
function initTreeTable(){
	$.ajax({
        url:"../xtMenuinfoController/getXtMenuinfoList",
        type:"post",
        dataType:"json",
        success:function(data) {
        	var $table = $("#table");
        	data = eval("(" + data + ")");
            $table.bootstrapTable('destroy').bootstrapTable({
            	data:data,
                striped:true,
                class:'table table-hover table-bordered',
                sidePagination:"client",//表示服务端请求  
                pagination:false,
                treeView:true,
                treeId:"id",
                treeField:"name",
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
                        field:'tempObject',
                        title:'性质'
                    },
                    {
                        field:'integerappend',
                        title:'数据权限',
                        formatter:'authorFormatter'
                    },
                    {
                        field:'integerappend',
                        title:'拦截类型',
                        formatter:'authorTypeFormatter'
                    },
                    {
                        field:'buessid',
                        title:'操作',
                        formatter:'btnFormatter'
                    }
                ],
                onLoadSuccess:function(data){
                }
            });
        }
	});
}

//数据权限
function authorFormatter(value, row, index){
	var integerappend = row.integerappend;
	if(integerappend != null && integerappend != ""){
		var val = integerappend.split(",");
    	if(val[0] == 0){
    		return "<font color='red'>是</font>";
    	}else if(val[0] == 1){
    		return "否";
    	}
	}
}

//拦截类型
function authorTypeFormatter(value, row, index){
	var val = value.split(",");
	if(val[1] == 0){
		return "无需拦截"
	}else if(val[1] == 1){
		return "<font color='red'>必须拦截</font>";
	}
}

//格式化按钮
function btnFormatter(value, row, index){
	var tempObject = row.tempObject;
	var name = row.name;
	if(tempObject == '菜单'){
		return '<a href=javascript:addXtMenuinfo("'+value+'","'+name+'") class="btn btn-success" title="添加子菜单"><i class="glyphicon glyphicon-plus"></i></a><a href=javascript:updateXtMenuinfo("'+value+'") title="编辑" class="btn btn-info"><i class="fa fa-edit"></i></a><a href=javascript:delXtMenuinfo("'+value+'") class="btn btn-danger" title="删除"><i class="fa fa-trash-o"></i></a>';
	}
}

function delXtMenuinfo(value){
	if(value == null){
		toastrBoot(4,"未能获取该数据编号");
		return;
	}
	msgTishCallFnBoot("确定删除该数据？",function(){
		var params = {xt_menuinfo_id:value};
		ajaxBRequestCallFn('../xtMenuinfoController/delXtMenuinfo',params,function(result){
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
function expandTree(){
	$('#table').bootstrapTable("expandAllTree")
}
function collapseTree(){
	$('#table').bootstrapTable("collapseAllTree")
}