var store;
var grid;
var expander;
var solrSchemaTempletList;
var solrDataConfigList
Ext.onReady(function(){
	//获取SolrSchema模板配置信息
	solrSchemaTempletList = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy( { 
			url:'../solrSchemaTempletController/getSolrSchemaTempletList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['solr_schema_templet_id', 'solr_schema_templet_title'],
		autoLoad:true 
	});
	
	//获取SolrDataConfig配置文件信息
	solrDataConfigList = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy( { 
			url:'../solrDataConfigController/getSolrDataConfigList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['solr_data_config_id', 'solr_data_config_title'],
		autoLoad:true 
	});
	
	store = getGridJsonStore('../solrCoreController/getSolrCoreListByCondition?solr_url_id='+$("#solr_url_id_").val(),[]);
	expander = Ext.create('Ext.ux.RowExpander',{
	    rowBodyTpl:Ext.create('Ext.XTemplate',
	    	'<div id="solr_entity{solr_core_id}-body" style="border:0px solid #5fa2dd;width:602px"></div>'
	    ),
	    lazyRender:true,  
       	enableCaching:false 
	});
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
		selType:'checkboxmodel',
		plugins:expander,
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true,
			menuDisabled:false,
			enableTextSelection:true//可以复制单元格文字
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'序号',
				width:77,
				xtype:'rownumberer'
			},
			{
				header:'实例名称',
				sortable:false,
				menuDisabled:true,
				dataIndex:'solr_core_name'
			},
			{
				header:'配置地址',
				flex:true,
				sortable:false,
				menuDisabled:true,
				dataIndex:'solr_url_url'
			},
			{
				header:'创建时间',
				sortable:false,
				menuDisabled:true,
				dataIndex:'solr_core_ctime'
			},
			{
				header:'修改时间',
				sortable:false,
				menuDisabled:true,
				dataIndex:'solr_core_uptime'
			},
			{
				header:'操作人',
				sortable:false,
				menuDisabled:true,
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'操作',
				sortable:false,
				menuDisabled:true,
				columns:[
				{
					header:'增量导入',
					align:'center',
					xtype:'widgetcolumn',
					width:100,
					sortable:false,
					menuDisabled:true,
					widget:{
		                xtype:'button',
		                text:'增量导入',
		                handler:function(rec){	
		                	 Ext.MessageBox.confirm('提示', '确定增量导入选项的实例数据吗？', function(btn) {  
						       if(btn == 'yes'){ 
								var solr_core_name = rec.getWidgetRecord().data.solr_core_name;
			  					var solr_url_id = rec.getWidgetRecord().data.solr_url_id;
			  					var solr_document_id = rec.getWidgetRecord().data.solr_document_id;
						    	var params = {solr_core_name:solr_core_name,solr_url_id:solr_url_id,solr_document_id:solr_document_id};
								ajaxRequest('../solrCoreController/dataimport',grid,params,'正在执行导入索引！请稍后...');
						       }  
						    }) 
					    }
		            }
				},
				{
					header:'全量导入',
					align:'center',
					xtype:'widgetcolumn',
					width:100,
					sortable:false,
					menuDisabled:true,
					widget:{
		                xtype:'button',
		                text:'全量导入',
		                handler:function(rec){	
		                	Ext.MessageBox.confirm('提示', '确定全量导入该实例数据吗？', function(btn) {  
						        if(btn == 'yes'){ 
				  					var solr_core_name = rec.getWidgetRecord().data.solr_core_name;
				  					var solr_url_id = rec.getWidgetRecord().data.solr_url_id;
				  					var solr_document_id = rec.getWidgetRecord().data.solr_document_id;
							    	var params = {solr_core_name:solr_core_name,solr_url_id:solr_url_id,solr_document_id:solr_document_id};
									ajaxRequest('../solrCoreController/fullimport',grid,params,'正在执行导入索引！请稍后...');
								}
							}) 
					    }
		            }
				},
				{
					header:'索引',
					align:'center',
					xtype:'widgetcolumn',
					width:100,
					sortable:false,
					menuDisabled:true,
					widget:{
		                xtype:'button',
		                text:'导入、映射索引',
		                handler:function(rec){	
		  					var solr_core_name = rec.getWidgetRecord().data.solr_core_name;
		  					var solr_url_id = rec.getWidgetRecord().data.solr_url_id;
		  					showSchemaDataConfigWin(solr_core_name,solr_url_id);
					    }
		            }
				}]
			}
		],
		tbar:[
			 {
				tooltip:'添加',
				text:'添加',
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addSolrCore();
				}
			 },
			 {
				tooltip:'编辑',
				text:'编辑',
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateSolrCore();
				}
			 },
			 {
				tooltip:'配置拷贝索引字段',
				text:'配置拷贝索引字段',
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					loadSolrFiledCopy();
				}
			 },
			 {
				tooltip:'删除',
				text:'删除',
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delSolrCore();
				}
			 },
			 {
				tooltip:'返回',
				text:'返回',
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../solrUrlController/loadSolrUrl";
				}
			 },
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailSolrCore();
					}
				 },
				 '-',
				 {
					text:'创建一级实体',
					glyph:0xf016,
					handler:function(){addSolrEntity(0,'暂无')}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportSolrCore(grid,'../solrCoreController/exportSolrCore');
					}
				 },
				 '-',
				 {
					text:'全 选',
					tooltip:'全 选',
					glyph:0xf046,
					handler:function(){selectAll(grid);}
				 },
				 {
					text:'反 选',
					tooltip:'反 选',
					glyph:0xf096,
					handler:function(){unSelectAll(grid);}
				 },
				 '-',
				 {
					text:'刷 新',
					tooltip:'刷 新',
					glyph:0xf021,
					handler:function(){
						refreshGrid(grid);
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store),
		listeners:{
			'afterrender':function(grid){
				grid.view.on('expandBody', function (rowNode, record, expandRow, eOpts){
					initSolrEntity(record.data.solr_core_id,record.data.solr_document_id); 
                });
                grid.view.on('collapsebody', function (rowNode, record, expandRow, eOpts){
                	$("#solr_entity"+record.data.solr_core_id).empty();
                });
            },
			'rowdblclick':function(grid, rowIndex, e){
				detailSolrCore();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
	/**
	grid.on("headerclick", function(ct,column,e,t,opts) {
         expendRows();
    });
    **/
	/**调用右键**/
	initRight();
});
/**删除操作**/
function delSolrCore(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var solr_core_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == solr_core_id){
			solr_core_id=model.selected.items[i].data.solr_core_id;
		}else{
			solr_core_id=solr_core_id+","+model.selected.items[i].data.solr_core_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {solr_core_id:solr_core_id};
			ajaxRequest('../solrCoreController/delSolrCore',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copySolrCore(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {solr_core_id:record.items[0].data.solr_core_id};
			ajaxRequest('../solrCoreController/copySolrCore',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportSolrCore(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addSolrCoreItem',
			handler:function(){addSolrCore();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateSolrCoreItem',
			handler:function(){updateSolrCore();}
		},
		{
			text:'配置拷贝索引字段',
			tooltip:'配置拷贝索引字段',
			scope:this,
			glyph:0xf044,
			handler:function(){loadSolrFiledCopy();}
		 },{
			text:'删 除',
			glyph:0xf014,
			id:'delSolrCoreItem',
			handler:function(){delSolrCore();}
		},'-',{
			text:'明 细',
			glyph:0xf03b,
			id:'detailSolrCoreItem',
			handler:function(){detailSolrCore();}
		},'-',{
			text:'创建一级实体',
			glyph:0xf016,
			handler:function(){addSolrEntity(0,'暂无');}
		},'-',{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportSolrCore(grid,'../solrCoreController/exportSolrCore');
			}
		},'-',{
			text:'全 选',
			glyph:0xf046,
			handler:function(){selectAll(grid);}
		},{
			text:'反 选',
			glyph:0xf096,
			handler:function(){unSelectAll(grid);}
		},'-',{
			text:'刷 新',
			glyph:0xf021,
			handler:function(){refreshGrid(grid);}
		}]
	});
	initrightgridcontextmenu(grid,contextmenu,['updateSolrCoreItem','delSolrCoreItem','detailSolrCoreItem']);
	/**
	store.on('load',function(thiz, options){
		expendRows();
    });
    **/
}
//展开符合某个条件的行 
/**
function expendRows(){ 
	for(var i=0;i<store.data.length;i++){ 
		var record = store.getAt(i);//循环遍历每一行 
		initSolrEntity(record.data.solr_core_id,record.data.solr_document_id);
		expander.toggleRow(i,record);
	} 
}
**/
function initSolrEntity(solr_core_id,solr_document_id){
	var solr_entity_store = Ext.create('Ext.data.TreeStore',{
    	root:{
			name:'一级',
			id:'0',
			expanded:true
		},
		/**此处一定要设置否则全部展开节点无效**/
		autoLoad:false,
        proxy:{
            type:'ajax',
            method:'post',
			url:'../solrCoreController/getSolrEntityListByCondition',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',expanded:true,solr_document_id:solr_document_id}
        }
    });
	Ext.create('Ext.tree.Panel', {
		renderTo:'solr_entity'+solr_core_id+'-body',
        collapsible:false,
        loadMask:true,
        useArrows:false,
        rootVisible:false,
        store:solr_entity_store,
        autoSctroll:true,
        animate:false,
        columnLines:true,
        frame:true,
        width:700,
        bufferedRenderer:false,
        title:'实体列表',
        headerPosition:'left',
        viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
        columns:[{
            xtype:'treecolumn',
            text:'名称',
            sortable:true,
            dataIndex:'text'
        },
        {
            text:'备注',
            dataIndex:'content'
        },
        {
            header:'创建人',
            dataIndex:'integerappend'
        },
        {
        	header:'操 作',
        	columns:[{
				header:'创建子实体',
				align:'center',
				xtype:'widgetcolumn',
				width:140,
				widget:{
					xtype:'button',
	                text:'创建子实体',
	                listeners:{
					    render:function(rec) {
					        var record = rec.getWidgetRecord();
					        var id = record.data.id;
					        rec.setText("创建子实体");
					    } 
					}, 
	                handler:function(rec){
	                	var id = rec.getWidgetRecord().data.id;
	                	var text = rec.getWidgetRecord().data.text;
	                	addSolrEntity(id,text);
				    }
	            }
			},
			{
				header:'编 辑',
				align:'center',
				xtype:'widgetcolumn',
				width:140,
				widget:{
					xtype:'button',
	                text:'编 辑',
	                listeners:{
					    render:function(rec) {
					        var record = rec.getWidgetRecord();
					        var type = record.data.pid;
					        rec.setText("编 辑");
					    } 
					}, 
	                handler:function(rec){
	                	var id = rec.getWidgetRecord().data.id;
	                	var text = rec.getWidgetRecord().parentNode.data.name;
	                	updateSolrEntity(id);
				    }
	            }
			}]
		}]
    });
}

var solrTransformerWin;
var solrTransformerGrid;
var solrTransformerStore;
function initSolrTransformer(){
	solrTransformerStore = getGridJsonStore('../solrCoreController/getSolrTransformerList',[]);
	solrTransformerGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:solrTransformerStore,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
		selType:'checkboxmodel',
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'序号',
				width:77,
				xtype:'rownumberer'
			},
			{
				header:'格式转换器名称',
				flex:1,
				dataIndex:'xt_data_dictionary_name'
			}
		],
		dockedItems:[{
	        xtype:'toolbar',
	        dock:'bottom',
	        ui:'footer',
	        layout:{
	            pack:'center'
	        },
	        items:[{
	            minWidth:80,
	            text:'保存',
				handler:function(button){
					var model = solrTransformerGrid.getSelectionModel();
					if(model.selected.length == 0){
						msgTishi('请选择');
						return;
					}
					var xt_data_dictionary_name;
					var xt_data_dictionary_id;
					for(var i = 0; i < model.selected.length; i++){
						if(null == xt_data_dictionary_id){
							xt_data_dictionary_id = model.selected.items[i].data.xt_data_dictionary_id;
							xt_data_dictionary_name="【"+model.selected.items[i].data.xt_data_dictionary_name+"】";
						}else{
							xt_data_dictionary_id = xt_data_dictionary_id+","+model.selected.items[i].data.xt_data_dictionary_id;
							xt_data_dictionary_name=xt_data_dictionary_name+","+"【"+model.selected.items[i].data.xt_data_dictionary_name+"】";
						}
					}
					var str = "[<font color=red><br>格式转换器:"+xt_data_dictionary_name+"<br></font>]";
					Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
						if(btn == 'yes'){
							Ext.getCmp('solr_entity_transformer').setValue(xt_data_dictionary_id);
							Ext.getCmp('solr_entity_transformer_text').setValue(xt_data_dictionary_name);
							solrTransformerWin.close();
						}
					});
				}
	        },{
	            minWidth:80,
	            text:'关闭',
				handler:function(button){
					solrTransformerWin.close();
				}
	        }]
	    }],
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var xt_data_dictionary_name = solrTransformerGrid.getSelectionModel().selected.items[0].data.xt_data_dictionary_name;
				var xt_data_dictionary_id = solrTransformerGrid.getSelectionModel().selected.items[0].data.xt_data_dictionary_id;
				var str = "[<font color=red><br>格式转换器:"+xt_data_dictionary_name+"<br></font>]";
				Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						Ext.getCmp('solr_entity_transformer').setValue(xt_data_dictionary_id);
						Ext.getCmp('solr_entity_transformer_text').setValue(xt_data_dictionary_name);
						solrTransformerWin.close();
					}
				});
			}
		}
	});
	solrTransformerWin = Ext.create('Ext.window.Window',{
		layout:'fit',
		width:600,                    
		height:400, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'选择格式转换器----请双击即可设置',
		items:[solrTransformerGrid]
	});
	solrTransformerWin.show();
}

//配置索引字段
function loadSolrFiledCopy(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要配置的一项！');
		return;
	}
	reGetWidthAndHeight();
	var solrFiledCopyWin = Ext.create('Ext.window.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'配置索引拷贝字段',
		headerPosition:'left',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../solrFiledCopyController/loadSolrFiledCopy?solr_core_id='+record.items[0].data.solr_core_id+'"></iframe>'
	});
	solrFiledCopyWin.show();
}