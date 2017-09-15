var solrUrlWinDetail;
var solrUrlFormDetail;
function detailSolrUrl(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initSolrUrlFormDetail();
	solrUrlWinDetail = Ext.create('Ext.Window',{
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
		items:solrUrlFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	solrUrlWinDetail.show();
	loadFormData(solrUrlFormDetail,'../solrUrlController/getSolrUrlById?solr_url_id='+ record.items[0].data.solr_url_id);
}
function initSolrUrlFormDetail(){
	solrUrlFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
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
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textarea',
			name:'solr_url_content',
			maxLength:1000,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'textfield',
			name:'solr_url_ctime',
			maxLength:32,
			anchor:'30%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'textfield',
			name:'solr_url_mtime',
			maxLength:32,
			anchor:'30%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'30%'
		}
		]
	});
}
