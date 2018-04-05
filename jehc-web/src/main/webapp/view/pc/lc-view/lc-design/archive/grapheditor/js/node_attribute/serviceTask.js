//服务任务节点
var serviceTaskNodeAttributeWin;
var serviceTaskNodeAttributeTabPanel;
var serviceTaskNodeAttributeForm;
function showServiceTaskNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		serviceTaskNodeAttributePanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),serviceTaskNodeAttributeTabPanel);
		basePanel.setHidden(true);
	}else{
		serviceTaskNodeAttributePanel(cell,graph_refresh);
		serviceTaskNodeAttributeWin = new Ext.Window({  
	         title:'配置服务任务属性',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:serviceTaskNodeAttributeTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
	          		var graph = new mxGraph();
	          		graph.getModel().beginUpdate();
					try
					{
						//1通用基本配置并具有赋值功能
					 	if(node_normal_setvalue(cell,1)== false){
					 		return;
					 	}
					 	//2事件配置
					 	if(event_setvalue(cell)== false){
					 		return;
					 	}
					 	//3配置会签
					 	multi_instance_setvalue(cell);
					 	//4基本配置
					 	var className = Ext.getCmp('className').getValue();
					    var taskType = Ext.getCmp('taskType').getValue();
					    var expression = Ext.getCmp('expression').getValue();
					    var delegateExpression = Ext.getCmp('delegateExpression').getValue();
					    var resultVariable = Ext.getCmp('resultVariable').getValue();
					    var skipExpression = Ext.getCmp('skipExpression').getValue();
					    if(null != className && '' != className){
					    	cell.className = className;
					    }
					    if(null != taskType && '' != taskType){
					    	cell.taskType = taskType;
					    }
					    if(null != expression && '' != expression){
					    	cell.expression = expression;
					    }
					    if(null != delegateExpression && '' != delegateExpression){
					    	cell.delegateExpression = delegateExpression;
					    }
					    if(null != resultVariable && '' != resultVariable){
					    	cell.resultVariable = resultVariable;
					    }
					    if(null != skipExpression && '' != skipExpression){
					    	cell.skipExpression = skipExpression;
					    }
					    
					    //基本配置中字段描述
					    if(serviceField_setvalue(cell)== false){
					    	return;
					    }
						graph.startEditing();
						serviceTaskNodeAttributeWin.close(this); 
					}
					finally
					{
						graph.getModel().endUpdate();
						graph_refresh.refresh();
					}
	          }  
	         }, {  
	          text:'取 消',  
	          handler:function(){  
	            serviceTaskNodeAttributeWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     serviceTaskNodeAttributeWin.show(); 
	}
}
var serviceNodeAttributeFieldGrid;
function editServiceTaskNodeAttributeForm(cell){
	initServiceNodeAttributeField(cell);
	var service_task_typeStore = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["javaclass","javaclass"],["delegateexpress","delegateexpress"],["express","express"]]
	});
	serviceTaskNodeAttributeForm = new Ext.FormPanel({
        frame:false,
        border:false,
        scrollable:true,
        region:"center",
        fieldDefaults:{
	        labelWidth:110,
	        labelAlign:"right",
	        flex:1
	    },
        items:[{
            valueField:"value",
            displayField:"name",
            xtype:"combo",
            store:service_task_typeStore,
            emptyText:"请选择",
            mode:"local",
            value:'javaclass',
            triggerAction:"all",
            editable:false,
            fieldLabel:'服务类型',  
           	name:'taskType',
           	id:'taskType',  
           	anchor:'40%',
           	listeners:{
                 select:function(combo,records,options){
                 	var val=this.value;
                 	if(val == 'javaclass'){
                 		Ext.getCmp('className').setHidden(false);
                 		Ext.getCmp('expression').setHidden(true);
                 		Ext.getCmp('expression').setValue('');
                 		Ext.getCmp('delegateExpression').setHidden(true);
                 		Ext.getCmp('delegateExpression').setValue('');
                 	}
                 	if(val == 'delegateexpress'){
                 		Ext.getCmp('className').setHidden(true);
                 		Ext.getCmp('className').setValue('');
                 		Ext.getCmp('expression').setHidden(true);
                 		Ext.getCmp('expression').setValue('');
                 		Ext.getCmp('delegateExpression').setHidden(false);
                 	}
                 	if(val == 'express'){
                 		Ext.getCmp('className').setHidden(true);
                 		Ext.getCmp('className').setValue('');
                 		Ext.getCmp('expression').setHidden(false);
                 		Ext.getCmp('delegateExpression').setHidden(true);
                 		Ext.getCmp('delegateExpression').setValue('');
                 	}
                 }
             }
         },{
         	xtype:'textfield',  
            fieldLabel:'执行的类',  
           	name:'className',
           	id:'className',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'表&nbsp;达&nbsp;&nbsp;式',  
           	name:'expression',
           	id:'expression',  
           	hidden:true,
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'委托类型表达式',  
           	name:'delegateExpression',
           	id:'delegateExpression',  
           	hidden:true,
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'结果变量',  
           	name:'resultVariable',
           	id:'resultVariable',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'跳过表达式',  
           	name:'skipExpression',
           	id:'skipExpression',  
           	anchor:'100%'
         },serviceNodeAttributeFieldGrid]
    });
	/**取值**/
	var taskType = cell.taskType;
 	var className = cell.className;
 	var expression = cell.expression;
 	var delegateExpression = cell.delegateExpression;
 	var resultVariable = cell.resultVariable;
 	var skipExpression = cell.skipExpression;
 	/**赋值**/
 	Ext.getCmp('className').setValue(className);
    Ext.getCmp('taskType').setValue(taskType);
    Ext.getCmp('expression').setValue(expression);
    Ext.getCmp('delegateExpression').setValue(delegateExpression);
    Ext.getCmp('resultVariable').setValue(resultVariable);
    Ext.getCmp('skipExpression').setValue(skipExpression);
    if(taskType == 'javaclass'){
   		Ext.getCmp('className').setHidden(false);
   		Ext.getCmp('expression').setHidden(true);
   		Ext.getCmp('expression').setValue('');
   		Ext.getCmp('delegateExpression').setHidden(true);
   		Ext.getCmp('delegateExpression').setValue('');
   	}else if(taskType == 'delegateexpress'){
   		Ext.getCmp('className').setHidden(true);
   		Ext.getCmp('className').setValue('');
   		Ext.getCmp('expression').setHidden(true);
   		Ext.getCmp('expression').setValue('');
   		Ext.getCmp('delegateExpression').setHidden(false);
   	}else if(taskType == 'express'){
   		Ext.getCmp('className').setHidden(true);
   		Ext.getCmp('className').setValue('');
   		Ext.getCmp('expression').setHidden(false);
   		Ext.getCmp('delegateExpression').setHidden(true);
   		Ext.getCmp('delegateExpression').setValue('');
   	}else{
   		Ext.getCmp('className').setHidden(false);
   		Ext.getCmp('taskType').setValue('javaclass');
   		Ext.getCmp('expression').setHidden(true);
   		Ext.getCmp('expression').setValue('');
   		Ext.getCmp('delegateExpression').setHidden(true);
   		Ext.getCmp('delegateExpression').setValue('');
   	}
}


//字段配置
function initServiceNodeAttributeField(cell){
	var serviceNodeAttributeFielddata;
	var serviceNodeAttributeField_value = cell.serviceNodeAttributeField_value;
	if(null != serviceNodeAttributeField_value && "" != serviceNodeAttributeField_value){
		/**1级grid显示即总Grid使用#分割**/
		var serviceNodeAttributeField_valueArray = serviceNodeAttributeField_value.split("#");
		for(var i = 0; i < serviceNodeAttributeField_valueArray.length; i++){
			var serviceNodeAttributeField_val = serviceNodeAttributeField_valueArray[i];
			if("undefined" != typeof(serviceNodeAttributeField_val)){
				/**grid行列**/
				var event_node_v = serviceNodeAttributeField_val.split("@");
				if(null == serviceNodeAttributeFielddata || "" == serviceNodeAttributeFielddata){
					serviceNodeAttributeFielddata = "['"+event_node_v[0]+"','"+event_node_v[1]+"','"+event_node_v[2]+"']";
				}else{
					serviceNodeAttributeFielddata = serviceNodeAttributeFielddata+","+"['"+event_node_v[0]+"','"+event_node_v[1]+"','"+event_node_v[2]+"',]";
				}
			}
		}
	}
	var data;
	if("undefined" != typeof(serviceNodeAttributeFielddata)){
		data = "["+serviceNodeAttributeFielddata+"]";
		data = Ext.decode(data);
	}
	//定义数据源
	var serviceNodeAttributeField_store = Ext.create('Ext.data.Store',{  
           fields:['field','string','fieldexpression'],  
           data:data,  
           proxy:{  
              type:'memory',  
              reader:{  
                  type:'json',  
                  rootProperty:'items'  
              }  
          }  
    })
	this.editing = Ext.create('Ext.grid.plugin.CellEditing',{
    	clicksToEdit:1
    });
	//定义grid编辑列
	serviceNodeAttributeFieldGrid = Ext.create('Ext.grid.Panel',{
			store:serviceNodeAttributeField_store,
			requires:[
		        'Ext.grid.plugin.CellEditing',
		        'Ext.form.field.Text',
		        'Ext.form.field.TextArea',
		        'Ext.toolbar.TextItem'
		    ],
			columnLines:true,
	        multiSelect:true,
	        border:true,
	        /**新方法使用开始**/  
	        scrollable:true,
	        height:250,
         	scrollable:true,  
         	scrollable:'x',
         	scrollable:'y',
	        plugins:[this.editing], 
	        title:'字段描述',
			viewConfig:{
				emptyText:'暂无数据',
				stripeRows:true
			},
			loadMask:{
				msg:'正在加载...'
			},
			columns:[
				{
					header:'字段名称',
					dataIndex:'field',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'字段值',
					dataIndex:'string',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'表达式',
					flex:1,
					dataIndex:'fieldexpression',
	                editor:{xtype:'textfield'}
				}
			],
			listeners:{  
			    selectionChange:'selectionChange'  
			},
			//选中的记录发生变化过后的事件  
			selectionChange:function(view, records){  
				serviceNodeAttributeFieldGrid.down('#service_del_button').setDisabled(!records.length);
			},
			tools:[{
				  text:'增 加',
				  xtype:'button',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  id:'node_service_add_button',
				  handler:function(){
		            var model = Ext.create(serviceNodeAttributeFieldGrid.getStore().model);  
				    serviceNodeAttributeFieldGrid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  xtype:'button',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'service_del_button',
				  handler:function(){
					 var model = serviceNodeAttributeFieldGrid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           serviceNodeAttributeFieldGrid.getStore().remove(serviceNodeAttributeFieldGrid.getSelectionModel().getSelection());  
				           serviceNodeAttributeFieldGrid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	serviceNodeAttributeFieldGrid.getStore().load();
}



function serviceTaskNodeAttributePanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editServiceTaskNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//会签配置
		initMultiInstance(cell);
	    serviceTaskNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	        	{title:'一般配置',items:nodeNormalForm,
	        		autoScroll:true,
	                /**新方法使用开始**/  
	                scrollable:true,  
	                scrollable:'x',
	                scrollable:'y',
	                /**新方法使用结束**/ 		
	        	},
	        	{title:'基本配置',items:serviceTaskNodeAttributeForm,layout:'border',
	        		autoScroll:true,
	                /**新方法使用开始**/  
	                scrollable:true,  
	                scrollable:'x',
	                scrollable:'y',
	                /**新方法使用结束**/ 		
	        	},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm,
	            	autoScroll:true,
	                /**新方法使用开始**/  
	                scrollable:true,  
	                scrollable:'x',
	                scrollable:'y',
	                /**新方法使用结束**/ 		
	            }
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
		          		var graph = new mxGraph();
		          		graph.getModel().beginUpdate();
						try
						{
							//1通用基本配置并具有赋值功能
						 	if(node_normal_setvalue(cell,1)== false){
						 		return;
						 	}
						 	//2事件配置
						 	if(event_setvalue(cell)== false){
						 		return;
						 	}
						 	//3配置会签
						 	multi_instance_setvalue(cell);
						 	//4基本配置
						 	var className = Ext.getCmp('className').getValue();
						    var taskType = Ext.getCmp('taskType').getValue();
						    var expression = Ext.getCmp('expression').getValue();
						    var delegateExpression = Ext.getCmp('delegateExpression').getValue();
						    var resultVariable = Ext.getCmp('resultVariable').getValue();
						    var skipExpression = Ext.getCmp('skipExpression').getValue();
						    if(null != className && '' != className){
						    	cell.className = className;
						    }
						    if(null != taskType && '' != taskType){
						    	cell.taskType = taskType;
						    }
						    if(null != expression && '' != expression){
						    	cell.expression = expression;
						    }
						    if(null != delegateExpression && '' != delegateExpression){
						    	cell.delegateExpression = delegateExpression;
						    }
						    if(null != resultVariable && '' != resultVariable){
						    	cell.resultVariable = resultVariable;
						    }
						    if(null != skipExpression && '' != skipExpression){
						    	cell.skipExpression = skipExpression;
						    }
						    
						    //基本配置中字段描述
						    if(serviceField_setvalue(cell)== false){
						    	return;
						    }
							graph.startEditing();
						}
						finally
						{
							graph.getModel().endUpdate();
							graph_refresh.refresh();
						}
		          }  
		     }]  
	    });
	}else{
		//基本配置与高级配置
		editServiceTaskNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//会签配置
		initMultiInstance(cell);
	    serviceTaskNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:serviceTaskNodeAttributeForm,layout:'border'},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm}
	        ]
	    });
	}
}


//点击确定按钮设置mxgraph中cell属性（字段描述）
function serviceField_setvalue(cell){
	var serviceNodeAttributeField_value;
	//验证操作
	for(var i=0;i<serviceNodeAttributeFieldGrid.getStore().getCount();i++){
		var field = serviceNodeAttributeFieldGrid.store.getAt(i).data.field;
		var string = serviceNodeAttributeFieldGrid.store.getAt(i).data.string;
		var fieldexpression = serviceNodeAttributeFieldGrid.store.getAt(i).data.fieldexpression;
		if("undefined" == typeof(field) || null == field || field == ""){
			serviceNodeAttributeFieldGrid.store.getAt(i).data.field="";
			serviceNodeAttributeField_value="";
			msgTishi('请输入基本配置中字段名称！');
			return false;	
		}
		if("undefined" == typeof(string) || null == string || string == ""){
			serviceNodeAttributeFieldGrid.store.getAt(i).data.string="";
			serviceNodeAttributeField_value="";
			msgTishi('请输入基本配置中字段值!');
			return false;
		}
		if("undefined" == typeof(fieldexpression) || null == fieldexpression || fieldexpression == ""){
			serviceNodeAttributeFieldGrid.store.getAt(i).data.fieldexpression="";
			serviceNodeAttributeField_value="";
			msgTishi('请输入基本配置中表达式!');
			return false;
		}
	}
	//赋值操作
	for(var i=0;i<serviceNodeAttributeFieldGrid.getStore().getCount();i++){
		var field = serviceNodeAttributeFieldGrid.store.getAt(i).data.field;
		var string = serviceNodeAttributeFieldGrid.store.getAt(i).data.string;
		var fieldexpression = serviceNodeAttributeFieldGrid.store.getAt(i).data.fieldexpression;
		if(null != serviceNodeAttributeField_value && "" != serviceNodeAttributeField_value){
			serviceNodeAttributeField_value = serviceNodeAttributeField_value+"#"+field+"@"+string+"@"+fieldexpression;
		}else{
			serviceNodeAttributeField_value = field+"@"+string+"@"+fieldexpression;
		}
	}
	//赋值
	if(null != serviceNodeAttributeField_value && "" != serviceNodeAttributeField_value){
		cell.serviceNodeAttributeField_value = serviceNodeAttributeField_value;
		serviceNodeAttributeField_value="";
	}
}