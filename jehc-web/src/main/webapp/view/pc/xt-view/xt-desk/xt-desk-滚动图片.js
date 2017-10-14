var loginGrid;
var loginStore;
var noticeGrid;
var noticeStore;
var rightPanel;
var leftPanel;
var line_spline;
var html = '<div class="scrolllist" id="s1">'+
				'<a class="abtn aleft" href="#left" title="左移"></a>'+
				'<div class="imglist_w">'+
					'<ul class="imglist">'+
						'<li>'+
							'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/1.jpg" /></a>'+
							'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
						'</li>'+
						'<li>'+
							'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/2.jpg" /></a>'+
							'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
						'</li>'+
						'<li>'+
							'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/3.jpg" /></a>'+
							'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
						'</li>'+
						'<li>'+
							'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/4.jpg" /></a>'+
							'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
						'</li>'+
						'<li>'+
							'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/5.jpg" /></a>'+
							'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
						'</li>'+
						'<li>'+
							'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/2.jpg" /></a>'+
							'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
						'</li>'+
						'<li>'+
							'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/3.jpg" /></a>'+
							'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
						'</li>'+
					'</ul><!--imglist end-->'+
				'</div>'+
				'<a class="abtn aright" href="#right" title="右移"></a>'+
			'</div><!--scrolllist end-->';
			
			
var html2='<div class="scrolllist" id="s2">'+
			'<a class="abtn aleft" href="#left" title="左移"></a>'+
			'<div class="imglist_w">'+
				'<ul class="imglist">'+
					'<li>'+
						'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/1.jpg" /></a>'+
						'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
					'</li>'+
					'<li>'+
						'<a target="_blank" href="#" title="中国风景文字内容，博大精深，华夏之楷模..."><img width="150" height="150" alt="中国风景文字内容，博大精深，华夏之楷模..." src="../deng/source/plugins/other/jquery/img/images/2.jpg"/></a>'+
						'<p><a target="_blank" href="#">中国风景文字内容，博大精深，华夏之楷模...</a></p>'+
					'</li>'+
				'</ul><!--imglist end-->'+
			'</div>'+
			'<a class="abtn aright" href="#right" title="右移"></a>'+
		'</div><!--scrolllist end-->';
Ext.onReady(function(){
	reGetWidthAndHeight();
	initLeft();
    initRight();
	Ext.create('Ext.Panel',{
	    renderTo:Ext.getBody(),
	    bodyStyle:{
		},
 		layout:"column",
		baseCls:'x-plain',
		xtype:'panel',
		frame:false,
		items:[
		      {
				columnWidth:.7,
				layout:"form",
				items:leftPanel
			   },
			   {
			    columnWidth:.3,
				layout:"form",
				items:rightPanel
			   }
			  ]
 	});
 	//默认状态下左右滚动
	$("#s1").xslider({
		unitdisplayed:4,
		movelength:1,
		unitlen:176,
		autoscroll:3000
	});
	//设置上下滚动
	$("#s2").xslider({
		unitdisplayed:4,
		movelength:1,
		dir:"V",
		unitlen:204,
		autoscroll:2000
	});
});

/**初始化登录信息**/
function initLoginGrid(){
	loginStore = getGridJsonStore('../xtLoginLogsController/getXtLoginLogsListByCondition?flag=1&limit=11',[]);
	loginGrid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
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
				flex:1,
				dataIndex:'xt_title'
			},
			{
				header:'创建时间',
				align:'right',
				flex:1,
				dataIndex:'xt_createTime'
			}
		]
	});
}

function initLeft(){
	initNoticeGrid();
	initSpline();
	leftPanel = Ext.create('Ext.Panel',{
	    defaults:{frame:true,style:'margin-right:10px;margin-bottom:10px;'/**,style:'margin-left:auto;margin-right:10px;margin-top:10px;'**/},
	    /**公司新闻模块开始**/
	    items:[
	    {
    	  title:'公司新闻',
    	  html:html,
    	  tools:[{
			    	type:'refresh',
			    	tooltip:'刷新',
			    	handler:function(event, toolEl, panelHeader){
			    	}
		  	  }],
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
		  /**公司新闻模块结束**/
          {
           title:'登陆报表',
           items:line_spline,
           tools:[{
			    	type:'refresh',
			    	tooltip:'刷新',
			    	handler:function(event, toolEl, panelHeader){
			    	}
		   		}],
		   bbar:['->',{
				 	xtype:'label',
					text:'导 出...',
					listeners:{
		            	click:{
				            element:'el',
				            fn:function(){
				            	Ext.MessageBox.confirm('提示', '确定要导出该图片吗?', function(choice){
				                    if(choice == 'yes'){
				                        line_spline.save({
				                            type:'image/png'
				                        });
				                    }
				                });
				            }
				        },
	         	 	scope:this
	        	   }
			 }]
          },
           /**平台公告模块开始**/
		  {
           title:'平台公告',
           items:noticeGrid,
           tools:[{
			    	type:'refresh',
			    	tooltip:'刷新',
			    	handler:function(event, toolEl, panelHeader){
			    		noticeGrid.getStore().reload();
			    	}
		   		}],
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
          /**平台公告模块结束**/
          {
           title:'未读邮件',
           html:'暂无信息',
           tools:[{
			    	type:'refresh',
			    	tooltip:'刷新',
			    	handler:function(event, toolEl, panelHeader){
			    	}
		   		}],
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
}

function initRight(){
	initLoginGrid();
	rightPanel = Ext.create('Ext.Panel',{
	    region:'center',
	    defaults:{frame:true,style:'margin-right:0px;margin-bottom:10px;'/**,style:'margin-left:auto;margin-right:10px;margin-top:10px;'**/},
	    items:[
	    	  /**待办事项模块开始**/
	          {
	           title:'部门新闻',
	           html:html2,
	           tools:[{
				    	type:'refresh',
				    	tooltip:'刷新',
				    	handler:function(event, toolEl, panelHeader){
				    		
				    	}
			  	  }],
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
	          /**待办事项模块结束**/
	          /**登录日志模块开始**/
	          {
	          title:'登录日志',
	          items:loginGrid,
			  tools:[{
			    	type:'refresh',
			    	tooltip:'刷新',
			    	handler:function(event, toolEl, panelHeader){
			    		loginGrid.getStore().reload();
			    	}
		  	  }],
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
	          /**登录日志模块结束**/	                 
	    ]
	});
}

function initSpline(){
	/**line-spline**/
    var line_spline_store = Ext.create('Ext.data.JsonStore', {
         fields: ['theta', 'sin' ],
         data: [
             { theta: 0, sin: 0.00 },
             { theta: 10, sin: 0.17 },
             { theta: 20, sin: 0.34 },
             { theta: 30, sin: 0.50 },
             { theta: 40, sin: 0.64 },
             { theta: 50, sin: 0.77 },
             { theta: 60, sin: 0.87 },
             { theta: 70, sin: 0.94 },
             { theta: 80, sin: 0.98 },
             { theta: 90, sin: 1.00 },
             { theta: 100, sin: 0.98 },
             { theta: 110, sin: 0.94 },
             { theta: 120, sin: 0.87 },
             { theta: 130, sin: 0.77 },
             { theta: 140, sin: 0.64 },
             { theta: 150, sin: 0.50 },
             { theta: 160, sin: 0.34 },
             { theta: 170, sin: 0.17 },
             { theta: 180, sin: 0.00 },
             { theta: 190, sin: -0.17 },
             { theta: 200, sin: -0.34 },
             { theta: 210, sin: -0.50 },
             { theta: 220, sin: -0.64 },
             { theta: 230, sin: -0.77 },
             { theta: 240, sin: -0.87 },
             { theta: 250, sin: -0.94 },
             { theta: 260, sin: -0.98 },
             { theta: 270, sin: -1.00 },
             { theta: 280, sin: -0.98 },
             { theta: 290, sin: -0.94 },
             { theta: 300, sin: -0.87 },
             { theta: 310, sin: -0.77 },
             { theta: 320, sin: -0.64 },
             { theta: 330, sin: -0.50 },
             { theta: 340, sin: -0.34 },
             { theta: 350, sin: -0.17 },
             { theta: 360, sin: 0.00 }
         ]
     });
    /**折线图**/
    line_spline = Ext.create('Ext.chart.Chart',{
         xtype:'chart',
         height:410,
         minHeight:400,
         width:800,
         minWidth:550,
         padding:'10 0 0 0',
         animate:true,
         shadow:false,
         style:'background:#fff;',
         store:line_spline_store,
         insetPadding:40,
         items:[{
             type:'text',
             text:'平台用户登录图表',
             font:'22px Helvetica',
             width:100,
             height:30,
             x:40,
             y:12
         }],
         axes:[{
             type:'numeric',
             position:'left',
             title:'登陆次数',
             grid:true,
             fields:'sin',
             label:{
                 renderer:function(v){
                     return Ext.util.Format.number(v, '0.00');
                 }
             }
         },{
             type:'numeric',
             position:'bottom',
             title:'日期',
             grid:true,
             fields:'theta',
             label:{
                 rotate:{
                     degrees:-45
                 }
             }
         }],
         series:[{
             type:'line',
             axis:'left',
             xField:'theta',
             yField:'sin',
             smooth:true,
             highlight:true,
             showMarkers:false
        }]
    });
}
