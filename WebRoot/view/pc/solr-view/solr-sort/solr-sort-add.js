var solrSortWinAdd;
var solrSortFormAdd;
function addSolrSort(){
	initSolrSortFormAdd();
	solrSortWinAdd = Ext.create('Ext.Window',{
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
		items:solrSortFormAdd,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(solrSortFormAdd,'../solrSortController/addSolrSort',grid,solrSortWinAdd,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	solrSortWinAdd.show();
	Ext.getCmp('solr_index_id').setValue($('#solr_index_id_').val());
}
function initSolrSortFormAdd(){
	solrSortFormAdd = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'排序名称',
			xtype:'textfield',
			name:'solr_sort_name',
			maxLength:200,
			anchor:'100%'
		},
		{
			fieldLabel:'排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序',
			xtype:"combo",
          	queryMode:'local', 
			store:solr_sort_combo_store,
			name:'solr_sort_code',
			triggerAction:"all",
            editable:false,
            emptyText:'请选择',
            valueField:"value",
            displayField:"text",
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'选用权重',
			xtype:'numberfield',
			value:'0',
			name:'solr_sort_useboost',
			maxLength:3,
			anchor:'100%'
		},
		{
			fieldLabel:'索引编号',
			xtype:'textfield',
			id:'solr_index_id',
			name:'solr_index_id',
			allowBlank:false,
			maxLength:32,
			hidden:true,
			anchor:'100%'
		}
		]
	});
}
