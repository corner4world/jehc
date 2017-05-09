var departStore;
var departGrid;
var departWin;
function showDepartWin(xt_menuinfo_id,xt_menuinfo_title){
	initDepartTreeGrid(xt_menuinfo_id,xt_menuinfo_title);
	reGetWidthAndHeight();
	departWin = Ext.create('top.Ext.Window',{
		layout:'fit', 
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		maximized:true,
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
		items:departGrid
	});
	departWin.setTitle("数据权限--->按部门设置数据权限--->"+xt_menuinfo_title);
	departWin.show();
}
function initDepartTreeGrid(xt_menuinfo_id,xt_menuinfo_title){
	departStore = Ext.create('Ext.data.TreeStore',{
    	root:{
			name:'一级',
			id:'0',
			expanded:true
		},
		/**此处一定要设置否则全部展开节点无效**/
		autoLoad:true,
        proxy:{
            type:'ajax',
            method:'post',
			url:'../xtDataAuthorityController/getXtDepartTree',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',type:encodeURI('部门')}
        },
        lazyFill:true
    });
    departGrid = Ext.create('top.Ext.tree.Panel', {
        title:'部门树结构',
        reserveScrollbar:true,
        collapsible:false,
        loadMask:true,
        useArrows:false,
        rootVisible:false,
        store:departStore,
        animate:false,
        columnLines:true,
        frame:true,
        listeners:{  
            beforeitemexpand:function(node,optd){
                var id=node.data.id; 
                var type=node.data.type; 
                departStore.setProxy({   
		       	   //异步从服务器上加载数据extjs会自动帮我们解析  
		           type:'ajax',  
		           url:'../xtDataAuthorityController/getXtDepartTree',  
		           extraParams:{id:id,type:encodeURI(type)}
		        });  
            },
            /**
            beforeitemcollapse:function(node,optd){
                return false;  
            },
            **/
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            	if(leaf == true){
            		
            	}
            }
        },
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
            sortable:true
        },{
            text:'备注',
            flex:1,
            dataIndex:'content',
            renderer:function(value){
            	return value;
            }
        },{
        	header:'操 作',
        	columns:[{
				header:'设置该部门拥有其它部门的权限',
				align:'center',
				xtype:'widgetcolumn',
				width:220,
				widget:{
					xtype:'button',
					icon:editIcon,
	                text:'设置该部门拥有其它部门的权限',
	                handler:function(rec){
	                	console.log(rec.getWidgetRecord());
	                	var id = rec.getWidgetRecord().data.id;
				    }
	            }
			},{
				header:'查看该部门拥有其它部门的权限',
				align:'center',
				xtype:'widgetcolumn',
				width:220,
				widget:{
	                xtype:'button',
	                icon:detailIcon,
	                text:'查看该部门拥有其它部门的权限',
	                handler:function(rec){
	                	var id = rec.getWidgetRecord().data.id;
				    }
	            }
			}]
        }]
    });
    departGrid.on('beforeload',function(treeloader,node) { 
   		/**
   		如果node为一个参数情况下取属性值如下:
   		var id = node.data.items[0].data.id;
           var type = node.data.items[0].data.type;
   		**/
   		/**
   		如果node为第二个参数情况下取属性值如下:
   		var id = node.node.data.id;
           var type = node.node.data.type;
   		**/
        var id = node.node.data.id;
        var type = node.node.data.type;
        departStore.setProxy({   
       	   //异步从服务器上加载数据extjs会自动帮我们解析  
           type:'ajax',  
           url:'../xtDataAuthorityController/getXtDepartTree',  
           extraParams:{id:id,type:encodeURI(type)}
        });
    });
	new Ext.util.DelayedTask(function(){  
       departGrid.expandAll();
       hideWaitMsg();
    }).delay(1000);
}