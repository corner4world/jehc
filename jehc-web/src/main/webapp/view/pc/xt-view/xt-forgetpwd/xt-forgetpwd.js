Ext.onReady(function(){
	xt_change_pwd_sex_combo = Ext.create('Ext.data.SimpleStore',{ 
        fields:['value','text'],  
		 data:[["0","男"],["1","女"]]
	});
	var xtChangePwdFormAdd = Ext.create('Ext.FormPanel',{
		xtype:'form',
		region:'center',
		defaultType:'textfield',
		/**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
		title:'<font color="white"><br>找回密码<br><br></font>',
		headerCssClass:'x-panel-header',
		fieldDefaults:{
			labelWidth:70,
			labelAlign:'left',
			flex:1,
			margin:'2 5 4 5'
		},
		items:[
		{
			fieldLabel:'用户名称',
			xtype:'textfield',
			name:'user_name',
			maxLength:64,
			allowBlank:false,
			anchor:'15%'
		},
		{
			fieldLabel:'登录账号',
			xtype:'textfield',
			name:'login_id',
			maxLength:255,
			allowBlank:false,
			anchor:'30%'
		},
		{
			fieldLabel:'性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别',
			xtype:'combo',
			emptyText:'请选择',
			allowBlank:false,
			store:xt_change_pwd_sex_combo,
			mode:'local',
			triggerAction:'all',
			editable:false,
			hiddenName:'sex',
			valueField:'value',
			displayField:'text',
			name:'sex',
			maxLength:4,
			anchor:'30%'
		},
		{
			fieldLabel:'手机号码',
			xtype:'textfield',
			name:'phone',
			allowBlank:false,
			maxLength:11,
			anchor:'30%'
		},
		{
			fieldLabel:'邮箱地址',
			xtype:'textfield',
			name:'mail',
			allowBlank:false,
			maxLength:255,
			anchor:'30%'
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
			anchor:'100%'
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
					src:basePath+"/deng/images/default/default.jpg"
				}
			}
		}
		],
		buttonAlign:'left',
		buttons:[{
					text:'提 交',
					handler:function(button){
						submitFormCallFn(xtChangePwdFormAdd,'../xtChangePwdController/addXtChangePwd',null,null,false,true,backIndex)
					}
				},{
					text:'返 回',
					handler:function(button){
						window.location.href=basePath;
					}
				}]
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:xtChangePwdFormAdd
	});
	/**初始化附件右键菜单开始 参数4为1表示拥有上传和删除功能 即新增和编辑页面使用**/
	initFileRight('xt_attachment_id','xt_attachment_id_pic',1,1);
	/**初始化附件右键菜单结束**/
});


function backIndex(form, action){
	Ext.Msg.confirm('提示',action.result.msg,function(btn){
		window.location.href=basePath;
	});
}


