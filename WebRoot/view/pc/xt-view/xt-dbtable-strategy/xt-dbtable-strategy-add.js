var xtDbtableStrategyWinAdd;
var xtDbtableStrategyFormAdd;
function addXtDbtableStrategy(){
	initXtDbtableStrategyFormAdd();
	xtDbtableStrategyWinAdd = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'添加信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtDbtableStrategyFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtDbtableStrategyFormAdd,'../xtDbtableStrategyController/addXtDbtableStrategy',grid,xtDbtableStrategyWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtDbtableStrategyWinAdd.show();
	
}
function initXtDbtableStrategyFormAdd(){
	xtDbtableStrategyFormAdd = Ext.create('Ext.FormPanel',{
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
