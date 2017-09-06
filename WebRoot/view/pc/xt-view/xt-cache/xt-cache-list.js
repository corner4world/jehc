var store;
var grid;
var expander;
Ext.onReady(function(){
	store = getGridJsonStore('../xtCacheController/getXtCacheListByCondition',[]);
	expander = new Ext.ux.RowExpander({
		rowBodyTpl:new Ext.XTemplate(
	    	'<div width="100%" style="margin:0 auto;border:0px solid #5fa2dd;width:602px" id="cache_data{CacheType}{CacheName}"></div>'
	    )
		/**,
       	toggleRow:function(row){  
       		if(typeof row == 'number'){  
               row = this.grid.view.getRow(row);  
           }  
           var record = grid.store.getAt(row.rowIndex); 
        } **/ 
	});
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:false,
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		selType:'checkboxmodel',
		plugins:expander,
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true,
			enableTextSelection:true//可以复制单元格文字
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'序号',
				width:77,
				xtype:'rownumberer'
			},
			{
				header:'缓存名称',
				sortable:false,
				menuDisabled:true,
				dataIndex:'CacheName'
			},
			{
				header:'数量',
				sortable:false,
				menuDisabled:true,
				dataIndex:'CacheSize'
			},
			{
				header:'占用内存大小',
				sortable:false,
				menuDisabled:true,
				dataIndex:'MemoryStoreSize'
			},
			{
				header:'读取命中次数',
				sortable:false,
				menuDisabled:true,
				dataIndex:'Hits'
			},
			{
				header:'读取错失次数',
				sortable:false,
				menuDisabled:true,
				dataIndex:'Misses'
			},
			{
				header:'硬盘存储大小',
				sortable:false,
				menuDisabled:true,
				dataIndex:'DiskStoreSize'
			},
			{
				header:'类型',
				sortable:false,
				menuDisabled:true,
				flex:true,
				dataIndex:'CacheType'
			},
			{
				header:'操作',
				sortable:false,
				menuDisabled:true,
				columns:[
				{
					header:'清空所有数据',
					align:'center',
					sortable:false,
					menuDisabled:true,
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'清空所有数据',
		                handler:function(rec){	
		                	 Ext.MessageBox.confirm('提示', '确定清空该缓存下所有数据吗？', function(btn) {  
						       if(btn == 'yes'){ 
								var CacheName = rec.getWidgetRecord().data.CacheName;
						    	var params = {cacheName:CacheName};
								ajaxRequest('../xtCacheController/delCache',grid,params,'正在执行清空操作中！请稍后...');
						       }  
						    }) 
					    }
		            }
				}]
			}
		],
		tbar:[
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportCache(grid,'../xtCacheController/exportCache');
					}
				 },
				 '-',
				 {
					text:'全 选',
					tooltip:'全 选',
					glyph:0xf046,
					handler:function(){selectAll(grid);}
				 },
				 {
					text:'反 选',
					tooltip:'反 选',
					glyph:0xf096,
					handler:function(){unSelectAll(grid);}
				 },
				 '-',
				 {
					text:'刷 新',
					tooltip:'刷 新',
					glyph:0xf021,
					handler:function(){
						refreshGrid(grid);
					}
				 }
				 ]
			 }
		],
		listeners:{
			'afterrender':function(grid){
				grid.view.on('expandBody', function (rowNode, record, expandRow, eOpts){
					initCacheData(record.data.CacheType,record.data.CacheName); 
                });
                grid.view.on('collapsebody', function (rowNode, record, expandRow, eOpts){
                	$("#cache_data"+record.data.CacheType+record.data.CacheName).empty();
                });
            },
			'rowdblclick':function(grid, rowIndex, e){}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
	/**
	grid.on("headerclick", function(ct,column,e,t,opts) {
         expendRows();
    });
    **/
	/**调用右键**/
	initRight();
});
/**导出**/
function exportCache(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[
		{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportCache(grid,'..//');
			}
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
	initrightgridcontextmenu(grid,contextmenu,[]);
	/**
	store.on('load',function(thiz, options){
		expendRows();
    });
    **/
}
/**
//展开符合某个条件的行 
function expendRows(){ 
	for(var i=0;i<store.data.length;i++){ 
		var record = store.getAt(i);//循环遍历每一行 
		initCacheData(record.data.CacheType,record.data.CacheName);
		expander.toggleRow(i,record);
	} 
}
**/
var cache_data_store;
var cache_data_grid;
function initCacheData(CacheType,CacheName){
	cache_data_store = getGridJsonStore('../xtCacheController/getXtCacheDataListByCondition?cacheName='+CacheName,[]);
	cache_data_grid = Ext.create('Ext.grid.Panel', {
		renderTo:'cache_data'+CacheType+CacheName,
        collapsible:false,
        store:cache_data_store,
        autoSctroll:true,
        animate:false,
        columnLines:true,
        frame:true,
        width:600,
        height:150,
        plugins:{
			ptype:'cellediting',
        	clicksToEdit:1
		},
        title:'数据列表',
        headerPosition:'left',
        viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
        columns:[{
            text:'键',
            dataIndex:'key'
        },
        {
            text:'值',
            flex:1,
            dataIndex:'value',
			editor:{
                xtype:'textareafield',
                height:125
            }
        }]
    });
}
