Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.fx.target.Sprite','Ext.layout.container.Fit', 'Ext.window.MessageBox']);
Ext.onReady(function () {
    /**xt_pie_store**/
    var xt_pie_store = Ext.create('Ext.data.JsonStore', {
        fields: ['os', 'data1', 'data2' ],
        data: [
            { os: 'Android', data1: 68.3, data2: 300 },
            { os: 'iOS', data1: 17.9, data2: 200 },
            { os: 'Windows Phone', data1: 10.2, data2: 150 },
            { os: 'BlackBerry', data1: 1.7, data2: 90 },
            { os: 'Others', data1: 1.9, data2: 100 }
        ]
    });
    /**xt_scatter**/
    var xt_pie = Ext.create('Ext.chart.Chart',{
	     xtype: 'chart',
            height: 410,
            padding: '10 0 0 0',
            style: 'background: #fff',
            animate: true,
            shadow: true,
            store: xt_pie_store,
            insetPadding: 40,
            legend: {
                field: 'os',
                position: 'bottom',
                boxStrokeWidth: 0,
                labelFont: '12px Helvetica'
            },
            items: [{
                type  : 'text',
                text  : 'Pie Charts - Custom Slice Sizing',
                font  : '22px Helvetica',
                width : 100,
                height: 30,
                x : 40, //the sprite x position
                y : 12  //the sprite y position
            }, {
                type: 'text',
                text: 'Data: IDC Predictions - 2017',
                font: '10px Helvetica',
                x: 12,
                y: 380
            }, {
                type: 'text',
                text: 'Source: Internet',
                font: '10px Helvetica',
                x: 12,
                y: 390
            }],
            series  : [{
                type: 'pie',
                animate: true,
                angleField: 'data1', //bind angle span to visits
                lengthField: 'data2', //bind pie slice length to views
                showInLegend: true,
                highlight: {
                    segment: {
                        margin: 40
                    }
                },
                label: {
                    field: 'os',   //bind label text to name
                    display: 'outside', //rotate labels (also middle, out).
                    calloutLine: true,
                    font: '14px Arial',
                    contrast: true
                },
                style: {
                    'stroke-width': 1,
                    'stroke': '#fff'
                },
                // add renderer
//                renderer: function(sprite, record, attr) {
//                    var value = (record.get('data1') >> 0) % 9,
//                        color = [ "#94ae0a", "#115fa6", "#a61120", "#ff8809", "#ffd13e", "#a61187", "#24ad9a", "#7c7474", "#a66111"][value];
//                    return Ext.apply(attr, {
//                        fill : color
//                    });
//                },
                tips: {
                    trackMouse: true,
                    renderer: function(storeItem, item) {
                        this.setTitle(storeItem.get('os') + ': ' + storeItem.get('data1') + '%');
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
        title:'饼图报表',
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
        items:xt_pie
    });
});