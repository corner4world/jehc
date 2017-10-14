var store;
var grid;
var xt_change_pwd_sex_combo;
var xt_change_pwd_status_combo;
Ext.onReady(function(){
	xt_change_pwd_sex_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","男"],["1","女"]]
	});
	xt_change_pwd_status_combo = Ext.create('Ext.data.SimpleStore',{ 
	     fields:['value','text'],  
		 data:[["0","待审核"],["1","审核未通过"],["2","审核通过"]]
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
			fieldLabel:'用户名称',
			xtype:'textfield',
			labelWidth:70,
			id:'user_name',
			name:'user_name',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'登录账号',
			xtype:'textfield',
			labelWidth:70,
			id:'login_id',
			name:'login_id',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:xt_change_pwd_sex_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'sex',
			valueField:'value',
			displayField:'text',
			id:'sex',
			name:'sex',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'手机号码',
			xtype:'textfield',
			labelWidth:70,
			id:'phone',
			name:'phone',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'创建时间',
			xtype:'textfield',
			labelWidth:70,
			id:'ctime',
			name:'ctime',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'居住地址',
			xtype:'textareafield',
			labelWidth:70,
			id:'address',
			name:'address',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'邮箱地址',
			xtype:'textfield',
			labelWidth:70,
			id:'mail',
			name:'mail',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:xt_change_pwd_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'status',
			valueField:'value',
			displayField:'text',
			id:'status',
			name:'status',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtChangePwdController/getXtChangePwdListByCondition',[]);
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
				header:'用户名称',
				dataIndex:'user_name'
			},
			{
				header:'登录账号',
				dataIndex:'login_id'
			},
			{
				header:'手机号码',
				dataIndex:'phone'
			},
			{
				header:'邮箱地址',
				dataIndex:'mail'
			},
			{
				header:'创建时间',
				dataIndex:'ctime'
			},
			{
				header:'状态',
				dataIndex:'status'
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
					addXtChangePwd();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtChangePwd();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtChangePwd();
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
						copyXtChangePwd();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtChangePwd();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtChangePwd(grid,'../xtChangePwdController/exportXtChangePwd');
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
				detailXtChangePwd();
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
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtChangePwd(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_change_pwd_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_change_pwd_id){
			xt_change_pwd_id=model.selected.items[i].data.xt_change_pwd_id;
		}else{
			xt_change_pwd_id=xt_change_pwd_id+","+model.selected.items[i].data.xt_change_pwd_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_change_pwd_id:xt_change_pwd_id};
			ajaxRequest('../xtChangePwdController/delXtChangePwd',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtChangePwd(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_change_pwd_id:record.items[0].data.xt_change_pwd_id};
			ajaxRequest('../xtChangePwdController/copyXtChangePwd',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtChangePwd(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtChangePwdItem',
			handler:function(){addXtChangePwd();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtChangePwdItem',
			handler:function(){updateXtChangePwd();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtChangePwdItem',
			handler:function(){delXtChangePwd();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtChangePwdItem',
			handler:function(){copyXtChangePwd();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtChangePwdItem',
			handler:function(){detailXtChangePwd();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtChangePwd(grid,'../xtChangePwdController/exportXtChangePwd');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtChangePwdItem','delXtChangePwdItem','copyXtChangePwdItem','detailXtChangePwdItem']);
}
/**查询操作**/
function search(){
	initSearch(store,'../xtChangePwdController/getXtChangePwdListByCondition',searchForm); 
}
