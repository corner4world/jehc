Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.fx.target.Sprite','Ext.layout.container.Fit', 'Ext.window.MessageBox']);
Ext.onReady(function () {
    /**xt_clustered_store**/
    var xt_clustered_store = Ext.create('Ext.data.JsonStore', {
        fields: ['month', 'data1', 'data2', 'data3', 'data4' ],
        data: [
            { month: 'Jan', data1: 20, data2: 37, data3: 35, data4: 4 },
            { month: 'Feb', data1: 20, data2: 37, data3: 36, data4: 5 },
            { month: 'Mar', data1: 19, data2: 36, data3: 37, data4: 4 },
            { month: 'Apr', data1: 18, data2: 36, data3: 38, data4: 5 },
            { month: 'May', data1: 18, data2: 35, data3: 39, data4: 4 },
            { month: 'Jun', data1: 17, data2: 34, data3: 42, data4: 4 },
            { month: 'Jul', data1: 16, data2: 34, data3: 43, data4: 4 },
            { month: 'Aug', data1: 16, data2: 33, data3: 44, data4: 4 },
            { month: 'Sep', data1: 16, data2: 32, data3: 44, data4: 4 },
            { month: 'Oct', data1: 16, data2: 32, data3: 45, data4: 4 },
            { month: 'Nov', data1: 15, data2: 31, data3: 46, data4: 4 },
            { month: 'Dec', data1: 15, data2: 31, data3: 47, data4: 4 }
        ]
    });
    /**xt_clustered**/
    var xt_clustered = Ext.create('Ext.chart.Chart',{
	    xtype: 'chart',
        height: 610,
        padding: '10 0 0 0',
        animate: true,
        shadow: false,
        style: 'background: #fff;',
        legend: {
            position: 'right',
            boxStrokeWidth: 0,
            labelFont: '12px Helvetica'
        },
        store:xt_clustered_store,
        insetPadding: 40,
        items: [{
            type  : 'text',
            text  : 'Bar Charts - Clustered Bars',
            font  : '22px Helvetica',
            width : 100,
            height: 30,
            x : 40, //the sprite x position
            y : 12  //the sprite y position
        }, {
            type: 'text',
            text: 'Data: Browser Stats 2012',
            font: '10px Helvetica',
            x: 12,
            y: 580
        }, {
            type: 'text',
            text: 'Source: http://www.w3schools.com/',
            font: '10px Helvetica',
            x: 12,
            y: 590
        }],
        axes: [{
            type: 'numeric',
            fields: 'data1',
            position: 'bottom',
            grid: true,
            minimum: 0,
            label: {
                renderer: function(v) { return v + '%'; }
            }
        }, {
            type: 'category',
            fields: 'month',
            position: 'left',
            grid: true
        }],
        series: [{
            type: 'bar',
            axis: 'bottom',
            title: [ 'IE', 'Firefox', 'Chrome', 'Safari' ],
            xField: 'month',
            yField: [ 'data1', 'data2', 'data3', 'data4' ],
            style: {
                opacity: 0.80
            },
            highlight: {
                fill: '#000',
                'stroke-width': 2,
                stroke: '#000'
            },
            tips: {
                trackMouse: true,
                style: 'background: #FFF',
                height: 20,
                renderer: function(storeItem, item) {
                    var browser = item.series.title[Ext.Array.indexOf(item.series.yField, item.yField)];
                    this.setTitle(browser + ' for ' + storeItem.get('month') + ': ' + storeItem.get(item.yField) + '%');
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
        title:'条形图报表',
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
        items:xt_clustered
    });
});