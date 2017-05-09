//节点的共同事件配置属性
//事件表格
var event_grid;
//事件数据源
var event_store;
function event_task_grid(cell,flag){
	var event_typeStore;
	//数据注意该数据主要从mxgraph属性中获取
	var data_value;
	var event_node_value = cell.event_node_value;
	if(null != event_node_value && "" != event_node_value){
		/**1级grid显示即总Grid使用#分割**/
		var event_node_valueArray = event_node_value.split("#");
		for(var i = 0; i < event_node_valueArray.length; i++){
			var event_node_val = event_node_valueArray[i];
			if("undefined" != typeof(event_node_val)){
				/**grid行列**/
				var event_node_v = event_node_val.split("@");
				if(null == data_value || "" == data_value){
					data_value = "['"+event_node_v[0]+"','"+event_node_v[1]+"','"+event_node_v[2]+"','"+event_node_v[3]+"']";
				}else{
					data_value = data_value+","+"['"+event_node_v[0]+"','"+event_node_v[1]+"','"+event_node_v[2]+"','"+event_node_v[3]+"',]";
				}
			}
		}
	}
	var data;
	if("undefined" != typeof(data_value)){
		data = "["+data_value+"]";
		data = Ext.decode(data);
	}
	//定义列
	//事件类型数据源
	var events_Store = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["create","create"],["assignment","assignment"],["complete","complete"],["All","All"]]
	});
	var event_typeStore = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["javaclass","javaclass"],["express","express"]]
	});
	//列的类型
	var event_Plant = Ext.data.Record.create([
           {name:'javaclass_express',type:'String'},
           {name:'event_type',type:'string'},
           {name:'event',type:'string'},
           {name:'fields',type:'string'}
    ]);
	//定义数据源
	event_store = Ext.create('Ext.data.Store',{  
           fields:['javaclass_express','event_type','event','fields'],  
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
	event_grid = Ext.create('Ext.grid.Panel',{
			store:event_store,
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
         	scrollable:'x',
         	scrollable:'y',
         	region:'center',
	        plugins:[this.editing], 
			viewConfig:{
				emptyText:'暂无数据',
				stripeRows:true
			},
			loadMask:{
				msg:'正在加载...'
			},
			columns:[
				{
					header:'执行的类或表达式',
					dataIndex:'javaclass_express',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'类型',
					dataIndex:'event_type',
	                editor:{
		                    name:"name",
				            hiddenName:'name',
				            valueField:"value",
				            displayField:"name",
				            xtype:"combo",
				            anchor:'100%',
				            store:event_typeStore,
				            emptyText:"请选择",
				            mode:"local",
				            triggerAction:"all",
				            editable:false
	                }
				},
				{
					header:'事件',
					dataIndex:'event',
	                editor:{
	                     	xtype:'combo',
		                    name:"name",
				            hiddenName:'name',
				            valueField:"value",
				            displayField:"name",
				            xtype:"combo",
				            anchor:'100%',
				            store:events_Store,
				            emptyText:"请选择",
				            mode:"local",
				            triggerAction:"all",
				            editable:false
	                }
				},
				{
					header:'配置字段',
					flex:1,
					dataIndex:'fields',
	                editor:{
	                    xtype:'textfield',
	                    readOnly:true,
	                    trigger1Cls:'x-form-cl-trigger',
						onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this)},
	                    listeners:{
			            	render:function(p) {   
								p.getEl().on('click', function(p){   
									showFieldWin();
								});  
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
				event_grid.down('#node_event_del_button').setDisabled(!records.length);
			},
			tbar:[{
				  text:'增 加',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  id:'node_event_add_button',
				  handler:function(){
		            var model = Ext.create(event_grid.getStore().model);  
				    /**model.set('','');**/  
				    event_grid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'node_event_del_button',
				  handler:function(){
					 var model = event_grid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           event_grid.getStore().remove(event_grid.getSelectionModel().getSelection());  
				           event_grid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	event_store.load(); 
}

//点击确定按钮设置mxgraph中cell属性
var event_node_value;
function event_setvalue(cell){
	//验证操作
	for(var i=0;i<event_grid.getStore().getCount();i++){
		var event_type = event_grid.store.getAt(i).data.event_type;
		var javaclass_express = event_grid.store.getAt(i).data.javaclass_express;
		if("undefined" == typeof(event_type) || null == event_type || event_type == ""){
			event_grid.store.getAt(i).data.event_type="";
			event_node_value="";
			msgTishi('请选择事件类型!');
			return false;	
		}
		if("undefined" == typeof(javaclass_express) || null == javaclass_express || javaclass_express == ""){
			event_grid.store.getAt(i).data.javaclass_express="";
			event_node_value="";
			msgTishi('请输入执行的类或表达式!');
			return false;
		}
	}
	//赋值操作
	for(var i=0;i<event_grid.getStore().getCount();i++){
		var javaclass_express = event_grid.store.getAt(i).data.javaclass_express;
		var event_type = event_grid.store.getAt(i).data.event_type;
		var event = event_grid.store.getAt(i).data.event;
		var fields = event_grid.store.getAt(i).data.fields;
		if(null != event_node_value && "" != event_node_value){
			event_node_value = event_node_value+"#"+javaclass_express+"@"+event_type+"@"+event+"@"+fields;
		}else{
			event_node_value = javaclass_express+"@"+event_type+"@"+event+"@"+fields;
		}
	}
	//赋值
	if(null != event_node_value && "" != event_node_value){
		cell.event_node_value = event_node_value;
		event_node_value="";
	}
}



/**设置字段**/
var fieldWin;
var fieldGrid;
function showFieldWin(){
	initField();
	fieldWin = Ext.create('top.Ext.Window',{
         title:'配置字段',  
         layout:'fit', 
         width:600, 
         height:300,
         resizable:true, 
         modal:true,  
         border:false,
         closable:false,  
         items:fieldGrid,
         buttons:[{  
         	text:'确 定',  
          	handler:function(){
          		if(setField() == false){
          			return;
          		}
          		fieldWin.close();
          	}  
         },{  
          text:'取 消',  
          handler:function(){fieldWin.close()}  
         }]  
     });  
     fieldWin.show(); 
}
function initField(){
	var fielddata;
	var field_data_value;
	var field_node_value = event_grid.getSelectionModel().selected.items[0].data.fields;
	console.info(field_node_value);
	if(null != field_node_value && "" != field_node_value){
		var field_node_valueArray = field_node_value.split("$");
		for(var i = 0; i < field_node_valueArray.length; i++){
			var field_node_val = field_node_valueArray[i];
			if("undefined" != typeof(field_node_val)){
				var field_node_v = field_node_val.split("&");
				if(null == field_data_value || "" == field_data_value){
					field_data_value = "['"+field_node_v[0]+"','"+field_node_v[1]+"','"+field_node_v[2]+"']";
				}else{
					field_data_value = field_data_value+","+"['"+field_node_v[0]+"','"+field_node_v[1]+"','"+field_node_v[2]+"']";
				}
			}
		}
	}
	if("undefined" != typeof(field_data_value)){
		fielddata = "["+field_data_value+"]";
		fielddata = Ext.decode(fielddata);
	}
	
	//事件类型数据源
	var fieldtype_store = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["string","string"],["expression","expression"]]
	});
	//列的类型
	var event_Plant = Ext.data.Record.create([
           {name:'fieldName',type:'String'},
           {name:'fieldtype',type:'String'},
           {name:'fieldValue',type:'String'}
    ]);
	//定义数据源
	field_store = Ext.create('Ext.data.Store',{  
           fields:['fieldName','fieldtype','fieldValue'],  
           data:fielddata,  
           proxy:{  
              type:'memory',  
              reader:{  
                  type:'json',  
                  rootProperty:'items'  
              }  
          }  
    })
	this.editing = Ext.create('top.Ext.grid.plugin.CellEditing',{
    	clicksToEdit:1
    });
	//定义grid编辑列
	fieldGrid = Ext.create('top.Ext.grid.Panel',{
			store:field_store,
			requires:[
		        'top.Ext.grid.plugin.CellEditing',
		        'top.Ext.form.field.Text',
		        'top.Ext.form.field.TextArea',
		        'top.Ext.toolbar.TextItem'
		    ],
			columnLines:true,
	        multiSelect:true,
	        border:true,
	        /**新方法使用开始**/  
         	scrollable:true,  
         	scrollable:'x',
         	scrollable:'y',
         	region:'center',
	        plugins:[this.editing], 
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
					dataIndex:'fieldName',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'字段类型',
					flex:1,
					dataIndex:'fieldtype',
	                editor:{
		                    name:"name",
				            hiddenName:'name',
				            valueField:"value",
				            displayField:"name",
				            xtype:"combo",
				            anchor:'100%',
				            store:fieldtype_store,
				            emptyText:"请选择",
				            mode:"local",
				            triggerAction:"all",
				            editable:false
	                }
				},
				{
					header:'字段值',
					flex:1,
					dataIndex:'fieldValue',
	                editor:{xtype:'textfield'}
				}
			],
			listeners:{  
			    selectionChange:'selectionChange'  
			},
			//选中的记录发生变化过后的事件  
			selectionChange:function(view, records){  
				fieldGrid.down('#field_del_button').setDisabled(!records.length);
			},
			tbar:[{
				  text:'增 加',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  id:'node_field_add_button',
				  handler:function(){
		            var model = Ext.create(event_grid.getStore().model);  
				    fieldGrid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'field_del_button',
				  handler:function(){
					 var model = fieldGrid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     top.Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           fieldGrid.getStore().remove(fieldGrid.getSelectionModel().getSelection());  
				           fieldGrid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	fieldGrid.getStore().load(); 
}

var field_node_value;
function setField(){
	//验证操作
	for(var i=0;i<fieldGrid.getStore().getCount();i++){
		var fieldName = fieldGrid.store.getAt(i).data.fieldName;
		var fieldtype = fieldGrid.store.getAt(i).data.fieldtype;
		var fieldValue = fieldGrid.store.getAt(i).data.fieldValue;
		if("undefined" == typeof(fieldtype) || null == fieldtype || fieldtype == ""){
			fieldGrid.store.getAt(i).data.fieldtype="";
			field_node_value="";
			msgTishi('请选择事件类型!');
			return false;	
		}
		if("undefined" == typeof(fieldName) || null == fieldName || fieldName == ""){
			fieldGrid.store.getAt(i).data.fieldName="";
			field_node_value="";
			msgTishi('请输入字段名称!');
			return false;
		}
	}
	//赋值操作
	for(var i=0;i<fieldGrid.getStore().getCount();i++){
		var fieldName = fieldGrid.store.getAt(i).data.fieldName;
		var fieldtype = fieldGrid.store.getAt(i).data.fieldtype;
		var fieldValue = fieldGrid.store.getAt(i).data.fieldValue;
		if(null != field_node_value && "" != field_node_value){
			field_node_value = field_node_value+"$"+fieldName+"&"+fieldtype+"&"+fieldValue;
		}else{
			console.info(field_node_value);
			field_node_value = fieldName+"&"+fieldtype+"&"+fieldValue;
		}
	}
	//赋值
	if(null != field_node_value && "" != field_node_value){
		event_grid.getSelectionModel().getSelected().items[0].set("fields",field_node_value);
		field_node_value="";
	}
}