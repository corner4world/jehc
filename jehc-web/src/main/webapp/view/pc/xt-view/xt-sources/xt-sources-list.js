var store;
var grid;
var XT_SOURCES_STATUS_COMBO
var XT_SOURCES_TYPE_COMBO
Ext.onReady(function(){
	XT_SOURCES_STATUS_COMBO = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","正常"],["1","禁用"]]
	});
	XT_SOURCES_TYPE_COMBO = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["1","css"],["2","img"],["3","js"],["4","doc"],["5","xls"],["6","csv"],["7","zip"],["8","rar"],["9","jar"]]
	});
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		maxHeight:150,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		layout:'table',
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:XT_SOURCES_STATUS_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_sources_status',
			valueField:'value',
			displayField:'text',
			id:'xt_sources_status',
			name:'xt_sources_status',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:XT_SOURCES_TYPE_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_sources_type',
			valueField:'value',
			displayField:'text',
			id:'xt_sources_type',
			name:'xt_sources_type',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'标题',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_sources_title',
			name:'xt_sources_title',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtSourcesController/getXtSourcesListByCondition',[]);
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
				header:'标题',
				flex:1,
				dataIndex:'xt_sources_title'
			},
			{
				header:'类型',
				flex:1,
				dataIndex:'xt_sources_type',
				renderer:function(value){
					return initComBoData(XT_SOURCES_TYPE_COMBO,value,'value','text');
				}
			},
			{
				header:'状态',
				flex:1,
				dataIndex:'xt_sources_status',
				renderer:function(value){
					return initComBoData(XT_SOURCES_STATUS_COMBO,value,'value','text');
				}
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'xt_sources_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'xt_sources_mtime'
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
					addXtSources();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtSources();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtSources();
				}
			 },
			 {
				text:'资源视图列表',
				tooltip:'资源视图列表',
				minWidth:tbarBtnMinWidth,
				cls:'detailBtn',
				icon:detailIcon,
				handler:function(){
					document.location.href='../xtSourcesController/toXtSourcesDataView';
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
						copyXtSources();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtSources();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtSources(grid,'../xtSourcesController/exportXtSources');
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
				detailXtSources();
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
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtSources(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_sources_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_sources_id){
			xt_sources_id=model.selected.items[i].data.xt_sources_id;
		}else{
			xt_sources_id=xt_sources_id+","+model.selected.items[i].data.xt_sources_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_sources_id:xt_sources_id};
			ajaxRequest('../xtSourcesController/delXtSources',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtSources(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_sources_id:record.items[0].data.xt_sources_id};
			ajaxRequest('../xtSourcesController/copyXtSources',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtSources(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtSourcesItem',
			handler:function(){addXtSources();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtSourcesItem',
			handler:function(){updateXtSources();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtSourcesItem',
			handler:function(){delXtSources();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtSourcesItem',
			handler:function(){copyXtSources();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtSourcesItem',
			handler:function(){detailXtSources();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtSources(grid,'../xtSourcesController/exportXtSources');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtSourcesItem','delXtSourcesItem','copyXtSourcesItem','detailXtSourcesItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtSourcesController/getXtSourcesListByCondition',searchForm); 
}
