var xtOperateBusinessLogsWinDetail;
var xtOperateBusinessLogsFormDetail;
function detailXtOperateBusinessLogs(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtOperateBusinessLogsFormDetail();
	xtOperateBusinessLogsWinDetail = Ext.create('Ext.Window',{
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
		items:xtOperateBusinessLogsFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtOperateBusinessLogsWinDetail.show();
	
	loadFormData(xtOperateBusinessLogsFormDetail,'../xtOperateBusinessLogsController/getXtOperateBusinessLogsById?xt_operate_business_logs_id='+ record.items[0].data.xt_operate_business_logs_id);
}
function initXtOperateBusinessLogsFormDetail(){
	xtOperateBusinessLogsFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			readOnly:true,
			margin:'2 5 4 5'
		},
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		items:[
		{
			fieldLabel:'平台业务操作日志ID',
			xtype:'textfield',
			hidden:true,
			name:'xt_operate_business_logs_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块',
			xtype:'textfield',
			name:'xt_operate_business_logsModules',
			maxLength:2000,
			anchor:'100%'
		},
		{
			fieldLabel:'执行方法',
			xtype:'textfield',
			name:'xt_operate_business_logsMethod',
			maxLength:800,
			anchor:'100%'
		},
		{
			fieldLabel:'方法参数',
			xtype:'textareafield',
			name:'xt_operate_business_logsMethodPar',
			maxLength:2147483647,
			anchor:'100%'
		},
		{
			fieldLabel:'执行结果',
			xtype:'textareafield',
			name:'xt_operate_business_logsResult',
			maxLength:2147483647,
			anchor:'100%'
		},
		{
			fieldLabel:'操作时间',
			xtype:'textfield',
			name:'xt_operate_business_logsTime',
			maxLength:20,
			anchor:'40%'
		},
		{
			fieldLabel:'操&nbsp;&nbsp;作&nbsp;&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'40%'
		}
		]
	});
}
