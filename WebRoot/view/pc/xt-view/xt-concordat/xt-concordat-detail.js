var xtConcordatWinDetail;
var xtConcordatFormDetail;
function detailXtConcordat(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtConcordatFormDetail();
	xtConcordatWinDetail = Ext.create('Ext.Window',{
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
		items:xtConcordatFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtConcordatWinDetail.show();
	loadFormData(xtConcordatFormDetail,'../xtConcordatController/getXtConcordatById?xt_concordat_id='+ record.items[0].data.xt_concordat_id);
}
function initXtConcordatFormDetail(){
	xtConcordatFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'合同编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_concordat_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'合同名称',
			xtype:'textfield',
			name:'xt_concordatName',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'合同描述',
			xtype:'textarea',
			name:'xt_concordatDesc',
			maxLength:50,
			anchor:'100%'
		}
		]
	});
}
