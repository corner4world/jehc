/**表单配置**/
//事件表格
var form_grid;
//事件数据源
var form_store;

//抽取出共同方法即渲染编辑表格方法(注意:flag为1表示只有开始和结束 2表示开始，结束，分配任务)
function initform_grid(cell){
	var event_typeStore;
	//数据注意该数据主要从mxgraph属性中获取
	var form_data_value;
	var form_node_value = cell.form_node_value;
	if(null != form_node_value && "" != form_node_value){
		var form_node_valueArray = form_node_value.split("#");
		for(var i = 0; i < form_node_valueArray.length; i++){
			var form_node_val = form_node_valueArray[i];
			if("undefined" != typeof(form_node_val)){
				var form_node_v = form_node_val.split("@");
				var fv0 = form_node_v[0];
				var fv1 = form_node_v[1];
				var fv2 = form_node_v[2];
				var fv3 = form_node_v[3];
				var fv4 = form_node_v[4];
				var fv5 = form_node_v[5];
				var fv6 = form_node_v[6];
				var fv7 = form_node_v[7];
				var fv8 = form_node_v[8];
				var fv9 = form_node_v[9];
				var fv10 = form_node_v[10];
				if(null == form_data_value || "" == form_data_value){
					if("undefined" == typeof(fv0) || "undefined" == fv0){
						fv0 = '';
					}
					if("undefined" == typeof(fv1) || "undefined" == fv1){
						fv1 = '';
					}
					if("undefined" == typeof(fv2) || "undefined" == fv2){
						fv2 = '';
					}
					if("undefined" == typeof(fv3) || "undefined" == fv3){
						fv3 = '';
					}
					if("undefined" == typeof(fv4) || "undefined" == fv4){
						fv4 = '';
					}
					if("undefined" == typeof(fv5) || "undefined" == fv5){
						fv5 = '';
					}
					if("undefined" == typeof(fv6) || "undefined" == fv6){
						fv6 = '';
					}
					if("undefined" == typeof(fv7) || "undefined" == fv7){
						fv7 = '';
					}
					if("undefined" == typeof(fv8) || "undefined" == fv8){
						fv8 = '';
					}
					if("undefined" == typeof(fv9) || "undefined" == fv9){
						fv9 = '';
					}
					if("undefined" == typeof(fv10) || "undefined" == fv10){
						fv10 = '';
					}
					form_data_value = "['"+fv0+"','"+fv1+"','"+fv2+"','"+fv3+"','"+fv4+"','"+fv5+"','"+fv6+"','"+fv7+"','"+fv8+"','"+fv9+"','"+fv10+"']";
				}else{
					form_data_value = form_data_value+","+"['"+fv0+"','"+fv1+"','"+fv2+"','"+fv3+"','"+fv4+"','"+fv5+"','"+fv6+"','"+fv7+"','"+fv8+"','"+fv9+"','"+fv10+"',]";
				}
			}
		}
	}
	var data;
	if("undefined" != typeof(form_data_value)){
		data = "["+form_data_value+"]";
		data = Ext.decode(data);
	}
	//定义列
	var event_typeStore = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["javaclass","javaclass"],["express","express"]]
	});
	//列的类型
	var event_Plant = Ext.data.Record.create([
           {name:'formID',type:'String'},
           {name:'formName',type:'string'},
           {name:'formType',type:'string'},
           {name:'formExpression',type:'string'},
           {name:'formVariable',type:'string'},
           {name:'formDefault',type:'string'},
           {name:'formDatePattern',type:'string'},
           {name:'formReadable',type:'string'},
           {name:'formWriteable',type:'string'},
           {name:'formRequired',type:'string'},
           {name:'formFormValues',type:'string'}
    ]);
	//定义数据源
	form_store = Ext.create('Ext.data.Store',{  
           fields:['formID','formName','formType','formExpression','formVariable','formDefault','formDatePattern','formReadable','formWriteable','formRequired','formFormValues'],  
           data:data,  
           proxy:{  
              type:'memory',  
              reader:{  
                  type:'json',  
                  rootProperty:'items'  
              }  
          }  
    })
	var form_store_t = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["string","string"],["long","long"],["enum","enum"],["date","date"],["boolean","boolean"]]
	});
	var form_store_readable = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["true","true"],["false","false"]]
	});
	var form_store_writeable = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["true","true"],["false","false"]]
	});
	var form_store_required = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["true","true"],["false","false"]]
	});
	this.editing = Ext.create('Ext.grid.plugin.CellEditing',{
    	clicksToEdit:1
    });
	//定义grid编辑列
	form_grid = Ext.create('Ext.grid.Panel',{
			store:form_store,
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
					header:'表单编号',
					dataIndex:'formID',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'表单名称',
					dataIndex:'formName',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'类型',
					dataIndex:'formType',
	                editor:{
		                    name:"name",
				            hiddenName:'name',
				            valueField:"value",
				            displayField:"name",
				            xtype:"combo",
				            anchor:'100%',
				            store:form_store_t,
				            emptyText:"请选择",
				            mode:"local",
				            triggerAction:"all",
				            editable:false
	                }
				},
				{
					header:'表达式',
					dataIndex:'formExpression',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'变量',
					dataIndex:'formVariable',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'默认',
					dataIndex:'formDefault',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'日期格式',
					dataIndex:'formDatePattern',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'可读',
					dataIndex:'formReadable',
	                editor:{
	                     	xtype:'combo',
		                    name:"name",
				            hiddenName:'name',
				            valueField:"value",
				            displayField:"name",
				            xtype:"combo",
				            anchor:'100%',
				            store:form_store_readable,
				            emptyText:"请选择",
				            mode:"local",
				            triggerAction:"all",
				            editable:false
	                }
				},
				{
					header:'可写',
					dataIndex:'formWriteable',
	                editor:{
	                     	xtype:'combo',
		                    name:"name",
				            hiddenName:'name',
				            valueField:"value",
				            displayField:"name",
				            xtype:"combo",
				            anchor:'100%',
				            store:form_store_writeable,
				            emptyText:"请选择",
				            mode:"local",
				            triggerAction:"all",
				            editable:false
	                }
				},
				{
					header:'是否校验',
					dataIndex:'formRequired',
	                editor:{
	                     	xtype:'combo',
		                    name:"name",
				            hiddenName:'name',
				            valueField:"value",
				            displayField:"name",
				            xtype:"combo",
				            anchor:'100%',
				            store:form_store_required,
				            emptyText:"请选择",
				            mode:"local",
				            triggerAction:"all",
				            editable:false
	                }
				},
				{
					header:'配置字段',
					flex:1,
					dataIndex:'formFormValues',
	                editor:{
	                    xtype:'textfield',
	                    readOnly:true,
	                    listeners:{
			            	render:function(p) {   
								p.getEl().on('click', function(p){   
									showFormValuesWin();
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
				form_grid.down('#node_event_form_del_button').setDisabled(!records.length);
			},
			tbar:[{
				  text:'增 加',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  id:'node_event_form_add_button',
				  handler:function(){
		            var model = Ext.create(form_grid.getStore().model);  
				    /**model.set('','');**/  
				    form_grid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'node_event_form_del_button',
				  handler:function(){
					 var model = form_grid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           form_grid.getStore().remove(form_grid.getSelectionModel().getSelection());  
				           form_grid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	form_store.load(); 
}



//点击确定按钮设置mxgraph中cell属性
var form_node_value;
function event_form_setvalue(cell){
	//验证操作
	for(var i=0;i<form_grid.getStore().getCount();i++){
		var formID = form_grid.store.getAt(i).data.formID;
		var formName = form_grid.store.getAt(i).data.formName;
		if("undefined" == typeof(formID) || null == formID || formID == ""){
			form_grid.store.getAt(i).data.formID="";
			form_node_value="";
			msgTishi('请输入表单编号!');
			return false;	
		}
		if("undefined" == typeof(formName) || null == formName || formName == ""){
			form_grid.store.getAt(i).data.formName="";
			form_node_value="";
			msgTishi('请输入表单名称!');
			return false;
		}
	}
	//赋值操作
	for(var i=0;i<form_grid.getStore().getCount();i++){
		var formID = form_grid.store.getAt(i).data.formID;
		var formName = form_grid.store.getAt(i).data.formName;
        var formType = form_grid.store.getAt(i).data.formType;
        var formExpression = form_grid.store.getAt(i).data.formExpression;
        var formVariable = form_grid.store.getAt(i).data.formVariable;
        var formDefault = form_grid.store.getAt(i).data.formDefault;
        var formDatePattern = form_grid.store.getAt(i).data.formDatePattern;
        var formReadable = form_grid.store.getAt(i).data.formReadable;
        var formWriteable = form_grid.store.getAt(i).data.formWriteable;
        var formRequired = form_grid.store.getAt(i).data.formRequired;
        var formFormValues = form_grid.store.getAt(i).data.formFormValues;
		if(null != form_node_value && "" != form_node_value){
			form_node_value = form_node_value+"#"+formID+"@"+formName+"@"+formType+"@"+formExpression+"@"+formVariable+"@"+formDefault+"@"+formDatePattern+"@"+formReadable+"@"+formWriteable+"@"+formRequired+"@"+formFormValues;
		}else{
			form_node_value = formID+"@"+formName+"@"+formType+"@"+formExpression+"@"+formVariable+"@"+formDefault+"@"+formDatePattern+"@"+formReadable+"@"+formWriteable+"@"+formRequired+"@"+formFormValues;
		}
	}
	//赋值
	if(null != form_node_value && "" != form_node_value){
		cell.form_node_value = form_node_value;
		form_node_value="";
	}
}



/**设置表单值**/
var formValuesWin;
var formValuesGrid;
var formValuesStore;
function showFormValuesWin(){
	initFormValues();
	formValuesWin = Ext.create('top.Ext.Window',{  
         title:'配置表单字段属性',  
         layout:'fit', 
         width:600, 
         height:300,
         resizable:true, 
         modal:true,  
         border:false,
         closable:false,  
         items:formValuesGrid,
         buttons:[{  
         	text:'确 定',  
          	handler:function(){
          		if(setFormValues() == false){
          			return;
          		}
          		formValuesWin.close();
          	}  
         },{  
          text:'取 消',  
          handler:function(){formValuesWin.close()}  
         }]  
     });  
     formValuesWin.show(); 
}
function initFormValues(){
	var formdata;
	var form_data_value;
	var form_node_value = form_grid.getSelectionModel().selected.items[0].data.formFormValues;
	if(null != form_node_value && "" != form_node_value){
		var form_node_valueArray = form_node_value.split("$");
		for(var i = 0; i < form_node_valueArray.length; i++){
			var form_node_val = form_node_valueArray[i];
			if("undefined" != typeof(form_node_val)){
				var form_node_v = form_node_val.split("&");
				if(null == form_data_value || "" == form_data_value){
					form_data_value = "['"+form_node_v[0]+"','"+form_node_v[1]+"']";
				}else{
					form_data_value = form_data_value+","+"['"+form_node_v[0]+"','"+form_node_v[1]+"']";
				}
			}
		}
	}
	if("undefined" != typeof(form_data_value)){
		formdata = "["+form_data_value+"]";
		formdata = Ext.decode(formdata);
	}
	
	//列的类型
	var event_Plant = Ext.data.Record.create([
           {name:'fname',type:'String'},
           {name:'fid',type:'String'}
    ]);
	//定义数据源
	formValuesStore = Ext.create('Ext.data.Store',{  
           fields:['fname','fid'],  
           data:formdata,  
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
	formValuesGrid = Ext.create('top.Ext.grid.Panel',{
			store:formValuesStore,
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
					header:'编号',
					flex:1,
					dataIndex:'fid',
	                editor:{xtype:'textfield'}
				},
				{
					header:'名称',
					dataIndex:'fname',
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
				formValuesGrid.down('#node_field_form_del_button').setDisabled(!records.length);
			},
			tbar:[{
				  text:'增 加',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  id:'node_field_form_add_button',
				  handler:function(){
		            var model = Ext.create(formValuesGrid.getStore().model);  
				    formValuesGrid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'node_field_form_del_button',
				  handler:function(){
					 var model = formValuesGrid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     top.Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           formValuesGrid.getStore().remove(formValuesGrid.getSelectionModel().getSelection());  
				           formValuesGrid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	formValuesGrid.getStore().load(); 
}

var form_values_node_value;
function setFormValues(){
	//验证操作
	for(var i=0;i<formValuesGrid.getStore().getCount();i++){
		var fname = formValuesGrid.store.getAt(i).data.fname;
		var fid = formValuesGrid.store.getAt(i).data.fid;
		if("undefined" == typeof(fid) || null == fid || fid == ""){
			formValuesGrid.store.getAt(i).data.fid="";
			form_values_node_value="";
			msgTishi('请输入编号!');
			return false;	
		}
		if("undefined" == typeof(fname) || null == fname || fname == ""){
			formValuesGrid.store.getAt(i).data.fname="";
			form_values_node_value="";
			msgTishi('请输入名称!');
			return false;
		}
	}
	//赋值操作
	for(var i=0;i<formValuesGrid.getStore().getCount();i++){
		var fname = formValuesGrid.store.getAt(i).data.fname;
		var fid = formValuesGrid.store.getAt(i).data.fid;
		if(null != form_values_node_value && "" != form_values_node_value){
			form_values_node_value = form_values_node_value+"$"+fname+"&"+fid;
		}else{
			form_values_node_value = fname+"&"+fid;
		}
	}
	//赋值
	if(null != form_values_node_value && "" != form_values_node_value){
		form_grid.getSelectionModel().getSelected().items[0].set("formFormValues",form_values_node_value);
		form_values_node_value="";
	}
}