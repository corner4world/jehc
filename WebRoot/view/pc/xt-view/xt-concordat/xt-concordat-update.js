var xtConcordatWinEdit;
var xtConcordatFormEdit;
function updateXtConcordat(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtConcordatFormEdit();
	xtConcordatWinEdit = Ext.create('Ext.Window',{
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
		items:xtConcordatFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtConcordatFormEdit,'../xtConcordatController/updateXtConcordat',grid,xtConcordatWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtConcordatWinEdit.show();
	loadFormData(xtConcordatFormEdit,'../xtConcordatController/getXtConcordatById?xt_concordat_id='+ record.items[0].data.xt_concordat_id);
}
function initXtConcordatFormEdit(){
	xtConcordatFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'合同编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_concordat_id',
			allowBlank:false,
			maxLength:32,
			anchor:'40%'
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
