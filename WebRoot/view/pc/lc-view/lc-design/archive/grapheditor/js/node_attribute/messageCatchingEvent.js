//消息捕捉事件
var messageCatchingEventWin;
var messageCatchingEventTabPanel;
var messageCatchingEventForm;
function messageCatchingEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		messageCatchingEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),messageCatchingEventTabPanel);
		basePanel.setHidden(true);
	}else{
		messageCatchingEventPanel(cell,graph_refresh);
		messageCatchingEventWin = new Ext.Window({  
	         title:'消息捕捉事件',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:messageCatchingEventTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
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
						if(null != messageRef && "" != messageRef){
							cell.messageRef = messageRef;
						}
						graph.startEditing();
						messageCatchingEventWin.close(this); 
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
	            messageCatchingEventWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     messageCatchingEventWin.show(); 
	}
}

function editMessageCatchingEventForm(cell){
	/**
	  *基本信息
	**/
	messageCatchingEventForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
         	xtype:'textfield',  
            fieldLabel:'消息依附',  
           	name:'messageRef',
           	id:'messageRef',  
           	anchor:'100%'
         }]
    });
 	var messageRef = cell.messageRef;
 	Ext.getCmp('messageRef').setValue(messageRef);
}


function messageCatchingEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editMessageCatchingEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    messageCatchingEventTabPanel = new Ext.TabPanel({
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
	        	{title:'基本配置',items:messageCatchingEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
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
		editMessageCatchingEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    messageCatchingEventTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:messageCatchingEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}