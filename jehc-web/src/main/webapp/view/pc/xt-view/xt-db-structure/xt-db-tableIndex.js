//表索引//
var xtDbTableIndexWinAdd;
var xtDbTableIndexFormAdd;
function getXtDbTableIndex(xtDbTableName){
	reGetWidthAndHeight();
	initXtDbTableIndexGrid(xtDbTableName);
	xtDbTableIndexWin = new Ext.Window({
		layout:'fit',                    
		width:800,      
		height:400,
		modal:true,               
		closeAction:'close',                    
		plain:true,                    
		title:'索引列表',
		maximizable:true,
		minimizable:true,
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
		items:xtDbTableIndexGrid
	});
	xtDbTableIndexWin.show();
}

function initXtDbTableIndexGrid(xtDbTableName){
	xtDbTableIndexStore = getGridJsonStore('../xtDbStructureController/getXtDbTableIndex?tableName='+xtDbTableName,[]);
	xtDbTableIndexGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
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
			{header:'索引名称',dataIndex:'key_name',sortable:true},
			{header:'对应字段',dataIndex:'column_Name',sortable:true},
			{header:'允许重复',width:120,dataIndex:'non_unique',sortable:true,
				renderer:function(value){
					if(value == 0){
						return "否"
					}else if(value == 1){
						return "是";
					}
				}
			},
			{header:'描述',flex:1,dataIndex:'comment',sortable:true}
		],
	    tbar:[{
			text:'创建索引',
			tooltip:'创建索引',
			scope:this,
			icon:addIcon,
			handler:function(){addXtDbTableIndex(xtDbTableName);}
		},{
			text:'删 除',
			tooltip:'删 除',
			scope:this,
			icon:delIcon,
			handler:function(){
				delXtDbTableIndex(xtDbTableName);
			}
		}],
	    store:xtDbTableIndexStore,
		loadMask:{msg:'正在加载数据,请稍候......'},
		trackMouseOver:true,
		autoScroll:true
	});
}

//删除
function delXtDbTableIndex(xtDbTableName){
	var filedStr = null;
	var record = xtDbTableIndexGrid.getSelectionModel().selected;
	if(record.length <= 0){
		Ext.example.msg("提示","请选择您要删除的索引");
	}else{
		Ext.MessageBox.confirm('提示', '确实要删除所选的索引吗?',function(btn){
			if(btn == 'yes'){
				for(var i = 0; i < record.length;i++){
					if(filedStr == null){
						filedStr = record.items[i].data.key_name;
					}else{
						filedStr = filedStr+","+record.items[i].data.key_name;
					}
				}
				var url = '../xtDbStructureController/delXtDbTableIndex?tableName='+xtDbTableName;
				var params = {filedStr:filedStr};
				ajaxRequest(url,xtDbTableIndexGrid,params,'正在删除中,请稍等...'); 
			}
		});
	}
}
