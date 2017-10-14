var store;
var grid;
var xt_service_center_type_combo ;
var xt_service_center_status_combo;
var xt_service_center_parameter_type_combo;
var xt_service_center_parameter_status_combo;
Ext.onReady(function(){
	xt_service_center_parameter_type_combo = xt_service_center_parameter_type_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","String"],["1","json"],["2","Integer"],["3","Date"],["4","double"],["5","float"]]
	});
	xt_service_center_parameter_status_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","通过正常"],["1","禁用"]]
	});
	xt_service_center_type_combo  = xt_service_center_type_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","dubbo"],["1","hessian"],["2","webservice"]]
	});
	xt_service_center_status_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","通过正常"],["1","禁用"]]
	});
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
			fieldLabel:'服务类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:xt_service_center_type_combo ,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_service_center_type',
			valueField:'value',
			displayField:'text',
			id:'xt_service_center_type',
			name:'xt_service_center_type',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:xt_service_center_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_service_center_status',
			valueField:'value',
			displayField:'text',
			id:'xt_service_center_status',
			name:'xt_service_center_status',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'服务名称',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_service_center_name',
			name:'xt_service_center_name',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtServiceCenterController/getXtServiceCenterListByCondition',[]);
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
				header:'服务名称',
				flex:1,
				dataIndex:'xt_service_center_name'
			},
			{
				header:'服务地址',
				flex:1,
				dataIndex:'xt_service_center_location'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'mtime'
			},
			{
				header:'服务类型',
				flex:1,
				dataIndex:'xt_service_center_type',
				renderer:function(value){
					if(value == 0){
						return 'dubbo'
					}else if(value == 1){
						return 'hessian';
					}else if(value == 2){
						return 'webservice';
					}else{
						return '--';
					}
				}
			},
			{
				header:'状态',
				flex:1,
				dataIndex:'xt_service_center_status',
				renderer:function(value){
					if(value == 0){
						return '正常通过'
					}else{
						return '禁用';
					}
				}
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'ctime'
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
					addXtServiceCenter();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtServiceCenter();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtServiceCenter();
				}
			 },
			 {
				text:'检索',
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
						copyXtServiceCenter();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtServiceCenter();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtServiceCenter(grid,'../xtServiceCenterController/exportXtServiceCenter');
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
				detailXtServiceCenter();
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
function delXtServiceCenter(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_service_center_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_service_center_id){
			xt_service_center_id=model.selected.items[i].data.xt_service_center_id;
		}else{
			xt_service_center_id=xt_service_center_id+","+model.selected.items[i].data.xt_service_center_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_service_center_id:xt_service_center_id};
			ajaxRequest('../xtServiceCenterController/delXtServiceCenter',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtServiceCenter(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_service_center_id:record.items[0].data.xt_service_center_id};
			ajaxRequest('../xtServiceCenterController/copyXtServiceCenter',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtServiceCenter(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtServiceCenterItem',
			handler:function(){addXtServiceCenter();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtServiceCenterItem',
			handler:function(){updateXtServiceCenter();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtServiceCenterItem',
			handler:function(){delXtServiceCenter();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtServiceCenterItem',
			handler:function(){copyXtServiceCenter();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtServiceCenterItem',
			handler:function(){detailXtServiceCenter();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtServiceCenter(grid,'../xtServiceCenterController/exportXtServiceCenter');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtServiceCenterItem','delXtServiceCenterItem','copyXtServiceCenterItem','detailXtServiceCenterItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtServiceCenterController/getXtServiceCenterListByCondition',searchForm);
}
