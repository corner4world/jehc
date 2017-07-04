//连线节点[transition]
var transitionNodeAttributeWin;
var transitionNodeAttributeTabPanel;
var transitionNodeAttributeForm;
function showTransitionNodeAttributeWin(cell,graph_refresh,flag){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		transitionNodeAttributePanel(cell,flag,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),transitionNodeAttributeTabPanel);
		basePanel.setHidden(true);
	}else{
		transitionNodeAttributePanel(cell,flag,graph_refresh);
		transitionNodeAttributeWin = new Ext.Window({  
	         title:'配置连线属性',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:transitionNodeAttributeTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
	          		var labelWidth = Ext.getCmp('labelWidth').getValue();
					var skipexpression = Ext.getCmp('skipexpression').getValue();
					var condition = Ext.getCmp('condition').getValue(); 
	          		var graph = new mxGraph();
	          		graph.getModel().beginUpdate();
					try
					{
						//1通用基本配置并具有赋值功能
					 	if(node_normal_setvalue(cell,2)== false){
					 		return;
					 	}
					 	if(flag != 2){
					 		//2事件配置
						 	if(event_setvalue(cell)== false){
						 		return;
						 	}
					 	}
					 	//3基本配置
					 	if(null != labelWidth && "" != labelWidth){
					 		cell.labelWidth = labelWidth;
					 	}
					 	if(null != skipexpression && "" != skipexpression){
					 		cell.skipexpression = skipexpression;
					 	}
					 	if(null != condition && "" != condition){
					 		cell.condition = condition;
					 	}
						graph.startEditing();
						transitionNodeAttributeWin.close(this); 
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
	            transitionNodeAttributeWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     transitionNodeAttributeWin.show(); 
	}
}

function editTransitionNodeAttributeForm(cell){
	transitionNodeAttributeForm  = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
         	xtype:'textfield',  
            fieldLabel:'标签宽度',  
           	name:'labelWidth',
           	id:'labelWidth',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'表&nbsp;&nbsp;达&nbsp;式',  
           	name:'skipexpression',
           	id:'skipexpression',  
           	anchor:'100%'
         },{
         	xtype:'textareafield',  
            fieldLabel:'情&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;况',  
           	name:'condition',
           	id:'condition',  
           	height:200,
           	anchor:'100%'
         }]
    });
    var labelWidth = cell.labelWidth;
 	var skipexpression = cell.skipexpression;
 	var condition = cell.condition;
	Ext.getCmp('labelWidth').setValue(labelWidth);
	Ext.getCmp('skipexpression').setValue(skipexpression);
	Ext.getCmp('condition').setValue(condition); 
}


function transitionNodeAttributePanel(cell,flag,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		if(flag == 2){
			//基本配置
			editTransitionNodeAttributeForm(cell);
			//一般属性 参数1表示非开始2其他
			initNodeNormalForm(cell,2);
		    transitionNodeAttributeTabPanel = new Ext.TabPanel({
		        border:false,
		        activeTab:0,
		        height:clientHeight*0.95,
		        split:true, 
		        region:"center",
		        tabPosition:'left',
		        items:[
		        	{title:'一般配置',items:nodeNormalForm},
		        	{title:'基本配置',items:transitionNodeAttributeForm}
		        ],
		        buttons:[{  
		          	text:'确 定',  
		           	handler:function(){ 
		           		var labelWidth = Ext.getCmp('labelWidth').getValue();
		 				var skipexpression = Ext.getCmp('skipexpression').getValue();
		 				var condition = Ext.getCmp('condition').getValue(); 
		           		var graph = new mxGraph();
		           		graph.getModel().beginUpdate();
		 				try
		 				{
		 					//1通用基本配置并具有赋值功能
		 				 	if(node_normal_setvalue(cell,2)== false){
		 				 		return;
		 				 	}
		 				 	if(flag != 2){
		 				 		//2事件配置
		 					 	if(event_setvalue(cell)== false){
		 					 		return;
		 					 	}
		 				 	}
		 				 	//3基本配置
		 				 	if(null != labelWidth && "" != labelWidth){
		 				 		cell.labelWidth = labelWidth;
		 				 	}
		 				 	if(null != skipexpression && "" != skipexpression){
		 				 		cell.skipexpression = skipexpression;
		 				 	}
		 				 	if(null != condition && "" != condition){
		 				 		cell.condition = condition;
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
			editTransitionNodeAttributeForm(cell);
			//共用taskGrid属性事件
			event_task_grid(cell,2);
			//一般属性 参数1表示非开始2其他
			initNodeNormalForm(cell,2);
		    transitionNodeAttributeTabPanel = new Ext.TabPanel({
		        border:false,
		        activeTab:0,
		        height:clientHeight*0.95,
		        split:true, 
		        region:"center",
		        tabPosition:'left',
		        items:[
		        	{title:'一般配置',items:nodeNormalForm},
		        	{title:'基本配置',items:transitionNodeAttributeForm},
		            {title:'事件配置',items:event_grid,layout:'border'}
		        ],
		        buttons:[{  
		          	text:'确 定',  
		           	handler:function(){ 
		           		var labelWidth = Ext.getCmp('labelWidth').getValue();
		 				var skipexpression = Ext.getCmp('skipexpression').getValue();
		 				var condition = Ext.getCmp('condition').getValue(); 
		           		var graph = new mxGraph();
		           		graph.getModel().beginUpdate();
		 				try
		 				{
		 					//1通用基本配置并具有赋值功能
		 				 	if(node_normal_setvalue(cell,2)== false){
		 				 		return;
		 				 	}
		 				 	if(flag != 2){
		 				 		//2事件配置
		 					 	if(event_setvalue(cell)== false){
		 					 		return;
		 					 	}
		 				 	}
		 				 	//3基本配置
		 				 	if(null != labelWidth && "" != labelWidth){
		 				 		cell.labelWidth = labelWidth;
		 				 	}
		 				 	if(null != skipexpression && "" != skipexpression){
		 				 		cell.skipexpression = skipexpression;
		 				 	}
		 				 	if(null != condition && "" != condition){
		 				 		cell.condition = condition;
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
		}
	}else{
		if(flag == 2){
			//基本配置
			editTransitionNodeAttributeForm(cell);
			//一般属性 参数1表示非开始2其他
			initNodeNormalForm(cell,2);
		    transitionNodeAttributeTabPanel = new Ext.TabPanel({
		        border:false,
		        activeTab:0,
		        height:clientHeight*0.95,
		        split:true, 
		        region:"center",
		        tabPosition:'left',
		        items:[
		        	{title:'一般配置',items:nodeNormalForm},
		        	{title:'基本配置',items:transitionNodeAttributeForm},
		        ]
		    });
		}else{
			//基本配置
			editTransitionNodeAttributeForm(cell);
			//共用taskGrid属性事件
			event_task_grid(cell,2);
			//一般属性 参数1表示非开始2其他
			initNodeNormalForm(cell,2);
		    transitionNodeAttributeTabPanel = new Ext.TabPanel({
		        border:false,
		        activeTab:0,
		        height:clientHeight*0.95,
		        split:true, 
		        region:"center",
		        tabPosition:'left',
		        items:[
		        	{title:'一般配置',items:nodeNormalForm},
		        	{title:'基本配置',items:transitionNodeAttributeForm},
		            {title:'事件配置',items:event_grid,layout:'border'}
		        ]
		    });
		}
	}
}