document.onreadystatechange = overLoad;
Ext.onReady(function(){
	if(getnavigator()==7 || getnavigator() == 6 || getnavigator() == 0){
		document.write("平台支持IE8及以上浏览器或谷歌，火狐，360等浏览器");
		return;
	}
	Ext.QuickTips.init();
	var panel = Ext.create('Ext.FormPanel',{
		title:"<font color='#ffffff'>"+sys_pt_login+"</font>",
		region:'center',
		id:'loginForm',
		width:400,
		height:525,
		border:true,
		titleAlign:'center',
		renderTo:Ext.getBody(),
		style:'padding:0px 5px 0px 5px',
		fieldDefaults:{
			labelWidth:60,
			labelSeparator:'',
			labelAlign:'top'
		},
		header:{
			height:70,
			cls:'x-panel-header-defined'
		},
		headerPosition:'top',
		/**
		header:{
		     items:[
		     {
	           ui:'default-toolbar',
	           xtype:'button',
	           tooltip:{title:'找回密码'},
	           handler:function(button){
	           		window.location.href=basePath+"/index/forget_pwd.html";
	           }
		     }
		     ]
        },
        title:sys_pt_index_foot,
        titleAlign:'center',
        **/
		items:[{
			fieldLabel:'<font style="font-size:18px;font-family:楷体;color:#4db3a5;">输入登录信息时，请注意周边环境</font>',
			xtype:'textfield',
			name:'userName',
			id:'userName',
			hideTrigger:false,//不隐藏触发按钮  
			triggerCls:'user-trigger',
			grow:true,
			blankText:'帐号不能为空,请输入!',
			emptyText:'请输入账户',
			maxLength:30,
			maxLengthText:'账号的最大长度为30个字符',
			allowBlank:false,
			anchor:'100%',
			msgTarget:'under',/**qtip、title、under、side、none**/
			listeners:{
				specialkey:function(field, e) {
					if(e.getKey() == Ext.EventObject.ENTER) {
						var userName = Ext.getCmp("userName").getValue();
						var password = Ext.getCmp("password").getValue();
						var VerifyCode = Ext.getCmp("VerifyCode").getValue();
						if (Ext.util.Format.trim(userName) == ""){
							Ext.getCmp('userName').focus();
						}else if(Ext.util.Format.trim(password) == ""){
							Ext.getCmp('password').focus();
						}else if(Ext.util.Format.trim(VerifyCode) == ""){
							Ext.getCmp('VerifyCode').focus();
						}else{
							login();
						}
					}
				}
			}
		},
		{
			id:'password',
			name:'password',
			xtype:'textfield',
			inputType:'password',
			blankText:'密码不能为空,请输入!',
			emptyText:'请输入密码',
			maxLength:20,
			maxLengthText:'密码的最大长度为20个字符',
			allowBlank:false,
			anchor:'100%',
			triggerCls:'pwd-trigger',
			msgTarget:'under',/**qtip、title、under、side、none**/
			listeners:{
				specialkey:function(field, e){
					if(e.getKey() == Ext.EventObject.ENTER){
						var userName = Ext.getCmp("userName").getValue();
						var password = Ext.getCmp("password").getValue();
						var VerifyCode = Ext.getCmp("VerifyCode").getValue();
						if (Ext.util.Format.trim(userName) == ""){
							Ext.getCmp('userName').focus();
						}else if(Ext.util.Format.trim(password) == ""){
							Ext.getCmp('password').focus();
						}else if(Ext.util.Format.trim(VerifyCode) == ""){
							Ext.getCmp('VerifyCode').focus();
						}else{
							login();
						}
					}
				}
			}
		},
		{
			xtype:"fieldcontainer",
			anchor:'100%',
			layout:'hbox',
			items:[{
					xtype:'textfield',
					name:'validateCode',
					maxLength:6,
					minLength:6,
					width:180,
					blankText:'验证码不能为空',
					emptyText:'请输入验证码',
					maxLengthText:'该项长度只能为6位',
					minLengthText:'该项长度只能为6位',
					id:"VerifyCode",  
					allowBlank:false,
					msgTarget:'under',/**qtip、title、under、side、none**/
					listeners:{
						specialkey:function(field, e) {
							if (e.getKey() == Ext.EventObject.ENTER) {
								var userName = Ext.getCmp("userName").getValue();
								var password = Ext.getCmp("password").getValue();
								var VerifyCode = Ext.getCmp("VerifyCode").getValue();
								if(Ext.util.Format.trim(userName) == ""){
									Ext.getCmp('userName').focus();
								}else if(Ext.util.Format.trim(password) == ""){
									Ext.getCmp('password').focus();
								}else if(Ext.util.Format.trim(VerifyCode) == ""){
									Ext.getCmp('VerifyCode').focus();
								}else{
									login();
								}
							}
						}
					}
			},
			{
				xtype:'panel',
				width:200,
				height:70,
	            html:'<img src="'+basePath+'/VerifyCodeServlet?\'+new Date()" id="safecode" onclick="this.src='+basePath+'/VerifyCodeServlet?\'+new Date()"/><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:clickYZM()">看不清请点击图片</a>'
			}]
		},
		{
			xtype:'checkbox',
			boxLabel:'记住我？',
			inputValue:"0",
			id:'readme',
			labelAlign:'right',
			name:'readme'
		},
		{
			xtype:'button',
			anchor:'100%',
            text:'登 录',
            scale:'large',
            /*ui:'default-toolbar',*/
            handler:function(){
				login();
			}
		}
		]
	});
	Ext.create('Ext.Viewport',{
		layout:'fit',
		xtype:'viewport',
		layout:{
	        align:'middle',
	        pack:'center',
	        type:'hbox'
	    },
		items:panel
	});
	Ext.getCmp('userName').focus();
	if(getCookie('readme') == 'readme'){
		Ext.getCmp('readme').setValue(true);
		Ext.getCmp('userName').setValue(getCookie('XTUSERNAMECOOKIE'));
		Ext.getCmp('password').setValue(getCookie('XTUSERPASSWORDCOOKIE'));
	}
});

/**
 * 提交登陆请求
 */
function login(){
	if(Ext.getCmp('loginForm').form.isValid()){
		Ext.getCmp('loginForm').form.submit({
			url:basePath+'/login/login',
			waitTitle:'提示',
			method:'POST',
			waitMsg:'正在验证您的身份,请稍候.....',
			success:function(form, action) {
				clearCookie('readme');
				clearCookie('XTUSERNAMECOOKIE');
				clearCookie('XTUSERPASSWORDCOOKIE');
				if(Ext.getCmp('readme').checked){
					if(null == getCookie('readme') || '' == getCookie('readme')){
						setCookie('readme','readme',240);
						setCookie('XTUSERNAMECOOKIE',Ext.getCmp('userName').getValue(),240);
						setCookie('XTUSERPASSWORDCOOKIE',Ext.getCmp('password').getValue(),240);
					}
				}
				delCookie('syslock');
				showLoading("身份校验成功，开始进入...");
				window.location.href=basePath+"/index/index.html";
			},
			failure:function(form, action) {
				try{
					if('undefined' == typeof(action.result.msg)){
						location.reload();
						return;
					}
					Ext.example.msg('提示',action.result.msg);
					Ext.getCmp("VerifyCode").setValue('');
					clickYZM();
				}catch(e){
					clickYZM();
					Ext.getCmp("VerifyCode").setValue('');
				}
			}
		});
	}else{
		Ext.example.msg('提示','请输入合法项!');
	}
}
//刷新验证码
function clickYZM(){
	document.getElementById('safecode').src = basePath+"/VerifyCodeServlet?"+new Date();
}