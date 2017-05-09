var xtErrorLogsWinEdit;
var xtErrorLogsFormEdit;
function updateXtErrorLogs(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtErrorLogsFormEdit();
	xtErrorLogsWinEdit = Ext.create('Ext.Window',{
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
		items:xtErrorLogsFormEdit,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtErrorLogsWinEdit.show();
	loadFormData(xtErrorLogsFormEdit,'../xtErrorLogsController/getXtErrorLogsById?xt_error_log_id='+ record.items[0].data.xt_error_log_id);
}
function initXtErrorLogsFormEdit(){
	xtErrorLogsFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		labelWidth:50,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:120,
			labelAlign:'right',
			flex:1,
			margin:'4 5 4 5'
		},
		items:[
		{
			fieldLabel:'异常ID',
			xtype:'textfield',
			hidden:true,
			name:'xt_error_log_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'日志内容',
			xtype:'textareafield',
			name:'xt_error_logContent',
			maxLength:65535,
			height:180,
			readOnly:true,
			anchor:'100%'
		},
		{
			fieldLabel:'日志级别',
			xtype:'numberfield',
			value:'0',
			readOnly:true,
			name:'xt_error_logType',
			anchor:'100%'
		},
		{
			fieldLabel:'出错时间',
			xtype:'datetimefield',
			name:'xt_error_logTime',
			maxLength:20,
			readOnly:true,
			anchor:'100%'
		},
		{
			fieldLabel:'操&nbsp;&nbsp;作&nbsp;&nbsp;人',
			xtype:'textfield',
			readOnly:true,
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'100%'
		}
		]
	});
}
