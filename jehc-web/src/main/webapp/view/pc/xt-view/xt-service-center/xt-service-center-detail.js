var xtServiceCenterWinDetail;
var xtServiceCenterFormDetail;
var xtServiceCenterFormDetailFieldSet;
var xtServiceCenterParameterFormDetailFieldSet;
function detailXtServiceCenter(){
	var record = grid.getSelectionModel().selected;
	if(record.length == 0){
		msgTishi('请选择要查看明细的项！');
		return;
	}
	initXtServiceCenterFormDetail();
	xtServiceCenterWinDetail = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'明细信息',
		headerPosition:'left',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		items:xtServiceCenterFormDetail,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	xtServiceCenterWinDetail.show();
	
	loadFormDataCallBack(xtServiceCenterFormDetail,'../xtServiceCenterController/getXtServiceCenterById?xt_service_center_id='+ record.items[0].data.xt_service_center_id,callFnDetail);
}
function initXtServiceCenterFormDetail(){
	xtServiceCenterFormDetailFieldSet = Ext.create('Ext.form.FieldSet',{
		autoScroll:true,
		title:'基本信息',
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		items:[
		{
			fieldLabel:'服务名称',
			xtype:'textfield',
			name:'xt_service_center_name',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'服务地址',
			xtype:'textfield',
			name:'xt_service_center_location',
			maxLength:1024,
			anchor:'100%'
		},
		{
			fieldLabel:'服务描述',
			xtype:'textareafield',
			name:'xt_service_center_remark',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'mtime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;人',
			xtype:'textfield',
			name:'xt_userinfo_id',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'服务类型',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_service_center_type_combo ,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_service_center_type',
			valueField:'value',
			displayField:'text',
			name:'xt_service_center_type',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_service_center_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_service_center_status',
			valueField:'value',
			displayField:'text',
			name:'xt_service_center_status',
			maxLength:4,
			anchor:'40%'
		},
		{
			xtype:'numberfield',
			hidden:true,
			value:'0',
			itemId:'xtServiceCenterParameterFormNumber'
		},
		{
			xtype:'textfield',
			hidden:true,
			name:'xt_Service_Center_Parameter_removed_flag',
			itemId:'xt_Service_Center_Parameter_removed_flag'
		}
		]
	});
	xtServiceCenterParameterFormDetailFieldSet = Ext.create('Ext.form.FieldSet',{
		anchor:'100%',
		title:'服务中心参数',
		items:[]
	});
	xtServiceCenterFormDetail = Ext.create('Ext.FormPanel',{
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
			readOnly:true,
			margin:'2 5 4 5'
		},
		items:[
			xtServiceCenterFormDetailFieldSet,
			xtServiceCenterParameterFormDetailFieldSet
		]
	});
}
function callFnDetail(form, action){
	var xt_Service_Center_Parameter = action.result.data.xt_Service_Center_Parameter;
	for(var i = 0; i < xt_Service_Center_Parameter.length; i++){
		addDetailXtServiceCenterParameterFormDetail(xt_Service_Center_Parameter[i]);
	}
}
function addDetailXtServiceCenterParameterFormDetail(data){
	var numbers = gfiValue(xtServiceCenterFormDetailFieldSet,'xtServiceCenterParameterFormNumber');
	sfiValue(xtServiceCenterFormDetailFieldSet,'xtServiceCenterParameterFormNumber',numbers+1);
	xtServiceCenterParameterFormDetail = Ext.create('Ext.FormPanel',{
		xtype:'form',
		waitMsgTarget:true,
		defaultType:'textfield',
		/**
		autoScroll:true,
		scrollable:true,
		scrollable:'x',
		scrollable:'y',
		**/
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			readOnly:true,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'服务参数编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_id',
			itemId:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'服务中心编号',
			xtype:'textfield',
			hidden:true,
			name:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_id',
			itemId:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_id',
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'参数名称',
			xtype:'textfield',
			name:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_name',
			itemId:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_name',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'参数描述',
			xtype:'textareafield',
			name:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_remark',
			itemId:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_remark',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'参数类型',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_service_center_parameter_type_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_type',
			valueField:'value',
			displayField:'text',
			name:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_type',
			itemId:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_type',
			maxLength:4,
			anchor:'40%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_service_center_parameter_status_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_status',
			valueField:'value',
			displayField:'text',
			name:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_status',
			itemId:'xt_Service_Center_Parameter['+numbers+'].xt_service_center_parameter_status',
			maxLength:4,
			anchor:'40%'
		},
		{
			fieldLabel:'创&nbsp;&nbsp;建&nbsp;人',
			xtype:'textfield',
			name:'xt_Service_Center_Parameter['+numbers+'].xt_userinfo_id',
			itemId:'xt_Service_Center_Parameter['+numbers+'].xt_userinfo_id',
			maxLength:32,
			anchor:'40%'
		},
		{
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'xt_Service_Center_Parameter['+numbers+'].ctime',
			itemId:'xt_Service_Center_Parameter['+numbers+'].ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'修改时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'xt_Service_Center_Parameter['+numbers+'].mtime',
			itemId:'xt_Service_Center_Parameter['+numbers+'].mtime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'参数类型附件',
			xtype:'textfield',
			hidden:true,
			id:'xt_Service_Center_Parameter['+numbers+'].attach_filetype',
			name:'xt_Service_Center_Parameter['+numbers+'].attach_filetype',
			itemId:'xt_Service_Center_Parameter['+numbers+'].attach_filetype',
			maxLength:32,
			anchor:'100%'
		},
		{
			title:'参数类型附件',
			anchor:'80%',
			xtype:'fieldset',
			items:{
				xtype:'box', 
				width:80,
				height:60, 
				id:'xt_Service_Center_Parameter['+numbers+'].attach_filetype_pic', 
				margin:'2 5 4 70', 
				autoEl:{
					tag:'img',
					src:bsdefimg
				}
			}
		},
		{
			fieldLabel:'参数配置附件',
			xtype:'textfield',
			hidden:true,
			id:'xt_Service_Center_Parameter['+numbers+'].attach_fileconfig',
			itemId:'xt_Service_Center_Parameter['+numbers+'].attach_fileconfig',
			name:'xt_Service_Center_Parameter['+numbers+'].attach_fileconfig',
			maxLength:32,
			anchor:'100%'
		},
		{
			title:'参数配置附件',
			anchor:'80%',
			xtype:'fieldset',
			items:{
				xtype:'box', 
				width:80,
				height:60, 
				id:'xt_Service_Center_Parameter['+numbers+'].attach_fileconfig_pic', 
				margin:'2 5 4 70', 
				autoEl:{
					tag:'img',
					src:bsdefimg
				}
			}
		}
		]
	});
	xtServiceCenterParameterFormDetailFieldSet.add(xtServiceCenterParameterFormDetailFieldSet.items.getCount(),xtServiceCenterParameterFormDetail);
	if(null != data){
		//获取表单中所有字段与值（key/value）
		var xtServiceCenterParameterFormDetailData = xtServiceCenterParameterFormDetail.getForm().getFieldValues();
		//遍历表单中所有字段名称（key）
		for(var xtServiceCenterParameterFormDetailField in xtServiceCenterParameterFormDetailData){
			//获取表单中所有字段名称（key）并且截取对象后面的字段 目的与实体类中字段名称一致
			var xtServiceCenterParameterFormDetailYField = xtServiceCenterParameterFormDetailField.split(".")[1];
			//遍历服务器传递来的对象
			for(var dataKey in data){
				//判断当前字段是否等于服务器传过来的字段
				if(xtServiceCenterParameterFormDetailYField == dataKey){
					//给表单每个字段赋值
					sfiValue(xtServiceCenterParameterFormDetail,xtServiceCenterParameterFormDetailField,data[dataKey]);
					/**配置附件回显方法开始**/
					if(xtServiceCenterParameterFormDetailYField == 'attach_filetype'){
						var params = {xt_attachment_id:data[dataKey],field_name:xtServiceCenterParameterFormDetailField};
						ajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);
					};
					if(xtServiceCenterParameterFormDetailYField == 'attach_fileconfig'){
						var params = {xt_attachment_id:data[dataKey],field_name:xtServiceCenterParameterFormDetailField};
						ajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);
					};
					/**配置附件回显方法结束**/
				}
			}
		}
	}
	xtServiceCenterParameterFormDetailFieldSet.afterLayout();
	resetTitle(xtServiceCenterParameterFormDetailFieldSet,true);
	/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/
	initFileRight('xt_Service_Center_Parameter['+numbers+'].attach_filetype','xt_Service_Center_Parameter['+numbers+'].attach_filetype_pic',1,2);
	initFileRight('xt_Service_Center_Parameter['+numbers+'].attach_fileconfig','xt_Service_Center_Parameter['+numbers+'].attach_fileconfig_pic',1,2);
	/**初始化附件右键菜单结束**/

}
