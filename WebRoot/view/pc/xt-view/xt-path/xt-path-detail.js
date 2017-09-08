var xtPathWinDetail;
var xtPathFormDetail;
function detailXtPath(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtPathFormDetail();
	xtPathWinDetail = Ext.create('Ext.Window',{
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
		items:xtPathFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtPathWinDetail.show();
	loadFormData(xtPathFormDetail,'../xtPathController/getXtPathById?xt_path_id='+ record.items[0].data.xt_path_id);
}
function initXtPathFormDetail(){
	xtPathFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'ID',
			xtype:'textfield',
			hidden:true,
			name:'xt_path_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'常量名称',
			xtype:'textfield',
			name:'xt_path_name',
			allowBlank:false,
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'常量路径',
			xtype:'textfield',
			name:'xt_path',
			allowBlank:false,
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'常&nbsp;量&nbsp;&nbsp;键',
			xtype:'textfield',
			name:'xt_value',
			allowBlank:false,
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:"类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型",
            name:"xt_type",
            hiddenName:'xt_type',
            xtype:"combo",
            anchor:'40%',
            store:[["1","平台模块"],["2","业务模块"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false
		},
		{
			fieldLabel:'建创时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'xt_time',
			allowBlank:false,
			anchor:'40%'
		}
		]
	});
}
