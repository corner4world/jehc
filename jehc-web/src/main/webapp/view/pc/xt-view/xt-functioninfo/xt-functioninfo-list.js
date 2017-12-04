var setting = {
   view:{
       selectedMulti:false
   },
   check:{
       enable:false
   },
   async:{
       enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
       url:"../xtFunctioninfoController/getXtFunctioninfoList",//Ajax获取数据的 URL地址  
       otherParam:{ 
    	 　　'expanded':function(){return 'false'} 
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
       onClick:onClick,//单击事件
       onAsyncSuccess:onAsyncSuccess//加载数据完成事件
   }  
};
$(document).ready(function(){
	InitztData();
});
//初始数据
function InitztData() {
	$.fn.zTree.init($("#tree"), setting);
}

//刷新
function refreshAll(){
	InitztData();
}

//加载数据完成事件
function onAsyncSuccess(event, treeId, treeNode, msg){  
	var length = $('#keyword').val().length;
	if(length > 0){
		var zTree = $.fn.zTree.getZTreeObj(treeId);
	    var nodeList = zTree.getNodesByParamFuzzy("name", $('#keyword').val());
	    //将找到的nodelist节点更新至Ztree内
	    $.fn.zTree.init($("#"+treeId), setting, nodeList);
	}
}  

//单击事件
function onClick(event, treeId, treeNode, msg){  
}  

/**
 * 搜索树，显示并展示
 * @param treeId
 * @param text文本框的id
 */
function filter(){
	InitztData();
}

//删除
function delXtFunctioninfo(){
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"必须选择一条记录进行删除");
		return;
	}
	if(nodes[0].tempObject != '功能'){
		toastrBoot(4,"选择的记录必须为功能才能删除");
		return;
	}
	console.info(nodes[0]);
	var params = {xt_functioninfo_id:nodes[0].id};
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		ajaxBRequestCallFn('../xtFunctioninfoController/delXtFunctioninfo',params,function(result){
			try {
	    		result = eval("(" + result + ")");  
	    		if(typeof(result.success) != "undefined"){
	    			if(result.success){
	            		window.parent.toastrBoot(3,result.msg);
	            		refreshAll();
	        		}else{
	        			window.parent.toastrBoot(4,result.msg);
	        		}
	    		}
			} catch (e) {
				
			}
		});
	})
}




/////////////////////模块选择器开始///////////////////
function xtMenuinfoSelect(flag){
	$('#flag').val(flag);
	$('#xtMenuinfoBody').height(300);
	var $modal_dialog = $("#xtMenuinfoSelectDialog");  
	$modal_dialog.css({'width':'500px'});  
	$('#xtMenuinfoSelectModal').modal({"backdrop":"static"});
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
	$.fn.zTree.init($("#menu"), setting);
}

//单击事件
function onClick(event, treeId, treeNode, msg){  

}  

function doXtMenuinfoSelect(){
	var zTree = $.fn.zTree.getZTreeObj("menu"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"请选择一条隶属模块信息");
		return;
	}
	msgTishCallFnBoot("确定要选择【<font color=red>"+nodes[0].name+"</font>】？",function(){
		if($('#flag').val() == 1){
			$("#addXtFunctioninfoForm").find('#xt_menuinfo_title').val(nodes[0].name);
			$("#addXtFunctioninfoForm").find('#xt_menuinfo_id_').val(nodes[0].id);
		}else if($('#flag').val() == 2){
			$("#updateXtFunctioninfoForm").find('#xt_menuinfo_title').val(nodes[0].name);
			$("#updateXtFunctioninfoForm").find('#xt_menuinfo_id_').val(nodes[0].id);
		}
		$('#xtMenuinfoSelectModal').modal('hide');
	})
}
/////////////////////模块选择器结束///////////////////