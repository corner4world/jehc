//生成代码
var xtGeneratorWinAdd;
var xtGeneratorFormAdd;
var xtGeneratorColumnGrid;
var xtGeneratorColumnStore;
var xtGeneratorSearchFiledGrid;
var xtGeneratorSearchFiledStore;
var xtScriptStore;
var xtGeneratorGridListColumnStore;
var xtGeneratorGridListColumnGrid;
var xtGeneratorTab;
function addXtGenerator(generatorFlag){
	//读取下拉框数据
	xtGeneratorTableStore = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy( { 
			url:'../xtGeneratorController/getXtGeneratorTableList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['TABLE_NAME', 'TABLE_COMMENT'],
		autoLoad:true 
	});
	//读取字段下拉框
	xtGeneratorTableColumnStore = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy( { 
			url:'../xtGeneratorController/getXtGeneratorTableColumnList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['COLUMN_NAME', 'COLUMN_COMMENT'],
		autoLoad:true 
	});
	//读取下拉框数据
	xtScriptStore = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy( { 
			url:'../xtScriptController/getXtScriptList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['xt_script_id', 'xt_script_title'],
		autoLoad:true 
	});
	xtGeneratorTableStore.load();
	initXtGeneratorFormAdd();
	reGetWidthAndHeight();
	xtGeneratorWinAdd =  Ext.create('Ext.Window',{                  
		layout:'fit',                    
		width:clientWidth*0.95,                    
		height:clientHeight*0.95,
		//maximized:true, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,    
		plain:true,   
		modal:true,                 
		title:'生成平台代码快速开发',                    
		items:xtGeneratorFormAdd, 
		headerPosition:'left',
		listeners:{
		    minimize:function(win,opts){
		        //xtGeneratorWinAdd.collapse();
		        if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
		    },
		    maximize:function(win,opts){
		        xtGeneratorWinAdd.maximize();
		    }
		},  
		buttons:[
		{  
            text:'保存',  
            itemId:'save',  
            handler:function(button){ 
            	var params;
            	if(getXtGeneratorGridListJSON() == false){
            		return;
            	}
            	if(getXtGeneratorFormListJSON() == false){
            		return;
            	}
            	//如果是一对多则获取子表JOSN
            	if(gValue('is_one_to_many')==1){
            		//验证子表JSON
            		if(getXtGeneratorOneToManyFormListJSON() == false){
	            		return;
	            	}
	            	params = {xtGeneratorSearchFileJSON:encodeURI(getXtGeneratorSearchFileJSON()),xtGeneratorGridListJSON:encodeURI(getXtGeneratorGridListJSON()),xtGeneratorTableColumnFormJson:encodeURI(getXtGeneratorFormListJSON()),xtGeneratorOneToManyFormListJSON:getXtGeneratorOneToManyFormListJSON()}
            	}else{
            		params = {xtGeneratorSearchFileJSON:encodeURI(getXtGeneratorSearchFileJSON()),xtGeneratorGridListJSON:encodeURI(getXtGeneratorGridListJSON()),xtGeneratorTableColumnFormJson:encodeURI(getXtGeneratorFormListJSON()),xtGeneratorOneToManyFormListJSON:''}
            	}
            	submitFormIncludeParams(xtGeneratorFormAdd,'../xtGeneratorController/addXtGenerator',grid,xtGeneratorWinAdd,false,true,params);
            }  
        },
        {  
            text:'关闭',  
            itemId:'close',  
            handler:function(button){  
                button.up('window').close();  
            }  
        }]                
	});
	xtGeneratorWinAdd.show(); 
	if(generatorFlag == 0){
		Ext.getCmp('one_to_many_typeFlag').hide();
		xtGeneratorWinAdd.setTitle("生成平台代码快速开发-<font color='red'>单表快速生成</font>");
	}else if(generatorFlag == 1){
		sValue('is_one_to_many',1);
		xtGeneratorWinAdd.setTitle("生成平台代码快速开发-<font color='red'>一对多快速生成</font>");
		initXtGeneratorOneToManyGrid();
		xtGeneratorTab.add({
			title:'<font color="red">一对多表配置</font>',
			items:xtGeneratorOneToManyGrid
		});
	}
}

function initXtGeneratorTab(){
 	xtGeneratorTab = Ext.create('Ext.TabPanel',{
		activeTab:0,
		region:'center',
		xtype:'tabpanel',
		tabPosition:'bottom',
		layout:'fit', 
		items:[{
			title:'后台配置',
			defaultType:'textfield',
			fieldDefaults:{
		        labelWidth:80,
		        labelAlign:"right",
		        margin:'2 5 4 5'
		    },
            scrollable:true,  
            scrollable:'x',
            scrollable:'y',
			items:[{	
					fieldLabel:'表名备注',
					name:'xt_generator_tbcomment',
					id:'xt_generator_tbcomment',
					hidden:true,
					hideLabel:true,
					allowBlank:true
				  },
				  {
					fieldLabel:'表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名',
					name:'xt_generator_tbname',
					width:'50%',
		            hiddenName:'xt_generator_tbname',
		            id:'TABLE_NAME',
		            xtype:"combo",
		            queryMode:'local', 
		            store:xtGeneratorTableStore,
		            emptyText:"请选择数据库表名",
		            triggerAction:"all",
		            valueField:"TABLE_NAME",
		            displayField:"TABLE_COMMENT",
					allowBlank:false,
		            listeners:{ 
					   select:function(combo,records,eOpts){
					  		//1.E5，6下拉框选值操作
					   		Ext.getCmp("xt_generator_tbcomment").setValue(records.data.TABLE_COMMENT);
					   		//执行联动效果
					   		Ext.getCmp("xt_generator_tb_columnname").setValue("");
					   		xtGeneratorTableColumnStore.load({params:{xt_generator_tbname:records.data.TABLE_NAME}});
					   		
					   		//2.联动新增 编辑 明细等表单 Grid操作
					   		xtGeneratorColumnStore.proxy.extraParams.xt_generator_tbname = records.data.TABLE_NAME;
	            			xtGeneratorColumnGrid.getStore().load();
	            			
	            			//3.联动列表Grid操作
					   		xtGeneratorGridListColumnStore.proxy.extraParams.xt_generator_tbname = records.data.TABLE_NAME;
	            			xtGeneratorGridListColumnGrid.getStore().load();
	            			
	            			//4.清空
	            			if(gValue('is_one_to_many') == 1){
	            				xtGeneratorOneToManyGrid.getStore().removeAll();  
	            			}
	            			
	            			//5.清空查询条件
	            			xtGeneratorSearchFiledGrid.getStore().removeAll();  
				  		}  
					}
				  },
				  {
					fieldLabel:'操作主键',
					name:'xt_generator_tb_columnname',
					id:'xt_generator_tb_columnname',
					width:'100%',
		            hiddenName:'xt_generator_tb_columnname',
		            xtype:"combo",
		            queryMode:'local', 
		            store:xtGeneratorTableColumnStore,
		            emptyText:"请选择操作主键字段(如果您字段中还没有设置主键,请设置主键,设置之后可操作)",
		            triggerAction:"all",
		            editable:false,
		            valueField:"COLUMN_NAME",
		            displayField:"COLUMN_COMMENT",
					allowBlank:false,
		            listeners:{ 
					   select:function(combo,records,eOpts){
					  		//E5，6下拉框选值操作
					   		//Ext.getCmp("xt_generator_tb_columnname").setValue(records.data.COLUMN_NAME);
					   }  
					}
				  },
				  {
				  	layout:"column",
					baseCls:'x-plain',
					xtype:'panel',
					border:false,
					items:[{
							columnWidth:.3,
							items:[{
								fieldLabel:'平台分页采用方式:物理',
								labelWidth:180,
								xtype:'radio',
								name:'xt_generator_page',
								inputValue:'1',
								checked:true
							}]
						},{
							columnWidth:.2,
							items:[{
								fieldLabel:'普通',
								xtype:'radio',
								name:'xt_generator_page',
								inputValue:'2'
							}]
						}]
				  },
				  {
				  	layout:"column",
					baseCls:'x-plain',
					xtype:'panel',
					items:[{
							columnWidth:.2,
							xtype:'checkbox',
							fieldLabel:'实体层',
							checked:true,
							inputValue:"实体层",
							name:'xt_generator_modules'
						},
						{
							columnWidth:.25,
							fieldLabel:'可继承父类:&nbsp;&nbsp;&nbsp;&nbsp;可以',
							labelWidth:150,
							xtype:'radio',
							name:'xt_generator_extendF',
							inputValue:'1',
							checked:true
						},
						{
							columnWidth:.2,
							fieldLabel:'不能',
							xtype:'radio',
							name:'xt_generator_extendF',
							inputValue:'2'
						}]
				  },
				  {
					fieldLabel:'实体层包名',
					name:'xt_generator_model_package',
					width:'100%',
					emptyText:"输入实体层包名",
				  	allowBlank:false
				  },
				  {
					xtype:'checkbox',
					fieldLabel:'数据层',
					checked:true,
					inputValue:"数据层",
					name:'xt_generator_modules'
				  },
				  {
					fieldLabel:'数据层包名',
					name:'xt_generator_dao_package',
					width:'100%',
					emptyText:"输入数据层包名",
				  	allowBlank:false
				  },
				  {
					xtype:'checkbox',
					fieldLabel:'业务层',
					checked:true,
					inputValue:"业务层",
					name:'xt_generator_modules'
				  },
				  {
					fieldLabel:'业务层包名',
					name:'xt_generator_service_package',
					width:'100%',
					emptyText:"输入业务层包名",
				  	allowBlank:false
				  },
				  {
				  	layout:"column",
					baseCls:'x-plain',
					xtype:'panel',
					border:false,
					items:[{
							columnWidth:.2,
							xtype:'checkboxfield',
							fieldLabel:'控制层',
							checked:true,
							inputValue:"控制层",
							name:'xt_generator_modules'
						},
						{
							columnWidth:.25,
							fieldLabel:'控制层模式:&nbsp;&nbsp;&nbsp;&nbsp;单例',
							labelWidth:150,
							xtype:'radio',
							name:'xt_generator_scope',
							inputValue:'1',
							checked:true
						},
						{
						columnWidth:.2,
						fieldLabel:'多例',
						xtype:'radio',
						name:'xt_generator_scope',
						inputValue:'2'
					}]
				  },
				  {
					fieldLabel:'控制层包名',
					name:'xt_generator_web_package',
					width:'100%',
					emptyText:"输入控制层包名",
				  	allowBlank:false
			   	  },
			   	  {
					xtype:'checkbox',
					fieldLabel:'页面层',
					checked:true,
					inputValue:"页面层",
					name:'xt_generator_modules'
				  },
				  {
					fieldLabel:'页面层包名',
					name:'xt_generator_page_package',
					width:'100%',
					emptyText:"输入页面层包名[建议不要放入到最内层即精确到总模块层即可]",
				  	allowBlank:false
			   	  },
			   	  {
					fieldLabel:'生成路径',
					name:'xt_generator_path',
					width:'30%',
					xtype:"combo",
					store:disksList, 
		            emptyText:'请选择',  
		            mode:'local',  
		            triggerAction:'all',  
		            valueField:'disks',  
		            displayField:'disksName',  
					allowBlank:false,
		            emptyText:"请选择代码生成路径",
		            mode:"local",
		            triggerAction:"all",
				  	editable:false
				 },
				 {
					xtype:'checkbox',
					fieldLabel:'二级缓存',
					checked:false,
					inputValue:'1',
					hidden:true,
					boxLabel:'是否选择Redis作为二级缓存？（默认采用Ehcache缓存作为二级缓存）',
					name:'xt_generator_isuse_rediscache'
				 },
				 {
				  	layout:"column",
					baseCls:'x-plain',
					xtype:'panel',
					border:false,
					items:[{
							columnWidth:.35,
							fieldLabel:'平台是否默认生成批量添加、修改、根据动态条件批量修改功能:是',
							labelWidth:450,
							xtype:'radio',
							name:'xt_generator_batch_add_update',
							inputValue:'1',
							checked:true
						},{
							columnWidth:.2,
							fieldLabel:'否',
							xtype:'radio',
							name:'xt_generator_batch_add_update',
							inputValue:'2'
						}]
				 },
			   	 {
					fieldLabel:'一对多标识',
					name:'is_one_to_many',
					id:'is_one_to_many',
					width:'20%',
					hidden:true,
					value:'0',
				  	allowBlank:false
			   	 },
				 {
				  	layout:"column",
					baseCls:'x-plain',
					xtype:'panel',
					id:'one_to_many_typeFlag',
					border:false,
					items:[{
							columnWidth:.25,
							fieldLabel:'<font color=red>【一对多】多的一方采用动态添加列表方式</font>',
							labelWidth:295,
							xtype:'radio',
							name:'one_to_many_type',
							inputValue:'0'
						},{
							columnWidth:.4,
							labelWidth:280,
							fieldLabel:'<font color=green>多的一方采用动态添加表单方式</font>',
							xtype:'radio',
							name:'one_to_many_type',
							inputValue:'1',
							checked:true
						}]
				  },
				  {
					fieldLabel:'操作人',
					name:'xt_userinfo_realName',
					width:'20%',
					emptyText:"输入操作人",
				  	allowBlank:false
			   	  }
			   	 ]
		},
		{
			title:'前台配置',
			defaultType:'textfield',
			fieldDefaults:{
		        labelWidth:120,
		        labelAlign:"right",
		        margin:'4 5 4 5'
		    },
            scrollable:true,  
            scrollable:'x',
            scrollable:'y',
		    items:[{
		    		title:'列表页面配置',
			    	xtype:'fieldset',
			    	items:[
			    	{
			    		xtype:'checkbox',
						fieldLabel:'列表中显示复选框',
						inputValue:"1",
						checked:true,
						labelWidth:140,
						name:'xt_generator_page_checkboxmodel'
			    	},
			    	{
			    		xtype:'checkbox',
						fieldLabel:'列表中可多选行',
						inputValue:"1",
						checked:true,
						labelWidth:140,
						name:'xt_generator_page_multiSelect'
			    	},
			    	{
			    		xtype:'checkbox',
						fieldLabel:'列表中是否可折叠',
						inputValue:"1",
						checked:true,
						labelWidth:140,
						name:'xt_generator_page_collapsible'
			    	}]
		    	  },
		    	  {
			    	title:'添加，编辑，明细页面配置',
			    	xtype:'fieldset',
			    	items:[
			    	{
						xtype:'checkbox',
						fieldLabel:'窗体默认最大化',
						inputValue:"1",
						labelWidth:140,
						name:'xt_generator_page_max'
					},
					{
						xtype:'checkbox',
						fieldLabel:'窗体宽度采用百分比',
						inputValue:"1",
						labelWidth:140,
						name:''
					},
					{
						xtype:'numberfield',
						fieldLabel:'页面窗体宽度',
						value:'800',
						width:'40%',
						labelWidth:140,
						name:'xt_generator_page_width'
					},
					{
						xtype:'checkbox',
						fieldLabel:'窗体高度采用百分比',
						inputValue:"1",
						labelWidth:140,
						name:''
					},
					{
						xtype:'numberfield',
						fieldLabel:'页面窗体高度',
						value:'400',
						width:'40%',
						labelWidth:140,
						name:'xt_generator_page_height'
					},
					{
						xtype:'numberfield',
						fieldLabel:'表单标签宽度',
						value:'70',
						width:'40%',
						labelWidth:140,
						name:'xt_generator_page_labelWidth'
					},
					{
						fieldLabel:'表单标签对齐方式',
						labelWidth:140,
	                	xtype:'radiogroup',
	                	/**columns:[150,150],**/
	                	items:[{boxLabel:'向左',checked:true,name:'xt_generator_page_labelAlign',inputValue:'1'},
	                		   {boxLabel:'向右',name:'xt_generator_page_labelAlign',inputValue:'2'},
	                		   {boxLabel:'向上',name:'xt_generator_page_labelAlign',inputValue:'3'}]
		    		},
		    		{
						xtype:'checkbox',
						fieldLabel:'窗体支持滚动条',
						inputValue:"1",
						labelWidth:140,
						checked:true,
						name:'xt_generator_page_winScroll'
					}]
		    }]
		},
		{
			title:'列表配置',
			layout:'fit',    
		    items:xtGeneratorGridListColumnGrid
		},
		{
			title:'配置查询',
			layout:'fit',    
		    items:xtGeneratorSearchFiledGrid
		},
		{
			title:'新增，编辑，明细配置',
			layout:'fit',    
		    items:xtGeneratorColumnGrid
		}]
	});
}
function initXtGeneratorFormAdd(){
	initXtGeneratorSearchFiledGrid();
	initXtGeneratorGridListColumn();
	initXtGeneratorColumn();
	initXtGeneratorTab();
	xtGeneratorFormAdd = Ext.create('Ext.FormPanel',{
		fieldDefaults:{
	        labelWidth:80,
	        labelAlign:"right",
	        margin:'4 5 4 5'
	    },
	    layout:'fit',    
		items:[xtGeneratorTab]		
	 });
}

function initXtGeneratorColumn(){
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
	    data:[['文本域','文本域'],['下拉框','下拉框'],['文件框','文件框']]  
	});
	xtGeneratorColumnStore = getGridJsonStore('../xtGeneratorController/getXtGeneratorTableColumnListByCondition',[]);
	xtGeneratorColumnGrid = Ext.create('Ext.grid.Panel',{
   	 	columnLines:true,
        multiSelect:true,
        collapsible:false,
        border:true,
        region:'center', 
        id:'xtGeneratorColumnGrid',
        title:'<font color=red>注：支持自定义下拉框、文本域、文件框（附件）</font>',
        layout:'fit',    
        plugins:{
			ptype:'cellediting',
        	clicksToEdit:1
		},
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/  
        store:xtGeneratorColumnStore,
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
			            		xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("column_label_position",record.data.text);
			            	}
			            }
		            },
	                dataIndex:'column_label_position'
            	},
            	{
	                header:'标签宽度',
	                width:80,
	                editor:{
		                allowBlank:false,
		                xtype:'numberfield'
		            },
	                dataIndex:'column_label_anchor'
            	},
            	{
	                header:'类型',
	                width:120,
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
			            		var DATA_TYPE = xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].data.DATA_TYPE;
			            		var column_type = record.data.value;
			            		console.info(column_type);
			            		var CHARACTER_MAXIMUM_LENGTH = xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].data.CHARACTER_MAXIMUM_LENGTH;
			            		if(DATA_TYPE == 'VARCHAR' || DATA_TYPE == 'CHAR' || DATA_TYPE == 'TEXT' || DATA_TYPE == 'LONGTEXT' || DATA_TYPE == 'LONGBLOB'){
			            			if('文件框'== column_type && CHARACTER_MAXIMUM_LENGTH <32){
		            					this.setValue('');
		            					msgTishi("你所选的文件框字段长度小于32位，必须大于等于32位才可选择");
		            					return;
			            			}
			            			this.setValue(record.data.value);
			            			xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("column_type",record.data.text);
			            		}else{
			            			this.setValue('');
			            			msgTishi("字段类型为VARCHAR，CHAR时，才可选择该项");
			            			return;
			            		}
			            	}
			            }
		            },
	                dataIndex:'column_type'
            	},
            	{
	                header:'脚本编号',
	                flex:1,
	                hidden:true,
	                editor:{
		                allowBlank:false,
		                xtype:'textfield'
		            },
	                dataIndex:'xt_script_id'
            	},
            	{
	                header:'脚本',
	                flex:1,
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
			            		var column_type = xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].data.column_type;
			            		if(null != column_type && '' != column_type){
			            			if('下拉框'== column_type){
			            				this.setValue(record.data.xt_script_title);
					            		xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",record.data.xt_script_id);
					            		xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",record.data.xt_script_title);
			            			}else{
			            				this.setValue('');
					            		xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",'');
					            		xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",'');
			            				msgTishi("类型为非下拉框，不能选择该下拉框数据源");
			            				return;
			            			}
			            		}else{
			            			this.setValue('');
				            		xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",'');
				            		xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",'');
		            				msgTishi("类型为空，不能选择该下拉框数据源");
		            				return;
			            		}
			            	}
			            }
		            },
	                dataIndex:'xt_script_text'
            	},
            	{
	                header:'隐藏',
	                width:50,
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
			            		xtGeneratorColumnGrid.getSelectionModel().getSelected().items[0].set("isHidden",record.data.text);
			            	}
			            }
		            },
	                dataIndex:'isHidden'
            	}],
        tbar:[{
	            text:'刷新',
	            scope:this,
	            icon:refreshIcon,
	            handler:function () {
	            	var TABLE_NAME = Ext.getCmp('TABLE_NAME').getValue();
	            	if(null == TABLE_NAME || "" == TABLE_NAME){
	            		msgTishi('请到后台配置选项卡中选择您要生成的表');
	            		return;
	            	}
	            	xtGeneratorColumnStore.proxy.extraParams.xt_generator_tbname = TABLE_NAME;
	            	xtGeneratorColumnGrid.getStore().reload();
            	}
         	}]
	    });
}

function initXtGeneratorGridListColumn(){
	xtGeneratorGridListColumnStore = getGridJsonStore('../xtGeneratorController/getXtGeneratorTableColumnListByCondition',[]);
	xtGeneratorGridListColumnGrid = Ext.create('Ext.grid.Panel',{
   	 	columnLines:true,
        multiSelect:true,
        collapsible:false,
        border:true,
        region:'center', 
        layout:'fit',  
        title:'<font color=red>注：展现出相关字段可以修改别名，（采用渲染值、渲染脚本类型、渲染脚本暂不可用）</font>',
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/   
        plugins:{
			ptype:'cellediting',
        	clicksToEdit:1
		},
        xtype:'grid',  
        store:xtGeneratorGridListColumnStore,
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
                editor:{
	                allowBlank:false,
	                readOnly:true,
	                xtype:'textfield'
	            },
                dataIndex:'COLUMN_NAME'
           	},
           	{
                header:'中文标签',
                flex:1,
                editor:{
	                allowBlank:false,
	                xtype:'textfield'
	            },
                dataIndex:'COLUMN_COMMENT'
           	},
           	{
                header:'字段类型',
                flex:1,
                dataIndex:'DATA_TYPE'
           	},
           	{
                header:'主键',
                flex:1,
                dataIndex:'COLUMN_KEY'
           	},
           	{
                header:'采用渲染值（即返回显示值）',
                flex:1,
                dataIndex:'xt_generator_grid_renderer'
           	},
           	{
                header:'渲染脚本类型',
                flex:1,
                dataIndex:'xt_generator_grid_renderer_type'
           	},
           	{
                header:'渲染脚本',
                flex:1,
                dataIndex:'xt_generator_grid_renderer_combo'
           	}],
        tbar:[
        	 {
	            text:'刷新',
	            scope:this,
	            icon:refreshIcon,
	            handler:function(){
	            	var TABLE_NAME = Ext.getCmp('TABLE_NAME').getValue();
	            	if(null == TABLE_NAME || "" == TABLE_NAME){
	            		msgTishi('请到后台配置选项卡中选择您要生成的表');
	            		return;
	            	}
	            	xtGeneratorGridListColumnStore.proxy.extraParams.xt_generator_tbname = TABLE_NAME;
	            	xtGeneratorGridListColumnGrid.getStore().reload();
            	}
         	 },
			 {  
                  text:'删除',  
                  disabled:true, 
                  icon:delIcon, 
                  itemId:'del_grid_filed_button', 
                  handler:function(){
                  	var model = xtGeneratorGridListColumnGrid.getSelectionModel();
					if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					}
					xtGeneratorGridListColumnGrid.getStore().remove(xtGeneratorGridListColumnGrid.getSelectionModel().getSelection());  
                  } 
             } 
             ],
        listeners:{  
		    selectionChange:'selectionChange'  
		},
		//选中的记录发生变化过后的事件  
		selectionChange:function(view, records){  
			xtGeneratorGridListColumnGrid.down('#del_grid_filed_button').setDisabled(!records.length);
		}
	});
}

//搜索字段列表
function initXtGeneratorSearchFiledGrid(){
	var comboData = Ext.create('Ext.data.SimpleStore',{  
	    fields:['value','text'],  
	    data:[['包含','包含'],['等于','等于'],['大于等于','大于等于'],['小于等于','小于等于'],['大于','大于'],['小于','小于'],['范围','范围']]  
	});
	var comboPostionData = Ext.create('Ext.data.SimpleStore',{  
	    fields:['value','text'],  
	    data:[['居上','居上'],['居左','居左']]  
	});
	var comboTypeData = Ext.create('Ext.data.SimpleStore',{  
	    fields:['value','text'],  
	    data:[['文本框','文本框'],['文本域','文本域'],['数字框','数字框'],['下拉框','下拉框'],['日期框','日期框']]  
	});
	xtGeneratorSearchFiledGrid = Ext.create('Ext.grid.Panel',{
		requires:[
	        'Ext.grid.plugin.CellEditing',
	        'Ext.form.field.Text',
	        'Ext.form.field.TextArea',
	        'Ext.toolbar.TextItem'
	    ],
		columnLines:true,
        multiSelect:true,
        border:true,
        store:[],
        title:'<font color=red>注：数字框、日期框标识无效，说明：日期框（开始时间与结束时间）、数字框（大于、等于、小于、大于等于、小于等于）</font>',
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
        plugins:{
			ptype:'cellediting',
        	clicksToEdit:1
		},
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
				header:'脚本编号',
				dataIndex:'xt_script_id',
				flex:1,
				hidden:true,
                field:{
                    type:'textfield',
                    readOnly:true,
                }
			},
			{
				header:'字段',
				dataIndex:'xt_generator_search_name',
				flex:1,
                field:{
                    type:'textfield',
                    readOnly:true,
                }
			},
			{
				header:'中文名称',
				dataIndex:'xt_generator_search_label',
				flex:1,
                editor:{
                    xtype:'textfield'
                }
			},
			{
				header:'Label位置',
				dataIndex:'xt_generator_search_label_position',
				flex:1,
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
		            		xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_generator_search_label_position",record.data.text);
		            	}
		            }
			    }
			},
			{
				header:'Label长度',
				dataIndex:'xt_generator_search_label_length',
				flex:1,
                editor:{
                    xtype:'numberfield',
					value:'70'
                }
			},
			{
				header:'标识',
				flex:1,
				dataIndex:'xt_generator_search_flag',
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
		            		var xt_generator_search_type = xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].data.xt_generator_search_type;
		            		if(null != xt_generator_search_type && '' != xt_generator_search_type){
		            			//如果下拉框只能采用等于
		            			if('下拉框'== xt_generator_search_type){
		            				msgTishi("类型为下拉框，查询条件只能为<font color='red'>等于</font>");
		            				this.setValue('等于');
		            				xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_generator_search_flag",'等于');
		            			}else{
		            				this.setValue(record.data.value);
		            				xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_generator_search_flag",record.data.text);
		            			}
		            		}
		            	}
		            }
			    }
			},
			{
				header:'类型',
				flex:1,
				dataIndex:'xt_generator_search_type',
				editor:{  
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
		            		var xt_generator_search_type = record.data.text;
		            		if(null != xt_generator_search_type && '' != xt_generator_search_type){
		            			if('下拉框'!= xt_generator_search_type){
		            				this.setValue('');
				            		xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",'');
				            		xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",'');
		            			}else{
		            				xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_generator_search_flag",'等于');
		            			}
		            		}
		            		this.setValue(record.data.value);
		            		xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_generator_search_type",record.data.text);
		            	}
		            }
			    }
			},
			{
				header:'下拉框渲染数据（即平台脚本）',
				flex:2,
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
		            		var xt_generator_search_type = xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].data.xt_generator_search_type;
		            		if(null != xt_generator_search_type && '' != xt_generator_search_type){
		            			if('下拉框'== xt_generator_search_type){
		            				this.setValue(record.data.xt_script_title);
				            		xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",record.data.xt_script_id);
				            		xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",record.data.xt_script_title);
		            			}else{
		            				this.setValue('');
				            		xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_id",'');
				            		xtGeneratorSearchFiledGrid.getSelectionModel().getSelected().items[0].set("xt_script_text",'');
		            				msgTishi("类型为非下拉框，不能选择该下拉框数据源");
		            				return;
		            			}
		            		}
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
			xtGeneratorSearchFiledGrid.down('#del_search_filed_button').setDisabled(!records.length);
		},
		tbar:[
			 {
				text:'新一行',
				tooltip:'新一行',
				scope:this,
				icon:addIcon,
				handler:function(){
				    initXtGeneratorSearchFileSelWin(); 
				}
			 },
			 {  
                  text:'删除',  
                  disabled:true, 
                  icon:delIcon, 
                  itemId:'del_search_filed_button', 
                  handler:function(){
                  	var model = xtGeneratorSearchFiledGrid.getSelectionModel();
					if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					}
					xtGeneratorSearchFiledGrid.getStore().remove(xtGeneratorSearchFiledGrid.getSelectionModel().getSelection());  
                  } 
             }  
			 ]
	});
}

//搜索字段Win
var xtGeneratorSearchFileSelectdWin;
var xtGeneratorSearchFileSelGrid;
var xtGeneratorSearchFileSelStore;
function initXtGeneratorSearchFileSelWin(){
	reGetWidthAndHeight();
	initXtGeneratorSearchFileSelGrid();
	xtGeneratorSearchFileSelectdWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'选择出字段----请双击即可设置',
		items:[xtGeneratorSearchFileSelGrid]
	});
	xtGeneratorSearchFileSelectdWin.show();
}
//选择数据库字段
function initXtGeneratorSearchFileSelGrid(){
	xtGeneratorSearchFileSelStore = getGridJsonStore('../xtGeneratorController/getXtGeneratorTableColumnListByCondition',[]);
	xtGeneratorSearchFileSelGrid = Ext.create('Ext.grid.Panel',{
   	 	columnLines:true,
        multiSelect:true,
        collapsible:false,
        border:true,
        scrollable:true,
		scrollable:'x',
		scrollable:'y',
        region:'center', 
        layout:'fit',    
        plugins:{
			ptype:'cellediting',
        	clicksToEdit:1
		},
        xtype:'grid',  
        store:xtGeneratorSearchFileSelStore,
        viewConfig:{
            emptyText:'暂无数据',
            stripeRows:true
        },
        loadMask:{
            msg:'正在加载...'
        },
        listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var COLUMN_NAME = xtGeneratorSearchFileSelGrid.getSelectionModel().selected.items[0].data.COLUMN_NAME;
				var COLUMN_COMMENT = xtGeneratorSearchFileSelGrid.getSelectionModel().selected.items[0].data.COLUMN_COMMENT;
				var str = "[<font color=red><br>字段:"+COLUMN_NAME+"<br>中文名称:"+COLUMN_COMMENT+"</font>]";
				Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						for(var i=0;i<xtGeneratorSearchFiledGrid.getStore().getCount();i++){
							var xt_generator_search_name = xtGeneratorSearchFiledGrid.store.getAt(i).data.xt_generator_search_name;
							if("undefined" != typeof(xt_generator_search_name) && null != xt_generator_search_name && xt_generator_search_name != ""){
								if(xt_generator_search_name == COLUMN_NAME){
									msgTishi("您选择的该字段已经设置过了，不能再次设置！");
							        return false;
								}
							}
						}
						var model = Ext.create(xtGeneratorSearchFiledGrid.getStore().model);  
					    model.set('xt_generator_search_name',COLUMN_NAME);  
					    model.set('xt_generator_search_label',COLUMN_COMMENT);
					    model.set('xt_generator_search_label_position','居上');
					    model.set('xt_generator_search_flag','包含');
					    model.set('xt_generator_search_label_length','70');
					    model.set('xt_generator_search_type','文本框');
					    xtGeneratorSearchFiledGrid.getStore().insert(0, model);  
						xtGeneratorSearchFileSelectdWin.close();
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
            	xtGeneratorSearchFileSelStore.proxy.extraParams.xt_generator_tbname = TABLE_NAME;
            	xtGeneratorSearchFileSelGrid.getStore().reload();
            }
        }]
	});
}
//验证并获取配置查询字段JSON规则
function getXtGeneratorSearchFileJSON(){
	///////////开始验证//////////////
	for(var i=0;i<xtGeneratorSearchFiledGrid.getStore().getCount();i++){
		var xt_generator_search_name = xtGeneratorSearchFiledGrid.store.getAt(i).data.xt_generator_search_name;
		var xt_generator_search_label = xtGeneratorSearchFiledGrid.store.getAt(i).data.xt_generator_search_label;
		var xt_generator_search_label_position = xtGeneratorSearchFiledGrid.store.getAt(i).data.xt_generator_search_label_position;
		var xt_generator_search_flag = xtGeneratorSearchFiledGrid.store.getAt(i).data.xt_generator_search_flag;
		if("undefined" == typeof(xt_generator_search_name) || null == xt_generator_search_name || xt_generator_search_name == ""){
			msgTishi("请选择字段");
	        return false;
		}
		if("undefined" == typeof(xt_generator_search_label) || null == xt_generator_search_label || xt_generator_search_label == ""){
			msgTishi("请输入中文名称");
	        return false;
		}
		if("undefined" == typeof(xt_generator_search_label_position) || null == xt_generator_search_label_position || xt_generator_search_label_position == ""){
			msgTishi("请选择标签位置");
	        return false;
		}
		if("undefined" == typeof(xt_generator_search_flag) || null == xt_generator_search_flag || xt_generator_search_flag == ""){
			msgTishi("请选择标识");
	        return false;
		}
	}
    ///////////结束验证//////////////
	var jsonArray = [];  
	for(var i=0;i<xtGeneratorSearchFiledGrid.getStore().getCount();i++){
		var record = xtGeneratorSearchFiledGrid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	return Ext.encode(jsonArray)
}



//验证并获取配置GridList字段JSON规则
function getXtGeneratorGridListJSON(){
	if(xtGeneratorGridListColumnGrid.getStore().getCount() == 0){
		msgTishi("至少选择一列!");
		return false;
	}
	///////////开始验证//////////////
	for(var i=0;i<xtGeneratorGridListColumnGrid.getStore().getCount();i++){
		var COLUMN_COMMENT = xtGeneratorGridListColumnGrid.store.getAt(i).data.COLUMN_COMMENT;
		var COLUMN_NAME = xtGeneratorGridListColumnGrid.store.getAt(i).data.COLUMN_NAME;
		var column_type = xtGeneratorGridListColumnGrid.store.getAt(i).data.column_type;
		var xt_script_text = xtGeneratorGridListColumnGrid.store.getAt(i).data.xt_script_text;
		if("undefined" == typeof(COLUMN_NAME) || null == COLUMN_NAME || COLUMN_NAME == ""){
			msgTishi("存在空值的字段，请检查!");
	        return false;
		}
		if("undefined" == typeof(COLUMN_COMMENT) || null == COLUMN_COMMENT || COLUMN_COMMENT == ""){
			msgTishi("【列表配置选项中】--请输入中文标签");
	        return false;
		}
		if("undefined" != typeof(column_type) && null != column_type && column_type != ""){
			if(column_type == "下拉框"){
				if("undefined" == typeof(xt_script_text) || null == xt_script_text || xt_script_text == ""){
					msgTishi("您选择了类型为下拉框项，必须要选择脚本！");
		        	return false;
	        	}
			}
		}
	}
    ///////////结束验证//////////////
	var jsonArray = [];  
	for(var i=0;i<xtGeneratorGridListColumnGrid.getStore().getCount();i++){
		var record = xtGeneratorGridListColumnGrid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	return Ext.encode(jsonArray)
}


//验证并获取配置新增，编辑，明细等表单字段JSON规则
function getXtGeneratorFormListJSON(){
	///////////开始验证//////////////
	for(var i=0;i<xtGeneratorColumnGrid.getStore().getCount();i++){
		var COLUMN_COMMENT = xtGeneratorColumnGrid.store.getAt(i).data.COLUMN_COMMENT;
		var COLUMN_NAME = xtGeneratorColumnGrid.store.getAt(i).data.COLUMN_NAME;
		var xt_script_text = xtGeneratorColumnGrid.store.getAt(i).data.xt_script_text;
		var column_type = xtGeneratorColumnGrid.store.getAt(i).data.column_type;
		if("undefined" == typeof(COLUMN_NAME) || null == COLUMN_NAME || COLUMN_NAME == ""){
			msgTishi("存在空值的字段，请检查!");
	        return false;
		}
		if("undefined" == typeof(COLUMN_COMMENT) || null == COLUMN_COMMENT || COLUMN_COMMENT == ""){
			msgTishi("【新增编辑明细模块中】---请输入中文标签");
	        return false;
		}
		if("undefined" != typeof(column_type) && null != column_type && column_type != ""){
			if(column_type == "下拉框"){
				if("undefined" == typeof(xt_script_text) || null == xt_script_text || xt_script_text == ""){
					msgTishi("您选择了类型为下拉框项，必须要选择脚本！");
		        	return false;
	        	}
			}
		}
	}
    ///////////结束验证//////////////
	var jsonArray = [];  
	for(var i=0;i<xtGeneratorColumnGrid.getStore().getCount();i++){
		var record = xtGeneratorColumnGrid.store.getAt(i).data;//循环遍历每一行 
		jsonArray.push(record);
	} 
	return Ext.encode(jsonArray)
}
