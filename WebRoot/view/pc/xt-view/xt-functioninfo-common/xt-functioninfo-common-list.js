var store;
var grid;
var xt_functioninfo_common_combo;
Ext.onReady(function(){
	xt_functioninfo_common_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","启用"],["1","禁用"]]
	});
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
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_functioninfo_common_title',
			name:'xt_functioninfo_common_title',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:xt_functioninfo_common_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_functioninfo_common_status',
			valueField:'value',
			displayField:'text',
			id:'xt_functioninfo_common_status',
			name:'xt_functioninfo_common_status',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'方法名称',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_functioninfo_common_method',
			name:'xt_functioninfo_common_method',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'功能地址',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_functioninfo_common_url',
			name:'xt_functioninfo_common_url',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtFunctioninfoCommonController/getXtFunctioninfoCommonListByCondition',[]);
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
				header:'标题',
				flex:1,
				dataIndex:'xt_functioninfo_common_title'
			},
			{
				header:'方法名称',
				flex:1,
				dataIndex:'xt_functioninfo_common_method'
			},
			{
				header:'功能地址',
				flex:1,
				dataIndex:'xt_functioninfo_common_url'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'xt_functioninfo_common_mtime'
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'xt_functioninfo_common_ctime'
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
					addXtFunctioninfoCommon();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtFunctioninfoCommon();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtFunctioninfoCommon();
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
						copyXtFunctioninfoCommon();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtFunctioninfoCommon();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtFunctioninfoCommon(grid,'../xtFunctioninfoCommonController/exportXtFunctioninfoCommon');
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
				detailXtFunctioninfoCommon();
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
function delXtFunctioninfoCommon(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_functioninfo_common_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_functioninfo_common_id){
			xt_functioninfo_common_id=model.selected.items[i].data.xt_functioninfo_common_id;
		}else{
			xt_functioninfo_common_id=xt_functioninfo_common_id+","+model.selected.items[i].data.xt_functioninfo_common_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_functioninfo_common_id:xt_functioninfo_common_id};
			ajaxRequest('../xtFunctioninfoCommonController/delXtFunctioninfoCommon',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtFunctioninfoCommon(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_functioninfo_common_id:record.items[0].data.xt_functioninfo_common_id};
			ajaxRequest('../xtFunctioninfoCommonController/copyXtFunctioninfoCommon',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtFunctioninfoCommon(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtFunctioninfoCommonItem',
			handler:function(){addXtFunctioninfoCommon();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtFunctioninfoCommonItem',
			handler:function(){updateXtFunctioninfoCommon();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtFunctioninfoCommonItem',
			handler:function(){delXtFunctioninfoCommon();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtFunctioninfoCommonItem',
			handler:function(){copyXtFunctioninfoCommon();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtFunctioninfoCommonItem',
			handler:function(){detailXtFunctioninfoCommon();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtFunctioninfoCommon(grid,'../xtFunctioninfoCommonController/exportXtFunctioninfoCommon');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtFunctioninfoCommonItem','delXtFunctioninfoCommonItem','copyXtFunctioninfoCommonItem','detailXtFunctioninfoCommonItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtFunctioninfoCommonController/getXtFunctioninfoCommonListByCondition',searchForm);
}
