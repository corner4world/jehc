Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.fx.target.Sprite','Ext.layout.container.Fit', 'Ext.window.MessageBox']);
Ext.onReady(function () {
    /**xt_animated_store**/
    var me = this,chart, timeAxis;
    var generateData = (function() {
        var data = [], i = 0,
        last = false,
        date = new Date(2011, 0, 1),
        seconds = +date,
        min = Math.min,
        max = Math.max,
        random = Math.random;
        return function() {
            data = data.slice(-6);
            data.push({
                date:  Ext.Date.add(date, Ext.Date.DAY, i++),
                visits: min(100, max(last? last.visits + (random() - 0.5) * 20 : random() * 100, 0)),
                views: min(100, max(last? last.views + (random() - 0.5) * 10 : random() * 100, 0)),
                users: min(100, max(last? last.users + (random() - 0.5) * 20 : random() * 100, 0))
            });
            last = data[data.length -1];
            return data;
        };
    })();
    var xt_animated_store = Ext.create('Ext.data.JsonStore', {
        fields: ['date', 'visits', 'views', 'users'],
        data:generateData()
    });
    /**xt_animated**/
    var xt_animated = Ext.create('Ext.chart.Chart',{
	    xtype: 'chart',
        id: 'myChartId',
        style: 'background: #fff',
        height: 410,
        padding: '40 0 0 0',
        store: xt_animated_store,
        shadow: false,
        animate: true,
        insetPadding: 40,
        items: [{
            type  : 'text',
            text  : 'Animated Chart',
            font  : '22px Helvetica',
            width : 100,
            height: 30,
            x : 40, //the sprite x position
            y : 15  //the sprite y position
        }],
        axes: [{
            type: 'numeric',
            minimum: 0,
            maximum: 100,
            position: 'left',
            fields: ['views', 'visits', 'users'],
            title: 'Number of Hits',
            grid: {
                odd: {
                    fill: '#dedede',
                    stroke: '#ddd',
                    'stroke-width': 0.5
                }
            }
        }, {
            type: 'time',
            position: 'bottom',
            fields: 'date',
            title: 'Day',
            dateFormat: 'M d',
            groupBy: 'year,month,day',
            aggregateOp: 'sum',
            constrain: true,
            fromDate: new Date(2011, 0, 1),
            toDate: new Date(2011, 0, 7),
            grid: true
        }],
        series: [{
            type: 'line',
            xField: 'date',
            yField: 'visits',
            shadow: false,
            axis: 'left',
            markerConfig: {
                radius: 4
            },
            style: {
                'stroke-width': 3
            }
        },{
            type: 'line',
            axis: 'left',
            xField: 'date',
            yField: 'views',
            markerConfig: {
                radius: 4
            },
            style: {
                'stroke-width': 3
            }
        },{
            type: 'line',
            axis: 'left',
            xField: 'date',
            yField: 'users',
            markerConfig: {
                radius: 4
            },
            style: {
                'stroke-width': 3
            }
        }]
    });
    chart = Ext.getCmp('myChartId'); // this.down('chart');
    var addNewData = function() {
        timeAxis = chart.axes.get(1);
        var gs = generateData();
        var toDate = timeAxis.toDate,
            lastDate = gs[gs.length - 1].date,
            markerIndex = chart.markerIndex || 0;
        if (+toDate < +lastDate) {
            markerIndex = 1;
            timeAxis.toDate = lastDate;
            timeAxis.fromDate = Ext.Date.add(Ext.Date.clone(timeAxis.fromDate), Ext.Date.DAY, 1);
            chart.markerIndex = markerIndex;
        }
        xt_animated_store.loadData(gs);
    };
    var task = Ext.TaskManager.start({
            run: addNewData,
            interval: 1000,
            repeat: 45
    });
    var win = Ext.create('Ext.Window', {
        width:800,
        height:400,
        minHeight:400,
        minWidth:550,
        hidden:false,
        maximizable:true,
        title:'动态图报表',
        renderTo:Ext.getBody(),
        layout:'fit',
        tbar:[{
            text:'导出图表',
            handler:function(){
                Ext.MessageBox.confirm('提示', '确定要导出该图片吗?', function(choice){
                    if(choice == 'yes'){
                        xt_animated.save({
                            type:'image/png'
                        });
                    }
                });
            }
        }],
        items:xt_animated
    });
});