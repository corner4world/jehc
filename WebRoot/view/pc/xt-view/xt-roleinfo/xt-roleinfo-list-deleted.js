var store_deleted;
var grid_deleted;
var win_deleted;
function initListDeleted(){
	store_deleted = getGridJsonStore('../xtRoleinfoController/getXtRoleinfoListByCondition?xt_role_isdelete=1',[]);
	grid_deleted = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store_deleted,
		title:'查询结果',
		headerPosition:'left',
		style:'margin-left:auto;margin-right:auto',
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
				header:'角色名称',
				flex:1,
				dataIndex:'xt_role_name'
			},
			{
				header:'状态',
				dataIndex:'xt_role_isdelete',
				renderer:function(value){
					if(value == 0){
						return '正在使用中';
					}else if(value == 1){
						return '已禁用';
					}else{
						return '缺省'
					}
				}
			}
		],
		tbar:[
			 {
				text:'恢复角色权限',
				tooltip:'恢复角色权限',
				scope:this,
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					recoverXtRoleinfo()
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
						exportXtRoleinfo(grid_deleted,'../xtRoleinfoController/exportXtRoleinfo');
					}
				 },
				 {
					text:'打 印',
					tooltip:'打 印',
					scope:this,
					glyph:0xf02f,
					handler:function(){
						printerGrid(grid_deleted);
					}
				 },
				 '-',
				 {
					text:'全 选',
					tooltip:'全 选',
					glyph:0xf046,
					handler:function(){selectAll(grid_deleted);}
				 },
				 {
					text:'反 选',
					tooltip:'反 选',
					glyph:0xf096,
					handler:function(){unSelectAll(grid_deleted);}
				 },
				 '-',
				 {
					text:'刷 新',
					tooltip:'刷 新',
					scope:this,
					glyph:0xf021,
					handler:function(){
						grid_deleted.getStore().reload();
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store_deleted),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
			}
		}
	});
	reGetWidthAndHeight();
	win_deleted = Ext.create('Ext.Window',{
		layout:'fit',
		title:'已被禁用的角色权限列表',
		width:clientWidth*0.8,                    
		height:clientHeight*0.8, 
		maximizable:true,
		minimizable:true,
		headerPosition:'top',
		animateTarget:document.body,
		plain:true,
		modal:true,
		items:grid_deleted,
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
	win_deleted.show();
}
/**恢复操作**/
function recoverXtRoleinfo(){
	var model = grid_deleted.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在恢复');
		return;
	}
	var xt_role_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_role_id){
			xt_role_id=model.selected.items[i].data.xt_role_id;
		}else{
			xt_role_id=xt_role_id+","+model.selected.items[i].data.xt_role_id;
		}
	}
	Ext.Msg.confirm('提示','确定恢复该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_role_id:xt_role_id};
			var gridArray = [];
			gridArray.push(grid);
			gridArray.push(grid_deleted);
			ajaxReq('../xtRoleinfoController/recoverXtRoleinfo',gridArray,params,'正在执行恢复操作中！请稍后...');
		}
	});
}

/**导出**/
function exportXtRoleinfo(grid_deleted,url){
	exportExcel(grid_deleted,url);
}
