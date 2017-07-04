//错误边界节点
var errorBoundaryEventNodeAttributeWin;
var errorBoundaryEventNodeAttributeTabPanel;
var errorBoundaryEventNodeAttributeForm;
function showErrorBoundaryEventNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		errorBoundaryEventNodeAttributePanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),errorBoundaryEventNodeAttributeTabPanel);
		basePanel.setHidden(true);
	}else{
		errorBoundaryEventNodeAttributePanel(cell,graph_refresh);
		errorBoundaryEventNodeAttributeWin = new Ext.Window({  
	         title:'配置错误边界属性',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:errorBoundaryEventNodeAttributeTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
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
					 	var errorcode = Ext.getCmp('errorcode').getValue();
					 	if(null != errorcode && '' !== errorcode){
					 		cell.errorcode = errorcode;
					 	}
						graph.startEditing();
						errorBoundaryEventNodeAttributeWin.close(this); 
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
	            errorBoundaryEventNodeAttributeWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     errorBoundaryEventNodeAttributeWin.show(); 
	}
}

function editErrorBoundaryEventNodeAttributeForm(cell){
	/**
	  *基本信息
	**/
	errorBoundaryEventNodeAttributeForm = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
         	xtype:'textfield',
            fieldLabel:'错误编码',  
           	name:'errorcode',
           	id:'errorcode',  
           	anchor:'100%'
         }]
    });
	var attachedToRef = cell.attachedToRef;
    var errorcode = cell.errorcode;
    Ext.getCmp('errorcode').setValue(errorcode);
}
function errorBoundaryEventNodeAttributePanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editErrorBoundaryEventNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    errorBoundaryEventNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:errorBoundaryEventNodeAttributeForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
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
						 	var errorcode = Ext.getCmp('errorcode').getValue();
						 	if(null != errorcode && '' !== errorcode){
						 		cell.errorcode = errorcode;
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
		editErrorBoundaryEventNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
	    errorBoundaryEventNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:errorBoundaryEventNodeAttributeForm},
	            {title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}