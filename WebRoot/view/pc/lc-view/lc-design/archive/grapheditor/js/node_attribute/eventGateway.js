//事件网关
var eventGatewayWin;
var eventGatewayTabPanel;
var eventGatewayForm;
function eventGatewayWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	eventGatewayPanel(cell);
	eventGatewayWin = Ext.create('Ext.Window',{
         title:'事件网关',  
         width:clientWidth, 
         height:clientHeight,
         autoHeight:true,
         resizable:true,  
         modal:true,  
         closable:false,    
         layout:'fit',
         items:eventGatewayTabPanel,
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
					graph.startEditing();
					eventGatewayWin.close(this); 
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
            eventGatewayWin.close(this);  
          }  
        }  
      ]  
     });  
     eventGatewayWin.show(); 
}

function eventGatewayPanel(cell){
	reGetWidthAndHeight();
	//共用taskGrid属性事件
	event_task_grid(cell,2);
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,1);
    eventGatewayTabPanel = Ext.create('Ext.TabPanel',{
        border:false,
        activeTab:0,
        height:clientHeight*0.95,
        split:true,
        region:"center",
        tabPosition:'left',
        items:[
            {title:'一般配置',items:nodeNormalForm},
            {title:'事件配置',items:event_grid,layout:'border'}
        ]
    });
}