//消息边界
var messageBoundaryEventWin;
var messageBoundaryEventTabPanel;
var messageBoundaryEventForm;
function messageBoundaryEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		messageBoundaryEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),messageBoundaryEventTabPanel);
		basePanel.setHidden(true);
	}else{
		messageBoundaryEventPanel(cell,graph_refresh);
		messageBoundaryEventWin = new Ext.Window({  
	         title:'消息边界',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:messageBoundaryEventTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
				 	var cancelActivity = Ext.getCmp('cancelActivity').getValue();
				 	var messageRef = Ext.getCmp('messageRef').getValue();
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
						if(null != cancelActivity && "" != cancelActivity){
							cell.cancelActivity = cancelActivity;
						}
						if(null != messageRef && "" != messageRef){
							cell.messageRef = messageRef;
						}
						graph.startEditing();
						messageBoundaryEventWin.close(this); 
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
	            messageBoundaryEventWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     messageBoundaryEventWin.show(); 
	}
}

function editMessageBoundaryEventForm(cell){
	//定义列
	var messageBoundaryEventStore = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["false","false"],["true","true"]]
	});
	/**
	  *基本信息
	**/
	messageBoundaryEventForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
        	fieldLabel:'结束活动',  
         	xtype:'combo', 
         	store:messageBoundaryEventStore,
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
            fieldLabel:'消息依附',  
           	name:'messageRef',
           	id:'messageRef',  
           	anchor:'100%'
         }]
    });
 	var cancelActivity = cell.cancelActivity;
 	var messageRef = cell.messageRef;
 	Ext.getCmp('cancelActivity').setValue(cancelActivity);
 	Ext.getCmp('messageRef').setValue(messageRef);
}


function messageBoundaryEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editMessageBoundaryEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    messageBoundaryEventTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:messageBoundaryEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
					 	var cancelActivity = Ext.getCmp('cancelActivity').getValue();
					 	var messageRef = Ext.getCmp('messageRef').getValue();
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
							if(null != cancelActivity && "" != cancelActivity){
								cell.cancelActivity = cancelActivity;
							}
							if(null != messageRef && "" != messageRef){
								cell.messageRef = messageRef;
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
		editMessageBoundaryEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    messageBoundaryEventTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:messageBoundaryEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}