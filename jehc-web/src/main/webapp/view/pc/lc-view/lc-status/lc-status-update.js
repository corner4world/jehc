var lcStatusWinEdit;
var lcStatusFormEdit;
function updateLcStatus(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initLcStatusFormEdit();
	lcStatusWinEdit = Ext.create('Ext.Window',{
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
		items:lcStatusFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(lcStatusFormEdit,'../lcStatusController/updateLcStatus',grid,lcStatusWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	lcStatusWinEdit.show();
	loadFormData(lcStatusFormEdit,'../lcStatusController/getLcStatusById?lc_status_id='+ record.items[0].data.lc_status_id);
}
function initLcStatusFormEdit(){
	lcStatusFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'流程状态编号',
			xtype:'textfield',
			hidden:true,
			name:'lc_status_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'状态名称',
			xtype:'textfield',
			name:'lc_status_name',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textareafield',
			name:'lc_status_remark',
			maxLength:800,
			anchor:'100%'
		},
		{
			fieldLabel:'流程常量',
			xtype:'textfield',
			name:'lc_status_constant',
			maxLength:50,
			anchor:'40%'
		},
		]
	});
}
