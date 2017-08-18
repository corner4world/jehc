var bStockWinDetail;
var bStockFormDetail;
function detailBStock(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBStockFormDetail();
	bStockWinDetail = Ext.create('Ext.Window',{
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
		items:bStockFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bStockWinDetail.show();
	loadFormData(bStockFormDetail,'../bStockController/getBStockById?b_stock_id='+ record.items[0].data.b_stock_id);
}
function initBStockFormDetail(){
	bStockFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'库存编号',
			xtype:'textfield',
			hidden:true,
			name:'b_stock_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'商户卖家',
			xtype:'textfield',
			name:'b_seller_name',
			id:'b_seller_name',
			maxLength:32,
			anchor:'60%',
			allowBlank:false,
			readOnly:true,
			listeners:{
				render:function(p){   
			     	p.getEl().on('click',function(p){   
			     		selectBSellerProduct();
				    });
			    }
			}
		},
		{
			fieldLabel:'商户产品',
			xtype:'textfield',
			name:'b_product_name',
			id:'b_product_name',
			maxLength:32,
			anchor:'60%',
			allowBlank:false,
			readOnly:true
		},
		{
			fieldLabel:'可卖数量',
			xtype:'numberfield',
			value:'0',
			name:'b_stock_countable_sell',
			anchor:'40%'
		},
		{
			fieldLabel:'已卖数量',
			xtype:'numberfield',
			value:'0',
			name:'b_stock_locks_number',
			anchor:'40%'
		}
		]
	});
}
