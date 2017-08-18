var bCartOrderAddressWinDetail;
var bCartOrderAddressFormDetail;
function detailBCartOrderAddress(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBCartOrderAddressFormDetail();
	bCartOrderAddressWinDetail = Ext.create('Ext.Window',{
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
		items:bCartOrderAddressFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bCartOrderAddressWinDetail.show();
	loadFormData(bCartOrderAddressFormDetail,'../bCartOrderAddressController/getBCartOrderAddressById?b_cart_order_address_id='+ record.items[0].data.b_cart_order_address_id);
}
function initBCartOrderAddressFormDetail(){
	bCartOrderAddressFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'购物车订单常用地址',
			xtype:'textfield',
			hidden:true,
			name:'b_cart_order_address_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'详细地址',
			xtype:'textfield',
			name:'b_cart_order_address_address',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'省份',
			xtype:'textfield',
			name:'xt_provinceID',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'城市',
			xtype:'textfield',
			name:'xt_cityID',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'区县',
			xtype:'textfield',
			name:'xt_districtID',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'会员编号',
			xtype:'textfield',
			name:'b_member_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'状态0正常1已作废',
			xtype:'textfield',
			name:'b_cart_order_address_status',
			maxLength:2,
			anchor:'100%'
		}
		]
	});
}
