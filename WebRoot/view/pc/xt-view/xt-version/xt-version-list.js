var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		maxHeight:220,
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
			fieldLabel:'版本名称',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_version_name',
			name:'xt_version_name',
			anchor:'47%',
			labelAlign:'left'
		},
		{
			layout:'table',
			xtype:'form',
			anchor:'50%',
			items:[
			{
				fieldLabel:'上传时间',
				xtype:'datefield',
				labelWidth:70,
				format:'Y-m-d',
				id:'xt_version_ctime_st',
				name:'xt_version_ctime_st',
				width:220,
				labelAlign:'left'
			},
			{
				fieldLabel:'至',
				xtype:'datefield',
				labelWidth:15,
				format:'Y-m-d',
				id:'xt_version_ctime_et',
				name:'xt_version_ctime_et',
				width:220,
				labelAlign:'left'
			}
			]
		},
		{
			layout:'table',
			xtype:'form',
			anchor:'50%',
			items:[
			{
				fieldLabel:'修改时间',
				xtype:'datefield',
				labelWidth:70,
				format:'Y-m-d',
				id:'xt_version_mtime_st',
				name:'xt_version_mtime_st',
				width:220,
				labelAlign:'left'
			},
			{
				fieldLabel:'至',
				xtype:'datefield',
				labelWidth:15,
				format:'Y-m-d',
				id:'xt_version_mtime_et',
				name:'xt_version_mtime_et',
				width:220,
				labelAlign:'left'
			}
			]
		},
		{
			layout:'table',
			xtype:'form',
			anchor:'50%',
			items:[
			{
				fieldLabel:'下载次数',
				labelWidth:70,
				xtype:'combo',
				emptyText:'请选择',
				store:cSData(),
				mode:'local',
				triggerAction:'all',
				editable:false,
				valueField:'value',
				displayField:'text',
				id:'xt_version_number_cs',
				name:'xt_version_number_cs',
				hiddenName:'xt_version_number_cs',
				width:220,
				labelAlign:'left'
			},
			{
				fieldLabel:'值',
				xtype:'numberfield',
				labelWidth:15,
				id:'xt_version_number',
				name:'xt_version_number',
				width:150,
				labelAlign:'left'
			}
			]
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtVersionController/getXtVersionListByCondition',[]);
	var tbar = Ext.create('Ext.Toolbar',{
		cls:'tbar',
		items:[
		{
			text:'添加',
			tooltip:'添加',
			minWidth:tbarBtnMinWidth,
			cls:'addBtn',
			icon:addIcon,
			handler:function(){
				addXtVersion();
			}
		 },
		 {
			text:'编辑',
			tooltip:'编辑',
			minWidth:tbarBtnMinWidth,
			cls:'updateBtn',
			icon:editIcon,
			handler:function(){
				updateXtVersion();
			}
		 },
		 {
			text:'删除',
			tooltip:'删除',
			minWidth:tbarBtnMinWidth,
			cls:'delBtn',
			icon:delIcon,
			handler:function(){
				delXtVersion();
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
					copyXtVersion();
				}
			 },
			 {
				text:'明 细',
				tooltip:'明 细',
				glyph:0xf03b,
				handler:function(){
					detailXtVersion();
				}
			 },
			 '-',
			 {
				text:'导 出',
				tooltip:'导 出',
				glyph:0xf1c3,
				handler:function(){
					exportXtVersion(grid,'../xtVersionController/exportXtVersion');
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
		 ]
    });
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		store:store,
		title:'查询结果',
		border:true,
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
				header:'版本名称',
				flex:1,
				dataIndex:'xt_version_name'
			},
			{
				header:'下载次数',
				flex:1,
				dataIndex:'xt_version_number'
			},
			{
				header:'上传时间',
				flex:1,
				dataIndex:'xt_version_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'xt_version_mtime'
			},
			{
				header:'上传人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			}
		],
		tbar:tbar,
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailXtVersion();
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
function delXtVersion(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_version_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_version_id){
			xt_version_id=model.selected.items[i].data.xt_version_id;
		}else{
			xt_version_id=xt_version_id+","+model.selected.items[i].data.xt_version_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_version_id:xt_version_id};
			ajaxRequest('../xtVersionController/delXtVersion',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtVersion(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_version_id:record.items[0].data.xt_version_id};
			ajaxRequest('../xtVersionController/copyXtVersion',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtVersion(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtVersionItem',
			handler:function(){addXtVersion();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtVersionItem',
			handler:function(){updateXtVersion();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtVersionItem',
			handler:function(){delXtVersion();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtVersionItem',
			handler:function(){copyXtVersion();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtVersionItem',
			handler:function(){detailXtVersion();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtVersion(grid,'../xtVersionController/exportXtVersion');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtVersionItem','delXtVersionItem','copyXtVersionItem','detailXtVersionItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtVersionController/getXtVersionListByCondition',searchForm);
}
