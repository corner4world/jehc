//设置岗位
var xtPostSelectWin;
var xtPostSelectGrid;
var xtPostSelectStore;
function updateXtPost(xt_post_name,xt_post_id,xt_menuinfo_id,xt_menuinfo_title){
	reGetWidthAndHeight();
	initPostGridSelect(xt_menuinfo_id,xt_menuinfo_title);
	xtPostSelectWin = Ext.create('top.Ext.Window',{
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
		items:xtPostSelectGrid
	});
	xtPostSelectWin.setTitle("数据权限--->按岗位设置数据权限--->"+xt_menuinfo_title+"["+xt_post_name+"]--->选择被设置岗位");
	xtPostSelectWin.show();
}

function initPostGridSelect(xt_menuinfo_id,xt_menuinfo_title){
	postSelectStore = Ext.create('Ext.data.TreeStore',{
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
			url:'../xtDataAuthorityController/getXtPostAndFunctionInfoAndDataAuthorityTreeGrid',
			reader:{
				type:'json'
			},
			extraParams:{xt_post_id:'0',type:encodeURI('岗位'),xt_menuinfo_id:xt_menuinfo_id}
        }
    });
	xtPostSelectGrid = Ext.create('top.Ext.tree.Panel', {
        reserveScrollbar:true,
        id:'xtPostSelectGrid',
        region:'center',
        collapsible:false,
        loadMask:true,
        useArrows:false,
        rootVisible:false,
        store:postSelectStore,
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
            text:'部门',
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
	xtPostSelectGrid.on('checkchange',function(node,checked){  
	    node.expand();  
	    node.checked = checked;  
	    node.eachChild(function(child){  
	        child.set('checked',checked);  
	        child.fireEvent('checkchange',child,checked);  
	    });  
	}, xtPostSelectGrid);
}