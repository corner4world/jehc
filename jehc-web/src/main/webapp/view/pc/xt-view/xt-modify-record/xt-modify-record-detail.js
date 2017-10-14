var xtModifyRecordWinDetail;
var xtModifyRecordFormDetail;
function detailXtModifyRecord(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtModifyRecordFormDetail();
	xtModifyRecordWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'明细信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtModifyRecordFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtModifyRecordWinDetail.show();
	
	loadFormData(xtModifyRecordFormDetail,'../xtModifyRecordController/getXtModifyRecordById?xt_modify_record_id='+ record.items[0].data.xt_modify_record_id);
}
function initXtModifyRecordFormDetail(){
	xtModifyRecordFormDetail = Ext.create('Ext.FormPanel',{
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
			readOnly:true,
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
			fieldLabel:'字&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;段',
			xtype:'textfield',
			name:'xt_modify_record_field',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'变更前值',
			xtype:'textareafield',
			name:'xt_modify_record_beforevalue',
			maxLength:2147483647,
			anchor:'100%'
		},
		{
			fieldLabel:'变更后值',
			xtype:'textareafield',
			name:'xt_modify_record_aftervalue',
			maxLength:2147483647,
			anchor:'100%'
		},
		{
			fieldLabel:'模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块',
			xtype:'textfield',
			name:'xt_modify_record_modules',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'创&nbsp;建&nbsp;&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'xt_modify_record_ctime',
			maxLength:19,
			anchor:'40%'
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
