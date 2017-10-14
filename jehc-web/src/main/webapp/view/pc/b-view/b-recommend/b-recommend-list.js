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
		layout:'table',
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'推荐标题',
			labelWidth:70,
			emptyText:'请输入',
			name:'b_recommend_title',
			anchor:'30%',
			labelAlign:'top'
		},
		{
			fieldLabel:'链接地址',
			labelWidth:70,
			emptyText:'请输入',
			name:'b_recommend_url',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bRecommendController/getBRecommendListByCondition',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'查询结果',
		columnLines:true,
		selType:'cellmodel',
		multiSelect:true,
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
				header:'推荐标题',
				flex:1,
				dataIndex:'b_recommend_title'
			},
			{
				header:'链接地址',
				dataIndex:'b_recommend_url'
			},
			{
				header:'宽度',
				dataIndex:'b_recommend_width'
			},
			{
				header:'高度',
				dataIndex:'b_recommend_height'
			},
			{
				header:'状态',
				dataIndex:'b_recommend_status',
				renderer:function(value){
					if(value == 0){
						return "正常";
					}else if(value==1){
						return "禁用";
					}else{
						return '---';
					}
				}
			},
			{
				header:'创建时间',
				dataIndex:'b_recommend_ctime'
			},
			{
				header:'修改时间',
				dataIndex:'b_recommend_mtime',
				renderer:function(value){
					if(value == null){
						return "暂无修改";
					}else{
						return value;
					}
				}
			},
			{
				header:'创建人',
				dataIndex:'xt_userinfo_realName'
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
					addBRecommend();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBRecommend();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBRecommend();
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
				 icon:listIcon,
				 iconAlign:'left',
				 menu:[
				 {
					text:'复制一行并生成记录',
					tooltip:'复制一行并生成记录',
					glyph:0xf0ea,
					handler:function(){
						copyBRecommend();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBRecommend();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBRecommend(grid,'../bRecommendController/exportBRecommend');
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
		bbar:getGridBBar(store),
		listeners:{
			'rowdblclick':function(grid, rowIndex, e){
				detailBRecommend();
			}
		}
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:[searchForm,grid]
	});
	/**调用右键**/
	initRight();
	store.on('beforeload',function(thiz, options){Ext.apply(thiz.proxy.extraParams,getParmas(store,searchForm));});
});
/**删除操作**/
function delBRecommend(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_recommend_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_recommend_id){
			b_recommend_id=model.selected.items[i].data.b_recommend_id;
		}else{
			b_recommend_id=b_recommend_id+","+model.selected.items[i].data.b_recommend_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_recommend_id:b_recommend_id};
			ajaxRequest('../bRecommendController/delBRecommend',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBRecommend(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_recommend_id:record.items[0].data.b_recommend_id};
			ajaxRequest('../bRecommendController/copyBRecommend',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBRecommend(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBRecommendItem',
			handler:function(){addBRecommend();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBRecommendItem',
			handler:function(){updateBRecommend();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBRecommendItem',
			handler:function(){delBRecommend();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBRecommendItem',
			handler:function(){copyBRecommend();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBRecommendItem',
			handler:function(){detailBRecommend();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBRecommend(grid,'../bRecommendController/exportBRecommend');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBRecommendItem','delBRecommendItem','copyBRecommendItem','detailBRecommendItem']);
}

/**上传操作**/
var uploadWin;
var uploadForm;
function clickHandler(){
	uploadForm = Ext.create('Ext.FormPanel',{
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1
		},
		items:[
			{
			    xtype:'filefield',
			    emptyText:'请选择要上传的图片',
			    name:'picFile',
			    buttonText:'选择文件',
			    anchor:'100%',
			    allowBlank:false,
			    buttonConfig:{
			        icon:'../deng/images/icons/picture.png'
			    }
			}
		]
	});
	uploadWin = Ext.create('Ext.Window',{
			layout:'fit',
			width:400,
			height:130,
			closeAction:'hide',
			closable:false, 
			animateTarget:document.body,
			plain:true,
			modal:true,
			fieldDefaults:{
				labelWidth:70,
				labelAlign:'left',
				flex:1,
				margin:'2 5 4 5'
			},
			listeners:{
				minimize:function(win,opts){
					win.collapse();
				}
			},
			items:uploadForm,
			buttons:[{
				text:'开始上传',
				itemId:'save',
				handler:function(button){
					if(uploadForm.isValid()) {
			            uploadForm.submit({
			                url:basePath+'/bRecommendController/uploadRecommendImages',
			                waitMsg:'正在上传中，请稍后...',
			                success:function(form, action){
			                	/**
			                    var tpl = new Ext.XTemplate(
			                        'File processed on the server.<br />',
			                        'Name: {fileName}<br />',
			                        'Size: {fileSize:fileSize}'
			                    );
			                    Ext.Msg.alert('Success', tpl.apply(o.result));
			                    **/
			                    /**动态改变容器中路径用该方法**/
			                    Ext.getCmp('pic').getEl().dom.src = action.result.data.jsonValue;
			                    Ext.getCmp('xt_attachment_id').setValue(action.result.data.jsonID);
			                    msgTishi(action.result.data.msg);
			                    uploadWin.close();
			                }
			            });
			        }else{
			        	msgTishi('请选择图片');
			        }
				}
			},{
				text:'关闭',
				itemId:'close',
				handler:function(button){
					button.up('window').close();
				}
			}]
		});
	uploadWin.show();
}
