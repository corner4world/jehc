//信号捕捉事件
var signalCatchingEventWin;
var signalCatchingEventTabPanel;
var signalCatchingEventForm;
function signalCatchingEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	signalCatchingEventPanel(cell);
	signalCatchingEventWin = new Ext.Window({  
         title:'信号捕捉事件',  
         width:clientWidth*0.9, 
         height:clientHeight*0.95,
         resizable:true, 
         modal:true,  
         border:false,
         closable:false, 
         items:signalCatchingEventTabPanel,
         buttons:[{  
         	text:'确 定',  
          	handler:function(){ 
			 	var signalRef = Ext.getCmp('signalRef').getValue();
          		var graph = new mxGraph();
          		graph.getModel().beginUpdate();
				try
				{
					//1通用基本配置并具有赋值功能
				 	if(node_normal_setvalue(cell,2)== false){
				 		return;
				 	}
				 	//2事件配置
				 	if(event_setvalue(cell)== false){
				 		return;
				 	}
				 	//3基本配置
					if(null != signalRef && "" != signalRef){
						cell.signalRef = signalRef;
					}
					graph.startEditing();
					signalCatchingEventWin.close(this); 
				}
				finally
				{
					graph.getModel().endUpdate();
					graph_refresh.refresh();
				}
          }  
         },{  
          text:'取 消',  
          handler:function(){  
            signalCatchingEventWin.close();  
          }  
        }  
      ]  
     });  
     signalCatchingEventWin.show(); 
}

function editSignalCatchingEventForm(cell){
	/**
	  *基本信息
	**/
	signalCatchingEventForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
         	xtype:'textfield',  
            fieldLabel:'信号依附',  
           	name:'signalRef',
           	id:'signalRef',  
           	anchor:'100%'
         }]
    });
 	var signalRef = cell.signalRef;
 	Ext.getCmp('signalRef').setValue(signalRef);
}


function signalCatchingEventPanel(cell){
	reGetWidthAndHeight();
	//基本配置与高级配置
	editSignalCatchingEventForm(cell);
	//共用taskGrid属性事件
	event_task_grid(cell,2);
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,2);
    signalCatchingEventTabPanel = new Ext.TabPanel({
        border:false,
        activeTab:0,
        height:clientHeight*0.95,
        split:true, 
        region:"center",
        tabPosition:'left',
        items:[
        	{title:'一般配置',items:nodeNormalForm},
        	{title:'基本配置',items:signalCatchingEventForm},
            {title:'事件配置',items:event_grid,layout:'border'}
        ]
    });
}