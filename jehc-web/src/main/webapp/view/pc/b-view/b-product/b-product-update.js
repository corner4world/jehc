//返回r
function goback(){
	tlocation("../bProductController/loadBProduct");
}
$('#defaultForm').bootstrapValidator({
	message:'此值不是有效的'
});
//保存
function updateBProduct(){
	submitBForm('defaultForm','../bProductController/updateBProduct','../bProductController/loadBProduct');
}
//初始化日期选择器
$(document).ready(function(){
	datetimeInit();
	initBbrandList();
});


function initBbrandList(){
	var str = "<option value=''>请选择</option>";
	$.ajax({
	   type:"GET",
	   url:"../bBrandController/getBBrandList",
	   success: function(result){
		   //从服务器获取数据进行绑定
           $.each(result, function(i, item){
           	 str += "<option value=" + item.b_brand_id + ">" + item.b_brand_name + "</option>";
           })
           $("#b_brand_id").append(str);
           $('#b_brand_id').val($('#b_brand_id_').val());
	   }
	});
}

/////////////////////品类选择器开始///////////////////
function bCategorySelect(){
	$('#bcategoryBody').height(300);
	var $modal_dialog = $("#bcategorySelectDialog");  
	$modal_dialog.css({'width':'500px'});  
	$('#bcategorySelectModal').modal({"backdrop":"static"});
	var setting = {
		view:{
			selectedMulti:false
		},
		check:{
			enable:false
		},
		async:{
			enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
			url:"../bCategoryController/getBCategoryList",//Ajax获取数据的 URL地址  
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

function doBcategorySelect(){
	var zTree = $.fn.zTree.getZTreeObj("menu"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"请选择一条品类");
		return;
	}
	msgTishCallFnBoot("确定要选择【<font color=red>"+nodes[0].name+"</font>】？",function(){
		$("#b_category_id").val(nodes[0].id);
		$("#b_category_name").val(nodes[0].name);
		$('#bcategorySelectModal').modal('hide');
	})
}
/////////////////////品类选择器结束///////////////////