var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
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
			fieldLabel:'商户、卖家',
			labelWidth:70,
			emptyText:'请选择',
			name:'b_seller_name',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'库位名称',
			labelWidth:70,
			emptyText:'请选择',
			name:'b_warehouse_location_name',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bWarehouseLocationController/getBWarehouseLocationListByCondition',[]);
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
				header:'归属商户、卖家',
				flex:1,
				dataIndex:'b_seller_name'
			},
			{
				header:'归属仓库',
				flex:1,
				dataIndex:'b_warehouse_name'
			},
			{
				header:'仓库库位名称',
				flex:1,
				dataIndex:'b_warehouse_location_name'
			},
			{
				header:'仓库库位空间大小(m³)',
				flex:1,
				dataIndex:'b_warehouse_location_space'
			},
			{
				header:'宽(m)',
				flex:1,
				dataIndex:'b_warehouse_location_width'
			},
			{
				header:'高度(m)',
				flex:1,
				dataIndex:'b_warehouse_location_height'
			},
			{
				header:'长度(m)',
				flex:1,
				dataIndex:'b_warehouse_location_length'
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
					addBWarehouseLocation();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBWarehouseLocation();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBWarehouseLocation();
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
						copyBWarehouseLocation();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBWarehouseLocation();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBWarehouseLocation(grid,'../bWarehouseLocationController/exportBWarehouseLocation');
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
				detailBWarehouseLocation();
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
function delBWarehouseLocation(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_warehouse_location_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_warehouse_location_id){
			b_warehouse_location_id=model.selected.items[i].data.b_warehouse_location_id;
		}else{
			b_warehouse_location_id=b_warehouse_location_id+","+model.selected.items[i].data.b_warehouse_location_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_warehouse_location_id:b_warehouse_location_id};
			ajaxRequest('../bWarehouseLocationController/delBWarehouseLocation',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBWarehouseLocation(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_warehouse_location_id:record.items[0].data.b_warehouse_location_id};
			ajaxRequest('../bWarehouseLocationController/copyBWarehouseLocation',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBWarehouseLocation(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBWarehouseLocationItem',
			handler:function(){addBWarehouseLocation();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBWarehouseLocationItem',
			handler:function(){updateBWarehouseLocation();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBWarehouseLocationItem',
			handler:function(){delBWarehouseLocation();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBWarehouseLocationItem',
			handler:function(){copyBWarehouseLocation();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBWarehouseLocationItem',
			handler:function(){detailBWarehouseLocation();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBWarehouseLocation(grid,'../bWarehouseLocationController/exportBWarehouseLocation');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBWarehouseLocationItem','delBWarehouseLocationItem','copyBWarehouseLocationItem','detailBWarehouseLocationItem']);
}

//选择仓库
var bWarehouseWin;
var bWarehouseGrid;
var bWarehouseStore;
function selectBWarehouse(){
	reGetWidthAndHeight();
	initBWarehouseGrid();
	bWarehouseWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth*0.8,                    
		height:clientHeight*0.8, 
		maximizable:true,
		minimizable:true,
		plain:true,
		modal:true,
		title:'选择仓库----请双击即可设置',
		items:bWarehouseGrid
	});
	bWarehouseWin.show();
}
function initBWarehouseGrid(){
	bWarehouseStore = getGridJsonStore('../bWarehouseController/getBWarehouseListByCondition',[]);
	bWarehouseGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:bWarehouseStore,
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
				header:'商户、卖家',
				flex:1,
				dataIndex:'b_seller_name'
			},
			{
				header:'仓库名称',
				flex:1,
				dataIndex:'b_warehouse_name'
			},
			{
				header:'省份',
				flex:1,
				dataIndex:'xt_provinceName'
			},
			{
				header:'城市',
				flex:1,
				dataIndex:'xt_cityName'
			},
			{
				header:'区县',
				flex:1,
				dataIndex:'xt_districtName'
			},
			{
				header:'详细地址',
				flex:1,
				dataIndex:'b_warehouse_address'
			},
			{
				header:'仓库类型',
				flex:1,
				dataIndex:'b_warehouse_type',
				renderer:function(value){
					if(value == 0){
						return "赠品";
					}else if(value==1){
						return "疵品";
					}else if(value == 2){
						return '正品';
					}else{
						return '---';
					}
				}
			}
		],
		bbar:getGridBBar(bWarehouseStore),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var b_warehouse_id = bWarehouseGrid.getSelectionModel().selected.items[0].data.b_warehouse_id;
				var b_seller_name = bWarehouseGrid.getSelectionModel().selected.items[0].data.b_seller_name;
				var b_warehouse_name = bWarehouseGrid.getSelectionModel().selected.items[0].data.b_warehouse_name;
				var str = "[<font color=red><br>商户:"+b_seller_name+"<br>仓库:"+b_warehouse_name+"<br></font>]";
				Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						Ext.getCmp('b_warehouse_name').setValue(b_warehouse_name);
						Ext.getCmp('b_warehouse_id').setValue(b_warehouse_id);
						Ext.getCmp('b_seller_name').setValue(b_seller_name);
						bWarehouseWin.close();
					}
				});
			}
		}
	});
}
