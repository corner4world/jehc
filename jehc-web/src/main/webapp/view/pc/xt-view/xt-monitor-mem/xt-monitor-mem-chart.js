Ext.require([
    'Ext.chart.*',
    'Ext.Window', 
    'Ext.fx.target.Sprite', 
    'Ext.layout.container.Fit', 
    'Ext.window.MessageBox'
]);
Ext.onReady(function () {
	reGetWidthAndHeight();
	var win;
    var xt_monitor_mem_store = Ext.create('Ext.data.JsonStore', {
		proxy:new Ext.data.HttpProxy({ 
			url:'../xtMonitorMemController/getXtMonitorMemCurrSy',
			reader:new Ext.data.JsonReader({
				root:'items',
				type:'json'
			})
		}),
		fields:['xt_monitor_memCurrSy','xt_monitor_memTime'],
		autoLoad:true 
	});
    new Ext.util.DelayedTask(function(){ 
    var xt_monitor_mem_spline = Ext.create('Ext.chart.Chart',{
        xtype:'chart',
        width:clientWidth*0.95,                    
		height:clientHeight, 
        animate:true,
        shadow:false,
        style:'background:#fff;',
        theme:'Category2',
        store:xt_monitor_mem_store,
        insetPadding:40,
        items:[{
            type:'text',
            text:'内存实时监控图表',
            font:'22px Helvetica',
            width:100,
            height:30,
            x:40,
            y:12
        }],
        axes:[{
            type:'numeric',
            position:'left',
            grid:true,
            minimum:0,
            fields:['xt_monitor_memCurrSy'],
            label:{
                renderer:function(v){return v + '%';}
            }
        },
        {
            type:'category',
            position:'bottom',
            grid:true,
            fields:['xt_monitor_memTime'],
            label:{
                rotate:{
                    degrees:-90
                }
            }
        }],
        series:[{
            type:'area',
            axis:'left',
            xField:'xt_monitor_memTime',
            yField:'xt_monitor_memCurrSy',
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
                    this.setTitle(storeItem.get('xt_monitor_memTime')+':' + storeItem.get('xt_monitor_memCurrSy') + '%');
                }
            }
        }]
    });
    win = Ext.create('Ext.Window', {
        width:clientWidth*0.8,                    
		height:clientHeight, 
        hidden:false,
        closeAction:'hide',
		closable:false, 
        title:'监控内存变化情况',
        renderTo:Ext.getBody(),
        layout:'fit',
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
        items:xt_monitor_mem_spline
    });
    }).delay(3000);
    setInterval(function(){
		xt_monitor_mem_store.reload();
	},5000);
});