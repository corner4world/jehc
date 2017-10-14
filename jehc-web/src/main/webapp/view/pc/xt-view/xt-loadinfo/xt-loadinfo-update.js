var xtLoadinfoWinEdit;
var xtLoadinfoFormEdit;
function updateXtLoadinfo(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtLoadinfoFormEdit();
	xtLoadinfoWinEdit = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'编辑信息',
		items:xtLoadinfoFormEdit,
		buttons:[/**{
			text:'保存',
			itemId:'save',
			glyph:0xf0c7,
			handler:function(button){
				submitForm(xtLoadinfoFormEdit,'../xtLoadinfoController/updateXtLoadinfo',grid,xtLoadinfoWinEdit,false,true);
			}
		},**/{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtLoadinfoWinEdit.show();
	loadFormData(xtLoadinfoFormEdit,'../xtLoadinfoController/getXtLoadinfoById?xt_loadinfo_id='+ record.items[0].data.xt_loadinfo_id);
}
function initXtLoadinfoFormEdit(){
	xtLoadinfoFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'4 5 4 5'
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
			anchor:'100%'
		},
		{
			fieldLabel:'载入时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_loadinfo_begtime',
			anchor:'100%'
		},
		{
			fieldLabel:'结束时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'xt_loadinfo_endtime',
			anchor:'100%'
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
