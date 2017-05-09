var store;
var grid;
/////表字段结构 begin//////
var xtDbStructureWin;
var xtDbStructureGrid;
var xtDbStructureStore;
/////end//////////////////
/////建表语句 begin////////
var tablePhrasesWin;
var tablePhrasesForm;
/////end//////////////////
/////表属性///////////////
var xtDbTableAttributeWin;
var xtDbTableAttributeForm;
/////end//////////////////
/////表索引///////////////
var xtDbTableIndexWin;
var xtDbTableIndexGrid;
var xtDbTableIndexStore;
/////end/////////////////
/////表大小//////////////
var xtDbTableSizeWin;
var xtDbTableSizeForm;
/////end////////////////
Ext.onReady(function(){
	store = getGridJsonStore('../xtDbStructureController/getXtDbTableAttribute',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'信息列表',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
		collapsible:true,
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
				header:'表名',
				locked:true,
				dataIndex:'name'
			},
			{
				header:'引擎',
				locked:true,
				dataIndex:'engine'
			},
			{
				header:'版本',
				locked:true,
				dataIndex:'version'
			},
			{
				header:'创建时间',
				dataIndex:'create_time'
			},
			{
				header:'修改时间',
				dataIndex:'update_time'
			},
			{
				header:'备注',
				dataIndex:'comment'
			},
			{
				header:'编码格式',
				dataIndex:'collation'
			},
			{header:'row_format',dataIndex:'row_format',sortable:true,hideable:false,hidden:true},
			{header:'rows',dataIndex:'rows',sortable:true,hideable:false,hidden:true},
			{header:'avg_row_length',dataIndex:'avg_row_length',sortable:true,hideable:false,hidden:true},
			{header:'data_length',dataIndex:'data_length',sortable:true,hideable:false,hidden:true},
			{header:'max_data_length',dataIndex:'max_data_length',sortable:true,hideable:false,hidden:true},
			{header:'index_length',dataIndex:'index_length',sortable:true,hideable:false,hidden:true},
			{header:'data_free',dataIndex:'data_free',sortable:true,hideable:false,hidden:true},
			{header:'auto_increment',dataIndex:'auto_increment',hideable:false,sortable:true,hidden:true},
			{header:'check_time',dataIndex:'check_time',sortable:true,hideable:false,hidden:true},
			{header:'checksum',dataIndex:'checksum',sortable:true,hideable:false,hidden:true},
			{header:'create_options',dataIndex:'create_options',sortable:true,hideable:false,hidden:true},
			{
				header:'操作',
				flex:2,
				columns:[{
					header:'更多属性',
					align:'center',
					xtype:'widgetcolumn',
					width:100,
					widget:{
		                xtype:'button',
		                text:'更多属性',
		                handler:function(rec){
		                	getXtDbTableAttribute(rec.getWidgetRecord())
		                }
		            }
				},{
					header:'字段结构',
					align:'center',
					xtype:'widgetcolumn',
					width:100,
					widget:{
		                xtype:'button',
		                text:'字段结构',
		                handler:function(rec){
		                	var name = rec.getWidgetRecord().data.name;
		                	getXtDbStructureByCondition(name);
		                }
		            }
				},{
					header:'索引',
					align:'center',
					xtype:'widgetcolumn',
					width:100,
					widget:{
		                xtype:'button',
		                text:'索引',
		                handler:function(rec){
		                	var name = rec.getWidgetRecord().data.name;
		                	getXtDbTableIndex(name);
		                }
		            }
				},{
					header:'表大小',
					align:'center',
					xtype:'widgetcolumn',
					width:100,
					widget:{
		                xtype:'button',
		                text:'表大小',
		                handler:function(rec){
		                	var name = rec.getWidgetRecord().data.name;
		                	getXtDbTableSize(name);
		                }
		            }
				}]
			}
		],
		tbar:[
			 {
				text:'导 出',
				tooltip:'导 出',
				scope:this,
				minWidth:tbarBtnMinWidth,
				icon:dcIcon,
				handler:function(){
					exportXtDbStructure(grid,'../xtDbStructureController/exportXtDbStructure');
				}
			 },
			 {
				text:'打 印',
				tooltip:'打 印',
				scope:this,
				minWidth:tbarBtnMinWidth,
				icon:printIcon,
				handler:function(){
					printerGrid(grid);
				}
			 },
			 {
				text:'刷 新',
				tooltip:'刷 新',
				scope:this,
				minWidth:tbarBtnMinWidth,
				icon:refreshIcon,
				handler:function(){
					grid.getStore().reload();
				}
			 }
		]
	});
	Ext.create('Ext.Viewport',{
		layout:'fit',
		xtype:'viewport',
		items:grid
	});
	/**调用右键**/
	initRight();
});
/**导出**/
function exportXtDbStructure(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtDbStructure(grid,'../xtDbStructureController/exportXtDbStructure');
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
	grid.on('itemcontextmenu',function(view,record,item,index,e){ 
		e.preventDefault(); 
		contextmenu.showAt(e.getXY());
	});
}
