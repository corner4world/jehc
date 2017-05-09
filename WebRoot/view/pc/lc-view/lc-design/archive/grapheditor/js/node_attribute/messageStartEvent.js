//消息开始事件
var messageStartEventWin;
var messageStartEventTabPanel;
var messageStartEventForm;
function messageStartEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	messageStartEventPanel(cell);
	messageStartEventWin = Ext.create('Ext.Window',{
         title:'消息开始事件',  
         width:clientWidth, 
         height:clientHeight,
         autoHeight:true,
         resizable:true,  
         modal:true,  
         closable:false,    
         layout:'fit',
         items:messageStartEventTabPanel,
         buttons:[{  
         	text:'确 定',  
          	handler:function(){ 
          		var messageRef = Ext.getCmp('messageRef').getValue(); 
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
					if(null != messageRef && "" != messageRef){
						cell.messageRef = messageRef;
					}
					graph.startEditing();
					messageStartEventWin.close(this); 
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
            messageStartEventWin.close(this);  
          }  
        }  
      ]  
     });  
     messageStartEventWin.show(); 
}

function editMessageStartEventForm(cell){
	messageStartEventForm = Ext.create('Ext.FormPanel',{
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
            fieldLabel:'消息依附',  
           	name:'messageRef',
           	id:'messageRef',  
           	anchor:'100%'
         }]
    });
 	var messageRef = cell.messageRef;
	Ext.getCmp('messageRef').setValue(messageRef);
}

function messageStartEventPanel(cell){
	reGetWidthAndHeight();
	//基本配置
	editMessageStartEventForm(cell);
	//共用taskGrid属性事件
	event_task_grid(cell,2);
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,1);
    messageStartEventTabPanel = Ext.create('Ext.TabPanel',{
        border:false,
        activeTab:0,
        height:clientHeight*0.95,
        split:true,
        region:"center",
        tabPosition:'left',
        items:[
            {title:'一般配置',items:nodeNormalForm},
            {title:'基本配置',items:messageStartEventForm},
            {title:'事件配置',items:event_grid,layout:'border'}
        ]
    });
}