//设置部门
var xtDepartSelectWin;
var xtDepartSelectGrid;
var xtDepartSelectStore;
function updateXtDepart(xt_departinfo_name,xt_departinfo_id,xt_menuinfo_id,xt_menuinfo_title){
	reGetWidthAndHeight();
	initDepartGridSelect(xt_menuinfo_id,xt_menuinfo_title);
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

function initDepartGridSelect(xt_menuinfo_id,xt_menuinfo_title){
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
			extraParams:{xt_post_id:'0',type:encodeURI('部门'),xt_menuinfo_id:xt_menuinfo_id}
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
		tbar:[{
				text:'保 存',
				tooltip:'保 存',
				icon:saveIcon,
				handler:function(){
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