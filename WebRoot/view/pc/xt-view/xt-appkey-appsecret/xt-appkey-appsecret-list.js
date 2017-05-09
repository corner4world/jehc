var store;
var grid;
var Xt_Appkey_AppSecret_STATUS_COMBO;
Ext.onReady(function(){
	Xt_Appkey_AppSecret_STATUS_COMBO = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","正常"],["1","禁用"]]
	});
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		maxHeight:150,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'态状',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:Xt_Appkey_AppSecret_STATUS_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_appkey_appsecret_status',
			valueField:'value',
			displayField:'text',
			id:'xt_appkey_appsecret_status',
			name:'xt_appkey_appsecret_status',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'名称',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_appkey_appsecret_name',
			name:'xt_appkey_appsecret_name',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtAppkeyAppsecretController/getXtAppkeyAppsecretListByCondition',[]);
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
				header:'名称',
				flex:1,
				dataIndex:'xt_appkey_appsecret_name'
			},
			{
				header:'备注',
				flex:1,
				dataIndex:'xt_appkey_appsecret_remark'
			},
			{
				header:'状态',
				flex:1,
				dataIndex:'xt_appkey_appsecret_status',
				renderer:function(value){
					return initComBoData(Xt_Appkey_AppSecret_STATUS_COMBO,value,'value','text');
				}
			},
			{
				header:'建创时间',
				flex:1,
				dataIndex:'xt_ctime'
			},
			{
				header:'最后改修时间',
				flex:1,
				dataIndex:'xt_mtime'
			},
			{
				header:'创建人',
				flex:1,
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
					addXtAppkeyAppsecret();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtAppkeyAppsecret();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtAppkeyAppsecret();
				}
			 },
			 {
				text:'检索',
				tooltip:'检索',
				minWidth:tbarBtnMinWidth,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					search();
				}
			 },
			 {
				text:'重置',
				tooltip:'重置',
				icon:clearSearchIcon,
				minWidth:tbarBtnMinWidth,
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
						copyXtAppkeyAppsecret();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtAppkeyAppsecret();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					scope:this,
					glyph:0xf1c3,
					handler:function(){
						exportXtAppkeyAppsecret(grid,'../xtAppkeyAppsecretController/exportXtAppkeyAppsecret');
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
				detailXtAppkeyAppsecret();
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
function delXtAppkeyAppsecret(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_appkey_appsecret_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_appkey_appsecret_id){
			xt_appkey_appsecret_id=model.selected.items[i].data.xt_appkey_appsecret_id;
		}else{
			xt_appkey_appsecret_id=xt_appkey_appsecret_id+","+model.selected.items[i].data.xt_appkey_appsecret_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_appkey_appsecret_id:xt_appkey_appsecret_id};
			ajaxRequest('../xtAppkeyAppsecretController/delXtAppkeyAppsecret',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtAppkeyAppsecret(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_appkey_appsecret_id:record.items[0].data.xt_appkey_appsecret_id};
			ajaxRequest('../xtAppkeyAppsecretController/copyXtAppkeyAppsecret',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtAppkeyAppsecret(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtAppkeyAppsecretItem',
			handler:function(){addXtAppkeyAppsecret();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtAppkeyAppsecretItem',
			handler:function(){updateXtAppkeyAppsecret();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtAppkeyAppsecretItem',
			handler:function(){delXtAppkeyAppsecret();}
		},'-',{
			text:'复制一行并生成记录',
			id:'copyXtAppkeyAppsecretItem',
			glyph:0xf0ea,
			handler:function(){copyXtAppkeyAppsecret();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtAppkeyAppsecretItem',
			handler:function(){detailXtAppkeyAppsecret();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtAppkeyAppsecret(grid,'../xtAppkeyAppsecretController/exportXtAppkeyAppsecret');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtAppkeyAppsecretItem','delXtAppkeyAppsecretItem','copyXtAppkeyAppsecretItem','detailXtAppkeyAppsecretItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtAppkeyAppsecretController/getXtAppkeyAppsecretListByCondition',searchForm); 
}
