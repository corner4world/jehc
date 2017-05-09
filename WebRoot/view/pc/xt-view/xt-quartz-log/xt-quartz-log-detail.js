var xtQuartzLogWinDetail;
var xtQuartzLogFormDetail;
function detailXtQuartzLog(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtQuartzLogFormDetail();
	xtQuartzLogWinDetail = Ext.create('Ext.Window',{
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
		items:xtQuartzLogFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtQuartzLogWinDetail.show();
	loadFormData(xtQuartzLogFormDetail,'../xtQuartzLogController/getXtQuartzLogById?xt_quartz_log_id='+ record.items[0].data.xt_quartz_log_id);
}
function initXtQuartzLogFormDetail(){
	xtQuartzLogFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'xt_quartz_log_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'名&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;称',
			xtype:'textfield',
			name:'xt_quartz_log_name',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'调度内容',
			xtype:'textareafield',
			name:'xt_quartz_log_content',
			maxLength:500,
			anchor:'100%'
		},
		{
			fieldLabel:'开始时间',
			xtype:'textfield',
			name:'xt_quartz_log_ctime',
			maxLength:30,
			anchor:'100%'
		},
		{
			fieldLabel:'结束时间',
			xtype:'textfield',
			name:'xt_quartz_log_etime',
			maxLength:30,
			anchor:'100%'
		},
		{
			fieldLabel:'运行标识',
			xtype:'textfield',
			name:'xt_quartz_log_flag',
			maxLength:20,
			anchor:'100%'
		}
		]
	});
}
