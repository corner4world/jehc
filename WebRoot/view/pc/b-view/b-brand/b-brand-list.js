var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	b_brand_status_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","可用"],["1","禁用"]]
	});
	b_brand_type_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","国内"],["1","国外"]]
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
			fieldLabel:'品牌名称',
			labelWidth:70,
			emptyText:'请选择',
			name:'b_brand_name',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:b_brand_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_brand_status',
			valueField:'value',
			displayField:'text',
			name:'b_brand_status',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:b_brand_type_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_brand_type',
			valueField:'value',
			displayField:'text',
			name:'b_brand_type',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bBrandController/getBBrandListByCondition',[]);
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
				header:'品牌名称',
				flex:1,
				dataIndex:'b_brand_name'
			},
			{
				header:'状态',
				dataIndex:'b_brand_status',
				renderer:function(value){
					if(value == 0){
						return "可用";
					}else{
						return "禁用";
					}
				}
			},
			{
				header:'类型',
				dataIndex:'b_brand_type',
				renderer:function(value){
					if(value == 0){
						return "国内";
					}else{
						return "国外";
					}
				}
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'b_brand_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'b_brand_mtime'
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
					addBBrand();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBBrand();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBBrand();
				}
			 },
			 {
				text:'检索',
				tooltip:'检索',
				minWidth:tbarBtnMinWidth,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					search()
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
						copyBBrand();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBBrand();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBBrand(grid,'../bBrandController/exportBBrand');
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
						grid.getStore().reload();
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
				detailBBrand();
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
function delBBrand(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_brand_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_brand_id){
			b_brand_id=model.selected.items[i].data.b_brand_id;
		}else{
			b_brand_id=b_brand_id+","+model.selected.items[i].data.b_brand_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_brand_id:b_brand_id};
			ajaxRequest('../bBrandController/delBBrand',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBBrand(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_brand_id:record.items[0].data.b_brand_id};
			ajaxRequest('../bBrandController/copyBBrand',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBBrand(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBBrandItem',
			handler:function(){addBBrand();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBBrandItem',
			handler:function(){updateBBrand();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBBrandItem',
			handler:function(){delBBrand();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBBrandItem',
			handler:function(){copyBBrand();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBBrandItem',
			handler:function(){detailBBrand();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBBrand(grid,'../bBrandController/exportBBrand');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBBrandItem','delBBrandItem','copyBBrandItem','detailBBrandItem']);
}

/**查询操作**/
function search(){
	initSearch(store,'../bBrandController/getBBrandListByCondition',searchForm); 
}