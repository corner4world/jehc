Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.fx.target.Sprite','Ext.layout.container.Fit', 'Ext.window.MessageBox']);
var xtDisksList;
Ext.onReady(function () {
    xtDisksList = new Ext.data.Store({
		singleton:true, 
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtDisksController/getDisksInfo',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['rootName', 'freeSpace','totalSpace','usableSpace'],
		autoLoad:true 
	});
    var xt_pie = Ext.create('Ext.chart.Chart',{
	     	xtype:'chart',
            height:410,
            padding:'10 0 0 0',
            style:'background: #fff',
            animate:true,
            shadow:true,
            store:xtDisksList,
            insetPadding:40,
            legend:{
                field:'rootName',
                position:'bottom',
                boxStrokeWidth:0,
                labelFont:'12px Helvetica'
            },
            items:[{
                type:'text',
                text:'硬盘已使用空间大小',
                font:'22px Helvetica',
                width:100,
                height:30,
                x:40,
                y:12
            }],
            series:[{
                type:'pie',
                animate:true,
                angleField:'usableSpace',
                lengthField:'totalSpace',
                showInLegend:true,
                highlight:{
                    segment:{
                        margin:40
                    }
                },
                label:{
                    field:'rootName',
                    display:'outside',
                    calloutLine: true,
                    font:'14px Arial',
                    contrast:true
                },
                style:{
                    'stroke-width':1,
                    'stroke':'#fff'
                },
                tips:{
                    trackMouse:true,
                    renderer:function(storeItem, item) {
                        this.setTitle(storeItem.get('rootName') + ': <br>总空间大小：' + storeItem.get('totalSpace') + 'M<br>可用空间大小：'+ storeItem.get('freeSpace')+'M<br>已用空间大小：'+storeItem.get('usableSpace')+'M<br>是否可读：'+storeItem.get('isRead')+"<br>是否可写："+storeItem.get('isWrite'));
                    }
                }
            }]
    });
    var win = Ext.create('Ext.Window', {
        width:800,
        height:400,
        minHeight:400,
        minWidth:550,
        hidden:false,
        closeAction:'hide',
		closable:false, 
		headerPosition:'right',
        title:'饼图报表',
        renderTo:Ext.getBody(),
        layout:'fit',
        items:xt_pie,
        tbar:[{
            text:'刷新',
            tooltip:'刷新',
			minWidth:tbarBtnMinWidth,
			cls:'refeshBtn',
			icon:refreshIcon,
            handler:function(){
                xtDisksList.reload();
            }
        }]
    });
});