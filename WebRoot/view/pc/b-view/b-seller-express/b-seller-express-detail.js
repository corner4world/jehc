var bSellerExpressWinDetail;
var bSellerExpressFormDetail;
function detailBSellerExpress(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBSellerExpressFormDetail();
	bSellerExpressWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		headerPosition:'left',
		title:'明细信息',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		items:bSellerExpressFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bSellerExpressWinDetail.show();
	loadFormData(bSellerExpressFormDetail,'../bSellerExpressController/getBSellerExpressById?b_seller_express_id='+ record.items[0].data.b_seller_express_id);
}
function initBSellerExpressFormDetail(){
	bSellerExpressFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'卖家快递编号',
			xtype:'textfield',
			hidden:true,
			name:'b_seller_express_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'卖家编号',
			xtype:'textfield',
			hidden:true,
			name:'b_seller_id',
			id:'b_seller_id_',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'公司名称',
			xtype:'textfield',
			name:'b_seller_express_name',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:"combo",
            store:[["0","正常"],["1","关闭"]],
            emptyText:"请选择",
            mode:"local",
            value:'0',
            triggerAction:"all",
            editable:false,
			hiddenName:'b_seller_express_status',
			allowBlank:false,
			anchor:'25%',
			name:'b_seller_express_status'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_seller_express_ctime',
			anchor:'100%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_seller_express_mtime',
			anchor:'100%'
		}
		]
	});
}
