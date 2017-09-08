var xtKwordsWinAdd;
var xtKwordsFormAdd;
function addXtKwords(){
	initXtKwordsFormAdd();
	xtKwordsWinAdd = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'添加信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtKwordsFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(xtKwordsFormAdd,'../xtKwordsController/addXtKwords',grid,xtKwordsWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtKwordsWinAdd.show();
	
}
function initXtKwordsFormAdd(){
	xtKwordsFormAdd = Ext.create('Ext.FormPanel',{
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
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		items:[
		{
			fieldLabel:'关键字内容',
			xtype:'textareafield',
			name:'xt_kwords_content',
			maxLength:65535,
			anchor:'100%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_kwords_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_kwords_status',
			valueField:'value',
			displayField:'text',
			name:'xt_kwords_status',
			maxLength:10,
			anchor:'40%'
		}
		]
	});
}
