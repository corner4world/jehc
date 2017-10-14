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
			url:'../xtAreaRegionController/getXtAreaRegionListByCondition',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',expanded:true}
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
        loadMask:true,
        loadMask:{
			msg:'正在加载...'
		},
        bbar:['->',
        	{
		        xtype:'triggerfield',
		        emptyText:'请输入关键字（如省、市、区县等）',
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
				text:'编 辑',
				tooltip:'编 辑',
				xtype:'button',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtAreaRegion();
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
            text:'行政编码、行政级别',
            flex:1,
            dataIndex:'tempObject',
            sortable:true
        },{
            text:'经度、纬度',
            dataIndex:'integerappend',
            sortable:true,
            flex:1
        },{
            text:'拼音',
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
/**删除操作**/
function delXtAreaRegion(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var ID;
	for(var i = 0; i < model.selected.length; i++){
		if(null == ID){
			ID=model.selected.items[i].data.ID;
		}else{
			ID=ID+","+model.selected.items[i].data.ID;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {ID:ID};
			ajaxRequest('../xtAreaRegionController/delXtAreaRegion',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
function refresh(){
	showWaitMsg("正在加载数据...");
	store.on('load',function(){
    	hideWaitMsg();
    });
}
