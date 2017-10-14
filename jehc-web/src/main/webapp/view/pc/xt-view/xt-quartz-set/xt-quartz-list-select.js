var storeSelect;
var gridSelect;
var gridSelectWin;
function selectGrid(){
	intGrid();
	reGetWidthAndHeight();
	gridSelectWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth,  
		height:clientHeight,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'选择配置信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:gridSelect
	});
	gridSelectWin.show();
}
function intGrid(){
	storeSelect = getGridJsonStore('../xtQuartzController/getXtQuartzListByCondition',[]);
	gridSelect = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:storeSelect,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:false,
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
				header:'任务编号',
				dataIndex:'jobId'
			},
			{
				header:'任务名称',
				dataIndex:'jobName'
			},
			{
				header:'任务分组',
				dataIndex:'jobGroup'
			},
			{
				header:'任务状态',
				dataIndex:'jobStatus',
				renderer:function(value){
					if(value == 'NORMAL'){
						return "启动";
					}else if(value == 'PAUSED'){
						return "暂停";
					}else{
						return "其它";
					}
				}
			},
			{
				header:'任务运行时间表达式',
				dataIndex:'cronExpression'
			},
			{
				header:'任务描述',
				dataIndex:'desc'
			},
			{
				header:'执行的类方法',
				dataIndex:'targetMethod'
			},
			{
				header:'执行的类',
				dataIndex:'targetClass',
				flex:1
			}
		],
		tbar:[
			 {
				text:'选择该配置',
				tooltip:'选择该配置',
				scope:this,
				icon:addIcon,
				handler:function(){
					selectXtQuartz();
				}
			 }
		],
		bbar:getGridBBar(storeSelect)
	});
}
function selectXtQuartz(){
	var model = gridSelect.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在要设置的配置项');
		return;
	}
	Ext.Msg.confirm('提示','确定要选择该行数据？',function(btn){
		if(btn == 'yes'){
			xtQuartzFormAdd.getForm().loadRecord(gridSelect.getSelectionModel().selected.items[0]);
			gridSelectWin.close();
		}
	});
}
