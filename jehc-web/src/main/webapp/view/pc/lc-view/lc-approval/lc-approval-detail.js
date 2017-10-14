var lcApprovalWinDetail;
var lcApprovalFormDetail;
function detailLcApproval(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initLcApprovalFormDetail();
	lcApprovalWinDetail = Ext.create('Ext.Window',{
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
		items:lcApprovalFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	lcApprovalWinDetail.show();
	
	loadFormData(lcApprovalFormDetail,'../lcApprovalController/getLcApprovalById?lc_approval_id='+ record.items[0].data.lc_approval_id);
}
function initLcApprovalFormDetail(){
	lcApprovalFormDetail = Ext.create('Ext.FormPanel',{
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
			readOnly:true,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'lc_approval_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'批审状态',
			xtype:'combo',
			emptyText:'请选择',
			store:lc_process_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'lc_status_id',
			valueField:'value',
			displayField:'text',
			name:'lc_status_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'申请编号',
			xtype:'textfield',
			name:'lc_apply_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'审批内容',
			xtype:'textareafield',
			name:'lc_approval_remark',
			maxLength:65535,
			anchor:'100%'
		},
		{
			fieldLabel:'任务编号',
			xtype:'textfield',
			name:'taskId',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'审批时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'lc_approval_time',
			maxLength:19,
			anchor:'100%'
		},
		{
			fieldLabel:'批审人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'100%'
		}
		]
	});
}
