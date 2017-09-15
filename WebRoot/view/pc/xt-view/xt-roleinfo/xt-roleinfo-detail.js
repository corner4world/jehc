var xtRoleinfoWinDetail;
var xtRoleinfoFormDetail;
function detailXtRoleinfo(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtRoleinfoFormDetail();
	xtRoleinfoWinDetail = Ext.create('Ext.Window',{
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
		items:xtRoleinfoFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtRoleinfoWinDetail.show();
	loadFormData(xtRoleinfoFormDetail,'../xtRoleinfoController/getXtRoleinfoById?xt_role_id='+ record.items[0].data.xt_role_id);
}
function initXtRoleinfoFormDetail(){
	xtRoleinfoFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		labelWidth:50,
		waitMsgTarget:true,
		defaultType:'textfield',
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			readOnly:true,
			margin:'4 5 4 5'
		},
		items:[
		{
			fieldLabel:'角色权限id',
			xtype:'textfield',
			hidden:true,
			name:'xt_role_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'角色名称',
			xtype:'textfield',
			name:'xt_role_name',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			fieldLabel:'角色描述',
			xtype:'textarea',
			name:'xt_role_desc',
			maxLength:50,
			anchor:'100%'
		},
		{
			fieldLabel:'角色类型',
			value:'0',
			name:'xt_role_type',
			anchor:'100%',
            name:"xt_role_type",
            hiddenName:'xt_role_type',
            xtype:"combo",
            store:[["0","平台权限"],["1","业务权限"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false
		}
		]
	});
}
