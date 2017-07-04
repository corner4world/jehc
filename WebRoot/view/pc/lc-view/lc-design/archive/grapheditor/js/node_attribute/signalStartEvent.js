//信号启动事件
var signalStartEventWin;
var signalStartEventTabPanel;
var signalStartEventForm;
function signalStartEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		signalStartEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),signalStartEventTabPanel);
		basePanel.setHidden(true);
	}else{
		signalStartEventPanel(cell,graph_refresh);
		signalStartEventWin = Ext.create('Ext.Window',{
	         title:'信号启动事件',  
	         width:clientWidth, 
	         height:clientHeight,
	         autoHeight:true,
	         resizable:true,  
	         modal:true,  
	         closable:false,    
	         layout:'fit',
	         items:signalStartEventTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
	          		var signalRef = Ext.getCmp('signalRef').getValue(); 
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
						if(null != signalRef && "" != signalRef){
							cell.signalRef = signalRef;
						}
						graph.startEditing();
						signalStartEventWin.close(this); 
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
	            signalStartEventWin.close(this);  
	          }  
	        }  
	      ]  
	     });  
	     signalStartEventWin.show(); 
	}
}

function editSignalStartEventForm(cell){
	signalStartEventForm = Ext.create('Ext.FormPanel',{
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
            fieldLabel:'信号依附',  
           	name:'signalRef',
           	id:'signalRef',  
           	anchor:'100%'
         }]
    });
 	var signalRef = cell.signalRef;
	Ext.getCmp('signalRef').setValue(signalRef);
}

function signalStartEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置
		editSignalStartEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    signalStartEventTabPanel = Ext.create('Ext.TabPanel',{
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        region:"center",
	        tabPosition:'left',
	        items:[
	            {title:'一般配置',items:nodeNormalForm},
	            {title:'基本配置',items:signalStartEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
		          		var signalRef = Ext.getCmp('signalRef').getValue(); 
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
		//基本配置
		editSignalStartEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    signalStartEventTabPanel = Ext.create('Ext.TabPanel',{
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        region:"center",
	        tabPosition:'left',
	        items:[
	            {title:'一般配置',items:nodeNormalForm},
	            {title:'基本配置',items:signalStartEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}