Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.fx.target.Sprite','Ext.layout.container.Fit', 'Ext.window.MessageBox']);
Ext.onReady(function () {
    /**line-spline**/
    var line_spline_store = Ext.create('Ext.data.JsonStore', {
         fields: ['month', 'data1' ],
         data: [
             { month: 'Jan', data1: 20 },
             { month: 'Feb', data1: 20 },
             { month: 'Mar', data1: 190 },
             { month: 'Apr', data1: 18 },
             { month: 'May', data1: 18 },
             { month: 'Jun', data1: 17 },
             { month: 'Jul', data1: 160 },
             { month: 'Aug', data1: 16 },
             { month: 'Sep', data1: 16 },
             { month: 'Oct', data1: 16 },
             { month: 'Nov', data1: 15 },
             { month: 'Dec', data1: 150 }
         ]
     });
    /**折线图**/
    var line_spline = Ext.create('Ext.chart.Chart',{
        xtype: 'chart',
        height: 410,
        padding: '10 0 0 0',
        animate: true,
        shadow: false,
        style: 'background: #fff;',
        store: line_spline_store,
        insetPadding: 40,
        items: [{
            type  : 'text',
            text  : 'Area Charts - Basic Area',
            font  : '22px Helvetica',
            width : 100,
            height: 30,
            x : 40, //the sprite x position
            y : 12  //the sprite y position
        }, {
            type: 'text',
            text: 'Data: Browser Stats 2012 - Internet Explorer',
            font: '10px Helvetica',
            x: 12,
            y: 380
        }, {
            type: 'text',
            text: 'Source: http://www.w3schools.com/',
            font: '10px Helvetica',
            x: 12,
            y: 390
        }],
        axes: [{
            type: 'numeric',
            position: 'left',
            grid: true,
            fields: ['data1'],
            label: {
                renderer: function(v) { return v + '%'; }
            },
            minimum: 0
        }, {
            type: 'category',
            position: 'bottom',
            grid: true,
            fields: ['month'],
            label: {
                rotate: {
                    degrees: -45
                }
            }
        }],
        series: [{
            type: 'area',
            axis: 'left',
            xField: 'month',
            yField: 'data1',
            style: {
                opacity: 0.80
            },
            highlight: {
                fill: '#f5f5f5',
                'stroke-width': 2,
                stroke: '#fff'
            },
            tips: {
                trackMouse: true,
                style: 'background: #fff',
                height: 20,
                renderer: function(storeItem, item) {
                    this.setTitle(storeItem.get('month') + ': ' + storeItem.get('data1') + ' %');
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
        title:'折线图报表',
        renderTo:Ext.getBody(),
        layout:'fit',
        tbar:[{
            text:'导出图表',
            handler:function(){
                Ext.MessageBox.confirm('提示', '确定要导出该图片吗?', function(choice){
                    if(choice == 'yes'){
                        line_spline.save({
                            type:'image/png'
                        });
                    }
                });
            }
        },{
            text:'刷新',
            handler:function(){
                line_spline_store.reload();
            }
        }],
        items:line_spline
    });
});