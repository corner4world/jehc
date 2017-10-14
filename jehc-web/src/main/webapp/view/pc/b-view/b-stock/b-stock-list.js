var store;
var grid;
Ext.onReady(function(){
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
			fieldLabel:'商品名称',
			labelWidth:70,
			emptyText:'请选择',
			name:'b_product_name',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bStockController/getBStockListByCondition',[]);
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
				header:'商家、卖家',
				flex:1,
				dataIndex:'b_seller_name'
			},
			{
				header:'商品名称',
				dataIndex:'b_product_name'
			},
			{
				header:'分类',
				dataIndex:'b_category_name'
			},
			{
				header:'品牌',
				dataIndex:'b_brand_name'
			},
			{
				header:'可卖数（商家所设置的数量）',
				dataIndex:'b_stock_countable_sell'
			},
			{
				header:'锁定数（已卖数）',
				dataIndex:'b_stock_locks_number'
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
					addBStock();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBStock();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBStock();
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
						copyBStock();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBStock();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBStock(grid,'../bStockController/exportBStock');
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
				detailBStock();
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
function delBStock(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_stock_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_stock_id){
			b_stock_id=model.selected.items[i].data.b_stock_id;
		}else{
			b_stock_id=b_stock_id+","+model.selected.items[i].data.b_stock_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_stock_id:b_stock_id};
			ajaxRequest('../bStockController/delBStock',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBStock(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_stock_id:record.items[0].data.b_stock_id};
			ajaxRequest('../bStockController/copyBStock',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBStock(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBStockItem',
			handler:function(){addBStock();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBStockItem',
			handler:function(){updateBStock();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBStockItem',
			handler:function(){delBStock();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBStockItem',
			handler:function(){copyBStock();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBStockItem',
			handler:function(){detailBStock();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBStock(grid,'../bStockController/exportBStock');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBStockItem','delBStockItem','copyBStockItem','detailBStockItem']);
}


//选择商户商品
var bSellerProductWin;
var bSellerProductGrid;
var bSellerProductStore;
function selectBSellerProduct(){
	reGetWidthAndHeight();
	initBSellerProductGrid();
	bSellerProductWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth*0.8,                    
		height:clientHeight*0.8, 
		maximized:true,
		maximizable:true,
		minimizable:true,
		plain:true,
		modal:true,
		title:'选择商户、卖家商品----请双击即可设置',
		items:bSellerProductGrid
	});
	bSellerProductWin.show();
}

function initBSellerProductGrid(){
	bSellerProductStore = getGridJsonStore('../bSellerProductController/getBSellerProductStockListByCondition',[]);
	bSellerProductGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:bSellerProductStore,
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
				header:'商户、卖家',
				flex:1,
				dataIndex:'b_seller_name'
			},
			{
				header:'产品名称',
				flex:1,
				dataIndex:'b_product_name'
			},
			{
				header:'分类',
				flex:1,
				dataIndex:'b_category_name'
			},
			{
				header:'品牌',
				flex:1,
				dataIndex:'b_brand_name'
			},
			{
				header:'状态',
				flex:1,
				dataIndex:'b_seller_product_status',
				renderer:function(value){
					if(value == 0){
						return "已关联";
					}else if(value==1){
						return "已取消";
					}else{
						return '---';
					}
				}
			}
		],
		bbar:getGridBBar(bSellerProductStore),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var b_seller_product_id = bSellerProductGrid.getSelectionModel().selected.items[0].data.b_seller_product_id;
				var b_seller_name = bSellerProductGrid.getSelectionModel().selected.items[0].data.b_seller_name;
				var b_product_name = bSellerProductGrid.getSelectionModel().selected.items[0].data.b_product_name; 
				var str = "[<font color=red><br>商户:"+b_seller_name+"<br>商品:"+b_product_name+"<br></font>]";
				Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						Ext.getCmp('b_seller_name').setValue(b_seller_name);
						Ext.getCmp('b_seller_product_id').setValue(b_seller_product_id);
						Ext.getCmp('b_product_name').setValue(b_product_name);
						bSellerProductWin.close();
					}
				});
			}
		}
	});
}
