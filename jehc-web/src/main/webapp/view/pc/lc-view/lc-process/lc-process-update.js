var lcProcessWinEdit;
var lcProcessFormEdit;
function updateLcProcess(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要修改的一项！');
		return;
	}
	initLcProcessFormEdit();
	lcProcessWinEdit = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'编辑信息',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:lcProcessFormEdit,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				submitForm(lcProcessFormEdit,'../lcProcessController/updateLcProcess',grid,lcProcessWinEdit,false,true);
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	lcProcessWinEdit.show();
	/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
	initFileRight('xt_attachment','xt_attachment_pic',1,1,'zip,rar','1024000','ActivitiLc','ActivitiLcRelative');
	/**初始化附件右键菜单结束**/
	if(record.items[0].data.lc_process_flag == 0){
		Ext.getCmp('xt_attachment_fieldset').setHidden(true);
	}
	/**配置附件回显方法开始**/
	var params = {xt_attachment_id:record.items[0].data.xt_attachment,field_name:'xt_attachment'};
	ajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);
	/**配置附件回显方法结束**/
	loadFormData(lcProcessFormEdit,'../lcProcessController/getLcProcessById?lc_process_id='+ record.items[0].data.lc_process_id);
}
function initLcProcessFormEdit(){
	lcProcessFormEdit = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'流程编号',
			xtype:'textfield',
			hidden:true,
			name:'lc_process_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'流程标题',
			xtype:'textfield',
			name:'lc_process_title',
			allowBlank:false,
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:lc_process_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'lc_process_status',
			valueField:'value',
			displayField:'text',
			name:'lc_process_status',
			maxLength:4,
			anchor:'40%'
		},
		{
			fieldLabel:'常&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_constant_id_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_constant_id',
			valueField:'xt_constant_id',
			displayField:'xt_constantRemark',
			name:'xt_constant_id',
			anchor:'40%'
		},
//		{
//			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;识',
//			xtype:'combo',
//			emptyText:'请选择',
//			store:lc_process_flag_combo,
//			mode:'local',
//			triggerAction:'all',
//			editable:false,
//			hiddenName:'lc_process_flag',
//			valueField:'value',
//			displayField:'text',
//			hidden:true,
//			name:'lc_process_flag',
//			anchor:'40%'
//		},
		{
			fieldLabel:'流程附件',
			xtype:'textfield',
			hidden:true,
			id:'xt_attachment',
			name:'xt_attachment',
			maxLength:32,
			anchor:'100%'
		},
		{
			title:'流程附件',
			xtype:'fieldset',
			id:'xt_attachment_fieldset',
			items:{
				xtype:'box', 
				id:'xt_attachment_pic', 
				margin:'2 5 4 70', 
				autoEl:{
					tag:'img',
					src:bsdefimg
				}
			}
		},
		{
			fieldLabel:'备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注',
			xtype:'textareafield',
			name:'lc_process_remark',
			maxLength:800,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'lc_process_ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'lc_process_mtime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'40%'
		}
		]
	});
}
