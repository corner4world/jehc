Ext.onReady(function(){
	var xtLogbackFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		region:'center',
	    html:'<iframe scrolling="false" id="logbackEditor" frameborder="0" width="100%" height="100%" src="../xtLogbackController/loadXtLogbackEditor"></iframe>',
		buttonAlign:'right'
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:xtLogbackFormEdit
	});
});
