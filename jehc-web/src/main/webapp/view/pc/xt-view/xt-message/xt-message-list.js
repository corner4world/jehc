var store;
var grid;
var xt_message_combo;
Ext.onReady(function(){
	xt_message_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","未读"],["1","已读"]]
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
			fieldLabel:'是否已读',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:xt_message_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'isread',
			valueField:'value',
			displayField:'text',
			id:'isread',
			name:'isread',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtMessageController/getXtMessageListByCondition',[]);
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
				header:'发送者',
				flex:1,
				dataIndex:'fromName'
			},
			{
				header:'接收者',
				flex:1,
				dataIndex:'toName'
			},
			{
				header:'送发内容',
				flex:1,
				dataIndex:'xt_meesage_content'
			},
			{
				header:'是否已读',
				flex:1,
				dataIndex:'isread',
				renderer:function(value){
					return initComBoData(xt_message_combo,value,'value','text');
				}
			},
			{
				header:'发送时间',
				flex:1,
				dataIndex:'ctime'
			},
			{
				header:'取读时间',
				flex:1,
				dataIndex:'readtime'
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
					addXtMessage();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtMessage();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtMessage();
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
						copyXtMessage();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtMessage();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtMessage(grid,'../xtMessageController/exportXtMessage');
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
				detailXtMessage();
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
function delXtMessage(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_message_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_message_id){
			xt_message_id=model.selected.items[i].data.xt_message_id;
		}else{
			xt_message_id=xt_message_id+","+model.selected.items[i].data.xt_message_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_message_id:xt_message_id};
			ajaxRequest('../xtMessageController/delXtMessage',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtMessage(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_message_id:record.items[0].data.xt_message_id};
			ajaxRequest('../xtMessageController/copyXtMessage',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtMessage(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtMessageItem',
			handler:function(){addXtMessage();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtMessageItem',
			handler:function(){updateXtMessage();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtMessageItem',
			handler:function(){delXtMessage();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtMessageItem',
			handler:function(){copyXtMessage();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtMessageItem',
			handler:function(){detailXtMessage();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtMessage(grid,'../xtMessageController/exportXtMessage');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtMessageItem','delXtMessageItem','copyXtMessageItem','detailXtMessageItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtMessageController/getXtMessageListByCondition',searchForm); 
}
