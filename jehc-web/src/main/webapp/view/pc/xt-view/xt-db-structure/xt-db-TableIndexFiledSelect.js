//////选择字段////////////////////
var xtDbTableIndexFiledGrid;
var xtDbTableIndexFiledStore;
var xtDbTableIndexFiledWin;
function getXtDbTableIndexFiledByCondition(xtDbTableName){
	reGetWidthAndHeight();
	initXtDbTableIndexFiledGrid(xtDbTableName);
	xtDbTableIndexFiledWin = new Ext.Window({
		layout:'fit',                    
		width:600,      
		height:350,
		modal:true,               
		plain:true,    
		closeAction:'close',                      
		title:'可供选择字段',
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
		items:xtDbTableIndexFiledGrid
	});
	xtDbTableIndexFiledWin.show();
}

function initXtDbTableIndexFiledGrid(xtDbTableName){
	xtDbIndexFiledStore = getGridJsonStore('../xtDbStructureController/getXtDbStructureByCondition?tableName='+xtDbTableName,[]);
	xtDbTableIndexFiledGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
	    store:xtDbIndexFiledStore,
		trackMouseOver:true,
		autoScroll:true,
		viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		loadMask:{
			msg:'正在加载...'
		},
	    columns:[
		    {header:'字段',width:125,dataIndex:'field',sortable:true},
			{header:'备注',flex:1,dataIndex:'comment',sortable:true}
	    ],
	    tbar:[{
			text:'保 存',
			tooltip:'保 存',
			scope:this,
			icon:saveIcon,
			handler:function(){
						doSelectFiled();
					}
			}]
	});
}

//保存选择的字段
function doSelectFiled(){
	var filedStr = null;
	var record = xtDbTableIndexFiledGrid.getSelectionModel().selected;
	for(var i = 0; i < record.length;i++){
		if(filedStr == null){
			filedStr = record.items[i].data.field;
		}else{
			filedStr = filedStr+","+record.items[i].data.field;
		}
	}
	Ext.getCmp("field").setValue(filedStr);
	xtDbTableIndexFiledWin.close();
}