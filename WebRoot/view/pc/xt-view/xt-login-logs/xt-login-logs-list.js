var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../xtLoginLogsController/getXtLoginLogsListByCondition',[]);
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
				header:'操作人',
				locked:true,
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'网络地址',
				locked:true,
				dataIndex:'xt_login_logIP'
			},
			{
				header:'登录时间',
				width:150,
				dataIndex:'xt_login_logtime'
			},
			{
				header:'浏览器类型',
				width:150,
				dataIndex:'xt_login_log_browser_type'
			},
			{
				header:'浏览器名称',
				width:150,
				dataIndex:'xt_login_log_browser_name'
			},
			{
				header:'浏览器版本',
				width:150,
				dataIndex:'xt_login_log_browser_version'
			},
			{
				header:'操作系统',
				width:150,
				dataIndex:'xt_login_log_system'
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
					delXtLoginLogs();
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
						detailXtLoginLogs();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtLoginLogs(grid,'../xtLoginLogsController/exportXtLoginLogs');
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
				detailXtLoginLogs();
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
function delXtLoginLogs(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_login_log_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_login_log_id){
			xt_login_log_id=model.selected.items[i].data.xt_login_log_id;
		}else{
			xt_login_log_id=xt_login_log_id+","+model.selected.items[i].data.xt_login_log_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_login_log_id:xt_login_log_id};
			ajaxRequest('../xtLoginLogsController/delXtLoginLogs',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtLoginLogs(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_login_log_id:record.items[0].data.xt_login_log_id};
			ajaxRequest('../xtLoginLogsController/copyXtLoginLogs',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtLoginLogs(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[/**{
			text:'添 加',
			glyph:0xf016,
			handler:function(){addXtLoginLogs();}
		},{
			text:'编 辑',
			glyph:0xf044,
			handler:function(){updateXtLoginLogs();}
		},**/{
			text:'删 除',
			glyph:0xf014,
			id:'delXtLoginLogsItem',
			handler:function(){delXtLoginLogs();}
		},'-',/**{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			handler:function(){copyXtLoginLogs();}
		},**/{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtLoginLogsItem',
			handler:function(){detailXtLoginLogs();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtLoginLogs(grid,'../xtLoginLogsController/exportXtLoginLogs');
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
	initrightgridcontextmenu(grid,contextmenu,['detailXtLoginLogsItem','delXtLoginLogsItem']);
}

/**查询操作**/
function search(){
	initSearch(store,'../xtLoginLogsController/getXtLoginLogsListByCondition',searchForm); 
}
