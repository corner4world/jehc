var setting = {
   view:{
       selectedMulti:false
   },
   check:{
       enable:false
   },
   async:{
       enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
       url:"../xtAreaRegionController/getXtAreaRegionListByCondition",//Ajax获取数据的 URL地址  
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
function delXtAreaRegion(){
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"必须选择一条记录进行删除");
		return;
	}
	var params = {ID:nodes[0].id};
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		ajaxBRequestCallFn('../xtAreaRegionController/delXtAreaRegion',params,function(result){
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