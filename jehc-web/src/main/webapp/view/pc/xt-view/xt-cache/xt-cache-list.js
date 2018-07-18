$(function (){
	initTreeTable();
}) 
var dialogWating;
function initTreeTable(){
	dialogWating = showWating({msg:'正在拼命的加载中...'});
	$.ajax({
        url:"../xtCacheController/getXtCacheListByCondition",
        type:"post",
        dataType:"json",
        success:function(data) {
        	closeWating(null,dialogWating);
        	var $table = $("#table");
        	data = eval("(" + data + ")");
    	    $('#table').bootstrapTable('destroy').bootstrapTable({
    	        columns:[
    	        {
    	            title:'名称',
    	            field:'CacheName',
    	            align:'left',
    	        },
    	        {
    	            title:'数量',
    	            field:'CacheSize',
    	            align:'left'
    	        },
    	        {
    	            title:'读取命中次数',
    	            field:'Hits',
    	            align:'left'
    	        },
    	        {
    	            title:'读取错失次数',
    	            field:'Misses',
    	            align:'left',
    	            formatter:function(value, row, index) {
    	            	return '<span class="label label-default">'+value+'</span>';
    	            }
    	        },
    	        {
    	            title:'硬盘存储大小',
    	            field:'DiskStoreSize',
    	            align:'left'
    	        },
    	        {
    	        	title:'类型',
    	            field:'CacheType',
    	            align:'left'
    			},
    	        {
    	            title:'占用内存大小',
    	            field:'MemoryStoreSize',
    	            align:'left'
    	        },
    	        {
    	            title:'操作',
    	            field:'CacheName',
    	            formatter:function(value, row, index) {
    	            	return '<a href=javascript:delCache("'+value+'") class="btn btn-danger" title="清空缓存"><i class="fa fa-trash-o"></i></a>';
    	            }
    	        }],
    	        data:data,
    	        method:'post',//请求方式(*)
    	        striped:true,//是否显示行间隔色
    	        cache:false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性(*)
    	        pagination:false,//是否显示分页(*)
    	        sortable:true,//是否启用排序
    	        sortOrder:"desc",//排序方式
    	        sidePagination:"server",//分页方式：client客户端分页，server服务端分页(*)
    	        showColumns:false,//是否显示所有的列
    	        showRefresh:false,//是否显示刷新按钮
    	        minimumCountColumns:2,//最少允许的列数
    	        clickToSelect:true,//是否启用点击选中行
    	        detailView:true,//是否显示父子表    *关键位置*
    	        contentType:"application/x-www-form-urlencoded; charset=UTF-8",
    	        checkboxHeader:true,
    	        search:false,
    	        singleSelect:true,
    	        striped:true,
    	        showColumns:true,//开启自定义列显示功能
    	        //注册加载子表的事件。你可以理解为点击父表中+号时触发的事件
    	        expanderExpandedClass:'fa fa-minus-circle',
                expanderCollapsedClass:'fa fa-plus-circle',
    	        onExpandRow:function(index, row, $detail) {
    	            var cur_table = $detail.html('<table></table>').find('table');
    	            var html = "";
    	            html += "<table class='table'>";
    	            html += "<thead>";
    	            html += "<tr style='height:40px;'>";
    	            html += "<th>键</th>";
    	            html += "<th>值</th>";
    	            html += "</tr>";
    	            html += "</thead>";
    	            $.ajax({
    	                type:"post",
    	                url:"../xtCacheController/getXtCacheDataListByCondition",
    	                data:{cacheName:row.CacheName},
    	                async:false,//同步请求
    	                success:function(data){
    	                	var dat = eval("(" + data + ")");
    	                    html += '<ul class="list-group" >';
    	                    $.each(dat,
    	                    function(n, value) {
    	                        html += "<tr align='center'>" 
    	                            + "<td>" + value.key + "</td>" 
    	                            + "<td>" + value.value + "</td>" 
    	                            + "</tr>";
    	                    });
    	                    html += '</table>';
    	                    $detail.html(html);
    	                }
    	            });
    	        }
    	    });
        }
	});
}

function delCache(value){
	if(value == null){
		toastrBoot(4,"未能获取该缓存名称");
		return;
	}
	msgTishCallFnBoot("确定清空该缓存？",function(){
		var params = {cacheName:value};
		ajaxBRequestCallFn('../xtCacheController/delCache',params,function(result){
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