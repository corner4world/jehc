var xtUserinfoWin;
var xtUserinfoGrid;
var xtUserinfoStore;
var xtUserinfoGrided;
var xtUserinfoSotreed;
var xtDpPanel;
var xtDpStore;
function showUserWin(xt_menuinfo_id,xt_menuinfo_title){
	initXtUserinfoGrid(xt_menuinfo_id,xt_menuinfo_title);
	initXtDpPanel(xt_menuinfo_id);
	reGetTopWidthAndHeight();
	xtUserinfoWin = Ext.create('Ext.Window',{
		layout:'border', 
		width:clientWidth*0.95,                    
		height:clientHeight*0.95, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		frame:true,
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
		items:[/**xtDpPanel,**/formSearc,xtUserinfoGrid]
	});
	xtUserinfoWin.setTitle("数据权限--->按人员设置数据权限--->"+xt_menuinfo_title);
	xtUserinfoWin.show();
}


/**用户显示**/
var formSearc;
function initXtUserinfoGrid(xt_menuinfo_id,xt_menuinfo_title){
	reGetWidthAndHeight();
	xtUserinfoStore = getGridJsonStore('../xtDataAuthorityController/getUserinfoListByCondition',[]);
	/**查询区域可扩展**/
	var formItems = Ext.create('Ext.FormPanel',{
		maxHeight:220,
		waitMsgTarget:true,
		defaultType:'textfield',
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
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
			},
			{
				fieldLabel:'模块',
				xtype:'textfield',
				hidden:true,
				value:xt_menuinfo_id,
				name:'xt_menuinfo_id',
				width:220
			}
			]
		}
		]
	});
	formSearc = initSearchFormByUserdefined('north',formItems,true,'left');
	xtUserinfoGrid = Ext.create('Ext.grid.Panel',{
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
		tbar:[
		{
			text:'检 索',
			tooltip:'检索',
			minWidth:tbarBtnMinWidth,
			cls:'searchBtn',
			icon:searchIcon,
			handler:function(){
				searchDefindSelectUser();
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
		 }
		],
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
				header:'岗位',
				dataIndex:'xt_post_name'
			},
			{
				header:'部门',
				flex:1,
				dataIndex:'xt_departinfo_name'
			},
			{
				header:'操作',
				flex:1,
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
			}
		],
		bbar:getGridTopBBar(xtUserinfoStore)
	});
	xtUserinfoStore.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(xtUserinfoStore,formSearc));});
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
	xtDpPanel = Ext.create('Ext.tree.Panel',{   
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

function searchDefindSelectUser(){
	initSearch(xtUserinfoStore,'../xtDataAuthorityController/getUserinfoListByCondition',formSearc); 
}