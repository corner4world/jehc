//表大小//
function getXtDbTableSize(xtDbTableName){
	reGetWidthAndHeight();
	initXtDbTableSizeForm(xtDbTableName);
	xtDbTableSizeWin = new Ext.Window({
		layout:'fit',                    
		width:800,      
		height:400,
		modal:true,               
		closeAction:'close',                    
		plain:true,                    
		title:'表空间大小',
		maximizable:true,
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		minimizable:true,
		animateTarget:document.body,
		items:xtDbTableSizeForm
	});
	xtDbTableSizeWin.show();
	loadFormData(xtDbTableSizeForm,'../xtDbStructureController/getXtDbTableSize?tableName='+xtDbTableName);
}

function initXtDbTableSizeForm(xtDbTableName){
	xtDbTableSizeForm = Ext.create('Ext.FormPanel',{
		labelWidth:57,
		region:'center',
		frame:true,
		waitMsgTarget:true,
		defaults:{ 
		   width:400
		},
		defaultType:'textfield',
		items:[{
			    fieldLabel:'表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
			    name:'table_name'
		   	   },{
			    fieldLabel:'数据大小',
			    name:'data_length'
		   	   },{
			    fieldLabel:'索引大小',
			    name:'index_length'
		   	   },{
			    fieldLabel:'共计大小',
			    name:'totalSize'
		   	   },{
			    fieldLabel:'记&nbsp;录&nbsp;&nbsp;数',
			    name:'table_rows'
		   	   }]
	});
}
