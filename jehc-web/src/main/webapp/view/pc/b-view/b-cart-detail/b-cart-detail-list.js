var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	var items = {};
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bCartDetailController/getBCartDetailListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
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
				header:'购物车明细编号',
				dataIndex:'b_cart_detail_id'
			},
			{
				header:'商品编号',
				dataIndex:'b_product_id'
			},
			{
				header:'卖家编号',
				dataIndex:'b_seller_id'
			},
			{
				header:'购物数量',
				dataIndex:'b_cart_detail_number'
			},
			{
				header:'购买单价',
				dataIndex:'b_cart_detail_price'
			},
			{
				header:'折扣',
				dataIndex:'b_cart_detail_discount'
			},
			{
				header:'创建时间',
				dataIndex:'b_cart_detail_ctime'
			},
			{
				header:'修改时间',
				dataIndex:'b_cart_detail_mtime'
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
					addBCartDetail();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBCartDetail();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBCartDetail();
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
						copyBCartDetail();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBCartDetail();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBCartDetail(grid,'../bCartDetailController/exportBCartDetail');
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
				detailBCartDetail();
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
});
/**删除操作**/
function delBCartDetail(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_cart_detail_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_cart_detail_id){
			b_cart_detail_id=model.selected.items[i].data.b_cart_detail_id;
		}else{
			b_cart_detail_id=b_cart_detail_id+","+model.selected.items[i].data.b_cart_detail_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_cart_detail_id:b_cart_detail_id};
			ajaxRequest('../bCartDetailController/delBCartDetail',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBCartDetail(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_cart_detail_id:record.items[0].data.b_cart_detail_id};
			ajaxRequest('../bCartDetailController/copyBCartDetail',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBCartDetail(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBCartDetailItem',
			handler:function(){addBCartDetail();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBCartDetailItem',
			handler:function(){updateBCartDetail();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBCartDetailItem',
			handler:function(){delBCartDetail();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBCartDetailItem',
			handler:function(){copyBCartDetail();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBCartDetailItem',
			handler:function(){detailBCartDetail();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBCartDetail(grid,'../bCartDetailController/exportBCartDetail');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBCartDetailItem','delBCartDetailItem','copyBCartDetailItem','detailBCartDetailItem']);
}
