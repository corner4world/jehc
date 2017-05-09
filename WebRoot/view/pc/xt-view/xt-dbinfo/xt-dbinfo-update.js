var xtDbinfoWinEdit;
var xtDbinfoFormEdit;
function updateXtDbinfo(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtDbinfoFormEdit();
	xtDbinfoWinEdit = Ext.create('Ext.Window',{
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
		items:xtDbinfoFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtDbinfoFormEdit,'../xtDbinfoController/updateXtDbinfo',grid,xtDbinfoWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtDbinfoWinEdit.show();
	loadFormData(xtDbinfoFormEdit,'../xtDbinfoController/getXtDbinfoById?xt_dbinfo_id='+ record.items[0].data.xt_dbinfo_id);
}
function initXtDbinfoFormEdit(){
	xtDbinfoFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		labelWidth:50,
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
			fieldLabel:'数据库信息表',
			xtype:'textfield',
			hidden:true,
			name:'xt_dbinfo_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'数据库名',
			xtype:'textfield',
			name:'xt_dbinfoName',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'用&nbsp;&nbsp;户&nbsp;名',
			xtype:'textfield',
			name:'xt_dbinfoUName',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码',
			xtype:'textfield',
			name:'xt_dbinfoPwd',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'IP&nbsp;地&nbsp;&nbsp;址',
			xtype:'textfield',
			name:'xt_dbinfoIp',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'端&nbsp;&nbsp;口&nbsp;号',
			xtype:'textfield',
			name:'xt_dbinfoPort',
			maxLength:255,
			anchor:'100%'
		}
		]
	});
}
