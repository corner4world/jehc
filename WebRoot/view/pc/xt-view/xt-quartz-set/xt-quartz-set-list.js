var store;
var grid;
Ext.onReady(function(){
	store = getGridJsonStore('../xtQuartzSetController/getXtQuartzSetList',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
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
				header:'任务编号',
				hidden:true,
				dataIndex:'jobId'
			},
			{
				header:'任务名称',
				locked:true,
				dataIndex:'jobName'
			},
			{
				header:'任务分组',
				hidden:true,
				dataIndex:'jobGroup'
			},
			{
				header:'任务状态',
				locked:true,
				dataIndex:'jobStatus',
				renderer:function(value){
					if(value == 'NORMAL'){
						return "已启动";
					}else if(value == 'PAUSED'){
						return "已暂停";
					}else{
						return "其它";
					}
				}
			},
			{
				header:'任务运行时间表达式',
				locked:true,
				dataIndex:'cronExpression'
			},
			{
				header:'任务描述',
				locked:true,
				dataIndex:'desc'
			},
			{
				header:'操作',
				columns:[{
					header:'暂停',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'暂停',
		                listeners:{
					    render:function(rec) {
					    		/**
						        var record = rec.getWidgetRecord();
						        var jobStatus = record.data.jobStatus;
						        if(jobStatus == 'PAUSED'){
						        	rec.setText("<font color=''>暂停</font>",true);
						        }else if(jobStatus == 'PAUSED'){
						        	rec.setText("<font color='green'>暂停</font>");
						        }else{
						        	rec.setText("<font color=''>暂停</font>",false);
						        }
						        **/
						    } 
						}, 
		                handler:function(rec){	
		  					var jobGroup = rec.getWidgetRecord().data.jobGroup;
		  					var jobName = rec.getWidgetRecord().data.jobName;
		  					var jobStatus = rec.getWidgetRecord().data.jobStatus;
		  					if(jobStatus == 'NORMAL'){
					        	stopXtQuartz(jobGroup,jobName);
					        }else{
					        	msgTishi("任务已经关闭,不能操作了!");
					        }
					    }
		            }
				},{
					header:'恢复',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'恢复',
		                handler:function(rec){
		                	var jobGroup = rec.getWidgetRecord().data.jobGroup;
		  					var jobName = rec.getWidgetRecord().data.jobName;
		  					var jobStatus = rec.getWidgetRecord().data.jobStatus;
		  					if(jobStatus == 'PAUSED'){
					        	reStartXtQuartz(jobGroup,jobName);
					        }else{
					        	msgTishi("任务已经启动,不能操作了!");
					        }
		                }
		            }
				},{
					header:'删除',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'删除',
		                handler:function(rec){
		                	var jobGroup = rec.getWidgetRecord().data.jobGroup;
		  					var jobName = rec.getWidgetRecord().data.jobName;
		  					delXtQuartz(jobGroup,jobName);
		                }
		            }
				},{
					header:'立即执行一次',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'立即执行一次',
		                handler:function(rec){
		                	var jobGroup = rec.getWidgetRecord().data.jobGroup;
		  					var jobName = rec.getWidgetRecord().data.jobName;
		  					var jobStatus = rec.getWidgetRecord().data.jobStatus;
		  					if(jobStatus == 'NORMAL'){
					        	startXtQuartz(jobGroup,jobName);
					        }else{
					        	msgTishi("任务已经关闭,不能操作了!");
					        }
		                }
		            }
				},{
					header:'一秒执行一次',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'一秒执行一次',
		                handler:function(rec){
		                	var jobGroup = rec.getWidgetRecord().data.jobGroup;
		  					var jobName = rec.getWidgetRecord().data.jobName;
		  					var jobStatus = rec.getWidgetRecord().data.jobStatus;
		  					if(jobStatus == 'NORMAL'){
					        	oneSecondXtQuartz(jobGroup,jobName);
					        }else{
					        	msgTishi("任务已经关闭,不能操作了!");
					        }
		                }
		            }
				},{
					header:'五秒执行一次',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'五秒执行一次',
		                handler:function(rec){
		                	var jobGroup = rec.getWidgetRecord().data.jobGroup;
		  					var jobName = rec.getWidgetRecord().data.jobName;
		  					var jobStatus = rec.getWidgetRecord().data.jobStatus;
		  					if(jobStatus == 'NORMAL'){
					        	fiveSecondsXtQuartz(jobGroup,jobName);
					        }else{
					        	msgTishi("任务已经关闭,不能操作了!");
					        }
		                }
		            }
				}]
			}
		],
		tbar:[
			{
				tooltip:'启动默认任务',
				icon:runIcon,
				handler:function(){
					defaultStartXtQuartzSet();
				}
			 },
			 {
				tooltip:'添加调度任务',
				icon:addIcon,
				handler:function(){
					addXtQuartzSet();
				}
			 },
			 {
				tooltip:'参考样例设置',
				icon:lingdangIcon,
				handler:function(){
					ckXtQuartzSet();
				}
			 }
		]
	});
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
});
/**暂停任务**/
function stopXtQuartz(jobGroup,jobName){
	Ext.Msg.confirm('提示','确定暂停该任务？',function(btn){
		if(btn == 'yes'){
			var params = {jobGroup:jobGroup,jobName:jobName};
			ajaxRequest('../xtQuartzSetController/stopXtQuartzSet',grid,params,'正在执行暂停任务操作中！请稍后...');
		}
	});
}
/**删除操作**/
function delXtQuartz(jobGroup,jobName){
	Ext.Msg.confirm('提示','确定删除该任务？',function(btn){
		if(btn == 'yes'){
			var params = {jobGroup:jobGroup,jobName:jobName};
			ajaxRequest('../xtQuartzSetController/delXtQuartzSet',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**重启任务**/
function reStartXtQuartz(jobGroup,jobName){
	Ext.Msg.confirm('提示','确定要重启该任务？',function(btn){
		if(btn == 'yes'){
			var params = {jobGroup:jobGroup,jobName:jobName};
			ajaxRequest('../xtQuartzSetController/reStartXtQuartzSet',grid,params,'正在执行重启任务操作中！请稍后...');
		}
	});
}
/**立即执行一次**/
function startXtQuartz(jobGroup,jobName){
	Ext.Msg.confirm('提示','确定要立即执行一次该任务？',function(btn){
		if(btn == 'yes'){
			var params = {jobGroup:jobGroup,jobName:jobName};
			ajaxRequest('../xtQuartzSetController/startXtQuartzSet',grid,params,'正在执行立即执行一次任务操作中！请稍后...');
		}
	});
}
/**一秒执行一次**/
function oneSecondXtQuartz(jobGroup,jobName){
	Ext.Msg.confirm('提示','确定要一秒执行一次该任务？',function(btn){
		if(btn == 'yes'){
			var params = {jobGroup:jobGroup,jobName:jobName};
			ajaxRequest('../xtQuartzSetController/oneSecondXtQuartzSet',grid,params,'正在执行一秒执行一次任务操作中！请稍后...');
		}
	});
}
/**五秒执行一次**/
function fiveSecondsXtQuartz(jobGroup,jobName){
	Ext.Msg.confirm('提示','确定要五秒执行一次该任务？',function(btn){
		if(btn == 'yes'){
			var params = {jobGroup:jobGroup,jobName:jobName};
			ajaxRequest('../xtQuartzSetController/fiveSecondsXtQuartzSet',grid,params,'正在执行五秒执行一次任务操作中！请稍后...');
		}
	});
}
/**默认启动**/
function defaultStartXtQuartzSet(){
	Ext.Msg.confirm('提示','确定要默认启动该任务？',function(btn){
		if(btn == 'yes'){
			var params = {};
			ajaxRequest('../xtQuartzSetController/defaultStartXtQuartzSet',grid,params,'正在执行默认启动操作中！请稍后...');
		}
	});
}
var xtQuartzWin;
var xtQuartzForm;
function ckXtQuartzSet(){
	initXtQuartzForm();
	xtQuartzWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'参考样例设置',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		items:xtQuartzForm,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtQuartzWin.show();
}

function initXtQuartzForm(){
	xtQuartzForm = Ext.create('Ext.FormPanel',{
		xtype:'form',
		labelWidth:0,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:140,
			labelAlign:'right',
			flex:1,
			margin:'4 5 4 5'
		},
		items:[
		{
			//fieldLabel:'0 0 12 * * ?',
			xtype:'textfield',
			value:'0 0 12 * * ? | 每天中午12点触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 ? * *',
			xtype:'textfield',
			value:'0 15 10 ? * * | 每天上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 * * ?',
			xtype:'textfield',
			value:'0 15 10 * * ? | 每天上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 * * ? *',
			xtype:'textfield',
			value:'0 15 10 * * ? * | 每天上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 * * ? 2005',
			xtype:'textfield',
			value:'0 15 10 * * ? 2005 | 2005年的每天上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 * 14 * * ?',
			xtype:'textfield',
			value:'0 * 14 * * ? | 在每天下午2点到下午2:59期间的每1分钟触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 0/5 14 * * ?',
			xtype:'textfield',
			value:'0 0/5 14 * * ? | 在每天下午2点到下午2:55期间的每5分钟触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 0/5 14,18 * * ?',
			xtype:'textfield',
			value:'0 0/5 14,18 * * ? | 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 0-5 14 * * ?',
			xtype:'textfield',
			value:'0 0-5 14 * * ? | 在每天下午2点到下午2:05期间的每1分钟触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 10,44 14 ? 3 WED',
			xtype:'textfield',
			value:'0 10,44 14 ? 3 WED | 每年三月的星期三的下午2:10和2:44触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 ? * MON-FRI',
			xtype:'textfield',
			value:'0 15 10 ? * MON-FRI | 周一至周五的上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 15 * ?',
			xtype:'textfield',
			value:'0 15 10 15 * ? | 每月15日上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 L * ?',
			xtype:'textfield',
			value:'0 15 10 L * ? | 每月最后一日的上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 ? * 6L',
			xtype:'textfield',
			value:'0 15 10 ? * 6L | 每月的最后一个星期五上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 ? * 6L 2002-2005',
			xtype:'textfield',
			value:'0 15 10 ? * 6L 2002-2005 | 2002年至2005年的每月的最后一个星期五上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		},
		{
			//fieldLabel:'0 15 10 ? * 6#3',
			xtype:'textfield',
			value:'0 15 10 ? * 6#3 | 每月的第三个星期五上午10:15触发',
			allowBlank:false,
			maxLength:50,
			anchor:'100%'
		}
		]
	});
}
