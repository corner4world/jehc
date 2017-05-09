var solrUrlWinAdd;
var solrUrlFormAdd;
function addSolrUrl(){
	initSolrUrlFormAdd();
	solrUrlWinAdd = Ext.create('Ext.Window',{
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
		items:solrUrlFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(solrUrlFormAdd,'../solrUrlController/addSolrUrl',grid,solrUrlWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	solrUrlWinAdd.show();
}
function initSolrUrlFormAdd(){
	solrUrlFormAdd = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'访问URL',
			xtype:'textfield',
			name:'solr_url_url',
			maxLength:1000,
			anchor:'100%'
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textarea',
			name:'solr_url_content',
			maxLength:1000,
			anchor:'100%'
		}
		]
	});
}
