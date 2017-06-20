var store;
var grid;
var contextmenu;
var XT_NOTICE_COMBO_STORE;
Ext.onReady(function(){
	XT_NOTICE_COMBO_STORE = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","初稿"],["1","审核中"],["2","审核通过"],["3","审核未通过"]]
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
			fieldLabel:'标题',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_title',
			name:'xt_title',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:XT_NOTICE_COMBO_STORE,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_state',
			valueField:'value',
			displayField:'text',
			id:'xt_state',
			name:'xt_state',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtNoticeController/getXtNoticeListByCondition',[]);
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
				header:'标题',
				flex:1,
				dataIndex:'xt_title'
			},
			{
				header:'状态',
				dataIndex:'xt_state',
				renderer:function(value){
					return initComBoData(XT_NOTICE_COMBO_STORE,value,'value','text');
				}
			},
			{
				header:'创建时间',
				dataIndex:'xt_createTime'
			},
			{
				header:'创建人',
				dataIndex:'xt_userinfo_realName'
			}
		],
		tbar:[
			 {
				text:'添加',
				tooltip:'添加',
				id:'addXtNotice',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addXtNotice();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				id:'updateXtNotice',
				icon:editIcon,
				handler:function(){
					updateXtNotice();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				id:'delXtNotice',
				handler:function(){
					delXtNotice();
				}
			 },
			 {
				text:'检索',
				tooltip:'检索',
				minWidth:tbarBtnMinWidth,
				authUneed:true,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					search();
				}
			 },
			 {
				text:'重置',
				tooltip:'重置',
				authUneed:true,
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
				 authUneed:true,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					authUneed:true,
					handler:function(){
						detailXtNotice();
					}
				 },
				 '-',
				 {
					text:'打 印',
					tooltip:'打 印',
					glyph:0xf02f,
					authUneed:true,
					handler:function(){
						printerGrid(grid);
					}
				 },
				 '-',
				 {
					text:'全 选',
					tooltip:'全 选',
					glyph:0xf046,
					authUneed:true,
					handler:function(){selectAll(grid);}
				 },
				 {
					text:'反 选',
					tooltip:'反 选',
					glyph:0xf096,
					authUneed:true,
					handler:function(){unSelectAll(grid);}
				 },
				 '-',
				 {
					text:'刷 新',
					tooltip:'刷 新',
					glyph:0xf021,
					authUneed:true,
					handler:function(){
						grid.getStore().reload();
					}
				 },
				 {
					text:'检 索',
					tooltip:'检 索',
					glyph:0xf002,
					authUneed:true,
					handler:function(){
						search();
					}
				 },
				 {
					text:'重 置',
					tooltip:'重 置',
					glyph:0xf014,
					authUneed:true,
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
				detailXtNotice();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	/**重新设置列值**/
	initDataGrid(grid);
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
	/**调用功能权限**/
	xtFunctionStrByCondition(store,grid,contextmenu);
});
/**删除操作**/
function delXtNotice(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_notice_id;
	var systemUID="";
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_notice_id){
			xt_notice_id=model.selected.items[i].data.xt_notice_id;
		}else{
			xt_notice_id=xt_notice_id+","+model.selected.items[i].data.xt_notice_id;
		}
		if(systemUID == ""){
			systemUID = model.selected.items[i].data.xt_userinfo_id;
		}else{
			systemUID = systemUID + "," + model.selected.items[i].data.xt_userinfo_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_notice_id:xt_notice_id,systemUID:systemUID};
			ajaxRequest('../xtNoticeController/delXtNotice',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtNotice(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_notice_id:record.items[0].data.xt_notice_id};
			ajaxRequest('../xtNoticeController/copyXtNotice',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtNotice(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtNoticeItem',
			handler:function(){addXtNotice();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtNoticeItem',
			handler:function(){updateXtNotice();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtNoticeItem',
			handler:function(){delXtNotice();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtNoticeItem',
			handler:function(){copyXtNotice();}
		},{
			text:'明 细',
			glyph:0xf03b,
			authUneed:true,
			id:'detailXtNoticeItem',
			handler:function(){detailXtNotice();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			authUneed:true,
			id:'exportXtNoticeItem',
			handler:function(){
				exportXtNotice(grid,'../xtNoticeController/exportXtNotice');
			}
		},{
			text:'打 印',
			glyph:0xf02f,
			authUneed:true,
			handler:function(){printerGrid(grid);}
		},'-',{
			text:'全 选',
			glyph:0xf046,
			authUneed:true,
			handler:function(){selectAll(grid);}
		},{
			text:'反 选',
			glyph:0xf096,
			authUneed:true,
			handler:function(){unSelectAll(grid);}
		},'-',{
			text:'刷 新',
			glyph:0xf021,
			authUneed:true,
			handler:function(){refreshGrid(grid);}
		}]
	});
	initrightgridcontextmenu(grid,contextmenu,['updateXtNoticeItem','delXtNoticeItem','copyXtNoticeItem','detailXtNoticeItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtNoticeController/getXtNoticeListByCondition',searchForm); 
}
