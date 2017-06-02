var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../xtMonitorController/getXtMonitorListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
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
				header:'用户名',
				flex:1,
				dataIndex:'xt_monitor_userName'
			},
			{
				header:'用户的账户名称',
				flex:1,
				dataIndex:'xt_monitor_accountName'
			},
			{
				header:'本地主机名',
				flex:1,
				dataIndex:'xt_monitor_localName'
			},
			{
				header:'JVM可以使用总内存',
				flex:1,
				dataIndex:'xt_monitor_jvm_totalMem'
			},
			{
				header:'JVM可以使用剩余内存',
				flex:1,
				dataIndex:'xt_monitor_jvm_Mem'
			},
			{
				header:'JVM可以使用处理器个数',
				flex:1,
				dataIndex:'xt_monitor_jvm_cup_count'
			},
			{
				header:'监控时间',
				flex:1,
				dataIndex:'xt_monitorTime'
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
					delXtMonitor();
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
					loadXtMonitorChart();
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
						detailXtMonitor();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtMonitor(grid,'../xtMonitorController/exportXtMonitor');
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
				detailXtMonitor();
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
function delXtMonitor(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_monitor_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_monitor_id){
			xt_monitor_id=model.selected.items[i].data.xt_monitor_id;
		}else{
			xt_monitor_id=xt_monitor_id+","+model.selected.items[i].data.xt_monitor_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_monitor_id:xt_monitor_id};
			ajaxRequest('../xtMonitorController/delXtMonitor',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtMonitor(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_monitor_id:record.items[0].data.xt_monitor_id};
			ajaxRequest('../xtMonitorController/copyXtMonitor',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtMonitor(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtMonitorItem',
			handler:function(){detailXtMonitor();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtMonitor(grid,'../xtMonitorController/exportXtMonitor');
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
	initrightgridcontextmenu(grid,contextmenu,['detailXtMonitorItem']);
}

function loadXtMonitorChart(){
	var xtMonitorChartWin = Ext.create('Ext.Window',{
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
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../xtMonitorController/loadXtMonitorJvmChart"></iframe>'
	});
	xtMonitorChartWin.show();
}
/**查询操作**/
function search(){
	initSearch(store,'../xtMonitorController/getXtMonitorListByCondition',searchForm); 
}
