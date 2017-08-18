var store;
var grid;
var B_PRODUCT_IMG_STATUS_COMBO_STORE;
var B_PRODUCT_IMG_TYPE_COMBO_STORE;
Ext.onReady(function(){
	B_PRODUCT_IMG_STATUS_COMBO_STORE = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
        data:[["0","正常"],["1","禁用"]]
	});
	B_PRODUCT_IMG_TYPE_COMBO_STORE = Ext.create('Ext.data.SimpleStore',{ 
	     fields:['value','text'],  
		 data:[["0","大图片"],["1","小图片"]]
	});
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
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
			fieldLabel:'图片名称',
			xtype:'textfield',
			labelWidth:70,
			id:'b_product_img_name',
			name:'b_product_img_name',
			anchor:'20%',
			labelAlign:'top'
		},
		{
			fieldLabel:'b_product_id',
			xtype:'textfield',
			hidden:true,
			labelWidth:70,
			value:$('#b_product_id_').val(),
			name:'b_product_id',
			anchor:'20%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:B_PRODUCT_IMG_STATUS_COMBO_STORE,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_product_img_status',
			valueField:'value',
			displayField:'text',
			id:'b_product_img_status',
			name:'b_product_img_status',
			anchor:'20%',
			labelAlign:'top'
		},
		{
			fieldLabel:'图片类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:B_PRODUCT_IMG_TYPE_COMBO_STORE,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_product_img_type',
			valueField:'value',
			displayField:'text',
			id:'b_product_img_type',
			name:'b_product_img_type',
			anchor:'20%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bProductImgController/getBProductImgListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'商家所选图片--查询结果',
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
				header:'商品图片名称',
				dataIndex:'b_product_img_name'
			},
			{
				header:'商品图片宽度',
				dataIndex:'b_product_img_width'
			},
			{
				header:'商品图片高度',
				dataIndex:'b_product_img_height'
			},
			{
				header:'创建时间',
				dataIndex:'b_product_img_ctime'
			},
			{
				header:'修改时间',
				dataIndex:'b_product_img_mtime'
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
					addBProductImg();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBProductImg();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBProductImg();
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
			 {
				text:'返回商品列表',
				tooltip:'返回商品列表',
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../bProductController/loadBProduct";
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
						copyBProductImg();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBProductImg();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBProductImg(grid,'../bProductImgController/exportBProductImg');
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
				detailBProductImg();
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
function delBProductImg(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_product_img_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_product_img_id){
			b_product_img_id=model.selected.items[i].data.b_product_img_id;
		}else{
			b_product_img_id=b_product_img_id+","+model.selected.items[i].data.b_product_img_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_product_img_id:b_product_img_id};
			ajaxRequest('../bProductImgController/delBProductImg',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBProductImg(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_product_img_id:record.items[0].data.b_product_img_id};
			ajaxRequest('../bProductImgController/copyBProductImg',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBProductImg(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBProductImgItem',
			handler:function(){addBProductImg();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBProductImgItem',
			handler:function(){updateBProductImg();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBProductImgItem',
			handler:function(){delBProductImg();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBProductImgItem',
			handler:function(){copyBProductImg();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBProductImgItem',
			handler:function(){detailBProductImg();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBProductImg(grid,'../bProductImgController/exportBProductImg');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBProductImgItem','delBProductImgItem','copyBProductImgItem','detailBProductImgItem']);
}
/**查询操作**/
function search(){
	var b_product_img_name = Ext.getCmp("b_product_img_name").getValue();
	var b_product_img_status = Ext.getCmp("b_product_img_status").getValue();
	var b_product_img_type = Ext.getCmp("b_product_img_type").getValue();
	store.load({
		url:'../bProductImgController/getBProductImgListByCondition',
		params:{
			start:0,
			limit:getGridBBar(store).pageSize,
			b_product_img_name:b_product_img_name,
			b_product_img_status:b_product_img_status,
			b_product_img_type:b_product_img_type
		}
	});
}

/**初始化商户、卖家列表**/
var bsellerWin;
var bsellerstore;
var bsellergrid;
function initBsellerGrid(){
	bsellerstore = getGridJsonStore('../bSellerController/getBSellerListByCondition',[]);
	bsellergrid = Ext.create('top.Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:bsellerstore,
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
			}
		],
		bbar:getGridTopBBar(bsellerstore),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var b_seller_name = bsellergrid.getSelectionModel().selected.items[0].data.b_seller_name;
				var b_seller_id = bsellergrid.getSelectionModel().selected.items[0].data.b_seller_id;
				var str = "[<font color=red><br>商家、卖家:"+b_seller_name+"<br></font>]";
				top.Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						Ext.getCmp('b_seller_name').setValue(b_seller_name);
						Ext.getCmp('b_seller_id').setValue(b_seller_id);
						bsellerWin.close();
					}
				});
			}
		}
	});
	reGetWidthAndHeight();
	bsellerWin = Ext.create('top.Ext.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'选择商户、卖家----请双击即可设置',
		items:[bsellergrid]
	});
	bsellerWin.show();
}
