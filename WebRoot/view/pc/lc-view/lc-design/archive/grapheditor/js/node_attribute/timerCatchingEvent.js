//定时捕捉事件
var timerCatchingEventNodeAttributeWin;
var timerCatchingEventNodeAttributeTabPanel;
var timerCatchingEventNodeAttributeForm;
function timerCatchingEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		timerCatchingEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),timerCatchingEventTabPanel);
		basePanel.setHidden(true);
	}else{
		timerCatchingEventPanel(cell,graph_refresh);
		timerCatchingEventWin = new Ext.Window({  
	         title:'定时捕捉事件性',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:timerCatchingEventTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
				 	var timeDuration = Ext.getCmp('timeDuration').getValue();
				 	var timeDate = Ext.getCmp('timeDate').getValue();
				 	var timeCycle = Ext.getCmp('timeCycle').getValue();
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
						if(null != timeDuration && "" != timeDuration){
							cell.timeDuration = timeDuration;
						}
						if(null != timeDate && "" != timeDate){
							cell.timeDate = timeDate;
						}
						if(null != timeCycle && "" != timeCycle){
							cell.timeCycle = timeCycle;
						}
						graph.startEditing();
						timerCatchingEventWin.close(this); 
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
	            timerCatchingEventWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     timerCatchingEventWin.show(); 
	}
}

function editTimerCatchingEventForm(cell){
	/**
	  *基本信息
	**/
	timerCatchingEventForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
         	xtype:'textfield',  
            fieldLabel:'时&nbsp;&nbsp;间&nbsp;段',  
           	name:'timeDuration',
           	id:'timeDuration',  
           	anchor:'100%'
         },{
         	xtype:'textfield',
            fieldLabel:'时间格式',  
           	name:'timeDate',
           	id:'timeDate',  
           	anchor:'100%'
         },{
         	xtype:'textfield',
            fieldLabel:'时间周期',  
           	name:'timeCycle',
           	id:'timeCycle',  
           	anchor:'100%'
         }]
    });
 	var timeDuration = cell.timeDuration;
 	var timeDate = cell.timeDate;
 	var timeCycle = cell.timeCycle;
 	Ext.getCmp('timeDuration').setValue(timeDuration);
 	Ext.getCmp('timeDate').setValue(timeDate);
 	Ext.getCmp('timeCycle').setValue(timeCycle);
}


function timerCatchingEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editTimerCatchingEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    timerCatchingEventTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:timerCatchingEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
					 	var timeDuration = Ext.getCmp('timeDuration').getValue();
					 	var timeDate = Ext.getCmp('timeDate').getValue();
					 	var timeCycle = Ext.getCmp('timeCycle').getValue();
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
							if(null != timeDuration && "" != timeDuration){
								cell.timeDuration = timeDuration;
							}
							if(null != timeDate && "" != timeDate){
								cell.timeDate = timeDate;
							}
							if(null != timeCycle && "" != timeCycle){
								cell.timeCycle = timeCycle;
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
		//基本配置与高级配置
		editTimerCatchingEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    timerCatchingEventTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:timerCatchingEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}