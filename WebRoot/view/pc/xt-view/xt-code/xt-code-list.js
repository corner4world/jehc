var xtCodeform;
var ue;
Ext.onReady(function(){
	reGetWidthAndHeight();
	xtCodeform = Ext.create('Ext.FormPanel',{
		region:'north',
		autoScroll:false,
		headerPosition:'left',
		titleAlign:'left',
		title:'<font color="#ffffff">操作内容</font>',
		/**自定义样式**/
		header:{
			cls:'x-panel-header-defined'
		},
		bbar:[
			 {
				text:'执行一下',
				tooltip:'执行一下',
				minWidth:tbarBtnMinWidth,
				handler:function(){
					jsRun();
				}
			 }
		],
		html:'<iframe scrolling="auto" id="jsRunEditor" frameborder="0" width="100%" height="100%" src="../xtCodeController/loadXtCodeJsEditor"></iframe>'
	});
	Ext.create('Ext.Viewport',{
		layout:'fit',
		xtype:'viewport',
		items:[xtCodeform]
	});
});

var jsRunWin;
function jsRun(){
	var val = $("#jsRunEditor")[0].contentWindow.setTextareaValue();
	jsRunWin = Ext.create('Ext.Window',{
		layout:'fit',
		width:800,
		height:400,
		maximizable:true,
		minimizable:true,
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
		html:'<iframe scrolling="auto" id="jsRunEditorFrame" frameborder="0" width="100%" height="100%" src="about:blank" noresize></iframe>',
		buttons:[{
			text:'关闭',
			itemId:'close',
			handler:function(button){
				button.up('window').close();
			}
		}]
	});
	jsRunWin.show();
	var url = '../xtCodeController/jsRun';
	var html = '<form id="jsRunForm" action="../xtCodeController/jsRun" method="POST" target="_self"><input type="hidden" name="jsRunContent" id="jsRunContent"></form>';
	$("#jsRunEditorFrame")[0].contentWindow.document.write(html); //将表单写入iframe中
	$("#jsRunEditorFrame")[0].contentWindow.document.getElementById('jsRunContent').value = val;
	$("#jsRunEditorFrame")[0].contentWindow.document.getElementById('jsRunForm').submit();
}
