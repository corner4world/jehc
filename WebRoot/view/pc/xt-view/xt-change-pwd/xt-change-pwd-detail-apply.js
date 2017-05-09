var xtChangePwdFormDetail;
var xt_change_pwd_sex_combo;
var xt_change_pwd_status_combo;
Ext.onReady(function(){
	xt_change_pwd_sex_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		data:[["0","男"],["1","女"]]
	});
	xt_change_pwd_status_combo = Ext.create('Ext.data.SimpleStore',{ 
	     fields:['value','text'],  
		 data:[["0","待审核"],["1","审核未通过"],["2","审核通过"]]
	});
	initXtChangePwdFormDetail();
	Ext.create('Ext.Viewport',{
		layout:'fit',
		xtype:'viewport',
		items:xtChangePwdFormDetail
	});
	/**初始化附件右键菜单开始 参数4为1表示不拥有上传和删除功能 即明细页面使用**/
	initFileRight('xt_attachment_id','xt_attachment_id_pic',1,2);
	/**初始化附件右键菜单结束**/
	loadFormDataCallBack(xtChangePwdFormDetail,'../xtChangePwdController/getXtChangePwdById?xt_change_pwd_id='+ $("#lc_apply_model_biz_id").val(),callbackFn);
});

function callbackFn(form, action){
	/**配置附件回显方法开始**/
	var params = {xt_attachment_id:action.result.data.xt_attachment_id,field_name:'xt_attachment_id'};
	ajaxFilePathBackRequest('../xtCommonController/getAttachmentPathPP',params,1);
	/**配置附件回显方法结束**/
}
function initXtChangePwdFormDetail(){
	xtChangePwdFormDetail = Ext.create('Ext.FormPanel',{
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
			fieldLabel:'主键',
			xtype:'textfield',
			hidden:true,
			name:'xt_change_pwd_id',
			allowBlank:false,
			maxLength:32,
			anchor:'100%'
		},
		{
			fieldLabel:'用户名称',
			xtype:'textfield',
			name:'user_name',
			maxLength:64,
			anchor:'40%'
		},
		{
			fieldLabel:'登录账号',
			xtype:'textfield',
			name:'login_id',
			maxLength:255,
			anchor:'40%'
		},
		{
			fieldLabel:'性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别',
			xtype:'combo',
			emptyText:'请选择',
			store:xt_change_pwd_sex_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'sex',
			valueField:'value',
			displayField:'text',
			name:'sex',
			maxLength:4,
			anchor:'40%'
		},
		{
			fieldLabel:'手机号码',
			xtype:'textfield',
			name:'phone',
			maxLength:11,
			anchor:'40%'
		},
		{
			fieldLabel:'邮箱地址',
			xtype:'textfield',
			name:'mail',
			maxLength:255,
			anchor:'40%'
		},
		{
			fieldLabel:'居住地址',
			xtype:'textareafield',
			name:'address',
			maxLength:255,
			anchor:'100%'
		},
		{
			fieldLabel:'证件照片',
			xtype:'textfield',
			hidden:true,
			id:'xt_attachment_id',
			name:'xt_attachment_id',
			maxLength:32,
			anchor:'40%'
		},
		{
			title:'证件照片',
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
			fieldLabel:'创建时间',
			xtype:'datetimefield',
			format:'Y-m-d H:i:s',
			name:'ctime',
			maxLength:19,
			anchor:'40%'
		},
		{
			fieldLabel:'状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态',
			xtype:'numberfield',
			value:'0',
			name:'status',
			maxLength:10,
			anchor:'40%'
		}
		]
	});
}
