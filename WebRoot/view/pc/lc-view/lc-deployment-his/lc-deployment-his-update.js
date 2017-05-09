var lcDeploymentHisWinEdit;
var lcDeploymentHisFormEdit;
function updateLcDeploymentHis(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initLcDeploymentHisFormEdit();
	lcDeploymentHisWinEdit = Ext.create('Ext.Window',{
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
		items:lcDeploymentHisFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(lcDeploymentHisFormEdit,'../lcDeploymentHisController/updateLcDeploymentHis',grid,lcDeploymentHisWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	lcDeploymentHisWinEdit.show();
	
	loadFormData(lcDeploymentHisFormEdit,'../lcDeploymentHisController/getLcDeploymentHisById?id='+ record.items[0].data.id);
}
function initLcDeploymentHisFormEdit(){
	lcDeploymentHisFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		/**新方法使用结束**/
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'流程部署Id',
			xtype:'textfield',
			name:'lc_deployment_his_id',
			maxLength:50,
			anchor:'100%'
		},
		{
			fieldLabel:'名称',
			xtype:'textfield',
			name:'lc_deployment_his_name',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'部署时间',
			xtype:'textfield',
			name:'lc_deployment_his_time',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'租户编号',
			xtype:'textfield',
			name:'lc_deployment_his_tenantId',
			maxLength:50,
			anchor:'100%'
		},
		{
			fieldLabel:'流程编号',
			xtype:'numberfield',
			value:'0',
			name:'lc_process_id',
			maxLength:10,
			anchor:'100%'
		}
		]
	});
}
