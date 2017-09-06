var xtDepartinfoWinAdd;
var xtDepartinfoFormAdd;
function addXtDepartinfo(xt_departinfo_id,xt_departinfo_parentId,text){
	initXtDepartinfoFormAdd();
	xtDepartinfoWinAdd = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		title:'添加部门信息',
		items:xtDepartinfoFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtDepartinfoFormAdd,'../xtDepartinfoController/addXtDepartinfo',grid,xtDepartinfoWinAdd,false,true,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtDepartinfoWinAdd.show();
	new Ext.util.DelayedTask(function(){  
        Ext.getCmp('xt_departinfo_parentId').setValue(xt_departinfo_id);
		Ext.getCmp('pid').setValue(text);
    }).delay(200);
}
function initXtDepartinfoFormAdd(){
	xtDepartinfoFormAdd = Ext.create('Ext.FormPanel',{
		xtype:'form',
		labelWidth:50,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		fieldDefaults:{
	        labelWidth:70,
	        labelAlign:"left",
	        flex:1,
	        margin:'4 5 4 5'
	    },
		items:[
		{
			fieldLabel:'上级部门',
			xtype:'textfield',
			name:'xt_departinfo_parentId',
			id:'xt_departinfo_parentId',
			allowBlank:false,
			hidden:true,
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'上级部门',
			xtype:'textfield',
			id:'pid',
			allowBlank:false,
			maxLength:32,
			disabled:true,
			anchor:'40%'
		},
		{
			fieldLabel:'部门名称',
			xtype:'textfield',
			name:'xt_departinfo_name',
			allowBlank:false,
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'联系电话',
			xtype:'textfield',
			name:'xt_departinfo_connectTelNo',
			maxLength:12,
			anchor:'40%'
		},
		{
			fieldLabel:'移动电话',
			xtype:'textfield',
			name:'xt_departinfo_connectMobileTelNo',
			maxLength:20,
			anchor:'40%'
		},
		{
			fieldLabel:'传&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;真',
			xtype:'textfield',
			name:'xt_departinfo_faxes',
			maxLength:50,
			anchor:'40%'
		},
		{
			fieldLabel:'部门信息',
			xtype:'textareafield',
			name:'xt_departinfo_desc',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'立成时间',
			xtype:'datefield',
			format:'Y-m-d',
			name:'xt_departinfo_time',
			maxLength:20,
			anchor:'40%'
		},
		{
			fieldLabel:'部门性质',
			xtype:'textfield',
			name:'xt_departinfo_type',
			maxLength:200,
			anchor:'40%'
		}
		]
	});
}
