//建表语句//
function getTablePhrases(xtDbTableName){
	reGetWidthAndHeight();
	initTablePhrasesForm(xtDbTableName);
	tablePhrasesWin = new Ext.Window({
		layout:'fit',                    
		width:clientWidth*0.9,      
		height:clientHeight*0.85,
		modal:true,               
		closeAction:'close',                    
		plain:true,                    
		title:'建表语句',
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
		items:tablePhrasesForm
	});
	tablePhrasesWin.show();
	loadFormData(tablePhrasesForm,'../xtDbStructureController/getTablePhrases?tableName='+xtDbTableName);
}

function initTablePhrasesForm(saDataBaseTableName){
	tablePhrasesForm = Ext.create('Ext.FormPanel',{
		labelWidth:57,
		region:'center',
		frame:true,
		waitMsgTarget:true,
		defaults:{ 
		   width:200
		},
		defaultType:'textfield',
		items:[{
				width:clientWidth*0.8,
			    fieldLabel:'表名',
			    name:'table'
		   },{
		   		fieldLabel:'语句',
		   		xtype:'textarea',
		   		width:clientWidth*0.8,
		   		height:clientHeight*0.7,
		   		name:'createTable'
		   }]
	});
}
