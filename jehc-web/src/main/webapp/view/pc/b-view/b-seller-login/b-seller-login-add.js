var bSellerLoginWinAdd;
var bSellerLoginFormAdd;
function addBSellerLogin(){
	initBSellerLoginFormAdd();
	bSellerLoginWinAdd = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'添加信息',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		items:bSellerLoginFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(bSellerLoginFormAdd,'../bSellerLoginController/addBSellerLogin',grid,bSellerLoginWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bSellerLoginWinAdd.show();
}
function initBSellerLoginFormAdd(){
	bSellerLoginFormAdd = Ext.create('Ext.FormPanel',{
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
