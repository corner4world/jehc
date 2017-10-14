var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../xtStartStopLogController/getXtStartStopLogListByCondition',[]);
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
				header:'启动时间',
				width:150,
				dataIndex:'xt_start_stop_log_starttime'
			},
			{
				header:'停止时间',
				width:150,
				dataIndex:'xt_start_stop_log_stoptime'
			},
			{
				header:'状态',
				dataIndex:'xt_start_stop_log_iserror',
				renderer:function(value){
					if(value == 0){
						return "正常"
					}else if(value == 1){
						return "出错";
					}
				}
			},
			{
				header:'加载内容',
				flex:1,
				dataIndex:'xt_start_stop_log_content'
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
					delXtStartStopLog();
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
						detailXtStartStopLog();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtStartStopLog(grid,'../xtStartStopLogController/exportXtStartStopLog');
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
						search()
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailXtStartStopLog();
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
function delXtStartStopLog(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_start_stop_log_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_start_stop_log_id){
			xt_start_stop_log_id=model.selected.items[i].data.xt_start_stop_log_id;
		}else{
			xt_start_stop_log_id=xt_start_stop_log_id+","+model.selected.items[i].data.xt_start_stop_log_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_start_stop_log_id:xt_start_stop_log_id};
			ajaxRequest('../xtStartStopLogController/delXtStartStopLog',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtStartStopLog(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_start_stop_log_id:record.items[0].data.xt_start_stop_log_id};
			ajaxRequest('../xtStartStopLogController/copyXtStartStopLog',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtStartStopLog(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[/**{
			text:'添 加',
			glyph:0xf016,
			handler:function(){addXtStartStopLog();}
		},{
			text:'编 辑',
			glyph:0xf044,
			handler:function(){updateXtStartStopLog();}
		},**/{
			text:'删 除',
			glyph:0xf014,
			id:'delXtStartStopLogItem',
			handler:function(){delXtStartStopLog();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtStartStopLogItem',
			handler:function(){detailXtStartStopLog();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtStartStopLog(grid,'../xtStartStopLogController/exportXtStartStopLog');
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
	initrightgridcontextmenu(grid,contextmenu,['detailXtStartStopLogItem','delXtStartStopLogItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtStartStopLogController/getXtStartStopLogListByCondition',searchForm); 
}