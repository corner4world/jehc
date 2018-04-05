//内嵌子流程节点
var callActivityNodeAttributeWin;
var callActivityNodeAttributeTabPanel;
var callActivityNodeAttributeForm;
function showCallActivityNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		callActivityNodeAttributePanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),callActivityNodeAttributeTabPanel);
		basePanel.setHidden(true);
	}else{
		callActivityNodeAttributePanel(cell,graph_refresh);
		callActivityNodeAttributeWin = new Ext.Window({  
	         title:'配置内嵌子流程属性',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:callActivityNodeAttributeTabPanel,
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
					 	
					 	//4基本属性
					 	if(callActivity_input_setvalue(cell)== false){
					 		return;
					 	}
					 	if(callActivity_out_setvalue(cell)== false){
					 		return;
					 	}
						graph.startEditing();
						callActivityNodeAttributeWin.close(this); 
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
	            callActivityNodeAttributeWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     callActivityNodeAttributeWin.show(); 
	}
}

var CallActivityNodeAttributeFormSenior;
var callActivityNodeAttributeInputParmGrid;
var callActivityNodeAttributeOutParmGrid;
function editCallActivityNodeAttributeForm(cell){
	initCallActivityNodeAttributeInputParm(cell);
	initCallActivityNodeAttributeOutParm(cell);
	/**
	  *基础配置
	**/
	CallActivityNodeAttributeFormSenior = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        region:"center",
        items:[{
       	    xtype:'textfield',  
            fieldLabel:'被呼叫流程',  
           	name:'calledElement', 
           	id:'calledElement', 
           	anchor:'100%'
         },callActivityNodeAttributeInputParmGrid,callActivityNodeAttributeOutParmGrid]
    });
}

//输入参数
function initCallActivityNodeAttributeInputParm(cell){
	var callActivityNodeAttributeInputParmdata;
	var callActivity_input_value = cell.callActivity_input_value;
	if(null != callActivity_input_value && "" != callActivity_input_value){
		/**1级grid显示即总Grid使用#分割**/
		var callActivity_input_valueArray = callActivity_input_value.split("#");
		for(var i = 0; i < callActivity_input_valueArray.length; i++){
			var callActivity_input_val = callActivity_input_valueArray[i];
			if("undefined" != typeof(callActivity_input_val)){
				/**grid行列**/
				var event_node_v = callActivity_input_val.split("@");
				if(null == callActivityNodeAttributeInputParmdata || "" == callActivityNodeAttributeInputParmdata){
					callActivityNodeAttributeInputParmdata = "['"+event_node_v[0]+"','"+event_node_v[1]+"','"+event_node_v[2]+"']";
				}else{
					callActivityNodeAttributeInputParmdata = callActivityNodeAttributeInputParmdata+","+"['"+event_node_v[0]+"','"+event_node_v[1]+"','"+event_node_v[2]+"',]";
				}
			}
		}
	}
	var data;
	if("undefined" != typeof(callActivityNodeAttributeInputParmdata)){
		data = "["+callActivityNodeAttributeInputParmdata+"]";
		data = Ext.decode(data);
	}
	//定义数据源
	callActivityNodeAttributeInputParm_store = Ext.create('Ext.data.Store',{  
           fields:['source','sourceExpression','target'],  
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
	callActivityNodeAttributeInputParmGrid = Ext.create('Ext.grid.Panel',{
			store:callActivityNodeAttributeInputParm_store,
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
	        height:150,
         	scrollable:true,  
         	scrollable:'x',
         	scrollable:'y',
         	region:'west',
	        plugins:[this.editing], 
	        autoScoller:true,
	        title:'输入参数',
			viewConfig:{
				emptyText:'暂无数据',
				stripeRows:true
			},
			loadMask:{
				msg:'正在加载...'
			},
			columns:[
				{
					header:'源',
					dataIndex:'source',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'源表达式',
					dataIndex:'sourceExpression',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'目标',
					flex:1,
					dataIndex:'target',
	                editor:{xtype:'textfield'}
				}
			],
			listeners:{  
			    selectionChange:'selectionChange'  
			},
			//选中的记录发生变化过后的事件  
			selectionChange:function(view, records){  
				callActivityNodeAttributeInputParmGrid.down('#call_del_button').setDisabled(!records.length);
			},
			tools:[{
				  text:'增 加',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  xtype:'button',
				  id:'node_call_add_button',
				  handler:function(){
		            var model = Ext.create(callActivityNodeAttributeInputParmGrid.getStore().model);  
				    callActivityNodeAttributeInputParmGrid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  xtype:'button',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'call_del_button',
				  handler:function(){
					 var model = callActivityNodeAttributeInputParmGrid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           callActivityNodeAttributeInputParmGrid.getStore().remove(callActivityNodeAttributeInputParmGrid.getSelectionModel().getSelection());  
				           callActivityNodeAttributeInputParmGrid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	callActivityNodeAttributeInputParmGrid.getStore().load(); 
}

//输出参数
function initCallActivityNodeAttributeOutParm(cell){
	var callActivityNodeAttributeOutParmdata;
	var callActivity_out_value = cell.callActivity_out_value;
	if(null != callActivity_out_value && "" != callActivity_out_value){
		/**1级grid显示即总Grid使用#分割**/
		var callActivity_out_valueArray = callActivity_out_value.split("#");
		for(var i = 0; i < callActivity_out_valueArray.length; i++){
			var callActivity_out_val = callActivity_out_valueArray[i];
			if("undefined" != typeof(callActivity_out_val)){
				/**grid行列**/
				var event_node_v = callActivity_out_val.split("@");
				if(null == callActivityNodeAttributeOutParmdata || "" == callActivityNodeAttributeOutParmdata){
					callActivityNodeAttributeOutParmdata = "['"+event_node_v[0]+"','"+event_node_v[1]+"','"+event_node_v[2]+"']";
				}else{
					callActivityNodeAttributeOutParmdata = callActivityNodeAttributeOutParmdata+","+"['"+event_node_v[0]+"','"+event_node_v[1]+"','"+event_node_v[2]+"',]";
				}
			}
		}
	}
	var data;
	if("undefined" != typeof(callActivityNodeAttributeOutParmdata)){
		data = "["+callActivityNodeAttributeOutParmdata+"]";
		data = Ext.decode(data);
	}
	
	//定义数据源
	callActivityNodeAttributeOutParm_store = Ext.create('Ext.data.Store',{  
           fields:['source','sourceExpression','target'],  
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
	callActivityNodeAttributeOutParmGrid = Ext.create('Ext.grid.Panel',{
			store:callActivityNodeAttributeOutParm_store,
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
	        height:150, 
         	scrollable:true,  
         	scrollable:'x',
         	scrollable:'y',
         	region:'center',
	        plugins:[this.editing], 
	        title:'输出参数',
			viewConfig:{
				emptyText:'暂无数据',
				stripeRows:true
			},
			loadMask:{
				msg:'正在加载...'
			},
			columns:[
				{
					header:'源',
					dataIndex:'source',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'源表达式',
					dataIndex:'sourceExpression',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'目标',
					flex:1,
					dataIndex:'target',
	                editor:{xtype:'textfield'}
				}
			],
			listeners:{  
			    selectionChange:'selectionChange'  
			},
			//选中的记录发生变化过后的事件  
			selectionChange:function(view, records){  
				callActivityNodeAttributeOutParmGrid.down('#callout_del_button').setDisabled(!records.length);
			},
			tools:[{
				  text:'增 加',
				  xtype:'button',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  id:'node_callout_add_button',
				  handler:function(){
		            var model = Ext.create(callActivityNodeAttributeOutParmGrid.getStore().model);  
				    callActivityNodeAttributeOutParmGrid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  xtype:'button',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'callout_del_button',
				  handler:function(){
					 var model = callActivityNodeAttributeOutParmGrid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           callActivityNodeAttributeOutParmGrid.getStore().remove(callActivityNodeAttributeOutParmGrid.getSelectionModel().getSelection());  
				           callActivityNodeAttributeOutParmGrid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	callActivityNodeAttributeOutParmGrid.getStore().load();
}

function callActivityNodeAttributePanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editCallActivityNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//会签配置
		initMultiInstance(cell);
	    callActivityNodeAttributeTabPanel = new Ext.TabPanel({
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
	        	{title:'基础配置',items:CallActivityNodeAttributeFormSenior,layout:'border',
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
					 	
					 	//4基本属性
					 	if(callActivity_input_setvalue(cell)== false){
					 		return;
					 	}
					 	if(callActivity_out_setvalue(cell)== false){
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
		editCallActivityNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//会签配置
		initMultiInstance(cell);
	    callActivityNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基础配置',items:CallActivityNodeAttributeFormSenior,layout:'border'},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm}
	        ]
	    });
	}
}



//点击确定按钮设置mxgraph中cell属性（输入参数）
function callActivity_input_setvalue(cell){
	var callActivity_input_value;
	//验证操作
	for(var i=0;i<callActivityNodeAttributeInputParmGrid.getStore().getCount();i++){
		var source = callActivityNodeAttributeInputParmGrid.store.getAt(i).data.source;
		var sourceExpression = callActivityNodeAttributeInputParmGrid.store.getAt(i).data.sourceExpression;
		var target = callActivityNodeAttributeInputParmGrid.store.getAt(i).data.target;
		if("undefined" == typeof(source) || null == source || source == ""){
			callActivityNodeAttributeInputParmGrid.store.getAt(i).data.source="";
			callActivity_input_value="";
			msgTishi('请输入输入参数中源！');
			return false;	
		}
		if("undefined" == typeof(sourceExpression) || null == sourceExpression || sourceExpression == ""){
			callActivityNodeAttributeInputParmGrid.store.getAt(i).data.sourceExpression="";
			callActivity_input_value="";
			msgTishi('请输入输入参数中源表达式!');
			return false;
		}
		if("undefined" == typeof(target) || null == target || target == ""){
			callActivityNodeAttributeInputParmGrid.store.getAt(i).data.target="";
			callActivity_input_value="";
			msgTishi('请输入输入参数中源目标!');
			return false;
		}
	}
	//赋值操作
	for(var i=0;i<callActivityNodeAttributeInputParmGrid.getStore().getCount();i++){
		var source = callActivityNodeAttributeInputParmGrid.store.getAt(i).data.source;
		var sourceExpression = callActivityNodeAttributeInputParmGrid.store.getAt(i).data.sourceExpression;
		var target = callActivityNodeAttributeInputParmGrid.store.getAt(i).data.target;
		if(null != callActivity_input_value && "" != callActivity_input_value){
			callActivity_input_value = callActivity_input_value+"#"+source+"@"+sourceExpression+"@"+target;
		}else{
			callActivity_input_value = source+"@"+sourceExpression+"@"+sourceExpression+"@"+target;
		}
	}
	//赋值
	if(null != callActivity_input_value && "" != callActivity_input_value){
		cell.callActivity_input_value = callActivity_input_value;
		callActivity_input_value="";
	}
}


//点击确定按钮设置mxgraph中cell属性（输出参数）
function callActivity_out_setvalue(cell){
	var callActivity_out_value;
	//验证操作
	for(var i=0;i<callActivityNodeAttributeOutParmGrid.getStore().getCount();i++){
		var source = callActivityNodeAttributeOutParmGrid.store.getAt(i).data.source;
		var sourceExpression = callActivityNodeAttributeOutParmGrid.store.getAt(i).data.sourceExpression;
		var target = callActivityNodeAttributeOutParmGrid.store.getAt(i).data.target;
		if("undefined" == typeof(source) || null == source || source == ""){
			callActivityNodeAttributeInputParmGrid.store.getAt(i).data.source="";
			callActivity_out_value="";
			msgTishi('请输入输出参数中源！');
			return false;	
		}
		if("undefined" == typeof(sourceExpression) || null == sourceExpression || sourceExpression == ""){
			callActivityNodeAttributeInputParmGrid.store.getAt(i).data.sourceExpression="";
			callActivity_out_value="";
			msgTishi('请输入输出参数中源表达式!');
			return false;
		}
		if("undefined" == typeof(target) || null == target || target == ""){
			callActivityNodeAttributeInputParmGrid.store.getAt(i).data.target="";
			callActivity_out_value="";
			msgTishi('请输入输出参数中源目标!');
			return false;
		}
	}
	//赋值操作
	for(var i=0;i<callActivityNodeAttributeOutParmGrid.getStore().getCount();i++){
		var source = callActivityNodeAttributeOutParmGrid.store.getAt(i).data.source;
		var sourceExpression = callActivityNodeAttributeOutParmGrid.store.getAt(i).data.sourceExpression;
		var target = callActivityNodeAttributeOutParmGrid.store.getAt(i).data.target;
		if(null != callActivity_out_value && "" != callActivity_out_value){
			callActivity_out_value = callActivity_out_value+"#"+source+"@"+sourceExpression+"@"+target;
		}else{
			callActivity_out_value = source+"@"+sourceExpression+"@"+sourceExpression+"@"+target;
		}
	}
	//赋值
	if(null != callActivity_out_value && "" != callActivity_out_value){
		cell.callActivity_out_value = callActivity_out_value;
		callActivity_out_value="";
	}
}