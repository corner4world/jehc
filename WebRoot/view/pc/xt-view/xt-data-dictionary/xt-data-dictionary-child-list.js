var store;
var grid;
Ext.onReady(function(){
	var xt_data_dictionary_id = document.getElementById('xt_data_dictionary_id').value;
	store = getGridJsonStore('../xtDataDictionaryController/getXtDataDictionaryListByCondition?xt_data_dictionary_pid='+xt_data_dictionary_id,[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'信息列表',
		style:'margin-left:auto;margin-right:auto',
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
				header:'注备',
				dataIndex:'xt_data_dictionary_remark',
				flex:1
			}
		],
		tbar:[
			 {
				text:'添 加',
				tooltip:'添 加',
				scope:this,
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				icon:addIcon,
				handler:function(){
					addXtDataDictionary(xt_data_dictionary_id);
				}
			 },
			 {
				text:'编 辑',
				tooltip:'编 辑',
				scope:this,
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtDataDictionary();
				}
			 },
			 {
				text:'删 除',
				tooltip:'删 除',
				scope:this,
				icon:delIcon,
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				handler:function(){
					delXtDataDictionary();
				}
			 },
			 {
				text:'刷 新',
				tooltip:'刷 新',
				scope:this,
				minWidth:tbarBtnMinWidth,
				cls:'refreshBtn',
				icon:refreshIcon,
				handler:function(){
					grid.getStore().reload();
				}
			 },
			 {
				text:'返 回',
				tooltip:'返 回',
				scope:this,
				minWidth:tbarBtnMinWidth,
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../xtDataDictionaryController/loadXtDataDictionary";
				}
			 }
		],
		bbar:getGridBBar(store)
	});
	Ext.create('Ext.Viewport',{
		layout:'fit',
		xtype:'viewport',
		items:grid
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
});
