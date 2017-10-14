function initViewPortPanel(mainPanel){
//	Ext.create('Ext.container.Viewport',{
//    	layout:'border',
//       	items:[leftPanel,mainPanel,eastPanel]
//    }); 
	var centersouthPanel = new Ext.Panel({
		region:'center',
		layout:'border',
		items:[mainPanel,eastPanel]
    });
	Ext.create('Ext.container.Viewport',{
		layout:'border',
       	items:[leftPanel,centersouthPanel]
    }); 
}