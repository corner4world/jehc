var solrDataConfigWinDetail;
var solrDataConfigFormDetail;
function detailSolrDataConfig(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initSolrDataConfigFormDetail();
	solrDataConfigWinDetail = Ext.create('Ext.Window',{
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
		items:solrDataConfigFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	solrDataConfigWinDetail.show();
	
	loadFormData(solrDataConfigFormDetail,'../solrDataConfigController/getSolrDataConfigById?solr_data_config_id='+ record.items[0].data.solr_data_config_id);
}
function initSolrDataConfigFormDetail(){
	solrDataConfigFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		fieldDefaults:{
			labelWidth:110,
			labelAlign:'right',
			flex:1,
			readOnly:true,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'solr_data_config_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'标题',
			xtype:'textfield',
			name:'solr_data_config_title',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'数据源连接配置',
			xtype:'textfield',
			name:'solr_data_config_datasource',
			maxLength:800,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'solr_data_config_ctime',
			maxLength:19,
			anchor:'100%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'solr_data_config_mtime',
			maxLength:19,
			anchor:'100%'
		},
		{
			fieldLabel:'操作人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			emptyText:'请选择',
			store:SOLR_DataConfig_COMBO_STORE,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'solr_data_config_status',
			valueField:'value',
			displayField:'text',
			name:'solr_data_config_status',
			maxLength:20,
			anchor:'100%'
		},
		{
			fieldLabel:'配置内容',
			xtype:'textareafield',
			name:'solr_data_config_content',
			maxLength:65535,
			anchor:'100%'
		}
		]
	});
}
