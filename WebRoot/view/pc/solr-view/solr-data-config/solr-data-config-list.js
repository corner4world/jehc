var store;
var grid;
var SOLR_DataConfig_COMBO_STORE;
Ext.onReady(function(){
	SOLR_DataConfig_COMBO_STORE = Ext.create('Ext.data.SimpleStore',{ 
         fields:['value','text'],  
		 data:[["0","正常"],["1","禁用"]]
	});
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
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
			fieldLabel:'标题',
			xtype:'textfield',
			labelWidth:70,
			id:'solr_data_config_title',
			name:'solr_data_config_title',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:SOLR_DataConfig_COMBO_STORE,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'solr_data_config_status',
			valueField:'value',
			displayField:'text',
			id:'solr_data_config_status',
			name:'solr_data_config_status',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../solrDataConfigController/getSolrDataConfigListByCondition',[]);
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
				header:'标题',
				flex:1,
				dataIndex:'solr_data_config_title'
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'solr_data_config_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'solr_data_config_mtime'
			},
			{
				header:'状态',
				flex:1,
				dataIndex:'solr_data_config_status',
				renderer:function(value){
					return initComBoData(SOLR_DataConfig_COMBO_STORE,value,'value','text');
				}
			},
			{
				header:'操作人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			}
		],
		tbar:[
			 {
				text:'添加',
				tooltip:'添加',
				icon:addIcon,
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				handler:function(){
					addSolrDataConfig();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateSolrDataConfig();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delSolrDataConfig();
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
					text:'复制一行并生成记录',
					tooltip:'复制一行并生成记录',
					glyph:0xf0ea,
					handler:function(){
						copySolrDataConfig();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailSolrDataConfig();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportSolrDataConfig(grid,'../solrDataConfigController/exportSolrDataConfig');
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
				detailSolrDataConfig();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	/**重新设置列值**/
	initDataGrid(grid);
	/**调用右键**/
	initRight();
});
/**删除操作**/
function delSolrDataConfig(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var solr_data_config_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == solr_data_config_id){
			solr_data_config_id=model.selected.items[i].data.solr_data_config_id;
		}else{
			solr_data_config_id=solr_data_config_id+","+model.selected.items[i].data.solr_data_config_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {solr_data_config_id:solr_data_config_id};
			ajaxRequest('../solrDataConfigController/delSolrDataConfig',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copySolrDataConfig(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {solr_data_config_id:record.items[0].data.solr_data_config_id};
			ajaxRequest('../solrDataConfigController/copySolrDataConfig',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportSolrDataConfig(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addSolrDataConfigItem',
			handler:function(){addSolrDataConfig();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateSolrDataConfigItem',
			handler:function(){updateSolrDataConfig();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delSolrDataConfigItem',
			handler:function(){delSolrDataConfig();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copySolrDataConfigItem',
			handler:function(){copySolrDataConfig();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailSolrDataConfigItem',
			handler:function(){detailSolrDataConfig();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportSolrDataConfig(grid,'../solrDataConfigController/exportSolrDataConfig');
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
	initrightgridcontextmenu(grid,contextmenu,['updateSolrDataConfigItem','delSolrDataConfigItem','copySolrDataConfigItem','detailSolrDataConfigItem']);
}
/**查询操作**/
function search(){
	var solr_data_config_title = Ext.getCmp("solr_data_config_title").getValue();
	var solr_data_config_status = Ext.getCmp("solr_data_config_status").getValue();
	store.load({
		url:'../solrDataConfigController/getSolrDataConfigListByCondition',
		params:{
			start:0,
			limit:getGridBBar(store).pageSize,
			solr_data_config_title:solr_data_config_title,
			solr_data_config_status:solr_data_config_status
		}
	});
}
