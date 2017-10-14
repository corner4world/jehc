Ext.require('Ext.chart.*');
Ext.require(['Ext.Window', 'Ext.fx.target.Sprite','Ext.layout.container.Fit', 'Ext.window.MessageBox']);
Ext.onReady(function () {
    /**line-spline**/
    var line_spline_store = Ext.create('Ext.data.JsonStore', {
         fields: ['theta', 'sin' ],
         data: [
             { theta: 0, sin: 0.00 },
             { theta: 10, sin: 0.17 },
             { theta: 20, sin: 0.34 },
             { theta: 30, sin: 0.50 },
             { theta: 40, sin: 0.64 },
             { theta: 50, sin: 0.77 },
             { theta: 60, sin: 0.87 },
             { theta: 70, sin: 0.94 },
             { theta: 80, sin: 0.98 },
             { theta: 90, sin: 1.00 },
             { theta: 100, sin: 0.98 },
             { theta: 110, sin: 0.94 },
             { theta: 120, sin: 0.87 },
             { theta: 130, sin: 0.77 },
             { theta: 140, sin: 0.64 },
             { theta: 150, sin: 0.50 },
             { theta: 160, sin: 0.34 },
             { theta: 170, sin: 0.17 },
             { theta: 180, sin: 0.00 },
             { theta: 190, sin: -0.17 },
             { theta: 200, sin: -0.34 },
             { theta: 210, sin: -0.50 },
             { theta: 220, sin: -0.64 },
             { theta: 230, sin: -0.77 },
             { theta: 240, sin: -0.87 },
             { theta: 250, sin: -0.94 },
             { theta: 260, sin: -0.98 },
             { theta: 270, sin: -1.00 },
             { theta: 280, sin: -0.98 },
             { theta: 290, sin: -0.94 },
             { theta: 300, sin: -0.87 },
             { theta: 310, sin: -0.77 },
             { theta: 320, sin: -0.64 },
             { theta: 330, sin: -0.50 },
             { theta: 340, sin: -0.34 },
             { theta: 350, sin: -0.17 },
             { theta: 360, sin: 0.00 }
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
               text  : 'Line Charts - Spline',
               font  : '22px Helvetica',
               width : 100,
               height: 30,
               x : 40, //the sprite x position
               y : 12  //the sprite y position
           }],
           axes: [{
               type: 'numeric',
               position: 'left',
               title: 'Sin (Theta)',
               grid: true,
               fields: 'sin',
               label: {
                   renderer: function(v) {
                       return Ext.util.Format.number(v, '0.00');
                   }
               }
           }, {
               type: 'numeric',
               position: 'bottom',
               title: 'Theta',
               grid: true,
               fields: 'theta',
               label: {
                   rotate: {
                       degrees: -45
                   }
               }
           }],
           series: [{
               type: 'line',
               axis: 'left',
               xField: 'theta',
               yField: 'sin',
               smooth: true,
               highlight: true,
               showMarkers: false
          }]
        /**
        axes:[{
            type:'Numeric',
            minimum:0,
            position:'left',
            fields:['sin'],
            title:'点击率',
         	minorTickSteps:1,
            grid:{
                odd:{
                    opacity:1,
                    fill:'#ddd',
                    stroke:'#bbb',
                    'stroke-width':0.5
                }
            }
        },{
            type:'Category',
            position:'bottom',
            fields:['theta'],
            title:'年月日'
        }],
        series:[{
            type:'line',//line
            highlight:{
                size:20,
                radius:7
            },
            tips:{
                trackMouse:true,
                width:80,
                height:40,
                renderer:function(storeItem, item) {
                    this.setTitle(storeItem.get('sin'));
                    this.update(storeItem.get('theta'));
                }
            },
            axis:'left',
         	smooth:true,//平滑线条
            fill:true,//填充面积
            xField:['theta'],
            yField:['sin'],
            markerConfig:{
                type:'circle',//cross是叉叉，这个是圆圈
                size:4,
                radius:4,
                'stroke-width':0
            }
        }]
        **/
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
        }],
        items:line_spline
    });
});