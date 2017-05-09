//表结构字段信息//
function getXtDbStructureByCondition(xtDbTableName){
	reGetWidthAndHeight();
	initXtDbStructureGrid(xtDbTableName);
	xtDbStructureWin = new Ext.Window({
		layout:'fit',                    
		width:clientWidth*0.9,      
		height:clientHeight*0.85,
		modal:true,               
		closeAction:'close',                    
		plain:true,                    
		title:'字段结构',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		items:xtDbStructureGrid
	});
	xtDbStructureWin.show();
}

function initXtDbStructureGrid(xtDbTableName){
	xtDbStructureStore = getGridJsonStore('../xtDbStructureController/getXtDbStructureByCondition?tableName='+xtDbTableName,[]);
	xtDbStructureGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
	    store:xtDbStructureStore,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
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
			{header:'字段',dataIndex:'field',sortable:true},
			{header:'类型',dataIndex:'type',sortable:true},
			{header:'编码',dataIndex:'collation',sortable:true},
			{header:'必填',dataIndex:'isNull',sortable:true},
			{header:'键',dataIndex:'key',sortable:true},
			{header:'默认值',dataIndex:'defaults',sortable:true},
			{header:'自动增长',dataIndex:'extra',sortable:true},
			{header:'操作权限',dataIndex:'privileges',sortable:true},
			{header:'备注',dataIndex:'comment',sortable:true}
		]
	});
}
