var solr_index_sql_store;
var solr_index_sql_grid;
var solrIndexQueryTypeList;
//获取索引查询类型数据字典
solrIndexQueryTypeList = new Ext.data.Store({
	singleton:true, 
	proxy:new Ext.data.HttpProxy( { 
		url:'../solrCoreController/getSolrIndexQueryTypeList',
		reader:new Ext.data.JsonReader({
			root:'items',
			type:'json'
		})
	}),
	fields:['xt_data_dictionary_id', 'xt_data_dictionary_name'],
	autoLoad:true 
});
function initSolrIndexSqlGrid(solr_entity_id){
	solr_index_sql_store = getGridJsonStore('../solrIndexSqlController/getSolrIndexSqlListByCondition',[]);
	solr_index_sql_grid = Ext.create('Ext.grid.Panel',{
		columnLines:true,
        multiSelect:true,
        border:true,
        region:'north',
        title:'sql语句配置',
        height:250,
        plugins:{
			ptype:'cellediting',
        	clicksToEdit:1
		},
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        store:solr_index_sql_store,
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'查询类型ID Hidden',
				dataIndex:'solr_index_sql_id',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'solr_entity_id',
				dataIndex:'solr_entity_id',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'查询类型Hidden',
				dataIndex:'solr_index_sql_type',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'查询语句',
				dataIndex:'solr_index_sql_query',
				flex:1,
                editor:{
                    xtype:'textareafield',
                    height:200,
                    flex:3
                }
			},
			{
				header:'查询类型',
				width:200,
				dataIndex:'solr_index_sql_type_text',
				editor:{
					xtype:'combo',
                    store:solrIndexQueryTypeList, 
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'xt_data_dictionary_id',  
		            displayField:'xt_data_dictionary_name',  
		            editable:false,
		            listeners:{
		            	select:function(combo, record,index){
		            		this.setValue(record.data.xt_data_dictionary_name);
		            		solr_index_sql_grid.getSelectionModel().getSelected().items[0].data.solr_index_sql_type=record.data.xt_data_dictionary_id;
		            	}
		            }
                },
                renderer:function(data,cell,record,rowIndex,columnIndex,store){ 
		            for(var i = 0; i < solrIndexQueryTypeList.totalCount; i++){
		            	var xt_data_dictionary_id = solrIndexQueryTypeList.getAt(i).data.xt_data_dictionary_id
		            	if(xt_data_dictionary_id == record.get('solr_index_sql_type')){
		            		return solrIndexQueryTypeList.getAt(i).data.xt_data_dictionary_name;
		            	}
		            }
		        }
			}
		],
		listeners:{  
		    selectionChange:'selectionChange'  
		},
		//选中的记录发生变化过后的事件  
		selectionChange:function(view, records){  
			solr_index_sql_grid.down('#del_solr_index_button').setDisabled(!records.length);
		},
		tbar:[
			 {
				text:'新一行',
				tooltip:'新一行',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
				    var model = Ext.create(solr_index_sql_grid.getStore().model);  
				    model.set('solr_index_sql_query','');  
				    solr_index_sql_grid.getStore().insert(0, model);  
				}
			 },
			 {  
                  text:'删除',  
                  disabled:true, 
                  icon:delIcon, 
                  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
                  itemId:'del_solr_index_button', 
                  handler:function(){
                  	 del_solr_index_sql_grid();
                  } 
             }  
			 ]
	});
}
//删除
function del_solr_index_sql_grid(){
	var model = solr_index_sql_grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi("请选择要删除的项");
		return;
	}
    Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
       if(btn == 'yes'){  
           solr_index_sql_grid.getStore().remove(solr_index_sql_grid.getSelectionModel().getSelection());  
       }  
    })
}

function getSolrIndexSqlJSON(){
	///////////开始验证//////////////
	if(solr_index_sql_grid.getStore().getCount() == 0){
		msgTishi("执行语句请至少添加一条记录");
	    return false;
	}
	for(var i=0;i<solr_index_sql_grid.getStore().getCount();i++){
		var solr_index_sql_query = solr_index_sql_grid.store.getAt(i).data.solr_index_sql_query;
		var solr_index_sql_type_text = solr_index_sql_grid.store.getAt(i).data.solr_index_sql_type_text;
		var solr_index_sql_type =  solr_index_sql_grid.store.getAt(i).data.solr_index_sql_type;
		if("undefined" == typeof(solr_index_sql_query) || null == solr_index_sql_query || solr_index_sql_query == ""){
			msgTishi("请填写SQL_QUERY");
	        return false;
		}
		if("undefined" == typeof(solr_index_sql_type) || null == solr_index_sql_type || solr_index_sql_type == ""){
			msgTishi("请选择查询类型");
	        return false;
		}
	}
    ///////////结束验证//////////////
	var jsonArray = []; 
	for(var i=0;i<solr_index_sql_grid.getStore().getCount();i++){
		var record = solr_index_sql_grid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	return Ext.encode(jsonArray)
}