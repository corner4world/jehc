var xtLoginLogsWinEdit;
var xtLoginLogsFormEdit;
function updateXtLoginLogs(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtLoginLogsFormEdit();
	xtLoginLogsWinEdit = Ext.create('Ext.Window',{
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
		items:xtLoginLogsFormEdit,
		buttons:[/**{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtLoginLogsFormEdit,'../xtLoginLogsController/updateXtLoginLogs',grid,xtLoginLogsWinEdit,false,true);
			}
		},**/{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtLoginLogsWinEdit.show();
	loadFormData(xtLoginLogsFormEdit,'../xtLoginLogsController/getXtLoginLogsById?xt_login_log_id='+ record.items[0].data.xt_login_log_id);
}
function initXtLoginLogsFormEdit(){
	xtLoginLogsFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'日志编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_login_log_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'网络地址',
			xtype:'textfield',
			name:'xt_login_logIP',
			maxLength:20,
			anchor:'100%'
		},
		{
			fieldLabel:'登录时间',
			xtype:'textfield',
			name:'xt_login_logtime',
			maxLength:20,
			anchor:'100%'
		},
		{
			fieldLabel:'登陆日志',
			xtype:'textfield',
			name:'xt_login_logContent',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'操&nbsp;作&nbsp;&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'100%'
		}
		]
	});
}
