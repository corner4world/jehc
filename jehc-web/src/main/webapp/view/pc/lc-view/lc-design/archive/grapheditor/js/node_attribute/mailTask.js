//邮件任务节点
var mailTaskNodeAttributeWin;
var mailTaskNodeAttributeTabPanel;
var mailTaskNodeAttributeForm;
var mailTaskNodeAttributeFormMain;
function showMailTaskNodeAttributeWin(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		var eItems =eastPanel.items;
		for(var le = 0; le < eItems.length; le++){
			 if(le > 0){
				 eastPanel.remove(eItems.get(le),true);
			 }
		}
		mailTaskNodeAttributePanel(cell,graph_refresh);
		//放置eastPanel位置
		eastPanel.add(eastPanel.items.getCount(),mailTaskNodeAttributeTabPanel);
		basePanel.setHidden(true);
	}else{
		mailTaskNodeAttributePanel(cell,graph_refresh);
		mailTaskNodeAttributeWin = new Ext.Window({  
	         title:'配置邮件任务属性',  
	         width:clientWidth*0.9, 
	         height:clientHeight*0.95,
	         resizable:true, 
	         modal:true,  
	         border:false,
	         closable:false, 
	         items:mailTaskNodeAttributeTabPanel,
	         buttons:[{  
	         	text:'确 定',  
	          	handler:function(){ 
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
					 	var to = Ext.getCmp('to').getValue();
					 	var from = Ext.getCmp('from').getValue();
					 	var subject = Ext.getCmp('subject').getValue();
					 	var cc = Ext.getCmp('cc').getValue();
					 	var bcc = Ext.getCmp('bcc').getValue();
					 	var charset = Ext.getCmp('charset').getValue();
					 	var html = Ext.getCmp('html').getValue();
					 	var nohtml = Ext.getCmp('nohtml').getValue();
					 	if(null != to && '' != to){
					 		cell.to = to;
					 	}
					 	if(null != from && '' != from){
					 		cell.from = from;
					 	}
					 	if(null != subject && '' != subject){
					 		cell.subject = subject;
					 	}
					 	if(null != cc&& '' != cc){
					 		cell.cc = cc;
					 	}
					 	if(null != bcc && '' != bcc){
					 		cell.bcc = bcc;
					 	}
					 	if(null != charset && '' != charset){
					 		cell.charset = charset;
					 	}
					 	if(null != html && '' != html){
					 		cell.html = html;
					 	}
					 	if(null != nohtml && '' != nohtml){
					 		cell.nohtml = nohtml;
					 	}
					 	//4配置会签
					 	multi_instance_setvalue(cell);
						graph.startEditing();
						mailTaskNodeAttributeWin.close(this); 
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
	            mailTaskNodeAttributeWin.close();  
	          }  
	        }  
	      ]  
	     });  
	     mailTaskNodeAttributeWin.show(); 
	}
}

function editMailTaskNodeAttributeForm(cell){
	reGetWidthAndHeight();
    mailTaskNodeAttributeFormMain = new Ext.FormPanel({
        labelWidth:68,
        labelAlign:'right',
        region:'center',
        frame:false,
        border:false,
        autoScroll:true,
        height:clientHeight*0.8,
        items:[{
         	xtype:'textfield',  
            fieldLabel:'接&nbsp;收&nbsp;&nbsp;者',  
           	name:'to',
           	id:'to',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'发&nbsp;送&nbsp;&nbsp;者',  
           	name:'from',
           	id:'from',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'主&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',  
           	name:'subject',
           	id:'subject',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'抄&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;送',  
           	name:'cc',
           	id:'cc',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'秘&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;送',  
           	name:'bcc',
           	id:'bcc',  
           	anchor:'100%'
         },{
         	xtype:'textfield',  
            fieldLabel:'字&nbsp;符&nbsp;&nbsp;集',  
           	name:'charset',
           	id:'charset',  
           	anchor:'100%'
         },{
         	xtype:'htmleditor',  
            fieldLabel:'HTML&nbsp;框',  
           	name:'html',
           	id:'html',  
           	anchor:'100%'
         },{
         	xtype:'textareafield',  
            fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',  
           	name:'nohtml',
           	id:'nohtml',  
           	anchor:'100%'
         }]
    });
    var to = cell.to;
 	var from = cell.from;
 	var subject = cell.subject;
 	var cc = cell.cc;
 	var bcc = cell.bcc;
 	var charset = cell.charset;
 	var html = cell.html;
 	var nohtml = cell.nohtml;
 	if(null != to && '' != to){
 		Ext.getCmp('to').setValue(to);
 	}
 	if(null != from && '' != from){
 		Ext.getCmp('from').setValue(from);
 	}
 	if(null != subject && '' != subject){
 		Ext.getCmp('subject').setValue(subject);
 	}
 	if(null != cc && '' != cc){
 		Ext.getCmp('cc').setValue(cc);
 	}
 	if(null != bcc && '' != bcc){
 		Ext.getCmp('bcc').setValue(bcc);
 	}
 	if(null != charset && '' != charset){
 		Ext.getCmp('charset').setValue(charset);
 	}
 	if(null != html && '' != html){
 		Ext.getCmp('html').setValue(html);
 	}
 	if(null != nohtml && '' != nohtml){
 		Ext.getCmp('nohtml').setValue(nohtml);
 	}
}


function mailTaskNodeAttributePanel(cell,graph_refresh){
	reGetWidthAndHeight();
	if(lc_design_displaywin_for_edit){
		//基本配置与高级配置
		editMailTaskNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//会签配置
		initMultiInstance(cell);
	    mailTaskNodeAttributeTabPanel = new Ext.TabPanel({
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
	        	{title:'基本配置',items:mailTaskNodeAttributeFormMain	
	        	},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm,
	            	autoScroll:true,
	                /**新方法使用开始**/  
	                scrollable:true,  
	                scrollable:'x',
	                scrollable:'y',
	                /**新方法使用结束**/ 	
	            }
	        ],
	         buttons:[{  
		         	text:'确 定',  
		          	handler:function(){ 
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
						 	var to = Ext.getCmp('to').getValue();
						 	var from = Ext.getCmp('from').getValue();
						 	var subject = Ext.getCmp('subject').getValue();
						 	var cc = Ext.getCmp('cc').getValue();
						 	var bcc = Ext.getCmp('bcc').getValue();
						 	var charset = Ext.getCmp('charset').getValue();
						 	var html = Ext.getCmp('html').getValue();
						 	var nohtml = Ext.getCmp('nohtml').getValue();
						 	if(null != to && '' != to){
						 		cell.to = to;
						 	}
						 	if(null != from && '' != from){
						 		cell.from = from;
						 	}
						 	if(null != subject && '' != subject){
						 		cell.subject = subject;
						 	}
						 	if(null != cc&& '' != cc){
						 		cell.cc = cc;
						 	}
						 	if(null != bcc && '' != bcc){
						 		cell.bcc = bcc;
						 	}
						 	if(null != charset && '' != charset){
						 		cell.charset = charset;
						 	}
						 	if(null != html && '' != html){
						 		cell.html = html;
						 	}
						 	if(null != nohtml && '' != nohtml){
						 		cell.nohtml = nohtml;
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
		editMailTaskNodeAttributeForm(cell);
		//共用taskGrid属性事件
		event_task_grid(cell,2);
		//一般属性 参数1表示非开始2其他
		initNodeNormalForm(cell,1);
		//会签配置
		initMultiInstance(cell);
	    mailTaskNodeAttributeTabPanel = new Ext.TabPanel({
	        border:false,
	        activeTab:0,
	        height:clientHeight*0.95,
	        split:true, 
	        region:"center",
	        tabPosition:'left',
	        tabRotation:0,
	        items:[
	        	{title:'一般配置',items:nodeNormalForm},
	        	{title:'基本配置',items:mailTaskNodeAttributeFormMain},
	            {title:'事件配置',items:event_grid,layout:'border'},
	            {title:'会签配置',items:multiInstanceLoopCharacteristicForm}
	        ]
	    });
	}
}