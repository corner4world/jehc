var bSellerAccountWinDetail;
var bSellerAccountFormDetail;
function detailBSellerAccount(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBSellerAccountFormDetail();
	bSellerAccountWinDetail = Ext.create('Ext.Window',{
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
		items:bSellerAccountFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bSellerAccountWinDetail.show();
	loadFormData(bSellerAccountFormDetail,'../bSellerAccountController/getBSellerAccountById?b_seller_account_id='+ record.items[0].data.b_seller_account_id);
}
function initBSellerAccountFormDetail(){
	bSellerAccountFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'卖家账户编号',
			xtype:'textfield',
			hidden:true,
			name:'b_seller_account_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'卖家编号',
			xtype:'textfield',
			name:'b_seller_id',
			maxLength:32,
			anchor:'100%'
		}
		]
	});
}
