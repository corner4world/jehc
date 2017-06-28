var flexform;
var accordionPanel;
/**表**/
var xtTablePanel;
var xtTableStore;
/**视图**/
var xtViewPanel;
var xtViewStore;
/**存储过程**/
var xtProPanel;
var xtProStore;
/**函数**/
var xtFunPanel;
var xtFunStore;
/**触发器**/
var xtTriPanel;
var xtTriStore;
var ue;
function initAccordionPanel(){
	initXtTablePanel();
	initXtViewPanel();
	initXtProPanel();
	initXtFunPanel();
	initXtTriPanel();
	accordionPanel = Ext.create('Ext.panel.Panel',{  
        region:'west',  
        title:'索引目录',  
		width:200,
		split:false,
		border:true,
		collapsible:true,
		titleCollapse:true,
		hideCollapseTool:true,
		floatable:false,
		/**collapsed:true,**/
		xtype:'panel',
        layout:{
			type:'accordion',
			titleCollapse:false,
			animate:true,
			hideCollapseTool:true,
			activeOnTop:true
		},
		header:{
              titleAlign:'left'
        },
		items:[{
			header:{
                 titleAlign:'left'
            },
			title:'数据库表',
            id:'leftPanel',
            bodyStyle:'overflow-x:hidden; overflow-y:scroll',
			items:xtTablePanel
		},{
			header:{
                 titleAlign:'left'
            },
            bodyStyle:'overflow-x:hidden; overflow-y:scroll',
			title:'视&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;图',
			items:xtViewPanel
		},{
			header:{
                 titleAlign:'left'
            },
            bodyStyle:'overflow-x:hidden; overflow-y:scroll',
			title:'存储过程',
			items:xtProPanel
		},{
			header:{
                 titleAlign:'left'
            },
            bodyStyle:'overflow-x:hidden; overflow-y:scroll',
			title:'函&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;数',
			items:xtFunPanel
		},{
			header:{
                 titleAlign:'left'
            },
            bodyStyle:'overflow-x:hidden; overflow-y:scroll',
			title:'触&nbsp;&nbsp;发&nbsp;器',
			items:xtTriPanel
		},{
			header:{
                 titleAlign:'left'
            },
            bodyStyle:'overflow-x:hidden; overflow-y:scroll',
			title:'事&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件',
			items:xtFunPanel
		}]
    });
}
Ext.onReady(function(){
//	initAccordionPanel();
	reGetWidthAndHeight();
	flexform = Ext.create('Ext.FormPanel',{
		region:'north',
		autoScroll:false,
		title:'执行语句',
		tools:[
			 {
	            name:"sqlType",
	            hiddenName:'sqlType',
	            xtype:"combo",
	            hideTrigger:true,
	            store:[["0","自动解析"],["1","查询"],["2","非结果集"]],
	            emptyText:"请选择",
	            mode:"local",
	            value:'0',
	            width:80,
	            triggerAction:"all",
	            editable:false
			},{
	            xtype:"button",
	            text:'切换数据库源',
	            editable:false,
	            handler:function(){
	            	Ext.Msg.confirm('提示','确定要切换数据源？',function(btn){
						if(btn == 'yes'){
							window.location.href="../xtFlexSearchController/loadXtFlexSearchForSelDb";
						}
					});
				}
			}
		],
		tbar:[
			{
				text:'立即执行',
				tooltip:'立即执行',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				handler:function(){
					flexQuery();
				}
			 }
		],
		html:'<iframe scrolling="auto" id="sqlEditor" frameborder="0" width="100%" height="100%" src="../xtFlexSearchController/loadXtFlexSqlEditor"></iframe>',
		items:[{
			xtype:'textarea',
			name:'sql',
			hidden:true,
			id:'sql', 
			style:'font-size:18px;',
			allowBlank:false,
			grow:true,
			growMin:(clientHeight*0.5),
			growMax:(clientHeight*0.5),
			anchor:'100%'
		}]
	});
	var grid = Ext.create('Ext.grid.Panel',{
		autoScroll:true,
		title:'查询结果',
		id:'configGrid',  
		region:'center',  
        columns:[],  
        split:false,
        height:(clientHeight*0.5),  
        columnLines:true,
        collapsible:false,
        border:true,
        viewConfig:{
			emptyText:'暂无数据',
			stripeRows:true,
			enableTextSelection:true//可以复制单元格文字
		},
		listeners:{
			'rowdblclick':function(grids,rowIndex,e){
				var record = grid.getSelectionModel().selected.items[0].data;
				backPanel(record);
			}
		},
        displayInfo:true,    
        items:[]  
	});
	flexpanel = Ext.create('Ext.Panel',{
		region:'center',
		layout:'border',
		autoScroll:false,
		items:[flexform,grid]
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[/*accordionPanel,*/flexpanel]
	});
});

function flexQuery(){
	var val = $("#sqlEditor")[0].contentWindow.setTextareaValue();
	sValue('sql',val);
	if(flexform.form.isValid()){   
	   	 flexform.form.submit({                    
	        url:'../xtFlexSearchController/flexQuery',
	        params:{ajaxform:0,xt_dbinfo_id:xt_dbinfo_id},
	        waitTitle:'提示',
	        timeout:6000000,/**设置超时时间100分钟**/
	        waitMsg:'正在执行SQL语句操作，请稍后...',                      
	        success:function(form, action){  
	        	var json = action.result;//获得后台传递json    
	            var store = Ext.create('Ext.data.Store',{    
		            fields:json.fieldsNames,//把json的fieldsNames赋给fields    
		            data:json.data          //把json的data赋给data    
	            });   
	            //Ext.getCmp("configGrid").expand();
	            Ext.getCmp("configGrid").reconfigure(store,json.columModle);//定义grid的store和column    
	            //Ext.getCmp("configGrid").render();    
	      	},
	        failure:function(form, action){
	      	}                  
	      });            
	  }else{ 
		 	msgTishi('请输入要执行的操作语句');//提示之后消失
	  }
}

/**表**/
function initXtTablePanel(){
	//1创建store
	xtTableStore = Ext.create('Ext.data.TreeStore', {  
	     root:{  
              expanded:true  
         },
         proxy:{  
                type:'ajax',  
                url:'../xtFlexSearchController/getXtFlexSearchTablesTree',  
                reader:{  
                    type:'json',  
                    rootProperty:'items'  
                },
				extraParams:{id:'0',xt_dbinfo_id:xt_dbinfo_id}  
         }  
	});  
	//2创建treePanel
	xtTablePanel = Ext.create('Ext.tree.Panel',{   
        store:xtTableStore,  
        autoEncode:true,//提交时是否自动编码   
        rootVisible:false,  
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
        bodyStyle:{    
            /**padding:'10px'**/  
        },   
        border:false,
        frame:true,
        listeners:{  
            beforeitemexpand:function(node,optd){  
            	//展开节点之前触发  
                var id=node.data.id;   
                xtTableStore.setProxy({   
                	//异步从服务器上加载数据  extjs会自动帮我们解析  
                    type:'ajax',  
                    url:'../xtFlexSearchController/getXtFlexSearchTablesTree',  
                    extraParams:{id:id,xt_dbinfo_id:xt_dbinfo_id}
                 });  
            },
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            }  
        }
    });  
}


/**视图**/
function initXtViewPanel(){
	//1创建store
	xtViewStore = Ext.create('Ext.data.TreeStore', {  
	     root:{  
              expanded:true  
         },
         proxy:{  
                type:'ajax',  
                url:'../xtFlexSearchController/getXtFlexSearchViewTree',  
                reader:{  
                    type:'json',  
                    rootProperty:'items'  
                },
				extraParams:{id:'0',xt_dbinfo_id:xt_dbinfo_id}  
         }  
	});  
	//2创建treePanel
	xtViewPanel = Ext.create('Ext.tree.Panel',{   
        store:xtViewStore,  
        autoEncode:true,//提交时是否自动编码   
        rootVisible:false,  
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
        bodyStyle:{    
            /**padding:'10px'**/  
        },   
        border:false,
        frame:true,
        listeners:{  
            beforeitemexpand:function(node,optd){  
            	//展开节点之前触发  
                var id=node.data.id;   
                xtViewStore.setProxy({   
                	//异步从服务器上加载数据  extjs会自动帮我们解析  
                    type:'ajax',  
                    url:'../xtFlexSearchController/getXtFlexSearchViewTree',  
                    extraParams:{id:id,xt_dbinfo_id:xt_dbinfo_id}
                 });  
            },
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            }  
        }
    });  
}



/**存储过程**/
function initXtProPanel(){
	//1创建store
	xtProStore = Ext.create('Ext.data.TreeStore', {  
	     root:{  
              expanded:true  
         },
         proxy:{  
                type:'ajax',  
                url:'../xtFlexSearchController/getXtFlexSearchProTree',  
                reader:{  
                    type:'json',  
                    rootProperty:'items'  
                },
				extraParams:{id:'0',xt_dbinfo_id:xt_dbinfo_id}  
         }  
	});  
	//2创建treePanel
	xtProPanel = Ext.create('Ext.tree.Panel',{   
        store:xtProStore,  
        autoEncode:true,//提交时是否自动编码   
        rootVisible:false,  
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
        bodyStyle:{    
            /**padding:'10px'**/  
        },   
        border:false,
        frame:true,
        listeners:{  
            beforeitemexpand:function(node,optd){  
            	//展开节点之前触发  
                var id=node.data.id;   
                xtProStore.setProxy({   
                	//异步从服务器上加载数据  extjs会自动帮我们解析  
                    type:'ajax',  
                    url:'../xtFlexSearchController/getXtFlexSearchProTree',  
                    extraParams:{id:id,xt_dbinfo_id:xt_dbinfo_id}
                 });  
            },
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            }  
        }
    });  
}





/**函数**/
function initXtFunPanel(){
	//1创建store
	xtFunStore = Ext.create('Ext.data.TreeStore', {  
	     root:{  
              expanded:true  
         },
         proxy:{  
                type:'ajax',  
                url:'../xtFlexSearchController/getXtFlexSearchFunTree',  
                reader:{  
                    type:'json',  
                    rootProperty:'items'  
                },
				extraParams:{id:'0',xt_dbinfo_id:xt_dbinfo_id}  
         }  
	});  
	//2创建treePanel
	xtFunPanel = Ext.create('Ext.tree.Panel',{   
        store:xtFunStore,  
        autoEncode:true,//提交时是否自动编码   
        rootVisible:false,  
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
        bodyStyle:{    
            /**padding:'10px'**/  
        },   
        border:false,
        frame:true,
        listeners:{  
            beforeitemexpand:function(node,optd){  
            	//展开节点之前触发  
                var id=node.data.id;   
                xtFunStore.setProxy({   
                	//异步从服务器上加载数据  extjs会自动帮我们解析  
                    type:'ajax',  
                    url:'../xtFlexSearchController/getXtFlexSearchFunTree',  
                    extraParams:{id:id,xt_dbinfo_id:xt_dbinfo_id}
                 });  
            },
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            }  
        }
    });  
}



/**函数**/
function initXtTriPanel(){
	//1创建store
	xtTriStore = Ext.create('Ext.data.TreeStore', {  
	     root:{  
              expanded:true  
         },
         proxy:{  
                type:'ajax',  
                url:'../xtFlexSearchController/getXtFlexSearchTriTree',  
                reader:{  
                    type:'json',  
                    rootProperty:'items'  
                },
				extraParams:{id:'0',xt_dbinfo_id:xt_dbinfo_id}  
         }  
	});  
	//2创建treePanel
	xtTriPanel = Ext.create('Ext.tree.Panel',{   
        store:xtTriStore,  
        autoEncode:true,//提交时是否自动编码   
        rootVisible:false,  
        /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
        bodyStyle:{    
            /**padding:'10px'**/  
        },   
        border:false,
        frame:true,
        listeners:{  
            beforeitemexpand:function(node,optd){  
            	//展开节点之前触发  
                var id=node.data.id;   
                xtTriStore.setProxy({   
                	//异步从服务器上加载数据  extjs会自动帮我们解析  
                    type:'ajax',  
                    url:'../xtFlexSearchController/getXtFlexSearchTriTree',  
                    extraParams:{id:id,xt_dbinfo_id:xt_dbinfo_id}
                 });  
            },
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            }  
        }
    });  
}


function backPanel(record){
	var flexSearchFormDetail = Ext.create('top.Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:150,
			labelAlign:'right',
			margin:'2 5 4 5',
			anchor:'100%'
		},
		items:[
		]
	});
	for(var dataKey in record){
		var subgroup =  Ext.create('top.Ext.form.TextField',{
			fieldLabel:dataKey,
			value:record[dataKey]
		});
		flexSearchFormDetail.add(flexSearchFormDetail.items.getCount(),subgroup);
	}
	var flexSearchWinDetail = Ext.create('top.Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'明细信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:flexSearchFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	flexSearchWinDetail.show();
}