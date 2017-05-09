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
		{}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../lcDeploymentHisController/getLcDeploymentHisListByCondition?lc_process_id='+$('#lc_process_id').val(),[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:true,
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
				header:'流程部署编号',
				flex:1,
				dataIndex:'lc_deployment_his_id'
			},
			{
				header:'名称',
				flex:1,
				dataIndex:'lc_deployment_his_name'
			},
			{
				header:'部署时间',
				flex:1,
				dataIndex:'lc_deployment_his_time'
			},
			{
				header:'租户编号',
				flex:1,
				dataIndex:'lc_deployment_his_tenantId'
			},
			{
				header:'操作',
				columns:[
				{
					header:'发起实例',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'发起实例',
		                handler:function(rec){	
		                	var lc_deployment_his_id = rec.getWidgetRecord().data.lc_deployment_his_id;
		                	var params = {lc_deployment_his_id:lc_deployment_his_id};
		                	Ext.Msg.confirm('提示','确定发布该流程实例？',function(btn){
								if(btn == 'yes'){
									ajaxRequest('../lcProcessController/startProcessInstance',grid,params,'正在发布该流程实例中！请稍后...');
								}
							});
					    }
		            }
				},
				{
					header:'查看流程实例',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'查看流程实例',
		                handler:function(rec){	
		  					var lc_deployment_his_id = rec.getWidgetRecord().data.lc_deployment_his_id;
		  					var lc_process_id = rec.getWidgetRecord().data.lc_process_id;
		  					document.location.href='../lcProcessController/loadLcProcessInstance?lc_deployment_his_id='+lc_deployment_his_id+'&lc_process_id='+lc_process_id;
					    }
		            }
				}]
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
					delLcDeploymentHis();
				}
			 },
			 {
				text:'检索',
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
			 /**
			 {
				text:'返回',
				tooltip:'返回',
				scope:this,
				minWidth:tbarBtnMinWidth,
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../lcProcessController/loadLcProcess";
				}
			 },
			 **/
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
						detailLcDeploymentHis();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportLcDeploymentHis(grid,'../lcDeploymentHisController/exportLcDeploymentHis');
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
				detailLcDeploymentHis();
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
});
/**删除操作**/
function delLcDeploymentHis(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == id){
			id=model.selected.items[i].data.id;
		}else{
			id=id+","+model.selected.items[i].data.id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {id:id};
			ajaxRequest('../lcDeploymentHisController/delLcDeploymentHis',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyLcDeploymentHis(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {id:record.items[0].data.id};
			ajaxRequest('../lcDeploymentHisController/copyLcDeploymentHis',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportLcDeploymentHis(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'删 除',
			glyph:0xf014,
			id:'delLcDeploymentHisItem',
			handler:function(){delLcDeploymentHis();}
		},'-',{
			text:'明 细',
			glyph:0xf03b,
			id:'detailLcDeploymentHisItem',
			handler:function(){detailLcDeploymentHis();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportLcDeploymentHis(grid,'../lcDeploymentHisController/exportLcDeploymentHis');
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
	initrightgridcontextmenu(grid,contextmenu,['detailLcDeploymentHisItem']);
}
/**查询操作**/
function search(){
	store.load({
		url:'../lcDeploymentHisController/getLcDeploymentHisListByCondition',
		params:{
			start:0,
			limit:getGridBBar(store).pageSize
		}
	});
}
