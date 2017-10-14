var bCartDetailWinEdit;
var bCartDetailFormEdit;
function updateBCartDetail(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initBCartDetailFormEdit();
	bCartDetailWinEdit = Ext.create('Ext.Window',{
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
		items:bCartDetailFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(bCartDetailFormEdit,'../bCartDetailController/updateBCartDetail',grid,bCartDetailWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bCartDetailWinEdit.show();
	loadFormData(bCartDetailFormEdit,'../bCartDetailController/getBCartDetailById?b_cart_detail_id='+ record.items[0].data.b_cart_detail_id);
}
function initBCartDetailFormEdit(){
	bCartDetailFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'购物车明细编号',
			xtype:'textfield',
			hidden:true,
			name:'b_cart_detail_id',
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
			name:'b_cart_detail_number',
			anchor:'100%'
		},
		{
			fieldLabel:'购买单价',
			xtype:'numberfield',
			value:'0',
			name:'b_cart_detail_price',
			anchor:'100%'
		},
		{
			fieldLabel:'折扣',
			xtype:'textfield',
			name:'b_cart_detail_discount',
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_cart_detail_ctime',
			anchor:'100%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_cart_detail_mtime',
			anchor:'100%'
		}
		]
	});
}
