var store;
var grid;
var solrsourceIndexList;
var solrdestIndexList;
var items;
Ext.onReady(function(){
	var solr_core_id = $('#solr_core_id').val();
	solrsourceIndexList = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy( { 
			url:'../solrIndexController/getSolrIndexList?solr_core_id='+solr_core_id,
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['solr_index_id', 'solr_index_name'],
		autoLoad:true 
	});
	solrdestIndexList = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy( { 
			url:'../solrIndexController/getSolrIndexList?solr_core_id='+solr_core_id,
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['solr_index_id', 'solr_index_name'],
		autoLoad:true 
	});
	/**查询区域可扩展**/
	items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		maxHeight:150,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:95,
			labelAlign:'right',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'索引字段源',
			xtype:'textfield',
			id:'solr_filed_copy_source_id_',
			xtype:'combo',
			emptyText:'请选择',
			store:solrsourceIndexList,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'solr_filed_copy_source_id',
			valueField:'solr_index_id',
			displayField:'solr_index_name',
			anchor:'40%'
		},
		{
			fieldLabel:'目标字段索引',
			xtype:'textfield',
			id:'solr_filed_copy_dest_id_',
			xtype:'combo',
			emptyText:'请选择',
			store:solrdestIndexList,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'solr_filed_copy_dest_id',
			valueField:'solr_index_id',
			displayField:'solr_index_name',
			anchor:'40%'
		},
		{
			fieldLabel:'备注',
			xtype:'textareafield',
			id:'solr_filed_copy_remark_',
			maxLength:200,
			anchor:'80%'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../solrFiledCopyController/getSolrFiledCopyListByCondition?solr_core_id='+solr_core_id,[]);
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
				header:'索引字段源',
				flex:1,
				dataIndex:'solr_filed_copy_source_name'
			},
			{
				header:'索引字段源（备注）',
				flex:1,
				dataIndex:'solr_filed_copy_source_index'
			},
			{
				header:'目标字段索引',
				flex:1,
				dataIndex:'solr_filed_copy_dest_name'
			},
			{
				header:'目标字段索引（备注）',
				flex:1,
				dataIndex:'solr_filed_copy_dest_index'
			},
			{
				header:'备注',
				flex:1,
				dataIndex:'solr_filed_copy_remark'
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'solr_filed_copy_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'solr_filed_copy_mtime'
			},
			{
				header:'创建人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
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
					addSolrFiledCopy();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateSolrFiledCopy();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delSolrFiledCopy();
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
						copySolrFiledCopy();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailSolrFiledCopy();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportSolrFiledCopy(grid,'../solrFiledCopyController/exportSolrFiledCopy');
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
				detailSolrFiledCopy();
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
function delSolrFiledCopy(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var solr_filed_copy_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == solr_filed_copy_id){
			solr_filed_copy_id=model.selected.items[i].data.solr_filed_copy_id;
		}else{
			solr_filed_copy_id=solr_filed_copy_id+","+model.selected.items[i].data.solr_filed_copy_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {solr_filed_copy_id:solr_filed_copy_id};
			ajaxRequest('../solrFiledCopyController/delSolrFiledCopy',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copySolrFiledCopy(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {solr_filed_copy_id:record.items[0].data.solr_filed_copy_id};
			ajaxRequest('../solrFiledCopyController/copySolrFiledCopy',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportSolrFiledCopy(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addSolrFiledCopyItem',
			handler:function(){addSolrFiledCopy();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateSolrFiledCopyItem',
			handler:function(){updateSolrFiledCopy();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delSolrFiledCopyItem',
			handler:function(){delSolrFiledCopy();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copySolrFiledCopyItem',
			handler:function(){copySolrFiledCopy();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailSolrFiledCopyItem',
			handler:function(){detailSolrFiledCopy();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportSolrFiledCopy(grid,'../solrFiledCopyController/exportSolrFiledCopy');
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
	initrightgridcontextmenu(grid,contextmenu,['updateSolrFiledCopyItem','delSolrFiledCopyItem','copySolrFiledCopyItem','detailSolrFiledCopyItem']);
}
/**查询操作**/
function search(){
	store.load({
		url:'../solrFiledCopyController/getSolrFiledCopyListByCondition',
		params:{
			start:0,
			solr_filed_copy_source_id:Ext.getCmp('solr_filed_copy_source_id_').getValue(),
			solr_filed_copy_dest_id:Ext.getCmp('solr_filed_copy_dest_id_').getValue(),
			solr_filed_copy_remark:Ext.getCmp('solr_filed_copy_remark_').getValue(),
			limit:getGridBBar(store).pageSize,
			solr_core_id:$('#solr_core_id').val()
		}
	});
}
