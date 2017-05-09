var xtConcordatWinAdd;
var xtConcordatFormAdd;
function addXtConcordat(){
	initXtConcordatFormAdd();
	xtConcordatWinAdd = Ext.create('Ext.Window',{
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
		items:xtConcordatFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtConcordatFormAdd,'../xtConcordatController/addXtConcordat',grid,xtConcordatWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtConcordatWinAdd.show();
}
function initXtConcordatFormAdd(){
	xtConcordatFormAdd = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'合同名称',
			xtype:'textfield',
			name:'xt_concordatName',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'合同描述',
			xtype:'textarea',
			name:'xt_concordatDesc',
			maxLength:50,
			anchor:'100%'
		}
		]
	});
}
