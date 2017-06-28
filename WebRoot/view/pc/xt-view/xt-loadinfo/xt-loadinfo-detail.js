var xtLoadinfoWinDetail;
var xtLoadinfoFormDetail;
function detailXtLoadinfo(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtLoadinfoFormDetail();
	xtLoadinfoWinDetail = Ext.create('Ext.Window',{
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
		items:xtLoadinfoFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtLoadinfoWinDetail.show();
	loadFormData(xtLoadinfoFormDetail,'../xtLoadinfoController/getXtLoadinfoById?xt_loadinfo_id='+ record.items[0].data.xt_loadinfo_id);
}
function initXtLoadinfoFormDetail(){
	xtLoadinfoFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'ID',
			xtype:'textfield',
			hidden:true,
			name:'xt_loadinfo_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'载加模块',
			xtype:'textfield',
			name:'xt_loadinfo_modules',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'加载时间',
			xtype:'numberfield',
			value:'0',
			name:'xt_loadinfo_time',
			anchor:'40%'
		},
		{
			fieldLabel:'载入时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_loadinfo_begtime',
			anchor:'40%'
		},
		{
			fieldLabel:'结束时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_loadinfo_endtime',
			anchor:'40%'
		},
		{
			fieldLabel:'操&nbsp;作&nbsp;&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'40%'
		}
		]
	});
}
