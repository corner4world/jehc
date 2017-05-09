var xtMenuinfoWin;
var xtMenuinfoGrid;
var xtMenuinfoStore;
function addXtMenuinfo(xt_role_id,xt_role_name){
	initXtMenuinfoTreeGrid(xt_role_id);
	reGetWidthAndHeight();
	xtMenuinfoWin = Ext.create('top.Ext.Window',{
		layout:'fit',
		width:clientWidth*0.8,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'导入资源',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtMenuinfoGrid,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				addXtMR(xt_role_id);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtMenuinfoWin.setTitle("角色权限--->导入资源--->"+xt_role_name);
	xtMenuinfoWin.show();
}

/**
*初始化资源模块
**/
function initXtMenuinfoTreeGrid(xt_role_id){
	xtMenuinfoStore = Ext.create('Ext.data.TreeStore',{
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
			url:'../xtRoleinfoController/getXtMenuinfoListAll',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',type:encodeURI('菜单'),expanded:true,xt_role_id:xt_role_id}
        }
    });
    xtMenuinfoGrid = Ext.create('top.Ext.tree.Panel', {
        reserveScrollbar:true,
        collapsible:false,
        loadMask:true,
        useArrows:false,
        rootVisible:false,
        store:xtMenuinfoStore,
        autoSctroll:true,
        id:'treeGrid',
        animate:false,
        columnLines:true,
        frame:true,
        bufferedRenderer:false,
        /**
        listeners:{  
            beforeitemcollapse:function(node,optd){
                return false;  
            },
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            	if(leaf == true){
            	}
            }
        },
        **/
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
            	if(value == '菜单'){
            		return "菜单";
            	}else{
            		return "<font color='red'>功能</font>";
            	}
            }
        },{
            text:'备注',
            flex:1,
            dataIndex:'remark',
            renderer:function(value){
            	return value;
            }
        }]
    });
    
    /**选择父节点选中子节点**/
    xtMenuinfoGrid.on('checkchange',function(node,checked){  
	    node.expand();  
	    node.checked = checked;  
	    node.eachChild(function(child){  
	        child.set('checked',checked);  
	        child.fireEvent('checkchange',child,checked);  
	    });  
	}, xtMenuinfoGrid); 
}


function addXtMR(xt_role_id){
	var id;
	var checkedNodes = xtMenuinfoGrid.getChecked();
	for(var i=0;i<checkedNodes.length;i++){
		if(id == null || id == ''){
			id = checkedNodes[i].id;
		}else{
			id = id+','+checkedNodes[i].id;
		}
	} 
	top.Ext.Msg.confirm('提示','确定分配所选资源？',function(btn){
		if(btn == 'yes'){
			var params = {id:id,xt_role_id:xt_role_id};
			ajaxRequest('../xtRoleinfoController/addXtMR',grid,params,'正在执行中...');
		}
	});
}