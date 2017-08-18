var bMemberAccountProprietaryWinEdit;
var bMemberAccountProprietaryFormEdit;
function updateBMemberAccountProprietary(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initBMemberAccountProprietaryFormEdit();
	bMemberAccountProprietaryWinEdit = Ext.create('Ext.Window',{
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
				win.collapse();
			}
		},
		items:bMemberAccountProprietaryFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(bMemberAccountProprietaryFormEdit,'../bMemberAccountProprietaryController/updateBMemberAccountProprietary',grid,bMemberAccountProprietaryWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bMemberAccountProprietaryWinEdit.show();
	loadFormData(bMemberAccountProprietaryFormEdit,'../bMemberAccountProprietaryController/getBMemberAccountProprietaryById?b_member_account_proprietary_id='+ record.items[0].data.b_member_account_proprietary_id);
}
function initBMemberAccountProprietaryFormEdit(){
	bMemberAccountProprietaryFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'专有账户编号',
			xtype:'textfield',
			hidden:true,
			name:'b_member_account_proprietary_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'会员编号',
			xtype:'textfield',
			name:'b_member_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'会员账号编号',
			xtype:'textfield',
			name:'b_member_account_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'状态0正常1锁定2禁用',
			xtype:'textfield',
			name:'b_member_account_proprietary_status',
			allowBlank:false,
			maxLength:4,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_member_account_proprietary_ctime',
			allowBlank:false,
			anchor:'100%'
		},
		{
			fieldLabel:'金额',
			xtype:'numberfield',
			value:'0',
			name:'b_member_account_proprietary_money',
			allowBlank:false,
			anchor:'100%'
		},
		{
			fieldLabel:'操作人编号',
			xtype:'textfield',
			name:'xt_userinfo_id',
			maxLength:32,
			anchor:'100%'
		}
		]
	});
}
