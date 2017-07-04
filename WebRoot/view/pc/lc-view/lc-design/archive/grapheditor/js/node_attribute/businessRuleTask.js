//业务规则
var businessRuleTaskNodeAttributeWin;
var businessRuleTaskNodeAttributeTabPanel;
var businessRuleTaskNodeAttributeForm;
var businessRuleTaskNodeAttributeFormMain;
function showBusinessRuleTaskNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		businessRuleTaskNodeAttributePanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),businessRuleTaskNodeAttributeTabPanel);
		basePanel.setHidden(true);
	}else{
		businessRuleTaskNodeAttributePanel(cell,graph_refresh);
		businessRuleTaskNodeAttributeWin = new Ext.Window({  
	         title:'配置业务规则属性',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:businessRuleTaskNodeAttributeTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
	          		var excluded = Ext.getCmp('excluded');
				 	var ruleName = Ext.getCmp('ruleName').getValue();
				 	var ruleVariablesInput = Ext.getCmp('ruleVariablesInput').getValue();
				 	var resultVariables = Ext.getCmp('resultVariables').getValue();
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
					 	if(null != excluded.checked == true){
					 		cell.excluded = excluded.getValue();
					 	}
					 	if(null != ruleName && '' != ruleName){
					 		cell.ruleName = ruleName;
					 	}
					 	if(null != ruleVariablesInput && '' != ruleVariablesInput){
					 		cell.ruleVariablesInput = ruleVariablesInput;
					 	}
					 	if(null != resultVariables && '' != resultVariables){
					 		cell.resultVariables = resultVariables;
					 	}
					 	//4配置会签
					 	multi_instance_setvalue(cell);
						graph.startEditing();
						businessRuleTaskNodeAttributeWin.close(this); 
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
	            businessRuleTaskNodeAttributeWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     businessRuleTaskNodeAttributeWin.show(); 
	}
}
function editBusinessRuleTaskNodeAttributeForm(cell){
    businessRuleTaskNodeAttributeFormMain = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
         	xtype:'checkbox',
			inputValue:"excluded", 
            fieldLabel:'是否规则',  
            inputValue:'1',
            value:'1',
           	name:'excluded',
           	id:'excluded',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'规则名称',  
           	name:'ruleName',
           	id:'ruleName',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'输入变量',  
           	name:'ruleVariablesInput',
           	id:'ruleVariablesInput',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'输出变量',  
           	name:'resultVariables',
           	id:'resultVariables',  
           	anchor:'100%'
         }]
    });
    /**取值**/
	var excluded = cell.excluded;
 	var ruleName = cell.ruleName;
 	var ruleVariablesInput = cell.ruleVariablesInput;
 	var resultVariables = cell.resultVariables;
 	/**赋值**/
 	if(null != excluded && '' != excluded){
 		Ext.getCmp('excluded').setValue(excluded,true);
 	}else{
 		Ext.getCmp('excluded').setValue(excluded,false);
 	}
    Ext.getCmp('ruleName').setValue(ruleName);
    Ext.getCmp('ruleVariablesInput').setValue(ruleVariablesInput);
    Ext.getCmp('resultVariables').setValue(resultVariables);
}


function businessRuleTaskNodeAttributePanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editBusinessRuleTaskNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//会签配置
		initMultiInstance(cell);
	    businessRuleTaskNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:businessRuleTaskNodeAttributeFormMain},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm}
	        ],
	        buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
		          		var excluded = Ext.getCmp('excluded');
					 	var ruleName = Ext.getCmp('ruleName').getValue();
					 	var ruleVariablesInput = Ext.getCmp('ruleVariablesInput').getValue();
					 	var resultVariables = Ext.getCmp('resultVariables').getValue();
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
						 	if(null != excluded.checked == true){
						 		cell.excluded = excluded.getValue();
						 	}
						 	if(null != ruleName && '' != ruleName){
						 		cell.ruleName = ruleName;
						 	}
						 	if(null != ruleVariablesInput && '' != ruleVariablesInput){
						 		cell.ruleVariablesInput = ruleVariablesInput;
						 	}
						 	if(null != resultVariables && '' != resultVariables){
						 		cell.resultVariables = resultVariables;
						 	}
						 	//4配置会签
						 	multi_instance_setvalue(cell);
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
		//基本配置与高级配置
		editBusinessRuleTaskNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//会签配置
		initMultiInstance(cell);
	    businessRuleTaskNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:businessRuleTaskNodeAttributeFormMain},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm}
	        ]
	    });
	}
}