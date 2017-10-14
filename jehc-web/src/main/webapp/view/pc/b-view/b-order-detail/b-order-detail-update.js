var bOrderDetailWinEdit;
var bOrderDetailFormEdit;
function updateBOrderDetail(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initBOrderDetailFormEdit();
	bOrderDetailWinEdit = Ext.create('Ext.Window',{
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
		items:bOrderDetailFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(bOrderDetailFormEdit,'../bOrderDetailController/updateBOrderDetail',grid,bOrderDetailWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bOrderDetailWinEdit.show();
	loadFormData(bOrderDetailFormEdit,'../bOrderDetailController/getBOrderDetailById?b_order_detail_id='+ record.items[0].data.b_order_detail_id);
}
function initBOrderDetailFormEdit(){
	bOrderDetailFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'订单详细编号',
			xtype:'textfield',
			hidden:true,
			name:'b_order_detail_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'商品编号',
			xtype:'textfield',
			name:'b_product_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'卖家编号',
			xtype:'textfield',
			name:'b_seller_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'购物数量',
			xtype:'numberfield',
			value:'0',
			name:'b_order_detail_number',
			anchor:'100%'
		},
		{
			fieldLabel:'购买单价',
			xtype:'numberfield',
			value:'0',
			name:'b_order_detail_price',
			anchor:'100%'
		},
		{
			fieldLabel:'折扣',
			xtype:'textfield',
			name:'b_order_detail_discount',
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_order_detail_ctime',
			anchor:'100%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_order_detail_mtime',
			anchor:'100%'
		}
		]
	});
}
