var xtVersionWinDetail;
var xtVersionFormDetail;
function detailXtVersion(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtVersionFormDetail();
	xtVersionWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'明细信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtVersionFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtVersionWinDetail.show();
	/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/
	initFileRight('xt_attachment_id','xt_attachment_id_pic',1,2);
	/**初始化附件右键菜单结束**/

	/**配置附件回显方法开始**/
	var params = {xt_attachment_id:record.items[0].data.xt_attachment_id,field_name:'xt_attachment_id'};
	ajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);
	/**配置附件回显方法结束**/
	loadFormData(xtVersionFormDetail,'../xtVersionController/getXtVersionById?xt_version_id='+ record.items[0].data.xt_version_id);
}
function initXtVersionFormDetail(){
	xtVersionFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		/**新方法使用开始**/
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		/**新方法使用结束**/
		bodyStyle:'background-color:#E6E6E6;',  
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5',
		    readOnly:true,
		    style:'background:#E6E6E6'
		},
		items:[
		{
			fieldLabel:'平台版本编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_version_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'版本名称',
			xtype:'textfield',
			name:'xt_version_name',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'下载次数',
			xtype:'numberfield',
			value:'0',
			name:'xt_version_number',
			maxLength:10,
			anchor:'30%'
		},
		{
			fieldLabel:'上&nbsp;&nbsp;传&nbsp;&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'30%'
		},
		{
			fieldLabel:'上传时间',
			xtype:'textfield',
			name:'xt_version_ctime',
			maxLength:30,
			anchor:'30%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'textfield',
			name:'xt_version_mtime',
			maxLength:30,
			anchor:'30%'
		},
		{
			fieldLabel:'版本描述',
			xtype:'textareafield',
			name:'xt_version_remark',
			maxLength:2000,
			anchor:'100%'
		},
		{
			fieldLabel:'附件编号',
			xtype:'textfield',
			hidden:true,
			id:'xt_attachment_id',
			name:'xt_attachment_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			title:'附件编号',
			xtype:'fieldset',
			items:{
				xtype:'box', 
				width:80, 
				height:60, 
				id:'xt_attachment_id_pic', 
				margin:'2 5 4 70', 
				autoEl:{
					tag:'img',
					src:bsdefimg
				}
			}
		}
		]
	});
}
