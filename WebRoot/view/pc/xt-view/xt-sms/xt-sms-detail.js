var xtSmsWinDetail;
var xtSmsFormDetail;
function detailXtSms(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtSmsFormDetail();
	xtSmsWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:450,
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
		items:xtSmsFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtSmsWinDetail.show();
	loadFormData(xtSmsFormDetail,'../xtSmsController/getXtSmsById?xt_sms_id='+ record.items[0].data.xt_sms_id);
}
function initXtSmsFormDetail(){
	xtSmsFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'ID',
			xtype:'textfield',
			hidden:true,
			name:'xt_sms_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'用&nbsp;&nbsp;户&nbsp;名',
			xtype:'textfield',
			name:'xt_smsName',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'接口密码',
			xtype:'textfield',
			name:'xt_smsPWD',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'URL地址',
			xtype:'textfield',
			name:'xt_smsURL',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'公&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;司',
			xtype:'textfield',
			name:'xt_smsCompany',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话',
			xtype:'textfield',
			name:'xt_smsCompanTel',
			maxLength:20,
			anchor:'40%'
		},
		{
			fieldLabel:'短信平台',
			xtype:'textfield',
			name:'xt_smsValue',
			maxLength:20,
			anchor:'40%'
		},
		{
			fieldLabel:'公司地址',
			xtype:'textarea',
			name:'xt_smsCompanyAddress',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'联&nbsp;&nbsp;系&nbsp;人',
			xtype:'textfield',
			name:'xt_smsCompanyContacts',
			maxLength:20,
			anchor:'40%'
		},
		{
			fieldLabel:'协议类型',
			value:'0',
			name:'xt_smsType',
			anchor:'40%',
			hiddenName:'xt_smsType',
            xtype:"combo",
            store:[["0","http"],["1","其他"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			value:'0',
			name:'xt_smsState',
			anchor:'40%',
			hiddenName:'xt_smsState',
            xtype:"combo",
            store:[["0","暂停"],["1","启用"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false
		}
		]
	});
}
