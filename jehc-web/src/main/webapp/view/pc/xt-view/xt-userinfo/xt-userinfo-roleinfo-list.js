var storeXtUserinfoRoleInfo;
var gridXtUserinfoRoleInfo;
var winXtUserinfoRoleInfo;
function initRoleinfo(xt_userinfo_id){
	storeXtUserinfoRoleInfo = getGridJsonStore('../xtUserinfoController/getXtRoleinfoListByUserinfoId?xt_userinfo_id='+xt_userinfo_id,[]);
	gridXtUserinfoRoleInfo = Ext.create('Ext.grid.Panel',{
		store:storeXtUserinfoRoleInfo,
		style:'margin-left:auto;margin-right:auto',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:false,
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
			}
		],
		tbar:[
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
						exportXtRoleinfo(gridXtUserinfoRoleInfo,'../xtRoleinfoController/exportXtRoleinfo');
					}
				 },
				 {
					text:'打 印',
					tooltip:'打 印',
					scope:this,
					glyph:0xf02f,
					handler:function(){
						printerGrid(gridXtUserinfoRoleInfo);
					}
				 },
				 '-',
				 {
					text:'全 选',
					tooltip:'全 选',
					glyph:0xf046,
					handler:function(){selectAll(gridXtUserinfoRoleInfo);}
				 },
				 {
					text:'反 选',
					tooltip:'反 选',
					glyph:0xf096,
					handler:function(){unSelectAll(gridXtUserinfoRoleInfo);}
				 },
				 '-',
				 {
					text:'刷 新',
					tooltip:'刷 新',
					scope:this,
					glyph:0xf021,
					handler:function(){
						gridXtUserinfoRoleInfo.getStore().reload();
					}
				 }
				 ]
			 }
		],
		bbar:getGridTopBBar(storeXtUserinfoRoleInfo),
		listeners:{
			'rowdblclick':function(gridXtUserinfoRoleInfo, rowIndex, e){
			}
		}
	});
	reGetWidthAndHeight();
	winXtUserinfoRoleInfo = Ext.create('Ext.Window',{
		layout:'fit',
		title:'角色权限列表',
		width:clientWidth*0.8,                    
		height:clientHeight*0.8, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		plain:true,
		modal:true,
		items:gridXtUserinfoRoleInfo,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	winXtUserinfoRoleInfo.show();
}