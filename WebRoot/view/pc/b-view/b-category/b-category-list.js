Ext.require([
   	'Ext.window.Window',
    'Ext.tab.*',
    'Ext.toolbar.Spacer',
    'Ext.layout.container.Card',
    'Ext.layout.container.Border',
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);
var store;
var grid;
Ext.onReady(function(){
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
			url:'../bCategoryController/getBCategoryListByCondition',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',type:encodeURI('品类分类'),expanded:true}
        }
    });
	grid = Ext.create('Ext.tree.Panel', {
    	region:'center',
    	xtype:'filtered-tree',
        reserveScrollbar:true,
        collapsible:false,
        loadMask:true,
        useArrows:true,
        rootVisible:false,
        store:store,
        autoSctroll:true,
        id:'treeGrid',
        animate:false,
        columnLines:true,
        frame:true,
        title:'品类列表',
        bufferedRenderer:false,
        viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		bbar:['->',
		{
				width:260,
			  	xtype:'triggerfield',
			  	emptyText:'请输入关键字',
			       triggerCls:'x-form-clear-trigger',
			       onTriggerClick:function(){
			           this.reset();
			       },
			       listeners:{
			           change:function(){
			           	filterBy(grid,this.getValue(),'text');
			           },
			           buffer:250
			       }
		},{
            text:'添 加',
            xtype:'button',
			minWidth:tbarBtnMinWidth,
			cls:'addBtn',
			icon:addIcon,
            handler:function(){addBCategory(0);}
       	},
       	{
           text:'编 辑',
		   tooltip:'编 辑',
		   xtype:'button',
		   minWidth:tbarBtnMinWidth,
		   cls:'updateBtn',
		   icon:editIcon,
           handler:function(){updateBCategory();}
       	},
		/*{
			text:'检索',
			tooltip:'检索',
			xtype:'button',
			minWidth:tbarBtnMinWidth,
			cls:'searchBtn',
			icon:searchIcon,
			handler:function(){
				grid.getStore().reload();
			}
		},
		{
			text:'重置',
			tooltip:'重置',
			minWidth:tbarBtnMinWidth,
			xtype:'button',
			icon:clearSearchIcon,
			handler:function(){
				searchForm.reset();
			}
		},*/
       	{
           text:'刷 新',
		   tooltip:'刷 新',
		   xtype:'button',
		   minWidth:tbarBtnMinWidth,
		   cls:'refreshBtn',
		   icon:refreshIcon,
           handler:function(){grid.getStore().reload();}
       	}],
        columns:[{
        	text:'ID',
            hideable:false,
            hidden:true,
            sortable:true,
            dataIndex:'id'
        },{
            xtype:'treecolumn',
            text:'名称',
            width:400,
            sortable:true,
            dataIndex:'text'
        },{
            text:'状态',
            width:100,
            dataIndex:'tempObject',
            sortable:true,
            renderer:function(value){
            	var val = value.split(",");
            	if(val[0] == 0){
            		return "<font color='red'>可用</font>";
            	}else if(val[0] == 1){
            		return "禁用";
            	}
            }
        },{
            text:'备注',
            hidden:true,
            dataIndex:'content',
            renderer:function(value){
            	return value;
            }
        },/*{
            text:'创建人',
            sortable:true,
            width:180,
            dataIndex:'integerappend'
        },*/{
			header:'操作',
			align:'center',
			xtype:'widgetcolumn',
			width:180,
			widget:{
                xtype:'button',
                text:'添加下级分类',
                icon:addIcon,
                handler:function(rec){	
  					var id = rec.getWidgetRecord().data.id;
  					addBCategory(id);
			    }
            }
		}]
    });
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	/**调用右键**/
	initRight();
	showWaitMsg("正在加载数据...");
	new Ext.util.DelayedTask(function(){  
       grid.expandAll();
       hideWaitMsg();
    }).delay(1000);
});
/**复制一行并生成记录**/
function copyBCategory(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_category_id:record.items[0].data.b_category_id};
			ajaxRequest('../bCategoryController/copyBCategory',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBCategory(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBCategoryItem',
			handler:function(){addBCategory();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBCategoryItem',
			handler:function(){updateBCategory();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBCategoryItem',
			handler:function(){copyBCategory();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBCategoryItem',
			handler:function(){detailBCategory();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBCategory(grid,'../bCategoryController/exportBCategory');
			}
		},{
			text:'打 印',
			glyph:0xf02f,
			handler:function(){printerGrid(grid);}
		},'-',{
			text:'全 选',
			glyph:0xf046,
			handler:function(){selectAll(grid);}
		},{
			text:'反 选',
			glyph:0xf096,
			handler:function(){unSelectAll(grid);}
		},'-',{
			text:'刷 新',
			glyph:0xf021,
			handler:function(){refreshGrid(grid);}
		}]
	});
	initrightgridcontextmenu(grid,contextmenu,['updateBCategoryItem','copyBCategoryItem','detailBCategoryItem']);
}
