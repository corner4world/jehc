var xtMessageWin;
var xtMessageTreePanel;
var xtMessagePanel;
var xtMessageEastPanel;
function initXtMessageWin(){
	Ext.getCmp('xtMessageBtn').setDisabled(true);　
	reGetWidthAndHeight();
	initChattingPanel();
	initXtMessageTreePanel();
	if(xtMessageWin != null){
		xtMessageWin.show();
		return;
	}
	xtMessageWin = Ext.create('Ext.Window',{
		layout:'border',
		width:clientWidth*0.6,                    
		height:clientHeight*0.8,
		closable:false,
		tools:[
		{
			xtype:'button',
			text:'收缩',
			id:'ecBtn',
			handler:function(){
				if(this.text == '收缩'){
					xtMessageWin.collapse();
					this.setText('展开');
				}else{
					xtMessageWin.expand();
					this.setText('收缩');
				}
			}
		},
		{
			xtype:'button',
			text:'最小化',
			style:{marginRight:'4px',marginLeft:'4px'},
			handler:function(){
				xtMessageWin.hide();
				Ext.getCmp('xtMessageBtn').setDisabled(false);　
			}
		},
		{
			xtype:'button',
			text:'关闭',
			style:{marginRight:'4px',marginLeft:'4px'},
			handler:function(){
				xtMessageWin.close();
				xtMessageWin=null;
				Ext.getCmp('xtMessageBtn').setDisabled(false);　
			}
		}
		],
		title:'短消息信息',
		listeners:{
			minimize:function(win,opts){
				win.collapse();
			}
		},
		items:[
			xtMessagePanel,
			xtMessageTreePanel,
			{
				 xtype:'textfield',
				 hidden:true,
				 id:'xt_userinfo_id'
		    }]
	});
	xtMessageWin.show();
}

function initChattingPanel(){
	var htmlEditorHeight = clientHeight*0.24;
	if(getCookie("css") == 'crisp'){
		htmlEditorHeight = clientHeight*0.26;
	}
	var htmlEditor = Ext.create('Ext.form.HtmlEditor',{
        labelSeparator:"：",
		id:'sendMHtmlEditor',
        enableAlignments:false,
        enableColors:true,
        enableFont:false,
        enableFontSize:false,
        enableFormat:false,
        enableLinks:false,
        enableLists:true,
        enableSourceEdit:false,
		height:htmlEditorHeight,
        fontFamilies:["宋体", "隶书", "黑体"]
    });
	xtMessagePanel = Ext.create('Ext.FormPanel',{
        maskDiabled:true,
        region:'center',
        title:'您正在使用短消息...',
        layoutOnTabChange:true,
        autoScroll:false,
        items:[
        Ext.create('Ext.FormPanel',{
		    title:'<font color="green">请勿轻信汇款、中奖和话费充值等信息，谨防受骗。</font>',
		    collapsible:false,
		    height:(clientHeight*0.35),
		    border:false,
		    autoScroll:true,
			bodyStyle:'overflow-x:hidden; overflow-y:scroll',
			id:'MContent',
		    html:''
	    }),
	    Ext.create('Ext.FormPanel',{
	    	region:'south',
	    	collapsible:false,
	    	border:false,
			anchor:'100%',
	    	items:htmlEditor
	    })
        ],
        buttonAlign:'right',
		buttons:[
		{
			xtype:'button',
       		text:'发送...',
       		buttonAlign:'right',
       		id:'sendBtn',
       		handler:function(){
       			sendM();
       		}
		},
		{
			xtype:'button',
       		text:'清屏',
       		buttonAlign:'right',
       		handler:function(){
       			clearmsg();
       		}
		}
		]
    });
}
function initXtMessageTreePanel(){
	var xtMessageTreeStore = Ext.create('Ext.data.TreeStore', {  
	     root:{  
	     	  text:'我的同事',
	     	  expanded:true
         },
         proxy:{  
                type:'ajax',  
                url:'../xtUserinfoController/getChattingUserinfoList',
                reader:{  
                    type:'json',  
                    rootProperty:'items'  
                }  
         }  
	});  
	xtMessageTreePanel = Ext.create('Ext.tree.Panel',{ 
		region:'east',  
        store:xtMessageTreeStore,  
        width:230,
        rootVisible:true, 
        border:true,
        split:true,
        frameHeader:false,
        xtype:'filtered-tree',
        title:'导航目录',
        collapsible:true,
        tbar:[
      		{
      		   width:220,
      		   xtype:'triggerfield',
      		   emptyText:'输入关键字（如“邓”，“周”等）',
      	       triggerCls:'x-form-clear-trigger',
      	       onTriggerClick:function(){
      	           this.reset();
      	       },
      	       listeners:{
      	           change:function(){
      	           	filterBy(xtMessageTreePanel,this.getValue(),'text');
      	           },
      	           buffer:250
      	       }
      		}
      	],
        listeners:{ 
            itemclick:function(node,optd){
            	var leaf = optd.data.leaf;
            	if(leaf == true){
            		xtMessageMenuClick(optd);
            	}
            }
        }
	})
//	xtMessageEastPanel = Ext.create('Ext.Panel',{
//    	region:'east',
//    	collapsible:true,
//    	border:false,
//    	split:true,
//		width:220,
//		hideCollapseTool:true,
//		items:[{
//	        scrollable:true,  
//	        scrollable:'x',
//	        scrollable:'y',
//			items:xtMessageTreePanel
//		}]
//    })
}

//菜单点击事件
function xtMessageMenuClick(node){
	var text = node.data.text;
	var id = node.data.id;
	console.info(id);
	var xt_userinfo_id = Ext.getCmp('xt_userinfo_id').getValue();
	if(id != xt_userinfo_id){
		clearmsg();
		//并加装与该用户对话记录
		ajaxGetHis(id);
	}
	Ext.getCmp('xt_userinfo_id').setValue(id);
	xtMessagePanel.setTitle("正在与【"+text+"】聊天中");
}

function sendM(){
	var xt_userinfo_id = Ext.getCmp('xt_userinfo_id').getValue();
	if(xt_userinfo_id == null || xt_userinfo_id == ''){
		msgTishi("请选择聊天的用户!")
		Ext.getCmp('sendMHtmlEditor').focus();
		return;
	}
	var sendTime = dt();
	var sendMHtmlEditor = gValue('sendMHtmlEditor');
	if(sendMHtmlEditor == null || sendMHtmlEditor == ''){
		msgTishi("请输入聊天内容!");
		Ext.getCmp('sendMHtmlEditor').focus();
		return;
	}
	Ext.getCmp('sendBtn').setDisabled(true);　
	Ext.Ajax.request({  
		params:{xt_meesage_content:sendMHtmlEditor,to_id:xt_userinfo_id}, 
	    timeout:600000,/**设置超时时间10十分钟**/
	    method:'post',
	    url:'../xtMessageController/addXtMessage',
	    success:function(response,opts){
	    	var MContent = Ext.getCmp('MContent').body.dom.innerHTML;
			var MContentText = Ext.getCmp('MContent').body.dom.innerText;
			if(MContentText == null || MContentText == ''){
				MContent = "";
			}
			Ext.getCmp('MContent').body.update(MContent+"<br><font color='red'>【"+sendTime+"】</font><br>"+sendMHtmlEditor);
			sValue('sendMHtmlEditor','');
			var d=Ext.getCmp('MContent').body.dom;
			d.scrollTop = d.scrollHeight - d.offsetHeight+2;
			Ext.getCmp('sendBtn').setDisabled(false);　
	    },  
	    failure:function(response,opts){  
	    	Ext.getCmp('sendBtn').setDisabled(false);
	    	msgTishi("消息发送失败!");
	    }  
	});
}

//清空
function clearmsg(){
	Ext.getCmp('MContent').body.update("");
}

//获取历史记录
function ajaxGetHis(xt_userinfo_id){
	Ext.Ajax.request({  
		params:{to_id:xt_userinfo_id}, 
	    timeout:600000,/**设置超时时间10十分钟**/
	    method:'post',
	    url:'../xtMessageController/getXtMessageHisListByCondition',
	    success:function(response,opts){
	    	var respText = Ext.util.JSON.decode(response.responseText).items;
	    	var MContent = "";
	    	for(var i = 0; i < respText.length; i++){
	    		var userSay='';
	    		if(respText[i].to_id != xt_userinfo_id){
	    			userSay='我说：'
	    		}
	    		MContent = MContent + '<font color="red">'+userSay+'【'+respText[i].ctime+'】</font><br>'+respText[i].xt_meesage_content+"<br>";
	    	}
	    	Ext.getCmp('MContent').body.update(MContent+Ext.getCmp('MContent').body.dom.innerHTML);
			var d=Ext.getCmp('MContent').body.dom;
			d.scrollTop = d.scrollHeight - d.offsetHeight+2;
	    },  
	    failure:function(response,opts){  
	    	msgTishi("读取历史记录失败!");
	    }  
	});
}

//统计未读个数
function unReadCount(){
	Ext.Ajax.request({  
	    timeout:600000,/**设置超时时间10十分钟**/
	    method:'post',
	    url:'../xtMessageController/getXtMessageUnReadCount',
	    success:function(response,opts){
	    	var respText = Ext.util.JSON.decode(response.responseText).items;
	    	var count=0;
	    	for(var i = 0; i < respText.length; i++){
	    		count+=respText[i].count;
	    	}
	    	if(count > 0){
	    		Ext.getCmp('xtMessageBtn').setText("<font color=red>"+count+"</font>");
	    	}else{
	    		Ext.getCmp('xtMessageBtn').setText('');
	    	}
	    },  
	    failure:function(response,opts){}  
	});
}
setInterval(unReadCount,6000); 