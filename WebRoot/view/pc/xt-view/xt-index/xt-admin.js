$(function () {
    $('#tabs').addtabs();
    clickAddTab('/index/desk.html','平台首页','home_index',false);
    initLockSystem();
})
function clickAddTab(url,title,id,close){
	if(null == url || url == ''){
		return;
	}
	var closeable = close;
	if(closeable == false){
		closeable=false;
	}else{
		closeable=true;
	}
	Addtabs.add({
        id:id,
        title:title,
        close:closeable,
        url:"../"+url
    })
}

/**注销**/
function loginout(){
	Ext.Msg.confirm("提示", "确定退出平台？",function(btn){
		if(btn == 'yes'){
			Ext.Ajax.request({  
			    url:'../login/loginout',
			    success:function(response,opts){
			    	top.Ext.Msg.alert('提示', "注销登录平台用户成功!平台将您转发到登录页面!",function(){
						var win = top;
						if(window.opener != null) {win=opener.top; window.close();}
						win.location.href=basePath;
					});
			    },
			    failure:function(response,opts){
			    	msgTishi("注销登录平台用户失败!")
			    }
			});
		}
	});
}

/////////////修改密码开始///////////////////
var pwdWin;
var pwdForm;
function updatePwd(){
	pwdForm = Ext.create('Ext.FormPanel',{
		xtype:'form',
		region:'center',
		waitMsgTarget:true,
		defaultType:'textfield',
		id:'pwdForm',
		autoScroll:true,
		fieldDefaults:{
	        labelWidth:70,
	        labelAlign:"left",
	        flex:1,
	        margin:'4 5 4 5'
	    },
		items:[
		{
			fieldLabel:'原有密码',
			xtype:'textfield',
			inputType:'password',
			allowBlank:false,
			maxLength:50,
			anchor:'100%',
			name:'oldPwd',
			id:'oldPwd'
		},{
			fieldLabel:'新&nbsp;&nbsp;密&nbsp;&nbsp;码',
			xtype:'textfield',
			inputType:'password',
			allowBlank:false,
			maxLength:50,
			anchor:'100%',
	        name:"newPwd",
	        id:'newPwd'
		},{
			fieldLabel:'确认密码',
			xtype:'textfield',
			inputType:'password',
			allowBlank:false,
			maxLength:50,
			anchor:'100%',
		   	name:"surePwd",
		   	id:'surePwd'
		}]
	});
	pwdWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:400,
		autoHeight:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		closable:false,
		title:'修改密码',
		headerPosition:'left',
		items:pwdForm,
		buttons:[{
			text:'保存',
			itemId:'save',
			handler:function(button){
				var oldPwd = Ext.getCmp('oldPwd');
				var newPwd = Ext.getCmp('newPwd');
				var surePwd = Ext.getCmp('surePwd');
				if(pwdForm.form.isValid()){
					if(newPwd.getValue() != surePwd.getValue()){
						msgTishi("两次输入的密码不一样,请重新输入!");
						Ext.getCmp('pwdForm').form.reset();
						return Ext.getCmp('oldPwd').focus();
					}
					Ext.getCmp('pwdForm').form.submit({
						url:basePath+'/index/updatePwd',
						waitTitle:'提示',
						method:'POST',
						waitMsg:'正在操作中',
						success:function(form, action){
							top.Ext.Msg.alert('提示', "修改密码成功!请重新登录该平台",function(){
								var win = top;
								if(window.opener != null){win=opener.top; window.close();}
								win.location.href=basePath;
							});
						},
						failure:function(form, action) {
							Ext.example.msg('提示', action.result.msg,function(){
							Ext.getCmp('pwdForm').form.reset();
						});
					  }
					});
				}else{Ext.example.msg('提示', '请输入必填项');}
			}
		},{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	pwdWin.show();
	pwdWin.on('show', function(){
		setTimeout(function(){
			Ext.getCmp('oldPwd').focus();
		}, 200);
	},this);
}
//////////////////修改密码结束////////////////
//////////////////初始化锁屏开始//////////////
var lockForm;
var lockWindow;
function initLockSystem(flag){
	//解锁表单
	lockForm = Ext.create('Ext.FormPanel',{
		id:'lockForm',
		style:'padding:5px 5px 5px 5px',
		fieldDefaults:{
			labelWidth:60,
			labelSeparator:'',
			labelAlign:'top'
		},
		items:[{
				name:'password',
				inputType:'password',
				id:'password',
				xtype:'textfield',
				allowBlank:false,
				/** 不使用该方法
				fieldCls:'fieldPwd',
				cls:'pwd',
				**/
				cls:'pwd',
				trigger1Cls:'x-form-pwd-trigger', 
				msgTarget:'side',/**qtip、title、under、side、none**/
				emptyText:'请输入您的账号密码',
				maxLength:32,
				anchor:'100%',
				size:10,
				listeners:{
					specialkey:function(field, e) {
						if(e.getKey() == Ext.EventObject.ENTER){
							unlockSystem();
						}
					}
				}
			}]
	});
	//执行锁屏
	lockWindow = Ext.create('Ext.Window',{
		title:'用户<font color=red>'+sys_pt_user_name+'</font>已被锁定中...',
		layout:'fit',
		width:300,
		autoHeight:true,
		collapsible:true,
		closable:false,
		maximizable:false,
		draggable:false,
		modal:true,
		constrain:true,
		resizable:false ,
		items:[lockForm],
		listeners:{
			'show':function(obj) {
				Ext.getCmp('password').focus();
			}
		},
		buttons:[{
					text:'解锁',
					handler:function(){
						unlockSystem();
					}
				}]
	});
	if(getCookie("syslock") == 1){
		lockWindow.show();
		setCookie("syslock", '1', 240);
	}else{
		if(flag == 1){
			lockWindow.show();
			setCookie("syslock", '1', 240);
		}
	}
}
//////////////////初始化锁屏结束//////////////

//////////////////执行解锁功能开始////////////
function unlockSystem() {
	if(lockForm.form.isValid()){
		lockForm.form.submit({
			url:basePath+'/index/validatePassword',
			waitTitle:'提示',
			method:'POST',
			waitMsg:'正在验证密码中...',
			success:function(form, action){
				if(action.result.msg == "1"){
					lockForm.reset();
					lockWindow.close();
					setCookie("syslock", '0', 240);
				}else{
					msgTishi('账户密码不正确,请重新输入!');
					lockForm.reset();
					Ext.getCmp('password').focus();
				}
			}
		});
	}else{msgTishi('请输入解锁密码');}
}
//////////////////解锁结束///////////////////
//关键字搜索
function search(){
	clickAddTab('/xtSearchController/loadXtSearch?keywords='+encodeURI($('#keywords').val()),'关键字搜索结果','search_page_');
}

function updateUserPic(){
	msgTishi('暂未开放');
}