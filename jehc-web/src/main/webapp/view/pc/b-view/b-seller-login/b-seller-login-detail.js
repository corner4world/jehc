var bSellerLoginWinDetail;
var bSellerLoginFormDetail;
function detailBSellerLogin(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBSellerLoginFormDetail();
	bSellerLoginWinDetail = Ext.create('Ext.Window',{
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
		items:bSellerLoginFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bSellerLoginWinDetail.show();
	loadFormData(bSellerLoginFormDetail,'../bSellerLoginController/getBSellerLoginById?b_seller_login_id='+ record.items[0].data.b_seller_login_id);
}
function initBSellerLoginFormDetail(){
	bSellerLoginFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'卖家账号编号',
			xtype:'textfield',
			hidden:true,
			name:'b_seller_login_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'卖家登陆账号',
			xtype:'textfield',
			name:'b_seller_login_name',
			maxLength:64,
			anchor:'100%'
		},
		{
			fieldLabel:'卖家登陆密码',
			xtype:'textfield',
			name:'b_seller_login_pwd',
			maxLength:64,
			anchor:'100%'
		},
		{
			fieldLabel:'卖家状态0正常1禁用',
			xtype:'textfield',
			name:'b_seller_login_status',
			maxLength:2,
			anchor:'100%'
		}
		]
	});
}
