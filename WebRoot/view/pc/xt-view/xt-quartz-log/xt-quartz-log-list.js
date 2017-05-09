var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../xtQuartzLogController/getXtQuartzLogListByCondition',[]);
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
				header:'名称',
				dataIndex:'xt_quartz_log_name'
			},
			{
				header:'调度内容',
				flex:1,
				dataIndex:'xt_quartz_log_content'
			},
			{
				header:'开始时间',
				flex:1,
				dataIndex:'xt_quartz_log_ctime'
			},
			{
				header:'结束时间',
				flex:1,
				dataIndex:'xt_quartz_log_etime'
			},
			{
				header:'运行标识',
				dataIndex:'xt_quartz_log_flag'
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
					delXtQuartzLog();
				}
			 },
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 /**
				 {
					text:'复制一行并生成记录',
					tooltip:'复制一行并生成记录',
					scope:this,
					glyph:0xf0ea,
					handler:function(){
						copyXtQuartzLog();
					}
				 },
				 **/
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtQuartzLog();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtQuartzLog(grid,'../xtQuartzLogController/exportXtQuartzLog');
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
				detailXtQuartzLog();
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
function delXtQuartzLog(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_quartz_log_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_quartz_log_id){
			xt_quartz_log_id=model.selected.items[i].data.xt_quartz_log_id;
		}else{
			xt_quartz_log_id=xt_quartz_log_id+","+model.selected.items[i].data.xt_quartz_log_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_quartz_log_id:xt_quartz_log_id};
			ajaxRequest('../xtQuartzLogController/delXtQuartzLog',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtQuartzLog(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_quartz_log_id:record.items[0].data.xt_quartz_log_id};
			ajaxRequest('../xtQuartzLogController/copyXtQuartzLog',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtQuartzLog(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'删 除',
			glyph:0xf014,
			id:'delXtQuartzLogItem',
			handler:function(){delXtQuartzLog();}
		},'-',{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtQuartzLogItem',
			handler:function(){detailXtQuartzLog();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtQuartzLog(grid,'../xtQuartzLogController/exportXtQuartzLog');
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
	initrightgridcontextmenu(grid,contextmenu,['delXtQuartzLogItem','detailXtQuartzLogItem']);
}

function search(){
	initSearch(store,'../xtQuartzLogController/getXtQuartzLogListByCondition',searchForm); 
}
