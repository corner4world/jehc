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
			fieldLabel:'姓名',
			labelWidth:70,
			emptyText:'请输入',
			name:'b_seller_contacts_uname',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'电话',
			labelWidth:70,
			emptyText:'请输入',
			name:'b_seller_contacts_tel',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'b_seller_id',
			labelWidth:70,
			name:'b_seller_id',
			anchor:'30%',
			hidden:true,
			value:$('#b_seller_id').val(),
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bSellerContactsController/getBSellerContactsListByCondition',[]);
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
				header:'姓名',
				dataIndex:'b_seller_contacts_uname'
			},
			{
				header:'电话',
				flex:1,
				dataIndex:'b_seller_contacts_tel'
			},
			{
				header:'性别',
				dataIndex:'b_seller_contacts_sex',
				renderer:function(value){
					if(value == 0){
						return "男";
					}else if(value==1){
						return "女";
					}
				}
			},
			{
				header:'类型',
				dataIndex:'b_seller_contacts_type',
				renderer:function(value){
					if(value == 0){
						return "普通员工";
					}else if(value==1){
						return "法人";
					}else if(value == 2){
						return '经理';
					}else if(value == 3){
						return '主管';
					}
				}
			},
			{
				header:'状态',
				dataIndex:'b_seller_contacts_turnover',
				renderer:function(value){
					if(value == 0){
						return "正常";
					}else if(value==1){
						return "离职";
					}
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
					addBSellerContacts();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBSellerContacts();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBSellerContacts();
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
						copyBSellerContacts();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBSellerContacts();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBSellerContacts(grid,'../bSellerContactsController/exportBSellerContacts');
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
					scope:this,
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
				detailBSellerContacts();
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
function delBSellerContacts(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_seller_contacts_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_seller_contacts_id){
			b_seller_contacts_id=model.selected.items[i].data.b_seller_contacts_id;
		}else{
			b_seller_contacts_id=b_seller_contacts_id+","+model.selected.items[i].data.b_seller_contacts_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_seller_contacts_id:b_seller_contacts_id};
			ajaxRequest('../bSellerContactsController/delBSellerContacts',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBSellerContacts(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_seller_contacts_id:record.items[0].data.b_seller_contacts_id};
			ajaxRequest('../bSellerContactsController/copyBSellerContacts',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBSellerContacts(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBSellerContactsItem',
			handler:function(){addBSellerContacts();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBSellerContactsItem',
			handler:function(){updateBSellerContacts();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBSellerContactsItem',
			handler:function(){delBSellerContacts();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBSellerContactsItem',
			handler:function(){copyBSellerContacts();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBSellerContactsItem',
			handler:function(){detailBSellerContacts();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBSellerContacts(grid,'../bSellerContactsController/exportBSellerContacts');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBSellerContactsItem','delBSellerContactsItem','copyBSellerContactsItem','detailBSellerContactsItem']);
}
