var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	b_seller_official_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","是"],["1","否"]]
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
			fieldLabel:'卖家电话',
			labelWidth:70,
			emptyText:'请输入',
			name:'b_seller_tel',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'是否官方商家',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:b_seller_official_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_seller_official',
			valueField:'value',
			displayField:'text',
			name:'b_seller_official',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bSellerController/getBSellerListByCondition',[]);
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
				header:'卖家名称',
				locked:true,
				dataIndex:'b_seller_name'
			},
			{
				header:'卖家电话',
				locked:true,
				dataIndex:'b_seller_tel'
			},
			{
				header:'是否官方商店',
				locked:true,
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
				locked:true,
				dataIndex:'b_seller_level'
			},
			{
				header:'状态',
				locked:true,
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
			},
			{
				header:'创建时间',
				dataIndex:'b_seller_ctime'
			},
			{
				header:'修改时间',
				dataIndex:'b_seller_mtime'
			},
			{
				header:'创建人',
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'操作',
				align:'center',
				xtype:'widgetcolumn',
				width:140,
				locked:true,
				widget:{
					xtype:'splitbutton',
	                text:'设置其他属性',
	                icon:listIcon,
	                width:140,
					menu:[{
						text:'登录账号',
						glyph:0xf007,
						width:140,
						handler:function(rec){
                			reGetWidthAndHeight();
			            	var b_seller_id = rec.up('splitbutton').getWidgetRecord().data.b_seller_id;
			        		var bSellerLoginWin = Ext.create('Ext.Window',{
								layout:'fit',
								width:clientWidth,                    
								height:clientHeight, 
								//maximized:true,
								maximizable:true,
								minimizable:true,
								animateTarget:document.body,
								plain:true,
								modal:true,
								title:'登陆账号',
								listeners:{
									minimize:function(win,opts){
										win.collapse();
									}
								},
								html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../bSellerLoginController/loadBSellerLogin?b_seller_id='+b_seller_id+'"></iframe>'
							});
							bSellerLoginWin.show();
			        	}
				    },
				    '-',
				  	{
						text:'账户信息',
						glyph:0xf0f0,
						handler:function(rec){
			            	var b_seller_id = rec.up('splitbutton').getWidgetRecord().data.b_seller_id;
			        	}
				  	},
				    '-',
				  	{
						text:'商户商品',
						glyph:0xf0f4,
						handler:function(rec){
			            	reGetWidthAndHeight();
			            	var b_seller_id = rec.up('splitbutton').getWidgetRecord().data.b_seller_id;
			        		var bSellerProductWin = Ext.create('Ext.Window',{
								layout:'fit',
								width:clientWidth,                    
								height:clientHeight, 
								//maximized:true,
								maximizable:true,
								minimizable:true,
								animateTarget:document.body,
								plain:true,
								modal:true,
								title:'商户商品信息',
								listeners:{
									minimize:function(win,opts){
										win.collapse();
									}
								},
								html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../bSellerProductController/loadBSellerProduct?b_seller_id='+b_seller_id+'"></iframe>'
							});
							bSellerProductWin.show();
			        	}
				  	},
				    '-',
				  	{
						text:'快递配置',
						glyph:0xf0d1,
						handler:function(rec){
							reGetWidthAndHeight();
			            	var b_seller_id = rec.up('splitbutton').getWidgetRecord().data.b_seller_id;
			            	var bSellerExpressWin = Ext.create('Ext.Window',{
								layout:'fit',
								width:clientWidth,                    
								height:clientHeight, 
								//maximized:true,
								maximizable:true,
								minimizable:true,
								animateTarget:document.body,
								plain:true,
								modal:true,
								title:'配置卖家送货快递公司',
								listeners:{
									minimize:function(win,opts){
										win.collapse();
									}
								},
								html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../bSellerExpressController/loadBSellerExpress?b_seller_id='+b_seller_id+'"></iframe>'
							});
							bSellerExpressWin.show();
			        	}
				  	},
				    '-',
				  	{
						text:'商户联系人管理',
						glyph:0xf0c0,
						handler:function(rec){
							reGetWidthAndHeight();
			            	var b_seller_id = rec.up('splitbutton').getWidgetRecord().data.b_seller_id;
			        		var bSellerContactsWin = Ext.create('Ext.Window',{
								layout:'fit',
								width:clientWidth,                    
								height:clientHeight, 
								//maximized:true,
								maximizable:true,
								minimizable:true,
								animateTarget:document.body,
								plain:true,
								modal:true,
								title:'商户[卖家]联系人信息',
								listeners:{
									minimize:function(win,opts){
										win.collapse();
									}
								},
								html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../bSellerContactsController/loadBSellerContacts?b_seller_id='+b_seller_id+'"></iframe>'
							});
							bSellerContactsWin.show();
			        	}
				  	}]
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
					addBSeller();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBSeller();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBSeller();
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
						copyBSeller();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBSeller();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBSeller(grid,'../bSellerController/exportBSeller');
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
				detailBSeller();
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
function delBSeller(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_seller_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_seller_id){
			b_seller_id=model.selected.items[i].data.b_seller_id;
		}else{
			b_seller_id=b_seller_id+","+model.selected.items[i].data.b_seller_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_seller_id:b_seller_id};
			ajaxRequest('../bSellerController/delBSeller',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBSeller(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_seller_id:record.items[0].data.b_seller_id};
			ajaxRequest('../bSellerController/copyBSeller',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBSeller(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBSellerItem',
			handler:function(){addBSeller();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBSellerItem',
			handler:function(){updateBSeller();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBSellerItem',
			handler:function(){delBSeller();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBSellerItem',
			handler:function(){copyBSeller();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBSellerItem',
			handler:function(){detailBSeller();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBSeller(grid,'../bSellerController/exportBSeller');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBSellerItem','delBSellerItem','copyBSellerItem','detailBSellerItem']);
}
