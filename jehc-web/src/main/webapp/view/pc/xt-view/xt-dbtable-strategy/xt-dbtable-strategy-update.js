var xtDbtableStrategyWinEdit;
var xtDbtableStrategyFormEdit;
function updateXtDbtableStrategy(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtDbtableStrategyFormEdit();
	xtDbtableStrategyWinEdit = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'编辑信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtDbtableStrategyFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtDbtableStrategyFormEdit,'../xtDbtableStrategyController/updateXtDbtableStrategy',grid,xtDbtableStrategyWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtDbtableStrategyWinEdit.show();
	
	loadFormData(xtDbtableStrategyFormEdit,'../xtDbtableStrategyController/getXtDbtableStrategyById?xt_dbtable_strategy_id='+ record.items[0].data.xt_dbtable_strategy_id);
}
function initXtDbtableStrategyFormEdit(){
	xtDbtableStrategyFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		/**新方法使用结束**/
		fieldDefaults:{
			labelWidth:100,
			labelAlign:'right',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'策略编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_dbtable_strategy_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'数据库表名',
			xtype:'textfield',
			name:'tableName',
			allowBlank:false,
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'CTime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'唯一模块标识',
			xtype:'textfield',
			name:'onlyModulesFlag',
			maxLength:255,
			anchor:'100%'
		}
		]
	});
}
