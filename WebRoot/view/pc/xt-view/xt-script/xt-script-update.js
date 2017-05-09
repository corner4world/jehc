var xtScriptWinEdit;
var xtScriptFormEdit;
function updateXtScript(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initXtScriptFormEdit();
	xtScriptWinEdit = Ext.create('Ext.Window',{
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
		items:xtScriptFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtScriptFormEdit,'../xtScriptController/updateXtScript',grid,xtScriptWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtScriptWinEdit.show();
	loadFormData(xtScriptFormEdit,'../xtScriptController/getXtScriptById?xt_script_id='+ record.items[0].data.xt_script_id);
}
function initXtScriptFormEdit(){
	xtScriptFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'脚本编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_script_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textfield',
			name:'xt_script_title',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述',
			xtype:'textareafield',
			name:'xt_script_content',
			maxLength:800,
			anchor:'100%'
		},
		{
			fieldLabel:'脚&nbsp;&nbsp;本&nbsp;键',
			xtype:'textfield',
			name:'xt_script_key',
			allowBlank:false,
			maxLength:200,
			anchor:'40%'
		},
		{
			fieldLabel:'脚本内容',
			xtype:'textareafield',
			name:'xt_script_text',
			maxLength:65535,
			height:200,
			anchor:'100%'
		},
		{
			fieldLabel:'脚本类型',
			name:'xt_script_type',
			xtype:"combo",
            store:[["1","javaScript"],["2","Sql"],["3","html"],["4","其他"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false,
			anchor:'40%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			name:'xt_script_status',
			xtype:"combo",
            store:[["0","正常"],["1","禁用"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false,
			anchor:'40%'
		},
		{
			fieldLabel:'参与配置',
			name:'xt_script_search_filed',
			xtype:"combo",
            store:[["0","是"],["1","否"]],
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false,
			maxLength:4,
			anchor:'40%'
		},
		{
			fieldLabel:'真&nbsp;&nbsp;实&nbsp;值',
			xtype:'textfield',
			name:'xt_script_valuefield',
			allowBlank:false,
			maxLength:200,
			anchor:'40%'
		},
		{
			fieldLabel:'显&nbsp;&nbsp;示&nbsp;值',
			xtype:'textfield',
			name:'xt_script_displayfield',
			allowBlank:false,
			maxLength:200,
			anchor:'40%'
		}
		]
	});
}
