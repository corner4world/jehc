function initViewPortPanel(mainPanel){
	Ext.create('Ext.container.Viewport',{
    	layout:'border',
       	items:[leftPanel,mainPanel,eastPanel]
    }); 
}