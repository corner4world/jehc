var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../xtMonitorCpuController/getXtMonitorCpuListByCondition',[]);
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
				header:'生产商',
				flex:1,
				dataIndex:'xt_monitor_cpu_producer'
			},
			{
				header:'用户使用率',
				flex:1,
				dataIndex:'xt_monitor_cpu_user_use_rate'
			},
			{
				header:'系统使用率',
				flex:1,
				dataIndex:'xt_monitor_cpu_sys_use_rate'
			},
			{
				header:'当前等待率',
				flex:1,
				dataIndex:'xt_monitor_cpu_wait_use_rate'
			},
			{
				header:'当前错误率',
				flex:1,
				dataIndex:'xt_monitor_cpu_error_use_rate'
			},
			{
				header:'当前空闲率',
				flex:1,
				dataIndex:'xt_monitor_cpu_currently_idle'
			},
			{
				header:'总使用率',
				flex:1,
				dataIndex:'xt_monitor_cpu_use_rate'
			},
			{
				header:'取读时间',
				flex:1,
				dataIndex:'xt_monitor_cpuTime'
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
					delXtMonitorCpu();
				}
			 },
			 {
				text:'检索',
				tooltip:'检索',
				minWidth:tbarBtnMinWidth,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					search();
				}
			 },
			 {
				text:'重置',
				tooltip:'重置',
				minWidth:tbarBtnMinWidth,
				icon:clearSearchIcon,
				handler:function(){
					searchForm.reset();
				}
			 },
			 {
				text:'监控',
				tooltip:'监控',
				minWidth:tbarBtnMinWidth,
				cls:'jkBtn',
				icon:taskIcon,
				handler:function(){
					loadXtMonitorCpuChart();
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
						detailXtMonitorCpu();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtMonitorCpu(grid,'../xtMonitorCpuController/exportXtMonitorCpu');
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
				 },
				 {
					text:'检 索',
					tooltip:'检 索',
					glyph:0xf002,
					handler:function(){
						search();
					}
				 },
				 {
					text:'重 置',
					tooltip:'重 置',
					glyph:0xf014,
					handler:function(){
						searchForm.reset();
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailXtMonitorCpu();
			}
		}
	});
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtMonitorCpu(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_monitor_cpu_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_monitor_cpu_id){
			xt_monitor_cpu_id=model.selected.items[i].data.xt_monitor_cpu_id;
		}else{
			xt_monitor_cpu_id=xt_monitor_cpu_id+","+model.selected.items[i].data.xt_monitor_cpu_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_monitor_cpu_id:xt_monitor_cpu_id};
			ajaxRequest('../xtMonitorCpuController/delXtMonitorCpu',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtMonitorCpu(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_monitor_cpu_id:record.items[0].data.xt_monitor_cpu_id};
			ajaxRequest('../xtMonitorCpuController/copyXtMonitorCpu',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtMonitorCpu(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'删 除',
			glyph:0xf014,
			id:'delXtMonitorCpuItem',
			handler:function(){delXtMonitorCpu();}
		},'-'/**,{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			handler:function(){copyXtMonitorCpu();}
		}**/,{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtMonitorCpuItem',
			handler:function(){detailXtMonitorCpu();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtMonitorCpu(grid,'../xtMonitorCpuController/exportXtMonitorCpu');
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
	initrightgridcontextmenu(grid,contextmenu,['detailXtMonitorCpuItem','delXtMonitorCpuItem']);
}

function loadXtMonitorCpuChart(){
	var xtMonitorCpuChartWin = Ext.create('Ext.Window',{
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
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../xtMonitorCpuController/loadXtMonitorCpuChart"></iframe>'
	});
	xtMonitorCpuChartWin.show();
}

/**查询操作**/
function search(){
	initSearch(store,'../xtMonitorCpuController/getXtMonitorCpuListByCondition',searchForm); 
}
