var xtPlatformWinEdit;
var xtPlatformFormEdit;
function updateXtPlatform(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtPlatformFormEdit();
	xtPlatformWinEdit = Ext.create('Ext.Window',{
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
		items:xtPlatformFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtPlatformFormEdit,'../xtPlatformController/updateXtPlatform',grid,xtPlatformWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtPlatformWinEdit.show();
	
	loadFormData(xtPlatformFormEdit,'../xtPlatformController/getXtPlatformById?xt_platform_id='+ record.items[0].data.xt_platform_id);
}
function initXtPlatformFormEdit(){
	xtPlatformFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;键',
			xtype:'textfield',
			hidden:true,
			name:'xt_platform_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textfield',
			name:'xt_platform_title',
			maxLength:100,
			anchor:'80%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_platform_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_platform_status',
			valueField:'value',
			displayField:'text',
			name:'xt_platform_status',
			maxLength:10,
			anchor:'40%'
		},
		{
			fieldLabel:'操&nbsp;&nbsp;作&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_id',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备',
			xtype:'textareafield',
			name:'xt_platform_remark',
			maxLength:500,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_platform_ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_platform_mtime',
			maxLength:19,
			anchor:'40%'
		}
		]
	});
}
