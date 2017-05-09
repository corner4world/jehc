var xtMessageWinDetail;
var xtMessageFormDetail;
function detailXtMessage(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtMessageFormDetail();
	xtMessageWinDetail = Ext.create('Ext.Window',{
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
		items:xtMessageFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtMessageWinDetail.show();
	
	loadFormData(xtMessageFormDetail,'../xtMessageController/getXtMessageById?xt_message_id='+ record.items[0].data.xt_message_id);
}
function initXtMessageFormDetail(){
	xtMessageFormDetail = Ext.create('Ext.FormPanel',{
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
			name:'xt_message_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'发&nbsp;&nbsp;送&nbsp;&nbsp;者',
			xtype:'textfield',
			name:'fromName',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'接&nbsp;&nbsp;收&nbsp;&nbsp;者',
			xtype:'textfield',
			name:'toName',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'送发内容',
			xtype:'htmleditor',
			name:'xt_meesage_content',
			maxLength:65535,
			anchor:'100%'
		},
		{
			fieldLabel:'是否已读',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_message_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'isread',
			valueField:'value',
			displayField:'text',
			name:'isread',
			maxLength:4,
			anchor:'40%'
		},
		{
			fieldLabel:'发送时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'取读时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'readtime',
			maxLength:19,
			anchor:'40%'
		}
		]
	});
}
