var store;
var grid;
var lc_process_status_combo;
var lc_process_flag_combo;
var xt_constant_id_combo;
var lc_process_mxgraph_style_combo;
Ext.onReady(function(){
	lc_process_status_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","待发布"],["1","发布中"],["2","已关闭"]]
	});
	lc_process_flag_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
	 	data:[["0","通过平台设计器设计"],["1","通过上传部署"]]
	});
	xt_constant_id_combo = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtConstantController/getXtConstantList?xt_constantType=3',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['xt_constant_id', 'xt_constantName'],
		autoLoad:true 
	});
	lc_process_mxgraph_style_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","直线"],["1","曲线"]]
	});
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		maxHeight:150,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'流程标题',
			xtype:'textfield',
			labelWidth:70,
			id:'lc_process_title',
			name:'lc_process_title',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:lc_process_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'lc_process_status',
			valueField:'value',
			displayField:'text',
			id:'lc_process_status',
			name:'lc_process_status',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'标识',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:lc_process_flag_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'lc_process_flag',
			valueField:'value',
			displayField:'text',
			id:'lc_process_flag',
			name:'lc_process_flag',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'备注',
			xtype:'textareafield',
			labelWidth:70,
			id:'lc_process_remark',
			name:'lc_process_remark',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'常量',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:xt_constant_id_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_constant_id',
			valueField:'xt_constant_id',
			displayField:'xt_constantRemark',
			id:'xt_constant_id',
			name:'xt_constant_id',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../lcProcessController/getLcProcessListByCondition',[]);
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
				header:'流程标题',
				flex:1,
				dataIndex:'lc_process_title'
			},
			{
				header:'标识',
				flex:1,
				dataIndex:'lc_process_flag',
				renderer:function(value){
					if(value == 0){
						return "<font color=red>通过平台设计器设计</font>"
					}else if(value == 1){
						return "<font color=green>通过上传部署</font>";
					}
				}
			},
			{
				header:'状态',
				flex:1,
				dataIndex:'lc_process_status',
				renderer:function(value){
					if(value == 0){
						return "待发布"
					}else if(value == 1){
						return "<font color=green>发布中</font>";
					}else if(value == 1){
						return "<font color=red>已关闭</font>";
					}else{
						return "缺省";
					}
				}
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'lc_process_ctime'
			},
			{
				header:'最后修改时间',
				flex:1,
				dataIndex:'lc_process_mtime'
			},
			{
				header:'创建人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'操作',
				columns:[
				{
					header:'设计流程',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'设计流程',
		                listeners:{
						    render:function(rec) {
						        var record = rec.getWidgetRecord();
						        var lc_process_flag = record.data.lc_process_flag;
						        if(lc_process_flag == 1){
						        	rec.setText("<font color='red'>不支持设计（原因该流程是通过上传部署类型）</font>");
						        }else{
						        	rec.setText("设计流程");
						        }
						    } 
						},
		                handler:function(rec){	
		                	var record = rec.getWidgetRecord();
					        var lc_process_flag = record.data.lc_process_flag;
					        if(lc_process_flag == 1){
					        }else{
					        	addLcDesign(record.data.lc_process_id,record.data.lc_process_title);
					        }
					    }
		            }
				},
				{
					header:'发布流程',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'发布流程',
		                handler:function(rec){	
		                	var lc_process_id = rec.getWidgetRecord().data.lc_process_id;
		                	var params = {lc_process_id:lc_process_id};
		                	Ext.Msg.confirm('提示','确定发布该流程？',function(btn){
								if(btn == 'yes'){
									ajaxRequest('../lcProcessController/createDeployment',grid,params,'正在发布该流程中！请稍后...');
								}
							});
					    }
		            }
				},
				{
					header:'发布历史记录',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                text:'发布历史记录',
		                handler:function(rec){	
		                	var lc_process_id = rec.getWidgetRecord().data.lc_process_id;
		                	showLcDeploymentHis(lc_process_id);
					    }
		            }
				},
				{
					header:'下载文件',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                listeners:{
						    render:function(rec) {
						        var record = rec.getWidgetRecord();
						        var lc_process_flag = record.data.lc_process_flag;
						        if(lc_process_flag == 1){
						        	rec.setText("<font color='red'>下载文件</font>");
						        }else{
						        	rec.setText("下载bpmn文件");
						        }
						    } 
						},
		                handler:function(rec){	
		  					var lc_process_id = rec.getWidgetRecord().data.lc_process_id;
		  					var lc_process_flag = rec.getWidgetRecord().data.lc_process_flag;
		  					var xt_attachment = rec.getWidgetRecord().data.xt_attachment;
					        if(lc_process_flag == 1){
					        	downOrExport(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment);
					        }else{
					        	downOrExport(basePath+'/lcProcessController/downFileBpmn?lc_process_id='+lc_process_id);
					        }
					    }
		            }
				},
				{
					header:'下载文件',
					align:'center',
					xtype:'widgetcolumn',
					widget:{
		                xtype:'button',
		                listeners:{
						    render:function(rec) {
						        var record = rec.getWidgetRecord();
						        var lc_process_flag = record.data.lc_process_flag;
						        if(lc_process_flag == 1){
						        	rec.setText("<font color='red'>下载文件</font>");
						        }else{
						        	rec.setText("下载img文件");
						        }
						    } 
						},
		                handler:function(rec){	
		  					var lc_process_id = rec.getWidgetRecord().data.lc_process_id;
		  					var xt_attachment = rec.getWidgetRecord().data.xt_attachment;
		  					var lc_process_flag = rec.getWidgetRecord().data.lc_process_flag;
					        if(lc_process_flag == 1){
					        	downOrExport(basePath+'/xtCommonController/downFile?xt_attachment_id='+xt_attachment);
					        }else{
					        	downOrExport(basePath+'/lcProcessController/downFileImg?lc_process_id='+lc_process_id);
					        }
					    }
		            }
				}]
			}
		],
		tbar:[
			 {
				text:'添加',
				tooltip:'添加',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addLcProcess();
				}
			 },
			 {
				text:'设计流程',
				tooltip:'设计流程',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addLcDesign(0,'流程设计');
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateLcProcess();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delLcProcess();
				}
			 },
			 {
				text:'检索',
				minWidth:tbarBtnMinWidth,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					search();
				}
			 },
			 {
				text:'重置',
				tooltip:'重置',
				minWidth:tbarBtnMinWidth,
				icon:clearSearchIcon,
				handler:function(){
					searchForm.reset();
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
					text:'复制一行并生成记录',
					tooltip:'复制一行并生成记录',
					glyph:0xf0ea,
					handler:function(){
						copyLcProcess();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailLcProcess();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportLcProcess(grid,'../lcProcessController/exportLcProcess');
					}
				 },
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
				 },
				 {
					text:'检 索',
					tooltip:'检 索',
					glyph:0xf002,
					handler:function(){
						search();
					}
				 },
				 {
					text:'重 置',
					tooltip:'重 置',
					glyph:0xf014,
					handler:function(){
						searchForm.reset();
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailLcProcess();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	/**调用右键**/
	initRight();
});
/**删除操作**/
function delLcProcess(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var lc_process_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == lc_process_id){
			lc_process_id=model.selected.items[i].data.lc_process_id;
		}else{
			lc_process_id=lc_process_id+","+model.selected.items[i].data.lc_process_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {lc_process_id:lc_process_id};
			ajaxRequest('../lcProcessController/delLcProcess',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyLcProcess(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {lc_process_id:record.items[0].data.lc_process_id};
			ajaxRequest('../lcProcessController/copyLcProcess',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportLcProcess(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addLcProcessItem',
			handler:function(){addLcProcess();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateLcProcessItem',
			handler:function(){updateLcProcess();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delLcProcessItem',
			handler:function(){delLcProcess();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyLcProcessItem',
			handler:function(){copyLcProcess();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailLcProcessItem',
			handler:function(){detailLcProcess();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportLcProcess(grid,'../lcProcessController/exportLcProcess');
			}
		},{
			text:'打 印',
			glyph:0xf02f,
			handler:function(){printerGrid(grid);}
		},'-',{
			text:'全 选',
			glyph:0xf046,
			handler:function(){selectAll(grid);}
		},{
			text:'反 选',
			glyph:0xf096,
			handler:function(){unSelectAll(grid);}
		},'-',{
			text:'刷 新',
			glyph:0xf021,
			handler:function(){refreshGrid(grid);}
		}]
	});
	initrightgridcontextmenu(grid,contextmenu,['updateLcProcessItem','delLcProcessItem','copyLcProcessItem','detailLcProcessItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../lcProcessController/getLcProcessListByCondition',searchForm); 
}

var lcDesignWin;
function addLcDesign(lc_process_id,lc_process_title){
	reGetWidthAndHeight();
	lcDesignWin = Ext.create('top.Ext.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximized:true,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'流程设计',
		headerPosition:'right',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			},
			close:function(){
				store.load();
			}
		},
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../lcProcessController/loadLcDesign?lc_process_id='+lc_process_id+'"></iframe>'
	});
	lcDesignWin.setTitle(lc_process_title);
	lcDesignWin.show();
}

var lcDeploymentHisWin;
function showLcDeploymentHis(lc_process_id){
	reGetWidthAndHeight();
	lcDeploymentHisWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'流程发布历史记录',
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../lcDeploymentHisController/loadLcDeploymentHis?lc_process_id='+lc_process_id+'"></iframe>',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			},
			close:function(){
				store.load();
			}
		}
	});
	lcDeploymentHisWin.show();
}