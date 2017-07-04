//泳道池
var poolWin;
var poolTabPanel;
var poolForm;
function poolWin_(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		poolPanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),poolTabPanel);
		basePanel.setHidden(true);
	}else{
		poolPanel(cell,graph_refresh);
		poolWin = new Ext.Window({  
	         title:'泳道池',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:poolTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
	          		var graph = new mxGraph();
	          		graph.getModel().beginUpdate();
					try
					{
						var processId_ = Ext.getCmp('processId_').getValue();
					 	var processName_ = Ext.getCmp('processName_').getValue();
					 	var poolnameSpace = Ext.getCmp('poolnameSpace').getValue();
					 	var candidateStarterUsers_ = Ext.getCmp('candidateStarterUsers_').getValue();
					 	var candidateStarterGroups_ = Ext.getCmp('candidateStarterGroups_').getValue();
					 	var candidateStarterUsers_Text_ = Ext.getCmp('candidateStarterUsers_Text_').getValue();
					 	var candidateStarterGroups_Text_ = Ext.getCmp('candidateStarterGroups_Text_').getValue();
						//1通用基本配置并具有赋值功能
					 	if(node_normal_setvalue(cell,2)== false){
					 		return;
					 	}
					 	//2事件配置
					 	if(event_setvalue(cell)== false){
					 		return;
					 	}
					 	//4配置流程
					 	if(null != processId_ && "" != processId_){
							cell.processId_ = processId_;
						}
						if(null != processName_ && '' != processName_){
					 		cell.processName_ = processName_;
					 	}
					 	if(null != poolnameSpace && '' != poolnameSpace){
					 		cell.poolnameSpace = poolnameSpace;
					 	}
					 	if(null != candidateStarterUsers_ && '' != candidateStarterUsers_){
					 		cell.candidateStarterUsers_ = candidateStarterUsers_;
					 	}
					 	if(null != candidateStarterGroups_ && '' != candidateStarterGroups_){
					 		cell.candidateStarterGroups_ = candidateStarterGroups_;
					 	}
					 	if(null != candidateStarterUsers_Text_ && '' != candidateStarterUsers_Text_){
					 		cell.candidateStarterUsers_Text_ = candidateStarterUsers_Text_;
					 	}
					 	if(null != candidateStarterGroups_Text_ && '' != candidateStarterGroups_Text_){
					 		cell.candidateStarterGroups_Text_ = candidateStarterGroups_Text_;
					 	}
						graph.startEditing();
						if(clickPOOLSureBtn(graph_refresh,cell) == true){
							//赋值给流程基本属性
							var processId_ = cell.processId_;
						 	var processName_ = cell.processName_;
						 	var poolnameSpace = cell.poolnameSpace;
						 	var candidateStarterUsers_ = cell.candidateStarterUsers_;
						 	var candidateStarterGroups_ = cell.candidateStarterGroups_;
						 	var candidateStarterUsers_Text_ = cell.candidateStarterUsers_Text_;
						 	var candidateStarterGroups_Text_ = cell.candidateStarterGroups_Text_;
							Ext.getCmp('processId').setValue(processId_);
							Ext.getCmp('processName').setValue(processName_);
							Ext.getCmp('mainNameSpace').setValue(poolnameSpace);
							Ext.getCmp('candidateStarterUsers').setValue(candidateStarterUsers_);
							Ext.getCmp('candidateStarterGroups').setValue(candidateStarterGroups_); 
							Ext.getCmp('candidateStarterUsers_Text').setValue(candidateStarterUsers_Text_);
							Ext.getCmp('candidateStarterGroups_Text').setValue(candidateStarterGroups_Text_); 
						}
						poolWin.close(this); 
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
	            poolWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     poolWin.show(); 
	}
}


function editPoolForm(cell){
	poolForm = new Ext.FormPanel({
        frame:false,
        labelWidth:68,
        labelAlign:'right',
        border:false,
        items:[
         {
         	xtype:'textfield',  
            fieldLabel:'流程编号',  
           	id:'processId_',  
           	name:'processId_',
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'流程名称',  
           	name:'processName_', 
           	id:'processName_', 
           	anchor:'100%'
         },{
            fieldLabel:'命名空间',  
           	id:'poolnameSpace', 
           	name:'poolnameSpace', 
           	xtype:'textfield',
			trigger1Cls:'x-form-cl-trigger',
			value:'http://www.activiti.org/test',
			onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this)},
           	anchor:'100%'
         },{
         	fieldLabel:'发&nbsp;起&nbsp;&nbsp;人',
			xtype:'textfield',
			hidden:true,
			id:'candidateStarterUsers_',
			name:'candidateStarterUsers_',
			maxLength:255,
			width:'80%'
         },{
         	fieldLabel:'发&nbsp;起&nbsp;&nbsp;人',
			xtype:'textfield',
			id:'candidateStarterUsers_Text_',
			name:'candidateStarterUsers_Text_',
			trigger1Cls:'x-form-cl-trigger',
			trigger2Cls:'x-form-userselect-trigger',
			onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this);Ext.getCmp('candidateStarterUsers_').setValue('')},
			onTrigger2Click:function(){initassignee(1,3)},
			width:'80%'
         },{
         	fieldLabel:'发起人组',
			xtype:'textfield',
			hidden:true,
			id:'candidateStarterGroups_',
			name:'candidateStarterGroups_',
			maxLength:255,
			width:'80%'
         },{
         	fieldLabel:'发起人组',
			xtype:'textfield',
			id:'candidateStarterGroups_Text_',
			name:'candidateStarterGroups_Text_',
			trigger1Cls:'x-form-cl-trigger',
			trigger2Cls:'x-form-userorgselect-trigger',
			onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this);Ext.getCmp('candidateStarterGroups_').setValue('')},
			onTrigger2Click:function(){initcandidateGroups(2,3)},
			width:'80%'
         }]
    });
 	var processId_ = cell.processId_;
 	var processName_ = cell.processName_;
 	var poolnameSpace = cell.poolnameSpace;
 	var candidateStarterUsers_ = cell.candidateStarterUsers_;
 	var candidateStarterGroups_ = cell.candidateStarterGroups_;
 	var candidateStarterUsers_Text_ = cell.candidateStarterUsers_Text_;
 	var candidateStarterGroups_Text_ = cell.candidateStarterGroups_Text_;
	Ext.getCmp('processId_').setValue(processId_);
	Ext.getCmp('processName_').setValue(processName_);
	if(null != poolnameSpace && '' != poolnameSpace){
		Ext.getCmp('poolnameSpace').setValue(poolnameSpace);
	}else{
		Ext.getCmp('poolnameSpace').setValue('http://www.activiti.org/test');
	}
	Ext.getCmp('candidateStarterUsers_').setValue(candidateStarterUsers_);
	Ext.getCmp('candidateStarterGroups_').setValue(candidateStarterGroups_); 
	Ext.getCmp('candidateStarterUsers_Text_').setValue(candidateStarterUsers_Text_);
	Ext.getCmp('candidateStarterGroups_Text_').setValue(candidateStarterGroups_Text_); 
	initACC(candidateStarterUsers_,null,candidateStarterGroups_,3);
}


function poolPanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		editPoolForm(cell);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
	    poolTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'流程配置',items:poolForm},
	        	{title:'事件配置',items:event_grid,layout:'border'}
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
		          		var graph = new mxGraph();
		          		graph.getModel().beginUpdate();
						try
						{
							var processId_ = Ext.getCmp('processId_').getValue();
						 	var processName_ = Ext.getCmp('processName_').getValue();
						 	var poolnameSpace = Ext.getCmp('poolnameSpace').getValue();
						 	var candidateStarterUsers_ = Ext.getCmp('candidateStarterUsers_').getValue();
						 	var candidateStarterGroups_ = Ext.getCmp('candidateStarterGroups_').getValue();
						 	var candidateStarterUsers_Text_ = Ext.getCmp('candidateStarterUsers_Text_').getValue();
						 	var candidateStarterGroups_Text_ = Ext.getCmp('candidateStarterGroups_Text_').getValue();
							//1通用基本配置并具有赋值功能
						 	if(node_normal_setvalue(cell,2)== false){
						 		return;
						 	}
						 	//2事件配置
						 	if(event_setvalue(cell)== false){
						 		return;
						 	}
						 	//4配置流程
						 	if(null != processId_ && "" != processId_){
								cell.processId_ = processId_;
							}
							if(null != processName_ && '' != processName_){
						 		cell.processName_ = processName_;
						 	}
						 	if(null != poolnameSpace && '' != poolnameSpace){
						 		cell.poolnameSpace = poolnameSpace;
						 	}
						 	if(null != candidateStarterUsers_ && '' != candidateStarterUsers_){
						 		cell.candidateStarterUsers_ = candidateStarterUsers_;
						 	}
						 	if(null != candidateStarterGroups_ && '' != candidateStarterGroups_){
						 		cell.candidateStarterGroups_ = candidateStarterGroups_;
						 	}
						 	if(null != candidateStarterUsers_Text_ && '' != candidateStarterUsers_Text_){
						 		cell.candidateStarterUsers_Text_ = candidateStarterUsers_Text_;
						 	}
						 	if(null != candidateStarterGroups_Text_ && '' != candidateStarterGroups_Text_){
						 		cell.candidateStarterGroups_Text_ = candidateStarterGroups_Text_;
						 	}
							graph.startEditing();
							if(clickPOOLSureBtn(graph_refresh,cell) == true){
								//赋值给流程基本属性
								var processId_ = cell.processId_;
							 	var processName_ = cell.processName_;
							 	var poolnameSpace = cell.poolnameSpace;
							 	var candidateStarterUsers_ = cell.candidateStarterUsers_;
							 	var candidateStarterGroups_ = cell.candidateStarterGroups_;
							 	var candidateStarterUsers_Text_ = cell.candidateStarterUsers_Text_;
							 	var candidateStarterGroups_Text_ = cell.candidateStarterGroups_Text_;
								Ext.getCmp('processId').setValue(processId_);
								Ext.getCmp('processName').setValue(processName_);
								Ext.getCmp('mainNameSpace').setValue(poolnameSpace);
								Ext.getCmp('candidateStarterUsers').setValue(candidateStarterUsers_);
								Ext.getCmp('candidateStarterGroups').setValue(candidateStarterGroups_); 
								Ext.getCmp('candidateStarterUsers_Text').setValue(candidateStarterUsers_Text_);
								Ext.getCmp('candidateStarterGroups_Text').setValue(candidateStarterGroups_Text_); 
							}
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
		            poolWin.close();  
		          }  
		        }  
		      ]
	    });
	}else{
		editPoolForm(cell);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,2);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
	    poolTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'流程配置',items:poolForm},
	        	{title:'事件配置',items:event_grid,layout:'border'}
	        ]
	    });
	}
}
