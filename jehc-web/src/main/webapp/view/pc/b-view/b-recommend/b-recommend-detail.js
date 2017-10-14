var bRecommendWinDetail;
var bRecommendFormDetail;
function detailBRecommend(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initBRecommendFormDetail();
	bRecommendWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximized:true,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		headerPosition:'left',
		title:'明细信息',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		items:bRecommendFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	bRecommendWinDetail.show();
	/**初次赋值改变容器中路径用该方法
	Ext.getCmp('pic').autoEl.src = record.items[0].data.hsimg_base_url+record.items[0].data.xt_attachmentPath;
	**/
	/*console.info(record.items[0].data.hsimg_base_url+record.items[0].data.xt_attachmentPath);
	Ext.getCmp('pic').getEl().dom.src = record.items[0].data.hsimg_base_url+record.items[0].data.xt_attachmentPath;*/
	/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/
	initFileRight('xt_attachment_id','xt_attachment_id_pic',1,2);
	/**初始化附件右键菜单结束**/

	/**配置附件回显方法开始**/
	var params = {xt_attachment_id:record.items[0].data.xt_attachment_id,field_name:'xt_attachment_id'};
	ajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);
	/**配置附件回显方法结束**/
	loadFormData(bRecommendFormDetail,'../bRecommendController/getBRecommendById?b_recommend_id='+ record.items[0].data.b_recommend_id);
}
function initBRecommendFormDetail(){
	bRecommendFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		/**新方法使用结束**/
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'推荐编号',
			xtype:'textfield',
			hidden:true,
			name:'b_recommend_id',
			allowBlank:false,
			maxLength:32,
			width:400
		},
		{
			fieldLabel:'推荐标题',
			xtype:'textfield',
			name:'b_recommend_title',
			maxLength:100,
			width:400
		},
		{
			fieldLabel:'链接地址',
			xtype:'textfield',
			name:'b_recommend_url',
			maxLength:500,
			width:400
		},
		{
			fieldLabel:'排&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;序',
			xtype:'numberfield',
			value:'0',
			name:'b_recommend_sort',
			width:300
		},
		{
			fieldLabel:'宽&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度',
			xtype:'numberfield',
			value:'0',
			name:'b_recommend_width',
			width:300
		},
		{
			fieldLabel:'高&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;度',
			xtype:'numberfield',
			value:'0',
			name:'b_recommend_height',
			width:300
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textareafield',
			name:'b_recommend_remark',
			maxLength:200,
			anchor:'60%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			name:'b_recommend_status',
			xtype:"combo",
            store:[["0","可用"],["1","禁用"]],
            emptyText:"请选择",
            mode:"local",
            value:'0',
            triggerAction:"all",
            editable:false,
			hiddenName:'b_recommend_status',
			allowBlank:false,
			maxLength:2,
			width:200
		},
		{
			fieldLabel:'附件编号',
			xtype:'textfield',
			name:'xt_attachment_id',
			id:'xt_attachment_id',
			maxLength:32,
			hidden:true,
			width:200
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			width:300
		},
		{
			fieldLabel:'创建时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_recommend_ctime',
			width:300
		},
		{
			fieldLabel:'修改时间',
			xtype:'datefield',
			format:'Y-m-d H:i:s',
			name:'b_recommend_mtime',
			width:300
		},
		{
			title:'附件',
			xtype:'fieldset',
			items:{
				xtype:'box', 
				width:80, 
				height:60, 
				id:'xt_attachment_id_pic', 
				margin:'2 5 4 70', 
				autoEl:{
					tag:'img',
					/** 不采用右键时候直接用点击事件触发
					onclick:"optupload('xt_attachment_id','xt_attachment_id_pic',1)",
					**/
					src:bsdefimg
				}
			}
		}
		]
	});
}
