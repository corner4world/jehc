var store;
var grid;
var kstore;
Ext.onReady(function(){
	kstore = getGridJsonStore('../xtKnowledgeController/autoXtKnowledgeListByCondition',[]);
	/**查询区域可扩展**/
	var items = {
	 	xtype:'combo',
	    store:kstore,
	    displayField:'resultTitle',
	    typeAhead:false,
	    id:'keywords',
	    hiddenName:'keywords',
	    name:'keywords',
	    anchor:'100%',
       	emptyText:"请输入关键字",
       	hideTrigger:true,
       	minChars:1,
   		matchFieldWidth:true,
       	fieldLabel:'标题、内容',
       	listConfig:{
	       	loadingText:'正在查找中...',
	       	emptyText:'没有您要搜索的结果',
	       	itemSelector:'.search-item',
	       	itemTpl:[
	           '<table class="search-item" href="#">',
	               '<tr><td style="width:95%">{resultTitle}</td><td style="width:5%" align="right">约{resultCount}个<td></tr>','',
	           '</table>'
	       ]
   	    },
      	style:{background:'#fff',marginRight:'0px',marginLeft:'0px'}
	};
	kstore.on('beforeload',function(thiz, options){
		Ext.apply(thiz.proxy.extraParams,{keywords:gValue('keywords')});
    });
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../xtKnowledgeController/getXtKnowledgeListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
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
				header:'标题',
				flex:1,
				dataIndex:'xt_knowledge_title'
			},
			{
				header:'创建者',
				dataIndex:'xt_userinfo_realName'
			},
			{
				header:'类型',
				dataIndex:'xt_type',
				renderer:function(value){
					if(value == 0){
						return "平台问题"
					}else if(value == 1){
						return "学习知识";
					}
				}
			},
			{
				header:'建创时间',
				dataIndex:'xt_time'
			},
			{
				header:'更新时间',
				dataIndex:'xt_uptime'
			},
			{
				header:'状态',
				dataIndex:'xt_state',
				renderer:function(value){
					if(value == 0){
						return "待解决"
					}else if(value == 1){
						return "已解决";
					}
				}
			},
			{
				header:'级别',
				dataIndex:'xt_level',
				renderer:function(value){
					if(value == 1){
						return "紧急"
					}else if(value == 2){
						return "正常";
					}else if(value == 3){
						return "一般";
					}
				}
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
					addXtKnowledge();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateXtKnowledge();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delXtKnowledge();
				}
			 },
			 {
				text:'检索',
				tooltip:'检索',
				minWidth:tbarBtnMinWidth,
				cls:'searchBtn',
				icon:searchIcon,
				handler:function(){
					search();
				}
			 },
			 {
				text:'重置',
				tooltip:'重置',
				icon:clearSearchIcon,
				handler:function(){
					searchForm.reset();
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
						copyXtKnowledge();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailXtKnowledge();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportXtKnowledge(grid,'../xtKnowledgeController/exportXtKnowledge');
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
				 },
				 {
					text:'检 索',
					tooltip:'检 索',
					glyph:0xf002,
					handler:function(){
						search();
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
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailXtKnowledge();
			}
		}
	});
	Ext.create('Ext.Viewport', {
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
	store.on('load',function(){
	 	//mergeCells(grid,[2,9]);  
	 	//mergeCells(grid,[3,9]);  
	});
});
/**删除操作**/
function delXtKnowledge(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var xt_knowledge_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == xt_knowledge_id){
			xt_knowledge_id=model.selected.items[i].data.xt_knowledge_id;
		}else{
			xt_knowledge_id=xt_knowledge_id+","+model.selected.items[i].data.xt_knowledge_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {xt_knowledge_id:xt_knowledge_id};
			ajaxRequest('../xtKnowledgeController/delXtKnowledge',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyXtKnowledge(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {xt_knowledge_id:record.items[0].data.xt_knowledge_id};
			ajaxRequest('../xtKnowledgeController/copyXtKnowledge',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportXtKnowledge(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addXtKnowledgeItem',
			handler:function(){addXtKnowledge();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateXtKnowledgeItem',
			handler:function(){updateXtKnowledge();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delXtKnowledgeItem',
			handler:function(){delXtKnowledge();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyXtKnowledgeItem',
			handler:function(){copyXtKnowledge();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailXtKnowledgeItem',
			handler:function(){detailXtKnowledge();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportXtKnowledge(grid,'../xtKnowledgeController/exportXtKnowledge');
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
	initrightgridcontextmenu(grid,contextmenu,['updateXtKnowledgeItem','delXtKnowledgeItem','copyXtKnowledgeItem','detailXtKnowledgeItem']);
}


/**查询操作**/
function search(){
	initSearch(store,'../xtKnowledgeController/getXtKnowledgeListByCondition',searchForm); 
}