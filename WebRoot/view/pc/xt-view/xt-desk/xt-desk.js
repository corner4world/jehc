var loginGrid;
var loginStore;
var noticeGrid;
var noticeStore;
var loadStore;
var loadGrid;
var pivotChart = null;
Ext.onReady(function(){
	reGetWidthAndHeight();
	initLoginGrid();
	initNoticeGrid();
	initloadinfo();
    Ext.create('Ext.Panel',{
	    renderTo:Ext.getBody(),
	    autoScroll:true,
	    layout:{
	        type:'table',
	        columns:2
	    },
	    defaults:{frame:true,width:clientWidth*0.2,style:'margin-right:20px;margin-top:10px;'/**,style:'margin-left:auto;margin-right:10px;margin-top:10px;'**/},
	    items:[
	    	  {
	    	  title:'访问模块记录图表',
	    	  width:clientWidth*0.6,
	    	  items:[pivotChart],
	    	  /**
	    	  tools:[{
				    	type:'refresh',
				    	tooltip:'刷新',
				    	handler:function(event, toolEl, panelHeader){
				    	}
			  	  }],
			  	  **/
			  bbar:['->',{
					 	xtype:'label',
						text:'更 多...',
						listeners:{
			            	click:{
					            element:'el',
					            fn:function(){
					            }
					        },
		         	 	scope:this
		        	   }
				 }]
			  },
	          {
	           title:'平台公告',
	           width:clientWidth*0.25,
	           items:noticeGrid,
	           /**
	           tools:[{
				    	type:'refresh',
				    	tooltip:'刷新',
				    	handler:function(event, toolEl, panelHeader){
				    	}
			  	  }],
			  	  */
			   bbar:['->',{
					 	xtype:'label',
						text:'更 多...',
						listeners:{
			            	click:{
					            element:'el',
					            fn:function(){
					            }
					        },
		         	 	scope:this
		        	   }
				 }]
	          },
	          {
	           title:'访问模块记录',
	           width:clientWidth*0.6,
	           items:loadGrid,
	           /**
	           tools:[{
				    	type:'refresh',
				    	tooltip:'刷新',
				    	handler:function(event, toolEl, panelHeader){
				    	}
			  	  }],
			  	  **/
			   bbar:['->',{
					 	xtype:'label',
						text:'更 多...',
						listeners:{
			            	click:{
					            element:'el',
					            fn:function(){
					            }
					        },
		         	 	scope:this
		        	   }
				 }]
	          },
	          {
	          title:'登录日志',
	          items:loginGrid,
	          width:clientWidth*0.25,
	          /**
			  tools:[{
			    	type:'refresh',
			    	tooltip:'刷新',
			    	handler:function(event, toolEl, panelHeader){
			    	}
		  	  }],**/
			  bbar:['->',{
					 	xtype:'label',
						text:'更 多...',
						listeners:{
			            	click:{
					            element:'el',
					            fn:function(){
					            }
					        },
		         	 	scope:this
		        	   }
				 }]
	          }	                 
	    ]
	});
});

/**初始化登录信息**/
function initLoginGrid(){
	loginStore = getGridJsonStore('../xtLoginLogsController/getXtLoginLogsListByCondition?flag=1&limit=11',[]);
	loginGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		height:clientHeight*0.4,
		store:loginStore,
		hideHeaders:true,
		viewConfig:{
			emptyText:'暂无数据',
			rowLines:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'IP',
				align:'left',
				dataIndex:'xt_login_logIP'
			},
			{
				header:'登录时间',
				flex:1,
				align:'right',
				dataIndex:'xt_login_logtime'
			}
		]
	});
}

function initNoticeGrid(){
	noticeStore = getGridJsonStore('../xtNoticeController/getXtNoticeListByCondition',[]);
	noticeGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:noticeStore,
		height:clientHeight*0.6,
		hideHeaders:true,
		viewConfig:{
			emptyText:'暂无数据',
			rowLines:true
		},
		loadMask:{
			msg:'正在加载...'
		},
		columns:[
			{
				header:'标题',
				align:'left',
				dataIndex:'xt_title'
			},
			{
				header:'创建时间',
				flex:1,
				align:'right',
				dataIndex:'xt_createTime'
			}
		]
	});
}

function initloadinfo(){
	reGetWidthAndHeight();
	var xt_column_store;
	loadStore = getGridJsonStore('../xtLoadinfoController/getXtLoadinfoListByCondition',[]);
	loadGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:loadStore,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:false,
		height:clientHeight*0.4,
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
			{
				header:'载加模块',
				flex:1,
				dataIndex:'xt_loadinfo_modules'
			},
			{
				header:'耗时(毫秒)',
				flex:1,
				dataIndex:'xt_loadinfo_time'
			}/**,
			
			{
				header:'页面载入时间',
				flex:1,
				dataIndex:'xt_loadinfo_begtime'
			},
			{
				header:'载入结束时间',
				flex:1,
				dataIndex:'xt_loadinfo_endtime'
			}
			**/
		]
	});
	//////////////////图表/////////////////////
	Ext.require('Ext.chart.*');
	Ext.require(['Ext.Window', 'Ext.fx.target.Sprite','Ext.layout.container.Fit', 'Ext.window.MessageBox']);
    xt_column_store = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtLoadinfoController/getXtLoadingGroupList',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['menuTitle', 'loadingTime'],
		autoLoad:true 
	});
    pivotChart = Ext.create('Ext.chart.Chart',{
	    xtype:'chart',
	    region:'center',
        height:clientHeight*0.6,
        width:clientWidth*0.6,
        style:'background:#fff',
        padding:'10 0 0 0',
        insetPadding:10,
        animate:true,
        shadow:false,
        store:xt_column_store,
        axes:[{
            type:'Numeric',
            position:'left',
            fields:['loadingTime'],
            label:{
                renderer:function(v){return v + 'ms';}
            },
            grid:true,
            minimum:0
        },{
            type:'Category',
            position:'bottom',
            fields:['menuTitle'],
            grid:true,
            label:{
                rotate:{
                    degrees:90
                }
            }
        }],
        series:[{
            type:'column',
            axis:'left',
            xField:'menuTitle',
            yField:'loadingTime',//x与y轴的数据声明
	        //此渲染器的存在能够使每条柱子的颜色，与上方声明的颜色数组相同
	        renderer: function(sprite, storeItem, barAttr, i, store) {  
	            barAttr.fill = randomColor();
	            return barAttr;  
	        },
            style:{
                opacity:0.90
            },
            highlight:{
                fill:'#000',
                'stroke-width':10,
                stroke:'#fff'
            },
            tips:{
                trackMouse:true,
                style:'background: #FFF',
                height:20,
                renderer:function(storeItem,item){
                    this.setTitle(storeItem.get('menuTitle') + ': ' + storeItem.get('loadingTime') + 'ms');
                }
            }
        }]
    });
}