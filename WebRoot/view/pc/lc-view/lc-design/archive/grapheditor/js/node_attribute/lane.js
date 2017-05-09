//泳道
var laneWin;
var laneTabPanel;
function laneWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	lanePanel(cell);
	laneWin = new Ext.Window({  
         title:'泳道',  
         width:clientWidth*0.9, 
         height:clientHeight*0.95,
         resizable:true, 
         modal:true,  
         border:false,
         closable:false, 
         items:laneTabPanel,
         buttons:[{  
         	text:'确 定',  
          	handler:function(){ 
          		var graph = new mxGraph();
          		graph.getModel().beginUpdate();
				try
				{
					//1通用基本配置并具有赋值功能
				 	if(node_normal_setvalue(cell,2)== false){
				 		return;
				 	}
					graph.startEditing();
					laneWin.close(this); 
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
            laneWin.close();  
          }  
        }  
      ]  
     });  
     laneWin.show(); 
}

function lanePanel(cell){
	reGetWidthAndHeight();
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,2);
    laneTabPanel = new Ext.TabPanel({
        border:false,
        activeTab:0,
        height:clientHeight*0.95,
        split:true, 
        region:"center",
        tabPosition:'top',
        items:[
        	{title:'一般配置',items:nodeNormalForm}
        ]
    });
}
