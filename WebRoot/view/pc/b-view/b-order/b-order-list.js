var store;
var grid;
var b_memberList;
var b_invoiceList;
var xtProvinceList;
var xtCityList;
var xtDistrictList;
var b_order_detail_grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	b_invoice_type_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","普通发票"],["1","增值税发票"]]
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
			fieldLabel:'订单号',
			labelWidth:70,
			emptyText:'请选择',
			name:'b_order_key',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'发票名称',
			labelWidth:70,
			emptyText:'请输入',
			name:'b_invoice_name',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'发票类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:b_invoice_type_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_invoice_type',
			valueField:'value',
			displayField:'text',
			name:'b_invoice_type',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	/**会员**/
	b_memberList = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy({ 
			url:'../bMemberController/getBMemberListByCondition',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		pageSize:10,  
		fields:['b_member_id', 'b_member_name'],
		autoLoad:true 
	});
	/**会员发票
	b_invoiceList = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy({ 
			url:'../bInvoiceController/getBInvoiceListByCondition',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		pageSize:10,  
		fields:['b_invoice_id', 'b_invoice_name'],
		autoLoad:true 
	});
	**/
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
	store = getGridJsonStore('../bOrderController/getBOrderListByCondition',[]);
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
				header:'订单号名称',
				locked:true,
				dataIndex:'b_order_name'
			},
			{
				header:'订单号',
				locked:true,
				dataIndex:'b_order_key'
			},
			{
				header:'订单总金额',
				locked:true,
				dataIndex:'b_order_total_price'
			},
			{
				header:'购买总数',
				locked:true,
				dataIndex:'b_order_total_number'
			},
			{
				header:'订单来源',
				dataIndex:'b_order_from',
				renderer:function(value){
					if(value == 0){
						return "普通订单";
					}else if(value == 1){
						return "团购订单";
					}else if(value == 2){
						return "其他订单";
					}else{
						return '---';
					}
				}
			},
			{
				header:'付款状态',
				locked:true,
				dataIndex:'b_order_type',
				renderer:function(value){
					if(value == 0){
						return "<font color='red'>待支付</font>";
					}else if(value == 1){
						return "<font color='blue'>已支付</font>";
					}else if(value == 2){
						return "<font color='green'>支付部分金额</font>";
					}else{
						return '---';
					}
				}
			},
			{
				header:'订单状态',
				locked:true,
				dataIndex:'b_order_status',
				renderer:function(value){
					if(value == 0){
						return "正常订单";
					}else if(value == 1){
						return "已作废订单";
					}else{
						return '---';
					}
				}
			},
			{
				header:'会员',
				flex:1,
				dataIndex:'b_member_name'
			},
			{
				header:'订单备注',
				flex:1,
				dataIndex:'b_order_remark'
			},
			{
				header:'创建时间',
				dataIndex:'b_order_ctime'
			},
			{
				header:'修改时间',
				dataIndex:'b_order_mtime'
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
					addBOrder();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBOrder();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBOrder();
				}
			 },
			 {
				text:'去支付',
				tooltip:'支付金额',
				icon:payIcon,
				minWidth:tbarBtnMinWidth,
				cls:'payBtn',
				handler:function(){
					addBOrderPay();
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
						copyBOrder();
					}
				 },
				 {
					text:'去支付',
					tooltip:'支付金额',
					glyph:0xf0d0,
					handler:function(){
						addBOrderPay();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBOrder();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBOrder(grid,'../bOrderController/exportBOrder');
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
				detailBOrder();
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
function delBOrder(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_order_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_order_id){
			b_order_id=model.selected.items[i].data.b_order_id;
		}else{
			b_order_id=b_order_id+","+model.selected.items[i].data.b_order_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_order_id:b_order_id};
			ajaxRequest('../bOrderController/delBOrder',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBOrder(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_order_id:record.items[0].data.b_order_id};
			ajaxRequest('../bOrderController/copyBOrder',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBOrder(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBOrderItem',
			handler:function(){addBOrder();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBOrderItem',
			handler:function(){updateBOrder();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBOrderItem',
			handler:function(){delBOrder();}
		},{
			text:'去支付',
			tooltip:'支付金额',
			glyph:0xf0d0,
			id:'addBOrderPayItem',
			handler:function(){
				addBOrderPay();
			}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBOrderItem',
			handler:function(){copyBOrder();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBOrderItem',
			handler:function(){detailBOrder();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBOrder(grid,'../bOrderController/exportBOrder');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBOrderItem','delBOrderItem','addBOrderPayItem','copyBOrderItem','detailBOrderItem']);
}


/***发票信息选择窗口***/
var bInvoiceWin;
var bInvoiceGrid;
function initBInvoiceWin(b_member_id){
	reGetWidthAndHeight();
	initBInvoiceGrid(b_member_id);
	bInvoiceWin = Ext.create('top.Ext.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'选择发票----请双击即可设置',
		items:bInvoiceGrid
	});
	bInvoiceWin.show();
}
function initBInvoiceGrid(b_member_id){
	bInvoiceStore = getGridJsonStore('../bInvoiceController/getBInvoiceListByCondition?b_member_id='+b_member_id,[]);
	bInvoiceGrid = Ext.create('top.Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:bInvoiceStore,
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
				header:'发票类型',
				locked:true,
				dataIndex:'b_invoice_type',
				renderer:function(value){
					if(value == 0){
						return "普通发票";
					}else if(value==1){
						return "增值税发票";
					}else{
						return '---';
					}
				}
			},
			{
				header:'发票名称',
				locked:true,
				dataIndex:'b_invoice_name'
			},
			{
				header:'发票公司',
				locked:true,
				dataIndex:'b_invoice_company'
			},
			{
				header:'发票号',
				locked:true,
				dataIndex:'b_invoice_num'
			},
			{
				header:'状态',
				locked:true,
				dataIndex:'b_invoice_status',
				renderer:function(value){
					if(value == 0){
						return "正常";
					}else if(value==1){
						return "已取消";
					}else{
						return '---';
					}
				}
			},
			{
				header:'开户银行',
				locked:true,
				dataIndex:'b_invoice_bank'
			},
			{
				header:'银行账号',
				locked:true,
				dataIndex:'b_invoice_bank_num'
			},
			{
				header:'联系电话',
				locked:true,
				dataIndex:'b_invoice_tel'
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
				dataIndex:'b_invoice_address'
			},
			{
				header:'创建时间',
				dataIndex:'b_invoice_ctime'
			},
			{
				header:'修改时间',
				dataIndex:'b_invoice_mtime'
			}
		],
		bbar:getGridTopBBar(bInvoiceStore),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var b_invoice_id = bInvoiceGrid.getSelectionModel().selected.items[0].data.b_invoice_id;
				var b_invoice_name = bInvoiceGrid.getSelectionModel().selected.items[0].data.b_invoice_name;
				var b_invoice_type =  bInvoiceGrid.getSelectionModel().selected.items[0].data.b_invoice_type;
				if(b_invoice_type == 0){
					b_invoice_type = "普通发票";
				}else if(b_invoice_type == 1){
					b_invoice_type = "增值税发票";
				}
				var str = "[<font color=red><br>发票名称:"+b_invoice_name+"<br>"+"发票类型:"+b_invoice_type+"<br></font>]";
				top.Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						top.Ext.getCmp('b_invoice_id').setValue(b_invoice_id);
						top.Ext.getCmp('b_invoice_name').setValue(b_invoice_name);
						bInvoiceWin.close();
						initBMemberAddressWin(b_member_id);
					}
				});
			}
		}
	});
}

/****常用地址****/
var bMemberAddressGrid;
var bMemberAddressStore;
var bMemberAddressWin;
function initBMemberAddressWin(b_member_id){
	if(null == b_member_id || "" == b_member_id){
		msgTishi("请选择会员！");
		return;
	}
	reGetWidthAndHeight();
	initBMemberAddressGrid(b_member_id);
	bMemberAddressWin = Ext.create('top.Ext.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'选择常用收货地址----请双击即可设置',
		items:bMemberAddressGrid
	});
	bMemberAddressWin.show();
}
function initBMemberAddressGrid(b_member_id){
	bMemberAddressStore = getGridJsonStore('../bMemberAddressController/getBMemberAddressListByCondition?b_member_id='+b_member_id,[]);
	bMemberAddressGrid = Ext.create('top.Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:bMemberAddressStore,
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
				header:'邮编地址',
				dataIndex:'postcode'
			},
			{
				header:'详细地址',
				flex:1,
				dataIndex:'b_member_address_detail'
			},
			{
				header:'创建时间',
				dataIndex:'b_member_address_ctime'
			},
			{
				header:'修改时间',
				dataIndex:'b_member_address_mtime'
			},
			{
				header:'是否设为常用收货地址',
				dataIndex:'b_member_address_status',
				renderer:function(value){
					if(value == 0){
						return "是";
					}else if(value==1){
						return "否";
					}else{
						return '---';
					}
				}
			}
		],
		bbar:getGridTopBBar(bMemberAddressStore),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var b_member_address_id = bMemberAddressGrid.getSelectionModel().selected.items[0].data.b_member_address_id;
				var postcode = bMemberAddressGrid.getSelectionModel().selected.items[0].data.postcode;
				var b_member_address_detail =  bMemberAddressGrid.getSelectionModel().selected.items[0].data.b_member_address_detail;
				var xt_provinceID = bMemberAddressGrid.getSelectionModel().selected.items[0].data.xt_provinceID;
				var xt_cityID = bMemberAddressGrid.getSelectionModel().selected.items[0].data.xt_cityID;
				var xt_districtID = bMemberAddressGrid.getSelectionModel().selected.items[0].data.xt_districtID;
				var xt_provinceName = bMemberAddressGrid.getSelectionModel().selected.items[0].data.xt_provinceName;
				var xt_cityName = bMemberAddressGrid.getSelectionModel().selected.items[0].data.xt_cityName;
				var xt_districtName = bMemberAddressGrid.getSelectionModel().selected.items[0].data.xt_districtName;
				var str = "[<font color=red><br>省份:"+xt_provinceName+"<br>城市:"+xt_cityName+"<br>区县:"+xt_districtName+"<br>详细地址:"+b_member_address_detail+"<br>邮编:"+postcode+"<br></font>]";
				top.Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						xtCityList.reload({params:{parentId:xt_provinceID}});
					    var parm = {parentId:xt_provinceID};
					    beforeloadstoreByStore(xtCityList,parm);
						xtDistrictList.load({params:{parentId:xt_cityID}});
					    var parms = {parentId:xt_cityID};
					    beforeloadstoreByStore(xtDistrictList,parms);
						top.Ext.getCmp('xt_cityID').setValue(xt_cityID);
						top.Ext.getCmp('xt_provinceID').setValue(xt_provinceID);
						top.Ext.getCmp('xt_districtID').setValue(xt_districtID);
						top.Ext.getCmp('b_cart_order_address_address').setValue(b_member_address_detail);
						top.Ext.getCmp('xt_districtID').setValue(xt_districtID);
						bMemberAddressWin.close();
					}
				});
			}
		}
	});
}



/**订单明细**/
function initBOrderDetailGrid(b_order_id){
	b_order_detail_store = getGridJsonStore('../bOrderDetailController/getBOrderDetailListByCondition?b_order_id='+b_order_id,[]);
	this.editing = Ext.create('top.Ext.grid.plugin.CellEditing',{
    	clicksToEdit:1
    });
	b_order_detail_grid = Ext.create('top.Ext.grid.Panel',{
		requires:[
	        'Ext.grid.plugin.CellEditing',
	        'Ext.form.field.Text',
	        'Ext.form.field.TextArea',
	        'Ext.toolbar.TextItem'
	    ],
		columnLines:true,
        multiSelect:true,
        border:true,
        plugins:[this.editing], 
        store:b_order_detail_store,
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'订单明细编号',
				dataIndex:'b_order_detail_id',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'商品编号',
				dataIndex:'b_product_id',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'商家、卖家编号',
				dataIndex:'b_seller_id',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'商家、卖家',
				dataIndex:'b_seller_name',
				flex:1
			},
			{
				header:'商品名称',
				dataIndex:'b_product_name',
				flex:1
			},
			{
				header:'分类',
				dataIndex:'b_category_name',
				flex:1
			},
			{
				header:'品牌',
				dataIndex:'b_brand_name',
				flex:1
			},
			{
				header:'购买数量（个）',
				dataIndex:'b_order_detail_number',
				flex:1,
				xtype:'numbercolumn',
                field:{
                    xtype:'numberfield',
	                allowBlank:false,
	                minValue:1,
	                maxValue:10000,
                    listeners:{
				  		'blur':function(obj){
				  			var b_order_detail_discount =  b_order_detail_grid.getSelectionModel().getSelected().items[0].data.b_order_detail_discount/100;
				  			var b_order_detail_price =  b_order_detail_grid.getSelectionModel().getSelected().items[0].data.b_order_detail_price;
				  			b_order_detail_grid.getSelectionModel().getSelected().items[0].set("b_order_detail_total_price",b_order_detail_discount*obj.value*b_order_detail_price);
				  			b_order_detail_grid.getSelectionModel().getSelected().items[0].set("b_order_detail_number",obj.value);
				  			var b_order_total_number=0;
				  			var summoney = 0.00;
				  			for(i=0;i<b_order_detail_store.data.length;i++){ 
								var record = b_order_detail_store.getAt(i);
								summoney += record.data.b_order_detail_total_price;
								b_order_total_number += record.data.b_order_detail_number;
							} 
							top.Ext.getCmp('summoney').setText("汇总金额:"+summoney+"元/单位");
							top.Ext.getCmp('b_order_total_price').setValue(summoney);
							top.Ext.getCmp('b_order_total_number').setValue(b_order_total_number);
				  		}
				  	}
                }
			},
			{
				header:'购买单价（元）',
				dataIndex:'b_order_detail_price',
				flex:1,
				readOnly:true,
                field:{
                	readOnly:true,
                    type:'textfield'
                }
			},
			{
				header:'折扣（%）',
				dataIndex:'b_order_detail_discount',
				flex:1,
				xtype:'numbercolumn',
                field:{
                    xtype:'numberfield',
	                allowBlank:false,
	                minValue:1,
	                maxValue:100,
                    listeners:{
				  		'blur':function(obj){
				  			var b_order_detail_number =  b_order_detail_grid.getSelectionModel().getSelected().items[0].data.b_order_detail_number;
				  			var b_order_detail_price =  b_order_detail_grid.getSelectionModel().getSelected().items[0].data.b_order_detail_price;
				  			b_order_detail_grid.getSelectionModel().getSelected().items[0].set("b_order_detail_total_price",b_order_detail_number*(obj.value/100)*b_order_detail_price);
				  			var b_order_total_number=0;
				  			var summoney = 0.00;
				  			for(i=0;i<b_order_detail_store.data.length;i++){ 
								var record = b_order_detail_store.getAt(i);
								summoney += record.data.b_order_detail_total_price;
								b_order_total_number+=record.data.b_order_detail_number;
							} 
							top.Ext.getCmp('summoney').setText("汇总金额:"+summoney+"元/单位");
							top.Ext.getCmp('b_order_total_price').setValue(summoney);
							top.Ext.getCmp('b_order_total_number').setValue(b_order_total_number);
				  		}
				  	}
                }
			},
			{
				header:'购买总价（元）',
				dataIndex:'b_order_detail_total_price',
				flex:1,
				/**
				summaryType:'sum',
				editor:'label',
				summaryRenderer:function (value, summaryData, dataIndex) {
		            return value;
		        },
		        **/
                field:{
                	readOnly:true,
                    type:'textfield'
                }
			},
		],
		listeners:{  
		    selectionChange:'selectionChange' 
		},
		//选中的记录发生变化过后的事件  
		selectionChange:function(view, records){  
			b_order_detail_grid.down('#del_b_order_detail_button').setDisabled(!records.length);
		},
		tbar:[
			 {
				text:'新一行',
				tooltip:'新一行',
				scope:this,
				icon:addIcon,
				handler:function(){
					initBProductWin();
				}
			 },
			 {  
                  text:'删除',  
                  disabled:true, 
                  icon:delIcon, 
                  itemId:'del_b_order_detail_button', 
                  handler:function(){
                  	var model = b_order_detail_grid.getSelectionModel();
					if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					}
					var b_order_detail_id = b_order_detail_grid.getSelectionModel().getSelected().items[0].data.b_order_detail_id;
				   	if(null == b_order_detail_id || '' == b_order_detail_id){
				   		b_order_detail_grid.getStore().remove(b_order_detail_grid.getSelectionModel().getSelection());  
				        b_order_detail_grid.getStore().sync();  
				        //重新修改值
			            var b_order_total_number=0;
		  			    var summoney = 0.00;
		  			    for(i=0;i<b_order_detail_store.data.length;i++){ 
					   		var record = b_order_detail_store.getAt(i);
							summoney += record.data.b_order_detail_total_price;
							b_order_total_number+=record.data.b_order_detail_number;
					    } 
					    top.Ext.getCmp('summoney').setText("汇总金额:"+summoney+"元/单位");
					    top.Ext.getCmp('b_order_total_price').setValue(summoney);
					    top.Ext.getCmp('b_order_total_number').setValue(b_order_total_number);
				   	}else{
					   	top.Ext.MessageBox.confirm('确定删除', '该条数据已经在数据中存在，确定要删除所选项吗？', function(btn) {  
					       if(btn == 'yes'){  
					       		var params = {b_order_detail_id:b_order_detail_id}
					       		showTopLoading("正在执行删除操作，请稍后..");
								Ext.Ajax.request({  
									params:params == null || '' == params ? {}:params, 
								    url:'../bOrderDetailController/delBOrderDetail',  
								    method:'post',  
								    success:function(response,opts){
								    	b_order_detail_grid.getStore().remove(b_order_detail_grid.getSelectionModel().getSelection());  
								    	b_order_detail_grid.getStore().sync();
								        //重新修改值
							            var b_order_total_number=0;
						  			    var summoney = 0.00;
						  			    for(i=0;i<b_order_detail_store.data.length;i++){ 
									   		var record = b_order_detail_store.getAt(i);
											summoney += record.data.b_order_detail_total_price;
											b_order_total_number+=record.data.b_order_detail_number;
									    } 
									    top.Ext.getCmp('summoney').setText("汇总金额:"+summoney+"元/单位");
									    top.Ext.getCmp('b_order_total_price').setValue(summoney);
									    top.Ext.getCmp('b_order_total_number').setValue(b_order_total_number);
									    hideTopWaitMsg();
								    },  
								    failure:function(response,opts){  
								    	hideTopWaitMsg();
								    	var obj=Ext.decode(response.responseText); 
								    }  
								});
					       }  
					    })
				   	}
                  } 
             }  
			 ],
		bbar:[
		'->',
		{
			text:'汇总金额:0.00',
			id:'summoney'
		}]
	});
}


/****选择商品****/
var bProductWin;
var bProductGrid;
var bSellerPanel;
var bSellerStore;
function initBProductWin(){
	reGetWidthAndHeight();
	initBProductGrid();
	bProductWin = Ext.create('top.Ext.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'选择出售商品----请双击即可设置',
		items:[bProductGrid]
	});
	bProductWin.show();
}

function initBProductGrid(){
	bProductStore = getGridJsonStore('../bStockController/getBStockListByCondition',[]);
	bProductGrid = Ext.create('top.Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:bProductStore,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
		selType:'checkboxmodel',
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true,
			enableTextSelection:true//可以复制单元格文字
		},
		loadMask:{
			msg:'正在加载...'
		},
		bbar:getGridTopBBar(bProductStore),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var b_seller_name = bProductGrid.getSelectionModel().selected.items[0].data.b_seller_name;
				var b_product_name = bProductGrid.getSelectionModel().selected.items[0].data.b_product_name;
				var b_product_id = bProductGrid.getSelectionModel().selected.items[0].data.b_product_id;
				var b_seller_id = bProductGrid.getSelectionModel().selected.items[0].data.b_seller_id;
				var b_category_name = bProductGrid.getSelectionModel().selected.items[0].data.b_category_name;
				var b_brand_name = bProductGrid.getSelectionModel().selected.items[0].data.b_brand_name;
				var suggested_price = bProductGrid.getSelectionModel().selected.items[0].data.suggested_price;
				var str = "[<font color=red><br>商家、卖家:"+b_seller_name+"<br>商品名称:"+b_product_name+"<br>品牌:"+b_brand_name+"<br>品类:"+b_category_name+"<br></font>]";
				top.Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						var model = Ext.create(b_order_detail_grid.getStore().model);  
					    model.set('b_seller_name',b_seller_name);  
					    model.set('b_product_name',b_product_name);  
					    model.set('b_product_id',b_product_id);  
					    model.set('b_seller_id',b_seller_id);  
					    model.set('b_category_name',b_category_name);  
					    model.set('b_brand_name',b_brand_name);  
					    model.set('b_order_detail_price',suggested_price);
					    model.set('b_order_detail_discount',100);
					    model.set('b_order_detail_number',1);
					    model.set('b_order_detail_total_price',suggested_price);
					    model.set('b_order_detail_id','');
					    b_order_detail_grid.getStore().insert(0, model);
					    var b_order_total_number=0;
			  			var summoney = 0.00;
			  			for(i=0;i<b_order_detail_store.data.length;i++){ 
							var record = b_order_detail_store.getAt(i);
							summoney += record.data.b_order_detail_total_price;
							b_order_total_number+=record.data.b_order_detail_number;
						} 
						top.Ext.getCmp('summoney').setText("汇总金额:"+summoney+"元/单位");
						top.Ext.getCmp('b_order_total_price').setValue(summoney);
						top.Ext.getCmp('b_order_total_number').setValue(b_order_total_number);
						bProductWin.close();
					}
				});
			}
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
				flex:1,
				dataIndex:'b_product_name'
			},
			{
				header:'分类',
				dataIndex:'b_category_name',
				flex:1
			},
			{
				header:'品牌',
				dataIndex:'b_brand_name',
				flex:1
			},
			{
				header:'基价',
				dataIndex:'base_price',
				flex:1
			},
			{
				header:'建议价格',
				dataIndex:'suggested_price',
				flex:1
			},
			{
				header:'原价',
				dataIndex:'original_price',
				flex:1
			},
			{
				header:'可卖数（商家所设置的数量）',
				flex:1,
				dataIndex:'b_stock_countable_sell'
			},
			{
				header:'锁定数（已卖数）',
				flex:1,
				dataIndex:'b_stock_locks_number'
			}
			]
		});
}

/**订单明细**/
function getBOrderDetailJSON(){
	///////////开始验证//////////////
	if(b_order_detail_grid.getStore().getCount() == 0){
		msgTishi("订单出售商品信息至少添加一条记录");
	    return false;
	}
	for(var i=0;i<b_order_detail_grid.getStore().getCount();i++){
		var b_seller_name = b_order_detail_grid.store.getAt(i).data.b_seller_name;
		var b_product_name = b_order_detail_grid.store.getAt(i).data.b_product_name;
		if("undefined" == typeof(b_seller_name) || null == b_seller_name || b_seller_name == ""){
			msgTishi("请选择卖家");
	        return false;
		}
		if("undefined" == typeof(b_product_name) || null == b_product_name || b_product_name == ""){
			msgTishi("请选择商品名称");
	        return false;
		}
	}
    ///////////结束验证//////////////
	var jsonArray = [];  
	for(var i=0;i<b_order_detail_grid.getStore().getCount();i++){
		var record = b_order_detail_grid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	return Ext.encode(jsonArray)
}










//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
/**去支付**/
var bOrderPayWinAdd;
var bOrderPayFormAdd;
function addBOrderPay(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi("请选择要支付的订单");
		return;
	}else if(record.length > 1){
		msgTishi("只能选择一条支付订单");
		return;
	}
	initBOrderPayFormAdd(record.items[0].data.b_order_id);
	bOrderPayWinAdd = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximized:true,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		headerPosition:'right',
		title:'支付订单金额',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:bOrderPayFormAdd,
		buttons:[{
			text:'确认支付',
			itemId:'save',
			handler:function(button){
				Ext.Msg.confirm('提示','确定要进行支付该笔订单？',function(btn){
					if(btn == 'yes'){
						submitForm(bOrderPayFormAdd,'../bOrderPayController/addBOrderPay',grid,bOrderPayWinAdd,false,true);
					}
				});
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bOrderPayWinAdd.show();
	var b_order_id = record.items[0].data.b_order_id;
	var b_order_key = record.items[0].data.b_order_key;
	var b_order_total_price = record.items[0].data.b_order_total_price;
	var b_order_from = record.items[0].data.b_order_from;
	var b_order_total_number = record.items[0].data.b_order_total_number;
	var b_member_name = record.items[0].data.b_member_name;
	var b_member_id = record.items[0].data.b_member_id;
	var b_order_type = record.items[0].data.b_order_type;
	Ext.getCmp('b_order_id').setValue(b_order_id);
	Ext.getCmp('b_order_key').setValue(b_order_key);
	Ext.getCmp('b_order_from').setValue(b_order_from);
	Ext.getCmp('b_order_total_number').setValue(b_order_total_number);
	Ext.getCmp('b_member_name').setValue(b_member_name);
	Ext.getCmp('b_member_id').setValue(b_member_id);
	Ext.getCmp('b_order_type').setValue(b_order_type);
	new Ext.util.DelayedTask(function(){  
        var summoney = 0.00;
        var totalPrice= 0.00;
		for(i=0;i<bOrderPaystore.data.length;i++){ 
			var record = bOrderPaystore.getAt(i);
			summoney += record.data.b_order_pay_money;
		} 
		if(b_order_total_price-summoney > 0){
			totalPrice = b_order_total_price-summoney;
		}
		Ext.getCmp('b_order_total_price').setTitle("此次订单应付的总金额:"+totalPrice+"元");
    }).delay(500);
}
function initBOrderPayFormAdd(b_order_id){
	initBOrderPayGrid(b_order_id);
	bOrderPayFormAdd = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		/**新方法使用结束**/
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
			{
				xtype:'panel',
				bodyStyle:'background:#35baf6;padding:10px 0 0 0;margin:0px 0 0 0',
				items:[{
					xtype:"fieldcontainer",
					anchor:'100%',
					layout:'hbox',
					items:[
					{
						labelWidth:180,
						labelSeparator:'<font color="white">:</font>',
						fieldLabel:'&nbsp;&nbsp;<font color="white">填写您此次支付的金额&nbsp;&nbsp;</font>',
						xtype:'numberfield',
						emptyText:'请输入支付金额',
						id:'b_order_pay_money',
						name:'b_order_pay_money',
						allowBlank:false
					},
			        {
			            xtype:"displayfield",
			            value:'<font color="white">元</font>'
			        }]
				}
				]
			},
			{
			xtype:'fieldset',
			title:'订单信息',
			border:false,
			/**
			disabled:true,
			**/
			items:[
			{
				xtype:"fieldcontainer",
				anchor:'50%',
				layout:'hbox',
				items:[{
					fieldLabel:'订单编号',
					xtype:'textfield',
					id:'b_order_id',
					name:'b_order_id',
					allowBlank:false,
					hidden:true,
					maxLength:32,
					anchor:'100%'
				},
				{
					fieldLabel:'订单单号',
					xtype:'textfield',
					readOnly:true,
					id:'b_order_key',
					name:'b_order_key',
					allowBlank:false,
					maxLength:32,
					anchor:'20%'
				},
				{
					fieldLabel:'会&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;员',
					xtype:'textfield',
					readOnly:true,
					id:'b_member_name',
					name:'b_member_name',
					allowBlank:false,
					maxLength:32,
					anchor:'20%'
				}]
			},
			{
				xtype:"fieldcontainer",
				anchor:'50%',
				layout:'hbox',
				items:[{
					fieldLabel:'来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源',
					xtype:"combo",
		            store:[["0","普通订单"],["1","团购订单"],["2","其他"]],
		            emptyText:"请选择",
		            mode:"local",
		            value:'0',
		            readOnly:true,
		            triggerAction:"all",
		            editable:false,
		            id:'b_order_from',
		            name:'b_order_from',
					hiddenName:'b_order_from',
					allowBlank:false,
					anchor:'20%'
				},
				{
					fieldLabel:'订单类型',
					name:'b_order_type',
					xtype:"combo",
		            store:[["0","待支付"],["1","已支付"],["2","支付部分金额"]],
		            emptyText:"请选择",
		            mode:"local",
		            value:'0',
		            readOnly:true,
		            triggerAction:"all",
		            editable:false,
		            id:'b_order_type',
					hiddenName:'b_order_type',
					allowBlank:false,
					anchor:'20%'
				}]
			},
			{
				fieldLabel:'会员编号',
				xtype:'textfield',
				name:'b_member_id',
				id:'b_member_id',
				hidden:true,
				allowBlank:false,
				maxLength:32,
				anchor:'100%'
			},
			{
				xtype:"fieldcontainer",
				anchor:'50%',
				layout:'hbox',
				items:[{
					fieldLabel:'购买数量',
					xtype:'numberfield',
					value:'0',
					readOnly:true,
					id:'b_order_total_number',
					name:'b_order_total_number',
					allowBlank:false,
					anchor:'20%'
				},
				{
					xtype:"displayfield",
			        value:'<font color="red">个</font>'
				}]
			}]
		},
		{
			xtype:'panel',
			bodyStyle:'background:#157fcc;padding:0px 0 0 0;margin:0px 0 0 0',
			readOnly:true,
			id:'b_order_total_price'
		},
		{
			xtype:'fieldset',
			title:'支付记录',
			border:false,
			items:[bOrderPayGrid]
		}
		]
	});
}