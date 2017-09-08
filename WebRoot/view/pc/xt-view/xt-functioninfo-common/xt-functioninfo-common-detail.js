var xtFunctioninfoCommonWinDetail;
var xtFunctioninfoCommonFormDetail;
function detailXtFunctioninfoCommon(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtFunctioninfoCommonFormDetail();
	xtFunctioninfoCommonWinDetail = Ext.create('Ext.Window',{
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
		items:xtFunctioninfoCommonFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtFunctioninfoCommonWinDetail.show();
	
	loadFormData(xtFunctioninfoCommonFormDetail,'../xtFunctioninfoCommonController/getXtFunctioninfoCommonById?xt_functioninfo_common_id='+ record.items[0].data.xt_functioninfo_common_id);
}
function initXtFunctioninfoCommonFormDetail(){
	xtFunctioninfoCommonFormDetail = Ext.create('Ext.FormPanel',{
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
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		items:[
		{
			fieldLabel:'公共功能编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_functioninfo_common_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textfield',
			name:'xt_functioninfo_common_title',
			maxLength:100,
			anchor:'40%'
		},
		{
			fieldLabel:'功能地址',
			xtype:'textfield',
			name:'xt_functioninfo_common_url',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'方法名称',
			xtype:'textfield',
			name:'xt_functioninfo_common_method',
			maxLength:200,
			anchor:'40%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_functioninfo_common_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_functioninfo_common_status',
			valueField:'value',
			displayField:'text',
			name:'xt_functioninfo_common_status',
			maxLength:4,
			anchor:'40%'
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textareafield',
			name:'xt_functioninfo_common_content',
			maxLength:800,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_functioninfo_common_ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_functioninfo_common_mtime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'40%'
		}
		]
	});
}
