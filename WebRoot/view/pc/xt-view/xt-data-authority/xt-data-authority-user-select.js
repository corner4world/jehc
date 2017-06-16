var xtUserinfoSelectWin;
var xtUserinfoSelectGrid;
var xtUserinfoSelectStore;
var xtDpPanelSelect;
var xtDpStoreSelect;
var oldID;
function showUserSelectWin(xt_userinfo_realName,xt_userinfo_id,xt_menuinfo_id,xt_menuinfo_title){
	reGetTopWidthAndHeight();
	initXtUserinfoSelectGrid(xt_menuinfo_id,xt_userinfo_id);
//	initXtDpPanelSelect(xt_menuinfo_id);
	xtUserinfoSelectWin = Ext.create('top.Ext.Window',{
		layout:'border', 
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		frame:true,
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:[/**xtDpPanelSelect,**/xtUserinfoSelectGrid]
	});
	xtUserinfoSelectWin.setTitle("数据权限--->按人员设置数据权限--->"+xt_menuinfo_title+"["+xt_userinfo_realName+"]--->选择被设置人员");
	xtUserinfoSelectWin.show();
}


/**用户显示**/
function initXtUserinfoSelectGrid(xt_menuinfo_id,xt_userinfo_id){
	reGetWidthAndHeight();
	xtUserinfoSelectStore = Ext.create('Ext.data.TreeStore',{
    	root:{
			name:'一级',
			id:'0',
			expanded:true
		},
		/**此处一定要设置否则全部展开节点无效**/
		autoLoad:false,
        proxy:{
            type:'ajax',
            method:'post',
			url:'../xtDataAuthorityController/getUserinfoAndFunctionInfoAndDataAuthorityTreeGrid',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',xt_menuinfo_id:xt_menuinfo_id,xt_userinfo_id:xt_userinfo_id,expanded:true}
        }
    });
    xtUserinfoSelectGrid = Ext.create('top.Ext.tree.Panel', {
    	region:'center',
        reserveScrollbar:true,
        collapsible:false,
        loadMask:true,
        useArrows:false,
        rootVisible:false,
        store:xtUserinfoSelectStore,
        autoSctroll:true,
        id:'treeGrid',
        animate:false,
        columnLines:true,
        frame:true,
        bufferedRenderer:false,
        viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		columns:[{
        	text:'ID',
            flex:2,
            hideable:false,
            hidden:true,
            sortable:true,
            dataIndex:'id'
        },{
            xtype:'treecolumn',
            text:'名称',
            flex:1,
            sortable:true,
            dataIndex:'text'
        },{
            text:'性质',
            dataIndex:'tempObject',
            sortable:true,
            renderer:function(value){
            	return value;
            }
        },{
            text:'备注',
            flex:1,
            dataIndex:'content',
            renderer:function(value){
            	return value;
            }
        }],
        listeners:{      
                'load':function(node){
					//加载结束之后
					oldID="";	
					var checkedNodes = xtUserinfoSelectGrid.getChecked();
					for(var i=0;i<checkedNodes.length;i++){
						if(oldID == null || oldID == ''){
							oldID = checkedNodes[i].id;
						}else{
							oldID = oldID+','+checkedNodes[i].id;
						}
					}   
                }  
        },
		tbar:[{
				text:'保 存',
				tooltip:'保 存',
				icon:saveIcon,
				handler:function(){
					addXtDataAuthorityByUser(xt_menuinfo_id,xt_userinfo_id);
				}
			 }]
    });
    /**选择父节点选中子节点**/
    xtUserinfoSelectGrid.on('checkchange',function(node,checked){  
	    node.expand();  
	    node.checked = checked;  
	    node.eachChild(function(child){  
	        child.set('checked',checked);  
	        child.fireEvent('checkchange',child,checked);  
	    });  
	}, xtUserinfoSelectGrid);
}
//function initXtDpPanelSelect(xt_menuinfo_id){
//	//1创建store
//	xtDpStoreSelect = Ext.create('Ext.data.TreeStore', {  
//	     root:{  
//              expanded:true  
//         },
//         proxy:{  
//                type:'ajax',  
//                url:'../xtCommonController/getXtOrgTree',  
//                reader:{  
//                    type:'json',  
//                    rootProperty:'items'  
//                },
//				extraParams:{id:'0',type:encodeURI('部门')}  
//         }  
//	});  
//	//2创建treePanel
//	xtDpPanelSelect = Ext.create('top.Ext.tree.Panel',{   
//		region:'west',
//        store:xtDpStore,  
//        autoEncode:true,//提交时是否自动编码   
//        rootVisible:false,  
//        width:200,
//        collapsible:true,
//        title:'组织机构',
//        /**新方法使用开始**/  
//        scrollable:true,  
//        scrollable:'x',
//        scrollable:'y',
//        listeners:{  
//            beforeitemexpand:function(node,optd){  
//            	//展开节点之前触发  
//                var id=node.data.id;  
//                var type=node.data.type; 
//                xtDpStoreSelect.setProxy({   
//                	//异步从服务器上加载数据  extjs会自动帮我们解析  
//                    type:'ajax',  
//                    url:'../xtCommonController/getXtOrgTree',  
//                    extraParams:{id:id,type:encodeURI(type)}
//                 });  
//            },
//            itemclick:function(node,optd){
//            	var leaf = optd.data.leaf;
//            	menuClick(optd,xt_menuinfo_id);
//            }  
//        }
//    });  
//}
////菜单点击事件
//function menuClick(node,xt_menuinfo_id){
// 	var id = node.data.id;
// 	var type = node.data.type;
// 	var parm = {id:id,type:encodeURI(type)};
// 	load(xtUserinfoSelectGrid,parm);
//}

/**按人员设置**/
function addXtDataAuthorityByUser(xt_menuinfo_id,xt_userinfo_id){
	var id;//最新的元素要提交到后台处理的数据
	var delID;//已被删除的元素
	var newID = new Array();//校验使用数据
	var checkedNodes = xtUserinfoSelectGrid.getChecked();
	//if(checkedNodes.length == 0){
		//msgTishi('请选择后在保存');
		//return;
	//}
	for(var i=0;i<checkedNodes.length;i++){
		if(id == null || id == ''){
			id = checkedNodes[i].id;
		}else{
			id = id+','+checkedNodes[i].id;
		}
		newID.push(checkedNodes[i].id);
	} 
	//oldID{1,2,3,4,5,6,7}
	//newID{2,3,5,6,7,8,9}
	//处理被按条件筛选出来原有的选择数据与后来操作的是数据 计算出来被删除的数据用于后台删除使用 条件是原有的选择数据存在 才能进行校验删除 否则无意义
	if(null != oldID && "" != oldID){
		var oldIDList = oldID.split(",");
		for(var i = 0; i < oldIDList.length; i++){
			if(!contains(newID, oldIDList[i])){
				if(delID == null || delID == ''){
					delID = oldIDList[i];
				}else{
					delID = oldIDList+','+oldIDList[i];
				}
			}
		}
	}
	top.Ext.Msg.confirm('提示','确定保存该数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_userinfo_id:xt_userinfo_id,id:id,xt_menuinfo_id:xt_menuinfo_id,delID:delID};
			ajaxRequest('../xtDataAuthorityController/addXtDataAuthorityByUser',xtUserinfoSelectGrid,params,'正在执行保存操作中！请稍后...');
		}
	});
}
/**处理包含元素校验**/
function contains(arr, obj){
    var i = arr.length;
    while(i--){
        if(arr[i] === obj){
            return true;
        }
    }
    return false;
}
