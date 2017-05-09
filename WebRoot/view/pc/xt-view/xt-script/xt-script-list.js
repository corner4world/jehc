var store;
var grid;
Ext.onReady(function(){
	var XT_SCRIPT_TYPE_COMBO = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["1","javaScript"],["2","Sql"],["3","html"],["4","其他"]]
	});
	var XT_SCRIPT_STATUS_COMBO = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
	 	data:[["0","正常"],["1","禁用"]]
	});
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
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
			fieldLabel:'脚本键值',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_script_key',
			name:'xt_script_key',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'脚本类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:XT_SCRIPT_TYPE_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_script_type',
			valueField:'value',
			displayField:'text',
			id:'xt_script_type',
			name:'xt_script_type',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:XT_SCRIPT_STATUS_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_script_status',
			valueField:'value',
			displayField:'text',
			id:'xt_script_status',
			name:'xt_script_status',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtScriptController/getXtScriptListByCondition',[]);
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
				dataIndex:'xt_script_title'
			},
			{
				header:'脚本键',
				flex:1,
				dataIndex:'xt_script_key'
			},			
			{
				header:'创建时间',
				flex:1,
				dataIndex:'xt_script_ctime'
			},
			{
				header:'最后修改时间',
				flex:1,
				dataIndex:'xt_script_mtime'
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
					addXtScript();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtScript();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtScript();
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
				 scope:this,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'复制一行并生成记录',
					tooltip:'复制一行并生成记录',
					glyph:0xf0ea,
					handler:function(){
						copyXtScript();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtScript();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtScript(grid,'../xtScriptController/exportXtScript');
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
				detailXtScript();
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
function delXtScript(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_script_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_script_id){
			xt_script_id=model.selected.items[i].data.xt_script_id;
		}else{
			xt_script_id=xt_script_id+","+model.selected.items[i].data.xt_script_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_script_id:xt_script_id};
			ajaxRequest('../xtScriptController/delXtScript',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtScript(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_script_id:record.items[0].data.xt_script_id};
			ajaxRequest('../xtScriptController/copyXtScript',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtScript(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtScriptItem',
			handler:function(){addXtScript();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtScriptItem',
			handler:function(){updateXtScript();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtScriptItem',
			handler:function(){delXtScript();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtScriptItem',
			handler:function(){copyXtScript();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtScriptItem',
			handler:function(){detailXtScript();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtScript(grid,'../xtScriptController/exportXtScript');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtScriptItem','delXtScriptItem','copyXtScriptItem','detailXtScriptItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtScriptController/getXtScriptListByCondition',searchForm); 
}
