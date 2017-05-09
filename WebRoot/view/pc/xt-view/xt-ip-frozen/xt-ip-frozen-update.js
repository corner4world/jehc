var xtIpFrozenWinEdit;
var xtIpFrozenFormEdit;
function updateXtIpFrozen(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtIpFrozenFormEdit();
	xtIpFrozenWinEdit = Ext.create('Ext.Window',{
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
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtIpFrozenFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtIpFrozenFormEdit,'../xtIpFrozenController/updateXtIpFrozen',grid,xtIpFrozenWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtIpFrozenWinEdit.show();
	loadFormData(xtIpFrozenFormEdit,'../xtIpFrozenController/getXtIpFrozenById?xt_ip_frozen_id='+ record.items[0].data.xt_ip_frozen_id);
}
function initXtIpFrozenFormEdit(){
	xtIpFrozenFormEdit = Ext.create('Ext.FormPanel',{
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
