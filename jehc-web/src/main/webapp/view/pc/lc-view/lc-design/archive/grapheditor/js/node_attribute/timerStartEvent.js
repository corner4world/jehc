//定时启动事件
var timerStartEventWin;
var timerStartEventTabPanel;
var timerStartEventForm;
function timerStartEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		timerStartEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),timerStartEventTabPanel);
		basePanel.setHidden(true);
	}else{
		timerStartEventPanel(cell,graph_refresh);
		timerStartEventWin = Ext.create('Ext.Window',{
	         title:'配置定时器启动事件',  
	         width:clientWidth, 
	         height:clientHeight,
	         autoHeight:true,
	         resizable:true,  
	         modal:true,  
	         closable:false,    
	         layout:'fit',
	         items:timerStartEventTabPanel,
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
					 	if(node_normal_setvalue(cell,1)== false){
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
						//4配置表单事件
					 	if(event_form_setvalue(cell) == false){
					 		return;
					 	}
						graph.startEditing();
						timerStartEventWin.close(this); 
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
	            timerStartEventWin.close(this);  
	          }  
	        }  
	      ]  
	     });  
	     timerStartEventWin.show(); 
	}
}

function editTimerStartEventForm(cell){
	timerStartEventForm = Ext.create('Ext.FormPanel',{
        xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:false,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
        items:[{
         	xtype:'textfield',  
            fieldLabel:'持续时间',  
           	name:'timeDuration',
           	id:'timeDuration',  
           	anchor:'100%'
         },
         {
         	xtype:'textfield',  
            fieldLabel:'时间日期',  
           	name:'timeDate',
           	id:'timeDate',  
           	anchor:'100%'
         },
         {
         	xtype:'textfield',  
            fieldLabel:'时间转换',  
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

function timerStartEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置
		editTimerStartEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//表单配置信息
		initform_grid(cell);
	    timerStartEventTabPanel = Ext.create('Ext.TabPanel',{
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
	            {title:'基本配置',items:timerStartEventForm,
	            	autoScroll:true,
	                /**新方法使用开始**/  
	                scrollable:true,  
	                scrollable:'x',
	                scrollable:'y',
	                /**新方法使用结束**/ 	
	            },
	            {title:'表单配置',items:form_grid,layout:'border'},
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
						 	if(node_normal_setvalue(cell,1)== false){
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
							//4配置表单事件
						 	if(event_form_setvalue(cell) == false){
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
		editTimerStartEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//表单配置信息
		initform_grid(cell);
	    timerStartEventTabPanel = Ext.create('Ext.TabPanel',{
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
	            {title:'基本配置',items:timerStartEventForm},
	            {title:'表单配置',items:form_grid,layout:'border'},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}