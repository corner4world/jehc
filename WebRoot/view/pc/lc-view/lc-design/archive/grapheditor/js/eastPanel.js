function initEastPanel(graph){
	//基本流程共用属性事件
	event_task_main_grid();
	//基本流程共用数据属性配置
	initdatamainProperties_grid();
	//基本流程共用信号配置
	initSignal_grid();
	//基本流程共用消息配置
	initmessage_grid();
	var processNo = new Date().getTime();
	var basePanel = new Ext.TabPanel({
        region:'center',
		autoScroll:true,
		tabPosition:'left',
		activeTab:1,  
        items:[{
	        	title:'事件配置',
	        	items:event_main_grid,layout:'border'
	        },
	        {
        	title:'基本信息',
        	items:[{
				fieldLabel:'流程编号',
				xtype:'textfield',
				name:'processId',
				id:'processId',
				trigger1Cls:'x-form-cl-trigger',
				value:'process'+processNo,
				onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this)},
				allowBlank:false,
				maxLength:255,
				width:320
			},
			{
				fieldLabel:'流程名称',
				xtype:'textfield',
				value:'process'+processNo,
				trigger1Cls:'x-form-cl-trigger',
				onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this)},
				name:'processName',
				id:'processName',
				allowBlank:false,
				maxLength:255,
				width:320
			},
			{
				fieldLabel:'命名空间',
				xtype:'textfield',
				trigger1Cls:'x-form-cl-trigger',
				value:'http://www.activiti.org/test',
				onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this)},
				name:'mainNameSpace',
				id:'mainNameSpace',
				allowBlank:false,
				width:320
			},
			{
				fieldLabel:'发起人',
				xtype:'textfield',
				name:'candidateStarterUsers',
				hidden:true,
				id:'candidateStarterUsers',
				maxLength:255,
				width:320
			},
			{
				fieldLabel:'发起人',
				xtype:'textfield',
				trigger1Cls:'x-form-cl-trigger',
				trigger2Cls:'x-form-userselect-trigger',
				onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this);Ext.getCmp('candidateStarterUsers').setValue('')},
				onTrigger2Click:function(){initassignee(1,2)},
				id:'candidateStarterUsers_Text',
				maxLength:255,
				width:320
			},
			{
				fieldLabel:'发起人组',
				xtype:'textfield',
				hidden:true,
				name:'candidateStarterGroups',
				id:'candidateStarterGroups',
				maxLength:255,
				width:'80%'
			},
			{
				fieldLabel:'发起人组',
				xtype:'textfield',
				trigger1Cls:'x-form-cl-trigger',
				trigger2Cls:'x-form-userorgselect-trigger',
				onTrigger1Click:function(){this.setValue(''); this.fireEvent('clear', this);Ext.getCmp('candidateStarterGroups').setValue('')},
				onTrigger2Click:function(){initcandidateGroups(2,2)},
				id:'candidateStarterGroups_Text',
				maxLength:255,
				width:'80%'
			},
			{
				fieldLabel:'备注',
				xtype:'textareafield',
				name:'remark',
				id:'remark',
				height:120,
				maxLength:255,
				width:'100%'
			},
			{
				fieldLabel:'所属模块',
				xtype:'combo',
				allowBlank:false,
				emptyText:'请选择',
				store:xt_constant_id_combo,
				mode:'local',
				triggerAction:'all',
				editable:false,
				hiddenName:'xt_constant_id',
				valueField:'xt_constant_id',
				displayField:'xt_constantRemark',
				name:'xt_constant_id',
				id:'xt_constant_id',
				anchor:'80%'
			},
			{
				fieldLabel:'mxgraphxml',
				xtype:'textareafield',
				hidden:true,
				name:'mxgraphxml',
				id:'mxgraphxml',
				anchor:'100%'
			},
			{
				fieldLabel:'imgxml',
				xtype:'textareafield',
				hidden:true,
				name:'imgxml',
				id:'imgxml',
				anchor:'100%'
			},
			{
				fieldLabel:'lc_process_mxgraph_style',
				xtype:'textfield',
				hidden:true,
				value:'0',
				id:'lc_process_mxgraph_style',
				name:'lc_process_mxgraph_style',
				anchor:'100%'
			},
			{
				fieldLabel:'lc_process_id',
				xtype:'textfield',
				hidden:true,
				id:'lc_process_id',
				name:'lc_process_id',
				anchor:'100%'
			},
			{
				fieldLabel:'是否为更新操作',
				xtype:'textfield',
				hidden:true,
				value:'0',
				name:'isUpdate',
				anchor:'100%'
			},
			{
				fieldLabel:'w',
				xtype:'textfield',
				hidden:true,
				id:'w',
				name:'w',
				anchor:'100%'
			},
			{
				fieldLabel:'h',
				xtype:'textfield',
				hidden:true,
				id:'h',
				name:'h',
				anchor:'100%'
			}
        	]
        },
        {
			title:'数据属性',
			items:datamainProperties_grid,layout:'fit'
		},
        {
        	title:'信号定义',
        	items:signal_grid,layout:'fit'
        },
        {
        	title:'消息配置',
        	items:message_grid,layout:'fit'
        }]
    });
    reGetWidthAndHeight();
	eastPanel = new Ext.FormPanel({
    	region:'east',
   		title:'流程基本信息',
//      title:'<a href="javascript:collapsibleCE(1);">流程基本信息</font></a>',
        header:{
//        	items:[{
//                 icon:indexCollapseIcon,
//                 scale:'large',
//                 xtype:'button',
//	               handler:function(button){
//					collapsibleCE(1);
//				   },
//	               style:{background:'#fff'}
//	        }],
            titleAlign:'left'
        },
		hideCollapseTool:false,
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
        headerHeight:400,
//      width:clientWidth-180,
        width:360,
        border:false,
        collapsible:true,
        floatable:false,
		waitMsgTarget:true,
		layout:'border',
		split:false,
		collapseDirection:'left',
//		collapsed:true,
		id:'eastPanel',
		defaultType:'textfield',
		headerPosition:'top',
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'right',
			flex:1,
			margin:'2 5 4 5'
		},
        items:[basePanel]
    })
//    eastPanel.on("collapse",function(e){
//    	Ext.getCmp('eastPanel').setTitle('<a href="javascript:collapsibleCE(1);"><font color="#5fa2dd">流程基本信息</font></a>');
//    });
//    eastPanel.on("expand",function(e){
//    	Ext.getCmp('eastPanel').setTitle('<font color="#fff">流程基本信息</font>');
//    });
}
