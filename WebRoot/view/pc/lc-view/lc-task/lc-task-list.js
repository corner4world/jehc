var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../lcTaskController/getTaskListByCondition',[]);
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
		  					var id = rec.getWidgetRecord().data.processInstanceId;
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
		  					var id = rec.getWidgetRecord().data.processInstanceId;
		  					initLcApprovalWin(id);
					    }
		            }
				}
				]
			}
		],
		tbar:[
			 {
			 	text:'设置经办人',
				tooltip:'设置经办人',
				cls:'addBtn',
				icon:addIcon,
				minWidth:tbarBtnMinWidth,
				handler:function(){
					setAssignee();
				}
			 },
			 {
			 	text:'设置归属人',
				tooltip:'设置归属人',
				cls:'updateBtn',
				icon:editIcon,
				minWidth:tbarBtnMinWidth,
				handler:function(){
					setOwner();
				}
			 },
			 {
			 	text:'添加组任务中成员',
				tooltip:'添加组任务中成员',
				cls:'setBtn',
				icon:addIcon,
				minWidth:tbarBtnMinWidth,
				handler:function(){
					addGroupUser();
				}
			 },
			 {
			 	text:'删除组任务中成员',
				tooltip:'向组任务中删除成员',
				cls:'delBtn',
				icon:delIcon,
				minWidth:tbarBtnMinWidth,
				handler:function(){
					deleteGroupUser();
				}
			 },
			 {
			 	text:'完成任务',
				tooltip:'完成任务',
				icon:taskIcon,
				cls:'completeBtn',
				minWidth:tbarBtnMinWidth,
				handler:function(){
					completeTask();
				}
			 },
//			 {
//				text:'检索',
//				minWidth:tbarBtnMinWidth,
//				cls:'searchBtn',
//				icon:searchIcon,
//				handler:function(){
//					search();
//				}
//			 },
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
		bbar:getGridBBar(store),
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


function setAssignee(){
	var record = grid.getSelectionModel().selected;
	if(record.length != 1){
		msgTishi('请选择要设置的一项！');
		return;
	}
	var taskId = record.items[0].data.taskId;
	initassignee(taskId,0);
}

function setOwner(){
	var record = grid.getSelectionModel().selected;
	if(record.length != 1){
		msgTishi('请选择要设置的一项！');
		return;
	}
	var taskId = record.items[0].data.taskId;
	initassignee(taskId,1);
}

function addGroupUser(){
	var record = grid.getSelectionModel().selected;
	if(record.length != 1){
		msgTishi('请选择要添加的一项！');
		return;
	}
	var taskId = record.items[0].data.taskId;
	initassignee(taskId,2);
}

function deleteGroupUser(){
	var record = grid.getSelectionModel().selected;
	if(record.length != 1){
		msgTishi('请选择要删除的一项！');
		return;
	}
	var taskId = record.items[0].data.taskId;
	initassignee(taskId,3);
}

//读取性别下拉框数据
var xtUserinfoSexList = new Ext.data.Store({
	singleton:true, 
	proxy:new Ext.data.HttpProxy( { 
		url:'../xtUserinfoController/getXtUserinfoSexList',
		reader:new Ext.data.JsonReader({
			root:'items',
			type:'json'
		})
	}),
	fields:['xt_data_dictionary_id', 'xt_data_dictionary_name'],
	autoLoad:true 
});
//读取名族下拉框数据
var xtUserinfoNationList = new Ext.data.Store({
	singleton:true, 
	proxy:new Ext.data.HttpProxy( { 
		url:'../xtUserinfoController/getXtUserinfoNationList',
		reader:new Ext.data.JsonReader({
			root:'items',
			type:'json'
		})
	}),
	fields:['xt_data_dictionary_id', 'xt_data_dictionary_name'],
	autoLoad:true 
});
//读取是否已婚下拉框数据
var xtUserinfoIsmarriedList = new Ext.data.Store({
	singleton:true, 
	proxy:new Ext.data.HttpProxy( { 
		url:'../xtUserinfoController/getXtUserinfoIsmarriedList',
		reader:new Ext.data.JsonReader({
			root:'items',
			type:'json'
		})
	}),
	fields:['xt_data_dictionary_id', 'xt_data_dictionary_name'],
	autoLoad:true 
});
function initassignee(taskId,type){
	var userStore = getGridJsonStore('../xtUserinfoController/getXtUserinfoListByCondition',[{}]);
	var userGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:userStore,
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
				header:'用户名',
				dataIndex:'xt_userinfo_name'
			},
			{
				header:'真实姓名',
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'性别',
				width:50,
				dataIndex:'xt_userinfo_sex',
				renderer:function(value){
					return initData(xtUserinfoSexList,value);
				}
			},
			{
				header:'是否已婚',
				width:80,
				dataIndex:'xt_userinfo_ismarried',
				renderer:function(value){
					return initData(xtUserinfoIsmarriedList,value);
				}
			},
			{
				header:'籍贯',
				dataIndex:'xt_userinfo_origo',
				renderer:function(value){
					if(value == ''){
						return '∨'
					}else{
						return value;
					}
				}
			},
			{
				header:'入职时间',
				dataIndex:'xt_userinfo_intime',
				renderer:function(value){
					if(value == ''){
						return '∨'
					}else{
						return value;
					}
				}
			},
			{
				header:'到期时间',
				dataIndex:'xt_userinfo_contractTime',
				renderer:function(value){
					if(value == ''){
						return '∨'
					}else{
						return value;
					}
				}
			},
			{
				header:'岗位',
				dataIndex:'xt_post_name'
			},
			{
				header:'部门',
				flex:1,
				dataIndex:'xt_departinfo_name'
			},
			{
				header:'状态',
				dataIndex:'xt_userinfo_isDelete',
				renderer:function(value){
					if(value == 0){
						return '在职'
					}else{
						return '离职'
					}
				}
			}
		],
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				var xt_userinfo_realName = userGrid.getSelectionModel().selected.items[0].data.xt_userinfo_realName;
				var xt_userinfo_id = userGrid.getSelectionModel().selected.items[0].data.xt_userinfo_id; 
				var xt_departinfo_name = userGrid.getSelectionModel().selected.items[0].data.xt_departinfo_name;
				var xt_post_name = userGrid.getSelectionModel().selected.items[0].data.xt_post_name;
				var str = "[<font color=red><br>用户姓名:"+xt_userinfo_realName+"<br>所属部门:"+xt_departinfo_name+"<br>所属岗位:"+xt_post_name+"<br></font>]";
				Ext.Msg.confirm('提示','确定要选择:<br>'+str+'？',function(btn){
					if(btn == 'yes'){
						var params = {taskId:taskId,userId:xt_userinfo_id};
						if(type == 0){
							ajaxRequest('../lcTaskController/setAssignee',grid,params,'设置经办人中！请稍后...');
						}
						if(type == 1){
							ajaxRequest('../lcTaskController/setOwner',grid,params,'设置归属人中！请稍后...');
						}
						if(type == 2){
							ajaxRequest('../lcTaskController/addGroupUser',grid,params,'向组任务中添加成员中！请稍后...');
						}
						if(type == 3){
							ajaxRequest('../lcTaskController/deleteGroupUser',grid,params,'向组任务中删除成员中！请稍后...');
						}
						userWin.close();
					}
				});
			}
		},
		bbar:getGridBBar(userStore)
	});
	reGetWidthAndHeight();
	userWin = Ext.create('Ext.Window',{
		layout:'fit',
		title:'用户列表',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		items:userGrid,
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	userWin.show();
}

//完成任务
function completeTask(){
	var record = grid.getSelectionModel().selected;
	if(record.length != 1){
		msgTishi('请选择要添加的一项！');
		return;
	}
	Ext.Msg.confirm('提示','确定要完成该任务？',function(btn){
		if(btn == 'yes'){
			var taskId = record.items[0].data.taskId;
			var params = {taskId:taskId};
			ajaxRequest('../lcTaskController/completeTask',grid,params,'向组任务中删除成员中！请稍后...');
		}
	});
}

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
		title:'审批记录',
		items:lcApprovalGrid,
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

/**查询操作**/
function search(){
}
