var bOrderPaystore;
var bOrderPayGrid;
function initBOrderPayGrid(b_order_id){
	bOrderPaystore = getGridJsonStore('../bOrderPayController/getBOrderPayListByCondition?b_order_id='+b_order_id,[]);
	bOrderPayGrid = Ext.create('Ext.grid.Panel',{
		xtype:'panel',
		store:bOrderPaystore,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		selType:'checkboxmodel',
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'序号',
				width:77,
				xtype:'rownumberer'
			},
			{
				header:'支付金额',
				dataIndex:'b_order_pay_money'
			},
			{
				header:'是否全额支付',
				dataIndex:'b_order_pay_isall',
				renderer:function(value){
					if(value == 0){
						return "是";
					}else if(value==1){
						return "否";
					}else{
						return '---';
					}
				}
			},
			{
				header:'支付时间',
				width:150,
				dataIndex:'b_order_pay_ctime'
			}
			]
	});
}
