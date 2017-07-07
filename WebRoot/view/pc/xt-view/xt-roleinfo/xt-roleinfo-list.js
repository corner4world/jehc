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
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'角色名称',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_role_name',
			name:'xt_role_name',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtRoleinfoController/getXtRoleinfoListByCondition?xt_role_isdelete=0',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		style:'margin-left:auto;margin-right:auto',
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
				header:'角色名称',
				flex:1,
				dataIndex:'xt_role_name'
			},
			{
				header:'状态',
				dataIndex:'xt_role_isdelete',
				renderer:function(value){
					if(value == 0){
						return '正在使用中';
					}else if(value == 1){
						return '已禁用';
					}else{
						return '缺省'
					}
				}
			},
			{
				header:'操作',
				flex:1,
				columns:[{
					header:'导入资源',
					align:'center',
					xtype:'widgetcolumn',
					width:150,
					widget:{
		                xtype:'button',
		                text:'导入资源',
		                /**style:{background:'blue'},**/
		                icon:addIcon,
		                handler:function(rec){	
		  					var xt_role_id = rec.getWidgetRecord().data.xt_role_id;
		  					var xt_role_name = rec.getWidgetRecord().data.xt_role_name;
		  					addXtMenuinfo(xt_role_id,xt_role_name);
					    }
		            }
				},{
					header:'导入用户',
					align:'center',
					xtype:'widgetcolumn',
					width:150,
					widget:{
		                xtype:'button',
		                text:'导入用户',
		                icon:addIcon,
		                /**style:{background:'blue'},**/
		                handler:function(rec){
		                	var xt_role_id = rec.getWidgetRecord().data.xt_role_id;
		                	var xt_role_name = rec.getWidgetRecord().data.xt_role_name;
		                	addXtUserinfo(xt_role_id,xt_role_name);
		                }
		            }
				}]
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
					addXtRoleinfo();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtRoleinfo();
				}
			 },
			 {
				text:'禁用',
				tooltip:'禁用',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtRoleinfo();
				}
			 },
			 {
				text:'已被禁用角色列表',
				tooltip:'已被禁用角色列表（即已被删除）',
				cls:'detailBtn',
				icon:listIcon,
				iconAlign:'left',
				handler:function(){
					initListDeleted();																			
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
				tooltip:'重置件',
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
						copyXtRoleinfo();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtRoleinfo();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtRoleinfo(grid,'../xtRoleinfoController/exportXtRoleinfo');
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
						initSearch(store,'../xtRoleinfoController/getXtRoleinfoListByCondition',searchForm); 
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
				detailXtRoleinfo();
			}
		}
	});
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtRoleinfo(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在禁用');
		return;
	}
	var xt_role_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_role_id){
			xt_role_id=model.selected.items[i].data.xt_role_id;
		}else{
			xt_role_id=xt_role_id+","+model.selected.items[i].data.xt_role_id;
		}
	}
	Ext.Msg.confirm('提示','确定禁用该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_role_id:xt_role_id};
			ajaxRequest('../xtRoleinfoController/delXtRoleinfo',grid,params,'正在执行禁用操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtRoleinfo(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_role_id:record.items[0].data.xt_role_id};
			ajaxRequest('../xtRoleinfoController/copyXtRoleinfo',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtRoleinfo(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtRoleinfoItem',
			handler:function(){addXtRoleinfo();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtRoleinfoItem',
			handler:function(){updateXtRoleinfo();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtRoleinfoItem',
			handler:function(){delXtRoleinfo();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtRoleinfoItem',
			handler:function(){copyXtRoleinfo();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtRoleinfoItem',
			handler:function(){detailXtRoleinfo();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtRoleinfo(grid,'../xtRoleinfoController/exportXtRoleinfo');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtRoleinfoItem','delXtRoleinfoItem','copyXtRoleinfoItem','detailXtRoleinfoItem']);
}

function search(){
	initSearch(store,'../xtRoleinfoController/getXtRoleinfoListByCondition',searchForm); 
}