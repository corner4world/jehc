var solrUrlWinEdit;
var solrUrlFormEdit;
function updateSolrUrl(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initSolrUrlFormEdit();
	solrUrlWinEdit = Ext.create('Ext.Window',{
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
		items:solrUrlFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(solrUrlFormEdit,'../solrUrlController/updateSolrUrl',grid,solrUrlWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	solrUrlWinEdit.show();
	loadFormData(solrUrlFormEdit,'../solrUrlController/getSolrUrlById?solr_url_id='+ record.items[0].data.solr_url_id);
}
function initSolrUrlFormEdit(){
	solrUrlFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'solr_url_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'访问URL',
			xtype:'textfield',
			name:'solr_url_url',
			maxLength:1000,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'textfield',
			hidden:true,
			name:'solr_url_ctime',
			maxLength:32,
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
