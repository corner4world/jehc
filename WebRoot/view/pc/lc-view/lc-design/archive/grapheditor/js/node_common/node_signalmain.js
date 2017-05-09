//基本流程信号设置
//信号属性表格
var signal_grid;
//信号属性数据源
var signal_store;
function initSignal_grid(){
	var event_typeStore;
	var data_value;
	var signal_node_value = Ext.getCmp('signal_node_value').getValue();
	if(null != signal_node_value && "" != signal_node_value){
		/**1级grid显示即总Grid使用#分割**/
		var signal_node_valueArray = signal_node_value.split("#");
		for(var i = 0; i < signal_node_valueArray.length; i++){
			var signal_node_val = signal_node_valueArray[i];
			if("undefined" != typeof(signal_node_val)){
				/**grid行列**/
				var signal_node_v = signal_node_val.split("@");
				if(null == data_value || "" == data_value){
					data_value = "['"+signal_node_v[0]+"','"+signal_node_v[1]+"','"+signal_node_v[2]+"',']";
				}else{
					data_value = data_value+","+"['"+signal_node_v[0]+"','"+signal_node_v[1]+"','"+signal_node_v[2]+"'',]";
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
	//类型数据源
	var signalTypeStore = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["global","global"],["processInstance","processInstance"]]
	});
	//列的类型
	var event_Plant = Ext.data.Record.create([
           {name:'ID',type:'String'},
           {name:'name',type:'string'},
           {name:'scope',type:'string'}
    ]);
	//定义数据源
	signal_store = Ext.create('Ext.data.Store',{  
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
	signal_grid = Ext.create('Ext.grid.Panel',{
			store:signal_store,
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
				},
				{
					header:'范围',
					dataIndex:'scope',
					flex:1,
	                editor:{
	                     	xtype:'combo',
		                    name:"name",
				            hiddenName:'name',
				            valueField:"value",
				            displayField:"name",
				            xtype:"combo",
				            anchor:'100%',
				            store:signalTypeStore,
				            emptyText:"请选择",
				            mode:"local",
				            triggerAction:"all",
				            editable:false
	                }
				}
			],
			listeners:{  
			    selectionChange:'selectionChange'  
			},
			//选中的记录发生变化过后的事件  
			selectionChange:function(view, records){  
				signal_grid.down('#node_signal_del_button').setDisabled(!records.length);
			},
			tbar:[{
				  text:'增 加',
				  minWidth:tbarBtnMinWidth,
				  cls:'addBtn',
				  icon:addIcon,
				  handler:function(){
		            var model = Ext.create(signal_grid.getStore().model);  
				    /**model.set('','');**/  
				    signal_grid.getStore().insert(0, model);  
				  }
				 },{
				  text:'删 除',
				  minWidth:tbarBtnMinWidth,
				  cls:'delBtn',
				  icon:delIcon,
				  id:'node_signal_del_button',
				  handler:function(){
					 var model = signal_grid.getSelectionModel();
					 if(model.selected.length == 0){
						msgTishi("请选择要删除的项");
						return;
					 }
				     Ext.MessageBox.confirm('确定删除', '确定要删除所选项吗？', function(btn) {  
				       if(btn == 'yes'){  
				           signal_grid.getStore().remove(signal_grid.getSelectionModel().getSelection());  
				           signal_grid.getStore().sync();  
				       }  
				     })
				  }
				 }]　　　　　
	}); 
	signal_store.load(); 
}

//点击确定按钮设置mxgraph中cell属性
var signal_node_value;
function signal_setvalue(cell){
	//验证操作
	for(var i=0;i<signal_grid.getStore().getCount();i++){
		var ID = signal_grid.store.getAt(i).data.ID;
		var name = signal_grid.store.getAt(i).data.name;
		var scope = signal_grid.store.getAt(i).data.scope;
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
		if("undefined" == typeof(type) || null == scope || scope == ""){
			signal_grid.store.getAt(i).data.scope="";
			signal_node_value="";
			msgTishi('请选择范围!');
			return false;	
		}
	}
	//赋值操作
	for(var i=0;i<signal_grid.getStore().getCount();i++){
		var ID = signal_grid.store.getAt(i).data.ID;
		var name = signal_grid.store.getAt(i).data.name;
		var type = signal_grid.store.getAt(i).data.scope;
		if(null != signal_node_value && "" != signal_node_value){
			signal_node_value = signal_node_value+"#"+ID+"@"+name+"@"+scope;
		}else{
			signal_node_value = ID+"@"+name+"@"+scope;
		}
	}
	//赋值
	if(null != signal_node_value && "" != signal_node_value){
		Ext.getCmp('signal_node_value').setValue(signal_node_value);
		signal_node_value="";
	}
}