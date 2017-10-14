var store;
var grid;
var xtProvinceList;
var xtCityList;
var xtDistrictList;
Ext.onReady(function(){
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
	store = getGridJsonStore('../bInvoiceController/getBInvoiceListByCondition?b_member_id='+$('#b_member_id').val(),[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'会员发票',
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
		tbar:[
			 {
				text:'添加',
				tooltip:'添加',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addBInvoice();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBInvoice();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBInvoice();
				}
			 },
			 {
				text:'返回会员列表',
				tooltip:'返回会员列表',
				icon:backIcon,
				handler:function(){
					document.location.href="../bMemberController/loadBMember";
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
						copyBInvoice();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBInvoice();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBInvoice(grid,'../bInvoiceController/exportBInvoice');
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
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailBInvoice();
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
function delBInvoice(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_invoice_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_invoice_id){
			b_invoice_id=model.selected.items[i].data.b_invoice_id;
		}else{
			b_invoice_id=b_invoice_id+","+model.selected.items[i].data.b_invoice_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_invoice_id:b_invoice_id};
			ajaxRequest('../bInvoiceController/delBInvoice',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBInvoice(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_invoice_id:record.items[0].data.b_invoice_id};
			ajaxRequest('../bInvoiceController/copyBInvoice',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBInvoice(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBInvoiceItem',
			handler:function(){addBInvoice();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBInvoiceItem',
			handler:function(){updateBInvoice();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBInvoiceItem',
			handler:function(){delBInvoice();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBInvoiceItem',
			handler:function(){copyBInvoice();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBInvoiceItem',
			handler:function(){detailBInvoice();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBInvoice(grid,'../bInvoiceController/exportBInvoice');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBInvoiceItem','delBInvoiceItem','copyBInvoiceItem','detailBInvoiceItem']);
}
