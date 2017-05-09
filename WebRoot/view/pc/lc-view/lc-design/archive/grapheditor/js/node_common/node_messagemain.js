//基本流程消息设置
//消息属性表格
var message_grid;
//信号属性数据源
var message_store;
function initmessage_grid(){
	var event_typeStore;
	var data_value;
	var message_node_value = Ext.getCmp('message_node_value').getValue();
	if(null != message_node_value && "" != message_node_value){
		/**1级grid显示即总Grid使用#分割**/
		var message_node_valueArray = message_node_value.split("#");
		for(var i = 0; i < message_node_valueArray.length; i++){
			var message_node_val = message_node_valueArray[i];
			if("undefined" != typeof(message_node_val)){
				/**grid行列**/
				var message_node_v = message_node_val.split("@");
				if(null == data_value || "" == data_value){
					data_value = "['"+message_node_v[0]+"','"+message_node_v[1]+"','"+message_node_v[2]+"',']";
				}else{
					data_value = data_value+","+"['"+message_node_v[0]+"','"+message_node_v[1]+"','"+message_node_v[2]+"'',]";
				}
			}
		}
	}
	var data;
	if("undefined" != typeof(data_value)){
		data = "["+data_value+"]";
		data = Ext.decode(data);
	}
	//列的类型
	var event_Plant = Ext.data.Record.create([
           {name:'ID',type:'String'},
           {name:'name',type:'string'}
    ]);
	//定义数据源
	message_store = Ext.create('Ext.data.Store',{  
           fields:['ID','name','scope'],  
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
	message_grid = Ext.create('Ext.grid.Panel',{
			store:message_store,
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
					header:'编号',
					dataIndex:'ID',
					flex:1,
	                editor:{
	                    xtype:'textfield'
	                }
				},
				{
					header:'名称',
					dataIndex:'name',
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
				message_grid.down('#node_message_del_button').setDisabled(!records.length);
			},
			tbar:[{
				  text:'增 加',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  handler:function(){
		            var model = Ext.create(message_grid.getStore().model);  
				    /**model.set('','');**/  
				    message_grid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'node_message_del_button',
				  handler:function(){
					 var model = message_grid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           message_grid.getStore().remove(message_grid.getSelectionModel().getSelection());  
				           message_grid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	message_store.load(); 
}

//点击确定按钮设置mxgraph中cell属性
var message_node_value;
function message_setvalue(cell){
	//验证操作
	for(var i=0;i<message_grid.getStore().getCount();i++){
		var ID = message_grid.store.getAt(i).data.ID;
		var name = message_grid.store.getAt(i).data.name;
		if("undefined" == typeof(ID) || null == ID || ID == ""){
			datamainProperties_grid.store.getAt(i).data.ID="";
			datamainProperties_node_value="";
			msgTishi('请输入编号!');
			return false;	
		}
		if("undefined" == typeof(name) || null == name || name == ""){
			datamainProperties_grid.store.getAt(i).data.name="";
			datamainProperties_node_value="";
			msgTishi('请输入名称!');
			return false;	
		}
	}
	//赋值操作
	for(var i=0;i<message_grid.getStore().getCount();i++){
		var ID = message_grid.store.getAt(i).data.ID;
		var name = message_grid.store.getAt(i).data.name;
		var type = message_grid.store.getAt(i).data.scope;
		if(null != message_node_value && "" != message_node_value){
			message_node_value = message_node_value+"#"+ID+"@"+name;
		}else{
			message_node_value = ID+"@"+name;
		}
	}
	//赋值
	if(null != message_node_value && "" != message_node_value){
		Ext.getCmp('message_node_value').setValue(message_node_value);
		message_node_value="";
	}
}