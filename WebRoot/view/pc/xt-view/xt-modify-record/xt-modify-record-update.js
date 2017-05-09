var xtModifyRecordWinEdit;
var xtModifyRecordFormEdit;
function updateXtModifyRecord(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtModifyRecordFormEdit();
	xtModifyRecordWinEdit = Ext.create('Ext.Window',{
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
		items:xtModifyRecordFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtModifyRecordFormEdit,'../xtModifyRecordController/updateXtModifyRecord',grid,xtModifyRecordWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtModifyRecordWinEdit.show();
	
	loadFormData(xtModifyRecordFormEdit,'../xtModifyRecordController/getXtModifyRecordById?xt_modify_record_id='+ record.items[0].data.xt_modify_record_id);
}
function initXtModifyRecordFormEdit(){
	xtModifyRecordFormEdit = Ext.create('Ext.FormPanel',{
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
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'xt_modify_record_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'字段',
			xtype:'textfield',
			name:'xt_modify_record_field',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'变更前值',
			xtype:'textfield',
			name:'xt_modify_record_beforevalue',
			maxLength:2147483647,
			anchor:'100%'
		},
		{
			fieldLabel:'变更后值',
			xtype:'textfield',
			name:'xt_modify_record_aftervalue',
			maxLength:2147483647,
			anchor:'100%'
		},
		{
			fieldLabel:'模块',
			xtype:'textfield',
			name:'xt_modify_record_modules',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'创建人',
			xtype:'textfield',
			name:'xt_userinfo_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'xt_modify_record_ctime',
			maxLength:19,
			anchor:'100%'
		},
		{
			fieldLabel:'业务编号',
			xtype:'textfield',
			name:'business_id',
			maxLength:32,
			hidden:true,
			anchor:'100%'
		}
		]
	});
}
