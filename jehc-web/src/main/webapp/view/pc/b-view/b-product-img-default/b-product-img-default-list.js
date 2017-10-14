var store;
var grid;
Ext.onReady(function(){
	/**查询区域可扩展**/
	B_PRODUCT_IMG_STATUS_COMBO_STORE = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
        data:[["0","正常"],["1","禁用"]]
	});
	B_PRODUCT_IMG_TYPE_COMBO_STORE = Ext.create('Ext.data.SimpleStore',{ 
	     fields:['value','text'],  
		 data:[["0","大图片"],["1","小图片"]]
	});
	/**查询区域可扩展**/
	var items = Ext.create('Ext.FormPanel',{
		xtype:'form',
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
			fieldLabel:'图片名称',
			xtype:'textfield',
			labelWidth:70,
			id:'b_product_img_name',
			name:'b_product_img_name',
			anchor:'20%',
			labelAlign:'top'
		},
		{
			fieldLabel:'b_product_id',
			xtype:'textfield',
			labelWidth:70,
			hidden:true,
			value:$('#b_product_id_').val(),
			name:'b_product_id',
			anchor:'20%',
			labelAlign:'top'
		},
		{
			fieldLabel:'状态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:B_PRODUCT_IMG_STATUS_COMBO_STORE,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_product_img_status',
			valueField:'value',
			displayField:'text',
			id:'b_product_img_status',
			name:'b_product_img_status',
			anchor:'20%',
			labelAlign:'top'
		},
		{
			fieldLabel:'图片类型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:B_PRODUCT_IMG_TYPE_COMBO_STORE,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'b_product_img_type',
			valueField:'value',
			displayField:'text',
			id:'b_product_img_type',
			name:'b_product_img_type',
			anchor:'20%',
			labelAlign:'top'
		}
		]
	});
	initSearchForm('north',items,false,'left');
	store = getGridJsonStore('../bProductImgDefaultController/getBProductImgDefaultListByCondition?b_product_id',[]);
	grid = Ext.create('Ext.grid.Panel',{
		region:'center',
		xtype:'panel',
		store:store,
		title:'平台默认图片--查询结果',
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
				header:'名称',
				dataIndex:'b_product_img_name'
			},
			{
				header:'类型',
				dataIndex:'b_product_img_type',
				renderer:function(value){
					if(value == 0){
						return "大图片";
					}else if(value==1){
						return "小图片";
					}else{
						return '---';
					}
				}
			},
			{
				header:'状态',
				dataIndex:'b_product_img_status',
				renderer:function(value){
					if(value == 0){
						return "可用";
					}else if(value==1){
						return "禁用";
					}else{
						return '---';
					}
				}
			},
			{
				header:'宽度',
				dataIndex:'b_product_img_width'
			},
			{
				header:'高度',
				dataIndex:'b_product_img_height'
			},
			{
				header:'创建时间',
				flex:1,
				dataIndex:'b_product_img_ctime'
			},
			{
				header:'修改时间',
				flex:1,
				dataIndex:'b_product_img_mtime'
			},
			{
				header:'操作者',
				flex:1,
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
					addBProductImgDefault();
				}
			 },
			 {
				text:'编辑',
				tooltip:'编辑',
				minWidth:tbarBtnMinWidth,
				cls:'updateBtn',
				icon:editIcon,
				handler:function(){
					updateBProductImgDefault();
				}
			 },
			 {
				text:'删除',
				tooltip:'删除',
				minWidth:tbarBtnMinWidth,
				cls:'delBtn',
				icon:delIcon,
				handler:function(){
					delBProductImgDefault();
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
			 {
				text:'返回商品列表',
				tooltip:'返回商品列表',
				cls:'backBtn',
				icon:backIcon,
				handler:function(){
					document.location.href="../bProductController/loadBProduct";
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
						copyBProductImgDefault();
					}
				 },
				 {
					text:'明 细',
					tooltip:'明 细',
					glyph:0xf03b,
					handler:function(){
						detailBProductImgDefault();
					}
				 },
				 '-',
				 {
					text:'导 出',
					tooltip:'导 出',
					glyph:0xf1c3,
					handler:function(){
						exportBProductImgDefault(grid,'../bProductImgDefaultController/exportBProductImgDefault');
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
				detailBProductImgDefault();
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
function delBProductImgDefault(){
	var model = grid.getSelectionModel();
	if(model.selected.length == 0){
		msgTishi('请选择后在删除');
		return;
	}
	var b_product_img_default_id;
	for(var i = 0; i < model.selected.length; i++){
		if(null == b_product_img_default_id){
			b_product_img_default_id=model.selected.items[i].data.b_product_img_default_id;
		}else{
			b_product_img_default_id=b_product_img_default_id+","+model.selected.items[i].data.b_product_img_default_id;
		}
	}
	Ext.Msg.confirm('提示','确定删除该行数据？',function(btn){
		if(btn == 'yes'){
			var params = {b_product_img_default_id:b_product_img_default_id};
			ajaxRequest('../bProductImgDefaultController/delBProductImgDefault',grid,params,'正在执行删除操作中！请稍后...');
		}
	});
}
/**复制一行并生成记录**/
function copyBProductImgDefault(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要复制的行！');
		return;
	}
	Ext.Msg.confirm('提示','确定要复制一行并生成记录？',function(btn){
		if(btn == 'yes'){
			var params = {b_product_img_default_id:record.items[0].data.b_product_img_default_id};
			ajaxRequest('../bProductImgDefaultController/copyBProductImgDefault',grid,params,'正在执行复制一行并生成记录！请稍后...');
		}
	});
}
/**导出**/
function exportBProductImgDefault(grid,url){
	exportExcel(grid,url);
}
/**初始化右键**/
function initRight(){
	var contextmenu = new Ext.menu.Menu({
		id:'theContextMenu',
		items:[{
			text:'添 加',
			glyph:0xf016,
			id:'addBProductImgDefaultItem',
			handler:function(){addBProductImgDefault();}
		},{
			text:'编 辑',
			glyph:0xf044,
			id:'updateBProductImgDefaultItem',
			handler:function(){updateBProductImgDefault();}
		},{
			text:'删 除',
			glyph:0xf014,
			id:'delBProductImgDefaultItem',
			handler:function(){delBProductImgDefault();}
		},'-',{
			text:'复制一行并生成记录',
			glyph:0xf0ea,
			id:'copyBProductImgDefaultItem',
			handler:function(){copyBProductImgDefault();}
		},{
			text:'明 细',
			glyph:0xf03b,
			id:'detailBProductImgDefaultItem',
			handler:function(){detailBProductImgDefault();}
		},{
			text:'导 出',
			glyph:0xf1c3,
			handler:function(){
				exportBProductImgDefault(grid,'../bProductImgDefaultController/exportBProductImgDefault');
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
	initrightgridcontextmenu(grid,contextmenu,['updateBProductImgDefaultItem','delBProductImgDefaultItem','copyBProductImgDefaultItem','detailBProductImgDefaultItem']);
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
			autoHeight:true,
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
			                url:basePath+'/bProductImgDefaultController/uploadProductImgDefaultImages',
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
