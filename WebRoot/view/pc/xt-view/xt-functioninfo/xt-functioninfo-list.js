var grid;
var store;
Ext.onReady(function(){	
	initGrid();
	Ext.create('Ext.Viewport', {
		layout:'fit',
		xtype:'viewport',
		items:[grid]
	});
});
/**
*初始化资源模块
**/
function initGrid(){
	store = Ext.create('Ext.data.TreeStore',{
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
			url:'../xtFunctioninfoController/getXtFunctioninfoList',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',type:encodeURI('菜单'),expanded:true}
        }
    });
    grid = Ext.create('Ext.tree.Panel', {
        reserveScrollbar:true,
        region:'center',
        loadMask:true,
        useArrows:true,
        rootVisible:false,
        store:store,
        autoSctroll:true,
        id:'treeGrid',
        animate:false,
        columnLines:true,
        frame:true,
        bufferedRenderer:false,
        bbar:['->',
        	{
		        xtype:'triggerfield',
		        emptyText:'请输入关键字（如菜单、平台等）',
		        triggerCls:'x-form-clear-trigger',
		        width:260,
		        onTriggerClick:function(){
		            this.reset();
		        },
		        listeners:{
		            change:function(){
		                filterBy(grid,this.getValue(),'text');
		            },
		            buffer:250
			    }
			 },
			 {
				text:'添 加',
				tooltip:'添 加',
				xtype:'button',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addXtFunctioninfo();
				}
			 },
			 {
				text:'编 辑',
				tooltip:'编 辑',
				xtype:'button',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtFunctioninfo();
				}
			 },
			 {
				text:'删 除',
				tooltip:'删 除',
				xtype:'button',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtFunctioninfo();
				}
			 },
			 {
				text:'刷 新',
				tooltip:'刷 新',
				xtype:'button',
				minWidth:tbarBtnMinWidth,
				cls:'refreshBtn',
				icon:refreshIcon,
				handler:function(){
					refresh();
				}
			 }
		],
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
            	return value;
            }
        },{
            text:'数据权限',
            dataIndex:'integerappend',
            sortable:true,
            renderer:function(value){
            	var val = value.split(",");
            	if(val[0] == 0){
            		return "<font color='red'>是</font>";
            	}else if(val[0] == 1){
            		return "否";
            	}
            }
        },{
            text:'拦截类型',
            dataIndex:'integerappend',
            sortable:true,
            flex:1,
            renderer:function(value){
            	var val = value.split(",");
            	if(val[1] == 0){
            		return "无需拦截---初始化该功能无需拦截"
            	}else if(val[1] == 1){
            		return "<font color='red'>必须拦截---初始化该功能必须拦截</font>";
            	}
            }
        },{
            text:'备注',
            flex:1,
            dataIndex:'content',
            renderer:function(value){
            	return value;
            }
        }]
    });
    refresh();
    /**选择父节点选中子节点**/
    /**
    grid.on('checkchange',function(node,checked){  
	    node.expand();  
	    node.checked = checked;  
	    node.eachChild(function(child){  
	        child.set('checked',checked);  
	        child.fireEvent('checkchange',child,checked);  
	    });  
	}, grid);
	**/ 
}

function delXtFunctioninfo(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	if(model.selected.items[0].data.tempObject == '菜单'){
		msgTishi('您选择的是菜单不能删除，请选择功能!');
		return;
	}
	var rowData = model.selected.items[0].data;
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var xt_functioninfo_id = rowData.id;
			var params = {xt_functioninfo_id:xt_functioninfo_id};
			ajaxRequest('../xtFunctioninfoController/delXtFunctioninfo',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}

function refresh(){
	showWaitMsg("正在加载数据...");
	/**
	new Ext.util.DelayedTask(function(){  
       
    }).delay(1000);
    **/
	store.on('load',function(){
		grid.expandAll();
	    hideWaitMsg();
    });
}