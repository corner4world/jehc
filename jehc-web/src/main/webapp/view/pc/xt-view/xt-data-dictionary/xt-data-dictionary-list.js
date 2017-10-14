var store;
var grid;
var cstore;
var cgrid;
Ext.onReady(function(){
	var getGrid = function(element) {
		store = getGridJsonStore('../xtDataDictionaryController/getXtDataDictionaryListByCondition',[]);
		grid = new Ext.grid.GridPanel({
			autoExpandColumn:'xt_data_dictionary_id',
			region:'center',
			store:store,
			title:'查询结果',
			style:'margin-left:auto;margin-right:auto',
			columnLines:true,
			selType:'cellmodel',
			multiSelect:true,
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
					header:'字典名称',
					dataIndex:'xt_data_dictionary_name'
				},
				{
					header:'态状',
					dataIndex:'xt_data_dictionary_state',
					renderer:function(value){
						if(value == 0){
							return "启用";
						}else{
							return "暂停";
						}
					}
				},
				{
					header:'操作',
					flex:2,
					columns:[{
						align:'center',
						header:'操作下级',
						xtype:'widgetcolumn',
						width:160,
						widget:{
			                xtype:'button',
			                text:'添加下级',
			                width:140,
			                icon:addIcon,
			                handler:function(rec){
			  					var xt_data_dictionary_id = rec.getWidgetRecord().data.xt_data_dictionary_id;
						    	addXtDataDictionary(xt_data_dictionary_id);
						    }
			            }
					},
					{
						align:'center',
						header:'下级列表',
						xtype:'widgetcolumn',
						width:160,
						widget:{
			                xtype:'button',
			                text:'下级列表',
			                width:140,
			                icon:detailIcon,
			                handler:function(rec){
			  					var xt_data_dictionary_id = rec.getWidgetRecord().data.xt_data_dictionary_id;
						   		document.location.href="../xtDataDictionaryController/loadXtDataDictionaryChild?xt_data_dictionary_id="+xt_data_dictionary_id;
						    }
			            }
					}]
				}
			],
			tbar:[
			 {
				text:'添加',
				tooltip:'添加',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addXtDataDictionary();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtDataDictionary();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtDataDictionary();
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
					text:'复制一行并生成记录',
					tooltip:'复制一行并生成记录',
					glyph:0xf0ea,
					handler:function(){
						copyXtDataDictionary();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtDataDictionary();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtDataDictionary(grid,'../xtDataDictionaryController/exportXtDataDictionary');
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
			loadMask:{msg:'正在加载数据,请稍候......'}
		});
		element&&grid.render(element);
		return grid;
	};
	
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[getGrid()]
	});
});
/**删除操作**/
function delXtDataDictionary(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var rowData = model.selected.items[0].data;
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var xt_data_dictionary_id = rowData.xt_data_dictionary_id;
			var params = {xt_data_dictionary_id:xt_data_dictionary_id};
			ajaxRequest('../xtDataDictionaryController/delXtDataDictionary',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}