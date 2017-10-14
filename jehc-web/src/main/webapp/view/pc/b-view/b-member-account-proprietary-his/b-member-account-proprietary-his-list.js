var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	var items = {};
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bMemberAccountProprietaryHisController/getBMemberAccountProprietaryHisListByCondition',[]);
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
				header:'专有账户充值记录',
				dataIndex:'b_member_account_proprietary_his_id'
			},
			{
				header:'专有账户编号',
				dataIndex:'b_member_account_proprietary_id'
			},
			{
				header:'充值金额',
				dataIndex:'b_member_account_proprietary_his_money'
			},
			{
				header:'充值时间',
				dataIndex:'b_member_account_proprietary_his_ctime'
			},
			{
				header:'充值方式0在线支付1线下支付2其他方式',
				dataIndex:'b_member_account_proprietary_his_type'
			},
			{
				header:'备注',
				dataIndex:'b_member_account_proprietary_his_log'
			},
			{
				header:'创建日期',
				dataIndex:'b_member_id'
			},
			{
				header:'平台操作人',
				dataIndex:'xt_userinfo_id'
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
					addBMemberAccountProprietaryHis();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBMemberAccountProprietaryHis();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBMemberAccountProprietaryHis();
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
						copyBMemberAccountProprietaryHis();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBMemberAccountProprietaryHis();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBMemberAccountProprietaryHis(grid,'../bMemberAccountProprietaryHisController/exportBMemberAccountProprietaryHis');
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
				detailBMemberAccountProprietaryHis();
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
function delBMemberAccountProprietaryHis(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_member_account_proprietary_his_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_member_account_proprietary_his_id){
			b_member_account_proprietary_his_id=model.selected.items[i].data.b_member_account_proprietary_his_id;
		}else{
			b_member_account_proprietary_his_id=b_member_account_proprietary_his_id+","+model.selected.items[i].data.b_member_account_proprietary_his_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_member_account_proprietary_his_id:b_member_account_proprietary_his_id};
			ajaxRequest('../bMemberAccountProprietaryHisController/delBMemberAccountProprietaryHis',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBMemberAccountProprietaryHis(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_member_account_proprietary_his_id:record.items[0].data.b_member_account_proprietary_his_id};
			ajaxRequest('../bMemberAccountProprietaryHisController/copyBMemberAccountProprietaryHis',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBMemberAccountProprietaryHis(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBMemberAccountProprietaryHisItem',
			handler:function(){addBMemberAccountProprietaryHis();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBMemberAccountProprietaryHisItem',
			handler:function(){updateBMemberAccountProprietaryHis();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBMemberAccountProprietaryHisItem',
			handler:function(){delBMemberAccountProprietaryHis();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBMemberAccountProprietaryHisItem',
			handler:function(){copyBMemberAccountProprietaryHis();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBMemberAccountProprietaryHisItem',
			handler:function(){detailBMemberAccountProprietaryHis();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBMemberAccountProprietaryHis(grid,'../bMemberAccountProprietaryHisController/exportBMemberAccountProprietaryHis');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBMemberAccountProprietaryHisItem','delBMemberAccountProprietaryHisItem','copyBMemberAccountProprietaryHisItem','detailBMemberAccountProprietaryHisItem']);
}
