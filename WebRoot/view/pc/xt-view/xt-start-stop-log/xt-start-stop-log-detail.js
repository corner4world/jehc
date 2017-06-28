var xtStartStopLogWinDetail;
var xtStartStopLogFormDetail;
function detailXtStartStopLog(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtStartStopLogFormDetail();
	xtStartStopLogWinDetail = Ext.create('Ext.Window',{
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
		items:xtStartStopLogFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtStartStopLogWinDetail.show();
	loadFormData(xtStartStopLogFormDetail,'../xtStartStopLogController/getXtStartStopLogById?xt_start_stop_log_id='+ record.items[0].data.xt_start_stop_log_id);
}
function initXtStartStopLogFormDetail(){
	xtStartStopLogFormDetail = Ext.create('Ext.FormPanel',{
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
		items:[
		{
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'xt_start_stop_log_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'启动时间',
			xtype:'textfield',
			name:'xt_start_stop_log_starttime',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'停止时间',
			xtype:'textfield',
			name:'xt_start_stop_log_stoptime',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'textfield',
			name:'xt_start_stop_log_iserror',
			name:"xt_start_stop_log_iserror",
            hiddenName:'xt_start_stop_log_iserror',
            xtype:"combo",
            anchor:'40%',
            store:[["0","正常"],["1","异常"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false
		},
		{
			fieldLabel:'加载内容',
			xtype:'textarea',
			height:150,
			name:'xt_start_stop_log_content',
			maxLength:65535,
			anchor:'100%'
		}
		]
	});
}
