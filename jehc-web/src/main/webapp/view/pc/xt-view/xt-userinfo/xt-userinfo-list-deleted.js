var store_deleted;
var grid_deleted;
var win_deleted;
var formSearc;
function initListDeleted(){
	store_deleted = getGridJsonStore('../xtUserinfoController/getXtUserinfoDeletedListByCondition',[{}]);
	expander_deleted = new Ext.ux.RowExpander({
	    rowBodyTpl:new top.Ext.XTemplate(
	    	'<table border="1" width="100%" style="border-collapse: collapse; border-color:#f5f5f5;">',
	             '<tr><td width="90"><b>照&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;片</b></td><td><img src="../deng/images/img/552cd6d7b8ec1_32.png"/></td></tr>',
	             /**'<tr><td><b>学&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;历</b></td><td>{xt_userinfo_highestDegree}</td></tr>',**/
	             '<tr><td><b>出生年月</td><td>{xt_userinfo_birthday}</b></td></tr>',
	             '<tr><td><b>政治面貌</td><td>{xt_userinfo_politicalStatus}</b></td></tr>',
	             '<tr><td><b>电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话</td><td>{xt_userinfo_mobile}</b></td></tr>',
	             '<tr><td><b>入职时间</td><td>{xt_userinfo_intime}</b></td></tr>',
	             '<tr><td><b>电子邮件</td><td>{xt_userinfo_email}</b></td></tr>',
	             '<tr><td><b>毕业学校</td><td>{xt_userinfo_schoolName}</b></td></tr>',
	             '<tr><td><b>备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</td><td>{xt_userinfo_remark}</b></td></tr>',
            '</table>'
	    )
	});
	/**查询区域可扩展**/
	var formItems = Ext.create('top.Ext.FormPanel',{
		maxHeight:220,
		waitMsgTarget:true,
		defaultType:'textfield',
		fieldDefaults:{
			labelWidth:40,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			layout:'table',
			xtype:'form',
			anchor:'100%',
			items:[
			{
				fieldLabel:'部门',
				xtype:'textfield',
				name:'xt_departinfo_name',
				width:220
			},
			{
				fieldLabel:'岗位',
				xtype:'textfield',
				name:'xt_post_name',
				width:220
			},
			{
				fieldLabel:'账户',
				xtype:'textfield',
				name:'xt_userinfo_name',
				width:220
			},
			{
				fieldLabel:'姓名',
				xtype:'textfield',
				name:'xt_userinfo_realName',
				width:220
			}
			]
		}
		]
	});
	formSearc = initSearchFormByUserdefined('north',formItems,true,'left');
	grid_deleted = Ext.create('top.Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store_deleted,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		selType:'checkboxmodel',
		title:'查询结果',
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		plugins:expander_deleted,
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
				header:'岗位',
				dataIndex:'xt_post_name'
			},
			{
				header:'部门',
				flex:1,
				dataIndex:'xt_departinfo_name'
			}
		],
		tbar:[
			 {
				text:'恢复用户',
				tooltip:'恢复用户',
				scope:this,
				icon:editIcon,
				handler:function(){
					recoverXtUserinfo();
				}
			 },
			 {
				text:'检 索',
				tooltip:'检索',
				minWidth:tbarBtnMinWidth,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					searchDeleted();
				}
			 },
			 {
				text:'重 置',
				tooltip:'重置',
				minWidth:tbarBtnMinWidth,
				icon:clearSearchIcon,
				handler:function(){
					formSearc.reset();
				}
			 },
			 grid_toolbar_moretext_gaps,
			 {
	            text:moretext,
	            tooltip:moretexttooltip,
	            scope:this,
	            icon:listIcon,
	            iconAlign:'left',
				menu:[
					 {
						text:'导 出',
						tooltip:'导 出',
						scope:this,
						glyph:0xf1c3,
						handler:function(){
							exportXtUserinfo(grid_deleted,'../xtUserinfoController/exportXtUserinfo');
						}
					 },
					 '-',
					 {
						text:'展开节点',
						tooltip:'展开节点',
						scope:this,
						glyph:0xf067,
						handler:function(){
							grid_deleted.getStore().reload({callback:expendRow_deleted});
						}
					 },
					 '-',
					 {
						text:'全 选',
						glyph:0xf046,
						handler:function(){selectAll(grid_deleted);}
					 },
					 {
						text:'反 选',
						glyph:0xf096,
						handler:function(){unSelectAll(grid_deleted);}
					 },
					 '-',
					 {
						text:'刷 新',
						glyph:0xf021,
						handler:function(){refreshGrid(grid_deleted);}
					 }
					 ]
     		 }
		],
		bbar:getGridTopBBar(store_deleted)
	});
	store_deleted.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store_deleted,formSearc));});
	reGetWidthAndHeight();
	win_deleted = Ext.create('top.Ext.Window',{
		layout:'border',
		title:'已删除用户列表',
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		headerPosition:'left',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		animateTarget:document.body,
		plain:true,
		modal:true,
		items:[formSearc,grid_deleted],
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	win_deleted.show();
}

//展开符合某个条件的行 
function expendRowFrist_deleted(){ 
	var i;//循环临时变量 
	var arr = [];//要展开的行的数组 
	//store是gridpanel的数据源 
	for(i=0;i<store_deleted.data.length;i++){ 
		var record = store_deleted.getAt(i);//循环遍历每一行 
		//符合我的条件则写入数组中 
		/**
		if(record.data.xt_userinfo_name=='10002'){
			arr.push(i); 
		} 
		**/
		/**暂时注释掉初始化展开状态 知道该方法即可 邓纯杰
		expander.toggleRow(i,record);
		break;
		**/
	} 
} 
function expendRow_deleted(){ 
	var i;//循环临时变量 
	var arr = [];//要展开的行的数组 
	//store是gridpanel的数据源 
	for(i=0;i<store_deleted.data.length;i++){ 
		var record = store_deleted.getAt(i);//循环遍历每一行 
		if(record.data.xt_userinfo_name=='10002'){
			//符合我的条件则写入数组中 
			arr.push(i); 
		} 
		expander_deleted.toggleRow(i,record)
	} 
} 

/**恢复操作**/
function recoverXtUserinfo(){
	var model = grid_deleted.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在恢复');
		return;
	}
	var xt_userinfo_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_userinfo_id){
			xt_userinfo_id=model.selected.items[i].data.xt_userinfo_id;
		}else{
			xt_userinfo_id=xt_userinfo_id+","+model.selected.items[i].data.xt_userinfo_id;
		}
	}
	top.Ext.Msg.confirm('提示','确定要恢复所选数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_userinfo_id:xt_userinfo_id};
			var gridArray = [];
			gridArray.push(grid_deleted);
			gridArray.push(grid);
			ajaxReq('../xtUserinfoController/recoverXtUserinfo',gridArray,params,'正在执行恢复数据中！请稍后...');
		}
	});
}

function searchDeleted(){
	initSearch(store_deleted,'../xtUserinfoController/getXtUserinfoDeletedListByCondition',formSearc); 
}