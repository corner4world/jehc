Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.fx.target.Sprite','Ext.layout.container.Fit', 'Ext.window.MessageBox']);
Ext.onReady(function () {
    /**xt_scatter_store**/
    var xt_scatter_store = Ext.create('Ext.data.JsonStore', {
            fields: ['x', 'y' ],
            data: [
                { x: 5, y: 20 },
                { x: 480, y: 90 },
                { x: 250, y: 50 },
                { x: 100, y: 33 },
                { x: 330, y: 95 },
                { x: 410, y: 12 },
                { x: 475, y: 44 },
                { x: 25, y: 67 },
                { x: 85, y: 21 },
                { x: 220, y: 88 },
                { x: 320, y: 79 },
                { x: 270, y: 32 }
            ]
        });
    /**xt_scatter**/
    var xt_scatter = Ext.create('Ext.chart.Chart',{
	    xtype: 'chart',
        height: 410,
        padding: '10 0 0 0',
        style: 'background: #fff',
        animate: true,
        shadow: false,
        store: xt_scatter_store,
        insetPadding: 40,
        axes: [{
            type: 'numeric',
            position: 'bottom',
            fields: 'x',
            grid: true
        }, {
            type: 'numeric',
            position: 'left',
            fields: 'y',
            grid: true
        }],
        items: [{
            type  : 'text',
            text  : 'Scatter Charts - Basic',
            font  : '22px Helvetica',
            width : 100,
            height: 30,
            x : 40, //the sprite x position
            y : 12  //the sprite y position
        }],
        series: [{
            type: 'scatter',
            xField: 'x',
            yField: 'y',
            showInLegend: true,
            markerConfig: {
                radius: 4
            },
            highlight: {
                fill: '#ccc',
                radius: 5,
                stroke: '#000',
                'stroke-width': 1
            },
            label: {
                display: 'over',
                font: '18px Arial',
                renderer: function(value, label, storeItem, item, i, display, animate, index) {
                    return storeItem.get('x') + ',' + storeItem.get('y');
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
        maximizable:true,
        title:'散点图报表',
        renderTo:Ext.getBody(),
        layout:'fit',
        tbar:[{
            text:'导出图表',
            handler:function(){
                Ext.MessageBox.confirm('提示', '确定要导出该图片吗?', function(choice){
                    if(choice == 'yes'){
                        xt_clustered.save({
                            type:'image/png'
                        });
                    }
                });
            }
        }],
        items:xt_scatter
    });
});