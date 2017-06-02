var store;
var grid;
var lcApprovalGrid;
var lcApprovalStore;
Ext.onReady(function(){
	store = getGridJsonStore('../lcProcessController/getProcessInstance?lc_deployment_his_id='+$('#lc_deployment_his_id').val(),[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
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
				header:'实例编号',
				dataIndex:'id'
			},
			{
				header:'流程定义名称',
				dataIndex:'processDefinitionName'
			},
			{
				header:'实例状态',
				dataIndex:'pstatus'
			},
			{
				header:'是否挂起',
				flex:1,
				dataIndex:'suspended'
			},
			{
				header:'版本',
				flex:1,
				dataIndex:'processDefinitionVersion'
			},
			{
				header:'操作',
				columns:[
				{
					header:'查看流程实例图',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'查看流程实例图',
		                handler:function(rec){	
		  					var id = rec.getWidgetRecord().data.id;
		  					showLcProcessInstance(id);
					    }
		            }
				},
				{
					header:'查看流程实例审批记录',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'查看流程实例审批记录',
		                handler:function(rec){	
		  					var id = rec.getWidgetRecord().data.id;
		  					initLcApprovalWin(id);
					    }
		            }
				},
				{
					header:'挂起',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'挂起',
		                handler:function(rec){	
		  					var id = rec.getWidgetRecord().data.id;
		  					var suspended = rec.getWidgetRecord().data.suspended;
		  					var params = {id:id};
		  					if(suspended == '是'){
		  						msgTishi("该实例已经挂起不能再次操作!");
		  						return;
		  					}
		  					Ext.Msg.confirm('提示','确定挂起该流程实例？',function(btn){
								if(btn == 'yes'){
									ajaxRequest('../lcProcessController/suspendProcessInstanceById',grid,params,'正在挂起该流程实例中！请稍后...');
								}
							});
					    }
		            }
				},
				{
					header:'激活',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'激活',
		                handler:function(rec){	
		  					var id = rec.getWidgetRecord().data.id;
		  					var suspended = rec.getWidgetRecord().data.suspended;
		  					var params = {id:id};
		  					if(suspended == '否'){
		  						msgTishi("该实例已经激活不能再次操作!");
		  						return;
		  					}
		  					Ext.Msg.confirm('提示','确定激活该流程实例？',function(btn){
								if(btn == 'yes'){
									ajaxRequest('../lcProcessController/activateProcessInstanceById',grid,params,'正在激活该流程实例中！请稍后...');
								}
							});
					    }
		            }
				}]
			}
		],
		tbar:[
			 {
				text:'返回',
				tooltip:'返回',
				minWidth:tbarBtnMinWidth,
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../lcDeploymentHisController/loadLcDeploymentHis?lc_process_id="+$('#lc_process_id').val();
				}
			 },
			 {
			 	text:'流程实例任务',
				tooltip:'流程实例任务',
				icon:listIcon,
				minWidth:tbarBtnMinWidth,
				handler:function(){
					getTaskList();
				}
			 },
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailLcDeploymentHis();
					}
				 },
				 '-',
				 {
					text:'打 印',
					tooltip:'打 印',
					glyph:0xf02f,
					handler:function(){
						printerGrid(grid);
					}
				 },
				 '-',
				 {
					text:'全 选',
					tooltip:'全 选',
					glyph:0xf046,
					handler:function(){selectAll(grid);}
				 },
				 {
					text:'反 选',
					tooltip:'反 选',
					glyph:0xf096,
					handler:function(){unSelectAll(grid);}
				 },
				 '-',
				 {
					text:'刷 新',
					tooltip:'刷 新',
					glyph:0xf021,
					handler:function(){
						grid.getStore().reload();
					}
				 }
				 ]
			 }
		],
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
});

var lcProcessInstanceWin;
function showLcProcessInstance(id){
	reGetWidthAndHeight();
	lcProcessInstanceWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth*0.8,                    
		height:clientHeight*0.8,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		headerPosition:'right',
		title:'实例监控图',
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../lcProcessController/loadLcProcessInstanceImg?processInstanceId='+id+'"></iframe>',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		}
	});
	lcProcessInstanceWin.show();
}
var lcApprovalWin;
function initLcApprovalWin(id){
	initLcApprovalGrid(id);
	reGetWidthAndHeight();
	lcApprovalWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth*0.8,                    
		height:clientHeight*0.8,
		headerPosition:'right',
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		items:lcApprovalGrid,
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		}
	});
	lcApprovalWin.show();
}

function initLcApprovalGrid(id){
	lcApprovalStore = getGridJsonStore('../lcApprovalController/getLcApprovalListByCondition?instanceId='+id,[]);
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
		title:'审批列表',
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
var taskGrid;
var taskStore;
function getTaskList(){
	reGetWidthAndHeight();
	var record = grid.getSelectionModel().selected;
	if(record.length != 1){
		msgTishi('请选择要查看的一项！');
		return;
	}
	var id = record.items[0].data.id;
	taskStore = getGridJsonStore('../lcTaskController/getTaskListByCondition?instanceId='+id,[]);
	taskGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:taskStore,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:true,
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
				header:'任务名称',
				locked:true,
				dataIndex:'name'
			},
			{
				header:'所属人',
				locked:true,
				dataIndex:'owner'
			},
			{
				header:'执行人',
				locked:true,
				dataIndex:'assignee'
			},
			{
				header:'描述',
				dataIndex:'description'
			},
			{
				header:'实例编号',
				dataIndex:'processInstanceId'
			},
			{
				header:'流程定义编号',
				dataIndex:'processDefinitionId'
			},
			{
				header:'租户',
				dataIndex:'tenantId'
			},
			{
				header:'提交时间',
				dataIndex:'createTime',
				renderer:function(value){
					return Ext.util.Format.date(value, "Y-m-d H:i:s")
				}
			}
		],
		tbar:[
			 {
			 	text:'完成任务',
				tooltip:'完成任务',
				minWidth:tbarBtnMinWidth,
				handler:function(){
					completeTask();
				}
			 },
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailLcDeploymentHis();
					}
				 },
				 '-',
				 {
					text:'打 印',
					tooltip:'打 印',
					glyph:0xf02f,
					handler:function(){
						printerGrid(grid);
					}
				 },
				 '-',
				 {
					text:'全 选',
					tooltip:'全 选',
					glyph:0xf046,
					handler:function(){selectAll(grid);}
				 },
				 {
					text:'反 选',
					tooltip:'反 选',
					glyph:0xf096,
					handler:function(){unSelectAll(grid);}
				 },
				 '-',
				 {
					text:'刷 新',
					tooltip:'刷 新',
					glyph:0xf021,
					handler:function(){
						grid.getStore().reload();
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(taskStore),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
			}
		}
	});
	var taskWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth*0.8,                    
		height:clientHeight*0.8,
		headerPosition:'right',
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		items:taskGrid,
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		}
	});
	taskWin.show();
}

//完成任务
function completeTask(){
	var record = taskGrid.getSelectionModel().selected;
	if(record.length != 1){
		msgTishi('请选择要完成的一项！');
		return;
	}
	Ext.Msg.confirm('提示','确定要完成该任务？',function(btn){
		if(btn == 'yes'){
			var taskId = record.items[0].data.taskId;
			var params = {taskId:taskId};
			ajaxRequest('../lcProcessController/completeTask',taskGrid,params,'正在操作中！请稍后...');
		}
	});
}
