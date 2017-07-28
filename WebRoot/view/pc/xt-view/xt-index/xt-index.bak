document.onreadystatechange = overLoad; 
Ext.onReady(function(){
	reGetWidthAndHeight();
	function getChildMenuinfo(id){
		//1创建store
		var store = Ext.create('Ext.data.TreeStore', {  
		     root:{  
	              expanded:true  
	         },
	         proxy:{  
	                type:'ajax',  
	                url:'../index/index_menu.html',
	                extraParams:{id:id},
	                reader:{  
	                    type:'json',  
	                    rootProperty:'items'  
	                }  
	         }  
		});  
		//2创建treePanel
		var treePanel = Ext.create('Ext.tree.Panel',{   
	        store:store,  
	        layout:'fit',
	        autoEncode:true,
	        rootVisible:false, 
	        useArrows:collapsibleDefined,
	        xtype:'filtered-tree',
	        tbar:[
        		{
        		   width:220,
        		   xtype:'triggerfield',
        		   emptyText:'输入关键字（如“订单”等）',
        	       triggerCls:'x-form-clear-trigger',
        	       onTriggerClick:function(){
        	           this.reset();
        	       },
        	       listeners:{
        	           change:function(){
        	           	filterBy(treePanel,this.getValue(),'text');
        	           },
        	           buffer:250
        	       }
        		}
        	],
	        listeners:{  
	            beforeitemexpand:function(node,optd){
	                var id=node.data.id;  
	                store.setProxy({   
	                	   //异步从服务器上加载数据  extjs会自动帮我们解析  
	                       type:'ajax',  
	                       url:'../index/index_menu.html',  
	                       extraParams:{id:id}
	                 });  
	            },
	            itemclick:function(node,optd){
	            	var leaf = optd.data.leaf;
	            	if(leaf == true){
	            		menuClick(optd);
	            	}
	            }
	        }
		})
		return treePanel;
	}
	/**布局**/
	Ext.create('Ext.Viewport',{  
 		layout:'border',
		items:[{
			region:'west',
			title:'<font color=#ffffff>目录索引</font>',
			icon:indexNav,
			width:255,
			split:false,
			border:false,
			collapsible:collapsibleDefined,
			hideCollapseTool:hideCollapseToolFlag,
			/**
			floatable:false,
			 **/
			titleCollapse:true,
			/**自定义样式**/
			header:{
//				height:100,//也可以放开 也挺好的
				cls:'x-panel-header-defined'
			},
			/**
			collapsed:true,
			**/
            collapseDirection:'left',
            id:'leftPanel',
            layout:{
				type:'accordion',
				animate:true,
				activeOnTop:true
			}
		},{
			region:'center',
			layout:'border',
			items:[{
					region:'north',
					split:false,
					border:false,
					tools:[
					       {
			            	 	 width:220,
								 xtype:'textfield',
					             emptyText:"请输入关键字",
					             triggerCls:'x-form-ss-trigger', 
							     onTriggerClick:function(){search(this.value)},
							     listeners:{
									specialkey:function(field, e){
										if(e.getKey() == Ext.EventObject.ENTER){
											search(this.value);
										}
									}
								 },
					             style:{background:'#ffffff',marginRight:'0px',marginLeft:'0px'}
					       },
					       {
					        	 icon:indexLingdang,
				                 xtype:'button',
					             handler:function(button){
					             	msgTishi("该功能还未开放！");
								 },
					             style:{background:'#ffffff'}
					        },
					        {
					        	 icon:indexMail,
				                 xtype:'button',
					             handler:function(button){
					             	msgTishi("该功能还未开放！");
								 },
					             style:{background:'#ffffff'}
					        },
					        {
				            xtype:'button',
				            icon:indexlist,
					 		iconAlign:'top',
					 		style:{background:'#ffffff',marginRight:'0px'},
				            menu:[{
									tooltip:{title:'注销平台',width:80},
					                text:'注销',
					                glyph:0xf014,
								    handler:function(){
								     	loginout();
								    }
								 },
								 {
								 	handler:function(){
								 		initPerpetualcalendar()
								    },
								    text:'日历',
								    glyph:0xf073,
								    tooltip:{
					                 	title:'万年历',
					                 	width:80
					                }
								 },
								 {
								    text:'关闭浏览器缓存',
								    glyph:0xf0ec,
								    tooltip:{
					                 	title:'关闭浏览器缓存',
					                 	width:80
					                },
								    handler:function(){
								     	jQuery.ajaxSetup({cache:false});
								     	msgTishi("关闭浏览器缓存成功");
								    }
								 },
								 {
								    text:'开启浏览器缓存',
								    glyph:0xf0ec,
								    tooltip:{
					                 	title:'开启浏览器缓存',
					                 	width:80
					                },
								    handler:function(){
								     	jQuery.ajaxSetup({cache:true});
								     	msgTishi("开启浏览器缓存成功");
								    }
								 },
								 {
						             text:'修改密码',
						             glyph:0xf044,
						             handler:function(){
						             	updatePwd();
								     },
								     tooltip:{
					                 	title:'修改密码',
					                 	width:80
					                 }
								 },
								 {
						             handler:function(){
						             	changeTheme();
								     },
								     text:'更换主题',
								     glyph:0xf022,
								     tooltip:{
					                 	title:'更换主题',
					                 	width:80
					                 }
								 },
								 {
								 	handler:function(){
								 		 window.location.href=basePath+"/index/index.html";
								    },
								    text:'刷新整个页面',
								    glyph:0xf021,
								    tooltip:{
					                 	title:'刷新整个页面',
					                 	width:80
					                }
								 }
							 ]
					         },
					    	 {
				                xtype:'box', 
								width:35, 
								height:35, 
								cls:'borderRadius',
								margin:'2 5 4 0', 
								autoEl:{
									tag:'img',
									tooltip:{
						        		text:"当前用户 "+sys_pt_user_name,
					                 	width:150
					                },
									src:userIcon
								},
					        	style:{background:'#ffffff',marginRight:'0px'}
						      } 
			        ],
		            header:{
		            	itemPosition:0,
//		            	height:100,//也可以放开 也挺好的
				        items:[
				        {
				        	xtype:'label',
				        	cls:'label',
				        	text:sys_pt_index
				        }
			        ]
				}
			},{
				region:'center',
				xtype:'tabpanel',
				id:'tabpanelCenter',
				tabPosition:'top',
				items:[{
					title:'个人首页',
					icon:homeIcon,
					id:'tab_home',
					html:'<iframe scrolling="auto" id=xtIframehome frameborder="0" width="100%" height="100%" src="../index/desk.html"></iframe>'
				}],plugins:new Ext.ux.TabCloseMenu()
			}]
		},{
			title:"<font style='font-size:14px;'>"+sys_pt_index_foot+"</font>",
			header:{
                  titlePosition:2,
                  titleAlign:'center',
                  itemPosition:2,
			      items:[
			      {
			            xtype:'label',
			            id:'timeLabel',
			            style:{marginRight:'4px',marginLeft:'4px'},
			            text:dt()
			      },
			      {
			            /**ui:'default-toolbar',**/
			            xtype:'button',
			            id:'xtMessageBtn',
			            icon:messageIcon,
			            style:{background:'#90c258',marginRight:'0px',marginLeft:'10px'},
			            tooltip:{
		                 	title:'短消息'
		                },
			            handler:function(button){
							initXtMessageWin();
						}
			      },
			      {
			            xtype:'button',
			            icon:lockIcon,
			            tooltip:{
		                 	title:'锁屏'
		                },
			            style:{background:'#e3495a',marginRight:'4px',marginLeft:'2px'},
			            handler:function(button){
			            	initLockSystem(1);
							setCookie("syslock", '1', 240);
						}
			      }
			      ]
            },
			region:'south',
			xtype:'panel',
			height:'auto',
			collapsible:false,  
			split:false
		}]
 	});
 	setInterval(function(){Ext.getCmp('timeLabel').setText(dt());},1000);
	var menus;
	if(menuJSON != "" && menuJSON != null && menuJSON != "]"){
		menus = Ext.util.JSON.decode(menuJSON);
	}
    for(var i =0; i < menus.length; i++) {  
        //先获取分组显示(载入根节点菜单)
        var group = menus[i]; 
        var id=group.id;
        var menupanel={    
            xtype:'panel',  
            id:'leftPanel'+id,
            /**
            tools:[
	            {type:'refresh'}
	        ],
	        **/
            title:group.text,
            /**新方法使用开始**/  
            scrollable:true,  
            scrollable:'x',
            scrollable:'y',
            /**新方法使用结束**/ 
            bodyStyle:{    
                padding:'0px'
            },   
            border:false,
            frame:false,
            icon:group.icon,
            listeners:{  
                 'afterrender':function(){
	            } 
            }
        };  
        Ext.getCmp("leftPanel").add(menupanel); 
        var menuP = getChildMenuinfo(id);
    	Ext.getCmp("leftPanel"+id).add(menuP);
    }   
	//菜单点击事件
    function menuClick(node){
    	var dt1 = nowTimestamp();
        var tabpanelCenter = Ext.getCmp('tabpanelCenter');
        var url = node.data.url;
    	var text = node.data.text;
    	var id = node.data.id;
    	/**开启多个Tab目的**/
        var tabid = 'tab_' + id;
        var itemsLength = tabpanelCenter.items.length;
        if(tabpanelCenter.getComponent(tabid)){
        	tabpanelCenter.getComponent(tabid).isfrist = 1;
            tabpanelCenter.setActiveTab(tabid);
        }else{
        	if(itemsLength >= 7){
            	msgTishi("您开启的选项卡太多请关闭一些在打开");
            	return;
            }
        	showLoading("正在拼命载入中，请耐心等待...");
            var panel = Ext.create('Ext.Panel',{
                id:tabid,
                name:tabid,
                maskDiabled:true,
                title:text,
                closable:true,
                icon:node.data.icon,
                layoutOnTabChange:false,
                href:url,
                isfrist:0,
                fitToFrame:true,
                autoDestroy:true,
                closeAction:'destory', 
				html:'<iframe width=100% height=100% id="xtIframe'+id+'" name="xtIframe" src="../'+url+'" frameborder="0" style="overflow:hidden;"/>'
            });
            tabpanelCenter.add(panel).show();
            loadXtIframeComplete(tabid,id,dt1,text);
        }
    }
    Ext.QuickTips.init();
    initLockSystem();
});
//判断iframe是否加载完毕
var xtIframe;
function loadXtIframeComplete(tabid,id,dt1,text){
	/**开启多个Tab目的**/
	xtIframe = document.getElementById("xtIframe"+id);
	if(xtIframe == null){
		var dt2 = nowTimestamp();
		hideWaitMsg();
		//执行监控页面信息操作
		loadinfo(text,dt1,dt2);
	}else{
		if(xtIframe.attachEvent){ 
			xtIframe.attachEvent("onload", function(){ 
				var tabpanelCenter = Ext.getCmp('tabpanelCenter');
				if(tabpanelCenter.getComponent(tabid).isfrist == 0){
					var dt2 = nowTimestamp();
					hideWaitMsg();
					//执行监控页面信息操作
					loadinfo(text,dt1,dt2);
					tabpanelCenter.getComponent(tabid).isfrist = 1;
					return;
		        }
			}); 
		}else{ 
			xtIframe.onload = function(){ 
				var tabpanelCenter = Ext.getCmp('tabpanelCenter');
				if(tabpanelCenter.getComponent(tabid).isfrist == 0){
					var dt2 = nowTimestamp();
					hideWaitMsg();
					//执行监控页面信息操作
					loadinfo(text,dt1,dt2);
					tabpanelCenter.getComponent(tabid).isfrist =1;
					return;
		        }
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
//弹出更改主题窗体
var themeWin;
function changeTheme(){
	var themeForm = Ext.create('Ext.FormPanel',{
		xtype:'form',
		region:'center',
		waitMsgTarget:true,
		defaultType:'textfield',
		autoScroll:true,
		fieldDefaults:{
	        labelWidth:70,
	        labelAlign:"left",
	        flex:1,
	        margin:'4 5 4 5'
	    },
		items:[themeCombo]
	});
	themeWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:300,
		autoHeight:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		closable:false,
		title:'更换主题',
		items:themeForm,
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	themeWin.show();
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
		width:500,
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
	}else{
		if(flag == 1){
			lockWindow.show();
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

//////////////////panel展开收开始////////////
var collapsibleflag= 1;  
function collapsibleCE(){
	if (collapsibleflag=== 1) {  
        Ext.getCmp('leftPanel').collapse();//panel收缩
        Ext.getCmp('leftPanel').setTitle(sys_pt_index);
        collapsibleflag= 0;
    } else {  
        Ext.getCmp('leftPanel').expand();//panel展开
        Ext.getCmp('leftPanel').setTitle(sys_pt_index);
        collapsibleflag = 1;
    }
}
/////////////////panel展开结束//////////////
function search(keywords){
	addTabContent('xtSearchController/loadXtSearch?keywords='+encodeURI(keywords),'10000','关键字搜索结果',indexSearchIcon);
}


//////////////////初始化锁屏开始//////////////
var perpetualcalendarWin;
function initPerpetualcalendar(){
	reGetWidthAndHeight();
	perpetualcalendarWin = Ext.create('Ext.Window',{
		title:'万年历',
		layout:'fit',
		width:700,
		height:clientHeight,
		autoHeight:true,
		collapsible:true,
		closable:true,
		maximizable:true,
		draggable:true,
		modal:true,
		constrain:true,
		resizable:true,
		html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="../index/perpetualcalendar"></iframe>'
	});
	perpetualcalendarWin.show();
}
//////////////////万年历//////////////