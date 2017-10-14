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
	/**查询区域可扩展**/
	b_member_type_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","普通会员"],["1","VIP会员"]]
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
			fieldLabel:'会员名称',
			labelWidth:70,
			emptyText:'请选择',
			name:'b_member_name',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'会员电话',
			labelWidth:70,
			emptyText:'请输入',
			name:'b_member_tel',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:b_member_type_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_member_type',
			valueField:'value',
			displayField:'text',
			name:'b_member_type',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bMemberController/getBMemberListByCondition',[]);
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
		/**
        lockedGridConfig:{
            header:false,
            collapsible:true,
            width:400,
            forceFit:true
        },
        lockedViewConfig:{
            scroll:'horizontal'
        },
        **/
		columns:[
			{
				header:'序号',
				width:77,
				xtype:'rownumberer'
			},
			{
				header:'会员名称',
				locked:true,
				dataIndex:'b_member_name'
			},
			{
				header:'性别',
				dataIndex:'b_member_sex',
				renderer:function(value){
					if(value == 0){
						return "男";
					}else if(value==1){
						return "女";
					}else{
						return '---';
					}
				}
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
				dataIndex:'xt_districtName',
				renderer:function(value){
					if(value == '' || value == null){
						return "---";
					}else{
						return value;
					}
				}
			},
			{
				header:'会员电话',
				dataIndex:'b_member_tel'
			},
			{
				header:'状态',
				dataIndex:'b_member_status',
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
				header:'等级',
				dataIndex:'b_member_level'
			},
			{
				header:'类型',
				dataIndex:'b_member_type',
				renderer:function(value){
					if(value == 0){
						return "普通会员";
					}else if(value==1){
						return "VIP会员";
					}else{
						return '---';
					}
				}
			},
			{
				header:'注册时间',
				dataIndex:'b_member_ctime'
			},
			{
				header:'最后修改时间',
				dataIndex:'b_member_mtime'
			},
			{
				header:'操作',
				flex:1,
				columns:[{
					header:'设置常用送货地址',
					align:'center',
					xtype:'widgetcolumn',
					width:150,
					widget:{
		                xtype:'button',
		                text:'设置常用送货地址',
		                icon:addIcon,
		                handler:function(rec){	
		  					var b_member_id = rec.getWidgetRecord().data.b_member_id;
		  					document.location.href="../bMemberAddressController/loadBMemberAddress?b_member_id="+b_member_id;
					    }
		            }
				},
				{
					header:'设置发票信息',
					align:'center',
					xtype:'widgetcolumn',
					width:150,
					widget:{
		                xtype:'button',
		                text:'设置发票信息',
		                icon:addIcon,
		                handler:function(rec){	
		  					var b_member_id = rec.getWidgetRecord().data.b_member_id;
		  					document.location.href="../bInvoiceController/loadBInvoice?b_member_id="+b_member_id;
					    }
		            }
				}
				]
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
					addBMember();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBMember();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBMember();
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
						copyBMember();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBMember();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBMember(grid,'../bMemberController/exportBMember');
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
					minWidth:tbarBtnMinWidth,
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
				detailBMember();
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
function delBMember(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_member_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_member_id){
			b_member_id=model.selected.items[i].data.b_member_id;
		}else{
			b_member_id=b_member_id+","+model.selected.items[i].data.b_member_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_member_id:b_member_id};
			ajaxRequest('../bMemberController/delBMember',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBMember(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_member_id:record.items[0].data.b_member_id};
			ajaxRequest('../bMemberController/copyBMember',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBMember(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBMemberItem',
			handler:function(){addBMember();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBMemberItem',
			handler:function(){updateBMember();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBMemberItem',
			handler:function(){delBMember();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBMemberItem',
			handler:function(){copyBMember();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBMemberItem',
			handler:function(){detailBMember();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBMember(grid,'../bMemberController/exportBMember');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBMemberItem','delBMemberItem','copyBMemberItem','detailBMemberItem']);
}
