var postStore;
var postGrid;
var postWin;
function showPostWin(xt_menuinfo_id,xt_menuinfo_title){
	initPost(xt_menuinfo_id,xt_menuinfo_title);
	reGetWidthAndHeight();
	postWin = Ext.create('top.Ext.Window',{
		layout:'fit', 
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
		items:postGrid
	});
	postWin.setTitle("数据权限--->按岗位设置数据权限--->"+xt_menuinfo_title);
	postWin.show();
}
function initPost(xt_menuinfo_id,xt_menuinfo_title){
	postStore = Ext.create('Ext.data.TreeStore',{
    	root:{
			name:'一级',
			id:'0',
			expanded:true
		},
		/**此处一定要设置否则全部展开节点无效**/
		autoLoad:true,
        proxy:{
            type:'ajax',
            method:'post',
			url:'../xtDataAuthorityController/getXtPostTree',
			reader:{
				type:'json'
			},
			extraParams:{id:'0',type:encodeURI('部门')}
        },
        lazyFill:true
    });
    postGrid = Ext.create('top.Ext.tree.Panel', {
        title:'岗位结构',
        reserveScrollbar:true,
        collapsible:false,
        loadMask:true,
        useArrows:false,
        rootVisible:false,
        store:postStore,
        animate:false,
        columnLines:true,
        frame:true,
        listeners:{  
            beforeitemexpand:function(node,optd){
                var id=node.data.id; 
                var type=node.data.type; 
                postStore.setProxy({   
		       	   //异步从服务器上加载数据extjs会自动帮我们解析  
		           type:'ajax',  
		           url:'../xtDataAuthorityController/getXtPostTree',  
		           extraParams:{id:id,type:encodeURI(type)}
		        });  
            },
            /**
            beforeitemcollapse:function(node,optd){
                return false;  
            },
            **/
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            	if(leaf == true){
            		
            	}
            }
        },
        viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true
		},
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
            dataIndex:'name'
        },{
            text:'性质',
            dataIndex:'type',
            sortable:true,
            renderer:function(value){
            	if(value == '部门'){
            		return value;
            	}else{
            		return "<font color='red'>"+value+"</font>";
            	}
            }
        },{
            text:'备注',
            flex:1,
            dataIndex:'remark',
            renderer:function(value){
            	return value;
            }
        },{
        	header:'操 作',
        	columns:[{
				header:'设置该岗位拥有其它部门的权限',
				align:'center',
				xtype:'widgetcolumn',
				width:220,
				widget:{
					xtype:'button',
					icon:editIcon,
	                text:'设置该岗位拥有其它部门的权限',
	                width:220,
	                listeners:{
					    render:function(rec) {
					        var record = rec.getWidgetRecord();
					        var type = record.data.type;
					        if(type == '部门'){
					        	rec.setText("<font color='red'>不能设置</font>");
					        }else{
					        	rec.setText("<font color=''>设置该岗位拥有其它部门的权限</font>");
					        }
					    } 
					}, 
	                handler:function(rec){
	                	console.log(rec.getWidgetRecord());
	                	var id = rec.getWidgetRecord().data.id;
	                	var type = rec.getWidgetRecord().data.type;
	                	var xt_post_parentId = rec.getWidgetRecord().data.xt_post_parentId;
	                	var text = rec.getWidgetRecord().parentNode.data.name;
	                	if(type == '部门'){
	                		updateXtDepartinfo(id,xt_post_parentId,text);
	                	}else{
	                		var xt_post_parentId = rec.getWidgetRecord().data.xt_post_parentId;
	                		var xt_depart_id = rec.getWidgetRecord().data.xt_depart_id;
	                		updateXtPost(xt_depart_id,xt_post_parentId,id);
	                	}
				    }
	            }
			},{
				header:'查看该岗位拥有其它部门的权限',
				align:'center',
				xtype:'widgetcolumn',
				width:220,
				widget:{
	                xtype:'button',
	                text:'查看该岗位拥有其它部门的权限',
	                icon:detailIcon,
	                width:220,
	                listeners:{
					    render:function(rec) {
					        var record = rec.getWidgetRecord();
					        var type = record.data.type;
					        if(type == '部门'){
					        	rec.setText("<font color='red'>不能设置</font>");
					        }else{
					        	rec.setText("<font color=''>查看该岗位拥有其它部门的权限</font>");
					        }
					    } 
					},
	                handler:function(rec){
	                	var id = rec.getWidgetRecord().data.id;
	                	var type = rec.getWidgetRecord().data.type;
	                	var xt_post_id = "";
	                	var xt_departinfo_id;
	                	var xt_departinfo_name;
	                	if(type == '部门'){
	                		xt_departinfo_id = id;
	                		//一级岗位
	                		xt_departinfo_name = rec.getWidgetRecord().data.name;
	                		addXtPost(xt_departinfo_id,'0',xt_departinfo_name);
	                	}else{
	                		//下级岗位
	                		xt_departinfo_name = rec.getWidgetRecord().data.xt_departinfo_name;
	                		var xt_departinfo_id = rec.getWidgetRecord().data.xt_departinfo_id;
	                		addXtPost(xt_departinfo_id,id,xt_departinfo_name);
	                	}
	                }
	            }
			}]
        }]
    });
    postGrid.on('beforeload',function(treeloader,node) { 
   		/**
   		如果node为一个参数情况下取属性值如下:
   		var id = node.data.items[0].data.id;
           var type = node.data.items[0].data.type;
   		**/
   		/**
   		如果node为第二个参数情况下取属性值如下:
   		var id = node.node.data.id;
           var type = node.node.data.type;
   		**/
        var id = node.node.data.id;
        var type = node.node.data.type;
        postStore.setProxy({   
       	   //异步从服务器上加载数据extjs会自动帮我们解析  
           type:'ajax',  
           url:'../xtDataAuthorityController/getXtPostTree',  
           extraParams:{id:id,type:encodeURI(type)}
        });
    });
}