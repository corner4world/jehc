var xtGeneratorForbidtableWinDetail;
var xtGeneratorForbidtableFormDetail;
function detailXtGeneratorForbidtable(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtGeneratorForbidtableFormDetail();
	xtGeneratorForbidtableWinDetail = Ext.create('Ext.Window',{
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
		items:xtGeneratorForbidtableFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtGeneratorForbidtableWinDetail.show();
	
	loadFormData(xtGeneratorForbidtableFormDetail,'../xtGeneratorForbidtableController/getXtGeneratorForbidtableById?xt_generator_forbidtable_id='+ record.items[0].data.xt_generator_forbidtable_id);
}
function initXtGeneratorForbidtableFormDetail(){
	xtGeneratorForbidtableFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'主键编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_generator_forbidtable_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
			xtype:'textfield',
			name:'xt_generator_forbidtable_table',
			allowBlank:false,
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textareafield',
			name:'xt_generator_forbidtable_remark',
			maxLength:500,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_generator_forbidtable_ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_generator_forbidtable_mtime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'操&nbsp;作&nbsp;&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'100%'
		}
		]
	});
}
