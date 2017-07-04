//时间边界节点
var timerBoundaryEventNodeAttributeWin;
var timerBoundaryEventNodeAttributeTabPanel;
var timerBoundaryEventNodeAttributeForm;
function showTimerBoundaryEventNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		timerBoundaryEventNodeAttributePanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),timerBoundaryEventNodeAttributeTabPanel);
		basePanel.setHidden(true);
	}else{
		timerBoundaryEventNodeAttributePanel(cell,graph_refresh);
		timerBoundaryEventNodeAttributeWin = new Ext.Window({  
	         title:'配置时间边界属性',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:timerBoundaryEventNodeAttributeTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
				 	//var attachedToRef = Ext.getCmp('attachedToRef').getValue();
				 	var cancelActivity = Ext.getCmp('cancelActivity').getValue();
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
					 	/**
						if(null != attachedToRef && "" != attachedToRef){
							cell.attachedToRef = attachedToRef;
						}
						**/
						if(null != timeDuration && "" != timeDuration){
							cell.timeDuration = timeDuration;
						}
						if(null != cancelActivity && "" != cancelActivity){
							cell.cancelActivity = cancelActivity;
						}
						if(null != timeDate && "" != timeDate){
							cell.timeDate = timeDate;
						}
						if(null != timeCycle && "" != timeCycle){
							cell.timeCycle = timeCycle;
						}
						graph.startEditing();
						timerBoundaryEventNodeAttributeWin.close(this); 
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
	            timerBoundaryEventNodeAttributeWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     timerBoundaryEventNodeAttributeWin.show(); 
	}
}

function editTimerBoundaryEventNodeAttributeForm(cell){
	//定义列
	var timerBoundaryEventStore = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["false","false"],["true","true"]]
	});
	/**
	  *基本信息
	**/
	timerBoundaryEventNodeAttributeForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
        	fieldLabel:'结束活动',  
         	xtype:'combo', 
         	store:timerBoundaryEventStore,
         	hiddenName:'name',
            valueField:"value",
            displayField:"name", 
            emptyText:"请选择",
            mode:"local",
            triggerAction:"all",
            editable:false,
           	name:'cancelActivity',
           	id:'cancelActivity',  
           	anchor:'40%'
         },{
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
	//var attachedToRef = cell.attachedToRef;
 	var cancelActivity = cell.cancelActivity;
 	var timeDuration = cell.timeDuration;
 	var timeDate = cell.timeDate;
 	var timeCycle = cell.timeCycle;
 	Ext.getCmp('cancelActivity').setValue(cancelActivity);
 	Ext.getCmp('timeDuration').setValue(timeDuration);
 	Ext.getCmp('timeDate').setValue(timeDate);
 	Ext.getCmp('timeCycle').setValue(timeCycle);
    //Ext.getCmp('attachedToRef').setValue(attachedToRef);
}


function timerBoundaryEventNodeAttributePanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editTimerBoundaryEventNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    timerBoundaryEventNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:timerBoundaryEventNodeAttributeForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
					 	//var attachedToRef = Ext.getCmp('attachedToRef').getValue();
					 	var cancelActivity = Ext.getCmp('cancelActivity').getValue();
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
						 	/**
							if(null != attachedToRef && "" != attachedToRef){
								cell.attachedToRef = attachedToRef;
							}
							**/
							if(null != timeDuration && "" != timeDuration){
								cell.timeDuration = timeDuration;
							}
							if(null != cancelActivity && "" != cancelActivity){
								cell.cancelActivity = cancelActivity;
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
		editTimerBoundaryEventNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    timerBoundaryEventNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:timerBoundaryEventNodeAttributeForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}