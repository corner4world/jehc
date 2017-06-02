var store;
var grid;
var pivotChart = null;
var xt_column_store;
Ext.onReady(function(){
	store = getGridJsonStore('../xtLoadinfoController/getXtLoadinfoListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		border:true,
		selType:'checkboxmodel',
		title:'查询结果',
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
			},
			{
				header:'页面载入时间',
				flex:1,
				dataIndex:'xt_loadinfo_begtime'
			},
			{
				header:'载入结束时间',
				flex:1,
				dataIndex:'xt_loadinfo_endtime'
			},
			{
				header:'操作人',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			}
		],
		tbar:[
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtLoadinfo();
				}
			 },
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtLoadinfo();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtLoadinfo(grid,'../xtLoadinfoController/exportXtLoadinfo');
					}
				 },
				 {
					text:'打 印',
					tooltip:'打 印',
					glyph:0xf02f,
					handler:function(){
						printerGrid(grid);
					}
				 },
				 '-',
				 {
					text:'全 选',
					tooltip:'全 选',
					glyph:0xf046,
					handler:function(){selectAll(grid);}
				 },
				 {
					text:'反 选',
					tooltip:'反 选',
					glyph:0xf096,
					handler:function(){unSelectAll(grid);}
				 },
				 '-',
				 {
					text:'刷 新',
					tooltip:'刷 新',
					glyph:0xf021,
					handler:function(){
						grid.getStore().reload();
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailXtLoadinfo();
			}
		}
	});
	initChart();
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[grid,pivotChart]
	});
	/**调用右键**/
	initRight();
});
/**删除操作**/
function delXtLoadinfo(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_loadinfo_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_loadinfo_id){
			xt_loadinfo_id=model.selected.items[i].data.xt_loadinfo_id;
		}else{
			xt_loadinfo_id=xt_loadinfo_id+","+model.selected.items[i].data.xt_loadinfo_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_loadinfo_id:xt_loadinfo_id};
			ajaxRequestCallFn('../xtLoadinfoController/delXtLoadinfo',grid,params,'正在执行删除操作中！请稍后...',fnRefresh);
		}
	});
}
function fnRefresh(){
	xt_column_store.reload();
}
/**复制一行并生成记录**/
function copyXtLoadinfo(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_loadinfo_id:record.items[0].data.xt_loadinfo_id};
			ajaxRequest('../xtLoadinfoController/copyXtLoadinfo',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtLoadinfo(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'删 除',
			glyph:0xf014,
			id:'delXtLoadinfoItem',
			handler:function(){delXtLoadinfo();}
		},'-',
		{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtLoadinfoItem',
			handler:function(){detailXtLoadinfo();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtLoadinfo(grid,'../xtLoadinfoController/exportXtLoadinfo');
			}
		},{
			text:'打 印',
			glyph:0xf02f,
			handler:function(){printerGrid(grid);}
		},'-',{
			text:'全 选',
			glyph:0xf046,
			handler:function(){selectAll(grid);}
		},{
			text:'反 选',
			glyph:0xf096,
			handler:function(){unSelectAll(grid);}
		},'-',{
			text:'刷 新',
			glyph:0xf021,
			handler:function(){refreshGrid(grid);}
		}]
	});
	initrightgridcontextmenu(grid,contextmenu,['delXtLoadinfoItem','detailXtLoadinfoItem']);
}


function initChart(){
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
	    region:'south',
        height:235,
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
