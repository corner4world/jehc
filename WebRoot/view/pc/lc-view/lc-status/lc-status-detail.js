var lcStatusWinDetail;
var lcStatusFormDetail;
function detailLcStatus(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initLcStatusFormDetail();
	lcStatusWinDetail = Ext.create('Ext.Window',{
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
		items:lcStatusFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	lcStatusWinDetail.show();
	loadFormData(lcStatusFormDetail,'../lcStatusController/getLcStatusById?lc_status_id='+ record.items[0].data.lc_status_id);
}
function initLcStatusFormDetail(){
	lcStatusFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'流程状态编号',
			xtype:'textfield',
			hidden:true,
			name:'lc_status_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'状态名称',
			xtype:'textfield',
			name:'lc_status_name',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textareafield',
			name:'lc_status_remark',
			maxLength:800,
			anchor:'100%'
		},
		{
			fieldLabel:'流程常量',
			xtype:'textfield',
			name:'lc_status_constant',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'textfield',
			name:'lc_status_ctime',
			maxLength:30,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'textfield',
			name:'lc_status_mtime',
			maxLength:30,
			anchor:'40%'
		},
		{
			fieldLabel:'操&nbsp;&nbsp;作&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'40%'
		}
		]
	});
}
