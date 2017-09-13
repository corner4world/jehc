$(function () {
    $('#tabs').addtabs();
    //clickAddTab('/index/desk.html','平台首页','home_index',false);
    initLockSystem();
})

function indexHome(){
	 $('#tab_home').addClass('active');
     $('#home').addClass('active');
     Addtabs.drop();
}
$(function () {  
	  $('#myTab a:last').tab('show');//初始化显示哪个tab  
	  
	  $('#myTab a').click(function (e) {  
	    e.preventDefault();//阻止a链接的跳转行为  
	    $(this).tab('show');//显示当前选中的链接及关联的content  
	  })  
	}) 

function clickAddTab(url,title,id,close){
	var tabNumbs = Addtabs.tabList();
	if(tabNumbs > 5){
		msgTishi("打开的选项卡标签不能超过6个，请关闭一些在操作!");
		return;
	}
	if(null == url || url == ''){
		return;
	}
	var closeable = close;
	if(closeable == false){
		closeable=false;
	}else{
		closeable=true;
	}
	var dt1 = nowTimestamp();
	Addtabs.add({
        id:id,
        title:title,
        close:closeable,
        url:"../"+url
    })
    loadXtIframeComplete(id,dt1,title);
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
	    /**新方法使用开始**/  
        scrollable:true,  
        scrollable:'x',
        scrollable:'y',
        /**新方法使用结束**/ 
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
	toastrBoot(1,'该功能暂未开放');
}

//关闭所有选项卡
function closeAllTab(){
	Addtabs.closeAll();
}

//关闭当前选项卡
function closeCruTab(){
	var id = $("li.active").children("a")[0].href.split("#")[1];
	if(id != 'home'){
		Addtabs.close(id);
	    Addtabs.drop();
	    $('#popMenu').fadeOut();
	}
}

function refreshCruTab(){
	var jehchref = $($("li.active").children("a")[0]).attr('jehchref');
	var jehcid = $($("li.active").children("a")[0]).attr('jehcid');
	if(null == jehchref){
		return;
	}
	Addtabs.add({'id':jehcid,'url':jehchref});
}

//关闭左侧选项卡
function closeLeftTab(){
	
}
//关闭右侧选项卡
function closeRightTab(){
	var tab_id = $("li.active").children("a")[0].href.split("#")[1];
	if(tab_id != 'home'){
	    $('#tab_' + tab_id).nextUntil().each(function () {
	        var id = $(this).attr('id');
	        if (id && id != 'tab_' + tab_id) {
	            Addtabs.close($(this).children('a').attr('aria-controls'));
	        }
	    });
	    Addtabs.drop();
	    $('#popMenu').fadeOut();
	}
}

///**
// * 增加标签页
// */
//function addTab(options) {
//    //option:
//    //tabMainName:tab标签页所在的容器
//    //tabName:当前tab的名称
//    //tabTitle:当前tab的标题
//    //tabUrl:当前tab所指向的URL地址
//    var exists = checkTabIsExists(options.tabMainName, options.tabName);
//    if(exists){
//        $("#tab_a_"+options.tabName).click();
//    } else {
//        $("#"+options.tabMainName).append('<li id="tab_li_'+options.tabName+'"><a href="#tab_content_'+options.tabName+'" data-toggle="tab" id="tab_a_'+options.tabName+'"><button class="close closeTab" type="button" onclick="closeTab(this);">×</button>'+options.tabTitle+'</a></li>');
//        //固定TAB中IFRAME高度
//        mainHeight = $(document.body).height() - 5;
//        var content = '';
//        if(options.content){
//            content = option.content;
//        } else {
//            content = '<iframe src="' + options.tabUrl + '" width="100%" height="'+mainHeight+'px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>';
//        }
//        $("#"+options.tabContentMainName).append('<div id="tab_content_'+options.tabName+'" role="tabpanel" class="tab-pane" id="'+options.tabName+'">'+content+'</div>');
//        $("#tab_a_"+options.tabName).click();
//    }
//}
// 
///**
// * 关闭标签页
// * @param button
// */
//function closeTab (button) {
//    //通过该button找到对应li标签的id
//    var li_id = $(button).parent().parent().attr('id');
//    var id = li_id.replace("tab_li_","");
//    //如果关闭的是当前激活的TAB，激活他的前一个TAB
//    if ($("li.active").attr('id') == li_id) {
//        $("li.active").prev().find("a").click();
//    }
//    //关闭TAB
//    $("#" + li_id).remove();
//    $("#tab_content_" + id).remove();
//};
///**
// * 判断是否存在指定的标签页
// * @param tabMainName
// * @param tabName
// * @returns {Boolean}
// */
//function checkTabIsExists(tabMainName, tabName){
//    var tab = $("#"+tabMainName+" > #tab_li_"+tabName);
//    return tab.length > 0;
//}


//判断iframe是否加载完毕
var xtIframe;
function loadXtIframeComplete(id,dt1,text){
	/**开启多个Tab目的**/
	xtIframe = document.getElementById("iframe_id_"+id);
	if(xtIframe == null){
		var dt2 = nowTimestamp();
		//执行监控页面信息操作
		loadinfo(text,dt1,dt2);
	}else{
		if(xtIframe.attachEvent){ 
			xtIframe.attachEvent("onload", function(){ 
				//执行监控页面信息操作
				loadinfo(text,dt1,dt2);
			}); 
		}else{ 
			xtIframe.onload = function(){ 
				var dt2 = nowTimestamp();
				//执行监控页面信息操作
				loadinfo(text,dt1,dt2);
			}; 
		} 
	}
}

//返回当前时间戳
function nowTimestamp(){
	return new Date().getTime();
}
function dt(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth()+1;
	var day = date.getDate();
	var hour = date.getHours();
	var minute = date.getMinutes();
	var second = date.getSeconds();
	var dts = year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second;
	return dts;
}
//执行监控页面信息操作
function loadinfo(text,dt1,dt2){
	Ext.Ajax.request({  
	    url:'../xtLoadinfoController/addXtLoadinfo',
	    params:{xt_loadinfo_begtime:dt1,xt_loadinfo_endtime:dt2,xt_loadinfo_modules:text},
	    success:function(response,opts){},
	    failure:function(response,opts){}
	});
}