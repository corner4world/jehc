//开始节点[start]到目前为止只能有一个开始节点
/**
 基本配置:名称 表单 备注
 事件配置:事件类型(开始,结束) 类名
**/
var StartNodeAttributeWin;
var StartNodeAttributeTabPanel;
var StartNodeAttributeForm;

function startNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	startNodeAttributePanel(cell);
	StartNodeAttributeWin = Ext.create('Ext.Window',{
         title:'配置开始节点属性',  
         width:clientWidth, 
         height:clientHeight,
         autoHeight:true,
         resizable:true,  
         modal:true,  
         closable:false,    
         layout:'fit',
         items:StartNodeAttributeTabPanel,
         buttons:[{  
         	text:'确 定',  
          	handler:function(){ 
          		var initiator = Ext.getCmp('initiator').getValue();
          		var formKey = Ext.getCmp('formKey').getValue();
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
					if(null != initiator && "" != initiator){
						cell.initiator = initiator;
					}
					if(null != formKey && "" != formKey){
						cell.formKey = formKey;
					}
					//4配置表单事件
				 	if(event_form_setvalue(cell) == false){
				 		return;
				 	}
					graph.startEditing();
					StartNodeAttributeWin.close(this); 
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
            StartNodeAttributeWin.close(this);  
          }  
        }  
      ]  
     });  
     StartNodeAttributeWin.show(); 
}

function editStartNodeAttributeForm(cell){
	StartNodeAttributeForm = Ext.create('Ext.FormPanel',{
        xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:false,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
        items:[{
         	xtype:'textfield',  
            fieldLabel:'发&nbsp;&nbsp;起&nbsp;人',  
           	name:'initiator',
           	id:'initiator',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'表&nbsp;&nbsp;单&nbsp;键',  
           	name:'formKey',
           	id:'formKey',  
           	anchor:'100%'
         }]
    });
 	var initiator = cell.initiator;
 	var formKey = cell.formKey;
	Ext.getCmp('initiator').setValue(initiator);
	Ext.getCmp('formKey').setValue(formKey);
}

function startNodeAttributePanel(cell){
	reGetWidthAndHeight();
	//基本配置
	editStartNodeAttributeForm(cell);
	//共用taskGrid属性事件
	event_task_grid(cell,2);
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,1);
	//表单配置信息
	initform_grid(cell);
    StartNodeAttributeTabPanel = Ext.create('Ext.TabPanel',{
        border:false,
        activeTab:0,
        height:clientHeight*0.95,
        split:true,
        region:"center",
        tabPosition:'left',
        items:[
            {title:'一般配置',items:nodeNormalForm},
            {title:'基本配置',items:StartNodeAttributeForm},
            {title:'表单配置',items:form_grid,layout:'border'},
            {title:'事件配置',items:event_grid,layout:'border'}
        ]
    });
}