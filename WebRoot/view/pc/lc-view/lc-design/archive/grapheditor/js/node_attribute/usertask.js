//任务节点[task]
var TaskNodeAttributeWin;
var TaskNodeAttributeTabPanel;
//基本配置
var TaskNodeAttributeForm;
//配置表单
var TaskNodeAttributeFormSenior;
//表单配置信息
var TaskNodeAttributeFormPz;
function userTaskNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	taskNodeAttributePanel(cell);
	TaskNodeAttributeWin = new Ext.Window({  
         title:'配置人工任务属性',  
         width:clientWidth*0.9, 
         height:clientHeight*0.95,
         resizable:true, 
         modal:true,  
         border:false,
         closable:false, 
         items:TaskNodeAttributeTabPanel,
         buttons:[{  
         	text:'确 定',  
          	handler:function(){ 
          		/**
          		  *assignee处理人
          		  *candidateUsers候选人ID
          		  *candidateGroups候选群组ID
          		  *
          		**/
          		var assignee = Ext.getCmp('assignee').getValue();
          		var candidateUsers = Ext.getCmp('candidateUsers').getValue();
          		var candidateGroups = Ext.getCmp('candidateGroups').getValue(); 
			 	/**
			 	*表单配置
			 	**/
			 	var formKey = Ext.getCmp('formKey').getValue();
			 	var dueDate = Ext.getCmp('dueDate').getValue();
			 	var priority = Ext.getCmp('priority').getValue();
			 	var Expression = Ext.getCmp('Expression').getValue();
			 	var isUseExpression = Ext.getCmp('isUseExpression');
			 	var category = Ext.getCmp('category').getValue();
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
					//3配置用户
					if(null != assignee && "" != assignee){
						cell.assignee = assignee;
					}
					if(null != candidateUsers && "" != candidateUsers){
						cell.candidateUsers = candidateUsers;
					}
	          	 	if(null != candidateGroups && "" != candidateGroups){
	          	 		cell.candidateGroups = candidateGroups;
	          	 	}
	          	 	//4配置表单
				 	if(null != formKey && "" != formKey){
						cell.formKey = formKey;
					}
					if(null != dueDate && '' != dueDate){
				 		cell.dueDate = dueDate;
				 	}
				 	if(null != priority && '' != priority){
				 		cell.priority = priority;
				 	}
				 	if(null != Expression && '' != Expression){
				 		cell.Expression = Expression;
				 	}
				 	if(isUseExpression.checked == true){
				 		cell.isUseExpression = isUseExpression.getValue();
				 	}else{
				 		cell.isUseExpression = "2"
				 	}
				 	if(null != category && '' != category){
				 		cell.category = category;
				 	}
				 	//5配置会签
				 	multi_instance_setvalue(cell);
				 	//6配置表单事件
				 	if(event_form_setvalue(cell) == false){
				 		return;
				 	}
					graph.startEditing();
					TaskNodeAttributeWin.close(this); 
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
            TaskNodeAttributeWin.close();  
          }  
        }  
      ]  
     });  
     TaskNodeAttributeWin.show(); 
}

function editTaskNodeAttributeForm(cell){
	/**
	  *配置用户
	**/
	TaskNodeAttributeForm = new Ext.FormPanel({
        frame:false,
        labelWidth:68,
        labelAlign:'right',
        border:false,
        items:[{
         	xtype:'textfield',  
            fieldLabel:'处&nbsp;理&nbsp;&nbsp;人',  
           	name:'assignee',
           	hidden:true,
           	id:'assignee',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'处&nbsp;理&nbsp;&nbsp;人',  
           	id:'assignee_text',  
           	trigger1Cls:'x-form-cl-trigger',
           	trigger2Cls:'x-form-userselect-trigger',
			onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this);Ext.getCmp('assignee').setValue('');},
			onTrigger2Click:function(){initassignee(1,1)},
           	anchor:'40%'
         },{
         	xtype:'textfield',  
            fieldLabel:'候&nbsp;选&nbsp;&nbsp;人',  
           	name:'candidateUsers', 
           	hidden:true,
           	trigger2Cls:'x-form-userselect-trigger',
           	id:'candidateUsers', 
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'候&nbsp;选&nbsp;&nbsp;人',  
           	id:'candidateUsers_Text', 
           	trigger1Cls:'x-form-cl-trigger',
           	trigger2Cls:'x-form-usergroupselect-trigger',
			onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this)},
			onTrigger2Click:function(){initassignee(2,1)},
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'候选群组',  
           	name:'candidateGroups',  
           	hidden:true,
           	id:'candidateGroups',
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'候选群组',  
           	id:'candidateGroups_Text',
           	trigger1Cls:'x-form-cl-trigger',
           	trigger2Cls:'x-form-userorgselect-trigger',
			onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this);Ext.getCmp('candidateGroups').setValue('')},
			onTrigger2Click:function(){initcandidateGroups(2,1)},
           	anchor:'100%'
         }]
    });
 	var assignee = cell.assignee;
 	var candidateUsers = cell.candidateUsers;
 	var candidateGroups = cell.candidateGroups;
	Ext.getCmp('assignee').setValue(assignee);
	Ext.getCmp('candidateUsers').setValue(candidateUsers);
	Ext.getCmp('candidateGroups').setValue(candidateGroups); 
	initACC(assignee,candidateUsers,candidateGroups,1);
	/**
	  *配置表单
	**/
	TaskNodeAttributeFormSenior = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        frame:false,
        border:false,
        items:[{
       	    xtype:'textfield',  
            fieldLabel:'表&nbsp;单&nbsp;&nbsp;键',  
           	name:'formKey', 
           	id:'formKey', 
           	anchor:'100%'
         },{
       	    xtype:'datetimefield',
			format:'Y-m-d H:i:s',
            fieldLabel:'截止日期',  
           	name:'dueDate', 
           	id:'dueDate', 
           	anchor:'40%'
         },{
       	    xtype:'textfield',  
            fieldLabel:'优&nbsp;先&nbsp;&nbsp;级',  
           	name:'priority', 
           	id:'priority', 
           	anchor:'40%'
         },{
       	    xtype:'checkbox',
			inputValue:"1",
            fieldLabel:'用表达式',  
           	name:'isUseExpression', 
           	id:'isUseExpression', 
           	anchor:'100%'
         },{
       	    xtype:'textfield',  
            fieldLabel:'表&nbsp;达&nbsp;&nbsp;式',  
           	name:'Expression', 
           	id:'Expression', 
           	anchor:'100%'
         },{
       	    xtype:'textfield',  
            fieldLabel:'分&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;类',  
           	name:'category', 
           	id:'category', 
           	anchor:'100%'
         }]
    });
    var formKey = cell.formKey;
 	var dueDate = cell.dueDate;
 	var priority = cell.priority;
 	var Expression = cell.Expression;
 	var isUseExpression = cell.isUseExpression;
 	var category = cell.category;
    Ext.getCmp('formKey').setValue(formKey);
	Ext.getCmp('dueDate').setValue(dueDate);
	Ext.getCmp('priority').setValue(priority);
	Ext.getCmp('Expression').setValue(Expression);
	if(isUseExpression == 1){
		Ext.getCmp('isUseExpression').setValue(true);
	}else{
		Ext.getCmp('isUseExpression').setValue(false);
	}
	Ext.getCmp('category').setValue(category);
	
}


function taskNodeAttributePanel(cell){
	reGetWidthAndHeight();
	//基本配置与高级配置
	editTaskNodeAttributeForm(cell);
	//共用taskGrid属性事件
	event_task_grid(cell,2);
	//表单配置信息
	initform_grid(cell);
	//一般属性 参数1表示非开始2其他
	initNodeNormalForm(cell,1);
	//会签配置
	initMultiInstance(cell);
    TaskNodeAttributeTabPanel = new Ext.TabPanel({
        border:false,
        activeTab:0,
        height:clientHeight*0.95,
        split:true,
        region:"center",
        tabPosition:'top',
        items:[
        	{title:'一般配置',items:nodeNormalForm},
            {title:'基本配置',items:TaskNodeAttributeFormSenior},
            {title:'表单配置',items:form_grid,layout:'border'},
            {title:'配置用户',items:TaskNodeAttributeForm},
            {title:'事件配置',items:event_grid,layout:'border'},
            {title:'会签配置',items:multiInstanceLoopCharacteristicForm}
        ]
    });
}



	