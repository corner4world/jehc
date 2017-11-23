//返回r
function goback(){
	tlocation("../xtPostController/loadXtPost");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateXtPost(){
	submitBForm('defaultForm','../xtPostController/updateXtPost','../xtPostController/loadXtPost');
}

$.ajax({
   type:"GET",
   url:"../xtPostController/getXtPostById",
   data:"xt_post_id="+$('#xt_post_parentId').val(),
   success: function(result){
	   result = eval("(" + result + ")");  
	   result = result.data;
	   if(result != null && result != ''){
		   $('#xt_post_parentName').val(result.xt_post_name);
	   }else{
		   $('#xt_post_parentName').val("无");
		   $('#xt_post_parentId').val("0");
	   }
   }
});

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
		$('#xt_departinfo_name').val(nodes[0].name);
		$('#xt_departinfo_id').val(nodes[0].id);
		$('#xt_post_parentName').val("无");
		$('#xt_post_parentId').val("0");
		$('#departSelectModal').modal('hide');
	})
}
/////////////////////部门选择器结束///////////////////

/////////////////////岗位选择器开始///////////////////
function postSelect(){
	var xt_departinfo_id = $('#xt_departinfo_id').val();
	if(xt_departinfo_id ==  null || xt_departinfo_id == ""){
		toastrBoot(4,"请选择隶属部门！");
		return;
	}
	$('#postSelectModal').modal();
	var setting = {
		view:{
				selectedMulti:false
			},
			check:{
				enable:false
			},
			async:{
				enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
				url:"../xtPostController/getXtPostBZTree",//Ajax获取数据的 URL地址  
				otherParam:{ 
				'expanded':function(){return 'true'},
				'xt_departinfo_id':function(){ return $('#xt_departinfo_id').val()}
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
	$.fn.zTree.init($("#posttree"), setting);
}

function doPostSelect(){
	var zTree = $.fn.zTree.getZTreeObj("posttree"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"请选择一条上级岗位信息");
		return;
	}
	msgTishCallFnBoot("确定要选择【<font color=red>"+nodes[0].name+"</font>】？",function(){
		$('#xt_post_parentName').val(nodes[0].name);
		$('#xt_post_parentId').val(nodes[0].id);
		$('#postSelectModal').modal('hide');
	})
}
/////////////////////岗位选择器结束///////////////////