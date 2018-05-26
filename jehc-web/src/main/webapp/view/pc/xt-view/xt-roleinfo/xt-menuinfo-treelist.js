//分配资源
function addXtMenuinfo(xt_role_id,xt_role_name){
	$('#XtMenuinfoBody').height(reGetBodyHeight()*0.7);
	$('#XtMenuinfoModal').modal({backdrop:'static',keyboard:false});
	$('#XtMenuinfoModal').modal({"backdrop":"static"}).modal('show').on("shown.bs.modal",function(){  
        // 是弹出框居中。。。  
        var $modal_dialog = $("#XtMenuinfoModalDialog");  
        $('#XtMenuinfoModalLabel').html("角色权限--->导入资源---><font color=red>"+xt_role_name+"</font>");
        $modal_dialog.css({'width':reGetBodyWidth()*0.9+'px'});  
        $('#roleIdForMenu').val(xt_role_id);
        var setting = {
   		   view:{
   		       selectedMulti:false
   		   },
   		   check:{
   		        enable:true,
   		    	chkboxType:  { "Y": "ps", "N": "" }
	       		/* chkboxType: { “Y”: “ps”, “N”: “ps” } 
	       		Y 属性定义 checkbox 被勾选后的情况； 
	       		N 属性定义 checkbox 取消勾选后的情况； 
	       		“p” 表示操作会影响父级节点； 
	       		“s” 表示操作会影响子级节点。 */
   		   },
   		   async:{
   		       enable:true,//设置 zTree是否开启异步加载模式  加载全部信息
   		       url:"../xtRoleinfoController/getXtMenuinfoListAll?xt_role_id="+xt_role_id,//Ajax获取数据的 URL地址  
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
   		   }  
   		};
        InitztData(setting);
    });  
}
//保存分配菜单
function addXtMR(){
	var treeObj = $.fn.zTree.getZTreeObj("tree");
    var nodes = treeObj.getCheckedNodes(true);
    //var msg = "name--id--pid\n";
    var id;
    for (var i = 0; i < nodes.length; i++) {
        //msg += nodes[i].name+"--"+nodes[i].id+"--"+nodes[i].pId+"\n";
    	if(id == null || id == ''){
			id = nodes[i].id;
		}else{
			id = id+','+nodes[i].id;
		}
    }
    msgTishCallFnBoot("确定保存分配的菜单？",function(){
    	var params = {id:id,xt_role_id:$('#roleIdForMenu').val()};
		ajaxBRequestCallFn('../xtRoleinfoController/addXtMR',params,function(){
			toastrBoot(3,"保存分配菜单成功");
		});
		
	})
}
//初始数据
function InitztData(setting) {
	$.fn.zTree.init($("#tree"), setting);
}
function closeXtMenuinfoWin(){
	$("#XtMenuinfoModal").modal('hide');//手动关闭
}