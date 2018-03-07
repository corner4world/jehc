var xtUserinfoWin;
var xtUserinfoGrid;
var xtUserinfoStore;
var xtUserinfoGrided;
var xtUserinfoSotreed;
var xtDpPanel;
var xtDpStore;
function addXtUserinfo(xt_role_id,xt_role_name){
	initXtUserinfoGrid(xt_role_id);
	initXtUserinfoGrided(xt_role_id);
	initXtDpPanel(xt_role_id);
	reGetWidthAndHeight();
	xtUserinfoWin = Ext.create('Ext.Window',{
		layout:'border', 
		maximizable:true,
		minimizable:true,
//		maximized:true,
		plain:true,
		modal:true,
		width:clientWidth,                    
		height:clientHeight, 
		headerPosition:'left',
		title:'分配用户',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:[xtDpPanel,
			  {
			  	region:'center',
			  	/**bodyStyle:'overflow-x:hidden; overflow-y:scroll',**/
			  	items:[
			  	{
			  		items:xtUserinfoGrid
			  	},
			  	{
			  		items:xtUserinfoGrided
			  	}
			  	]
			  }
			  ]/**,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				var items = Ext.ComponentQuery.query('button',xtUserinfoWin); 
				console.info(items);
				return;
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]**/
	});
	xtUserinfoWin.setTitle("角色权限--->导入用户--->"+xt_role_name);
	xtUserinfoWin.show();
}


/**待导入用户**/
function initXtUserinfoGrid(xt_role_id){
	reGetWidthAndHeight();
	xtUserinfoStore = getGridJsonStore('../xtRoleinfoController/getUserinfoListByCondition?flag=2&xt_role_id='+xt_role_id,[]);
	xtUserinfoGrid = Ext.create('Ext.grid.Panel',{
		region:'north',
		store:xtUserinfoStore,
		height:document.documentElement.clientHeight*0.5,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:false,
		autoScroll:true,
		border:false,
		selType:'checkboxmodel',
		title:'待导入用户',
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
				flex:1,
				dataIndex:'xt_userinfo_name'
			},
			{
				header:'真实姓名',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'性别',
				dataIndex:'xt_userinfo_sex',
				renderer:function(value){
					if(value == 0){
						return '男'
					}else{
						return '女'
					}
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
				header:'岗位',
				dataIndex:'xt_post_name',
				renderer:function(value){
					if(value == ''){
						return '—'
					}else{
						return value;
					}
				}
			},
			{
				header:'部门',
				flex:1,
				dataIndex:'xt_departinfo_name',
				renderer:function(value){
					if(value == ''){
						return '—'
					}else{
						return value;
					}
				}
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
			}/**,
			{
				header:'岗位',
				dataIndex:'xt_post_name'
			},
			{
				header:'部门',
				dataIndex:'xt_departinfo_name'
			}**/
		],
		tbar:[
			 {
				text:'导入用户',
				tooltip:'导入用户',
				scope:this,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addXtUr(xt_role_id);
				}
			 }
		],
		bbar:getGridTopBBar(xtUserinfoStore)
	});
}


/**已导入用户**/
function initXtUserinfoGrided(xt_role_id){
	reGetWidthAndHeight();
	xtUserinfoStoreed = getGridJsonStore('../xtRoleinfoController/getUserinfoListByCondition?flag=1&xt_role_id='+xt_role_id,[]);
	xtUserinfoGrided = Ext.create('Ext.grid.Panel',{
		region:'center',
		store:xtUserinfoStoreed,
		height:document.documentElement.clientHeight*0.4,
		style:'margin-left:auto;margin-right:auto;margin-bottom:auto;',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		autoScroll:true,
		border:true,
		selType:'checkboxmodel',
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
		title:'已导入用户',
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
				flex:1,
				dataIndex:'xt_userinfo_name'
			},
			{
				header:'真实姓名',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'性别',
				dataIndex:'xt_userinfo_sex',
				renderer:function(value){
					if(value == 0){
						return '男'
					}else{
						return '女'
					}
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
				header:'岗位',
				dataIndex:'xt_post_name',
				renderer:function(value){
					if(value == ''){
						return '—'
					}else{
						return value;
					}
				}
			},
			{
				header:'部门',
				flex:1,
				dataIndex:'xt_departinfo_name',
				renderer:function(value){
					if(value == ''){
						return '—'
					}else{
						return value;
					}
				}
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
			}/**,
			{
				header:'籍贯',
				dataIndex:'xt_userinfo_origo'
			},
			{
				header:'岗位',
				dataIndex:'xt_post_name'
			},
			{
				header:'部门',
				dataIndex:'xt_departinfo_name'
			}**/
		],
		tbar:[
			 {
				text:'移除用户',
				tooltip:'移除用户',
				scope:this,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtUR(xt_role_id);
				}
			 }
		],
		bbar:getGridTopBBar(xtUserinfoStoreed)
	});
}


function initXtDpPanel(xt_role_id){
	//1创建store
	xtDpStore = Ext.create('Ext.data.TreeStore', {  
	     root:{  
              expanded:true  
         },
         proxy:{  
                type:'ajax',  
                url:'../xtCommonController/getXtOrgTree',  
                reader:{  
                    type:'json',  
                    rootProperty:'items'  
                },
				extraParams:{id:'0',type:encodeURI('部门')}  
         }  
	});  
	//2创建treePanel
	xtDpPanel = Ext.create('Ext.tree.Panel',{   
		region:'west',
        store:xtDpStore,  
        autoEncode:true,//提交时是否自动编码   
        rootVisible:false,  
        width:200,
        collapsible:false,
        title:'组织机构',
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        listeners:{  
            beforeitemexpand:function(node,optd){  
            	//展开节点之前触发  
                var id=node.data.id;  
                var type=node.data.type; 
                xtDpStore.setProxy({   
                	//异步从服务器上加载数据  extjs会自动帮我们解析  
                    type:'ajax',  
                    url:'../xtCommonController/getXtOrgTree',  
                    extraParams:{id:id,type:encodeURI(type)}
                 });  
            },
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            	menuClick(optd,xt_role_id);
            }  
        }
    });  
}

/**导入用户**/
function addXtUr(xt_role_id){
	var model = xtUserinfoGrid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择待导入用户');
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
	Ext.Msg.confirm('提示','确定导入所选用户？',function(btn){
		if(btn == 'yes'){
			var params = {xt_userinfo_id:xt_userinfo_id,xt_role_id:xt_role_id};
			showWaitMsg("正在导入中...");
			Ext.Ajax.request({  
				params:params, 
			    url:'../xtRoleinfoController/addXtUR',  
			    method:'post',  
			    success:function(response,opts){
			    	hideWaitMsg();
			    	var obj=Ext.decode(response.responseText);  
	       			Ext.example.msg('提示', obj.msg);//提示之后消失
					xtUserinfoGrid.getStore().reload();
					xtUserinfoGrided.getStore().reload();
			    },  
			    failure:function(response,opts){  
			    	hideWaitMsg();
			    }  
			});
		}
	});
}


/**移除用户**/
function delXtUR(xt_role_id){
	var model = xtUserinfoGrided.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择要移除的用户');
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
	Ext.Msg.confirm('提示','确定移除所选用户？',function(btn){
		if(btn == 'yes'){
			var params = {xt_userinfo_id:xt_userinfo_id,xt_role_id:xt_role_id};
			showWaitMsg("正在移除用户中...");
			Ext.Ajax.request({  
				params:params, 
			    url:'../xtRoleinfoController/delXtUR',  
			    method:'post',  
			    success:function(response,opts){
			    	hideWaitMsg();
			    	var obj=Ext.decode(response.responseText);  
	       			Ext.example.msg('提示', obj.msg);//提示之后消失
					xtUserinfoGrid.getStore().reload();
					xtUserinfoGrided.getStore().reload();
			    },  
			    failure:function(response,opts){  
			    	hideWaitMsg();
			    }  
			});
		}
	});
}



//菜单点击事件
function menuClick(node,xt_role_id){
 	var id = node.data.id;
 	var type = node.data.type;
 	var parm = {id:id,type:encodeURI(type)};
 	load(xtUserinfoGrid,parm);
 	load(xtUserinfoGrided,parm);
}