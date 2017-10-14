var xtUnitWinDetail;
var xtUnitFormDetail;
function detailXtUnit(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtUnitFormDetail();
	xtUnitWinDetail = Ext.create('Ext.Window',{
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
		items:xtUnitFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtUnitWinDetail.show();
	loadFormData(xtUnitFormDetail,'../xtUnitController/getXtUnitById?xt_unit_id='+ record.items[0].data.xt_unit_id);
}
function initXtUnitFormDetail(){
	xtUnitFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'单位编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_unit_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'单位名称',
			xtype:'textfield',
			name:'xt_unitName',
			allowBlank:false,
			maxLength:20,
			anchor:'100%'
		}
		]
	});
}
