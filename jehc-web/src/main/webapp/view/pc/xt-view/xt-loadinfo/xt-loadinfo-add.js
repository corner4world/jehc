var xtLoadinfoWinAdd;
var xtLoadinfoFormAdd;
function addXtLoadinfo(){
	initXtLoadinfoFormAdd();
	xtLoadinfoWinAdd = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'添加信息',
		items:xtLoadinfoFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtLoadinfoFormAdd,'../xtLoadinfoController/addXtLoadinfo',grid,xtLoadinfoWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtLoadinfoWinAdd.show();
}
function initXtLoadinfoFormAdd(){
	xtLoadinfoFormAdd = Ext.create('Ext.FormPanel',{
		xtype:'form',
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
			fieldLabel:'载加模块',
			xtype:'textfield',
			name:'xt_loadinfo_modules',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'加载时间',
			xtype:'numberfield',
			value:'0',
			name:'xt_loadinfo_time',
			anchor:'100%'
		},
		{
			fieldLabel:'载入时间',
			xtype:'datefield',
			format:'Y-m-d',
			name:'xt_loadinfo_begtime',
			anchor:'100%'
		},
		{
			fieldLabel:'结束时间',
			xtype:'datefield',
			format:'Y-m-d',
			name:'xt_loadinfo_endtime',
			anchor:'100%'
		}
		]
	});
}
