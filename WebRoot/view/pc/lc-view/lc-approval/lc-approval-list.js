var store;
var grid;
var lc_process_status_combo;
Ext.onReady(function(){
	lc_process_status_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","待发布"],["1","发布中"],["2","已关闭"]]
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
			fieldLabel:'批审状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:lc_process_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'lc_status_id',
			valueField:'value',
			displayField:'text',
			id:'lc_status_id',
			name:'lc_status_id',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../lcApprovalController/getLcApprovalListByCondition',[]);
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
				header:'主键',
				flex:1,
				dataIndex:'lc_approval_id'
			},
			{
				header:'批审状态',
				flex:1,
				dataIndex:'lc_status_name'
			},
			{
				header:'申请编号',
				flex:1,
				dataIndex:'lc_apply_id'
			},
			{
				header:'审批内容',
				flex:1,
				dataIndex:'lc_approval_remark'
			},
			{
				header:'任务编号',
				flex:1,
				dataIndex:'taskId'
			},
			{
				header:'审批时间',
				flex:1,
				dataIndex:'lc_approval_time'
			},
			{
				header:'批审人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			}
		],
		tbar:[
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delLcApproval();
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
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailLcApproval();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportLcApproval(grid,'../lcApprovalController/exportLcApproval');
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
				detailLcApproval();
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
function delLcApproval(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var lc_approval_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == lc_approval_id){
			lc_approval_id=model.selected.items[i].data.lc_approval_id;
		}else{
			lc_approval_id=lc_approval_id+","+model.selected.items[i].data.lc_approval_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {lc_approval_id:lc_approval_id};
			ajaxRequest('../lcApprovalController/delLcApproval',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyLcApproval(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {lc_approval_id:record.items[0].data.lc_approval_id};
			ajaxRequest('../lcApprovalController/copyLcApproval',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportLcApproval(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'删 除',
			glyph:0xf014,
			id:'delLcApprovalItem',
			handler:function(){delLcApproval();}
		},'-',{
			text:'明 细',
			glyph:0xf03b,
			id:'detailLcApprovalItem',
			handler:function(){detailLcApproval();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportLcApproval(grid,'../lcApprovalController/exportLcApproval');
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
	initrightgridcontextmenu(grid,contextmenu,['delLcApprovalItem','detailLcApprovalItem']);
}
/**查询操作**/
function search(){
	var lc_status_id = Ext.getCmp("lc_status_id").getValue();
	store.load({
		url:'../lcApprovalController/getLcApprovalListByCondition',
		params:{
			start:0,
			limit:getGridBBar(store).pageSize,
			lc_status_id:lc_status_id
		}
	});
}
