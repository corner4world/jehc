var setting = {
   view:{
       selectedMulti:false
   },
   check:{
       enable:false
   },
   async:{
       enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
       url:"../xtOrgController/getXtOrgTree",//Ajax获取数据的 URL地址  
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
var dialogWating;
function InitztData() {
	dialogWating = showWating({msg:'正在拼命的加载中...'});
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
	closeWating(null,dialogWating);
}  

//单击事件
function onClick(event, treeId, treeNode, msg){  
	var id = treeNode.id;
	var tempObject = treeNode.tempObject;
	if(tempObject == 'DEPART'){
		$('#postFieldSet').hide();
		$('#departFieldSet').show();
		ajaxBRequestCallFn('../xtDepartinfoController/getXtDepartinfoById?xt_departinfo_id='+id,null,function(result){
			var data = eval("(" + result + ")");  
			data = data.data;
			console.log(data);
			$('#xt_departinfo_name').val(data.xt_departinfo_name);
			$('#xt_departinfo_type').val(data.xt_departinfo_type);
			$('#xt_departinfo_connectTelNo').val(data.xt_departinfo_connectTelNo);
			$('#xt_departinfo_mobileTelNo').val(data.xt_departinfo_mobileTelNo);
			$('#xt_departinfo_faxes').val(data.xt_departinfo_faxes);
			$('#xt_departinfo_desc').val(data.xt_departinfo_desc);
			$('#xt_departinfo_time').val(data.xt_departinfo_time);
			$('#xt_departinfo_parentName').val(data.xt_departinfo_parentName);
		});
	}
	if(tempObject == 'POST'){
		$('#postFieldSet').show();
		$('#departFieldSet').hide();
		ajaxBRequestCallFn('../xtPostController/getXtPostById?xt_post_id='+id,null,function(result){
			var data = eval("(" + result + ")");  
			data = data.data;
			$('#xt_departinfo_name_').val(data.xt_departinfo_name);
			$('#xt_post_name').val(data.xt_post_name);
			$('#xt_post_desc').val(data.xt_post_desc);
			$('#xt_post_maxNum').val(data.xt_post_maxNum);
			$('#xt_post_grade').val(data.xt_post_grade);
			$('#xt_post_desc').val(data.xt_post_desc);
			$('#xt_departinfo_id_').val(data.xt_departinfo_id);
			if(data.xt_post_parentId == '0' || data.xt_post_parentId == '' || data.xt_post_parentId == null){
				$('#xt_post_parentName').val('无');
			}else{
				$('#xt_post_parentName').val(data.xt_post_pname);
			}
		});
	}
}  

/**
 * 搜索树，显示并展示
 * @param treeId
 * @param text文本框的id
 */
function filter(){
	InitztData();
}

//弹窗新增部门
function addOrg(){
	var zTree = $.fn.zTree.getZTreeObj("tree");
	var nodes = zTree.getSelectedNodes();
	if (nodes.length > 1) {
		toastrBoot(4,"只能选择一条记录");
		return;
	}else if(nodes.length <= 0){
		//直接是创建根部门
		$('#SaveOrUpdateXtOrgDepartForm')[0].reset();
		$('#SaveOrUpdateXtOrgDepartForm').bootstrapValidator({
			message:'此值不是有效的'
		});
		datetimeInit();
		$('#SaveOrUpdateXtOrgDepartModal').modal({"backdrop":"static"});
		$('#xt_departinfo_parentNameTemp').val("无");
	}else{
		if(nodes[0].tempObject == 'DEPART'){
			//弹窗选择创建子部门还是岗位
			var dialog = bootbox.dialog({
				title:'提示',
				message:"创建子部门或岗位",
				buttons:{
				    noclose: {
				        label:"创建子部门",
				        //className:'btn-warning',
				        callback:function(){
				            //return false;
				        	displayDepartOrPost(nodes[0],1)
				        }
				    },
				    ok:{
				        label:"创建岗位",
				        //className:'btn-info',
				        callback:function(){
				        	//return false;
				        	displayDepartOrPost(nodes[0],2)
				        }
				    },
				    cancel:{
				        label:"取消",
				        className:'btn-info',
				        callback:function(){
				        	//return false;
				        }
				    }
				}
			});
		}else if(nodes[0].tempObject == 'POST'){
			displayDepartOrPost(nodes[0],2);//只能创建子岗位
		}
	}
}

function displayDepartOrPost(node,flag){
	if(flag == 1){
		//创建子部门
		$('#SaveOrUpdateXtOrgDepartForm')[0].reset();
		$('#SaveOrUpdateXtOrgDepartForm').bootstrapValidator({
			message:'此值不是有效的'
		});
		datetimeInit();
		$('#xt_departinfo_parentIdTemp').val(node.id);
		$('#xt_departinfo_parentNameTemp').val(node.name);
		$('#SaveOrUpdateXtOrgDepartModal').modal({"backdrop":"static"});
	}
	if(flag == 2){
		//创建子岗位
		$('#SaveOrUpdateXtOrgPostForm')[0].reset();
		$('#SaveOrUpdateXtOrgPostForm').bootstrapValidator({
			message:'此值不是有效的'
		});
		if(node.tempObject == 'DEPART'){
			//如果当前选择节点是部门 则设置部门名称
			$('#xt_departinfo_id_Temp').val(node.id);
			$('#xt_departinfo_name_Temp').val(node.name);
		}
		if(node.tempObject == 'POST'){
			$('#xt_departinfo_id_Temp').val($('#xt_departinfo_id_').val());
			$('#xt_departinfo_name_Temp').val($('#xt_departinfo_name_').val());
			$('#xt_post_parentIdTemp').val(node.id);
			$('#xt_post_parentNameTemp').val(node.name);
		}
		$('#SaveOrUpdateXtOrgPostModal').modal({"backdrop":"static"});
	}
}

//处理新增窗体
function doSaveOrUpdateOrg(flag){
	if(flag == 1){
		//编辑或修改部门
		submitBFormCallFn('SaveOrUpdateXtOrgDepartForm','../xtOrgController/saveOrUpdateXtDepartinfo',function(result){
			try {
	    		result = eval("(" + result + ")");  
	    		if(typeof(result.success) != "undefined"){
	    			if(result.success){
	            		window.parent.toastrBoot(3,result.msg);
	            		filter('tree','keyword');
	            		$('#SaveOrUpdateXtOrgDepartModal').modal('hide');
	        		}else{
	        			window.parent.toastrBoot(4,result.msg);
	        		}
	    		}
			} catch (e) {
				
			}
		});
	}else if(flag == 2){
		submitBFormCallFn('SaveOrUpdateXtOrgPostForm','../xtOrgController/saveOrUpdateXtPost',function(result){
			try {
	    		result = eval("(" + result + ")");  
	    		if(typeof(result.success) != "undefined"){
	    			if(result.success){
	            		window.parent.toastrBoot(3,result.msg);
	            		filter('tree','keyword');
	            		$('#SaveOrUpdateXtOrgPostModal').modal('hide');
	        		}else{
	        			window.parent.toastrBoot(4,result.msg);
	        		}
	    		}
			} catch (e) {
				
			}
		});
	}
}

//弹窗编辑窗口
function updateOrg(){
	var zTree = $.fn.zTree.getZTreeObj("tree");
	var nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"只能选择一条记录");
		return;
	}
	var id = nodes[0].id;
	if(nodes[0].tempObject == 'DEPART'){
		ajaxBRequestCallFn('../xtDepartinfoController/getXtDepartinfoById?xt_departinfo_id='+id,null,function(result){
			$('#SaveOrUpdateXtOrgDepartModal').modal({"backdrop":"static"});
			var data = eval("(" + result + ")");  
			data = data.data;
			datetimeInit();
			$('#SaveOrUpdateXtOrgDepartForm')[0].reset();
			$('#SaveOrUpdateXtOrgDepartForm').bootstrapValidator({
				message:'此值不是有效的'
			});
			if(data.xt_departinfo_parentId == '0' || data.xt_departinfo_parentId == null || data.xt_departinfo_parentId == ''){
				$('#xt_departinfo_parentNameTemp').val("无");
			}else{
				$('#xt_departinfo_parentNameTemp').val(data.xt_departinfo_parentName);
			}
			$('#xt_departinfo_parentIdTemp').val(data.xt_departinfo_parentId);
			$('#xt_departinfo_nameTemp').val(data.xt_departinfo_name);
			$('#xt_departinfo_typeTemp').val(data.xt_departinfo_type);
			$('#xt_departinfo_connectTelNoTemp').val(data.xt_departinfo_connectTelNo);
			$('#xt_departinfo_mobileTelNoTemp').val(data.xt_departinfo_mobileTelNo);
			$('#xt_departinfo_faxesTemp').val(data.xt_departinfo_faxes);
			$('#xt_departinfo_descTemp').val(data.xt_departinfo_desc);
			$('#xt_departinfo_timeTemp').val(data.xt_departinfo_time);
			$('#xt_departinfo_idTemp').val(data.xt_departinfo_id);
		});
	}
	if(nodes[0].tempObject == 'POST'){
		ajaxBRequestCallFn('../xtPostController/getXtPostById?xt_post_id='+id,null,function(result){
			$('#SaveOrUpdateXtOrgPostModal').modal({"backdrop":"static"});
			var data = eval("(" + result + ")");  
			data = data.data;
			$('#SaveOrUpdateXtOrgPostForm')[0].reset();
			$('#SaveOrUpdateXtOrgPostForm').bootstrapValidator({
				message:'此值不是有效的'
			});
			$('#xt_post_idTemp').val(data.xt_post_id);
			$('#xt_post_nameTemp').val(data.xt_post_name);
			$('#xt_departinfo_id_Temp').val(data.xt_departinfo_id);
			$('#xt_departinfo_name_Temp').val(data.xt_departinfo_name);
			
			if(data.xt_post_parentId == '0' || data.xt_post_parentId == null || data.xt_post_parentId == ''){
				$('#xt_post_parentNameTemp').val('无');
			}else{
				$('#xt_post_parentNameTemp').val(data.xt_post_pname);
			}
			$('#xt_post_parentIdTemp').val(data.xt_post_parentId);
			$('#xt_post_descTemp').val(data.xt_post_desc);
			$('#xt_post_maxNumTemp').val(data.xt_post_maxNum);
			$('#xt_post_gradeTemp').val(data.xt_post_grade);
		});
	}
}

function delOrg(){
	var zTree = $.fn.zTree.getZTreeObj("tree"),
	nodes = zTree.getSelectedNodes();
	if (nodes.length != 1) {
		toastrBoot(4,"必须选择一条记录进行删除");
		return;
	}
	var params = {id:nodes[0].id,tempObject:nodes[0].tempObject};
	msgTishCallFnBoot("确定要删除所选择的数据？",function(){
		ajaxBRequestCallFn('../xtOrgController/delXtOrg',params,function(result){
			try {
	    		result = eval("(" + result + ")");  
	    		if(typeof(result.success) != "undefined"){
	    			if(result.success){
	    				filter('tree','keyword');
	            		window.parent.toastrBoot(3,result.msg);
	        		}else{
	        			window.parent.toastrBoot(4,result.msg);
	        		}
	    		}
			} catch (e) {
				
			}
		});
	})
}