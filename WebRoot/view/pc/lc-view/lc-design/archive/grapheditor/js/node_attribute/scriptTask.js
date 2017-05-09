//脚本任务
var scriptTaskNodeAttributeWin;
var scriptTaskNodeAttributeTabPanel;
var scriptTaskNodeAttributeForm;
function showScriptTaskNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	scriptTaskNodeAttributePanel(cell);
	scriptTaskNodeAttributeWin = new Ext.Window({  
         title:'配置脚本任务属性',  
         width:clientWidth*0.9, 
         height:clientHeight*0.95,
         resizable:true, 
         modal:true,  
         border:false,
         closable:false, 
         items:scriptTaskNodeAttributeTabPanel,
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
				 	//3基本配置
				 	var scriptLanguage = Ext.getCmp('scriptLanguage').getValue();
				 	var script = Ext.getCmp('script').getValue();
				 	if(null != scriptLanguage && '' != scriptLanguage){
				 		cell.scriptLanguage = scriptLanguage;
				 	}
				 	if(null != script && '' != script){
				 		cell.script = script;
				 	}
				 	//4配置会签
				 	multi_instance_setvalue(cell);
					graph.startEditing();
					scriptTaskNodeAttributeWin.close(this); 
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
            scriptTaskNodeAttributeWin.close();  
          }  
        }  
      ]  
     });  
     scriptTaskNodeAttributeWin.show(); 
}

function editScriptTaskNodeAttributeForm(cell){
	var scriptLanguage_store = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["javascript","javascript"],["groovy","groovy"]]
	});
	scriptTaskNodeAttributeForm  = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
         	xtype:'combo',  
            fieldLabel:'语言类型',  
		    hiddenName:'name',
		    valueField:"value",
		    displayField:"name",
		    store:scriptLanguage_store,
		    emptyText:"请选择",
		    mode:"local",
		    triggerAction:"all",
		    editable:false,
           	name:'scriptLanguage',
           	id:'scriptLanguage',  
           	anchor:'40%'
         },{
         	xtype:'textareafield',  
            fieldLabel:'脚本内容',  
           	name:'script',
           	id:'script',  
           	height:200,
           	anchor:'100%'
         }]
    });
    var scriptLanguage = cell.scriptLanguage;
 	var script = cell.script;
 	Ext.getCmp('scriptLanguage').setValue(scriptLanguage);
	Ext.getCmp('script').setValue(script);
}


function scriptTaskNodeAttributePanel(cell){
	reGetWidthAndHeight();
	//基本配置与高级配置
	editScriptTaskNodeAttributeForm(cell);
	//共用taskGrid属性事件
	event_task_grid(cell,2);
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,1);
	//会签配置
	initMultiInstance(cell);
    scriptTaskNodeAttributeTabPanel = new Ext.TabPanel({
        border:false,
        activeTab:0,
        height:clientHeight*0.95,
        split:true, 
        region:"center",
        tabPosition:'left',
        items:[
        	{title:'一般配置',items:nodeNormalForm},
        	{title:'基本配置',items:scriptTaskNodeAttributeForm},
            {title:'事件配置',items:event_grid,layout:'border'},
            {title:'会签配置',items:multiInstanceLoopCharacteristicForm}
        ]
    });
}