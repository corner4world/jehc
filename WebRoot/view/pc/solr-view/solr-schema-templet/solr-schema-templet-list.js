var store;
var grid;
var SOLR_SCHEMA_COMBO_STORE;
Ext.onReady(function(){
	SOLR_SCHEMA_COMBO_STORE = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","正常"],["1","禁用"]]
	});
	store = getGridJsonStore('../solrSchemaTempletController/getSolrSchemaTempletListByCondition',[]);
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
				dataIndex:'solr_schema_templet_title'
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'solr_schema_templet_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'solr_schema_templet_mtime'
			},
			{
				header:'操作人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'状态',
				flex:1,
				dataIndex:'solr_schema_templet_status',
				renderer:function(value){
					return initComBoData(SOLR_SCHEMA_COMBO_STORE,value,'value','text');
				}
			}
		],
		tbar:[
			 {
				text:'添加',
				tooltip:'添加',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addSolrSchemaTemplet();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateSolrSchemaTemplet();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delSolrSchemaTemplet();
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
						copySolrSchemaTemplet();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailSolrSchemaTemplet();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportSolrSchemaTemplet(grid,'../solrSchemaTempletController/exportSolrSchemaTemplet');
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
				detailSolrSchemaTemplet();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
	/**重新设置列值**/
	initDataGrid(grid);
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delSolrSchemaTemplet(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var solr_schema_templet_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == solr_schema_templet_id){
			solr_schema_templet_id=model.selected.items[i].data.solr_schema_templet_id;
		}else{
			solr_schema_templet_id=solr_schema_templet_id+","+model.selected.items[i].data.solr_schema_templet_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {solr_schema_templet_id:solr_schema_templet_id};
			ajaxRequest('../solrSchemaTempletController/delSolrSchemaTemplet',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copySolrSchemaTemplet(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {solr_schema_templet_id:record.items[0].data.solr_schema_templet_id};
			ajaxRequest('../solrSchemaTempletController/copySolrSchemaTemplet',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportSolrSchemaTemplet(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addSolrSchemaTempletItem',
			handler:function(){addSolrSchemaTemplet();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateSolrSchemaTempletItem',
			handler:function(){updateSolrSchemaTemplet();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delSolrSchemaTempletItem',
			handler:function(){delSolrSchemaTemplet();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copySolrSchemaTempletItem',
			handler:function(){copySolrSchemaTemplet();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailSolrSchemaTempletItem',
			handler:function(){detailSolrSchemaTemplet();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportSolrSchemaTemplet(grid,'../solrSchemaTempletController/exportSolrSchemaTemplet');
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
	initrightgridcontextmenu(grid,contextmenu,['updateSolrSchemaTempletItem','delSolrSchemaTempletItem','copySolrSchemaTempletItem','detailSolrSchemaTempletItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../solrSchemaTempletController/getSolrSchemaTempletListByCondition',searchForm); 
}
