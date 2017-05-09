var solrIndexAttributeWinEdit;
var solrIndexAttributeFormEdit;
function updateSolrIndexAttribute(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initSolrIndexAttributeFormEdit();
	solrIndexAttributeWinEdit = Ext.create('Ext.Window',{
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
		items:solrIndexAttributeFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(solrIndexAttributeFormEdit,'../solrIndexAttributeController/updateSolrIndexAttribute',grid,solrIndexAttributeWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	solrIndexAttributeWinEdit.show();
	
	loadFormData(solrIndexAttributeFormEdit,'../solrIndexAttributeController/getSolrIndexAttributeById?solr_index_attribute_id='+ record.items[0].data.solr_index_attribute_id);
}
function initSolrIndexAttributeFormEdit(){
	solrIndexAttributeFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:130,
			labelAlign:'right',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'solr_index_attribute_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'facet',
			xtype:'textfield',
			name:'solr_index_attribute_facet',
			maxLength:20,
			anchor:'100%'
		},
		{
			fieldLabel:'权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;重',
			xtype:'numberfield',
			value:'0',
			name:'solr_index_attribute_boost',
			maxLength:12,
			anchor:'40%'
		},
		{
			fieldLabel:'拼&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;写',
			xtype:'textfield',
			name:'solr_index_attribute_spellcheck',
			maxLength:20,
			anchor:'100%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:SOLR_INDEX_ATTRIBUTE_STATUS_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'solr_index_attribute_status',
			valueField:'value',
			displayField:'text',
			name:'solr_index_attribute_status',
			maxLength:10,
			anchor:'40%'
		},
		{
			fieldLabel:'是否高亮',
			xtype:'combo',
			emptyText:'请选择',
			store:sor_index_highlight_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'sor_index_highlight',
			valueField:'value',
			displayField:'text',
			name:'sor_index_highlight',
			maxLength:10,
			anchor:'40%'
		},
		{
			fieldLabel:'是否参与过滤查询',
			xtype:'combo',
			emptyText:'请选择',
			store:sor_index_filter_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'sor_index_filter',
			valueField:'value',
			displayField:'text',
			name:'sor_index_filter',
			maxLength:10,
			anchor:'40%'
		},
		{
			fieldLabel:'是否参与查询',
			xtype:'combo',
			emptyText:'请选择',
			store:SOLR_INDEX_ATTRIBUTE_ISSEARCH_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'solr_index_attribute_issearch',
			valueField:'value',
			displayField:'text',
			name:'solr_index_attribute_issearch',
			maxLength:20,
			anchor:'40%'
		},
		{
			fieldLabel:'条件类型',
			xtype:'combo',
			emptyText:'请选择',
			store:sor_index_attribute_type_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'solr_index_attribute_type',
			valueField:'value',
			displayField:'text',
			name:'solr_index_attribute_type',
			maxLength:20,
			anchor:'40%'
		},
		{
			fieldLabel:'索引编号',
			xtype:'textfield',
			name:'solr_index_id',
			allowBlank:false,
			hidden:true,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'solr_index_attribute_ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'solr_index_attribute_mtime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;&nbsp;者',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			allowBlank:false,
			maxLength:32,
			anchor:'40%'
		}
		]
	});
}
