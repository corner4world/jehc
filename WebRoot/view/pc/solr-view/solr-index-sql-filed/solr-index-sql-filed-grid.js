var solr_index_sql_filed_store;
var solr_index_sql_filed_grid;
function initSolrIndexSqlFiledGrid(solr_entity_id){
	solr_index_sql_filed_store = getGridJsonStore('../solrIndexSqlFiledController/getSolrIndexSqlFiledListByCondition',[]);
	solr_index_sql_filed_grid = Ext.create('Ext.grid.Panel',{
		columnLines:true,
        multiSelect:true,
        border:true,
        region:'center',
        title:'映射索引',
        collapsible:false,
        plugins:{
			ptype:'cellediting',
        	clicksToEdit:1
		},  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        store:solr_index_sql_filed_store,
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'SQL查询结果编号ID Hidden',
				dataIndex:'solr_index_sql_filed_id',
				flex:1,
				hidden:true,
                editor:{
                    xtype:'textfield'
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
				header:'表列名',
				dataIndex:'solr_index_sql_filed_name',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			},
			{
				header:'对应索引名称',
				dataIndex:'solr_index_filed_name',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			},
			{
				header:'中文说明',
				dataIndex:'solr_index_sql_filed_zh',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			}
		],
		listeners:{  
		    selectionChange:'selectionChange'  
		},
		//选中的记录发生变化过后的事件  
		selectionChange:function(view, records){  
			solr_index_sql_filed_grid.down('#del_solr_index_filed_button').setDisabled(!records.length);
		},
		tbar:[
			 {
				text:'新一行',
				tooltip:'新一行',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
				    var model = Ext.create(solr_index_sql_filed_grid.getStore().model);  
				    model.set('solr_index_sql_filed_name','');  
				    solr_index_sql_filed_grid.getStore().insert(0, model);  
				}
			 },
			 {  
                  text:'删除',  
                  disabled:true, 
                  icon:delIcon, 
                  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
                  itemId:'del_solr_index_filed_button', 
                  handler:function(){
                  	 del_solr_index_sql_filed_grid();
                  } 
             }  
			 ]
	});
}
//删除
function del_solr_index_sql_filed_grid(){
	var model = solr_index_sql_filed_grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi("请选择要删除的项");
		return;
	}
    Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
       if(btn == 'yes'){  
           solr_index_sql_filed_grid.getStore().remove(solr_index_sql_filed_grid.getSelectionModel().getSelection());  
       }  
    })
}
function getSolrIndexSqlFiledJSON(){
	///////////开始验证//////////////
	if(solr_index_sql_filed_grid.getStore().getCount() == 0){
		msgTishi("数据库与索引映射字段请至少添加一条记录");
	    return false;
	}
	for(var i=0;i<solr_index_sql_filed_grid.getStore().getCount();i++){
		var solr_index_sql_filed_name = solr_index_sql_filed_grid.store.getAt(i).data.solr_index_sql_filed_name;
		var solr_index_filed_name = solr_index_sql_filed_grid.store.getAt(i).data.solr_index_filed_name;
		var solr_index_sql_filed_zh = solr_index_sql_filed_grid.store.getAt(i).data.solr_index_sql_filed_zh;
		if("undefined" == typeof(solr_index_sql_filed_name) || null == solr_index_sql_filed_name || solr_index_sql_filed_name == ""){
			msgTishi("请填写表列名");
	        return false;
		}
		if("undefined" == typeof(solr_index_filed_name) || null == solr_index_filed_name || solr_index_filed_name == ""){
			msgTishi("请填写对应索引名称");
	        return false;
		}
		if("undefined" == typeof(solr_index_sql_filed_zh) || null == solr_index_sql_filed_zh || solr_index_sql_filed_zh == ""){
			msgTishi("请填写中文说明");
	        return false;
		}
	}
    ///////////结束验证//////////////
	var jsonArray = [];  
	for(var i=0;i<solr_index_sql_filed_grid.getStore().getCount();i++){
		var record = solr_index_sql_filed_grid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	return Ext.encode(jsonArray)
}