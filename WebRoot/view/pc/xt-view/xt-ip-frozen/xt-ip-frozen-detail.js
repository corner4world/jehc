var xtIpFrozenWinDetail;
var xtIpFrozenFormDetail;
function detailXtIpFrozen(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtIpFrozenFormDetail();
	xtIpFrozenWinDetail = Ext.create('Ext.Window',{
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
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtIpFrozenFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtIpFrozenWinDetail.show();
	loadFormData(xtIpFrozenFormDetail,'../xtIpFrozenController/getXtIpFrozenById?xt_ip_frozen_id='+ record.items[0].data.xt_ip_frozen_id);
}
function initXtIpFrozenFormDetail(){
	xtIpFrozenFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			readOnly:true,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'IP冻结编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_ip_frozen_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'I&nbsp;P&nbsp;地&nbsp;址',
			xtype:'textfield',
			name:'xt_ip_frozen_address',
			allowBlank:false,
			maxLength:30,
			anchor:'60%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			name:'xt_ip_frozen_status',
			value:'0',
			anchor:'60%',
            hiddenName:'xt_ip_frozen_status',
            xtype:"combo",
            store:[["0","正常"],["1","冻结"],["2","黑名单"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false
		},
		{
			fieldLabel:'内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容',
			xtype:'textareafield',
			name:'xt_ip_frozen_content',
			maxLength:500,
			anchor:'100%'
		}
		]
	});
}
