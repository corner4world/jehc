//错误启动事件
var errorStartEventWin;
var errorStartEventTabPanel;
var errorStartEventForm;
function errorStartEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	errorStartEventPanel(cell);
	errorStartEventWin = Ext.create('Ext.Window',{
         title:'错误启动事件',  
         width:clientWidth, 
         height:clientHeight,
         autoHeight:true,
         resizable:true,  
         modal:true,  
         closable:false,    
         layout:'fit',
         items:errorStartEventTabPanel,
         buttons:[{  
         	text:'确 定',  
          	handler:function(){ 
          		var errorRef = Ext.getCmp('errorRef').getValue(); 
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
					if(null != errorRef && "" != errorRef){
						cell.errorRef = errorRef;
					}
					graph.startEditing();
					errorStartEventWin.close(this); 
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
            errorStartEventWin.close(this);  
          }  
        }  
      ]  
     });  
     errorStartEventWin.show(); 
}

function editErrorStartEventForm(cell){
	errorStartEventForm = Ext.create('Ext.FormPanel',{
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
            fieldLabel:'错误依附',  
           	name:'errorRef',
           	id:'errorRef',  
           	anchor:'100%'
         }]
    });
 	var errorRef = cell.errorRef;
	Ext.getCmp('errorRef').setValue(errorRef);
}

function errorStartEventPanel(cell){
	reGetWidthAndHeight();
	//基本配置
	editErrorStartEventForm(cell);
	//共用taskGrid属性事件
	event_task_grid(cell,2);
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,1);
    errorStartEventTabPanel = Ext.create('Ext.TabPanel',{
        border:false,
        activeTab:0,
        height:clientHeight*0.95,
        split:true,
        region:"center",
        tabPosition:'left',
        items:[
            {title:'一般配置',items:nodeNormalForm},
            {title:'基本配置',items:errorStartEventForm},
            {title:'事件配置',items:event_grid,layout:'border'}
        ]
    });
}