Ext.onReady(function(){
	var data = [  
	      ["测试数据", "../deng/images/top/index_user.png", '10001', '销售部.........', "active"],  
	      ["测试数据", "../deng/images/top/index_user.png", '10002', '测试部', "active"],  
	      ["测试数据", "../deng/images/top/index_user.png", '10003', '市场部', ""],  
	      ["测试数据", "../deng/images/top/index_user.png", '10004', '保密局', "active"],  
	  
	      ["测试数据", "../deng/images/top/index_user.png", '10005', '实验室', ""],  
	      ["测试数据", "../deng/images/top/index_user.png", '10006', '人资部', "active"]  
	];  
	Ext.define('model_icon',{  
	    extend:'Ext.data.Model',  
	    fields:[  
	        { name:'name',type:'string'},  
	        { name:'pic',type:'string'},  
	        { name:'id',type:'string'},  
	        { name:'dep',type:'string'},  
	        { name:'checked',type:'string'}  
	    ]  
	});  
	var store_icon = Ext.create('Ext.data.Store',{  
	    model: 'model_icon',  
	    sortInfo:{  
	        field:'name',  
	        direction:'ASC'  
	    },  
	    data: data  
	});  
	var dataview = Ext.create('Ext.view.View',{  
	    deferInitialRefresh:false,  
	    store:store_icon,  
	    tpl:Ext.create('Ext.XTemplate',  
	        '<tpl for=".">',  
	            '<div class="card-box {id} {checked}">',  
	                '<div class="card-box-img">',  
	                    '<img src="{pic}" />',  
	                '</div>',  
	                '<div id={id} class="card-box-content">',  
	                    '<p>账户：{id}</p>',  
	                    '<p>姓名：{name}</p>',  
	                    '<p>部门：{dep}</p>',  
	                '</div><i></i>',  
	           '</div>',  
	        '</tpl>'  
	    ),  
	    listeners:{  
	        itemclick:function (view, record, items, index, e){  
	        	if(record.data.checked == 'active'){
	        		record.set('checked','')
	        	}else{
	        		record.set('checked','active')
	        	}
	            //切换“active”样式  
	            //Ext.select(".card-box " + record.data[2]).toggleCls("active");  
	        }  
	    },  
	    itemSelector:'div.card-box',//需要加上选择器  
	    multiSelect:false,  
	    autoScroll:true  
	});  
	var panel = Ext.create('Ext.Panel',{  
	    layout:'border',  
	    title:'资源视图列表',  
	    tools:[{xtype:'button',
	    		text:'返 回',
	    		handler:function(){document.location.href='../xtSourcesController/loadXtSources';}
	    	   },
	    	   {xtype:'button',
	    		text:'获取选择记录',
	    		handler:function(){getSelectedItems()}
	    	   }
	    	],
	    bodyStyle:{  
	        background:'#fff'  
	    },  
	    items:dataview
	});  
	Ext.create('Ext.Viewport',{
		layout:'fit',
		xtype:'viewport',
		items:[panel]
	});
});

function getSelectedItems(){
	 var sArray = [];  
     Ext.select('div.card-box.active').each(function () {//遍历选中（拥有active 样式）的div  
         sArray.push(this.query('.card-box-content')[0].id);  
     });  
     msgTishi("您选中了<strong>" + sArray.length + '</strong>条数据');
}