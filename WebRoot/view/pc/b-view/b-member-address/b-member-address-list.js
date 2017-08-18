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
	store = getGridJsonStore('../bMemberAddressController/getBMemberAddressListByCondition?b_member_id='+$('#b_member_id').val(),[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'会员常用地址',
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
		tbar:[
			 {
				text:'添加',
				tooltip:'添加',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addBMemberAddress();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBMemberAddress();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBMemberAddress();
				}
			 },
			 {
				text:'返回会员列表',
				tooltip:'返回会员列表',
				cls:'backBtn',
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
						copyBMemberAddress();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBMemberAddress();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBMemberAddress(grid,'../bMemberAddressController/exportBMemberAddress');
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
				detailBMemberAddress();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
	/**调用右键**/
	initRight();
});
/**删除操作**/
function delBMemberAddress(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_member_address_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_member_address_id){
			b_member_address_id=model.selected.items[i].data.b_member_address_id;
		}else{
			b_member_address_id=b_member_address_id+","+model.selected.items[i].data.b_member_address_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_member_address_id:b_member_address_id};
			ajaxRequest('../bMemberAddressController/delBMemberAddress',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBMemberAddress(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_member_address_id:record.items[0].data.b_member_address_id};
			ajaxRequest('../bMemberAddressController/copyBMemberAddress',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBMemberAddress(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBMemberAddressItem',
			handler:function(){addBMemberAddress();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBMemberAddressItem',
			handler:function(){updateBMemberAddress();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBMemberAddressItem',
			handler:function(){delBMemberAddress();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBMemberAddressItem',
			handler:function(){copyBMemberAddress();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBMemberAddressItem',
			handler:function(){detailBMemberAddress();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBMemberAddress(grid,'../bMemberAddressController/exportBMemberAddress');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBMemberAddressItem','delBMemberAddressItem','copyBMemberAddressItem','detailBMemberAddressItem']);
}
