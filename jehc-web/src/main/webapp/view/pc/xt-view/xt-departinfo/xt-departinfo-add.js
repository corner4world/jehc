//返回r
function goback(){
	tlocation("../xtDepartinfoController/loadXtDepartinfo");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
});
//保存
function addXtDepartinfo(){
	submitBForm('defaultForm','../xtDepartinfoController/addXtDepartinfo','../xtDepartinfoController/loadXtDepartinfo');
}

/////////////////////部门选择器开始///////////////////
function departSelect(){
	$('#departSelectModal').modal();
	var setting = {
	   view:{
	       selectedMulti:false
	   },
	   check:{
	       enable:false
	   },
	   async:{
	       enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
	       url:"../xtDepartinfoController/getXtDepartinfoBZTree",//Ajax获取数据的 URL地址  
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
}

//单击事件
function onClick(event, treeId, treeNode, msg){  
}  
function doDepartSelect(){
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"请选择一条隶属部门信息");
		return;
	}
	msgTishCallFnBoot("确定要选择【<font color=red>"+nodes[0].name+"</font>】？",function(){
		$('#xt_departinfo_parentName').val(nodes[0].name);
		$('#xt_departinfo_parentId').val(nodes[0].id);
		$('#departSelectModal').modal('hide');
	})
}
/////////////////////部门选择器结束///////////////////