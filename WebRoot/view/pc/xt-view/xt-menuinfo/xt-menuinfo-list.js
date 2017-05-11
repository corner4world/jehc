Ext.require([
   	'Ext.window.Window',
    'Ext.tab.*',
    'Ext.toolbar.Spacer',
    'Ext.layout.container.Card',
    'Ext.layout.container.Border',
    'Ext.grid.*',
    'Ext.data.*',
    'Ext.util.*',
    'Ext.state.*'
]);
var store;
var grid;
Ext.onReady(function(){
   store = Ext.create('Ext.data.TreeStore',{
    	root:{
			name:'一级',
			id:'0',
			expanded:true
		},
		/**此处一定要设置否则全部展开节点无效**/
		autoLoad:false,
        proxy:{
            type:'ajax',
            method:'post',
			url:'../xtMenuinfoController/getXtMenuinfoList',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',type:encodeURI('菜单'),expanded:true}
        }
    });
    grid = Ext.create('Ext.tree.Panel', {
    	region:'center',
    	xtype:'filtered-tree',
        reserveScrollbar:true,
        collapsible:false,
        loadMask:true,
        useArrows:true,
        rootVisible:false,
        store:store,
        autoSctroll:true,
        id:'treeGrid',
        animate:true,
        columnLines:true,
        frame:true,
        bufferedRenderer:false,
        viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
		bbar:['->',
		{
		   width:260,
		   xtype:'triggerfield',
		   emptyText:'请输入关键字（如菜单、平台等）',
	       triggerCls:'x-form-clear-trigger',
	       onTriggerClick:function(){
	           this.reset();
	       },
	       listeners:{
	           change:function(){
	           	filterBy(grid,this.getValue(),'text');
	           },
	           buffer:250
	       }
		},
		{
            text:'添 加',
			tooltip:'添 加',
			xtype:'button',
			minWidth:tbarBtnMinWidth,
			cls:'addBtn',
			icon:addIcon,
            handler:function(){addXtMenuinfo();}
       	},
       	{
           text:'编 辑',
		   tooltip:'编 辑',
		   xtype:'button',
		   minWidth:tbarBtnMinWidth,
		   cls:'updateBtn',
		   icon:editIcon,
           handler:function(){updateXtMenuinfo();}
       	},
       	{
           text:'刷 新',
		   tooltip:'刷 新',
		   xtype:'button',
		   minWidth:tbarBtnMinWidth,
		   cls:'refreshBtn',
		   icon:refreshIcon,
           handler:function(){
	           	refresh();
           }
       	}],
        columns:[{
        	text:'ID',
            flex:2,
            hideable:false,
            hidden:true,
            sortable:true,
            dataIndex:'id'
        },{
            xtype:'treecolumn',
            text:'名称',
            flex:1,
            sortable:true,
            dataIndex:'text'
        },{
            text:'性质',
            dataIndex:'tempObject',
            sortable:true,
            renderer:function(value){
            	return value;
            }
        },{
            text:'数据权限',
            dataIndex:'integerappend',
            sortable:true,
            renderer:function(value){
            	var val = value.split(",");
            	if(val[0] == 0){
            		return "<font color='red'>是</font>";
            	}else if(val[0] == 1){
            		return "否";
            	}
            }
        },{
            text:'拦截类型',
            dataIndex:'integerappend',
            sortable:true,
            flex:1,
            renderer:function(value){
            	var val = value.split(",");
            	if(val[1] == 0){
            		return "无需拦截---初始化该功能无需拦截"
            	}else if(val[1] == 1){
            		return "<font color='red'>必须拦截---初始化该功能必须拦截</font>";
            	}
            }
        },{
            text:'备注',
            flex:1,
            dataIndex:'content',
            renderer:function(value){
            	return value;
            }
        }]
    });
    Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[grid]
	});
	showWaitMsg("正在加载数据...");
	refresh();
	//调用右键
	initRight();
});
/**删除操作**/
function delXtMenuinfo(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_menuinfo_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_menuinfo_id){
			xt_menuinfo_id=model.selected.items[i].data.id;
		}else{
			xt_login_log_id=xt_menuinfo_id+","+model.selected.items[i].data.id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_menuinfo_id:xt_menuinfo_id};
			ajaxRequest('../xtMenuinfoController/delXtMenuinfo',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtMenuinfo(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	if(record.items[0].data.tempObject == '功能'){
		msgTishi('功能性质不能修改！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_menuinfo_id:record.items[0].data.id};
			ajaxRequest('../xtMenuinfoController/copyXtMenuinfo',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
        id:'theContextMenu',
        items:[{
            text:'添 加',
            glyph:0xf016,
            id:'addXtMenuinfoItem',
            handler:function(){addXtMenuinfo();}
        },{
            text:'编 辑',
            glyph:0xf044,
            id:'updateXtMenuinfoItem',
            handler:function(){updateXtMenuinfo();}
        },{
            text:'复制一行并生成记录',
            glyph:0xf0ea,
            id:'copyXtMenuinfoItem',
            handler:function(){copyXtMenuinfo();}
        },{
            text:'刷 新',
            glyph:0xf021,
            handler:function(){
            	refresh();
            }
        }]
    });
    initrightgridcontextmenu(grid,contextmenu,['updateXtMenuinfoItem','copyXtMenuinfoItem']);
}
var xtMenuinfoImgWin;
function img_select(){
	var xt_menuinfo_images = Ext.getCmp('xt_menuinfo_images').getRawValue();
	xtMenuinfoImgWin = Ext.create('Ext.Window',{                   
		layout:'fit',                    
		width:800,                    
		height:400, 
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,            
		plain:true,   
		modal:true,                 
		title:'选择图标',                    
		html:'<iframe scrolling="auto" id="img_iframe" name="img_iframe" frameborder="0" width="100%" height="100%" src="../xtMenuinfoController/getImgList?xt_menuinfo_images='+xt_menuinfo_images+'"></iframe>'                  
	});
	xtMenuinfoImgWin.show(); 
}

function refresh(){
	showWaitMsg("正在加载数据...");
	/**
	new Ext.util.DelayedTask(function(){  
       
    }).delay(1000);
    **/
	store.on('load',function(){
		grid.expandAll();
	    hideWaitMsg();
    });
}