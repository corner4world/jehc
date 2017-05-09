var xtDbinfoWinAdd;
var xtDbinfoFormAdd;
function addXtDbinfo(){
	initXtDbinfoFormAdd();
	xtDbinfoWinAdd = Ext.create('Ext.Window',{
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
		items:xtDbinfoFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtDbinfoFormAdd,'../xtDbinfoController/addXtDbinfo',grid,xtDbinfoWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtDbinfoWinAdd.show();
}
function initXtDbinfoFormAdd(){
	xtDbinfoFormAdd = Ext.create('Ext.FormPanel',{
		xtype:'form',
		labelWidth:50,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'4 5 4 5'
		},
		items:[
		{
			fieldLabel:'数据库名',
			xtype:'textfield',
			name:'xt_dbinfoName',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'用&nbsp;&nbsp;户&nbsp;名',
			xtype:'textfield',
			name:'xt_dbinfoUName',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码',
			xtype:'textfield',
			name:'xt_dbinfoPwd',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'IP&nbsp;地&nbsp;址',
			xtype:'textfield',
			name:'xt_dbinfoIp',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'端&nbsp;&nbsp;口&nbsp;号',
			xtype:'textfield',
			name:'xt_dbinfoPort',
			maxLength:255,
			anchor:'100%'
		}
		]
	});
}
