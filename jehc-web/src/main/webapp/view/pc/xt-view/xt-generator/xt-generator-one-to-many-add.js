/////////////一对多生成///////////
var xtGeneratorOneToManyGrid;
var xtGeneratorOneToManyStore;
/////////////选择子表////////////
var oneToManyTableGrid;
var oneToManyTableStore;
var oneToManyTableWin;
/////////////选择子表字段/////////
var oneToManyTableFiledGrid;
var oneToManyTableFiledStore;
var oneToManyTableFiledWin;
/////////////选择子表对应主表的外键字段/////////////
var oneToManyFKeyGrid;
var oneToManyFKeyStore;
var oneToManyFKeyWin;
function initXtGeneratorOneToManyGrid(){
	xtGeneratorOneToManyGrid = Ext.create('Ext.grid.Panel',{
		requires:[
	        'Ext.grid.plugin.CellEditing',
	        'Ext.form.field.Text',
	        'Ext.form.field.TextArea',
	        'Ext.toolbar.TextItem'
	    ],
	    title:'<font color=red>注：子表中文名称、实体包名、数据层包名、业务层包名、控制层包名、子表对应的主表外键、子表字段等必填</font>',
		columnLines:true,
        multiSelect:true,
        border:true,
        autoHeight:true,
        scrollable:true,
		scrollable:'x',
		scrollable:'y',
        store:[],
        plugins:
        [
			Ext.create('Ext.grid.plugin.CellEditing',{
		    	clicksToEdit:1
		    })
		],  
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true,
            plugins:[{  
		        ptype:'gridviewdragdrop',  
		        enableDrop:true//设为false,不允许在本grid中拖动  
	        }]
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'表名',
				dataIndex:'xt_generator_one_to_many_table_name',
				flex:1,
                field:{
                    type:'textfield',
                    readOnly:true
                }
			},
			{
				header:'一对多表Grid信息',
				dataIndex:'xt_generator_ontomany_grid',
				hidden:true,
                field:{
                    type:'textfield',
                    readOnly:true
                }
			},
			{
				header:'中文名称',
				dataIndex:'xt_generator_one_to_many_table_name_zh',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			},
			{
				header:'实体层包路径（Model）',
				dataIndex:'xt_generator_one_to_many_table_model_package',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			},
			{
				header:'数据访问层包路径（Dao）',
				dataIndex:'xt_generator_one_to_many_table_dao_package',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			},
			{
				header:'业务逻辑层包路径（Service）',
				dataIndex:'xt_generator_one_to_many_table_service_package',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			},
			{
				header:'控制层包路径（Web）',
				dataIndex:'xt_generator_one_to_many_table_web_package',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			},
			{
				header:'外键（子表中对应主表编号）',
				flex:1,
				dataIndex:'xt_generator_one_to_many_table_fkey',
                editor:{
                    xtype:'textfield',
                    readOnly:true
                }
			},
			{
				xtype:'actioncolumn',
				align:'center',
				header:'<div style="text-align:center">操作</div>',
		        menuDisabled:true,
		        sortable:false,
		        width:150,
		        items:[{
                    icon:'../deng/images/icons/add.png',
            		tooltip:'外键（子表中对应主表编号）',
		            handler:function(view, recIndex, cellIndex, item, e, record){
		            	oneToManyGridFkeyWin(record);
		            }
                },
                '->',
                {
                    icon:'../deng/images/icons/system.png',
            		tooltip:'设置子表列信息',
		            handler:function(view, recIndex, cellIndex, item, e, record){
		            	oneToManyGridFiledWin(record);
		            }
		         }]
			}
		],
		listeners:{  
		    selectionChange:'selectionChange'
		},
		//选中的记录发生变化过后的事件  
		selectionChange:function(view, records,ob,obj){  
			xtGeneratorOneToManyGrid.down('#del_one_to_many_button').setDisabled(!records.length);
		},
		tbar:[
			 {
				text:'选择子表',
				tooltip:'选择子表',
				scope:this,
				icon:addIcon,
				handler:function(){
				    oneToManyWin();
				}
			 },
			 {  
                  text:'删除',  
                  disabled:true, 
                  icon:delIcon, 
                  itemId:'del_one_to_many_button', 
                  handler:function(){
                  	var model = xtGeneratorOneToManyGrid.getSelectionModel();
					if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					}
					xtGeneratorOneToManyGrid.getStore().remove(xtGeneratorOneToManyGrid.getSelectionModel().getSelection());  
                  } 
             }  
			 ]
	});
}

function oneToManyWin(){
	oneToManyTableStore = getGridJsonStore('../xtGeneratorController/getXtGeneratorTableGridList',[]);
	oneToManyTableGrid = Ext.create('Ext.grid.Panel',{
   	 	columnLines:true,
        multiSelect:true,
        collapsible:false,
        border:true,
        region:'center', 
        layout:'fit',    
        plugins:
        [
			Ext.create('Ext.grid.plugin.CellEditing',{
		    	clicksToEdit:1
		    })
		], 
        xtype:'grid',  
        store:oneToManyTableStore,
        viewConfig:{
            emptyText:'暂无数据',
            stripeRows:true
        },
        loadMask:{
            msg:'正在加载...'
        },
        listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var TABLE_NAME_ = Ext.getCmp('TABLE_NAME').getValue();
            	if(null == TABLE_NAME_ || "" == TABLE_NAME_){
            		msgTishi('请到后台配置选项卡中选择主表！');
            		return;
            	}
				var TABLE_NAME = oneToManyTableGrid.getSelectionModel().selected.items[0].data.TABLE_NAME;
				if(TABLE_NAME_ == TABLE_NAME){
					msgTishi('子表和主表不能相同！');
            		return;
				}
				var TABLE_COMMENT = oneToManyTableGrid.getSelectionModel().selected.items[0].data.TABLE_COMMENT;
				var str = "[<font color=red><br>子表名称:"+TABLE_NAME+"<br>中文名称:"+TABLE_COMMENT+"</font>]";
				Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						for(var i=0;i<xtGeneratorOneToManyGrid.getStore().getCount();i++){
							var xt_generator_one_to_many_table_name = xtGeneratorOneToManyGrid.store.getAt(i).data.xt_generator_one_to_many_table_name;
							if("undefined" != typeof(xt_generator_one_to_many_table_name) && null != xt_generator_one_to_many_table_name && xt_generator_one_to_many_table_name != ""){
								if(xt_generator_one_to_many_table_name == TABLE_NAME){
									msgTishi("您选择的子表已经设置过了，不能再次设置！");
							        return false;
								}
							}
						}
						var model = Ext.create(oneToManyTableGrid.getStore().model);  
					    model.set('xt_generator_one_to_many_table_name',TABLE_NAME);  
					    model.set('xt_generator_one_to_many_table_name_zh',TABLE_COMMENT);
					    xtGeneratorOneToManyGrid.getStore().insert(0, model);  
						oneToManyTableWin.close();
					}
				});
			}
		},
        columns:[
        		{
        			header:'序号',
        			width:77,
		            xtype:'rownumberer'
		        },
        		{
	                header:'表名',
	                widht:280,
	                dataIndex:'TABLE_NAME'
            	},{
	                header:'备注',
	                flex:1,
	                dataIndex:'TABLE_COMMENT'
            	}],
        tbar:[{
            text:'刷 新',
            scope:this,
            icon:refreshIcon,
            handler:function(){
            	var TABLE_NAME = Ext.getCmp('TABLE_NAME').getValue();
            	if(null == TABLE_NAME || "" == TABLE_NAME){
            		msgTishi('请到后台配置选项卡中选择主表');
            		return;
            	}
            	oneToManyTableGrid.getStore().reload();
            }
        }]
	});
	reGetWidthAndHeight();
	oneToManyTableWin =  Ext.create('Ext.Window',{                  
		layout:'fit',                    
		width:clientWidth,                    
		height:clientHeight,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,   
		modal:true,     
		headerPosition:'left',              
		title:'请选择子表信息----请双击即可设置',                    
		items:oneToManyTableGrid, 
		listeners:{
		    minimize:function(win,opts){
		        oneToManyTableWin.collapse();
		    },
		    maximize:function(win,opts){
		        oneToManyTableWin.maximize();
		    }
		}              
	});
	oneToManyTableWin.show();
}
//选择子表对应主表外键字段
function oneToManyGridFkeyWin(record){
	oneToManyFKeyStore = getGridJsonStore('../xtGeneratorController/getXtGeneratorTableColumnListByCondition?xt_generator_tbname='+record.data.xt_generator_one_to_many_table_name,[]);
	oneToManyFKeyGrid = Ext.create('Ext.grid.Panel',{
   	 	columnLines:true,
        multiSelect:true,
        collapsible:false,
        border:true,
        region:'center', 
        layout:'fit',  
        scrollable:true,
		scrollable:'x',
		scrollable:'y',  
        plugins:
        [
			Ext.create('Ext.grid.plugin.CellEditing',{
		    	clicksToEdit:1
		    })
		], 
        store:oneToManyFKeyStore,
        viewConfig:{
            emptyText:'暂无数据',
            stripeRows:true
        },
        loadMask:{
            msg:'正在加载...'
        },
        listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var COLUMN_NAME = oneToManyFKeyGrid.getSelectionModel().selected.items[0].data.COLUMN_NAME;
				var COLUMN_COMMENT = oneToManyFKeyGrid.getSelectionModel().selected.items[0].data.COLUMN_COMMENT;
				var str = "[<font color=red><br>子表字段名称:"+COLUMN_NAME+"<br>中文名称:"+COLUMN_COMMENT+"</font>]";
				Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						record.set('xt_generator_one_to_many_table_fkey',COLUMN_NAME);
						oneToManyFKeyWin.close();
					}
				});
			}
		},
        columns:[
        		{
        			header:'序号',
        			width:77,
		            xtype:'rownumberer'
		        },
        		{
	                header:'字段名称',
	                flex:1,
	                dataIndex:'COLUMN_NAME'
            	},{
	                header:'字段备注[标签显示值可修改]',
	                flex:1,
	                dataIndex:'COLUMN_COMMENT'
            	},{
	                header:'字段类型',
	                flex:1,
	                dataIndex:'DATA_TYPE'
            	},{
	                header:'长度',
	                flex:1,
	                dataIndex:'CHARACTER_MAXIMUM_LENGTH'
            	},{
	                header:'必填',
	                flex:1,
	                dataIndex:'IS_NULLABLE'
            	},{
	                header:'主键',
	                flex:1,
	                dataIndex:'COLUMN_KEY'
            	}],
        tbar:[{
            text:'刷 新',
            scope:this,
            icon:refreshIcon,
            handler:function(){
            	var TABLE_NAME = Ext.getCmp('TABLE_NAME').getValue();
            	if(null == TABLE_NAME || "" == TABLE_NAME){
            		msgTishi('请到后台配置选项卡中选择您要生成的表');
            		return;
            	}
            	oneToManyFKeyStore.proxy.extraParams.xt_generator_tbname = record.data.xt_generator_one_to_many_table_name;
            	oneToManyFKeyGrid.getStore().reload();
            }
        }]
	});
	reGetWidthAndHeight();
	oneToManyFKeyWin = Ext.create('Ext.Window',{                  
		layout:'fit',                    
		width:clientWidth,                    
		height:clientHeight,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,      
		plain:true,   
		modal:true,   
		headerPosition:'left',                
		title:'请选择子表对应主表外键字段信息----请双击即可设置',                    
		items:oneToManyFKeyGrid, 
		listeners:{
		    minimize:function(win,opts){
		        oneToManyFKeyWin.collapse();
		    },
		    maximize:function(win,opts){
		        oneToManyFKeyWin.maximize();
		    }
		}              
	});
	oneToManyFKeyWin.show();
}
//选择子表字段
function oneToManyGridFiledWin(record){
	var table_name = record.data.xt_generator_one_to_many_table_name;
	var xt_generator_ontomany_grid = record.data.xt_generator_ontomany_grid;
	//渲染一下之前保存过的记录
	var data;
	var oldData;
	var isOldStore = false;
	if("undefined" != typeof(xt_generator_ontomany_grid)){
		data = Ext.decode(xt_generator_ontomany_grid);
		oldData = Ext.decode(xt_generator_ontomany_grid);
	}
	var comboPostionData = Ext.create('Ext.data.SimpleStore',{  
	    fields:['value','text'],  
	    data:[['居上','居上'],['居左','居左']]  
	});
	var comboIsHiddenData = Ext.create('Ext.data.SimpleStore',{  
	    fields:['value','text'],  
	    data:[['否','否'],['是','是']]  
	});
	var comboTypeData = Ext.create('Ext.data.SimpleStore',{  
	    fields:['value','text'],  
	    data:[['重置','重置'],['文本域','文本域'],['下拉框','下拉框'],['文件框','文件框']]  
	});
	if(null != xt_generator_ontomany_grid && '' != xt_generator_ontomany_grid){
		oneToManyTableFiledStore = Ext.create('Ext.data.Store',{  
           fields:['COLUMN_NAME','COLUMN_COMMENT','DATA_TYPE','CHARACTER_MAXIMUM_LENGTH','IS_NULLABLE','COLUMN_KEY','column_label_position','column_label_anchor','column_type','xt_script_id','xt_script_text','isHidden'],  
           data:data,  
           proxy:{  
              type:'memory',  
              reader:{  
                  type:'json',  
                  rootProperty:'items'  
              }  
          }  
    	})
    	isOldStore = true;
	}else{
		isOldStore = false;
		oneToManyTableFiledStore = getGridJsonStore('../xtGeneratorController/getXtGeneratorTableColumnListByCondition?table_name='+table_name,[]);
	}
	oneToManyTableFiledGrid = Ext.create('Ext.grid.Panel',{
   	 	columnLines:true,
        multiSelect:true,
        collapsible:false,
        border:true,
        region:'center', 
        layout:'fit',    
        scrollable:true,
		scrollable:'x',
		scrollable:'y',
        plugins:
        [
			Ext.create('Ext.grid.plugin.CellEditing',{
		    	clicksToEdit:1
		    })
		], 
        store:oneToManyTableFiledStore,
        viewConfig:{
            emptyText:'暂无数据',
            stripeRows:true,
            plugins:[{  
		        ptype:'gridviewdragdrop',  
		        enableDrop:true//设为false,不允许在本grid中拖动  
	        }] 
        },
        loadMask:{
            msg:'正在加载...'
        },
        columns:[
       		{
                header:'字段名称',
                flex:1,
                dataIndex:'COLUMN_NAME'
           	},
           	{
                header:'显示标签',
                flex:1,
                editor:{
	                allowBlank:false,
	                xtype:'textfield'
	            },
                dataIndex:'COLUMN_COMMENT'
           	},
           	{
                header:'字段类型',
                width:120,
                dataIndex:'DATA_TYPE'
           	},
           	{
                header:'长度',
                width:50,
                dataIndex:'CHARACTER_MAXIMUM_LENGTH'
           	},
           	{
                header:'必填',
                width:50,
                dataIndex:'IS_NULLABLE'
           	},
           	{
                header:'主键',
                width:100,
                dataIndex:'COLUMN_KEY'
           	},
           	{
                header:'标签位置',
                width:80,
                dataIndex:'column_label_position',
                editor:{
	                xtype:'combo',
                    store:comboPostionData,  
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'value',  
		            displayField:'text',  
		            editable:false,
		            listeners:{
		            	select:function(combo, record,index){
		            		this.setValue(record.data.value);
		            		oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("column_label_position",record.data.text);
		            	}
		            }
	            }
           	},
           	{
                header:'标签宽度',
                width:80,
                dataIndex:'column_label_anchor',
                editor:{
	                allowBlank:false,
	                xtype:'numberfield'
	            }
           	},
           	{
                header:'类型',
                width:120,
                dataIndex:'column_type',
                editor:{
	                allowBlank:false,
	                xtype:'combo',
                    store:comboTypeData,  
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'value',  
		            displayField:'text',  
		            editable:false,
		            listeners:{
		            	select:function(combo, record,index){
		            		if(record.data.value == '重置'){
		            			this.setValue("");
		            			oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("column_type","");
		            			return;
		            		}
		            		var DATA_TYPE = oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].data.DATA_TYPE;
		            		var column_type = record.data.value;
		            		var CHARACTER_MAXIMUM_LENGTH = oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].data.CHARACTER_MAXIMUM_LENGTH;
		            		if(DATA_TYPE == 'VARCHAR' || DATA_TYPE == 'CHAR' || DATA_TYPE == 'TEXT' || DATA_TYPE == 'LONGTEXT' || DATA_TYPE == 'LONGBLOB'){
		            			if('文件框'== column_type && CHARACTER_MAXIMUM_LENGTH <32){
	            					this.setValue('');
	            					msgTishi("你所选的文件框字段长度小于32位，必须大于等于32位才可选择");
	            					return;
		            			}
		            			this.setValue(record.data.value);
		            			oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("column_type",record.data.text);
		            		}else{
		            			this.setValue('');
		            			msgTishi("字段类型为VARCHAR，CHAR时，才可选择该项");
		            			return;
		            		}
		            	}
		            }
	            }
           	},
           	{
                header:'脚本编号',
                flex:1,
                hidden:true,
                dataIndex:'xt_script_id',
                editor:{
	                allowBlank:false,
	                xtype:'textfield'
	            }
           	},
           	{
                header:'脚本',
                flex:1,
                dataIndex:'xt_script_text',
                editor:{
	                xtype:'combo',
					store:xtScriptStore,  
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'xt_script_id',  
		            displayField:'xt_script_title', 
		            listeners:{
		            	select:function(combo, record,index){
		            		var column_type = oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].data.column_type;
		            		if(null != column_type && '' != column_type){
		            			if('下拉框'== column_type){
		            				this.setValue(record.data.xt_script_title);
				            		oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",record.data.xt_script_id);
				            		oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",record.data.xt_script_title);
		            			}else{
		            				this.setValue('');
				            		oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",'');
				            		oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",'');
		            				msgTishi("类型为非下拉框，不能选择该下拉框数据源");
		            				return;
		            			}
		            		}else{
		            			this.setValue('');
			            		oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",'');
			            		oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",'');
	            				msgTishi("类型为空，不能选择该下拉框数据源");
	            				return;
		            		}
		            	}
		            }
	            }
           	},
           	{
                header:'隐藏',
                width:50,
                dataIndex:'isHidden',
                editor:{
	                allowBlank:false,
	                xtype:'combo',
                    store:comboIsHiddenData,  
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'value',  
		            displayField:'text',  
		            editable:false,
		            listeners:{
		            	select:function(combo, record,index){
		            		this.setValue(record.data.value);
		            		oneToManyTableFiledGrid.getSelectionModel().getSelected().items[0].set("isHidden",record.data.text);
		            	}
		            }
	            }
           	}],
        tbar:[{
            text:'刷 新',
            scope:this,
            icon:refreshIcon,
            handler:function(){
            	Ext.Msg.confirm('提示','刷新操作会将您之前该列表信息全部置为初始化状态，确定刷新？',function(btn){
					if(btn == 'yes'){
						var TABLE_NAME = Ext.getCmp('TABLE_NAME').getValue();
		            	if(null == TABLE_NAME || "" == TABLE_NAME){
		            		msgTishi('请到后台配置选项卡中选择您要生成的表');
		            		return;
		            	}
		            	if(isOldStore == false){
		            		oneToManyTableFiledStore.proxy.extraParams.xt_generator_tbname = table_name;
            				oneToManyTableFiledGrid.getStore().reload();
		            	}else{
		            		msgTishi('恢复至上次数据源');
		            		oneToManyTableFiledGrid.getStore().removeAll();
		            		oneToManyTableFiledGrid.getStore().loadData(oldData);
		            	}
					}
				});
            }
        },
        {
            text:'保 存',
            scope:this,
            icon:saveIcon,
            handler:function(){
            	Ext.Msg.confirm('提示','确定保存所选的子表字段？',function(btn){
					if(btn == 'yes'){
						doSelectTableFiled(record);
					}
				});
            }
        }
        ]
	});
	reGetWidthAndHeight();
	oneToManyTableFiledWin = Ext.create('Ext.Window',{                  
		layout:'fit',                    
		width:clientWidth,                    
		height:clientHeight,
		maximized:true, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,    
		plain:true,   
		modal:true,    
		headerPosition:'left',             
		title:'请选择子表字段信息----请双击即可设置',                    
		items:oneToManyTableFiledGrid, 
		listeners:{
		    minimize:function(win,opts){
		        oneToManyTableFiledWin.collapse();
		    },
		    maximize:function(win,opts){
		        oneToManyTableFiledWin.maximize();
		    }
		}              
	});
	oneToManyTableFiledWin.show();
}

//保存子表编辑列信息
function doSelectTableFiled(rec){
	///////////结束验证//////////////
	var jsonArray = [];  
	for(var i=0;i<oneToManyTableFiledGrid.getStore().getCount();i++){
		var record = oneToManyTableFiledGrid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	console.info(rec);
	rec.set('xt_generator_ontomany_grid',Ext.encode(jsonArray));
	oneToManyTableFiledWin.close();
}


//验证并获取一对多编辑列字段JSON规则
function getXtGeneratorOneToManyFormListJSON(){
	///////////开始验证//////////////
	if(xtGeneratorOneToManyGrid.getStore().getCount() == 0){
		msgTishi("一对多表单生成时，子表至少有一条记录!");
	    return false;
	}
	for(var i=0;i<xtGeneratorOneToManyGrid.getStore().getCount();i++){
		var xt_generator_one_to_many_table_name = xtGeneratorOneToManyGrid.store.getAt(i).data.xt_generator_one_to_many_table_name;
		var xt_generator_ontomany_grid = xtGeneratorOneToManyGrid.store.getAt(i).data.xt_generator_ontomany_grid;
		var xt_generator_one_to_many_table_fkey = xtGeneratorOneToManyGrid.store.getAt(i).data.xt_generator_one_to_many_table_fkey;
		var xt_generator_one_to_many_table_model_package = xtGeneratorOneToManyGrid.store.getAt(i).data.xt_generator_one_to_many_table_model_package;
		var xt_generator_one_to_many_table_dao_package = xtGeneratorOneToManyGrid.store.getAt(i).data.xt_generator_one_to_many_table_dao_package;
		var xt_generator_one_to_many_table_service_package = xtGeneratorOneToManyGrid.store.getAt(i).data.xt_generator_one_to_many_table_service_package;
		var xt_generator_one_to_many_table_web_package = xtGeneratorOneToManyGrid.store.getAt(i).data.xt_generator_one_to_many_table_web_package;
		if("undefined" == typeof(xt_generator_one_to_many_table_name) || null == xt_generator_one_to_many_table_name || xt_generator_one_to_many_table_name == ""){
			msgTishi("【一对多表配置】子表信息存在空值，请检查!");
	        return false;
		}
		if("undefined" == typeof(xt_generator_ontomany_grid) || null == xt_generator_ontomany_grid || xt_generator_ontomany_grid == ""){
			msgTishi("【一对多表配置】子表列信息存在空值，请设置子表字段列!");
	        return false;
		}
		if("undefined" == typeof(xt_generator_one_to_many_table_fkey) || null == xt_generator_one_to_many_table_fkey || xt_generator_one_to_many_table_fkey == ""){
			msgTishi("【一对多表配置】外键（子表中对应主表编号）存在空值，请检查!");
	        return false;
		}
		if("undefined" == typeof(xt_generator_one_to_many_table_model_package) || null == xt_generator_one_to_many_table_model_package || xt_generator_one_to_many_table_model_package == ""){
			msgTishi("【一对多表配置】实体层包路径（Model）存在空值，请检查!");
	        return false;
		}
		if("undefined" == typeof(xt_generator_one_to_many_table_dao_package) || null == xt_generator_one_to_many_table_dao_package || xt_generator_one_to_many_table_dao_package == ""){
			msgTishi("【一对多表配置】数据访问层包路径（Dao）存在空值，请检查!");
	        return false;
		}
		if("undefined" == typeof(xt_generator_one_to_many_table_service_package) || null == xt_generator_one_to_many_table_service_package || xt_generator_one_to_many_table_service_package == ""){
			msgTishi("【一对多表配置】业务逻辑层包路径（Service）存在空值，请检查!");
	        return false;
		}
		if("undefined" == typeof(xt_generator_one_to_many_table_web_package) || null == xt_generator_one_to_many_table_web_package || xt_generator_one_to_many_table_web_package == ""){
			msgTishi("【一对多表配置】控制层包路径（Web）存在空值，请检查!");
	        return false;
		}
	}
    ///////////结束验证//////////////
	var jsonArray = [];  
	for(var i=0;i<xtGeneratorOneToManyGrid.getStore().getCount();i++){
		var record = xtGeneratorOneToManyGrid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	return Ext.encode(jsonArray)
}