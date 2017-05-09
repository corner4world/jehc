var xtSourcesWinDetail;
var xtSourcesFormDetail;
function detailXtSources(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtSourcesFormDetail();
	xtSourcesWinDetail = Ext.create('Ext.Window',{
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
		items:xtSourcesFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtSourcesWinDetail.show();
	/**配置附件回显方法开始**/
	var params = {xt_attachment_id:record.items[0].data.xt_attachment_id,field_name:'xt_attachment_id'};
	ajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);
	/**配置附件回显方法结束**/
	loadFormData(xtSourcesFormDetail,'../xtSourcesController/getXtSourcesById?xt_sources_id='+ record.items[0].data.xt_sources_id);
}
function initXtSourcesFormDetail(){
	xtSourcesFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			readOnly:true,
			margin:'2 5 4 5'
		},
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		items:[
		{
			fieldLabel:'静态资源编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_sources_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题',
			xtype:'textfield',
			name:'xt_sources_title',
			maxLength:100,
			anchor:'100%'
		},
		{
			fieldLabel:'描&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;述',
			xtype:'textareafield',
			name:'xt_sources_remark',
			maxLength:800,
			anchor:'100%'
		},
		{
			fieldLabel:'附件编号',
			xtype:'textfield',
			hidden:true,
			allowBlank:false,
			name:'xt_attachment_id',
			id:'xt_attachment_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			title:'静态资源附件',
			xtype:'fieldset',
			items:{
				xtype:'box', 
				id:'xt_attachment_id_pic',
				margin:'2 5 4 70',
				autoEl:{  
			        tag:'img',			    
			        src:bsdefimg
			    } 
			}
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:XT_SOURCES_STATUS_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_sources_status',
			valueField:'value',
			displayField:'text',
			name:'xt_sources_status',
			anchor:'40%'
		},
		{
			fieldLabel:'类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型',
			xtype:'combo',
			labelWidth:70,
			emptyText:'请选择',
			store:XT_SOURCES_TYPE_COMBO,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_sources_type',
			valueField:'value',
			displayField:'text',
			name:'xt_sources_type',
			anchor:'40%'
		},
		{
			fieldLabel:'操&nbsp;&nbsp;作&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_realName',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'xt_sources_ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'xt_sources_mtime',
			maxLength:19,
			anchor:'40%'
		}
		]
	});
}
