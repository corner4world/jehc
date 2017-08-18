var bMemberAccountProprietaryHisWinDetail;
var bMemberAccountProprietaryHisFormDetail;
function detailBMemberAccountProprietaryHis(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBMemberAccountProprietaryHisFormDetail();
	bMemberAccountProprietaryHisWinDetail = Ext.create('Ext.Window',{
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
				win.collapse();
			}
		},
		items:bMemberAccountProprietaryHisFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bMemberAccountProprietaryHisWinDetail.show();
	loadFormData(bMemberAccountProprietaryHisFormDetail,'../bMemberAccountProprietaryHisController/getBMemberAccountProprietaryHisById?b_member_account_proprietary_his_id='+ record.items[0].data.b_member_account_proprietary_his_id);
}
function initBMemberAccountProprietaryHisFormDetail(){
	bMemberAccountProprietaryHisFormDetail = Ext.create('Ext.FormPanel',{
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
		items:[
		{
			fieldLabel:'专有账户充值记录',
			xtype:'textfield',
			hidden:true,
			name:'b_member_account_proprietary_his_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'专有账户编号',
			xtype:'textfield',
			name:'b_member_account_proprietary_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'充值金额',
			xtype:'numberfield',
			value:'0',
			name:'b_member_account_proprietary_his_money',
			allowBlank:false,
			anchor:'100%'
		},
		{
			fieldLabel:'充值时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_member_account_proprietary_his_ctime',
			allowBlank:false,
			anchor:'100%'
		},
		{
			fieldLabel:'充值方式0在线支付1线下支付2其他方式',
			xtype:'textfield',
			name:'b_member_account_proprietary_his_type',
			allowBlank:false,
			maxLength:4,
			anchor:'100%'
		},
		{
			fieldLabel:'备注',
			xtype:'textfield',
			name:'b_member_account_proprietary_his_log',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'创建日期',
			xtype:'textfield',
			name:'b_member_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'平台操作人',
			xtype:'textfield',
			name:'xt_userinfo_id',
			maxLength:32,
			anchor:'100%'
		}
		]
	});
}
