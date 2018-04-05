//事物流程
var transactionProcessWin;
var transactionProcessTabPanel;
function transactionProcessWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		transactionProcessPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),transactionProcessTabPanel);
		basePanel.setHidden(true);
	}else{
		transactionProcessPanel(cell,graph_refresh);
		transactionProcessWin = new Ext.Window({  
	         title:'事物流程',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:transactionProcessTabPanel,
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
					 	//3数据属性
					 	if(dataProperties_setvalue(cell) == false){
					 		return;
					 	}
					 	//4配置会签
					 	multi_instance_setvalue(cell);
						graph.startEditing();
						transactionProcessWin.close(this); 
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
	            transactionProcessWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     transactionProcessWin.show(); 
	}
}

function transactionProcessPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//数据属性配置
		initdataProperties_grid(cell);
		//会签配置
		initMultiInstance(cell);
	    transactionProcessTabPanel = new Ext.TabPanel({
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
	        	{title:'数据属性',items:dataProperties_grid},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm,
	            	autoScroll:true,
	                /**新方法使用开始**/  
	                scrollable:true,  
	                scrollable:'x',
	                scrollable:'y',
	                /**新方法使用结束**/ 	
	            }
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
						 	//3数据属性
						 	if(dataProperties_setvalue(cell) == false){
						 		return;
						 	}
						 	//4配置会签
						 	multi_instance_setvalue(cell);
							graph.startEditing();
						}
						finally
						{
							graph.getModel().endUpdate();
							graph_refresh.refresh();
						}
		          }  
		     }]  
	    });
	}else{
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//数据属性配置
		initdataProperties_grid(cell);
		//会签配置
		initMultiInstance(cell);
	    transactionProcessTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'数据属性',items:dataProperties_grid},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm}
	        ]
	    });
	}
}
