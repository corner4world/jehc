var xtLog4jContent;
Ext.onReady(function(){
	var xtLog4jFormEdit = Ext.create('Ext.FormPanel',{
		xtype:'form',
		region:'center',
	    html:'<iframe scrolling="auto" id="log4jEditor" frameborder="0" width="100%" height="100%" src="../xtLog4jController/loadLog4jEditor"></iframe>',
		buttonAlign:'right',
		buttons:[{
			text:'重 启',
			itemId:'save',
			handler:function(button){
				Ext.Msg.confirm('提示','确定重启该log4j配置？',function(btn){
					if(btn == 'yes'){
						submitFormCallBack(xtLog4jFormEdit,'../xtLog4jController/autoLoadLog4j',null,null,false,true,false,null,xtLog4jFormEdit);
					}
				});
			}
		}]
	});
	Ext.create('Ext.Viewport',{
		layout:'border',
		xtype:'viewport',
		items:xtLog4jFormEdit
	});
});
