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
		return '<a href=javascript:addXtMenuinfo("'+value+'","'+name+'") class="btn btn-default" title="添加子菜单"><i class="glyphicon glyphicon-plus">添加子菜单</i></a><a href=javascript:updateXtMenuinfo("'+value+'") title="编辑" class="btn btn-default"><i class="fa fa-edit">编辑</i></a><a href=javascript:delXtMenuinfo("'+value+'") class="btn btn-default" title="删除"><i class="fa fa-trash-o">删除</i></a>';
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

function changeMenuinfoSelect(){
	$('#changeXtMenuinfoBody').height(200);
	$('#changeXtMenuinfoModal').modal({backdrop:'static',keyboard:false});
	$('#changeXtMenuinfoModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        var setting = {
		   view:{
		       selectedMulti:false
		   },
		   check:{
		       enable:false
		   },
		   async:{
		       enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
		       url:"../xtMenuinfoController/getXtMenuinfoBZTree",//Ajax获取数据的 URL地址  
		       otherParam:{ 
		    	 　　'expanded':function(){return 'true'} 
		       } //异步参数
		   },
		   data:{
			   //必须使用data  
		       simpleData:{
		           enable:true,
		           idKey:"id",//id编号命名 默认  
		           pIdKey:"pId",//父id编号命名 默认   
		           rootPId:0 //用于修正根节点父节点数据，即 pIdKey 指定的属性值  
		       }
		   },
		   edit:{
		       enable:false
		   },  
		   callback:{  
		       onClick:onClick//单击事件
		   }  
		};
		$.fn.zTree.init($("#tree"), setting);
    });  
}
//单击事件
function onClick(event, treeId, treeNode, msg){  
} 

function doXtMenuinfoSel(){
	var id = $('#xt_menuinfo_id').val();
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"请选择菜单");
		return;
	}
	
    var node = zTree.getNodes();
    var treeNode;
    var nodesarray = zTree.transformToArray(node);
    for(var i = 0; i < nodesarray.length; i++){
    	if(nodesarray[i].id == id){
    		treeNode = nodesarray[i];
    		break;
    	}
    }
    
	//var treeNode = nodes[0];
	var result = '';
	result = getChildNodes(treeNode,result);
	result = treeNode.id + ","+result;
	if(result.indexOf(nodes[0].id) >= 0){
		toastrBoot(4,"您选择的上级菜单不合法");
		return;
	}
	msgTishCallFnBoot("确定要选择【<font color=red>"+nodes[0].name+"</font>】？",function(){
		$('#xt_menuinfo_parentTitle_').val(nodes[0].name);
		$('#xt_menuinfo_parentId_').val(nodes[0].id);
		$('#changeXtMenuinfoModal').modal('hide');
	})
}

function getChildNodes(treeNode,result){
    if (treeNode.isParent) {
      var childrenNodes = treeNode.children;
      if (childrenNodes) {
          for (var i = 0; i < childrenNodes.length; i++) {
              result += ',' + childrenNodes[i].id;
              result = getChildNodes(childrenNodes[i], result);
          }
      }
  }
  return result;
}