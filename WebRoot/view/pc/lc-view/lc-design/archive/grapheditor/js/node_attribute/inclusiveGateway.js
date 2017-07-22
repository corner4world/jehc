//包括网关
var inclusiveGatewayWin;
var inclusiveGatewayTabPanel;
var inclusiveGatewayForm;
function inclusiveGatewayWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		inclusiveGatewayPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),inclusiveGatewayTabPanel);
		basePanel.setHidden(true);
	}else{
		inclusiveGatewayPanel(cell,graph_refresh);
		inclusiveGatewayWin = Ext.create('Ext.Window',{
	         title:'包括网关',  
	         width:clientWidth, 
	         height:clientHeight,
	         autoHeight:true,
	         resizable:true,  
	         modal:true,  
	         closable:false,    
	         layout:'fit',
	         items:inclusiveGatewayTabPanel,
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
						inclusiveGatewayWin.close(this); 
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
	            inclusiveGatewayWin.close(this);  
	          }  
	        }  
	      ]  
	     });  
	     inclusiveGatewayWin.show(); 
	}
}

function inclusiveGatewayPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    inclusiveGatewayTabPanel = Ext.create('Ext.TabPanel',{
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	            {title:'一般配置',items:nodeNormalForm,
	            	autoScroll:true,
	                /**新方法使用开始**/  
	                scrollable:true,  
	                scrollable:'x',
	                scrollable:'y',
	                /**新方法使用结束**/ 	
	            },
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
	    inclusiveGatewayTabPanel = Ext.create('Ext.TabPanel',{
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	            {title:'一般配置',items:nodeNormalForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}