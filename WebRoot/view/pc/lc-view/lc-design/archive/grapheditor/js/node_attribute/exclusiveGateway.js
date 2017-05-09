//同步节点
var exclusiveGatewayNodeAttributeWin;
var exclusiveGatewayNodeAttributeTabPanel;
var exclusiveGatewayNodeAttributeForm;
function showExclusiveGatewayNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	exclusiveGatewayNodeAttributePanel(cell);
	exclusiveGatewayNodeAttributeWin = new Ext.Window({  
         title:'配置同步属性',  
         width:clientWidth*0.9, 
         height:clientHeight*0.95,
         resizable:true, 
         modal:true,  
         border:false,
         closable:false, 
         items:exclusiveGatewayNodeAttributeTabPanel,
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
					exclusiveGatewayNodeAttributeWin.close(this); 
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
            exclusiveGatewayNodeAttributeWin.close();  
          }  
        }  
      ]  
     });  
     exclusiveGatewayNodeAttributeWin.show(); 
}

function editExclusiveGatewayNodeAttributeForm(cell){
}


function exclusiveGatewayNodeAttributePanel(cell){
	reGetWidthAndHeight();
	//基本配置与高级配置
	editExclusiveGatewayNodeAttributeForm(cell);
	//共用taskGrid属性事件
	event_task_grid(cell,2);
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,1);
    exclusiveGatewayNodeAttributeTabPanel = new Ext.TabPanel({
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