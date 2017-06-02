var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		maxHeight:150,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'模块',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_operate_business_logsModules',
			name:'xt_operate_business_logsModules',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'执行方法',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_operate_business_logsMethod',
			name:'xt_operate_business_logsMethod',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'方法参数',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_operate_business_logsMethodPar',
			name:'xt_operate_business_logsMethodPar',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'执行结果',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_operate_business_logsResult',
			name:'xt_operate_business_logsResult',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'操作时间',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_operate_business_logsTime',
			name:'xt_operate_business_logsTime',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'操作人',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_userinfo_realName',
			name:'xt_userinfo_realName',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtOperateBusinessLogsController/getXtOperateBusinessLogsListByCondition',[]);
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
				header:'模块',
				flex:1,
				dataIndex:'xt_operate_business_logsModules'
			},
			{
				header:'执行方法',
				flex:1,
				dataIndex:'xt_operate_business_logsMethod'
			},
			{
				header:'方法参数',
				flex:1,
				dataIndex:'xt_operate_business_logsMethodPar'
			},
			{
				header:'执行结果',
				flex:1,
				dataIndex:'xt_operate_business_logsResult'
			},
			{
				header:'操作时间',
				flex:1,
				dataIndex:'xt_operate_business_logsTime'
			},
			{
				header:'操作人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
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
					delXtOperateBusinessLogs();
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
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'删除',
					tooltip:'删除',
					icon:delIcon,
					handler:function(){
						delXtOperateBusinessLogs();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtOperateBusinessLogs();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtOperateBusinessLogs(grid,'../xtOperateBusinessLogsController/exportXtOperateBusinessLogs');
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
				detailXtOperateBusinessLogs();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtOperateBusinessLogs(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_operate_business_logs_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_operate_business_logs_id){
			xt_operate_business_logs_id=model.selected.items[i].data.xt_operate_business_logs_id;
		}else{
			xt_operate_business_logs_id=xt_operate_business_logs_id+","+model.selected.items[i].data.xt_operate_business_logs_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_operate_business_logs_id:xt_operate_business_logs_id};
			ajaxRequest('../xtOperateBusinessLogsController/delXtOperateBusinessLogs',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtOperateBusinessLogs(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_operate_business_logs_id:record.items[0].data.xt_operate_business_logs_id};
			ajaxRequest('../xtOperateBusinessLogsController/copyXtOperateBusinessLogs',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtOperateBusinessLogs(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'删 除',
			glyph:0xf014,
			id:'delXtOperateBusinessLogsItem',
			handler:function(){delXtOperateBusinessLogs();}
		},'-',{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtOperateBusinessLogsItem',
			handler:function(){detailXtOperateBusinessLogs();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtOperateBusinessLogs(grid,'../xtOperateBusinessLogsController/exportXtOperateBusinessLogs');
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
	initrightgridcontextmenu(grid,contextmenu,['detailXtOperateBusinessLogsItem','delXtOperateBusinessLogsItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtOperateBusinessLogsController/getXtOperateBusinessLogsListByCondition',searchForm); 
}
