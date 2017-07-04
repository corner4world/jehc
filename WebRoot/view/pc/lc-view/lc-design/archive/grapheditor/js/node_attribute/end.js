//结束节点[end]
/**
 基本配置:名称 结束方式 结束状态 备注
 事件配置:事件类型(开始,结束) 类名
**/
var EndNodeAttributeWin;
var EndNodeAttributeTabPanel;
var EndNodeAttributeForm;

function endNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		endNodeAttributePanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),EndNodeAttributeTabPanel);
		basePanel.setHidden(true);
	}else{
		endNodeAttributePanel(cell,graph_refresh);
		EndNodeAttributeWin = new Ext.Window({  
	         title:'配置结束节点属性',  
	         buttonAlign:'right', 
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         autoHeight:true,
	         resizable:true,  
	         modal:true,  
	         closable:false,  
	         buttonAlign:'center',
	         items:EndNodeAttributeTabPanel,
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
						graph.startEditing();
						EndNodeAttributeWin.close(this); 
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
	            EndNodeAttributeWin.close(this);  
	          }  
	        }  
	      ]  
	     });  
	     EndNodeAttributeWin.show(); 
	}
}

function editEndNodeAttributeForm(cell){
}

function endNodeAttributePanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置
		editEndNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    EndNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        tabPosition:'left',
	        region:"center",
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
		//基本配置
		editEndNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    EndNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        tabPosition:'left',
	        region:"center",
	        items:[
	            {title:'一般配置',items:nodeNormalForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}