var store;
var grid;
var solr_sort_combo_store;
Ext.onReady(function(){
	solr_sort_combo_store =Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["asc","升序"],["desc","降序"]]
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
			fieldLabel:'排序名称',
			xtype:'textfield',
			labelWidth:70,
			id:'solr_sort_name',
			name:'solr_sort_name',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../solrSortController/getSolrSortListByCondition?solr_index_id='+$('#solr_index_id_').val(),[]);
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
				header:'排序名称',
				flex:1,
				dataIndex:'solr_sort_name'
			},
			{
				header:'排序code',
				flex:1,
				dataIndex:'solr_sort_code'
			},
			{
				header:'选用权重',
				flex:1,
				dataIndex:'solr_sort_useboost'
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'solr_sort_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'solr_sort_mtime'
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
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addSolrSort();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateSolrSort();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delSolrSort();
				}
			 },
			 {
				text:'检索',
				tooltip:'检索',
				minWidth:tbarBtnMinWidth,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					grid.getStore().reload();
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
						copySolrSort();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailSolrSort();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportSolrSort(grid,'../solrSortController/exportSolrSort');
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
				detailSolrSort();
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
function delSolrSort(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var solr_sort_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == solr_sort_id){
			solr_sort_id=model.selected.items[i].data.solr_sort_id;
		}else{
			solr_sort_id=solr_sort_id+","+model.selected.items[i].data.solr_sort_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {solr_sort_id:solr_sort_id};
			ajaxRequest('../solrSortController/delSolrSort',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copySolrSort(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {solr_sort_id:record.items[0].data.solr_sort_id};
			ajaxRequest('../solrSortController/copySolrSort',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportSolrSort(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addSolrSortItem',
			handler:function(){addSolrSort();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateSolrSortItem',
			handler:function(){updateSolrSort();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delSolrSortItem',
			handler:function(){delSolrSort();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copySolrSortItem',
			handler:function(){copySolrSort();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailSolrSortItem',
			handler:function(){detailSolrSort();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportSolrSort(grid,'../solrSortController/exportSolrSort');
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
	initrightgridcontextmenu(grid,contextmenu,['updateSolrSortItem','delSolrSortItem','copySolrSortItem','detailSolrSortItem']);
}
/**查询操作**/
function search(){
	var solr_sort_name = Ext.getCmp("solr_sort_name").getValue();
	store.load({
		url:'../solrSortController/getSolrSortListByCondition',
		params:{
			start:0,
			limit:getGridBBar(store).pageSize,
			solr_sort_name:solr_sort_name
		}
	});
}
