Ext.require([
    'Ext.chart.*',
    'Ext.Window', 
    'Ext.fx.target.Sprite', 
    'Ext.layout.container.Fit', 
    'Ext.window.MessageBox'
]);
Ext.onReady(function () {
	var win;
	/**
    var xt_monitor_cpu_store = Ext.create('Ext.data.JsonStore', {
         fields: ['x_zon', 'xt_monitor_cpu_use_rate' ],
         data: [
             { x_zon: 'Jan', xt_monitor_cpu_use_rate: 20 },
             { x_zon: 'Feb', xt_monitor_cpu_use_rate: 20 },
             { x_zon: 'Mar', xt_monitor_cpu_use_rate: 190 },
             { x_zon: 'Apr', xt_monitor_cpu_use_rate: 18 }
         ]
     });
    **/
    var xt_monitor_cpu_store = Ext.create('Ext.data.JsonStore', {
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtMonitorCpuController/getXtMonitorCPU',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['x_zon','xt_monitor_cpu_use_rate'],
		autoLoad:true 
	});
    /**折线图**/
    new Ext.util.DelayedTask(function(){ 
    var xt_monitor_cpu_spline = Ext.create('Ext.chart.Chart',{
        xtype:'chart',
        height:450,
        padding:'10 0 0 0',
        animate:true,
        shadow:false,
        style:'background:#fff;',
        theme:'Category2',
        store:xt_monitor_cpu_store,
        insetPadding:40,
        items:[{
            type:'text',
            text:'CPU实时监控图表',
            font:'22px Helvetica',
            width:100,
            height:30,
            x:40,
            y:12
        }],
        axes:[{
            type:'radial',
            position:'radial',
            grid:true,
            minimum:0,
            maximum:100,
            majorTickSteps:4,
            fields:['xt_monitor_cpu_use_rate'],
            label:{
            	//renderer: Ext.util.Format.numberRenderer('0,0')
                renderer:function(v){return v + '%';}
            }
        }/**,{
            type:'category',
            position:'bottom',
            grid:true,
            fields:['x_zon'],
            label:{
                rotate:{
                    degrees:-45
                }
            }
        }**/],
        series:[{
            type:'radar',
            axis:'left',
            xField:'x_zon',
            yField:'xt_monitor_cpu_use_rate',
            style:{
                opacity:0.80
            },
            colors:["#115fa6","lightgrey"],
            highlight:{
                fill:'#000',
                'stroke-width':1,
                stroke:'red'
            },
            tips:{
                trackMouse:false,
                style:'background:#fff',
                height:20,
                renderer:function(storeItem, item) {
                    this.setTitle(storeItem.get('x_zon')+':' + storeItem.get('xt_monitor_cpu_use_rate') + '%');
                }
            }
        }]
    });
    win = Ext.create('Ext.Window', {
        width:800,
        height:450,
        minHeight:450,
        minWidth:550,
        hidden:false,
        closeAction:'hide',
		closable:false, 
        title:'监控CPU变化情况',
        headerPosition:'left',
        layout:'fit',
        listeners:{
			minimize:function(win,opts){
				if(!win.collapse()){
					win.collapse();
				}else{
					win.expand();
				}
			}
		},
        tbar:[{
            text:'导出图表',
            handler:function(){
                Ext.MessageBox.confirm('提示', '确定要导出该图片吗?',function(choice){
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
            }
        }],
        items:xt_monitor_cpu_spline
    });
    }).delay(3000);
    setInterval(function(){
		xt_monitor_cpu_store.reload();
	},5000);
});