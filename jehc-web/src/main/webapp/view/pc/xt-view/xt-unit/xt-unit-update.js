var xtUnitWinEdit;
var xtUnitFormEdit;
function updateXtUnit(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtUnitFormEdit();
	xtUnitWinEdit = Ext.create('Ext.Window',{
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
		items:xtUnitFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtUnitFormEdit,'../xtUnitController/updateXtUnit',grid,xtUnitWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtUnitWinEdit.show();
	loadFormData(xtUnitFormEdit,'../xtUnitController/getXtUnitById?xt_unit_id='+ record.items[0].data.xt_unit_id);
}
function initXtUnitFormEdit(){
	xtUnitFormEdit = Ext.create('Ext.FormPanel',{
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
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		items:[
		{
			fieldLabel:'单位编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_unit_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'单位名称',
			xtype:'textfield',
			name:'xt_unitName',
			allowBlank:false,
			maxLength:20,
			anchor:'100%'
		}
		]
	});
}
