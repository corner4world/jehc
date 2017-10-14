var solr_index_store;
var solr_index_grid;
var solrIndexTypeList;
//获取索引类型数据字典
solrIndexTypeList = new Ext.data.Store({
	singleton:true, 
	proxy:new Ext.data.HttpProxy( { 
		url:'../solrCoreController/getSolrIndexTypeList',
		reader:new Ext.data.JsonReader({
			root:'items',
			type:'json'
		})
	}),
	fields:['xt_data_dictionary_id', 'xt_data_dictionary_name'],
	autoLoad:true 
});
function initSolrIndexGrid(solr_core_id){
	var comboData = Ext.create('Ext.data.SimpleStore',{  
	    fields:['value','text'],  
	    data:[['true','是'],['false','否']]  
	});
	solr_index_store = getGridJsonStore('../solrIndexController/getSolrIndexListByCondition?solr_core_id='+solr_core_id,[]);
	solr_index_grid = Ext.create('Ext.grid.Panel',{
		columnLines:true,
        multiSelect:false,
        border:true,
        region:'west',
        title:'索引配置',
        plugins:{
			ptype:'cellediting',
        	clicksToEdit:1
		},
        store:solr_index_store,
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'索引ID Hidden',
				dataIndex:'solr_index_id',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'索引类型Hidden',
				dataIndex:'solr_index_type',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'是否被索引hidden',
				dataIndex:'solr_index_indexed',
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'是否存储内容hidden',
				dataIndex:'solr_index_stored',
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'是否为多值类型hidden',
				dataIndex:'solr_index_multiValued',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield'
                }
			},
			{
				header:'索引字段',
				dataIndex:'solr_index_name',
				flex:1,
                field:{
                    type:'textfield',
                    listeners:{
				  		'blur':function(obj,record,index){
	                    	for(var i=0;i<solr_index_grid.getStore().getCount();i++){
								var solr_index_name = solr_index_grid.store.getAt(i).data.solr_index_name;
								if("undefined" == typeof(solr_index_name) || null == solr_index_name || solr_index_name == ""){
									msgTishi("请输入索引！");
									return;
								}
							}
						}
				  	}
                }
			},
			{
				header:'索引类型',
				flex:1,
				dataIndex:'solr_index_type_text',
				editor:{
					xtype:'combo',
                    store:solrIndexTypeList,  
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            id:'solr_index_type_text',
		            valueField:'xt_data_dictionary_id',  
		            displayField:'xt_data_dictionary_name',  
		            editable:false, 
		            allowBlank:false, 
		            /**
		            tpl:Ext.create('Ext.XTemplate',
		            	'<tpl for=".">',
		            		'<div class="x-boundlist-item">{value}-{text}</div>',
		            	'</tpl>'
		            ),
		            displayTpl:Ext.create('Ext.XTemplate',
		            	'<tpl for=".">',
		            		'{value}-{text}',
		            	'</tpl>'
		            ),
		            **/
		            listeners:{
		            	select:function(combo, record,index){
		            		this.setValue(record.data.xt_data_dictionary_name);
            				solr_index_grid.getSelectionModel().getSelected().items[0].data.solr_index_type=record.data.xt_data_dictionary_id;
		            	}
		            } 
                },  
		        renderer:function(data,cell,record,rowIndex,columnIndex,store){ 
		            for(var i = 0; i < solrIndexTypeList.totalCount; i++){
		            	var xt_data_dictionary_id = solrIndexTypeList.getAt(i).data.xt_data_dictionary_id
		            	if(xt_data_dictionary_id == record.get('solr_index_type')){
		            		return solrIndexTypeList.getAt(i).data.xt_data_dictionary_name;
		            	}
		            }
		        }
			},
			{
				header:'是否被索引',
				flex:1,
				dataIndex:'solr_index_indexed_text',
				editor:{  
		            xtype:'combo',
                    store:comboData,  
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'value',  
		            displayField:'text',  
		            editable:false,
		            listeners:{
		            	select:function(combo, record,index){
		            		this.setValue(record.data.text);
		            		solr_index_grid.getSelectionModel().getSelected().items[0].data.solr_index_indexed=record.data.value;
		            	}
		            }
			    },  
		        renderer:function(data,cell,record,rowIndex,columnIndex,store){ 
		        	for(var i = 0; i < comboData.totalCount; i++){
		            	var value = comboData.getAt(i).data.value
		            	if(value == record.get('solr_index_indexed')){
		            		return comboData.getAt(i).data.text;
		            	}
		            }
		        }
			},
			{
				header:'是否存储内容',
				flex:1,
				dataIndex:'solr_index_stored_text',
				editor:{  
		            xtype:'combo',
                    store:comboData,  
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'value',  
		            displayField:'text',  
		            editable:false,
		            listeners:{
		            	select:function(combo, record,index){
		            		this.setValue(record.data.text);
		            		solr_index_grid.getSelectionModel().getSelected().items[0].data.solr_index_stored=record.data.value;
		            	}
		            }
			    },
			    renderer:function(data,cell,record,rowIndex,columnIndex,store){ 
		        	for(var i = 0; i < comboData.totalCount; i++){
		            	var value = comboData.getAt(i).data.value
		            	if(value == record.get('solr_index_stored')){
		            		return comboData.getAt(i).data.text;
		            	}
		            }
		        }  
			},
			{
				header:'是否为多值类型',
				flex:1,
				dataIndex:'solr_index_multiValued_text',
				editor:{  
		            xtype:'combo',
                    store:comboData,  
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'value',  
		            displayField:'text',  
		            editable:false,
		            listeners:{
		            	select:function(combo, record,index){
		            		this.setValue(record.data.text);
		            		solr_index_grid.getSelectionModel().getSelected().items[0].data.solr_index_multiValued=record.data.value;
		            	}
		            }
			    },
			    renderer:function(data,cell,record,rowIndex,columnIndex,store){ 
		        	for(var i = 0; i < comboData.totalCount; i++){
		            	var value = comboData.getAt(i).data.value
		            	if(value == record.get('solr_index_multiValued')){
		            		return comboData.getAt(i).data.text;
		            	}
		            }
		        }  
			},
			{
				header:'备注',
				flex:2,
				dataIndex:'solr_index_remark',
				field:{
                    type:'textfield'
                }
			}
		],
		listeners:{  
		    selectionChange:'selectionChange',
		    afteredit:function(val){ 
		    	/**
            	var solr_index_type_text = val.record.get("solr_index_type_text");
            	val.record.set("solr_index_type", solr_index_type_text);
            	**/
            } 
		},
		//选中的记录发生变化过后的事件  
		selectionChange:function(view, records){   
		    solr_index_grid.down('#del_solr_index_sql_button').setDisabled(!records.length);
		},
		tbar:[
			 {
				text:'新一行',
				tooltip:'新一行',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
				    var model = Ext.create(solr_index_grid.getStore().model);  
				    model.set('solr_index_name','');  
				    solr_index_grid.getStore().insert(0, model);  
				}
			 },
			 {  
                  text:'删除',  
                  disabled:true, 
                  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
                  icon:delIcon, 
                  itemId:'del_solr_index_sql_button', 
                  handler:function(){
                  	 del_solr_index_grid();
                  } 
             }  
			 ],
		bbar:getGridBBar(solr_index_store)
	});
	initSolrSort();
}

//删除一行
function del_solr_index_grid(){
	/**
	var selection = solr_index_grid.getSelectionModel(), message = ''; 
	**/
	var model = solr_index_grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi("请选择要删除的项");
		return;
	}
    Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
       if(btn == 'yes'){  
           solr_index_grid.getStore().remove(solr_index_grid.getSelectionModel().getSelection());  
       }  
    }) 
}

function getSolrIndexJSON(){
	///////////开始验证//////////////
	if(solr_index_grid.getStore().getCount() == 0){
		msgTishi("您还没有添加索引呢？请添加至少一条！");
	    return false;
	}
	var ary = new Array();
	for(var i=0;i<solr_index_grid.getStore().getCount();i++){
		var solr_index_name = solr_index_grid.store.getAt(i).data.solr_index_name;
		ary.push(solr_index_name);
	}
	var nary=ary.sort();
	for(var i=0;i<ary.length;i++){
		if(nary[i]==nary[i+1]){
			msgTishi("您输入的索引存在如下项重复:<br><font color=red>[<br>"+nary[i]+"<br>]</font>");
			return false;
		}
	}
	for(var i=0;i<solr_index_grid.getStore().getCount();i++){
		var solr_index_name = solr_index_grid.store.getAt(i).data.solr_index_name;
		var solr_index_type = solr_index_grid.store.getAt(i).data.solr_index_type;
		var solr_index_indexed = solr_index_grid.store.getAt(i).data.solr_index_indexed;
		var solr_index_stored = solr_index_grid.store.getAt(i).data.solr_index_stored;
		var solr_index_multiValued = solr_index_grid.store.getAt(i).data.solr_index_multiValued;
		if("undefined" == typeof(solr_index_name) || null == solr_index_name || solr_index_name == ""){
			msgTishi("请填写索引字段");
	        return false;
		}
		if("undefined" == typeof(solr_index_type) || null == solr_index_type || solr_index_type == ""){
			msgTishi("请选择索引类型");
	        return false;
		}
		if("undefined" == typeof(solr_index_indexed) || null == solr_index_indexed || solr_index_indexed == ""){
			msgTishi("请选择是否被索引");
	        return false;
		}
		if("undefined" == typeof(solr_index_stored) || null == solr_index_stored || solr_index_stored == ""){
			msgTishi("请选择是否存储内容");
	        return false;
		}
		if("undefined" == typeof(solr_index_multiValued) || null == solr_index_multiValued || solr_index_multiValued == ""){
			msgTishi("请选择是否为多值类型");
	        return false;
		}
	}
    ///////////结束验证//////////////
	var jsonArray = []; 
	for(var i=0;i<solr_index_grid.getStore().getCount();i++){
		var record = solr_index_grid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	return Ext.encode(jsonArray)
}
/**初始化右键**/
function initSolrSort(){
	var cmenu = new Ext.create('Ext.menu.Menu',{
		items:[{
			text:'设置排序',
			glyph:0xf044,
			handler:function(){initSolrSortWin();}
		},
		{
			text:'更多属性',
			glyph:0xf044,
			handler:function(){initSolrAttributeWin();}
		}]
	});
	solr_index_grid.on('itemcontextmenu',function(view,record,item,index,e){ 
		e.preventDefault(); 
		cmenu.showAt(e.getXY());
	});
}

function initSolrSortWin(){
	var solr_index_id = solr_index_grid.getSelectionModel().getSelected().items[0].data.solr_index_id;
	var solrSortWin = Ext.create('Ext.window.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		closeAction:'destroy',
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'排序',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../solrSortController/loadSolrSort?solr_index_id='+solr_index_id+'"></iframe>'
	});
	solrSortWin.show();
}

function initSolrAttributeWin(){
	var solr_index_id = solr_index_grid.getSelectionModel().getSelected().items[0].data.solr_index_id;
	var solrAttributeWin = Ext.create('Ext.window.Window',{
		layout:'fit',
		closeAction:'destroy',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'属性',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../solrIndexAttributeController/loadSolrIndexAttribute?solr_index_id='+solr_index_id+'"></iframe>'
	});
	solrAttributeWin.show();
}
