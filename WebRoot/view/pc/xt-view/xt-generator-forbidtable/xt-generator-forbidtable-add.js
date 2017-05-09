var xtGeneratorForbidtableWinAdd;
var xtGeneratorForbidtableFormAdd;
function addXtGeneratorForbidtable(){
	initXtGeneratorForbidtableFormAdd();
	xtGeneratorForbidtableWinAdd = Ext.create('Ext.Window',{
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
		items:xtGeneratorForbidtableFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtGeneratorForbidtableFormAdd,'../xtGeneratorForbidtableController/addXtGeneratorForbidtable',grid,xtGeneratorForbidtableWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtGeneratorForbidtableWinAdd.show();
	
}
function initXtGeneratorForbidtableFormAdd(){
	xtGeneratorForbidtableFormAdd = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
			xtype:'textfield',
			name:'xt_generator_forbidtable_table',
			allowBlank:false,
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textareafield',
			name:'xt_generator_forbidtable_remark',
			maxLength:500,
			anchor:'100%'
		}
		]
	});
}
