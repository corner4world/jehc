var xtUserinfoWin;
var xtUserinfoGrid;
var xtUserinfoStore;
var xtUserinfoGrided;
var xtUserinfoSotreed;
var xtDpPanel;
var xtDpStore;
function showUserWin(xt_menuinfo_id,xt_menuinfo_title){
	reGetWidthAndHeight();
	initXtUserinfoGrid(xt_menuinfo_id,xt_menuinfo_title);
	initXtDpPanel(xt_menuinfo_id);
	xtUserinfoWin = Ext.create('top.Ext.Window',{
		layout:'border', 
		width:clientWidth,                    
		height:clientHeight, 
		maximizable:true,
		minimizable:true,
		maximized:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		frame:true,
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:[xtDpPanel,xtUserinfoGrid]
	});
	xtUserinfoWin.setTitle("数据权限--->按人员设置数据权限--->"+xt_menuinfo_title);
	xtUserinfoWin.show();
}


/**用户显示**/
function initXtUserinfoGrid(xt_menuinfo_id,xt_menuinfo_title){
	reGetWidthAndHeight();
	xtUserinfoStore = getGridJsonStore('../xtDataAuthorityController/getUserinfoListByCondition?&xt_menuinfo_id='+xt_menuinfo_id,[]);
	xtUserinfoGrid = Ext.create('top.Ext.grid.Panel',{
		region:'center',
		store:xtUserinfoStore,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:false,
		autoScroll:true,
		selType:'checkboxmodel',
		title:'拥有者列表',
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
				header:'是否已婚',
				dataIndex:'xt_userinfo_ismarried',
				renderer:function(value){
					if(value == 0){
						return '已婚'
					}else{
						return '未婚'
					}
				}
			},
			{
				header:'操作',
				flex:1,
				columns:[{
					header:'设置该用户拥有其他人员的权限',
					align:'center',
					xtype:'widgetcolumn',
					width:240,
					widget:{
		                xtype:'button',
		                icon:editIcon,
		                text:'设置该用户拥有其他人员的权限',
		                handler:function(rec){
		                	var xt_userinfo_id = rec.getWidgetRecord().data.xt_userinfo_id;
		                	var xt_userinfo_realName = rec.getWidgetRecord().data.xt_userinfo_realName;
		                	showUserSelectWin(xt_userinfo_realName,xt_userinfo_id,xt_menuinfo_id,xt_menuinfo_title)
		                }
		            }
				},{
					header:'查看该用户拥有其他人员的权限',
					align:'center',
					xtype:'widgetcolumn',
					width:240,
					widget:{
		                xtype:'button',
		                icon:detailIcon,
		                text:'查看该用户拥有其他人员的权限',
		                handler:function(rec){
		                	var xt_userinfo_id = rec.getWidgetRecord().data.xt_userinfo_id;
		                }
		            }
				}]
			}
		],
		bbar:getGridTopBBar(xtUserinfoStore)
	});
}
function initXtDpPanel(xt_menuinfo_id){
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
	xtDpPanel = Ext.create('top.Ext.tree.Panel',{   
		region:'west',
        store:xtDpStore,  
        autoEncode:true,//提交时是否自动编码   
        rootVisible:false,  
        width:200,
        collapsible:true,
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
            	menuClick(optd,xt_menuinfo_id);
            }  
        }
    });  
}
//菜单点击事件
function menuClick(node,xt_menuinfo_id){
 	var id = node.data.id;
 	var type = node.data.type;
 	var parm = {id:id,type:encodeURI(type)};
 	load(xtUserinfoGrid,parm);
}