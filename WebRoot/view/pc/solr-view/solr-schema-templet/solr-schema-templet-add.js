var solrSchemaTempletWinAdd;
var solrSchemaTempletFormAdd;
function addSolrSchemaTemplet(){
	initSolrSchemaTempletFormAdd();
	solrSchemaTempletWinAdd = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximized:true,
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
		items:solrSchemaTempletFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(solrSchemaTempletFormAdd,'../solrSchemaTempletController/addSolrSchemaTemplet',grid,solrSchemaTempletWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	solrSchemaTempletWinAdd.show();
	/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
	/**初始化附件右键菜单结束**/

}
function initSolrSchemaTempletFormAdd(){
	solrSchemaTempletFormAdd = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textfield',
			name:'solr_schema_templet_title',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'模板内容',
			xtype:'textareafield',
			name:'solr_schema_templet_content',
			maxLength:2147483647,
			height:200,
			anchor:'100%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:SOLR_SCHEMA_COMBO_STORE,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'solr_schema_templet_status',
			valueField:'value',
			displayField:'text',
			name:'solr_schema_templet_status',
			maxLength:20,
			anchor:'40%'
		}
		]
	});
}
