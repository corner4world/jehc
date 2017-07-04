//终止结束事件
var terminateEndEventWin;
var terminateEndEventTabPanel;
var terminateEndEventForm;
function terminateEndEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		terminateEndEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),terminateEndEventTabPanel);
		basePanel.setHidden(true);
	}else{
		terminateEndEventPanel(cell,graph_refresh);
		terminateEndEventWin = Ext.create('Ext.Window',{
	         title:'终止结束事件',  
	         width:clientWidth, 
	         height:clientHeight,
	         autoHeight:true,
	         resizable:true,  
	         modal:true,  
	         closable:false,    
	         layout:'fit',
	         items:terminateEndEventTabPanel,
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
						terminateEndEventWin.close(this); 
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
	            terminateEndEventWin.close(this);  
	          }  
	        }  
	      ]  
	     });  
	     terminateEndEventWin.show(); 
	}
}

function terminateEndEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    terminateEndEventTabPanel = Ext.create('Ext.TabPanel',{
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        region:"center",
	        tabPosition:'left',
	        items:[
	            {title:'一般配置',items:nodeNormalForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
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
						}
						finally
						{
							graph.getModel().endUpdate();
							graph_refresh.refresh();
						}
		          }  
		         } 
		      ]  
	    });
	}else{
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    terminateEndEventTabPanel = Ext.create('Ext.TabPanel',{
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
}