var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../xtMonitorMemController/getXtMonitorMemListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		selType:'checkboxmodel',
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
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
				header:'总内存',
				flex:1,
				dataIndex:'xt_monitor_memTotal'
			},
			{
				header:'当前内存使用量',
				flex:1,
				dataIndex:'xt_monitor_memCurrUse'
			},
			{
				header:'当前内存剩余量',
				flex:1,
				dataIndex:'xt_monitor_memCurrSy'
			},
			{
				header:'交换区总量',
				flex:1,
				dataIndex:'xt_monitor_memJhTotal'
			},
			{
				header:'当前交换区使用量',
				flex:1,
				dataIndex:'xt_monitor_memJhCurrUse'
			},
			{
				header:'当前交换区剩余量',
				flex:1,
				dataIndex:'xt_monitor_memJhSy'
			},
			{
				header:'取读时间',
				flex:1,
				dataIndex:'xt_monitor_memTime',
				renderer:function(value){
					return dateformat(value); 
				}
			}
		],
		tbar:[
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtMonitorMem();
				}
			 },
			 {
				text:'监控',
				tooltip:'监控',
				minWidth:tbarBtnMinWidth,
				cls:'jkBtn',
				icon:taskIcon,
				handler:function(){
					loadXtMonitorMemChart();
				}
			 },
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtMonitorMem();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtMonitorMem(grid,'../xtMonitorMemController/exportXtMonitorMem');
					}
				 },
				 {
					text:'打 印',
					tooltip:'打 印',
					glyph:0xf02f,
					handler:function(){
						printerGrid(grid);
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
						grid.getStore().reload();
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailXtMonitorMem();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtMonitorMem(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_monitor_mem_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_monitor_mem_id){
			xt_monitor_mem_id=model.selected.items[i].data.xt_monitor_mem_id;
		}else{
			xt_monitor_mem_id=xt_monitor_mem_id+","+model.selected.items[i].data.xt_monitor_mem_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_monitor_mem_id:xt_monitor_mem_id};
			ajaxRequest('../xtMonitorMemController/delXtMonitorMem',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtMonitorMem(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_monitor_mem_id:record.items[0].data.xt_monitor_mem_id};
			ajaxRequest('../xtMonitorMemController/copyXtMonitorMem',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtMonitorMem(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'删 除',
			glyph:0xf014,
			id:'delXtMonitorMemItem',
			handler:function(){delXtMonitorMem();}
		},'-',{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtMonitorMemItem',
			handler:function(){detailXtMonitorMem();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtMonitorMem(grid,'../xtMonitorMemController/exportXtMonitorMem');
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
	initrightgridcontextmenu(grid,contextmenu,['detailXtMonitorMemItem','delXtMonitorMemItem']);
}

function loadXtMonitorMemChart(){
	var xtMonitorMemChartWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,                    
		height:400, 
		maximized:true,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'监控',
		headerPosition:'left',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../xtMonitorMemController/loadXtMonitorMemChart"></iframe>'
	});
	xtMonitorMemChartWin.show();
}

/**查询操作**/
function search(){
	initSearch(store,'../xtMonitorMemController/getXtMonitorMemListByCondition',searchForm); 
}
