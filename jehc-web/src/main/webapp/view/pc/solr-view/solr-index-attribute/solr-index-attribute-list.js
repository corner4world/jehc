var store;
var grid;
var SOLR_INDEX_ATTRIBUTE_STATUS_COMBO;
var SOLR_INDEX_ATTRIBUTE_ISSEARCH_COMBO;
var sor_index_highlight_combo;
var sor_index_filter_combo;
var sor_index_attribute_type_combo;
Ext.onReady(function(){
	SOLR_INDEX_ATTRIBUTE_STATUS_COMBO = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","正常"],["1","禁用"]]
	});
	SOLR_INDEX_ATTRIBUTE_ISSEARCH_COMBO = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","是"],["1","否"]]
	});
	sor_index_highlight_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","是"],["1","否"]]
	});
	sor_index_filter_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","是"],["1","否"]]
	});
	sor_index_attribute_type_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],
		data:[["AND","并且"],["OR","或者"],["NOT","不包含"],["TO","范围"]]
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
		{}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../solrIndexAttributeController/getSolrIndexAttributeListByCondition',[]);
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
				header:'facet',
				flex:1,
				dataIndex:'solr_index_attribute_facet'
			},
			{
				header:'权重',
				flex:1,
				dataIndex:'solr_index_attribute_boost'
			},
			{
				header:'拼写',
				flex:1,
				dataIndex:'solr_index_attribute_spellcheck'
			},
			{
				header:'索引编号',
				flex:1,
				dataIndex:'solr_index_id'
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'solr_index_attribute_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'solr_index_attribute_mtime'
			},
			{
				header:'创建者',
				flex:1,
				dataIndex:'xt_userinfo_id'
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
					addSolrIndexAttribute();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateSolrIndexAttribute();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delSolrIndexAttribute();
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
						copySolrIndexAttribute();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailSolrIndexAttribute();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportSolrIndexAttribute(grid,'../solrIndexAttributeController/exportSolrIndexAttribute');
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
				detailSolrIndexAttribute();
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
function delSolrIndexAttribute(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var solr_index_attribute_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == solr_index_attribute_id){
			solr_index_attribute_id=model.selected.items[i].data.solr_index_attribute_id;
		}else{
			solr_index_attribute_id=solr_index_attribute_id+","+model.selected.items[i].data.solr_index_attribute_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {solr_index_attribute_id:solr_index_attribute_id};
			ajaxRequest('../solrIndexAttributeController/delSolrIndexAttribute',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copySolrIndexAttribute(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {solr_index_attribute_id:record.items[0].data.solr_index_attribute_id};
			ajaxRequest('../solrIndexAttributeController/copySolrIndexAttribute',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportSolrIndexAttribute(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addSolrIndexAttributeItem',
			handler:function(){addSolrIndexAttribute();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateSolrIndexAttributeItem',
			handler:function(){updateSolrIndexAttribute();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delSolrIndexAttributeItem',
			handler:function(){delSolrIndexAttribute();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copySolrIndexAttributeItem',
			handler:function(){copySolrIndexAttribute();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailSolrIndexAttributeItem',
			handler:function(){detailSolrIndexAttribute();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportSolrIndexAttribute(grid,'../solrIndexAttributeController/exportSolrIndexAttribute');
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
	initrightgridcontextmenu(grid,contextmenu,['updateSolrIndexAttributeItem','delSolrIndexAttributeItem','copySolrIndexAttributeItem','detailSolrIndexAttributeItem']);
}
/**查询操作**/
function search(){
	store.load({
		url:'../solrIndexAttributeController/getSolrIndexAttributeListByCondition',
		params:{
			start:0,
			limit:getGridBBar(store).pageSize
		}
	});
}
