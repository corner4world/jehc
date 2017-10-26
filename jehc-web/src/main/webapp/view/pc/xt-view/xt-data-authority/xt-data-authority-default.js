//启动初始化数据权限
var defaultGrid;
var defaultWin;
var defaultStore;
function showDataAuthorityDefaultAllWin(xt_menuinfo_id,xt_menuinfo_title){
	defaultStore = getGridJsonStore('../xtDataAuthorityController/getDataAuthorityDefaultGrid?xt_menuinfo_id='+xt_menuinfo_id,[]);
	defaultGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:defaultStore,
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
				header:'功能编号',
				flex:1,
				hidden:true,
				dataIndex:'xt_functioninfo_id'
			},
			{
				header:'功能名称',
				flex:1,
				dataIndex:'xt_functioninfoName'
			}
		],
		listeners:{
			afterrender:function(grids){
				setTimeout(function(){
					// 选中所有记录
			        var records=defaultGrid.getStore().getRange();
			        var glength = defaultGrid.getStore().getCount();
			        var model = defaultGrid.getSelectionModel();
					for(var i = 0; i < records.length; i++){
	                    var record = records[i];
	                    if(record.get('item')==1){
	                    	model.select(record,true,true);//选中record，并且保持现有的选择，不触发选中事件
	                    }
	                }
	            }, 1000);
		    }
		},
		tbar:[
			 {
				text:'保存',
				tooltip:'保存',
				minWidth:tbarBtnMinWidth,
				cls:'saveBtn',
				icon:saveIcon,
				handler:function(){
					addXtDataAuthorityByDefault(xt_menuinfo_id);
				}
			 }
		]
	});
	defaultWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'默认权限设置',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:defaultGrid,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	defaultWin.show();
}

/**按默认初始化设置**/
function addXtDataAuthorityByDefault(xt_menuinfo_id){
	var model = defaultGrid.getSelectionModel();
	var xt_functioninfo_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_functioninfo_id){
			xt_functioninfo_id=model.selected.items[i].data.xt_functioninfo_id;
		}else{
			xt_functioninfo_id=xt_functioninfo_id+","+model.selected.items[i].data.xt_functioninfo_id;
		}
	}
	Ext.Msg.confirm('提示','确定保存该数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_functioninfo_id:xt_functioninfo_id,xt_menuinfo_id:xt_menuinfo_id};
			ajaxRequest('../xtDataAuthorityController/addXtDataAuthorityByDefault',null,params,'正在执行保存操作中！请稍后...');
		}
	});
}
