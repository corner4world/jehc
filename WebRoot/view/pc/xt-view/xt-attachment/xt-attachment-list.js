var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
		maxHeight:150,
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'附件原名称',
			xtype:'textfield',
			labelWidth:70,
			id:'xt_attachmentTitle',
			name:'xt_attachmentTitle',
			anchor:'30%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtAttachmentController/getXtAttachmentListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
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
				header:'附件原名称',
				flex:1,
				dataIndex:'xt_attachmentTitle',
				renderer:function(value){
					return "<a href='#'>"+value+"</a>"
				}
			},
			{
				header:'附件新名称',
				flex:1,
				dataIndex:'xt_attachmentName'
			},
			{
				header:'件文类型',
				flex:1,
				dataIndex:'xt_attachmentType'
			},
			{
				header:'件文大小',
				flex:1,
				dataIndex:'xt_attachmentSize'
			},
			{
				header:'状态',
				flex:1,
				dataIndex:'xt_attachmentIsDelete',
				renderer:function(value){
					if(value == 0){
						return "正常";
					}else{
						return "删除";
					}
				}
			},
			{
				header:'上传时间',
				flex:1,
				dataIndex:'xt_attachmentCtime'
			},
			{
				header:'上传者',
				flex:1,
				dataIndex:'xt_userinfo_realName'
			}
		],
		tbar:[
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtAttachment();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtAttachment();
				}
			 },
			 {
				text:'检索',
				tooltip:'检索',
				minWidth:tbarBtnMinWidth,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					grid.getStore().reload();
				}
			 },
			 {
				text:'重置',
				tooltip:'重置',
				minWidth:tbarBtnMinWidth,
				icon:clearSearchIcon,
				handler:function(){
					searchForm.reset();
				}
			 },
			 grid_toolbar_moretext_gaps,
			 {
				 text:moretext,
				 tooltip:moretexttooltip,
				 scope:this,
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
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
				 },
				 {
					text:'检 索',
					tooltip:'检 索',
					glyph:0xf002,
					handler:function(){
						grid.getStore().reload();
					}
				 },
				 {
					text:'重 置',
					tooltip:'重 置',
					glyph:0xf014,
					handler:function(){
						searchForm.reset();
					}
				 }
				 ]
			 }
		],
		bbar:getGridBBar(store)
	});
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delXtAttachment(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_attachment_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_attachment_id){
			xt_attachment_id=model.selected.items[i].data.xt_attachment_id;
		}else{
			xt_attachment_id=xt_attachment_id+","+model.selected.items[i].data.xt_attachment_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_attachment_id:xt_attachment_id};
			ajaxRequest('../xtAttachmentController/delXtAttachment',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
