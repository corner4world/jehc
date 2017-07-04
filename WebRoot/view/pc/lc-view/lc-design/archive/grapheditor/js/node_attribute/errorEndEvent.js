//错误结束事件
var errorEndEventWin;
var errorEndEventTabPanel;
var errorEndEventForm;
function errorEndEventWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		errorEndEventPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),errorEndEventTabPanel);
		basePanel.setHidden(true);
	}else{
		errorEndEventPanel(cell,graph_refresh);
		errorEndEventWin = Ext.create('Ext.Window',{
	         title:'错误结束事件',  
	         width:clientWidth, 
	         height:clientHeight,
	         autoHeight:true,
	         resizable:true,  
	         modal:true,  
	         closable:false,    
	         layout:'fit',
	         items:errorEndEventTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
	          		var errorRef = Ext.getCmp('errorRef').getValue(); 
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
						if(null != errorRef && "" != errorRef){
							cell.errorRef = errorRef;
						}
						graph.startEditing();
						errorEndEventWin.close(this); 
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
	            errorEndEventWin.close(this);  
	          }  
	        }  
	      ]  
	     });  
	     errorEndEventWin.show(); 
	}
}

function editErrorEndEventForm(cell){
	errorEndEventForm = Ext.create('Ext.FormPanel',{
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
            fieldLabel:'错误依附',  
           	name:'errorRef',
           	id:'errorRef',  
           	anchor:'100%'
         }]
    });
 	var errorRef = cell.errorRef;
	Ext.getCmp('errorRef').setValue(errorRef);
}

function errorEndEventPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置
		editErrorEndEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    errorEndEventTabPanel = Ext.create('Ext.TabPanel',{
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        region:"center",
	        tabPosition:'left',
	        items:[
	            {title:'一般配置',items:nodeNormalForm},
	            {title:'基本配置',items:errorEndEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
		          		var errorRef = Ext.getCmp('errorRef').getValue(); 
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
							if(null != errorRef && "" != errorRef){
								cell.errorRef = errorRef;
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
		editErrorEndEventForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
	    errorEndEventTabPanel = Ext.create('Ext.TabPanel',{
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true,
	        region:"center",
	        tabPosition:'left',
	        items:[
	            {title:'一般配置',items:nodeNormalForm},
	            {title:'基本配置',items:errorEndEventForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}