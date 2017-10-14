var store;
var grid;
var xtProvinceList;
var xtCityList;
var xtDistrictList;
Ext.onReady(function(){
	/**查询区域可扩展**/
	b_warehouse_type_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","赠品"],["1","疵品"],["2","正品"]]
	});
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
			fieldLabel:'仓库名称',
			labelWidth:70,
			emptyText:'请选择',
			name:'b_warehouse_name',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'仓库类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:b_warehouse_type_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_warehouse_type',
			valueField:'value',
			displayField:'text',
			name:'b_warehouse_type',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	/**省份**/
	xtProvinceList = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtAreaRegionController/getPList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['ID', 'NAME'],
		autoLoad:true 
	});
	/**城市**/
	xtCityList = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtAreaRegionController/getCList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['ID', 'NAME']
	});
	/**区县**/
	xtDistrictList = new Ext.data.Store({
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtAreaRegionController/getDList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['ID', 'NAME']
	});
	store = getGridJsonStore('../bWarehouseController/getBWarehouseListByCondition',[]);
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
				header:'商户、卖家',
				flex:1,
				dataIndex:'b_seller_name'
			},
			{
				header:'仓库名称',
				dataIndex:'b_warehouse_name'
			},
			{
				header:'省份',
				dataIndex:'xt_provinceName'
			},
			{
				header:'城市',
				dataIndex:'xt_cityName'
			},
			{
				header:'区县',
				dataIndex:'xt_districtName'
			},
			{
				header:'详细地址',
				flex:1,
				dataIndex:'b_warehouse_address'
			},
			{
				header:'仓库类型',
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
			},
			{
				header:'创建时间',
				dataIndex:'b_warehouse_ctime'
			},
			{
				header:'修改时间',
				dataIndex:'b_warehouse_mtime'
			},
			{
				header:'操作者',
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
					addBWarehouse();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBWarehouse();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBWarehouse();
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
						copyBWarehouse();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBWarehouse();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBWarehouse(grid,'../bWarehouseController/exportBWarehouse');
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
				detailBWarehouse();
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
function delBWarehouse(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_warehouse_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_warehouse_id){
			b_warehouse_id=model.selected.items[i].data.b_warehouse_id;
		}else{
			b_warehouse_id=b_warehouse_id+","+model.selected.items[i].data.b_warehouse_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_warehouse_id:b_warehouse_id};
			ajaxRequest('../bWarehouseController/delBWarehouse',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBWarehouse(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_warehouse_id:record.items[0].data.b_warehouse_id};
			ajaxRequest('../bWarehouseController/copyBWarehouse',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBWarehouse(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBWarehouseItem',
			handler:function(){addBWarehouse();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBWarehouseItem',
			handler:function(){updateBWarehouse();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBWarehouseItem',
			handler:function(){delBWarehouse();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBWarehouseItem',
			handler:function(){copyBWarehouse();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBWarehouseItem',
			handler:function(){detailBWarehouse();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBWarehouse(grid,'../bWarehouseController/exportBWarehouse');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBWarehouseItem','delBWarehouseItem','copyBWarehouseItem','detailBWarehouseItem']);
}

//选择商户
var bSellerWin;
var bSellerGrid;
var bSellerStore;
function selectBSeller(){
	reGetWidthAndHeight();
	initBSellerGrid();
	bSellerWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth*0.8,                    
		height:clientHeight*0.8, 
		maximizable:true,
		minimizable:true,
		plain:true,
		modal:true,
		title:'选择商户【即卖家】----请双击即可设置',
		items:bSellerGrid
	});
	bSellerWin.show();
}

function initBSellerGrid(){
	bSellerStore = getGridJsonStore('../bSellerController/getBSellerListByCondition',[]);
	bSellerGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:bSellerStore,
		columnLines:true,
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
				header:'卖家名称',
				flex:1,
				dataIndex:'b_seller_name'
			},
			{
				header:'卖家电话',
				dataIndex:'b_seller_tel'
			},
			{
				header:'是否官方商店',
				dataIndex:'b_seller_official',
				renderer:function(value){
					if(value == 0){
						return "是";
					}else if(value==1){
						return "否";
					}else{
						return '---';
					}
				}
			},
			{
				header:'卖家等级',
				dataIndex:'b_seller_level'
			},
			{
				header:'状态',
				dataIndex:'b_seller_status',
				renderer:function(value){
					if(value == 0){
						return "可用";
					}else if(value==1){
						return "禁用";
					}else{
						return '---';
					}
				}
			}
		],
		bbar:getGridBBar(bSellerStore),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var b_seller_id = bSellerGrid.getSelectionModel().selected.items[0].data.b_seller_id;
				var b_seller_name = bSellerGrid.getSelectionModel().selected.items[0].data.b_seller_name;
				var str = "[<font color=red><br>商户:"+b_seller_name+"<br></font>]";
				Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						Ext.getCmp('b_seller_name').setValue(b_seller_name);
						Ext.getCmp('b_seller_id').setValue(b_seller_id);
						bSellerWin.close();
					}
				});
			}
		}
	});
}
