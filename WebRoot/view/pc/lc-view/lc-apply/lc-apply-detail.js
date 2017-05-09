var lcApplyWinDetail;
var lcApplyFormDetail;
var lcApplyTabPanel;
var lcApprovalGrid;
var lcApprovalStore;
function detailLcApply(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看申请信息明细的项！');
		return;
	}
	var processInstance_id = record.items[0].data.processInstance_id;
	var processdefinitions_id = record.items[0].data.processdefinitions_id;
	var lc_apply_model_biz_id = record.items[0].data.lc_apply_model_biz_id;
	var lc_apply_id = record.items[0].data.lc_apply_id;
	var xt_constantURL = record.items[0].data.xt_constantURL;
	initLcApplyFormDetail(lc_apply_id,processInstance_id,processdefinitions_id,lc_apply_model_biz_id,xt_constantURL);
	reGetWidthAndHeight();
	lcApplyWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		headerPosition:'right',
		title:'申请信息明细',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:lcApplyTabPanel/**,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]**/
	});
	lcApplyWinDetail.show();
	loadFormData(lcApplyFormDetail,'../lcApplyController/getLcApplyById?lc_apply_id='+ record.items[0].data.lc_apply_id);
}
function initLcApplyFormDetail(lc_apply_id,processInstance_id,processdefinitions_id,lc_apply_model_biz_id,url){
	lcApplyFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		/**新方法使用结束**/
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			readOnly:true,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'流程实例发起编号（即申请编号）',
			xtype:'textfield',
			hidden:true,
			name:'lc_apply_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textareafield',
			name:'lc_apply_title',
			maxLength:1024,
			anchor:'100%'
		},
		{
			fieldLabel:'模&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;块',
			xtype:'textfield',
			name:'xt_constantRemark',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'流程实例',
			xtype:'textfield',
			hidden:true,
			name:'processInstance_id',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'流程定义',
			xtype:'textfield',
			hidden:true,
			name:'processdefinitions_id',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'申请时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'lc_apply_time',
			maxLength:19,
			anchor:'40%'
		}
		]
	});
	initLcApprovalGrid(lc_apply_id);
	lcApplyTabPanel = Ext.create('Ext.TabPanel',{
        activeTab:0,
        tabPosition:'top',
        split:false,
        region:"south",
        frame:true, 
        autoHeight:true, 
        /**
        title:'流程实例表单信息',
        headerPosition:'left',
        **/
        items:[
            {title:'实例信息',items:lcApplyFormDetail},
            {title:'表单信息',html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../'+url+'?lc_apply_model_biz_id='+lc_apply_model_biz_id+'"></iframe>'},
            {title:'审批记录',items:lcApprovalGrid,layout:'fit'},
            {title:'流程实例图',html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../lcProcessController/loadLcProcessInstanceImg?processInstanceId='+processInstance_id+'"></iframe>'}
        ]
    });
}


function initLcApprovalGrid(lc_apply_id){
	lcApprovalStore = getGridJsonStore('../lcApprovalController/getLcApprovalListByCondition?lc_apply_id='+lc_apply_id,[]);
	lcApprovalGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:lcApprovalStore,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
		selType:'checkboxmodel',
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'序号',
				width:77,
				xtype:'rownumberer'
			},
			{
				header:'批审状态',
				flex:1,
				dataIndex:'lc_status_name'
			},
			{
				header:'审批内容',
				flex:1,
				dataIndex:'lc_approval_remark'
			},
			{
				header:'审批时间',
				flex:1,
				dataIndex:'lc_approval_time'
			},
			{
				header:'批审人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			}
		],
		bbar:getGridBBar(lcApprovalStore)
	});
}
