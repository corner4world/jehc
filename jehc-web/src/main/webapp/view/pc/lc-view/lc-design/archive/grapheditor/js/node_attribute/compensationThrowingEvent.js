//补偿抛出事件
var compensationThrowingEventWin;
var compensationThrowingEventTabPanel;
var compensationThrowingEventForm;
function compensationThrowingEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		compensationThrowingEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),compensationThrowingEventTabPanel);
		basePanel.setHidden(true);
	}else{
		compensationThrowingEventPanel(cell,graph_refresh);
		compensationThrowingEventWin = Ext.create('Ext.Window',{
	         title:'补偿抛出事件',  
	         width:clientWidth, 
	         height:clientHeight,
	         autoHeight:true,
	         resizable:true,  
	         modal:true,  
	         closable:false,    
	         layout:'fit',
	         items:compensationThrowingEventTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
	          		var graph = new mxGraph();
	          		graph.getModel().beginUpdate();
	          		var activityRef = Ext.getCmp('activityRef').getValue();
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
						if(null != activityRef && "" != activityRef){
							cell.activityRef = activityRef;
						}
						graph.startEditing();
						compensationThrowingEventWin.close(this); 
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
	            compensationThrowingEventWin.close(this);  
	          }  
	        }  
	      ]  
	     });  
	     compensationThrowingEventWin.show(); 
	}
}

function editCompensationThrowingEventForm(cell){
	/**
	  *基本信息
	**/
	compensationThrowingEventForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
         	xtype:'textfield',  
            fieldLabel:'活动依附',  
           	name:'activityRef',
           	id:'activityRef',  
           	anchor:'100%'
         }]
    });
 	var activityRef = cell.activityRef;
 	Ext.getCmp('activityRef').setValue(activityRef);
}
function compensationThrowingEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
		editCompensationThrowingEventForm(cell);
	    compensationThrowingEventTabPanel = Ext.create('Ext.TabPanel',{
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
	            {title:'基本配置',items:compensationThrowingEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
		          		var graph = new mxGraph();
		          		graph.getModel().beginUpdate();
		          		var activityRef = Ext.getCmp('activityRef').getValue();
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
							if(null != activityRef && "" != activityRef){
								cell.activityRef = activityRef;
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
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
		editCompensationThrowingEventForm(cell);
	    compensationThrowingEventTabPanel = Ext.create('Ext.TabPanel',{
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	            {title:'一般配置',items:nodeNormalForm},
	            {title:'基本配置',items:compensationThrowingEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}