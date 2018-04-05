//信号边界
var signalBoundaryEventWin;
var signalBoundaryEventTabPanel;
var signalBoundaryEventForm;
function signalBoundaryEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		signalBoundaryEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),signalBoundaryEventTabPanel);
		basePanel.setHidden(true);
	}else{
		signalBoundaryEventPanel(cell,graph_refresh);
		signalBoundaryEventWin = new Ext.Window({  
	         title:'信号边界',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:signalBoundaryEventTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
				 	var cancelActivity = Ext.getCmp('cancelActivity').getValue();
				 	var signalRef = Ext.getCmp('signalRef').getValue();
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
						if(null != signalRef && "" != signalRef){
							cell.signalRef = signalRef;
						}
						graph.startEditing();
						signalBoundaryEventWin.close(this); 
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
	            signalBoundaryEventWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     signalBoundaryEventWin.show(); 
	}
}

function editSignalBoundaryEventForm(cell){
	//定义列
	var signalBoundaryEventStore = new Ext.data.SimpleStore({
	  fields:['value', 'name'],
      data:[["false","false"],["true","true"]]
	});
	/**
	  *基本信息
	**/
	signalBoundaryEventForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
        	fieldLabel:'结束活动',  
         	xtype:'combo', 
         	store:signalBoundaryEventStore,
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
            fieldLabel:'信号依附',  
           	name:'signalRef',
           	id:'signalRef',  
           	anchor:'100%'
         }]
    });
 	var cancelActivity = cell.cancelActivity;
 	var signalRef = cell.signalRef;
 	Ext.getCmp('cancelActivity').setValue(cancelActivity);
 	Ext.getCmp('signalRef').setValue(signalRef);
}


function signalBoundaryEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editSignalBoundaryEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    signalBoundaryEventTabPanel = new Ext.TabPanel({
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
	        	{title:'基本配置',items:signalBoundaryEventForm,
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
				 	var cancelActivity = Ext.getCmp('cancelActivity').getValue();
				 	var signalRef = Ext.getCmp('signalRef').getValue();
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
						if(null != signalRef && "" != signalRef){
							cell.signalRef = signalRef;
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
		editSignalBoundaryEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,1);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    signalBoundaryEventTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:signalBoundaryEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}