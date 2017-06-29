var xtCodeform;
var ue;
Ext.onReady(function(){
	reGetWidthAndHeight();
	xtCodeform = Ext.create('Ext.FormPanel',{
		region:'north',
		autoScroll:false,
		title:'在线JS',
		headerPosition:'top',
		/**自定义样式
		header:{
			cls:'x-panel-header-defined'
		},
		**/
		fbar:[
			 {
				text:'blocked-out',
				tooltip:'切换至编辑器',
				minWidth:tbarBtnMinWidth,
				handler:function(){
					xtCodeform.setTitle("在线JS");
					$("#jsRunEditor")[0].contentWindow.location.href="../xtCodeController/loadXtCodeJsEditor";
				}
			 },
			 {
				text:'run',
				tooltip:'开始运行',
				minWidth:tbarBtnMinWidth,
				cls:'addBtn',
				handler:function(){
					jsRun();
				}
			 }
		],
		url:'../xtCodeController/jsRun',
		html:'<iframe scrolling="auto" id="jsRunEditor" frameborder="0" width="100%" height="100%" src="../xtCodeController/loadXtCodeJsEditor"></iframe>',
		items:[{
			xtype:'textarea',
			name:'jsRunContent',
			hidden:true,
			id:'jsRunContent', 
			style:'font-size:18px;',
			allowBlank:false,
			anchor:'100%'
		}]
	});
	Ext.create('Ext.Viewport',{
		layout:'fit',
		xtype:'viewport',
		items:[xtCodeform]
	});
});

var jsRunWin;
function jsRun(){
	xtCodeform.setTitle("运行结果");
	$("#jsRunEditor")[0].contentWindow.runJs();
//	var val = $("#jsRunEditor")[0].contentWindow.setTextareaValue();
//	sValue('jsRunContent',val);
//	if(xtCodeform.form.isValid()){   
//		xtCodeform.getForm().submit();          
//	}else{ 
//		msgTishi('请输入内容');//提示之后消失
//	}
	/**
	jsRunWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
		animateTarget:document.body,
		plain:true,
		modal:true,
		title:'运行结果',
		listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
		html:'<iframe scrolling="auto" id="jsRunEditor" frameborder="0" width="100%" height="100%" src="../xtCodeController/jsRun?jsRunContent='+val+'"></iframe>',
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	jsRunWin.show();
	**/
}
