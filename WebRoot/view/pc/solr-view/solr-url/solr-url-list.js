var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../solrUrlController/getSolrUrlListByCondition',[]);
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
				header:'访问地址',
				flex:1,
				dataIndex:'solr_url_url'
			},
			{
				header:'备注',
				flex:1,
				dataIndex:'solr_url_content'
			},
			{
				header:'创建时间',
				width:150,
				dataIndex:'solr_url_ctime'
			},
			{
				header:'修改时间',
				width:150,
				dataIndex:'solr_url_mtime'
			},
			{
				header:'创建人',
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'操作',
				align:'center',
				xtype:'widgetcolumn',
				width:150,
				widget:{
	                xtype:'button',
	                text:'配置多实例',
	                icon:detailIcon,
	                handler:function(rec){
	                	var solr_url_id = rec.getWidgetRecord().data.solr_url_id;
	  					window.location.href = '../solrCoreController/loadSolrCore?solr_url_id='+solr_url_id;
				    }
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
					addSolrUrl();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateSolrUrl();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delSolrUrl();
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
						copySolrUrl();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailSolrUrl();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportSolrUrl(grid,'../solrUrlController/exportSolrUrl');
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
				detailSolrUrl();
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
});
/**删除操作**/
function delSolrUrl(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var solr_url_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == solr_url_id){
			solr_url_id=model.selected.items[i].data.solr_url_id;
		}else{
			solr_url_id=solr_url_id+","+model.selected.items[i].data.solr_url_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {solr_url_id:solr_url_id};
			ajaxRequest('../solrUrlController/delSolrUrl',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copySolrUrl(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {solr_url_id:record.items[0].data.solr_url_id};
			ajaxRequest('../solrUrlController/copySolrUrl',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportSolrUrl(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addSolrUrlItem',
			handler:function(){addSolrUrl();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateSolrUrlItem',
			handler:function(){updateSolrUrl();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delSolrUrlItem',
			handler:function(){delSolrUrl();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copySolrUrlItem',
			handler:function(){copySolrUrl();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailSolrUrlItem',
			handler:function(){detailSolrUrl();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportSolrUrl(grid,'../solrUrlController/exportSolrUrl');
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
	initrightgridcontextmenu(grid,contextmenu,['updateSolrUrlItem','delSolrUrlItem','copySolrUrlItem','detailSolrUrlItem']);
}
