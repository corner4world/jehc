//设置部门
var xtDepartSelectWin;
var xtDepartSelectGrid;
var xtDepartSelectStore;
function updateXtDepart(xt_departinfo_name,xt_departinfo_id,xt_menuinfo_id,xt_menuinfo_title){
	reGetWidthAndHeight();
	initDepartGridSelect(xt_menuinfo_id,xt_menuinfo_title,xt_departinfo_id);
	xtDepartSelectWin = Ext.create('top.Ext.Window',{
		layout:'border', 
		width:clientWidth*0.8,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		closeAction:'close', 
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtDepartSelectGrid
	});
	xtDepartSelectWin.setTitle("数据权限--->按部门设置数据权限--->"+xt_menuinfo_title+"["+xt_departinfo_name+"]--->选择被设置部门");
	xtDepartSelectWin.show();
}

function initDepartGridSelect(xt_menuinfo_id,xt_menuinfo_title,xt_departinfo_id){
	xtDepartSelectStore = Ext.create('Ext.data.TreeStore',{
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
			url:'../xtDataAuthorityController/getXtDepartAndFunctionInfoAndDataAuthorityTreeGrid',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',xt_departinfo_id:xt_departinfo_id,type:encodeURI('部门'),xt_menuinfo_id:xt_menuinfo_id}
        }
    });
	xtDepartSelectGrid = Ext.create('top.Ext.tree.Panel', {
        reserveScrollbar:true,
        id:'xtDepartinfoSelectGrid',
        region:'center',
        collapsible:false,
        loadMask:true,
        useArrows:false,
        rootVisible:false,
        store:xtDepartSelectStore,
        animate:false,
        columnLines:true,
        frame:true,
        viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		columns:[{
        	text:'ID',
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
					var checkedNodes = xtDepartSelectGrid.getChecked();
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
					addXtDataAuthorityByDepart(xt_menuinfo_id,xt_departinfo_id);
				}
			 }]
    });
	/**选择父节点选中子节点**/
	xtDepartSelectGrid.on('checkchange',function(node,checked){  
	    node.expand();  
	    node.checked = checked;  
	    node.eachChild(function(child){  
	        child.set('checked',checked);  
	        child.fireEvent('checkchange',child,checked);  
	    });  
	}, xtDepartSelectGrid);
}

/**按部门设置**/
function addXtDataAuthorityByDepart(xt_menuinfo_id,xt_departinfo_id){
	var id;//最新的元素要提交到后台处理的数据
	var delID;//已被删除的元素
	var newID = new Array();//校验使用数据
	var checkedNodes = xtDepartSelectGrid.getChecked();
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
			var params = {xt_departinfo_id:xt_departinfo_id,id:id,xt_menuinfo_id:xt_menuinfo_id,delID:delID};
			ajaxRequest('../xtDataAuthorityController/addXtDataAuthorityByDepart',xtDepartSelectGrid,params,'正在执行保存操作中！请稍后...');
		}
	});
}
