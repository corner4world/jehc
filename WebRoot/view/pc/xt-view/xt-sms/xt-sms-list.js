var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../xtSmsController/getXtSmsListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
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
				header:'用户名',
				dataIndex:'xt_smsName'
			},
			{
				header:'公司',
				flex:1,
				dataIndex:'xt_smsCompany'
			},
			{
				header:'联系人',
				dataIndex:'xt_smsCompanyContacts'
			},
			{
				header:'电话',
				dataIndex:'xt_smsCompanTel'
			},
			{
				header:'短信协议类型',
				dataIndex:'xt_smsType',
				renderer:function(value){
					if(value == 0){
						return "http";
					}else{
						return "其他";
					}
				}
			},
			{
				header:'状态',
				dataIndex:'xt_smsState',
				renderer:function(value){
					if(value == 0){
						return "正常";
					}else{
						return "启用";
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
					addXtSms();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtSms();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtSms();
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
						copyXtSms();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtSms();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtSms(grid,'../xtSmsController/exportXtSms');
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
				detailXtSms();
			}
		}
	});
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtSms(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_sms_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_sms_id){
			xt_sms_id=model.selected.items[i].data.xt_sms_id;
		}else{
			xt_sms_id=xt_sms_id+","+model.selected.items[i].data.xt_sms_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_sms_id:xt_sms_id};
			ajaxRequest('../xtSmsController/delXtSms',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtSms(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_sms_id:record.items[0].data.xt_sms_id};
			ajaxRequest('../xtSmsController/copyXtSms',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtSms(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtSmsItem',
			handler:function(){addXtSms();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtSmsItem',
			handler:function(){updateXtSms();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtSmsItem',
			handler:function(){delXtSms();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtSmsItem',
			handler:function(){copyXtSms();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtSmsItem',
			handler:function(){detailXtSms();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtSms(grid,'../xtSmsController/exportXtSms');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtSmsItem','delXtSmsItem','copyXtSmsItem','detailXtSmsItem']);
}
